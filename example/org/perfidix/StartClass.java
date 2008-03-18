package org.perfidix;

import org.perfidix.visitor.AsciiTable;

public class StartClass {

    public static void main(String[] args) {
        final IMeter countingmeter = Perfidix.createMeter("test", "t");
        countingmeter.tick();
        countingmeter.tick();
        countingmeter.tick();
        countingmeter.tick();
        countingmeter.tick();
        countingmeter.tick();
        countingmeter.tick();
        countingmeter.tick();
        countingmeter.tick();
        final Benchmark bench = new Benchmark();
        bench.register(countingmeter);
        bench.add(new ClassAnnoBenchmark());
        bench.add(new SomeAnnoBenchmark());
        bench.add(new SomeSpecificSetUpTearDownBenchmark());
        final Result res = bench.run(1);
        final AsciiTable table = new AsciiTable();
        table.visit(res);
        System.out.println(table);
    }

}
