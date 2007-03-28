/*
 * Copyright 2007 University of Konstanz
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
 * $Id: RandomResultTest.java 2624 2007-03-28 15:08:52Z kramis $
 * 
 */

package org.perfidix;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * CHANGELOG: 1.12.2005: fixed standard deviation error.
 */
public class RandomResultTest extends PerfidixTest {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testOne() {

		long[] data = { 1, Benchmark.LONG_NULLVALUE, 3 };
		Result r = Perfidix.createSingleResult(data);
		assertEquals(4l, r.sum());
		assertEquals(10l, r.squareSum());
		assertEquals(2.0, r.avg());
		assertEquals(2l, r.resultCount());
		double expectedDeviation = Math.sqrt(5.0 - 4.0);
		assertEquals(expectedDeviation, r.getStandardDeviation(),
				PerfidixTest.EPSILON);
		assertEquals(1l, r.min());
		assertEquals(3l, r.max());
	}

	/**
	 * sum and average methods did not work when the computation included NULL
	 * values.
	 * 
	 * 
	 */
	@Test
	public void testTwo() {

		long[] data1 = { 1, Benchmark.LONG_NULLVALUE, 3 };
		long[] data2 = { Benchmark.LONG_NULLVALUE, 2, 3 };
		long[] data3 = { 1, 2, 3 };

		ResultContainer<IResult.SingleResult> container = new IResult.MethodResult(
				"********BLA********");
		container.append(Perfidix.createSingleResult("hiho", data1));
		container.append(Perfidix.createSingleResult("hoho", data2));
		container.append(Perfidix.createSingleResult("bla", data3));

		assertEquals(15l, container.sum());
		assertEquals(3l, container.resultCount());

		assertEquals(5.0, container.avg());

		assertEquals(4l, container.min());

		assertEquals(6l, container.max());

		long[] myResult = { 4, 5, 6 };
		Result whatEver = Perfidix.createSingleResult(myResult);
		assertEquals(whatEver.getStandardDeviation(), container
				.getStandardDeviation());

	}

	@Test
	public void testFour() {

		long[] data1 = { 1, 3 };
		long[] data2 = { 1, Benchmark.LONG_NULLVALUE, 3 };
		Result r1 = Perfidix.createSingleResult("1,3", data1);
		Result r2 = Perfidix.createSingleResult("1,null,3", data2);
		assertEquals(r1.sum(), r2.sum());
		assertEquals(r1.avg(), r2.avg());
		assertEquals(r1.getConf95(), r2.getConf95());
		assertEquals(r1.getConf99(), r2.getConf99());
		assertEquals(r1.getStandardDeviation(), r2.getStandardDeviation());
	}

	@Test
	public void testFive() {

		ResultContainer<IResult.SingleResult> resultA;
		ResultContainer<IResult.SingleResult> resultB;
		ResultContainer rc;

		resultA = new IResult.MethodResult("class A");
		resultA.append(Perfidix.createSingleResult("a", new long[] { 1, 1 }));
		resultA.append(Perfidix.createSingleResult("b", new long[] { 1, 1 }));

		resultB = new IResult.MethodResult("class B");
		resultB.append(Perfidix.createSingleResult("c", new long[] { 3, 1 }));
		resultB.append(Perfidix.createSingleResult("d", new long[] { 1, 1 }));

		rc = new IResult.MethodResult("hellowho?");
		rc.append(resultA);
		rc.append(resultB);
		// startDebug();

		assertEquals(4l, resultA.sum());
		assertEquals(6l, resultB.sum());
		assertEquals(resultA.sum() + resultB.sum(), rc.sum());

	}

}
