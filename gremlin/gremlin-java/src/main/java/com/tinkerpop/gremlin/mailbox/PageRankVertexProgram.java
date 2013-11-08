package com.tinkerpop.gremlin.mailbox;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Property;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.mailbox.GraphMemory;
import com.tinkerpop.blueprints.mailbox.Mailbox;
import com.tinkerpop.blueprints.mailbox.VertexProgram;
import com.tinkerpop.blueprints.query.util.VertexQueryBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class PageRankVertexProgram implements VertexProgram<Double> {

    protected final Map<String, KeyType> computeKeys = new HashMap<String, KeyType>();
    private VertexQueryBuilder adjacentQuery = new VertexQueryBuilder().direction(Direction.OUT);
    private VertexQueryBuilder oppositeQuery = new VertexQueryBuilder().direction(Direction.IN);

    public static final String PAGE_RANK = PageRankVertexProgram.class.getName() + ".pageRank";
    public static final String EDGE_COUNT = PageRankVertexProgram.class.getName() + ".edgeCount";

    private double vertexCountAsDouble = 1;
    private double alpha = 0.85d;
    private int totalIterations = 30;

    protected PageRankVertexProgram() {
        computeKeys.put(PAGE_RANK, KeyType.VARIABLE);
        computeKeys.put(EDGE_COUNT, KeyType.CONSTANT);
        computeKeys.put(Property.Key.hidden("mailbox"), KeyType.VARIABLE);
        this.oppositeQuery = this.adjacentQuery.build().reverse();
    }


    public Map<String, KeyType> getComputeKeys() {
        return computeKeys;
    }

    public void setup(final GraphMemory graphMemory) {

    }

    public void execute(final Vertex vertex, Mailbox<Double> mailbox, final GraphMemory graphMemory) {
        if (graphMemory.isInitialIteration()) {
            double initialPageRank = 1.0d / this.vertexCountAsDouble;
            double edgeCount = Long.valueOf(adjacentQuery.build(vertex).count()).doubleValue();
            vertex.setProperty(PAGE_RANK, initialPageRank);
            vertex.setProperty(EDGE_COUNT, edgeCount);
            mailbox.sendMessage(vertex, adjacentQuery, initialPageRank / edgeCount);
        } else {
            double newPageRank = 0.0d;
            for (final Double pageRank : mailbox.getMessages(vertex, this.oppositeQuery)) {
                newPageRank += pageRank;
            }
            newPageRank = (this.alpha * newPageRank) + ((1.0d - this.alpha) / this.vertexCountAsDouble);
            vertex.setProperty(PAGE_RANK, newPageRank);
            mailbox.sendMessage(vertex, adjacentQuery, newPageRank / (Double) vertex.getValue(EDGE_COUNT));
        }
    }

    public boolean terminate(final GraphMemory graphMemory) {
        return graphMemory.getIteration() >= this.totalIterations;
    }

    public static Builder create() {
        return new Builder();
    }

    //////////////////////////////

    public static class Builder {

        private final PageRankVertexProgram vertexProgram = new PageRankVertexProgram();

        public Builder iterations(final int iterations) {
            this.vertexProgram.totalIterations = iterations;
            return this;
        }

        public Builder alpha(final double alpha) {
            this.vertexProgram.alpha = alpha;
            return this;
        }

        public Builder adjacent(final VertexQueryBuilder adjacentQuery) {
            this.vertexProgram.adjacentQuery = adjacentQuery;
            return this;
        }

        public Builder vertexCount(final int count) {
            this.vertexProgram.vertexCountAsDouble = (double) count;
            return this;
        }

        public PageRankVertexProgram build() {
            return this.vertexProgram;
        }
    }

}