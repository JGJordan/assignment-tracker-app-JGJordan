package utils.utils

import utils.models.Assignment
import utils.models.Subject

object Utilities {

    // NOTE: JvmStatic annotation means that the methods are static i.e. we can call them over the class
    //      name; we don't have to create an object of Utilities to use them.

    @JvmStatic
    fun formatListString(subjectsToFormat: List<Subject>): String =
        subjectsToFormat
            .joinToString(separator = "\n") { subject -> "$subject" }

    @JvmStatic
    fun formatSetString(assignmentsToFormat: Set<Assignment>): String =
        assignmentsToFormat
            .joinToString(separator = "\n") { assignment -> "\t$assignment" }

}
