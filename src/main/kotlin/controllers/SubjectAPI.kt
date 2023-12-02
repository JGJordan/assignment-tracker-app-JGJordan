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
        return if (subjects.isNotEmpty()) {
            subjects.joinToString(" ")
        } else {
            "You have no subjects"
        }
    }
}

