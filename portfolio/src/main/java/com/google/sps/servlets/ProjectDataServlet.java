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

import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * Servlet that stores votes for projects & returns the same.
 */
@WebServlet("/vote-project")
public class ProjectDataServlet extends HttpServlet {

  private static final String REQUEST_PARAM_NAME = "project";
  private Map<String, Integer> projectVotes = new HashMap<>();

  /**
   * Handles server side GET requests.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    Gson gson = new Gson();
    String json = gson.toJson(projectVotes);
    response.getWriter().println(json);
  }

  /**
   * Handles server side POST requests.
   */
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String project = request.getParameter(REQUEST_PARAM_NAME);
    int currentVotes = projectVotes.containsKey(project) ? projectVotes.get(project) : 0;
    projectVotes.put(project, currentVotes + 1);

    response.sendRedirect("/index.html");
  }
}
