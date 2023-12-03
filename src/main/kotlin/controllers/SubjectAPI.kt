package utils.controllers

import utils.models.Subject
import utils.persistence.Serializer
import utils.utils.Utilities

/**
 * Handles operations related to managing subjects through an API.
 * @param serializerType The type of serializer to use for data storage.
 */
class SubjectAPI(serializerType: Serializer) {
    var subjects = ArrayList<Subject>() // List of subjects managed by the API
    private var serializer: Serializer = serializerType // Serializer used for data operations

    /**
     * Adds a subject to the list.
     * @param subject The subject to be added.
     * @return True if addition is successful, false otherwise.
     */
    fun add(subject: Subject): Boolean {
        return subjects.add(subject)
    }

    /**
     * Retrieves the number of subjects in the list.
     * @return The number of subjects.
     */
    fun numberOfSubjects(): Int {
        return subjects.size
    }

    /**
     * Retrieves a formatted string representing the list of subjects.
     * @return Formatted string of subjects or a message if no subjects are found.
     */
    fun listSubjects() =
        if (subjects.isEmpty()) "\nNo Subjects Found"
        else Utilities.formatListString(subjects)

    /**
     * Checks if the index is valid within a given list.
     * @param index The index to be checked.
     * @param list The list to check the index against.
     * @return True if the index is valid, false otherwise.
     */
    fun isValidListIndex(index: Int, list: List<Any?>): Boolean {
        return (index >= 0 && index < list.size)
    }

    /**
     * Checks if the index is valid within the subjects list.
     * @param index The index to be checked.
     * @return True if the index is valid, false otherwise.
     */
    fun validIndex(index: Int): Boolean {
        return isValidListIndex(index, subjects)
    }

    /**
     * Deletes a subject at a specific index.
     * @param indexToDelete The index of the subject to delete.
     * @return The deleted subject or null if deletion fails.
     */
    fun deleteSubject(indexToDelete: Int): Subject? {
        return if (isValidListIndex(indexToDelete, subjects)) {
            subjects.removeAt(indexToDelete)
        } else null
    }

    /**
     * Finds a subject at a specific index.
     * @param index The index of the subject to find.
     * @return The subject found at the index or null if not found.
     */
    fun subjectFinder(index: Int): Subject? {
        return if (isValidListIndex(index, subjects)) {
            subjects[index]
        } else null
    }

    /**
     * Updates a subject's details.
     * @param subjectChoice The index of the subject to update.
     * @param subject The updated subject object.
     * @return True if the update is successful, false otherwise.
     */
    fun updateSubject(subjectChoice: Int, subject: Subject?): Boolean {
        val foundSubject = subjectFinder(subjectChoice)

        if ((foundSubject != null) && (subject != null)) {
            foundSubject.subjectName = subject.subjectName
            foundSubject.subjectGrade = subject.subjectGrade
            foundSubject.subjectLecturer = subject.subjectLecturer
            return true
        }
        return false
    }

    /**
     * Loads the subjects from storage.
     * @throws Exception If an error occurs during loading.
     */
    @Throws(Exception::class)
    fun load() {
        subjects = serializer.read() as ArrayList<Subject>
    }

    /**
     * Stores the subjects using the serializer.
     * @throws Exception If an error occurs during storing.
     */
    @Throws(Exception::class)
    fun store() {
        serializer.write(subjects)
    }
}
