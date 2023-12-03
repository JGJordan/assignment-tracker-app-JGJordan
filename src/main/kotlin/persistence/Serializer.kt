package utils.persistence

/**
 * Interface for serialization operations.
 */
interface Serializer {
    /**
     * Writes the provided object.
     * @param obj The object to be written.
     * @throws Exception If an error occurs during writing.
     */
    @Throws(Exception::class)
    fun write(obj: Any?)

    /**
     * Reads an object.
     * @return The read object.
     * @throws Exception If an error occurs during reading.
     */
    @Throws(Exception::class)
    fun read(): Any?
}
