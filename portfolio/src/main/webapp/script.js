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
 * Adds a random greeting to the page.
 */
// function addRandomGreeting() {
//   const greetings =
//       ["What we know is a drop and what we don't know is an ocean.", "Beets, Bears, Battlestar Galactica", "To live is to risk it all.", "People don't change. They become more of who they are."];

//   // Pick a random greeting.
//   const greeting = greetings[Math.floor(Math.random() * greetings.length)];

//   // Add it to the page.
//   console.log("this is a function for random quotes");
//   const greetingContainer = document.getElementById('greeting-container');
//   greetingContainer.innerText = greeting;
// }

function addProjectDetail(buttonId) {
  const description =
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
      descriptionDiv.innerText = description[0];
      break;
    case "b":
      descriptionDiv.innerText = description[1];
      break;
    case "c":
      descriptionDiv.innerText = description[2];
      break;
    case "d":
      descriptionDiv.innerText = description[3];
      break;
    default:
      descriptionDiv.innerText = "OOPS! Something went wrong.";
  }

}
