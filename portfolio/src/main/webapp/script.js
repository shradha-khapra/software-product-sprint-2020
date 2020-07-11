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
  */
function showProjectDetail(event) {
  const descriptions =
      [
       "A Machine Learning Bot classifier used for segregation of Bots and Humans on Twitter during Indian socio-political uproar.",
       "A Machine Learning image classifier to compare accuracy of KNN (with SVM) model and Neural Networks model for character recognition.", 
       "A portal for organizing information and making appointments with healthcare professionals.", 
       "A Google Maps & Weather API based application for real-time weather info on Maps with additional functionalities."
      ];

  // Add description to the page. 
  const descriptionDiv = document.createElement('div');
  const targetElement = event.target;

  switch(targetElement.id) {
    case "1":
      descriptionDiv.innerHTML = descriptions[0];
      break;
    case "2":
      descriptionDiv.innerHTML = descriptions[1];
      break;
    case "3":
      descriptionDiv.innerHTML = descriptions[2];
      break;
    case "4":
      descriptionDiv.innerHTML = descriptions[3];
      break;
    default:
      descriptionDiv.innerHTML = "OOPS! Something went wrong.";
  }
  
  targetElement.parentElement.appendChild(descriptionDiv);

}
