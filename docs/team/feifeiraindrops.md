---
layout: default.md
title: "Yufei Sun's Project Portfolio Page"
---

### Project: ModuLight

ModuLight is a desktop application that uses Command Lined Interface(CLI) to manage student, scores and graded components. 

* **New Feature**: `addStu` - Add a student to the database
    * **What it does**: Adds a student to the database. An empty score of the student will be added to all existing 
  graded components as well. It requires the student name, student id, and email, while the tutorial group and tag are optional.
    * **Justification**: A student might be added after the graded components are set up, thus it is necessary to add 
    scores to graded components. Furthermore, a tutorial group is optional as it might be undetermined at the start of the semester.
    * **Highlights**: Implementing this feature requires the creation of new classes such as tutorial groups and student id.
  It requires a good understanding to make sure that they only accept the correct inputs.


* **New Feature**: `editStu` - edit the student information
    * **What it does**: Edit a student's name, student id, email, tutorial group or tag, as indicated by the index. All student scores related to this student wil be updated as well.
    * **Justification**: There might be changes to a student's information.


* **New Feature**: `deleteStu` - delete a student and related student scores from the database
    * **What it does**: Delete a student as all related student scores as indicated by the index.
    * **Justification**: A student can drop the module.


* **New Feature**: `findStu` - find a student based on the keywords
    * **What it does**: Find a student in the database based on the name, student id, tutorial group, email or tag keywords. 
  All student scores related to the student will be displayed as well. All graded components are displayed as they are all relevant.
    * **Justification**: The user might be interested to find the students with one or many attributes. Allowing search 
  by attributes increases the flexibility of the search. Since student score is related to students, they are also displayed so that the user does not need rto do another search for them.
    * **Highlights**: Implementing this feature requires a good design of the algorithm and understanding or the project models to show the correct student and student scores.


* **New Feature**: `findScore` - find a student score based on the keywords
    * **What it does**: Find a student score in the database based on graded component name, student name, student id, tutorial group, email or tag keywords.
      The student and graded component list will be emptied.
    * **Justification**: A student score is related to one graded component and one student, thus it can ab found by keywords of both classes. 
  We assume that the user is only interested in the particular student scores when using this command. If they are interested in the students and graded components as well, findStu or findComp should be used instead.


* **New Feature**: `findComp` - find a graded component based on the keywords
    * **What it does**:  Find a graded component in the database based on component name. All students are displayed as they are all relevant.
      All student scores related to the component will be displayed as well.
    * **Justification**: Since student score is related to students, they are also displayed so that the user does not need rto do another search for them.

* **New Feature**: `listAll` - list all students, student scores and graded components
  * **What it does**:  Displays all students, student scores and graded components in the respective lists
  * **Justification**: This feature is useful in resetting the display after a find command
  
  

* **Code contributed**:
    * Highlights: designed and implemented tutorial group and student id classes, which are unique to the project
    * [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=feifeiraindrops&tabRepo=AY2324S1-CS2103T-W08-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Project management**:
    * Setting up team repository, website and relevant tools to be used
    * Setting up zoom for weekly meeting


* **Documentation**:
    * User Guide:
      * Documented the CRUD features for student and find features
      * Organised and formatted the content
      * Added comprehensive UI demonstration
  
    * Developer Guide:
      * Documented implementation of the logic component
      * Documented implementation of addStu, editStu, findStu commands
      * Documented manual test of addStu, editStu, findStu commands
      * Updated UML diagrams for proposed implementation and better oop


* **Testing**:
    * Implemented testing for models, commands and parsers related to student CRUD features, the find features and list feature.


* **Community**:
    * Reviewed 16 PRs
      * 2 with non-trivial comment



