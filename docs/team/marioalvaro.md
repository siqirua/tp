---
layout: default.md
title: "Mario Alvaro's Project Portfolio Page"
---

### Project: ModuLight

ModuLight is a web application to grade students in a module. 
The user interface will mainly be CLI.



* **Code contributed**
  * Create StudentScore model
    * Implement StudentScore model to store the students' score and other attributes.
  * Create EditStudentScoreCommand
    * Implement a command to edit StudentScore, regarding their attributes such as scores, comments, and tags.
    * Side note: Add and delete implementation is omitted due to redundancy as our code automatically adds and deletes them when students od graded components are added.
  * AutoGrading Feature
    * Create a command to automatically grade every student based on their total scores
    * Command consist of two method; percentile method, which calculate the statistical percentile and use them to rank the students; absolute score method, which grades the student based on their total score compared to the absolute passing score.
  * Testings
    * Implement thorough and detailed testings for every personally written code. The tests was written to accommodate most of the path possibilities.
    * Create a couple of utility class to help with testings, such as StudentScoreBuilder and more.
  * For further details, please refer to this: [RepoSense Link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=marioalvaro&breakdown=true)

* **Enhancements implemented**
  * To be added soon...
* **Contributions to the UG**
  * Added EditStudentScoreCommand and AutoGrade to UG.
  * Contribute to the parameter table for StudentScore and AutoGradeCommand.
* **Contributions to the DG**
  * Add implementation detail for AutoGrade Command, with the UML diagrams included.
* **Contributions to team-based tasks**
  * Edit Index.md to the correct format for example, by adding badges.
* **Review Contributions**
  * Rechecking the UG to find bugs.
* **Contributions beyond the project team**
  * Reviewed other team Repositories for PED.

### Contributions to User Guide
| Parameter | Description                                                                                                                                                                                       | Constraints                                                                                                                                                                                                                    | Valid Examples                                    | Invalid Examples            |
|-----------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------|-----------------------------|
| pg/       | Used in [autoGrade](#auto-grading-all-the-students) to determine the passing value of the grade                                                                                                   | At most 11 number, with each of them must be an integer. Furthermore, the value must be decreasing and cannot exceed 100 or below 0                                                                                            | 90 80 50 30 20, 0, 100                            | 101, -2, 90 70 75, 90 90 90 |                    |                            |
| ag/       | Used in [autoGrade](#auto-grading-all-the-students) to determine the grading method | One of the: p, percentile, Percentile, a, absolute, Absolute                                                                                                                                                                   | p, percentile, Percentile, a, absolute, Absolute | Asolut, persentil           |

### Edit student score: `editScore`

Edits a student’s mark for a certain graded component, based on the 1-based index of the student score shown in the Student Scores list.

Note: a StudentScore will be automatically added when a graded component is created or when a new student is added. Similarly, student scores will be automatically deleted when its associated graded component or student is deleted.

* 1 or more fields to be edited must be provided in the command.
* The index provided must be more than 0 and not exceed the number of student scores displayed in the Student Scores list.
* The mark given cannot exceed the maximum marks for that graded component.
* Please refrain from entering numbers with more than 2 decimal places of precision.

Format: `editScore INDEX [m/SCORE] [x/comment] [t/tags]`

* if the mark is being edited, the new mark should be more than 0 and not exceed the associated component's maximum marks.
* if no parameters except index are passed in, it will throw an error.

Examples: `editScore 7 m/57` assigns a mark of 57 for the seventh student score in the Student Scores list.

### Auto grading all the students: `autoGrade`

Automatically assign grades to all students based on their total score and
the automatic grading method.

Format: `autoGrade ag/METHOD pg/PASSING_VALUE`

There are 2 possible `METHOD`:
* Percentile Method: `p`, `percentile`, `Percentile`
  * Calculate students' grade based on the statistical percentile.
* Absolute Score Method: `a`, `absolute`, `Absolute`
  * Calculate students' grade based on the given passing grade values.
  * the absolute value is compared directly with the students' total score (in percentage of the maximum score possible).

The `PASSING_VALUE` are numbers that determine the boundary for each grade
* The structure of `PASSING_VALUE`: `[A+] [A] [A-] [B+] [B] [B-] [C+] [C] [D+] [D] [F]`
* Each bracket represents the boundary value for the grade.
  * For `percentile` method, it is the statistical percentile value.
  * For `absolute` method, it is the total score.
* It is **not** compulsory to fill all the `PASSING_VALUE`, but such approach would make students below the lowest given passing value to be graded `F`.
* Example: `pg/90 80 65 40 30`. This would correspond to:
  * Value `90` given to `A+`
  * Value `80` given to `A`
  * Value `65` given to `A-`
  * Value `40` given to `B+`
  * Value `30` given to `B`
  * Any Value below `30` will be given `F`

**Important Note:**
* The `autoGrade` command works on the filtered student list. This would allow for example, to grade students only compared to their own tutorial group. To automatically grade every student in the module, please use `findStu` command to display every student.

Example:
* `autoGrade ag/absolute pg/95 80 70 55 40 20`. This would automatically grade student by using absolute grade threshold. Student with total score `95%` above will be given `A+`, total score below `95%` and `90` above will be given `A`, and so on, while below `20%` will be given `F`.

### Contributions to Developer Guide

### Auto-grading

#### Implementation

The auto-grading command uses the help of `EditStudentScommand` and `SortStuCommand` to properly assign each grade to the students.
The `SortStuCommand` is used to find the grade threshold value for each grade, if the method used is by `percentile` (this will be explained later).
Additionally, it creates clearer result as it sorts the students by their total score inversely.
In a short manner, the mechanism works by finding the grade threshold for each grade and assigning the grade to each student by comparing
their total score to the previously found grade threshold.

There are 2 possible method of grading:
* Percentile Method: `percentile`
  * Calculate students' grade based on the statistical percentile. This will assign the grade for students above k-th percentile.
    `SortStuCommand` will be used to sort the students and find the students at the exact position of the grade threshold.
    Note that it will **round up** the index to take a more lenient approach. The total score of that student will be used as the grade threshold.
* Absolute Score Method: `absolute`
  * Calculate students' grade based on the given passing grade values.
  * The absolute value is compared directly with the students' total score (in percentage of the maximum score possible).

**Important Note:**
* The `autoGrade` command works on the filtered student list. This would allow for example, to grade students only compared to their own tutorial group. To automatically grade every student in the module, `findStu` command can be used to display every student.


Given below is an example usage scenario and how the auto-grading mechanism for percentile calculation behaves at each step.


Step 1. The user launch the application for the first time.

Step 2. The user creates the desired graded components, adds all the students in the cohort, and assign them with scores.

Step 3. The user then executes `autoGrade ag/percentile pg/95 70 65 50 40 30 20` to execute the auto-grading system, the `percentile`
keyword indicates that ModuLight grades based on the students' percentile compared to another. The value after `pg/` indicates
the top percentile for each corresponding grade threshold, i.e. `pg/[A+] [A] [A-] [B+] ...`.

<box type="info" seamless>

**Note:** The value for `ag/` can be type `absolute` which determines the grade based on the passing score of the student's total score.

</box>

This step will first trigger the parse function and several things will be executed
1. The string argument will be parsed into the grading method and the passing value.
2. `AutoGradeCommandParser#checkAutoGradeType()` then will parse the grading method string into AutoGradeType `PERCENTILE`.
3. `AutoGradeCommandParser#mapToFloat()` will parse the passing value string into an array of float. In this step, string that is not parsable will be checked and an exception will be thrown.
   Furthermore, values less than zero or more than 100 will cause an exception to be thrown as the total mark of a student is in percentage.
   Further check on values must be decreasing is also available as lower grades cannot have higher grade threshold.
4. The parser then will return a new `AutoGradeCommand` object.

Step 4. The `AutoGradeCommand` returned will then be executed and several other things will be executed
1. This step will first trigger the `sortStuCommand` and causes the filtered student list to be updated into the sorted form.
2. A check will be done to ensure that the inputted array of float does not pass the maximum number of values. An exception will be thrown otherwise.
3. As the grading method used in this example is `PERCENTILE`, it will then trigger `AutoGradeCommand#setGradeThresholdPercentile()` to be executed in order to calculate the
   grade threshold.
4. It will then create an `EditStudentDescriptor` for each student in the filtered list and the assigned grade.
   The grade is determined by comparing the student's total score and the grade threshold.
5. `EditStudentCommand` will be created and executed for each student and the grade will be added.


The following sequence diagram shows how the auto-grading mechanism works:
* The parser implementation (Command execution is hidden):

<puml src="diagrams/AutoGradeParserSequenceDiagram.puml"></puml>

* The command implementation :

<puml src="diagrams/AutoGradeCommandSequenceDiagram.puml"></puml>



**Use case: Edit a student score**

**MSS**

1. User requests to list student scores.
2. ModuLight shows a list of student scores.
3. User requests to edit the details of a specific student score.
4. ModuLight updates the detail of that student score with entered data.
5. ModuLight shows a list of updated student scores.

   Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

  * 3a1. ModuLight shows an error message.

    Use case resumes at step 2.

* 3b. There is some error in the entered data.

  * 3b1. ModuLight shows an error message.

  Use case resumes at step 2.

**Use case: Automatically grade students based on their total score**

**MSS**

1. User requests to automatically grade student using AutoGradeCommand.
2. Modulight automatically grade every student command based on their total score, grading method, and passing value.
3. Modulight automatically sort students based on their total score for convenience.

   Use case ends.

**Extensions**

* 1a. User request to use unsupported grading method.
  * 1a1. Modulight shows an error message and a list of supported grading method available.

    Use case ends

* 1b. User inputted non-decreasing values for passing value.
  * 1b1. Modulight shows an error message specifying that the values inputted is non-decreasing.

    Use case ends

* 1c. User inputted passing values outside the bound of 0 and 100 inclusively.
  * 1c1. Modulight shows an error message specifying that the values must be between 0 and 100 inclusively.

    Use case ends

* 1d. User inputted too many passing values.
  * 1d1. Modulight shows an error message specifying that there are too many passing values inputted.

    Use case ends
