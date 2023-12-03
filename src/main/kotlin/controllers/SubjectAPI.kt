package utils.controllers
import utils.models.Subject


class SubjectAPI {
    var subjects = ArrayList<Subject>()

    fun add(subject: Subject): Boolean {
        return subjects.add(subject)
    }

    fun numberOfSubjects() : Int {
        return subjects.size
    }
    fun listSubjects(): String {
        if (subjects.isNotEmpty()) {
            return subjects.joinToString(" ")
        } else {
            return "You have no subjects"
        }
    }

    fun isValidListIndex(index: Int, list: List <Any?>): Boolean {
        return (index >= 0 && index < list.size)
    }

    fun deleteSubject(indexToDelete: Int) : Subject? {
        return if (isValidListIndex(indexToDelete, subjects)) {
            subjects.removeAt(indexToDelete)
        } else null
    }


}

