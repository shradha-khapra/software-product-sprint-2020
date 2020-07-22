/**
  * This is the main javascript file for the portfolio page. Contains a showProjectDetail method.
  *
  * Copyright 2019 Google LLC
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  *  https://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  *
  * @author: shradha-khapra
  */

/**
  * Shows details of a specified project.
  * @param {!Event} event The event object received.
  * @return {void}
  * @throws {!Error} If the HTML id specified in the target element is incorrect.
  */
function showProjectDetail(event) {
  const descriptions =
      [
       "A Machine Learning Bot classifier used for segregation of Bots and Humans on Twitter during Indian socio-political uproar.",
       "A Machine Learning image classifier to compare accuracy of KNN (with SVM) model and Neural Networks model for character recognition.", 
       "A portal for organizing information and making appointments with healthcare professionals.", 
       "A Google Maps & Weather API based application for real-time weather info on Maps with additional functionalities.",
      ];

  // Add description to the page. 
  const descriptionDiv = document.createElement('div');
  const targetElement = event.target;

  const index = parseInt(targetElement.id, /* radix= */ 10);
  if(index < 0 || index >= descriptionDiv.length) {
    throw new Error("Description is not available for the given id.");
  }
  descriptionDiv.innerHTML = descriptions[index];
  targetElement.parentElement.appendChild(descriptionDiv);
}

window.onload = showComments();

/**
 * Shows comments on the page.
 */
function showComments() {
  fetch('/add-comment').then(response => response.json()).then((comments) => {
    
    const commentsListElement = document.getElementById('CommentsBoard');
    
    comments.forEach((comment) => {
      commentsListElement.appendChild(
      createListElement(`${comment.message}\n - by ${comment.name}`));
    });

  });
}

/**
 * Creates a list of comments and displays it to main page.
 * @params {string} text
 * @return {!Element}
 */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}

google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback(drawChart);

/**
 * Creates a chart for programming languages and adds it to the page.
 */
function drawChart() {
  const data = google.visualization.arrayToDataTable([
    ['Language', 'Proficiency', { role: 'style' }],
    ['Java', 80, 'color: blue'],      
    ['C++', 70, 'color: blue'],          
    ['Golang', 50, 'color: blue'],
    ['Python', 30, 'color: blue' ], 
  ]);
  const options = {
    title: 'My proficiency in various proramming languages',
    chartArea: {width: '50%'},
    hAxis: {
      title: 'Proficiency (out of 100)',
      minValue: 0
    },
    vAxis: {
      title: 'Language'
    }
  };
  const chart = new google.visualization.BarChart(document.getElementById('LangChartContainer'));
  chart.draw(data, options);
}