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

package uk.gov.gchq.gaffer.accumulostore.key.core.impl.byteEntity;

public final class ByteEntityPositions {
    public static final byte ENTITY = (byte) 1;
    public static final byte UNDIRECTED_EDGE = (byte) 4;
    public static final byte CORRECT_WAY_DIRECTED_EDGE = (byte) 2;
    public static final byte INCORRECT_WAY_DIRECTED_EDGE = (byte) 3;

    private ByteEntityPositions() {
        // private constructor to prevent users instantiating this class
        // as it only contains constants.
    }
}
