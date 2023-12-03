package utils.models

import utils.utils.Utilities


data class Subject(
    var subjectName : String,
    var subjectGrade : Int,
    var subjectLecturer : String,
    var assignments: MutableSet<Assignment> = mutableSetOf()){

    private var lastAssignmentID = 0
    private fun getAssignmentID() = lastAssignmentID++

    fun addAssignment(assignment: Assignment): Boolean {
    assignment.assignmentID = getAssignmentID()
        return assignments.add(assignment)
    }

    fun numberOfAssignments() = assignments.size

    fun findAssignment(id: Int): Assignment?{
        return assignments.find { assignment -> assignment.assignmentID== id}
    }

    fun delete(id: Int): Boolean {
        return assignments.removeIf { assignment -> assignment.assignmentID == id }
    }

    fun update(id: Int, newAssignment: Assignment): Boolean {
        val foundAssignment = findAssignment(id)

        if (foundAssignment != null){
            foundAssignment.assignmentSummary = newAssignment.assignmentSummary
            foundAssignment.assignmentWeight = newAssignment.assignmentWeight
            return true
        }
        return false
    }
    fun listAssignments() =
        if (assignments.isEmpty()) "\n NO ASSIGNMENTS ADDED"
            else Utilities.formatSetString(assignments)

}

