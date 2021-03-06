<!--
~~
~~ Copyright (c) 2011, University of Konstanz, Distributed Systems Group
~~ All rights reserved.
~~
~~ Redistribution and use in source and binary forms, with or without
~~ modification, are permitted provided that the following conditions are met:
~~     * Redistributions of source code must retain the above copyright
~~       notice, this list of conditions and the following disclaimer.
~~     * Redistributions in binary form must reproduce the above copyright
~~       notice, this list of conditions and the following disclaimer in the
~~       documentation and/or other materials provided with the distribution.
~~     * Neither the name of the University of Konstanz nor the
~~       names of its contributors may be used to endorse or promote products
~~       derived from this software without specific prior written permission.
~~
~~ THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
~~ ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
~~ WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
~~ DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
~~ DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
~~ (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
~~ LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
~~ ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
~~ (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
~~ SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
~~
-->

<a href="https://github.com/disy/perfidix"><img style="position: absolute; top: 0; right: 0; border: 0;" src="https://s3.amazonaws.com/github/ribbons/forkme_right_red_aa0000.png" alt="Fork me on GitHub"/></a>

Examples
==========
Perfidix offers a very flexible usage based on annotation. The examples contains all the same usecase but are different implemented. A compressed file access is compared to a normal file access. The code in the following examples therefore are doing exactly the same.

Example 1
---------

The setUp() and tear-Down() methods are invoked before each run of each method.

	public class SomeAnnoBenchmark { 
    	CompressedHandler c; SimpleFileHandler s;
    
    	//setUp , invoked before each run
    	@BeforeEachBenchRun
    	public void setUp() {
    		c = new CompressedHandler ( ) ; s = new SimpleFileHandler ();
    	}

    	//tearDown , invoked after each run
    	@AfterEachBenchRun 
    	public void tearDown() {
    		c = null; s = null;
    	}

    	//bench Method 1
    	@Bench
    	public void benchCWrite() {
    		c.write(”hello world”);
    	}

    	//bench Method 1
    	@Bench
    	public void benchSRead() {
    		s.read();
    	} 

    	//bench Method 2
    	@Bench
    	public void benchSWrite () {
    		s.write(”hello world”); 
    	}

    	//bench Method 3
    	@Bench
    	public void benchCRead() {
    		c.read();
    	}
	}

Example 2
----------- 

In the following example, you see the usage of specific setUp and tearDown methods. These methods have the same behavior than methods with the BeforeBenchRun annotation.

	public class SomeSpecificSetUpTearDownBenchmark { 
    	CompressedHandler c; SimpleFileHandler s;
    
    	//setUp for benchCRead/benchCWrite . Invoked via @Bench−params
    	public void setUpCompressed () {
    		c = new CompressedHandler ( ) ;
    	}

    	//tearDown for benchCRead/benchCWrite . Invoked via @Bench−params
    	public void tearDownCompressed() {
    		c = null;
    	}

    	//setUp for benchSRead/benchSWrite . Invoked via @Bench−params
    	public void setUpSimple () {
    		s = new SimpleFileHandler ();
    	}

    	//tearDown for benchSRead/benchSWrite . Invoked via @Bench−params 
    	public void tearDownSimple () {
    		s = null;
    	}

    	//bench Method 1
    	@Bench(beforeEachBenchRun=”setUpCompressed” ,afterEachBenchRun=”tearDownCompressed”)
    	public void benchCWrite() { 
    		c.write(”hello world”);
    	}

    	//bench Method 2
    	@Bench(beforeEachBenchRun =”setUpSimple”, afterEachBenchRun =”tearDownSimple”)
    	public void benchSWrite () { 
    		s.write(”hello world”);
    	}

    	//bench Method 3
    	@Bench(beforeEachBenchRun =”setUpCompressed”, afterEachBenchRun=”tearDownCompressed”)
    	public void benchCRead() { 
    		c.read();
    	}

    	//bench Method 4
    	@Bench(beforeEachBenchRun =”setUpSimple”, afterEachBenchRun =”tearDownSimple”)
    	public void benchSRead() { 
    		s.read();
    	}
	}

Example 3
----------

In the following example, the same Bench is a little bit modified:
First of all, the class-annotation BenchClass with the param runs is used. That means 	that every method which is parameter-free and is not annotated with a setUp / tearDown annotation, is benched 10 times, except the benchSWrite method, which has an extra Bench annotation with a run parameter. This method is benched 60 times.\
Additional to that, every possible setUp and tearDown method is used in this example. A description is given in the code and in Section 3.

    @BenchClass(runs=10)
    public class ClassAnnoBenchmark {
    	CompressedHandler c ; SimpleFileHandler s ;

    	String toTest; long testLength;

    	//classwide setUp , invoked just one time , just setting the length
    	@BeforeBenchClass
    	public void beforeClass () {
    		Math.abs(testLength = new Random().nextInt(100));
    	}

    	//methodWide setUp , invoked just one time per method , building a
    	@BeforeFirstBenchRun
    	public void beforeMethod () {
    		for(int i = 0; i<testLength; i++) {
    			toTest = toTest + (char)(new Random(). nextInt(Ch
    			}
    		}

    		//normal setUp , invoked one time per method per run, instantiating
    		@BeforeEachBenchRun
    	public void beforeRun () {
    		c = new CompressedHandler ( ) ; 
    		s = new SimpleFileHandler ();
    	}

    	//normal tearDown , invoked one time per method per run , removing
    	@AfterEachBenchRun 
    	public void afterRun () {
    		c = null;
    		s = null;
    	}

    	//methodWide tearDown , invoked just one time
    	@AfterLastBenchRun
    	public void afterMethod () {
    		toTest = null;
    	}

    	//classwide tearDown , invoked just one time ,
    	@AfterBenchClass
    	public void afterClass () {
    		testLength = −1;
    	}

    	//bench 1, invoked because of class−annotation
    	public void benchCWrite() {
    		c.write(”hello world”);
    	}

    	//bench 2, invoked because of method−annotation 
    	@Bench(runs=60)
    	public void benchSWrite () {
    		s.write(”hello world”);
    	}

    	//bench 3, invoked because of class−annotation 
    	public void benchCRead() {
    		c.read();
    	}

    	//bench 4, invoked because of class−annotation
    	public void benchSRead() {
    		s.read();
    	}
	}
