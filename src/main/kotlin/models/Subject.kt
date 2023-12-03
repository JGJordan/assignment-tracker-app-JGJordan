package utils.models

import utils.utils.Utilities

/**
 * Represents a subject with its details and associated assignments.
 * @property subjectName The name of the subject.
 * @property subjectGrade The grade/level of the subject.
 * @property subjectLecturer The lecturer teaching the subject.
 * @property assignments The set of assignments related to the subject.
 */
data class Subject(
    var subjectName: String,
    var subjectGrade: Int,
    var subjectLecturer: String,
    var assignments: MutableSet<Assignment> = mutableSetOf()
) {
    private var lastAssignmentID = 0

    /**
     * Retrieves the next available assignment ID.
     * @return The next available assignment ID.
     */
    private fun getAssignmentID() = lastAssignmentID++

    /**
     * Adds an assignment to the subject.
     * @param assignment The assignment to be added.
     * @return True if addition is successful, false otherwise.
     */
    fun addAssignment(assignment: Assignment): Boolean {
        assignment.assignmentID = getAssignmentID()
        return assignments.add(assignment)
    }

    /**
     * Retrieves the number of assignments associated with the subject.
     * @return The number of assignments.
     */
    fun numberOfAssignments() = assignments.size

    /**
     * Finds an assignment within the subject by its ID.
     * @param id The ID of the assignment to find.
     * @return The assignment found or null if not found.
     */
    fun findAssignment(id: Int): Assignment? {
        return assignments.find { assignment -> assignment.assignmentID == id }
    }

    /**
     * Deletes an assignment from the subject by its ID.
     * @param id The ID of the assignment to delete.
     * @return True if deletion is successful, false otherwise.
     */
    fun delete(id: Int): Boolean {
        return assignments.removeIf { assignment -> assignment.assignmentID == id }
    }

    /**
     * Updates an assignment's details within the subject.
     * @param id The ID of the assignment to update.
     * @param newAssignment The updated assignment object.
     * @return True if the update is successful, false otherwise.
     */
    fun update(id: Int, newAssignment: Assignment): Boolean {
        val foundAssignment = findAssignment(id)

        if (foundAssignment != null) {
            foundAssignment.assignmentSummary = newAssignment.assignmentSummary
            foundAssignment.assignmentWeight = newAssignment.assignmentWeight
            return true
        }
        return false
    }

    /**
     * Retrieves a formatted string representing the list of assignments.
     * @return Formatted string of assignments or a message if no assignments are found.
     */
    fun listAssignments() =
        if (assignments.isEmpty()) "NO ASSIGNMENTS ADDED"
        else Utilities.formatSetString(assignments)

    /**
     * Overrides the default toString method to represent the Subject's details.
     * @return A formatted string representation of the Subject's details and assignments.
     */
    override fun toString(): String {
        return """
            |      Subject: $subjectName
            |      Lecturer: $subjectLecturer
            |      Current Grade: $subjectGrade
            |      Assignments: ${listAssignments()} 
            |  
        """.trimMargin()
    }
}
