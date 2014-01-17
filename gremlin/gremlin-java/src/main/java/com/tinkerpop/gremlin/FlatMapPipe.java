package com.tinkerpop.gremlin;

import com.tinkerpop.gremlin.pipes.util.GremlinHelper;
import com.tinkerpop.gremlin.pipes.util.HolderIterator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Function;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class FlatMapPipe<S, E> extends AbstractPipe<S, E> {

    protected Function<Holder<S>, Iterator<E>> function;
    private final Queue<Iterator<Holder<E>>> queue = new LinkedList<>();

    public FlatMapPipe(final Pipeline pipeline, Function<Holder<S>, Iterator<E>> function) {
        super(pipeline);
        this.function = function;
    }

    public Holder<E> processNextStart() {
        while (true) {
            final Holder<E> holder = this.getNext();
            if (null != holder) {
                holder.setFuture(GremlinHelper.getNextPipeLabel(this.pipeline, this));
                return holder;
            }
        }
    }

    private synchronized Holder<E> getNext() {
        if (this.queue.isEmpty()) {
            final Holder<S> holder = this.starts.next();
            this.queue.add(new HolderIterator<>((Optional) Optional.of(holder), this, this.function.apply(holder)));
            return null;
        } else {
            final Iterator<Holder<E>> iterator = this.queue.peek();
            if (iterator.hasNext()) {
                return iterator.next();
            } else {
                this.queue.remove();
                return null;
            }
        }
    }
}