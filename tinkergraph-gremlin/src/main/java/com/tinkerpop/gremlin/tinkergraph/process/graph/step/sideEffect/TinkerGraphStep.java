package com.tinkerpop.gremlin.tinkergraph.process.graph.step.sideEffect;

import com.tinkerpop.gremlin.process.Traversal;
import com.tinkerpop.gremlin.process.graph.step.sideEffect.GraphStep;
import com.tinkerpop.gremlin.process.util.TraversalHelper;
import com.tinkerpop.gremlin.structure.Compare;
import com.tinkerpop.gremlin.structure.Edge;
import com.tinkerpop.gremlin.structure.Element;
import com.tinkerpop.gremlin.structure.Vertex;
import com.tinkerpop.gremlin.structure.util.HasContainer;
import com.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import com.tinkerpop.gremlin.tinkergraph.structure.TinkerHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class TinkerGraphStep<E extends Element> extends GraphStep<E> {

    public TinkerGraph graph;
    public final List<HasContainer> hasContainers = new ArrayList<>();

    public TinkerGraphStep(final Traversal traversal, final Class<E> returnClass, final TinkerGraph graph) {
        super(traversal, returnClass);
        this.graph = graph;
    }

    public void generateTraverserIterator(final boolean trackPaths) {
        this.start = Vertex.class.isAssignableFrom(this.returnClass) ? this.vertices() : this.edges();
        super.generateTraverserIterator(trackPaths);
    }

    private Iterator<? extends Edge> edges() {
        final HasContainer indexedContainer = getIndexKey(Edge.class);
        final Stream<? extends Edge> edgeStream = (null == indexedContainer) ?
                TinkerHelper.getEdges(this.graph).stream() :
                TinkerHelper.queryEdgeIndex(this.graph, indexedContainer.key, indexedContainer.value).stream();

        // the copy to a new List is intentional as remove() operations will cause ConcurrentModificationException otherwise
        return edgeStream.filter(e -> HasContainer.testAll(e, hasContainers)).collect(Collectors.<Edge>toList()).iterator();
    }

    private Iterator<? extends Vertex> vertices() {
        final HasContainer indexedContainer = getIndexKey(Vertex.class);
        final Stream<? extends Vertex> vertexStream = (null == indexedContainer) ?
                TinkerHelper.getVertices(this.graph).stream() :
                TinkerHelper.queryVertexIndex(this.graph, indexedContainer.key, indexedContainer.value).stream();

        // the copy to a new List is intentional as remove() operations will cause ConcurrentModificationException otherwise
        return vertexStream.filter(v -> HasContainer.testAll(v, this.hasContainers)).collect(Collectors.<Vertex>toList()).iterator();
    }

    private HasContainer getIndexKey(final Class<? extends Element> indexedClass) {
        final Set<String> indexedKeys = this.graph.getIndexedKeys(indexedClass);
        return this.hasContainers.stream()
                .filter(c -> indexedKeys.contains(c.key) && c.predicate.equals(Compare.EQUAL))
                .findFirst()
                .orElseGet(() -> null);
    }

    public String toString() {
        return this.hasContainers.isEmpty() ? super.toString() : TraversalHelper.makeStepString(this, this.hasContainers);
    }

}
