import _ from 'lodash';
import Point from './es6/Point.js';

import RupFeedback from 'uda-rup/src/rup.feedback.js';

function component () {
  let element = document.createElement('div');

  /* lodash is required for the next line to work */


  let body = document.querySelector('body');
  body.textContent = 'Good point: ' + new Point(11, 39);

  element.innerHTML = _.join(['Hello','webpack'], ' ');

  return element;
}

document.body.appendChild(component());
