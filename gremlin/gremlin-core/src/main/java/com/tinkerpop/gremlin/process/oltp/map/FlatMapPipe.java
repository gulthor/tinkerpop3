package com.tinkerpop.gremlin.process.oltp.map;

import com.tinkerpop.gremlin.process.Traversal;
import com.tinkerpop.gremlin.process.Holder;
import com.tinkerpop.gremlin.process.Pipe;
import com.tinkerpop.gremlin.process.oltp.AbstractPipe;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class FlatMapPipe<S, E> extends AbstractPipe<S, E> {

    protected Function<Holder<S>, Iterator<E>> function;
    protected final Queue<Iterator<Holder<E>>> queue = new LinkedList<>();

    public FlatMapPipe(final Traversal pipeline, Function<Holder<S>, Iterator<E>> function) {
        super(pipeline);
        this.function = function;
    }

    public FlatMapPipe(final Traversal pipeline) {
        super(pipeline);
    }

    public void setFunction(final Function<Holder<S>, Iterator<E>> function) {
        this.function = function;
    }

    protected Holder<E> processNextStart() {
        while (true) {
            final Holder<E> holder = this.getNext();
            if (null != holder) return holder;
        }
    }

    protected Holder<E> getNext() {
        if (this.queue.isEmpty()) {
            final Holder<S> holder = this.starts.next();
            this.queue.add(new FlatMapHolderIterator<>(holder, this, this.function.apply(holder)));
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

    private class FlatMapHolderIterator<A, B> implements Iterator<Holder<B>> {

        private final Holder<A> head;
        private final Iterator<B> iterator;
        private final Pipe pipe;

        protected FlatMapHolderIterator(final Holder<A> head, final Pipe pipe, final Iterator<B> iterator) {
            this.iterator = iterator;
            this.head = head;
            this.pipe = pipe;
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public Holder<B> next() {
            return this.head.makeChild(this.pipe.getAs(), this.iterator.next());
        }
    }

}