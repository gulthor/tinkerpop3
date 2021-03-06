package com.tinkerpop.gremlin.util.function;

import org.junit.Test;

import java.util.function.UnaryOperator;

import static org.junit.Assert.assertEquals;

/**
 * @author Stephen Mallette (http://stephen.genoprime.com)
 */
public class SQuadFunctionTest {
    @Test
    public void shouldApplyCurrentFunctionAndThenAnotherSuppliedOne() {
        final SQuadFunction<String, String, String, String, String> f = (a, b, c, d) -> a + b + c + d;
        final UnaryOperator<String> after = (s) -> s + "last";
        assertEquals("1234last", f.andThen(after).apply("1", "2", "3", "4"));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowIfAfterFunctionIsNull() {
        final SQuadFunction<String, String, String, String, String> f = (a, b, c, d) -> a + b + c;
        f.andThen(null);
    }
}
