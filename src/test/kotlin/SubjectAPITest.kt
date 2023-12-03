package controllers


import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import utils.models.Subject
import utils.controllers.SubjectAPI


class SubjectAPITest {
    private var maths: Subject? = null
    private var science: Subject? = null
    private var geography: Subject? = null
    private var language: Subject? = null
    private var emptyList: SubjectAPI? = SubjectAPI()
    private var populatedList: SubjectAPI? = SubjectAPI()

    @BeforeEach
    fun setup(){
        maths = Subject("Maths","67","Ms. Smith")
        science = Subject("Science","54", "Mr. Jones")
        geography = Subject("Geography", "45","Ms. Murphy")
        language = Subject("Language","65","Mr. Walsh")

        populatedList!!.add(maths!!)
        populatedList!!.add(science!!)
        populatedList!!.add(geography!!)
        populatedList!!.add(language!!)

    }


    @Nested
    inner class CRUD{
        @Test
        fun `Adding a subject to an empty array list adds to the list`() {
            val newSubject = Subject("Maths","60", "Ms. Smith")
            assertEquals(0, emptyList!!.numberOfSubjects())
            assertTrue(emptyList!!.add(newSubject))
            assertEquals(1, emptyList!!.numberOfSubjects())
        }

        @Test
        fun `Adding a subject to a populated array list adds to the list`(){
            val newSubject = Subject("Maths","60","Ms.Smith")
            assertEquals(4, populatedList!!.numberOfSubjects())
            assertTrue(populatedList!!.add(newSubject))
            assertEquals(5, populatedList!!.numberOfSubjects())
        }

        @Test
        fun `Deleting a non-existent subject returns null (error handling)`(){
            assertNull(emptyList!!.deleteSubject(3))
            assertNull(populatedList!!.deleteSubject(6))
            assertNull(populatedList!!.deleteSubject(-43))
        }

        @Test
        fun `Deleting an existing subject removes it from the Array List`(){
            assertEquals(4, populatedList!!.numberOfSubjects())
            assertEquals(maths, populatedList!!.deleteSubject(0))
            assertEquals(3, populatedList!!.numberOfSubjects())
        }

    }
}