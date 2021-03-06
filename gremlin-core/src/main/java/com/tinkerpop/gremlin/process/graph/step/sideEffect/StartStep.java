package com.tinkerpop.gremlin.process.graph.step.sideEffect;

import com.tinkerpop.gremlin.process.PathTraverser;
import com.tinkerpop.gremlin.process.SimpleTraverser;
import com.tinkerpop.gremlin.process.Traversal;
import com.tinkerpop.gremlin.process.graph.marker.Reversible;
import com.tinkerpop.gremlin.process.graph.marker.TraverserSource;
import com.tinkerpop.gremlin.process.util.SingleIterator;
import com.tinkerpop.gremlin.process.util.TraversalHelper;
import com.tinkerpop.gremlin.process.util.TraverserIterator;

import java.util.Iterator;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class StartStep<S> extends SideEffectStep<S> implements TraverserSource, Reversible {

    public Object start;

    public StartStep(final Traversal traversal, final Object start) {
        super(traversal);
        this.start = start;
    }

    public StartStep(final Traversal traversal) {
        this(traversal, null);
    }

    public void clear() {
        this.starts.clear();
    }

    public String toString() {
        return null == this.start ? TraversalHelper.makeStepString(this) : TraversalHelper.makeStepString(this, this.start);
    }

    public void generateTraverserIterator(final boolean trackPaths) {
        if (null != this.start) {
            this.starts.clear();
            if (this.start instanceof Iterator)
                this.starts.add(new TraverserIterator(this, trackPaths, (Iterator) this.start));
            else
                this.starts.add(new SingleIterator(trackPaths ? new PathTraverser<>(this.getAs(), this.start) : new SimpleTraverser<>(this.start)));
        }
    }
}
