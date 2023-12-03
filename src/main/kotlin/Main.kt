package utils

import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import utils.controllers.SubjectAPI
import utils.models.Subject
import java.util.*
import java.lang.System.exit


private val SubjectAPI = SubjectAPI()

/**
 * Runs the assignment tracker menu.
 */
fun main(args: Array<String>) {
    runMenu()
}

/**
 * Displays the main menu options and retrieves user input.
 * @return The selected menu option.
 */
fun mainMenu(): Int {
    return ScannerInput.readNextInt("""
        |    ____________________________
        |    | Assignment Tracker V2.0  |
        |    |--------------------------|
        |    | 1) View Subjects         |
        |    | 2) Add Subject           |
        |    | 3) Update Subject        |
        |    | 4) Delete Subject        |
        |    |--------------------------|
        |    | 0) Exit Program          |
        |    |--------------------------|
        |    
        |     Enter your choice     ===>: 
    """.trimMargin())
}

/**
 * Runs the main menu loop.
 */
fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1 -> viewSubject()
            2 -> addSubject()
            3 -> updateSubject()
            4 -> deleteSubject()
            0 -> closeApp()
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
    val subjectGrade = readNextInt("Enter the grade current grade you have achieved: ")
    val subjectLecturer = readNextLine("Enter the name of your lecturer: ")
    val isAdded = SubjectAPI.add(Subject(subjectName, subjectGrade,subjectLecturer))
    if (isAdded) {
        println("Subject is Added")
    } else {
        println("Add failed")
    }
}

fun deleteSubject() {
    viewSubject()
    if (SubjectAPI.numberOfSubjects() > 0) {
        val indexToDelete = readNextInt("Please enter the index No. of your subject: ")
        val subjectToDelete = SubjectAPI.deleteSubject(indexToDelete)
        if (subjectToDelete != null) {
            println("Delete is successful")
        }
    } else {
        println("Delete not successful")
    }
}

/**
 * Displays a message indicating the user chose to update a subject.
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
                println("Successfully Updates")
            } else {
                println("Failed")
            }
        } else {
            println("Incorrect Index Number. Try again.")
        }
    }
}

/**
 * Displays a message indicating the user chose to delete a subject.
 */

/**
 * Displays a message indicating the user chose to exit the program and closes the application.
 */
fun closeApp() {
    println("Exiting...")
    exit(0)
}
