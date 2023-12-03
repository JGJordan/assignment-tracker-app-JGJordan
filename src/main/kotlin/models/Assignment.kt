package utils.models

/**
 * Represents an assignment with its details.
 * @property assignmentID The ID of the assignment.
 * @property assignmentSummary A summary or description of the assignment.
 * @property assignmentWeight The weight/importance of the assignment.
 */
data class Assignment(
    var assignmentID: Int = 0,
    var assignmentSummary: String,
    var assignmentWeight: Int
)
