package utils.controllers
import utils.models.Subject
import utils.persistence.Serializer


class SubjectAPI (serializerType: Serializer) {
    var subjects = ArrayList<Subject>()
    private var serializer: Serializer = serializerType

    fun add(subject: Subject): Boolean {
        return subjects.add(subject)
    }

    fun numberOfSubjects() : Int {
        return subjects.size
    }
    fun listSubjects(): String {
        return if (subjects.isNotEmpty()) {
            subjects.joinToString(" ")
        } else {
            "You have no subjects"
        }
    }

    fun isValidListIndex(index: Int, list: List <Any?>): Boolean {
        return (index >= 0 && index < list.size)
    }

    fun validIndex(index : Int): Boolean{
        return isValidListIndex(index, subjects)
    }

    fun deleteSubject(indexToDelete: Int) : Subject? {
        return if (isValidListIndex(indexToDelete, subjects)) {
            subjects.removeAt(indexToDelete)
        } else null
    }

    fun subjectFinder(index : Int): Subject?{
        return if (isValidListIndex(index, subjects)) {
            subjects[index]
        } else null
    }

    fun updateSubject(subjectChoice : Int, subject: Subject?): Boolean {
        val foundSubject = subjectFinder(subjectChoice)

        if ((foundSubject != null) && (subject != null)) {
            foundSubject.subjectName = subject.subjectName
            foundSubject.subjectGrade = subject.subjectGrade
            foundSubject.subjectLecturer = subject.subjectLecturer
            return true
        }
        return false
    }

    @Throws(Exception::class)
    fun load() {
        subjects = serializer.read() as ArrayList<Subject>
    }

    @Throws(Exception::class)
    fun store(){
        serializer.write(subjects)
    }
}

