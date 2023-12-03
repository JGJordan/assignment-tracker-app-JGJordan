package utils

import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import utils.controllers.SubjectAPI
import utils.models.Assignment
import utils.models.Subject
import utils.persistence.JSONSerializer
import utils.persistence.XMLSerializer
import java.io.File
import java.util.*
import java.lang.System.exit

// Create an instance of SubjectAPI using a JSONSerializer and a file named "Subjects.json"
private val SubjectAPI = SubjectAPI(JSONSerializer(File("Subjects.json")))

/**
 * Runs the assignment tracker menu.
 * This function serves as the entry point for the assignment tracker application.
 * @param args Command-line arguments (not used in this application).
 */
fun main(args: Array<String>) {
    runMenu()
}

/**
 * Displays the main menu options and retrieves user input.
 * @return The selected menu option.
 */
fun mainMenu(): Int {
    return readNextInt("""
        |    ____________________________
        |    | Assignment Tracker V2.0  |
        |    |--------------------------|
        |    |      SUBJECT MENU        |
        |    |                          | 
        |    | 1) View Subjects         |
        |    | 2) Add Subject           |
        |    | 3) Update Subject        |
        |    | 4) Delete Subject        |
        |    |--------------------------|
        |    |     ASSIGNMENT MENU      |
        |    |                          | 
        |    |                          |
        |    | 5) Add an Assignment     |
        |    | 6) Update an Assignment  |
        |    | 7) Delete an Assignment  |
        |    |--------------------------|
        |    | 0) Exit Program          |
        |    |--------------------------|
        |    | 10) Load Subjects        |
        |    | 11) Save Subjects        |
        |    ----------------------------
        |    
        |     Enter your choice     ===>: 
    """.trimMargin())
}


/**
 * Runs the main menu loop.
 */
/**
 * Runs the main menu loop, continuously prompting for user options until exit.
 */
fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1 -> viewSubject()
            2 -> addSubject()
            3 -> updateSubject()
            4 -> deleteSubject()
            5 -> addAssignment()
            6 -> updateAssignment()
            7 -> deleteAssignment()
            0 -> closeApp()
            10 -> load()
            11 -> save()
            else -> println("Option: $option is not valid. Try again")
        }
    } while (true)
}

/**
 * Displays a message indicating the user chose to view a subject.
 */
fun viewSubject() {
    println(SubjectAPI.listSubjects())
}

/**
 * Displays a message indicating the user chose to add a subject.
 */
fun addSubject() {
    val subjectName = readNextLine("Enter the name of the subject: ")
    val subjectGrade = readNextInt("Enter the current grade you have achieved: ")
    val subjectLecturer = readNextLine("Enter the name of your lecturer: ")
    val isAdded = SubjectAPI.add(Subject(subjectName, subjectGrade, subjectLecturer))
    if (isAdded) {
        println("Subject is Added")
    } else {
        println("Add failed")
    }
}


/**
 * Deletes a subject based on the user's input index.
 */
fun deleteSubject() {
    viewSubject()
    if (SubjectAPI.numberOfSubjects() > 0) {
        val indexToDelete = readNextInt("Please enter the index No. of your subject: ")
        val subjectToDelete = SubjectAPI.deleteSubject(indexToDelete)
        if (subjectToDelete != null) {
            println("Delete is successful")
        } else {
            println("Delete not successful")
        }
    } else {
        println("No subjects available to delete")
    }
}

/**
 * Updates the details of a subject based on the user's input.
 */
fun updateSubject() {
    viewSubject()
    if (SubjectAPI.numberOfSubjects() > 0) {
        val subjectChoice = readNextInt("Please enter the Index number of the subject you want to update: ")
        if (SubjectAPI.validIndex(subjectChoice)) {
            val subjectName = readNextLine("Please enter the new name for your subject: ")
            val subjectGrade = readNextInt("Please enter your current grade, to the closest whole number: ")
            val subjectLecturer = readNextLine("Please enter the name of your lecturer: ")

            if (SubjectAPI.updateSubject(subjectChoice, Subject(subjectName, subjectGrade, subjectLecturer))){
                println("Successfully updated subject")
            } else {
                println("Failed to update subject")
            }
        } else {
            println("Incorrect Index Number. Try again.")
        }
    }
}

/**
 * Displays a message indicating the user chose to exit the program and closes the application.
 */
fun closeApp() {
    println("Exiting...")
    exit(0)
}

/**
 * Saves data using the SubjectAPI.
 */
fun save(){
    try{
        SubjectAPI.store()
    }   catch (e: Exception) {
        System.err.println("Error writing to the file $e")
    }
}

/**
 * Loads data using the SubjectAPI.
 */
fun load(){
    try {
        SubjectAPI.load()
    }   catch (e: Exception) {
        System.err.println("Error loading the file $e")
    }
}

/**
 * Adds an assignment to a subject based on user input.
 */
private fun addAssignment(){
    val subject: Subject? = askToChooseSubject()
    if (subject != null) {
        if (subject.addAssignment(Assignment(assignmentSummary = readNextLine("Enter a summary for this assignment: "),
                assignmentWeight = readNextInt("Enter assignment weight to the closest whole number: ")))) {
            println("Assignment added successfully")
        } else {
            println("Failed to add assignment")
        }
    } else {
        println("No subject chosen")
    }
}

/**
 * Prompts the user to choose a subject.
 * @return The chosen subject or null if not found or chosen.
 */
private fun askToChooseSubject(): Subject? {
    viewSubject()

    if (SubjectAPI.numberOfSubjects() > 0) {
        val subject = SubjectAPI.subjectFinder((readNextInt("Enter the Index number of your subject: ")))
        return if (subject != null) {
            subject
        } else {
            null
        }
    } else {
        println("Invalid Choice")
    }
    return null
}

/**
 * Updates an assignment based on user input.
 */
fun updateAssignment(){
    val subject: Subject? = askToChooseSubject()
    if (subject != null) {
        val assignment: Assignment? = chooseAnAssignment(subject)
        if (assignment != null){
            val newSummary = readNextLine("Enter new assignment summary: ")
            val newWeight = readNextInt("Enter new assignment weight: ")
            if (subject.update(assignment.assignmentID, Assignment(assignmentSummary = newSummary,
                    assignmentWeight = newWeight))) {
                println("Assignments Updated")
            } else {
                println("Assignment not Updated")
            }
        } else {
            println("Invalid Assignment ID")
        }

    }
}

/**
 * Deletes an assignment based on user input.
 */
fun deleteAssignment(){
    val subject: Subject? = askToChooseSubject()
    if (subject != null) {
        val assignment: Assignment? = chooseAnAssignment(subject)
        if (assignment != null) {
            val deleted = subject.delete(assignment.assignmentID)
            if (deleted) {
                println("Delete Successful")
            } else {
                println("Delete Not Successful")
            }
        }
    }
}

/**
 * Allows the user to choose an assignment for a subject.
 * @param subject The subject for which the assignment is chosen.
 * @return The chosen assignment or null if not found or chosen.
 */
private fun chooseAnAssignment(subject: Subject): Assignment? {
    if (subject.numberOfAssignments() > 0) {
        print(subject.listAssignments())
        return subject.findAssignment(readNextInt("Enter the index number of your assignment: "))
    } else {
        println("You have no assignments uploaded")
        return null
    }
}




