package utils.controllers

import utils.models.Subject


class NoteAPI {
    private var subjects = ArrayList<Subject>()

    fun addSubject(subject : Subject) : Boolean {
        return subjects.add(subject)
    }
}

