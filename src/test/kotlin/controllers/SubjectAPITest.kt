package controllers.controllers


import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import utils.models.Subject
import utils.controllers.SubjectAPI
import utils.persistence.JSONSerializer
import utils.persistence.XMLSerializer
import java.io.File


class SubjectAPITest {
    private var maths: Subject? = null
    private var science: Subject? = null
    private var geography: Subject? = null
    private var language: Subject? = null
    private var emptyList: SubjectAPI? = SubjectAPI(XMLSerializer(File("Subjects.xml")))
    private var populatedList: SubjectAPI? = SubjectAPI(XMLSerializer(File("Subject.xml")))

    @BeforeEach
    fun setup(){
        maths = Subject("Maths",67,"Ms. Smith")
        science = Subject("Science",54, "Mr. Jones")
        geography = Subject("Geography", 45,"Ms. Murphy")
        language = Subject("Language",65,"Mr. Walsh")

        populatedList!!.add(maths!!)
        populatedList!!.add(science!!)
        populatedList!!.add(geography!!)
        populatedList!!.add(language!!)

    }


    @Nested
    inner class CRUD{
        @Test
        fun `Adding a subject to an empty array list adds to the list`() {
            val newSubject = Subject("Maths",60, "Ms. Smith")
            assertEquals(0, emptyList!!.numberOfSubjects())
            assertTrue(emptyList!!.add(newSubject))
            assertEquals(1, emptyList!!.numberOfSubjects())
        }

        @Test
        fun `Adding a subject to a populated array list adds to the list`(){
            val newSubject = Subject("Maths",60,"Ms.Smith")
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

        @Test
        fun `When the viewSubjects function is invoked on a populated list, notes are returned`(){
            val subjectList = populatedList!!.listSubjects().lowercase()
            assertEquals(4, populatedList!!.numberOfSubjects())
            assertTrue(subjectList.contains("maths"))
            assertTrue(subjectList.contains("science"))
            assertTrue(subjectList.contains("language"))
            assertTrue(subjectList.contains("geography"))
        }

        @Test
        fun `When the viewSubjects function is invoked on an empty list, a string is returned`(){
            assertEquals(0,emptyList!!.numberOfSubjects())
            assertTrue(emptyList!!.listSubjects().contains("You have no subjects"))
        }

        @Test
        fun `Updating a subject that exists updates the subject`(){
            assertEquals(maths, populatedList!!.subjectFinder(0))
            assertEquals("Maths", populatedList!!.subjectFinder(0)!!.subjectName)
            assertTrue(populatedList!!.updateSubject(0, Subject("Woodwork",91,
                "Declan")))
            assertEquals("Woodwork", populatedList!!.subjectFinder(0)!!.subjectName)
        }

        @Test
        fun `Trying to update a subject that doesn't exist returns false`(){
            assertFalse(populatedList!!.updateSubject(15, Subject("Woodwork", 91, "" +
                    "Declan")))

        }


    }

    @Nested
    inner class persistenceTests {

        @Test
        fun `Saving and loading an empty collection won't crash the application` (){
            val storeSubject = SubjectAPI(XMLSerializer(File("subjects.xml")))
            storeSubject.store()

            val loadingSubject = SubjectAPI(XMLSerializer(File("subjects.xml")))
            loadingSubject.load()

            assertEquals(0, storeSubject.numberOfSubjects())
            assertEquals(0, loadingSubject.numberOfSubjects())
            assertEquals (storeSubject.numberOfSubjects(), loadingSubject.numberOfSubjects())
        }

        @Test
        fun `Saving and loading an empty collection wont crash the application` (){
            val storeSubject = SubjectAPI(JSONSerializer(File("subjects.json")))
            storeSubject.store()

            val loadingSubject = SubjectAPI(JSONSerializer(File("subjects.json")))
            loadingSubject.load()

            assertEquals(0, storeSubject.numberOfSubjects())
            assertEquals(0, loadingSubject.numberOfSubjects())
            assertEquals (storeSubject.numberOfSubjects(), loadingSubject.numberOfSubjects())
        }

    }

}