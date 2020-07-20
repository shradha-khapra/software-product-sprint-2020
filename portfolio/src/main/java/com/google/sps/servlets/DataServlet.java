// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.gson.Gson;
import java.util.*;

@WebServlet("/add-comment")
public class DataServlet extends HttpServlet {
  //This is a class to store a comment object.  
  private class Comment {
    String message, name;
    public Comment(String message, String name){
      this.message = message;
      this.name = name;
    }
  }
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    addCommentToDataStore(request);

    response.setContentType("text/html;");
    response.sendRedirect("/index.html");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Query query = new Query("Comment");
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);
 
    List<Comment> comments = getDataStoreComments(results);

    String json = convertToJsonUsingGson(comments);
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  /**
  * Gets comments from data store.
  */
  private List<Comment> getDataStoreComments(PreparedQuery results) {
    List<Comment> comments = new ArrayList<>();
    for (Entity entity : results.asIterable()) {
      String message = (String) entity.getProperty("message");
      String name = (String) entity.getProperty("name");
      Comment comment = new Comment(message, name);
      comments.add(comment);
    }
    return comments;
  }

  /**
  * Adds the comments to the data store.
  */
  private void addCommentToDataStore(HttpServletRequest request){
    String nMessage = request.getParameter("message");
    String nName = request.getParameter("name");

    if(nName.length() == 0)
      nName = "Anonymous";
    if(nMessage.length() == 0)
      nMessage = "Somebody left me a blank message:')";

    Entity nComment = new Entity("Comment");
    nComment.setProperty("message", nMessage);
    nComment.setProperty("name", nName);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(nComment);
  }
  
  /**
  * Converts comments into a JSON string using the Gson library.
  */
  private String convertToJsonUsingGson(List<Comment> comments) {
    Gson gson = new Gson();
    String json = gson.toJson(comments);
    return json;
  }
}