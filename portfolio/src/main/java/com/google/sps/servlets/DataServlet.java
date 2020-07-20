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

/** 
 * Servlet that stores/returns comments to/from a data store.
 */
@WebServlet("/add-comment")
public class DataServlet extends HttpServlet {
  /** 
   * This class represents a comment.
   */  
  private static class Comment {
    private final String message;
    private final String name;
    public Comment(String message, String name) {
      this.message = message;
      this.name = name;
    }
    
    public String getMessage() {
      return this.message;
    }

    public String getName() {
      return this.name;
    }
  }

  private static final String ENTITY_MESSAGE_PROPERTY_NAME = "message";
  private static final String ENTITY_NAME_PROPERTY_NAME = "name";
  private static final String ENTITY_NAME = "Comment";
  
  /**
   * Server side handler for POST requests.
   */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    addCommentToDataStore(request);

    response.setContentType("text/html;");
    response.sendRedirect("/index.html");
  }

  /**
   * Server side handler for GET requests.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Query query = new Query(ENTITY_NAME);
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery dsQueryResults = datastore.prepare(query);
 
    List<Comment> comments = getDataStoreComments(dsQueryResults);

    String json = convertToJsonUsingGson(comments);
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  /**
   * Gets comments from data store.
   */
  private List<Comment> getDataStoreComments(PreparedQuery dsQueryResults) {
    List<Comment> comments = new ArrayList<>();
    for (Entity entity : dsQueryResults.asIterable()) {
      String message = (String) entity.getProperty(ENTITY_MESSAGE_PROPERTY_NAME);
      String name = (String) entity.getProperty(ENTITY_NAME_PROPERTY_NAME);
      Comment comment = new Comment(message, name);
      comments.add(comment);
    }
    return comments;
  }

  /**
   * Adds the comments to the data store.
   */
  private void addCommentToDataStore(HttpServletRequest request) {
    String nMessage = request.getParameter(ENTITY_MESSAGE_PROPERTY_NAME);
    String nName = request.getParameter(ENTITY_NAME_PROPERTY_NAME);

    if(nName.length() == 0) {
      nName = "Anonymous";
    }
      
    if(nMessage.length() == 0) {
      nMessage = "Somebody left me a blank message:')";
    }

    Entity nComment = new Entity(ENTITY_NAME);
    nComment.setProperty(ENTITY_MESSAGE_PROPERTY_NAME, nMessage);
    nComment.setProperty(ENTITY_NAME_PROPERTY_NAME, nName);

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