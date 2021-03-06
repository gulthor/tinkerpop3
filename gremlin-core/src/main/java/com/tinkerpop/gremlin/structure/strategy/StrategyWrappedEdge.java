package com.tinkerpop.gremlin.structure.strategy;

import com.tinkerpop.gremlin.process.graph.GraphTraversal;
import com.tinkerpop.gremlin.structure.Direction;
import com.tinkerpop.gremlin.structure.Edge;
import com.tinkerpop.gremlin.structure.Vertex;
import com.tinkerpop.gremlin.structure.util.wrapped.WrappedEdge;

import java.util.Iterator;

/**
 * @author Stephen Mallette (http://stephen.genoprime.com)
 */
public class StrategyWrappedEdge extends StrategyWrappedElement implements Edge, StrategyWrapped, WrappedEdge<Edge> {
    private final Edge baseEdge;
    private final Strategy.Context<StrategyWrappedEdge> strategyContext;

    public StrategyWrappedEdge(final Edge baseEdge, final StrategyWrappedGraph strategyWrappedGraph) {
        super(baseEdge, strategyWrappedGraph);
        this.strategyContext = new Strategy.Context<>(strategyWrappedGraph.getBaseGraph(), this);
        this.baseEdge = baseEdge;
    }

    @Override
    public Edge getBaseEdge() {
        return this.baseEdge;
    }

    @Override
    public Iterator<Vertex> vertices(final Direction direction) {
        return new StrategyWrappedVertex.StrategyWrappedVertexIterator(this.baseEdge.vertices(direction), strategyWrappedGraph);
    }

    @Override
    public GraphTraversal<Edge, Edge> start() {
        return applyStrategy(this.baseEdge.start());
    }

    public static class StrategyWrappedEdgeIterator implements Iterator<Edge> {
        private final Iterator<Edge> edges;
        private final StrategyWrappedGraph strategyWrappedGraph;

        public StrategyWrappedEdgeIterator(final Iterator<Edge> itty,
                                           final StrategyWrappedGraph strategyWrappedGraph) {
            this.edges = itty;
            this.strategyWrappedGraph = strategyWrappedGraph;
        }

        @Override
        public boolean hasNext() {
            return this.edges.hasNext();
        }

        @Override
        public Edge next() {
            return new StrategyWrappedEdge(this.edges.next(), this.strategyWrappedGraph);
        }
    }
}
