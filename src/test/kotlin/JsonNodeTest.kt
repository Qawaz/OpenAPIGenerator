import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import junit.framework.TestCase.*
import org.junit.Test


class JsonNodeTest {

    private fun JsonNode.isReference(): Boolean {
        val refNode = this.get("\$ref")
        return refNode != null && !refNode.isNull
    }

    @Test
    fun `test if node is a $ref`() {
        val jsonString = "{ \"name\": \"John Doe\", \"pets\": { \"\$ref\": \"#/definitions/PetList\" } }"
        val objectMapper = ObjectMapper()
        val jsonNode = objectMapper.readTree(jsonString)
        assertTrue(jsonNode.get("pets").isReference())
        val refNode = jsonNode.findValue("\$ref")
        assertEquals("#/definitions/PetList", refNode.textValue())
    }

    @Test
    fun `test if node is not a $ref`() {
        val jsonString = "{ \"name\": \"John Doe\", \"age\": 30 }"
        val objectMapper = ObjectMapper()
        val jsonNode = objectMapper.readTree(jsonString)
        assertFalse(jsonNode.isReference())
        val refNode = jsonNode.findValue("\$ref")
        assertNull(refNode)
    }

}
