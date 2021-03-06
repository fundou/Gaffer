/*
 * Copyright 2016-2020 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.gov.gchq.gaffer.serialisation;

import org.junit.jupiter.api.Test;

import uk.gov.gchq.gaffer.commonutil.pair.Pair;
import uk.gov.gchq.gaffer.exception.SerialisationException;
import uk.gov.gchq.gaffer.types.IntegerFreqMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegerFreqMapSerialiserTest extends ToBytesSerialisationTest<IntegerFreqMap> {

    @Test
    public void canSerialiseEmptyFreqMap() throws SerialisationException {
        // Given
        byte[] b = serialiser.serialise(new IntegerFreqMap());

        // When
        final Object o = serialiser.deserialise(b);

        // Then
        assertEquals(IntegerFreqMap.class, o.getClass());
        assertEquals(0, ((IntegerFreqMap) o).size());
    }

    @Test
    public void canSerialiseDeSerialiseFreqMapWithValues() throws SerialisationException {
        // Given
        IntegerFreqMap freqMap = new IntegerFreqMap();
        freqMap.put("x", 10);
        freqMap.put("y", 5);
        freqMap.put("z", 20);

        // When
        byte[] b = serialiser.serialise(freqMap);

        // Then
        IntegerFreqMap o = serialiser.deserialise(b);
        assertEquals(IntegerFreqMap.class, o.getClass());
        assertEquals((Integer) 10, o.get("x"));
        assertEquals((Integer) 5, o.get("y"));
        assertEquals((Integer) 20, o.get("z"));
    }

    @Test
    public void testSerialiserWillSkipEntryWithNullValue() throws SerialisationException {
        // Given
        IntegerFreqMap freqMap = new IntegerFreqMap();
        freqMap.put("x", null);
        freqMap.put("y", 5);
        freqMap.put("z", 20);

        // When
        final byte[] b = serialiser.serialise(freqMap);
        final IntegerFreqMap o = serialiser.deserialise(b);

        // Then
        assertEquals(IntegerFreqMap.class, o.getClass());
        assertNull(o.get("x"));
        assertEquals((Integer) 5, o.get("y"));
        assertEquals((Integer) 20, o.get("z"));
    }

    @Test
    public void cantSerialiseStringClass() {
        assertFalse(serialiser.canHandle(String.class));
    }

    @Test
    public void canSerialiseFreqMap() {
        assertTrue(serialiser.canHandle(IntegerFreqMap.class));
    }

    @Override
    public Serialiser<IntegerFreqMap, byte[]> getSerialisation() {
        return new IntegerFreqMapSerialiser();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Pair<IntegerFreqMap, byte[]>[] getHistoricSerialisationPairs() {
        IntegerFreqMap freqMap = new IntegerFreqMap();
        freqMap.put("x", 10);
        freqMap.put("y", 5);
        freqMap.put("z", 20);

        return new Pair[] {
                new Pair(freqMap, new byte[] {120, 92, 44, 49, 48, 92, 44, 121, 92, 44, 53, 92, 44, 122, 92, 44, 50, 48})
        };
    }

    @Test
    @Override
    public void shouldDeserialiseEmpty() throws SerialisationException {
        assertEquals(new IntegerFreqMap(), serialiser.deserialiseEmpty());
    }
}
