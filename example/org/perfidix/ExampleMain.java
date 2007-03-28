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
 * $Id: ExampleMain.java 2624 2007-03-28 15:08:52Z kramis $
 * 
 */

package org.perfidix;

import org.perfidix.visitor.ResultToXml;

public class ExampleMain {

  /**
   * @param args
   */
  public static void main(String[] args) {

    Benchmark b = new Benchmark("Compressed vs. Simple");
    b.add(new SomeBenchmark());
    Result r = b.run(50);
    ResultToXml x = new ResultToXml("helloWorld.xml");
    x.visit(r);
    try {
      x.save();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println();
    }
    System.out.println(r);
  }

}
