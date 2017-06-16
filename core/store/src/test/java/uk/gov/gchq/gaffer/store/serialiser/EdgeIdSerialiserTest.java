package uk.gov.gchq.gaffer.store.serialiser;

import org.junit.Before;
import org.junit.Test;
import uk.gov.gchq.gaffer.commonutil.TestGroups;
import uk.gov.gchq.gaffer.data.element.Edge;
import uk.gov.gchq.gaffer.data.element.id.EdgeId;
import uk.gov.gchq.gaffer.exception.SerialisationException;
import uk.gov.gchq.gaffer.operation.data.generator.EdgeIdExtractor;
import uk.gov.gchq.gaffer.serialisation.implementation.StringSerialiser;
import uk.gov.gchq.gaffer.store.schema.Schema;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EdgeIdSerialiserTest {

    private EdgeIdSerialiser edgeIdSerialiser;
    private Schema schema;

    @Before
    public void setUp() {
        schema = new Schema.Builder()
                .vertexSerialiser(new StringSerialiser())
                .build();
        edgeIdSerialiser = new EdgeIdSerialiser(schema);
    }

    @Test
    public void testNullSerialiser() {
        // Given
        schema = new Schema.Builder()
                .build();

        // When / Then
        try {
            edgeIdSerialiser = new EdgeIdSerialiser(schema);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Vertex serialiser is required"));
        }
    }

    /*@Test
    public void testInvalidSerialiser() {
        // Given
        schema = new Schema.Builder()
                .vertexSerialiser()
                .build();

        // When / Then
        try {
            edgeIdSerialiser = new EdgeIdSerialiser(schema);
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Vertex serialiser must be a"));
        }
    }*/

    @Test
    public void testCanSeraliseEdgeId() throws SerialisationException {
        // Given
        final EdgeIdExtractor extractor = new EdgeIdExtractor();
        final Edge edge = new Edge(TestGroups.EDGE, "source", "destination", true);
        final EdgeId edgeId = extractor._apply(edge);

        // When
        final byte[] serialised = edgeIdSerialiser.serialise(edgeId);
        final EdgeId deserialisedEdgeId = edgeIdSerialiser.deserialise(serialised);

        // Then
        assertEquals(edgeId, deserialisedEdgeId);
    }

    @Test
    public void cantSerialiseIntegerClass() throws SerialisationException {
        assertFalse(edgeIdSerialiser.canHandle(Integer.class));
    }

    @Test
    public void canSerialiseEdgeIdClass() throws SerialisationException {
        assertTrue(edgeIdSerialiser.canHandle(EdgeId.class));
    }
}
