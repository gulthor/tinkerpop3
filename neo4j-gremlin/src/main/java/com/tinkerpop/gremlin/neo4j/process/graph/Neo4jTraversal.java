////// THIS CLASS IS AUTO-GENERATED, DO NOT EDIT
////// TO ADD METHODS TO THIS CLASS, EDIT Neo4jTraversalStub

package com.tinkerpop.gremlin.neo4j.process.graph;

import com.tinkerpop.gremlin.neo4j.process.graph.step.map.Neo4jCypherStep;
import com.tinkerpop.gremlin.neo4j.process.graph.util.DefaultNeo4jTraversal;
import com.tinkerpop.gremlin.neo4j.structure.Neo4jGraph;
import com.tinkerpop.gremlin.process.graph.GraphTraversal;
import com.tinkerpop.gremlin.structure.Graph;

import java.util.Map;

/**
 * Neo4jTraversal is merged with {@link GraphTraversal} via the Maven exec-plugin.
 * The Maven plugin yields Neo4jTraversal which is ultimately what is executed at runtime.
 * This class maintains {@link Neo4jTraversal} specific methods that extends {@link GraphTraversal}.
 *
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 * @author Stephen Mallette (http://stephen.genoprime.com)
 */
public interface Neo4jTraversal<S, E> extends GraphTraversal<S, E> {

    public static <S> Neo4jTraversal<S, S> of(final Graph graph) {
        if (!(graph instanceof Neo4jGraph))
            throw new IllegalArgumentException(String.format("graph must be of type %s", Neo4jGraph.class));
        return new DefaultNeo4jTraversal<S, S>((Neo4jGraph) graph);
    }

    public static <S> Neo4jTraversal<S, S> of() {
        return new DefaultNeo4jTraversal<>();
    }

    public default Neo4jTraversal<S, E> cypher(final String query) {
        return (Neo4jTraversal) this.addStep(new Neo4jCypherStep<>(query, this));
    }

    public default Neo4jTraversal<S, E> cypher(final String query, final Map<String, Object> params) {
        return (Neo4jTraversal) this.addStep(new Neo4jCypherStep<>(query, params, this));
    }

	///////////////////////////////////////////////////////////////////////////////////
	//// METHODS INHERITED FROM com.tinkerpop.gremlin.process.graph.GraphTraversal ////
	///////////////////////////////////////////////////////////////////////////////////

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Vertex> addBothE(java.lang.String arg0, java.lang.String arg1, java.lang.Object... arg2) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.addBothE(arg0, arg1, arg2);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Vertex> addE(com.tinkerpop.gremlin.structure.Direction arg0, java.lang.String arg1, java.lang.String arg2, java.lang.Object... arg3) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.addE(arg0, arg1, arg2, arg3);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Vertex> addInE(java.lang.String arg0, java.lang.String arg1, java.lang.Object... arg2) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.addInE(arg0, arg1, arg2);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Vertex> addOutE(java.lang.String arg0, java.lang.String arg1, java.lang.Object... arg2) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.addOutE(arg0, arg1, arg2);
	}

	public default Neo4jTraversal<S, E> aggregate() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.aggregate();
	}

	public default Neo4jTraversal<S, E> aggregate(com.tinkerpop.gremlin.util.function.SFunction<E, ?> arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.aggregate(arg0);
	}

	public default Neo4jTraversal<S, E> aggregate(java.lang.String arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.aggregate(arg0);
	}

	public default Neo4jTraversal<S, E> aggregate(java.lang.String arg0, com.tinkerpop.gremlin.util.function.SFunction<E, ?> arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.aggregate(arg0, arg1);
	}

	public default Neo4jTraversal<S, E> as(java.lang.String arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.as(arg0);
	}

	public default <E2> Neo4jTraversal<S, E2> back(java.lang.String arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.back(arg0);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Vertex> both(java.lang.String... arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.both(arg0);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Vertex> both(int arg0, java.lang.String... arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.both(arg0, arg1);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Edge> bothE(java.lang.String... arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.bothE(arg0);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Edge> bothE(int arg0, java.lang.String... arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.bothE(arg0, arg1);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Vertex> bothV() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.bothV();
	}

	public default <E2> Neo4jTraversal<S, E2> cap() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.cap();
	}

	public default <E2> Neo4jTraversal<S, E2> cap(java.lang.String arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.cap(arg0);
	}

	public default <E2,M> Neo4jTraversal<S, E2> choose(com.tinkerpop.gremlin.util.function.SFunction<com.tinkerpop.gremlin.process.Traverser<S>, M> arg0, java.util.Map<M, com.tinkerpop.gremlin.process.Traversal<S, E2>> arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.choose(arg0, arg1);
	}

	public default <E2> Neo4jTraversal<S, E2> choose(com.tinkerpop.gremlin.util.function.SPredicate<com.tinkerpop.gremlin.process.Traverser<S>> arg0, com.tinkerpop.gremlin.process.Traversal arg1, com.tinkerpop.gremlin.process.Traversal arg2) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.choose(arg0, arg1, arg2);
	}

	public default Neo4jTraversal<S, java.lang.Long> count() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.count();
	}

	public default Neo4jTraversal<S, E> cyclicPath() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.cyclicPath();
	}

	public default Neo4jTraversal<S, E> dedup() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.dedup();
	}

	public default Neo4jTraversal<S, E> dedup(com.tinkerpop.gremlin.util.function.SFunction<E, ?> arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.dedup(arg0);
	}

	public default Neo4jTraversal<S, E> except(E arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.except(arg0);
	}

	public default Neo4jTraversal<S, E> except(java.lang.String arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.except(arg0);
	}

	public default Neo4jTraversal<S, E> except(java.util.Collection<E> arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.except(arg0);
	}

	public default Neo4jTraversal<S, E> filter(com.tinkerpop.gremlin.util.function.SPredicate<com.tinkerpop.gremlin.process.Traverser<E>> arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.filter(arg0);
	}

	public default <E2> Neo4jTraversal<S, E2> flatMap(com.tinkerpop.gremlin.util.function.SFunction<com.tinkerpop.gremlin.process.Traverser<E>, java.util.Iterator<E2>> arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.flatMap(arg0);
	}

	public default Neo4jTraversal<S, java.util.List<E>> fold() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.fold();
	}

	public default <E2> Neo4jTraversal<S, E2> fold(E2 arg0, com.tinkerpop.gremlin.util.function.SBiFunction<E2, E, E2> arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.fold(arg0, arg1);
	}

	public default Neo4jTraversal<S, java.util.Map<java.lang.String, java.lang.Object>> given(java.lang.String arg0, com.tinkerpop.gremlin.process.T arg1, java.lang.String arg2) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.given(arg0, arg1, arg2);
	}

	public default Neo4jTraversal<S, java.util.Map<java.lang.String, java.lang.Object>> given(java.lang.String arg0, com.tinkerpop.gremlin.util.function.SBiPredicate arg1, java.lang.String arg2) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.given(arg0, arg1, arg2);
	}

	public default Neo4jTraversal<S, java.util.Map<java.lang.String, java.lang.Object>> given(java.lang.String arg0, java.lang.String arg1, com.tinkerpop.gremlin.util.function.SBiPredicate arg2) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.given(arg0, arg1, arg2);
	}

	public default Neo4jTraversal<S, E> groupBy(com.tinkerpop.gremlin.util.function.SFunction<E, ?> arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.groupBy(arg0);
	}

	public default Neo4jTraversal<S, E> groupBy(com.tinkerpop.gremlin.util.function.SFunction<E, ?> arg0, com.tinkerpop.gremlin.util.function.SFunction<E, ?> arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.groupBy(arg0, arg1);
	}

	public default Neo4jTraversal<S, E> groupBy(java.lang.String arg0, com.tinkerpop.gremlin.util.function.SFunction<E, ?> arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.groupBy(arg0, arg1);
	}

	public default Neo4jTraversal<S, E> groupBy(com.tinkerpop.gremlin.util.function.SFunction<E, ?> arg0, com.tinkerpop.gremlin.util.function.SFunction<E, ?> arg1, com.tinkerpop.gremlin.util.function.SFunction<java.util.Collection, ?> arg2) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.groupBy(arg0, arg1, arg2);
	}

	public default Neo4jTraversal<S, E> groupBy(java.lang.String arg0, com.tinkerpop.gremlin.util.function.SFunction<E, ?> arg1, com.tinkerpop.gremlin.util.function.SFunction<E, ?> arg2) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.groupBy(arg0, arg1, arg2);
	}

	public default Neo4jTraversal<S, E> groupBy(java.lang.String arg0, com.tinkerpop.gremlin.util.function.SFunction<E, ?> arg1, com.tinkerpop.gremlin.util.function.SFunction<E, ?> arg2, com.tinkerpop.gremlin.util.function.SFunction<java.util.Collection, ?> arg3) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.groupBy(arg0, arg1, arg2, arg3);
	}

	public default Neo4jTraversal<S, E> groupCount() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.groupCount();
	}

	public default Neo4jTraversal<S, E> groupCount(com.tinkerpop.gremlin.util.function.SFunction<E, ?> arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.groupCount(arg0);
	}

	public default Neo4jTraversal<S, E> groupCount(java.lang.String arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.groupCount(arg0);
	}

	public default Neo4jTraversal<S, E> groupCount(java.lang.String arg0, com.tinkerpop.gremlin.util.function.SFunction<E, ?> arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.groupCount(arg0, arg1);
	}

	public default <E2> Neo4jTraversal<S, E2> has(java.lang.String arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.has(arg0);
	}

	public default <E2> Neo4jTraversal<S, E2> has(java.lang.String arg0, java.lang.Object arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.has(arg0, arg1);
	}

	public default <E2> Neo4jTraversal<S, E2> has(java.lang.String arg0, com.tinkerpop.gremlin.process.T arg1, java.lang.Object arg2) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.has(arg0, arg1, arg2);
	}

	public default <E2> Neo4jTraversal<S, E2> has(java.lang.String arg0, com.tinkerpop.gremlin.util.function.SBiPredicate arg1, java.lang.Object arg2) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.has(arg0, arg1, arg2);
	}

	public default <E2> Neo4jTraversal<S, E2> hasNot(java.lang.String arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.hasNot(arg0);
	}

	public default Neo4jTraversal<S, java.lang.Object> id() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.id();
	}

	public default Neo4jTraversal<S, E> identity() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.identity();
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Vertex> in(java.lang.String... arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.in(arg0);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Vertex> in(int arg0, java.lang.String... arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.in(arg0, arg1);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Edge> inE(java.lang.String... arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.inE(arg0);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Edge> inE(int arg0, java.lang.String... arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.inE(arg0, arg1);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Vertex> inV() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.inV();
	}

	public default Neo4jTraversal<S, E> inject(E... arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.inject(arg0);
	}

	public default <E2> Neo4jTraversal<S, E2> interval(java.lang.String arg0, java.lang.Comparable arg1, java.lang.Comparable arg2) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.interval(arg0, arg1, arg2);
	}

	public default Neo4jTraversal<S, E> jump(java.lang.String arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.jump(arg0);
	}

	public default Neo4jTraversal<S, E> jump(java.lang.String arg0, com.tinkerpop.gremlin.util.function.SPredicate<com.tinkerpop.gremlin.process.Traverser<E>> arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.jump(arg0, arg1);
	}

	public default Neo4jTraversal<S, E> jump(java.lang.String arg0, int arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.jump(arg0, arg1);
	}

	public default Neo4jTraversal<S, E> jump(java.lang.String arg0, com.tinkerpop.gremlin.util.function.SPredicate<com.tinkerpop.gremlin.process.Traverser<E>> arg1, com.tinkerpop.gremlin.util.function.SPredicate<com.tinkerpop.gremlin.process.Traverser<E>> arg2) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.jump(arg0, arg1, arg2);
	}

	public default Neo4jTraversal<S, E> jump(java.lang.String arg0, int arg1, com.tinkerpop.gremlin.util.function.SPredicate<com.tinkerpop.gremlin.process.Traverser<E>> arg2) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.jump(arg0, arg1, arg2);
	}

	public default Neo4jTraversal<S, java.lang.String> label() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.label();
	}

	public default <E2> Neo4jTraversal<S, E2> map(com.tinkerpop.gremlin.util.function.SFunction<com.tinkerpop.gremlin.process.Traverser<E>, E2> arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.map(arg0);
	}

	public default <E2> Neo4jTraversal<S, java.util.Map<java.lang.String, E2>> match(java.lang.String arg0, com.tinkerpop.gremlin.process.Traversal... arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.match(arg0, arg1);
	}

	public default Neo4jTraversal<S, E> order() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.order();
	}

	public default Neo4jTraversal<S, E> order(java.util.Comparator<com.tinkerpop.gremlin.process.Traverser<E>> arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.order(arg0);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Vertex> otherV() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.otherV();
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Vertex> out(java.lang.String... arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.out(arg0);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Vertex> out(int arg0, java.lang.String... arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.out(arg0, arg1);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Edge> outE(java.lang.String... arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.outE(arg0);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Edge> outE(int arg0, java.lang.String... arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.outE(arg0, arg1);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Vertex> outV() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.outV();
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.process.Path> path(com.tinkerpop.gremlin.util.function.SFunction... arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.path(arg0);
	}

	public default <E2> Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Property<E2>> property(java.lang.String arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.property(arg0);
	}

	public default Neo4jTraversal<S, E> random(double arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.random(arg0);
	}

	public default Neo4jTraversal<S, E> range(int arg0, int arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.range(arg0, arg1);
	}

	public default Neo4jTraversal<S, E> retain(E arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.retain(arg0);
	}

	public default Neo4jTraversal<S, E> retain(java.lang.String arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.retain(arg0);
	}

	public default Neo4jTraversal<S, E> retain(java.util.Collection<E> arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.retain(arg0);
	}

	public default <E2> Neo4jTraversal<S, E2> select(java.lang.String arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.select(arg0);
	}

	public default <E2> Neo4jTraversal<S, java.util.Map<java.lang.String, E2>> select(com.tinkerpop.gremlin.util.function.SFunction... arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.select(arg0);
	}

	public default <E2> Neo4jTraversal<S, E2> select(java.lang.String arg0, com.tinkerpop.gremlin.util.function.SFunction arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.select(arg0, arg1);
	}

	public default <E2> Neo4jTraversal<S, java.util.Map<java.lang.String, E2>> select(java.util.List<java.lang.String> arg0, com.tinkerpop.gremlin.util.function.SFunction... arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.select(arg0, arg1);
	}

	public default Neo4jTraversal<S, E> shuffle() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.shuffle();
	}

	public default Neo4jTraversal<S, E> sideEffect(com.tinkerpop.gremlin.util.function.SConsumer<com.tinkerpop.gremlin.process.Traverser<E>> arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.sideEffect(arg0);
	}

	public default Neo4jTraversal<S, E> simplePath() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.simplePath();
	}

	public default Neo4jTraversal<S, E> store() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.store();
	}

	public default Neo4jTraversal<S, E> store(com.tinkerpop.gremlin.util.function.SFunction<E, ?> arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.store(arg0);
	}

	public default Neo4jTraversal<S, E> store(java.lang.String arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.store(arg0);
	}

	public default Neo4jTraversal<S, E> store(java.lang.String arg0, com.tinkerpop.gremlin.util.function.SFunction<E, ?> arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.store(arg0, arg1);
	}

	public default Neo4jTraversal<S, E> subgraph(com.tinkerpop.gremlin.util.function.SPredicate<com.tinkerpop.gremlin.structure.Edge> arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.subgraph(arg0);
	}

	public default Neo4jTraversal<S, E> subgraph(java.lang.String arg0, com.tinkerpop.gremlin.util.function.SPredicate<com.tinkerpop.gremlin.structure.Edge> arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.subgraph(arg0, arg1);
	}

	public default Neo4jTraversal<S, E> subgraph(java.util.Set<java.lang.Object> arg0, java.util.Map<java.lang.Object, com.tinkerpop.gremlin.structure.Vertex> arg1, com.tinkerpop.gremlin.util.function.SPredicate<com.tinkerpop.gremlin.structure.Edge> arg2) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.subgraph(arg0, arg1, arg2);
	}

	public default Neo4jTraversal<S, E> subgraph(java.lang.String arg0, java.util.Set<java.lang.Object> arg1, java.util.Map<java.lang.Object, com.tinkerpop.gremlin.structure.Vertex> arg2, com.tinkerpop.gremlin.util.function.SPredicate<com.tinkerpop.gremlin.structure.Edge> arg3) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.subgraph(arg0, arg1, arg2, arg3);
	}

	public default Neo4jTraversal<S, E> submit(com.tinkerpop.gremlin.process.computer.GraphComputer arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.submit(arg0);
	}

	public default Neo4jTraversal<S, E> timeLimit(long arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.timeLimit(arg0);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Vertex> to(com.tinkerpop.gremlin.structure.Direction arg0, java.lang.String... arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.to(arg0, arg1);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Vertex> to(com.tinkerpop.gremlin.structure.Direction arg0, int arg1, java.lang.String... arg2) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.to(arg0, arg1, arg2);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Edge> toE(com.tinkerpop.gremlin.structure.Direction arg0, java.lang.String... arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.toE(arg0, arg1);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Edge> toE(com.tinkerpop.gremlin.structure.Direction arg0, int arg1, java.lang.String... arg2) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.toE(arg0, arg1, arg2);
	}

	public default Neo4jTraversal<S, com.tinkerpop.gremlin.structure.Vertex> toV(com.tinkerpop.gremlin.structure.Direction arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.toV(arg0);
	}

	public default Neo4jTraversal<S, E> trackPaths() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.trackPaths();
	}

	public default Neo4jTraversal<S, E> tree(com.tinkerpop.gremlin.util.function.SFunction... arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.tree(arg0);
	}

	public default Neo4jTraversal<S, E> tree(java.lang.String arg0, com.tinkerpop.gremlin.util.function.SFunction... arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.tree(arg0, arg1);
	}

	public default <E2> Neo4jTraversal<S, E2> unfold() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.unfold();
	}

	public default <E2> Neo4jTraversal<S, E2> value() {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.value();
	}

	public default <E2> Neo4jTraversal<S, E2> value(java.lang.String arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.value(arg0);
	}

	public default <E2> Neo4jTraversal<S, E2> value(java.lang.String arg0, E2 arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.value(arg0, arg1);
	}

	public default <E2> Neo4jTraversal<S, E2> value(java.lang.String arg0, java.util.function.Supplier<E2> arg1) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.value(arg0, arg1);
	}

	public default Neo4jTraversal<S, java.util.Map<java.lang.String, java.lang.Object>> values(java.lang.String... arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.values(arg0);
	}

	public default Neo4jTraversal<S, E> with(java.lang.Object... arg0) {
		return (Neo4jTraversal) com.tinkerpop.gremlin.process.graph.GraphTraversal.super.with(arg0);
	}

}