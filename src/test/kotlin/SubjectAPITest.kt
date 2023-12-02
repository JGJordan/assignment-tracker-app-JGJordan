package controllers


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import utils.models.Subject
import utils.controllers.SubjectAPI


class SubjectAPITest {

    private var emptyList: SubjectAPI? = SubjectAPI()




    @Nested
    inner class CRUD{
        @Test
        fun `Adding a subject to an empty array list adds to the list`() {
            val newSubject = Subject(1,"Maths","60", "Ms. Smith")
            assertEquals(0, emptyList!!.numberOfSubjects())
            assertTrue(emptyList!!.add(newSubject))
            assertEquals(1, emptyList!!.numberOfSubjects())
        }
    }
}