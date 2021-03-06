package com.tinkerpop.gremlin.process.util;

import com.tinkerpop.gremlin.process.PathTraverser;
import com.tinkerpop.gremlin.process.Step;
import com.tinkerpop.gremlin.process.Traversal;
import com.tinkerpop.gremlin.process.Traverser;
import com.tinkerpop.gremlin.structure.Graph;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public abstract class AbstractStep<S, E> implements Step<S, E> {

    protected String as;
    protected Traversal traversal;
    public ExpandableStepIterator<S> starts;
    protected Traverser<E> nextEnd;
    protected boolean available;
    protected boolean futureSetByChild = false;

    protected Step<?, S> previousStep = EmptyStep.instance();
    protected Step<E, ?> nextStep = EmptyStep.instance();

    public AbstractStep(final Traversal traversal) {
        this.traversal = traversal;
        this.starts = new ExpandableStepIterator<S>((Step) this);
        this.as = Graph.Key.hide(Integer.toString(this.traversal.getSteps().size()));
    }

    public void addStarts(final Iterator<Traverser<S>> starts) {
        this.starts.add((Iterator) starts);
    }

    public void setPreviousStep(final Step<?, S> step) {
        this.previousStep = step;
    }

    public Step<?, S> getPreviousStep() {
        return this.previousStep;
    }

    public void setNextStep(final Step<E, ?> step) {
        this.nextStep = step;
    }

    public Step<E, ?> getNextStep() {
        return this.nextStep;
    }

    public void setAs(final String as) {
        this.as = as;
    }

    public String getAs() {
        return this.as;
    }

    public Traverser<E> next() {
        if (this.available) {
            this.available = false;
            prepareTraversalForNextStep(this.nextEnd);
            return this.nextEnd;
        } else {
            final Traverser<E> traverser = this.processNextStart();
            prepareTraversalForNextStep(traverser);
            return traverser;
        }
    }

    public boolean hasNext() {
        if (this.available)
            return true;
        else {
            try {
                this.nextEnd = this.processNextStart();
                this.available = true;
                return true;
            } catch (final NoSuchElementException e) {
                this.available = false;
                return false;
            }
        }
    }

    public <A, B> Traversal<A, B> getTraversal() {
        return this.traversal;
    }

    protected abstract Traverser<E> processNextStart() throws NoSuchElementException;

    public String toString() {
        return TraversalHelper.makeStepString(this);
    }

    public Object clone() throws CloneNotSupportedException {
        final AbstractStep step = (AbstractStep) super.clone();
        step.starts = new ExpandableStepIterator<S>(step);
        step.previousStep = EmptyStep.instance();
        step.nextStep = EmptyStep.instance();
        step.available = false;
        step.nextEnd = null;
        return step;
    }

    private void prepareTraversalForNextStep(final Traverser<E> traverser) {
        if (!this.futureSetByChild)
            traverser.setFuture(this.nextStep.getAs());
        if (traverser instanceof PathTraverser) traverser.getPath().addAs(this.getAs());
    }
}
