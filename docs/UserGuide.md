---
layout: default.md
title: "User Guide"
pageNav: 3
---

# LumiNUS User Guide

LumiNUS is a **desktop app for managing students' grades, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, LumiNUS can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `luminus.jar` from [here](https://github.com/se-edu/addressbook-level3/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

## Command Format

| Notes                                                                                           | Explanation                                                                  | Examples                                                                                                                  |
|-------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|
| Words in UPPER_CASE                                                                             | These are parameters that are supplied by the user                           | `addStu s/STUDENT_NO n/NAME…` can be used as `addStu s/A1234567Z n/John…`                                                 |
| Items in square brackets                                                                        | These are optional parameters (can be left empty by the user)                | `editScore 1 m/MARKS [x/COMMENTS]` can be used as `editScore 1 m/75` or `editScore 1 m/75 x/Great work.`                  |
| Items with ... after them                                                                       | These are parameters that can be used multiple times (or omitted completely) | `listStu [g/TUTORIAL_GRP...]` can be used as `listStu g/T02 T03` or `listStu g/T01` or just `listStu`                     |
| Parameters can be in any order                                                                  | NIL                                                                          | `editStu 1 n/megan t/T00` is equivalent to `editStu 1 n/megan t/T00`                                                      |
| If a parameter is expected only once and entered multiple times, an error message will be shown | NIL                                                                          | `editStu 1 n/megan n/maega` results in error message `Multiple values specified for the following single-valued field(s)` |
| Extraneous parameters for commands that do not take in parameters will be ignored               | NIL                                                                          | `help abc` is equivalent to `help`                                                                                        |


## Glossary

### Definitions

| Term                     | Definition                                                                                                                                                                               |
|--------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Command                  | An input written by the user to tell Modulight to perform a certain action.                                                                                                              |
| Parameter                | A value that must be inputted by the user to complete a command.                                                                                                                         |
| Index                    | A number that refers to the position of the components in an ordering. Modulight uses a 1-based index, which means the first number in an order is 1.                                    |
| Command Line Interface   | It is a text-based user interface that accepts text inputs to execute commands.                                                                                                          |
| Graphical User Interface | It is a digital interface where the users interact with the system using graphical components, such as icons and buttons.                                                                |
| User Interface           | It is the point in which a human user interacts with a computer. It can be a physical device or software program.                                                                        |
| Component                | A component is a part of the user interface.                                                                                                                                             |
| JSON file                | JavaScript Object Notation(JSON) is a file used for data storage in ModuLight. For more information, please refer to the guide [here](https://www.oracle.com/sg/database/what-is-json/). |
| Alphanumeric             | A piece of alphanumeric text should consist of only alphabets and numeric values. For instance,  the text “ABC11” is alphanumeric whereas “(**)” is not.                                 |
| Domain                   | A domain is a digital address of a website. For emails, domain is the web address that comes after the @ symbol. For example, the domain in the email address 123@gmail.com is gmail.com |

## Parameter Information

The following section gives an overview of the parameters used for the commands related to Student, StudentScore and GradedComponent, as well as the constraints of these parameters.

### Student Parameters

| Parameter | Description                   | Constraints                                                                | Valid Examples               | Invalid Examples           |
|-----------|-------------------------------|----------------------------------------------------------------------------|------------------------------|----------------------------|
| n/        | Name of the student           | Must only contain alphanumeric characters and must not be empty.           | John, Lee Xiao Ming          | 晓明, Xiao Ming@Lee, 이준      | 
| e/        | Email of the student          | Must consist of a alphanumeric prefix, @ symbol and a domain               | 12@gmail.com, e123@u.nus.edu | 12@, 1234gmail             |
| s/        | Student ID of the student     | Must start and end with a capital letter and have 7 digits in between them | A1234567W                    | a1234567w, a123w, B1234567 |
| g/        | Tutorial group of the student | Must consist of a capital letter followed by 2 digits                      | T06, L10                     | T1, t10, T111, @T11        |

### Graded Component Parameters

| Parameter | Description                                               | Constraints                                                                                                                                         | Valid Examples      | Invalid Examples             |
|-----------|-----------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------|---------------------|------------------------------|
| c/        | Name of the graded component                              | Must only contain alphanumeric characters and must not be empty                                                                                     | Midterm Exam, CA2   | 高考, CA2/Oral, Practical-Exam |
| w/        | Weightage of the graded component                         | Must be a non-negative number, though decimals are allowed                                                                                          | 0, 0.25, 20, 1000.8 | -0.3, 1/2, (20), NIL         |
| mm/       | Maximum marks for the graded component, in absolute terms | Must be a non-negative number, though decimals are allowed. If the weightage for this component is non-zero, then maximum marks must be more than 0 | 0.0, 28, 100, 200.0 | -0.3, 1/2, (20), NIL         |


### Student Score Parameters

| Parameter | Description                                   | Constraints                                                                                                                                  | Valid Examples             | Invalid Examples   |
|-----------|-----------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------|----------------------------|--------------------|
| m/        | Marks of the Student Score, in absolute terms | Must be a non-negative number, though decimals are allowed. Cannot exceed the maximum marks of the graded component this score is related to | 0, 0.23, 30.00, 20         | -1, ⅔, 2^3, twelve |
| x/        | Comments of the student score                 | Must only contain alphanumeric characters                                                                                                    | Nice work!, Check number 2 | 好的                 |
| INDEX     | The index of the target student score         | Positive integer                                                                                                                             | 1, 10, 21                  | -2, 0, 03          |

<box type="info" seamless>

**Notes on Graded Component and Student Score parameters for score calculation**<br>

* The maximum marks of a graded component and marks of a student score are both absolute values and are used together to determine the relative performance of a student for a component. For instance, if the maximum marks for a component Midterms is 50, and the marks for the student is 35, then the student scored 35/50 =70% on this graded component.

* The weightage of a graded component is used to determine its contribution to a student’s overall score, and is calculated relative to the sum of all other component weightages. For instance, if there are only 2 components in the system currently, and component A has weightage 30, and component B weightage 20, then component A currently represents 20/(20+30) = 60% of the student’s overall score. This is modified as components are added and removed.

</box>

## Navigating the Graphical User Interface (GUI)

ModuLight comes with a GUI to allow for nice visual feedback for our users. Here is a quick runthrough of the different sections of our GUI, as well as some notes regarding the use of the GUI.

### Quick Orientation:
![Ui overview](images/Ui_overview.png)

Here is a summary of each GUI component within ModuLight.

| Name of Component     | Description                                                                                                                                                              |
|-----------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Menu Bar              | Contains dropdown menu options for the App                                                                                                                               |
| Command Box           | Allow users to enter their commands                                                                                                                                      |
| Result Display        | Provides feedback upon a user command. Allows users to see if their command was successful or not.Provides error messages to guide user on how to use ModuLight Commands |
| Graded Component List | Shows a list of Graded Component Cards. This list can be manipulated through commands. Upon starting the app, this list will reflect all Graded Components stored.       |
| Graded Component Card | Displays key information about a Graded Component such as maximum marks and weightage.                                                                                   |
| Student List          | Shows a list of Student Cards. This list can be manipulated through commands. Upon starting the app, this list will reflect all Students stored.                         |
| Student Card          | Displays key information about a Student such as name, tutorial group, emailid, etc.                                                                                     |
| Student Score List    | Shows a list of Student Score Cards. This list can be manipulated through commands. Upon starting the app, this list will reflect all StudentsScores stored.             |
| Student Score Card    | Displays key information about student scores such as graded component name for which the student is given the score and the student score itself.                       |


## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Add a student: `addStu`
Adds a student to the database. Throws error if student with same student number already exists. If it succeeds, an acknowledgement message (“Student was added successfully”) is shown to the user and data is saved. If it fails, show an error message instead. (“Something went wrong while adding student”)
Valid student numbers start and end with an upper-case alphabet, and have 7 numeric symbols in between.

Format: `addStu s/STUDENT_NO [n/NAME] [e/EMAIL] [g/TUTORIAL_GRP]…​`

Examples:
* `addStu s/A1234567Y n/Andy g/03` Adds a student with student number A1234567Y, name Andy, belonging to tutorial group 03 to the database.

### Edit a student : `editStu`
Edit student information. If it succeeds, it shows an acknowledgement message and saves the edited information (“Student information edited successfully”). Throws an error if student does not exist (“Something went wrong while editing student information”). Note that the student number cannot be edited as it is a unique identifier. In such a case, the student should be deleted.
0 or more optional fields should be provided, and all the optional fields will be edited concurrently. Valid student numbers start and end with an upper-case alphabet, and have 7 numeric symbols in between.

Format: `editStu s/STUDENT_NO [g/TUTORIAL_GRP][n/NAME][e/EMAIL]…​`

Examples:
* `editStu n/Megan Chan g/03` Edits a student to have name Megan Chan and be in tutorial group 03.

### Delete a student : `deleteStu`
Delete a student in the database. If it succeeds, it shows an acknowledgement message and deletes the student from the database (“Student information deleted successfully”). Throws an error if student does not exist (“Something went wrong while deleting the student”). All associated student scores will also be deleted.
Valid student numbers start and end with an upper-case  alphabet, and have 7 numeric symbols in between.

Format: `deleteStu s/STUDENT_NO`

Examples:
* `deleteStu s/A1234567Y`

### Add a student score: `addStuScore`
Assign a student’s score for a certain graded component. Throws an error if the Student or the graded component does not exist, or the score provided is negative. If the score exceeds the maximum score, it will show up on the panel as such, but for internal calculations it will be treated as the maximum score. (This is for users to indicate bonus scores)
Valid student numbers start and end with an upper-case alphabet, and have 7 numeric symbols in between.

Format: `addStuScore s/STUDENT_NO c/COMP_NAME m/MARKS [x/comments]`

Examples: 
* `addStuScore s/A1234567Y c/Midterm m/57`
Assigns a score of 57 to the graded component Midterm for the student with student number A123567Y.

### Edit student score: `editStuScore`
Edit a student’s score or comments. Throws an error if student or graded component does not exist, or if scores is negative.  If it succeeds, it shows an acknowledgement message and saves the edited information, and throws an error otherwise.
0 or more optional fields need to be provided, and all the information will be edited concurrently.

Format: `editStuScore s/STUDENT_NO c/COMP_NAME [m/MARKS] [x/comments]`

Examples: 
* `editComp s/A1234567Y c/Midterm x/Q4 answer is debatable. Discuss in next staff meeting. associates the given comment to A1234567Y’s Midterm score.`
 Edits the comments of the student score.

### Delete student score: `deleteStuScore`
Delete a student score in the database. If it succeeds, it shows an acknowledgement message and deletes the student score from the database (“Student score deleted successfully”). Throws an error if student score does not exist (“Something went wrong while deleting the student”).
Valid student numbers start and end with an upper-case  alphabet, and have 7 numeric symbols in between.

Format: `deleteStuScore s/STUDENT_NO c/COMP_NAME`

Examples:
* `deleteStuScore s/A1234567Y c/Midterm`

### List all students : `listStudents`
Shows a list of all students and associated student scores in separate panels respectively. The lists may be additionally filtered by optional arguments tutorial group. (can have more than 1)

Format: `listStudents [g/TUTORIAL_GRP …]`

Examples:
* `listStudents g/02 03`

### Searching students: `findStu`

Shows all students who match the given search keyword of the specific parameter. All the relevant student scores and all graded components will be displayed as well.


Format: `findStu [s/STUDENT_NO] [n/NAME] [e/EMAIL] [g/TUTORIAL_GRP] [t/TAG]`

* The search is case-insensitive. e.g. `a0123456` will match `A0123456`
* You can search based on any of the following attributes: name, student number, email, tutorial group or tag.
* It is allowed to have 0 searching criteria. In this case, this command will simply list all students.
* For searching based on tutorial group and tag, it will find the students with exactly the same description
(case-insensitive). However, for searching based on student ID, name and email, it will find the students as long as the
description contains the searching criteria.
* For searching with multiple student parameters of the same type, it will find the students who satisfy any of the
criteria.
* For searching with student parameters of different types, it will find the students who satisfy all the criteria.
* For searching with multiple student parameters of different types, it will find the students who satisfy at least one
criterion for each type.
* If a input of the incorrect format is given, there might be no students found. For example, if you search findStu 
s/A00000Y, no students will be found since this is not a substring of any valid student number.

Examples:
* `find n/Alice n/Bob g/T01` returns the data of the students whose name contains 'Alice' or 'Bob' (case-insensitive)
while in tutorial group T01.

### Find students : `findComp`
Shows all students who match the given search keyword of the specific parameter. All the relevant student scores and 
all graded components will be displayed as well.

Format: `findComp c/COMP_NAME`

* Graded components whose name contains the keyword will be found.

Example: `findComp c/midterm` lists all graded component contains the string midterm  (and their associated scores). 
All graded students will be shown since they are relevant.



### Find students : `findScore`
Shows all student scores that matches the given search keyword of the specific parameter. No student or graded components will be displayed

Format: `findScore  [s/STUDENT_NO] [n/NAME] [e/EMAIL] [g/TUTORIAL_GRP] [c/COMP_NAME][x/comments][t/tags]...`

Example: `findScore g/T02 c/midterm` lists all midterm scores in tutorial group T02. The graded component and student list will be emptied.

### List all students : `listAll`
Shows all students, student scores and graded components in their lists respectively. This removes all the filter applied from the find command.

Format: `listAll`

Example: `listAll`

### Sorting students: `sortStu`

Sorts students' data by the given criteria.

Format: `sortStu [o/SORTING_ORDER] [r/REVERSE]`

* The sorting order keyword must be one of the acceptable description given below: <br>
"n", "name", "s", "studentId", "studentID", "e", "email", "g", "tutorial", "tut", "tutGroup""ts", "totalScore", 
"score", "totalscore".
* The reverse keyword must be one of the acceptable description given below: <br>
"decreasing", "0", "false", "f" (These 4 keywords have the same effect), "increasing", "1", "true", "t" (These 4 
keywords have the same effect).
* It is allowed to omit sorting order and reverse. In this case, the default sorting order is by total score while 
the default reverse is false (i.e. increasing).
* This command will only sort the currently displayed students. If you want to sort all students, please use `listStu`
command in advance.

Examples:
* `sortStu o/name r/true` returns the sorted students whose names are in descending alphabetical order.

### Calculating overall statistics

Calculates the overall statistics of all students.

Format: `stats [st/STATS] [g/TUTORIAL_GRP]`

* It is allowed to omit `[st/STATS]`. In this case, it will return a summary of all statistics that are currently
supported.
* For stats keywords, it must be currently supported. Here is an exhaustive list of currently supported statistical
measures: mean, standardDeviation, upperQuartile, lowerQuartile, max, min, skewness.
* It is allowed to have multiple stats keywords, but only allowed to have 0 or 1 tutorial group keywords.

Examples:
* `stats st/upperQuartile st/lowerQuartile` returns the upper and lower quartile of the overall student grades.


### Clearing all entries : `clear`

Clears all data from ModuLight.

Format: `clear`

* The clearing process is irreversible.


### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

ModuLight data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Loading the previous data

There is no need to manually load data stored on the hard disc. It will be available automatically everytime the program starts.

### Editing the data file

ModuLight data are saved automatically as a JSON file `[JAR file location]/data/modulight.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, ModuLight will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.
</box>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous LumiNUS home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                         | Format, Examples                                                                                                                                                    |
|--------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add a student**              | `addStu s/STUDENT_NO [n/NAME] [e/EMAIL] [g/TUTORIAL_GRP]…` <br> e.g., `addStu s/A1234567Y n/Andy g/03`                                                              |
| **Add a graded component**     | `addComp c/COMP_NAME w/WEIGHTAGE m/MAX_MARKS…` <br> e.g., `addComp c/Midterm w/30 m/70`                                                                             |
| **Add a student score**<br/>   | `addStuScore s/STUDENT_NO c/COMP_NAME m/MARKS [x/comments]…` <br> e.g., `addStuScore s/A1234567Y c/Midterm m/57 `                                                   |
| **Edit a student**             | `editStu s/STUDENT_NO [g/TUTORIAL_GRP][n/NAME][e/EMAIL]…​` <br> e.g., `editStu n/Megan Chan g/03`                                                                   |
| **Edit a graded component**    | `editComp c/COMP_NAME [w/WEIGHTAGE] [mm/MAX_MARKS]` <br> e.g., `editComp c/Midterm  w/25 `                                                                          |
| **Edit a student score**       | `editStuScore s/STUDENT_NO c/COMP_NAME [m/MARKS] [x/comments]` <br> e.g., `editComp s/A1234567Y c/Midterm x/Q4 answer is debatable. Discuss in next staff meeting.` |
| **Delete a student**           | `deleteStu s/STUDENT_NO` <br> e.g., `deleteStu s/A1234567Y`                                                                                                         |
| **Delete a graded component**  | `deleteComp s/Optional Project` <br> e.g., `deleteComp s/Optional Project`                                                                                          |
| **Delete a student score**     | `deleteStuScore s/STUDENT_NO c/COMP_NAME` <br> e.g., `deleteStuScore s/A1234567Y c/Midterm`                                                                         |
| **Clear**                      | `clear`                                                                                                                                                             |
| **Find**                       | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                          |
| **List all students**          | ` listStudents [g/TUTORIAL_GRP …]` <br> e.g., `listStudents g/02 03`                                                                                                |
| **List all graded components** | ` listComps [c/COMP_NAME …]` <br> e.g., `listComps c/CA1 CA2`                                                                                                       |
| **Help**                       | `help`                                                                                                                                                              |
