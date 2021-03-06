package com.tinkerpop.gremlin.process.graph.step.sideEffect;

import com.tinkerpop.gremlin.process.Traversal;
import com.tinkerpop.gremlin.process.computer.MapReduce;
import com.tinkerpop.gremlin.process.graph.marker.Bulkable;
import com.tinkerpop.gremlin.process.graph.marker.MapReducer;
import com.tinkerpop.gremlin.process.graph.marker.SideEffectCapable;
import com.tinkerpop.gremlin.process.graph.marker.VertexCentric;
import com.tinkerpop.gremlin.process.graph.step.sideEffect.mapreduce.CountCapMapReduce;
import com.tinkerpop.gremlin.structure.Graph;
import com.tinkerpop.gremlin.structure.Vertex;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class CountCapStep<S> extends SideEffectStep<S> implements SideEffectCapable, Bulkable, VertexCentric, MapReducer<MapReduce.NullObject, Long, MapReduce.NullObject, Long, Long> {

    private static final String COUNT_KEY = Graph.Key.hide("count");
    private long bulkCount = 1l;
    private AtomicLong count = new AtomicLong(0l);
    private boolean vertexCentric = false;

    public CountCapStep(final Traversal traversal) {
        super(traversal);
        this.setConsumer(traverser -> {
            this.count.set(this.count.get() + this.bulkCount);
            if (!this.vertexCentric) this.traversal.sideEffects().set(COUNT_KEY, this.count.get());
        });

    }

    public void setCurrentBulkCount(final long bulkCount) {
        this.bulkCount = bulkCount;
    }

    public String getSideEffectKey() {
        return COUNT_KEY;
    }

    public void setCurrentVertex(final Vertex vertex) {
        this.vertexCentric = true;
        this.count = vertex.<AtomicLong>property(COUNT_KEY).orElse(new AtomicLong(0));
        if (!vertex.property(COUNT_KEY).isPresent())
            vertex.property(COUNT_KEY, this.count);
    }

    public MapReduce<MapReduce.NullObject, Long, MapReduce.NullObject, Long, Long> getMapReduce() {
        return new CountCapMapReduce(this);
    }

}
