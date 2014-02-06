package com.tinkerpop.gremlin.process.olap.gremlin.structures;

import com.tinkerpop.gremlin.process.Memory;
import com.tinkerpop.gremlin.process.olap.GraphMemory;

import java.util.Set;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class DistributedMemory implements Memory {

    final GraphMemory graphMemory;

    public DistributedMemory(final GraphMemory graphMemory) {
        this.graphMemory = graphMemory;
    }

    public <T> void set(final String variable, final T t) {
        this.graphMemory.setIfAbsent(variable, t);
    }

    public <T> T get(final String variable) {
        return (T) this.graphMemory.get(variable);
    }

    public Set<String> getVariables() {
        return this.graphMemory.getVariables();
    }
}