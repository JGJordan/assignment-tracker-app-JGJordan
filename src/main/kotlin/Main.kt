package utils

import java.util.*
import java.lang.System.exit

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
        |    | Assignment Tracker V1.0  |
        |    |--------------------------|
        |    | 1) View Subjects         |
        |    | 2) Add Subject           |
        |    | 3) Update Subject        |
        |    | 4) Delete Subject        |
        |    |--------------------------|
        |    | 0) Exit Program          |
        |    |--------------------------|
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
    println("You chose to view a subject")
}

/**
 * Displays a message indicating the user chose to add a subject.
 */
fun addSubject() {
    println("You chose to add a subject")
}

/**
 * Displays a message indicating the user chose to update a subject.
 */
fun updateSubject() {
    println("You chose to update a subject")
}

/**
 * Displays a message indicating the user chose to delete a subject.
 */
fun deleteSubject() {
    println("You chose to delete a subject")
}

/**
 * Displays a message indicating the user chose to exit the program and closes the application.
 */
fun closeApp() {
    println("You chose to exit the program")
    exit(0)
}
