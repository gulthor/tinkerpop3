package com.tinkerpop.gremlin.process.graph.step.sideEffect;

import com.tinkerpop.gremlin.process.Traversal;
import com.tinkerpop.gremlin.process.graph.marker.PathConsumer;
import com.tinkerpop.gremlin.process.graph.marker.Reversible;
import com.tinkerpop.gremlin.process.graph.marker.SideEffectCapable;
import com.tinkerpop.gremlin.structure.Edge;
import com.tinkerpop.gremlin.structure.Graph;
import com.tinkerpop.gremlin.structure.Vertex;
import com.tinkerpop.gremlin.structure.util.ElementHelper;
import com.tinkerpop.gremlin.structure.util.GraphFactory;
import com.tinkerpop.gremlin.util.function.SPredicate;
import org.javatuples.Pair;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * A side-effect step that produces an edge induced subgraph.
 *
 * @author Stephen Mallette (http://stephen.genoprime.com)
 */
public class SubgraphStep<S> extends SideEffectStep<S> implements SideEffectCapable, PathConsumer, Reversible {
    private final Graph subgraph;
    private final boolean subgraphSupportsUserIds;
    private final Map<Object, Vertex> idVertexMap;
    private final Set<Object> edgeIdsAdded;
    private final String sideEffectKey;
    private static final Map<String, Object> DEFAULT_CONFIGURATION = new HashMap<String, Object>() {{
        put("gremlin.graph", "com.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph");
    }};

    // TODO: add support for side-effecting out an edge list.

    public SubgraphStep(final Traversal traversal, final String sideEffectKey,
                        final Set<Object> edgeIdHolder,
                        final Map<Object, Vertex> idVertexMap,
                        final SPredicate<Edge> includeEdge) {
        super(traversal);
        this.sideEffectKey = null == sideEffectKey ? this.getAs() : sideEffectKey;
        this.edgeIdsAdded = null == edgeIdHolder ? new HashSet<>() : edgeIdHolder;
        this.idVertexMap = null == idVertexMap ? new HashMap<>() : idVertexMap;
        this.subgraph = this.traversal.sideEffects().getOrCreate(this.sideEffectKey, () -> GraphFactory.open(DEFAULT_CONFIGURATION));
        this.subgraphSupportsUserIds = this.subgraph.features().vertex().supportsUserSuppliedIds();

        this.setConsumer(traverser -> {
            traverser.getPath().stream().map(Pair::getValue1)
                    .filter(i -> i instanceof Edge)
                    .map(e -> (Edge) e)
                    .filter(e -> !this.edgeIdsAdded.contains(e.id()))
                    .filter(includeEdge::test)
                    .forEach(e -> {
                        final Vertex newVOut = getOrCreateVertex(e.outV().next());
                        final Vertex newVIn = getOrCreateVertex(e.inV().next());
                        newVOut.addEdge(e.label(), newVIn, ElementHelper.getProperties(e, this.subgraphSupportsUserIds, false, Collections.emptySet(), Collections.emptySet()));
                        // TODO: If userSuppliedIds exist, don't do this to save sideEffects
                        this.edgeIdsAdded.add(e.id());
                    });
        });
    }

    public String getSideEffectKey() {
        return this.sideEffectKey;
    }

    private Vertex getOrCreateVertex(final Vertex vertex) {
        Vertex foundVertex = null;
        if (this.subgraphSupportsUserIds) {
            try {
                foundVertex = this.subgraph.v(vertex.id());
            } catch (final NoSuchElementException e) {
                // do nothing;
            }
        } else {
            foundVertex = this.idVertexMap.get(vertex.id());
        }

        if (null == foundVertex) {
            foundVertex = this.subgraph.addVertex(ElementHelper.getProperties(vertex, this.subgraphSupportsUserIds, true, Collections.emptySet(), Collections.emptySet()));
            if (!this.subgraphSupportsUserIds)
                this.idVertexMap.put(vertex.id(), foundVertex);
        }
        return foundVertex;
    }
}
