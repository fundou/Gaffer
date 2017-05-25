/*
 * Copyright 2016 Crown Copyright
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
package uk.gov.gchq.gaffer.serialisation.ToStringSerialiser;

import uk.gov.gchq.gaffer.serialisation.Serialiser;

/**
 * Created on 22/05/2017.
 */
public interface ToStringSerialiser<INPUT> extends Serialiser<INPUT, String> {
    /**
     * Handle an incoming null value and generate an appropriate String representation.
     *
     * @return String the serialised output
     */
    @Override
    default String serialiseNull() {
        return null;
    }


    /**
     * Handle an empty String and reconstruct an appropriate representation in Object form.
     *
     * @return INPUT the deserialised object
     */
    @Override
    default INPUT deserialiseEmpty() {
        return null;
    }

}
