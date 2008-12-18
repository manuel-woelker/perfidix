/*
 * Copyright 2008 Distributed Systems Group, University of Konstanz
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
 *
 * $Revision$
 * $Author$
 * $Date$
 *
 */
package org.perfidix.result;

import java.lang.reflect.Method;
import java.util.Set;

import org.perfidix.meter.AbstractMeter;

/**
 * Class to hold the result related to one method. That means that all
 * information is inherited from the {@link ClassResult} plus the possibility to
 * add additional datasets.
 * 
 * @author Sebastian Graf, University of Konstanz
 * @author Alexander Onea, neue Couch
 */
public final class MethodResult extends AbstractResult {

    /** Method which corresponds to this result */
    private final Method meth;

    /**
     * Simple Constructor
     * 
     * @param paramMethod
     *            , the method related to these results
     * @param paramMeters
     *            , the related meters to this results
     */
    public MethodResult(
            final Method paramMethod, final Set<AbstractMeter> paramMeters) {
        super(paramMeters);
        meth = paramMethod;

    }

    /**
     * Adding a result to a given meter
     * 
     * @param meter
     *            where the result should be stored to.
     * @param data
     *            to be stored.
     */
    public final void addResult(final AbstractMeter meter, final double data) {
        if (!super.getRegisteredMeters().contains(meter)) {
            throw new IllegalStateException(new StringBuilder(
                    "Need to initialize the meter first! ")
                    .append(meter).append(" was not initialized!").toString());
        } else {
            super.getResultSet(meter).add(data);
        }
    }

    /**
     * Getter for the method.
     * 
     * @return the method of this result
     */
    public final Method getMeth() {
        return meth;
    }

}