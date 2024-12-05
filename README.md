# SYSC4806 Group 31 Mini Survey Monkey

## Status of Project
Currently, the web application pulls stored surveys from the backend database and then displays a list of them in
the homepage. A user can click on one of the surveys and then display the survey. A user can create an account, and log 
in to the created account. A registered user gets access to a dashboard which lists their created surveys, and the
current results of the survey (at the time of opening the page). A registered user can create a survey, which adds it to
the survey list and the dashboard list. All planned websocket-based features were added: the automatic closure of open
survey sessions if the creator of a survey closes the survey from their dashboard, and the live updating of a special 
live dashboard when a user submits a response, The home page also updates its survey list if a survey's status updates.
All pages have been styled, trying to maintain a cohesive design language.


## Build and Deploy Statuses
[![Build Status (Java CI With Maven)](https://github.com/douglytle/group31-minisurveymonkey/actions/workflows/maven.yml/badge.svg)](https://github.com/douglytle/group31-minisurveymonkey/actions/workflows/maven.yml)

[![Deploy Status (Azure App Service)](https://github.com/douglytle/group31-minisurveymonkey/actions/workflows/main_mini-surveymonkey-group31.yml/badge.svg)](https://github.com/douglytle/group31-minisurveymonkey/actions/workflows/main_mini-surveymonkey-group31.yml)

## Diagrams
### UML Diagram of Model
![group31-minisurveymonkey-UML-diagram-of-model.png](diagrams%2Fgroup31-minisurveymonkey-UML-diagram-of-model.png)

### Diagram of Database Schema
![SYSC4806-G31-SurveyMonkey.png](diagrams%2FSYSC4806-G31-SurveyMonkey.png)
