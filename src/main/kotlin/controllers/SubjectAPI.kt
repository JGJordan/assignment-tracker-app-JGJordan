package utils.controllers
import utils.models.Subject


class SubjectAPI {
    private var subjects = ArrayList<Subject>()

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
}

