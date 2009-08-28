/*
 * Copyright 2009 Distributed Systems Group, University of Konstanz
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
 * $LastChangedRevision$
 * $LastChangedBy$
 * $LastChangedDate$
 *
 */
package org.perfidix.perclipse.viewtreedata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.perfidix.perclipse.viewtreedata.TreeDataProvider;

/**
 * TestCase for testing the class TreeDataProvider.
 * 
 * @author Lewandowski Lukas, DiSy, University of Konstanz
 */
public class TreeDataProviderTest {

    private TreeDataProvider dataProvider;

    /**
     * Simple setUp method for our TreeDataProvider instance.
     */
    @Before
    public void setUp() {
        dataProvider = new TreeDataProvider("MyElement", 66, 22);
    }

    /**
     * This method tests the getters for the class
     * {@link org.perfidix.perclipse.viewtreedata.TreeDataProvider}.
     */
    @Test
    public void testGettersInClass() {
        assertEquals("MyElement", dataProvider.getParentElementName());
        assertEquals(66, dataProvider.getNumberOfBenchsForElement());
        assertEquals(22, dataProvider.getCurrentBench());
        assertEquals(0, dataProvider.getCurrentBenchError());
        dataProvider.updateCurrentBenchError(5);
        assertEquals(5, dataProvider.getCurrentBenchError());
        assertNotNull(dataProvider.getChildElements());
        assertNotNull(dataProvider.getParent());
        assertEquals(dataProvider, dataProvider.getParent());

    }

    /**
     * Tests the constructor
     * {@link org.perfidix.perclipse.viewtreedata.TreeDataProvider#TreeDataProvider(String, int, int)}
     */
    @Test
    public void testTreeDataProvider() {
        assertNotNull(dataProvider);
        dataProvider = new TreeDataProvider(null, 55, 11);
        assertEquals(null, dataProvider.getParent());
    }

    /**
     * Tests the method updateCurrentBench.
     * {@link org.perfidix.perclipse.viewtreedata.TreeDataProvider#updateCurrentBench(int)}
     */
    @Test
    public void testUpdateCurrentBench() {
        dataProvider.updateCurrentBench(23);
        assertEquals(23, dataProvider.getCurrentBench());
    }

    /**
     * Tests the method update error count.
     * {@link org.perfidix.perclipse.viewtreedata.TreeDataProvider#updateCurrentBenchError(int)}
     */
    @Test
    public void testUpdateCurrentBenchError() {
        dataProvider.updateCurrentBench(23);
        assertEquals(23, dataProvider.getCurrentBench());
    }

    /**
     * Tests the method toString.
     * {@link org.perfidix.perclipse.viewtreedata.TreeDataProvider#toString()}
     */
    @Test
    public void testToString() {
        assertTrue((dataProvider.toString() != null));
        dataProvider = new TreeDataProvider(null, 55, 11);
        assertTrue((dataProvider.toString() == null));
    }

}
