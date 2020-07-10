/**
  * This is the main javascript file for the portfolio page. Contains a showProjectDetail method.
  * @author: shradha-khapra
  */
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

/**
  * Shows details of a specified project.
  * @param {HTMLElement.itemId} buttonId The ID of the button that was clicked to invoke the function.
  * @return {void}
  */
function showProjectDetail(buttonId) {
  const descriptions =
      [
       "A Machine Learning Bot classifier used for segregation of Bots and Humans on Twitter during Indian socio-political uproar.",
       "A Machine Learning image classifier to compare accuracy of KNN (with SVM) model and Neural Networks model for character recognition.", 
       "A portal for organizing information and making appointments with healthcare professionals.", 
       "A Google Maps & Weather API based application for real-time weather info on Maps with additional functionalities."
      ];

  // Add description to the page. 
  const descriptionDiv = document.getElementById(buttonId+"-div");

  switch(buttonId) {
    case "a":
      descriptionDiv.innerHTML = descriptions[0];
      break;
    case "b":
      descriptionDiv.innerHTML = descriptions[1];
      break;
    case "c":
      descriptionDiv.innerHTML = descriptions[2];
      break;
    case "d":
      descriptionDiv.innerHTML = descriptions[3];
      break;
    default:
      descriptionDiv.innerHTML = "OOPS! Something went wrong.";
  }

}
