package com.tinkerpop.gremlin.process.computer;

import com.tinkerpop.gremlin.AbstractGremlinTest;
import com.tinkerpop.gremlin.ExceptionCoverage;
import com.tinkerpop.gremlin.FeatureRequirement;
import com.tinkerpop.gremlin.LoadGraphWith;
import com.tinkerpop.gremlin.process.computer.lambda.LambdaMapReduce;
import com.tinkerpop.gremlin.process.computer.lambda.LambdaVertexProgram;
import com.tinkerpop.gremlin.structure.Graph;
import com.tinkerpop.gremlin.structure.util.StringFactory;
import com.tinkerpop.gremlin.util.StreamFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.tinkerpop.gremlin.LoadGraphWith.GraphData.CLASSIC_DOUBLE;
import static org.junit.Assert.*;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
@ExceptionCoverage(exceptionClass = GraphComputer.Exceptions.class, methods = {
        "providedKeyIsNotAMemoryKey",
        "computerHasNoVertexProgramNorMapReducers",
        "computerHasAlreadyBeenSubmittedAVertexProgram"
})
@ExceptionCoverage(exceptionClass = Graph.Exceptions.class, methods = {
        "graphDoesNotSupportProvidedGraphComputer",
        "onlyOneOrNoGraphComputerClass"
})
public class GraphComputerTest extends AbstractGremlinTest {

    @Test
    @FeatureRequirement(featureClass = Graph.Features.GraphFeatures.class, feature = Graph.Features.GraphFeatures.FEATURE_COMPUTER)
    public void shouldHaveStandardStringRepresentation() {
        final GraphComputer computer = g.compute();
        assertEquals(StringFactory.computerString(computer), computer.toString());
    }

    @Test
    @FeatureRequirement(featureClass = Graph.Features.GraphFeatures.class, feature = Graph.Features.GraphFeatures.FEATURE_COMPUTER)
    public void shouldNotAllowBadGraphComputers() {
        try {
            g.compute(BadGraphComputer.class);
            fail("Providing a bad graph computer class should fail");
        } catch (Exception ex) {
            final Exception expectedException = Graph.Exceptions.graphDoesNotSupportProvidedGraphComputer(BadGraphComputer.class);
            assertEquals(expectedException.getClass(), ex.getClass());
            assertEquals(expectedException.getMessage(), ex.getMessage());
        }
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    @FeatureRequirement(featureClass = Graph.Features.GraphFeatures.class, feature = Graph.Features.GraphFeatures.FEATURE_COMPUTER)
    public void shouldHaveImmutableComputeResultMemory() throws Exception {
        final ComputerResult results = g.compute().program(LambdaVertexProgram.build().
                setup(s -> {
                }).
                execute((v, m, s) -> {
                }).
                terminate(s -> true).memoryComputeKeys(new HashSet<>(Arrays.asList("set", "incr", "and", "or"))).create()).submit().get();

        try {
            results.getMemory().set("set", "test");
        } catch (Exception ex) {
            final Exception expectedException = Memory.Exceptions.memoryCompleteAndImmutable();
            assertEquals(expectedException.getClass(), ex.getClass());
            assertEquals(expectedException.getMessage(), ex.getMessage());
        }

        try {
            results.getMemory().incr("incr", 1);
        } catch (Exception ex) {
            final Exception expectedException = Memory.Exceptions.memoryCompleteAndImmutable();
            assertEquals(expectedException.getClass(), ex.getClass());
            assertEquals(expectedException.getMessage(), ex.getMessage());
        }

        try {
            results.getMemory().and("and", true);
        } catch (Exception ex) {
            final Exception expectedException = Memory.Exceptions.memoryCompleteAndImmutable();
            assertEquals(expectedException.getClass(), ex.getClass());
            assertEquals(expectedException.getMessage(), ex.getMessage());
        }

        try {
            results.getMemory().or("or", false);
        } catch (Exception ex) {
            final Exception expectedException = Memory.Exceptions.memoryCompleteAndImmutable();
            assertEquals(expectedException.getClass(), ex.getClass());
            assertEquals(expectedException.getMessage(), ex.getMessage());
        }
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    @FeatureRequirement(featureClass = Graph.Features.GraphFeatures.class, feature = Graph.Features.GraphFeatures.FEATURE_COMPUTER)
    public void shouldNotAllowBadMemoryKeys() throws Exception {
        try {
            g.compute().program(LambdaVertexProgram.build().
                    setup(s -> s.set("", true)).
                    execute((v, m, s) -> {
                    }).
                    terminate(s -> true).memoryComputeKeys(new HashSet<>(Arrays.asList("")))
                    .create()).submit().get();
            fail("Providing empty memory key should fail");
        } catch (Exception ex) {
            final Exception expectedException = Memory.Exceptions.memoryKeyCanNotBeEmpty();
            assertEquals(expectedException.getClass(), ex.getClass());
            assertEquals(expectedException.getMessage(), ex.getMessage());
        }

        try {
            g.compute().program(LambdaVertexProgram.build().
                    setup(s -> s.set("blah", null)).
                    execute((v, m, s) -> {
                    }).
                    terminate(s -> true).memoryComputeKeys(new HashSet<>(Arrays.asList(""))).
                    memoryComputeKeys(new HashSet<>(Arrays.asList("blah")))
                    .create()).submit().get();
            fail("Providing null memory key should fail");
        } catch (Exception ex) {
            final Exception expectedException = Memory.Exceptions.memoryValueCanNotBeNull();
            assertEquals(expectedException.getClass(), ex.getCause().getClass());
            assertEquals(expectedException.getMessage(), ex.getCause().getMessage());
        }
    }

    @Test
    @FeatureRequirement(featureClass = Graph.Features.GraphFeatures.class, feature = Graph.Features.GraphFeatures.FEATURE_COMPUTER)
    public void shouldOnlyAllowOneOrNoGraphComputerClass() throws Exception {
        try {
            g.compute(BadGraphComputer.class, BadGraphComputer.class).submit().get();
            fail("Should throw an IllegalArgument when two graph computers are passed in");
        } catch (Exception ex) {
            final Exception expectedException = Graph.Exceptions.onlyOneOrNoGraphComputerClass();
            assertEquals(expectedException.getClass(), ex.getClass());
            assertEquals(expectedException.getMessage(), ex.getMessage());
        }
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    @FeatureRequirement(featureClass = Graph.Features.GraphFeatures.class, feature = Graph.Features.GraphFeatures.FEATURE_COMPUTER)
    public void shouldNotAllowWithNoVertexProgramNorMapReducers() throws Exception {
        try {
            g.compute().submit().get();
            fail("Should throw an IllegalStateException when there is no vertex program nor map reducers");
        } catch (Exception ex) {
            final Exception expectedException = GraphComputer.Exceptions.computerHasNoVertexProgramNorMapReducers();
            assertEquals(expectedException.getClass(), ex.getClass());
            assertEquals(expectedException.getMessage(), ex.getMessage());
        }
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    @FeatureRequirement(featureClass = Graph.Features.GraphFeatures.class, feature = Graph.Features.GraphFeatures.FEATURE_COMPUTER)
    public void shouldNotAllowTheSameComputerToExecutedTwice() throws Exception {
        final GraphComputer computer = g.compute().program(identity());
        computer.submit().get(); // this should work as its the first run of the graph computer

        try {
            computer.submit(); // this should fail as the computer has already been executed
            fail("Using the same graph computer to compute again should not be possible");
        } catch (IllegalStateException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail("Should yield an illegal state exception for graph computer being executed twice");
        }

        computer.program(identity());
        // test no rerun of graph computer
        try {
            computer.submit(); // this should fail as the computer has already been executed even through new program submitted
            fail("Using the same graph computer to compute again should not be possible");
        } catch (IllegalStateException e) {
            assertTrue(true);
        } catch (Exception e) {
            fail("Should yield an illegal state exception for graph computer being executed twice");
        }
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    @FeatureRequirement(featureClass = Graph.Features.GraphFeatures.class, feature = Graph.Features.GraphFeatures.FEATURE_COMPUTER)
    public void shouldRequireRegisteringMemoryKeys() throws Exception {
        try {
            g.compute().program(LambdaVertexProgram.build().
                    setup(s -> s.set("or", true)).
                    execute((v, m, s) -> {
                    }).
                    terminate(s -> s.getIteration() >= 2).create()).submit().get();
            // TODO: Giraph fails HARD and kills the process (thus, test doesn't proceed past this point)
            fail("Should fail with an ExecutionException[IllegalArgumentException]");
        } catch (ExecutionException e) {
            assertEquals(IllegalArgumentException.class, e.getCause().getClass());
        } catch (Exception e) {
            fail("Should fail with an ExecutionException[IllegalArgumentException]: " + e);
        }
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    @FeatureRequirement(featureClass = Graph.Features.GraphFeatures.class, feature = Graph.Features.GraphFeatures.FEATURE_COMPUTER)
    public void shouldAllowMapReduceWithNoVertexProgram() throws Exception {
        final ComputerResult results = g.compute().mapReduce(LambdaMapReduce.<MapReduce.NullObject, Integer, MapReduce.NullObject, Integer, Integer>build()
                .map((v, e) -> v.<Integer>property("age").ifPresent(age -> e.emit(MapReduce.NullObject.instance(), age)))
                .reduce((k, vv, e) -> e.emit(MapReduce.NullObject.instance(), StreamFactory.stream(vv).mapToInt(i -> i).sum()))
                .memory(i -> i.next().getValue1())
                .memoryKey("ageSum").create())
                .submit().get();
        assertEquals(123, results.getMemory().<Integer>get("ageSum").intValue());
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    @FeatureRequirement(featureClass = Graph.Features.GraphFeatures.class, feature = Graph.Features.GraphFeatures.FEATURE_COMPUTER)
    public void shouldHaveConsistentMemoryAndExceptions() throws Exception {
        GraphComputer computer = g.compute();
        ComputerResult results = computer.program(LambdaVertexProgram.build().
                setup(s -> s.set("or", true)).
                execute((v, m, s) -> {
                    if (s.isInitialIteration()) {
                        v.property("nameLengthCounter", v.<String>value("name").length());
                        s.incr("counter", v.<String>value("name").length());
                        s.and("and", v.<String>value("name").length() == 5);
                        s.and("or", false);
                    } else
                        v.property("nameLengthCounter", v.<String>value("name").length() + v.<Integer>value("nameLengthCounter"));
                }).
                terminate(s -> s.getIteration() >= 2).
                elementComputeKeys("nameLengthCounter", VertexProgram.KeyType.VARIABLE).
                memoryComputeKeys(new HashSet<>(Arrays.asList("counter", "and", "or"))).create()).submit().get();
        assertEquals(1, results.getMemory().getIteration());
        assertEquals(3, results.getMemory().asMap().size());
        assertEquals(3, results.getMemory().keys().size());
        assertTrue(results.getMemory().keys().contains("counter"));
        assertTrue(results.getMemory().keys().contains("and"));
        assertTrue(results.getMemory().keys().contains("or"));
        assertTrue(results.getMemory().getRuntime() >= 0);

        assertEquals(Long.valueOf(28), results.getMemory().<Long>get("counter"));
        assertFalse(results.getMemory().<Boolean>get("and"));
        assertFalse(results.getMemory().<Boolean>get("or"));
        try {
            results.getMemory().get("BAD");
            fail("Should throw an IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals(Memory.Exceptions.memoryDoesNotExist("BAD").getMessage(), e.getMessage());
        }
        assertEquals(Long.valueOf(6), results.getGraph().V().count().next());

        results.getGraph().V().forEach(v -> {
            assertTrue(v.property("nameLengthCounter").isPresent());
            assertEquals(Integer.valueOf(v.<String>value("name").length() * 2), Integer.valueOf(v.<Integer>value("nameLengthCounter")));
        });
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    @FeatureRequirement(featureClass = Graph.Features.GraphFeatures.class, feature = Graph.Features.GraphFeatures.FEATURE_COMPUTER)
    public void shouldSupportMultipleMapReduceJobs() throws Exception {
        final ComputerResult results = g.compute().program(LambdaVertexProgram.build()
                .execute((v, m, s) -> v.<Integer>property("counter", s.isInitialIteration() ? 1 : v.<Integer>value("counter") + 1))
                .terminate(s -> s.getIteration() > 9)
                .elementComputeKeys("counter", VertexProgram.KeyType.VARIABLE).create())
                .mapReduce(LambdaMapReduce.<MapReduce.NullObject, Integer, MapReduce.NullObject, Integer, Integer>build()
                        .map((v, e) -> e.emit(MapReduce.NullObject.instance(), v.value("counter")))
                        .reduce((k, vv, e) -> {
                            int counter = 0;
                            while (vv.hasNext()) {
                                counter = counter + vv.next();
                            }
                            e.emit(MapReduce.NullObject.instance(), counter);

                        })
                        .memory(i -> i.next().getValue1())
                        .memoryKey("a").create())
                .mapReduce(LambdaMapReduce.<MapReduce.NullObject, Integer, MapReduce.NullObject, Integer, Integer>build()
                        .map((v, e) -> e.emit(MapReduce.NullObject.instance(), v.value("counter")))
                        .combine((k, vv, e) -> e.emit(MapReduce.NullObject.instance(), 1))
                        .reduce((k, vv, e) -> e.emit(MapReduce.NullObject.instance(), 1))
                        .memory(i -> i.next().getValue1())
                        .memoryKey("b").create())
                .submit().get();


        assertEquals(60, results.getMemory().<Integer>get("a").intValue());
        assertEquals(1, results.getMemory().<Integer>get("b").intValue());
    }

    private static LambdaVertexProgram identity() {
        return LambdaVertexProgram.build().
                setup(s -> {
                }).
                execute((v, m, s) -> {
                }).
                terminate(s -> true).create();
    }

    class BadGraphComputer implements GraphComputer {
        public GraphComputer isolation(final Isolation isolation) {
            return null;
        }

        public GraphComputer program(final VertexProgram vertexProgram) {
            return null;
        }

        public GraphComputer mapReduce(final MapReduce mapReduce) {
            return null;
        }

        public Future<ComputerResult> submit() {
            return null;
        }
    }
}
