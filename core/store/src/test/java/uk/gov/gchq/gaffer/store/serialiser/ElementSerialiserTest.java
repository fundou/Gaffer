package uk.gov.gchq.gaffer.store.serialiser;

import org.junit.Before;
import org.junit.Test;
import uk.gov.gchq.gaffer.commonutil.TestGroups;
import uk.gov.gchq.gaffer.commonutil.TestPropertyNames;
import uk.gov.gchq.gaffer.commonutil.TestTypes;
import uk.gov.gchq.gaffer.data.element.Edge;
import uk.gov.gchq.gaffer.data.element.Element;
import uk.gov.gchq.gaffer.exception.SerialisationException;
import uk.gov.gchq.gaffer.serialisation.implementation.StringSerialiser;
import uk.gov.gchq.gaffer.store.schema.Schema;
import uk.gov.gchq.gaffer.store.schema.SchemaEdgeDefinition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ElementSerialiserTest {

    private ElementSerialiser elementSerialiser;
    private Schema schema;

    @Before
    public void setUp() {

        final SchemaEdgeDefinition edgeDef = new SchemaEdgeDefinition.Builder()
                .property(TestPropertyNames.PROP_1, TestTypes.PROP_STRING)
                .build();

        schema = new Schema.Builder()
                .vertexSerialiser(new StringSerialiser())
                .edge(TestGroups.EDGE, edgeDef)
                .build();
        elementSerialiser = new ElementSerialiser(schema);
    }
    
    @Test
    public void testCanSeraliseEdgeElement() throws SerialisationException {
        // Given
        final Edge edge = new Edge(TestGroups.EDGE, "source", "destination", true);

        // When
        final byte[] serialisedEdge = elementSerialiser.serialise(edge);
        final Element deserialisedEdge = elementSerialiser.deserialise(serialisedEdge);

        // Then
        assertEquals(edge, deserialisedEdge);
    }

    @Test
    public void testCanSeraliseEntityElement() throws SerialisationException {
    }

    @Test
    public void testGetGroup() throws SerialisationException {
        // Given
        final Edge edge = new Edge(TestGroups.EDGE, "source", "destination", true);

        // When
        final byte[] serialisedEdge = elementSerialiser.serialise(edge);

        // Then
        assertEquals(TestGroups.EDGE, elementSerialiser.getGroup(serialisedEdge));
    }

    @Test
    public void cantSerialiseIntegerClass() throws SerialisationException {
        assertFalse(elementSerialiser.canHandle(Integer.class));
    }

    @Test
    public void canSerialiseEdgeClass() throws SerialisationException {
        assertTrue(elementSerialiser.canHandle(Edge.class));
    }
}
