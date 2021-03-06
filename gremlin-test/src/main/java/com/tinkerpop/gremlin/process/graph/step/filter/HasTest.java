package com.tinkerpop.gremlin.process.graph.step.filter;

import com.tinkerpop.gremlin.AbstractGremlinSuite;
import com.tinkerpop.gremlin.FeatureRequirement;
import com.tinkerpop.gremlin.FeatureRequirementSet;
import com.tinkerpop.gremlin.LoadGraphWith;
import com.tinkerpop.gremlin.process.AbstractGremlinProcessTest;
import com.tinkerpop.gremlin.process.T;
import com.tinkerpop.gremlin.process.Traversal;
import com.tinkerpop.gremlin.structure.Edge;
import com.tinkerpop.gremlin.structure.Element;
import com.tinkerpop.gremlin.structure.Graph;
import com.tinkerpop.gremlin.structure.Vertex;
import com.tinkerpop.gremlin.util.StreamFactory;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.tinkerpop.gremlin.LoadGraphWith.GraphData.CLASSIC_DOUBLE;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 * @author Stephen Mallette (http://stephen.genoprime.com)
 */
public abstract class HasTest extends AbstractGremlinProcessTest {

    public abstract Traversal<Vertex, Vertex> get_g_v1_hasXkeyX(final Object v1Id, final String key);

    public abstract Traversal<Vertex, Vertex> get_g_v1_hasXname_markoX(final Object v1Id);

    public abstract Traversal<Vertex, Vertex> get_g_V_hasXname_markoX();

    public abstract Traversal<Vertex, Vertex> get_g_V_hasXname_blahX();

    public abstract Traversal<Vertex, Vertex> get_g_V_hasXblahX();

    public abstract Traversal<Vertex, Vertex> get_g_v1_hasXage_gt_30X(final Object v1Id);

    public abstract Traversal<Vertex, Vertex> get_g_v1_out_hasXid_2X(final Object v1Id, final Object v2Id);

    public abstract Traversal<Vertex, Vertex> get_g_V_hasXage_gt_30X();

    public abstract Traversal<Edge, Edge> get_g_e7_hasXlabelXknowsX(final Object e7Id);

    public abstract Traversal<Edge, Edge> get_g_E_hasXlabelXknowsX();

    public abstract Traversal<Edge, Edge> get_g_E_hasXlabelXknows_createdX();

    public abstract Traversal<Vertex, Vertex> get_g_V_hasXlabelXperson_animalX();

    public abstract Traversal<Vertex, Vertex> get_g_V_hasXname_equalspredicate_markoX();

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    public void g_v1_hasXkeyX() {
        Traversal<Vertex, Vertex> traversal = get_g_v1_hasXkeyX(convertToVertexId("marko"), "name");
        printTraversalForm(traversal);
        assertEquals("marko", traversal.next().<String>value("name"));
        assertFalse(traversal.hasNext());
        traversal = get_g_v1_hasXkeyX(convertToVertexId("marko"), "circumference");
        System.out.println("Testing: " + traversal);
        assertFalse(traversal.hasNext());
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    public void g_v1_hasXname_markoX() {
        Traversal<Vertex, Vertex> traversal = get_g_v1_hasXname_markoX(convertToVertexId("marko"));
        printTraversalForm(traversal);
        assertEquals("marko", traversal.next().<String>value("name"));
        assertFalse(traversal.hasNext());
        traversal = get_g_v1_hasXname_markoX(convertToVertexId("vadas"));
        System.out.println("Testing: " + traversal);
        assertFalse(traversal.hasNext());
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    public void g_V_hasXname_markoX() {
        final Traversal<Vertex, Vertex> traversal = get_g_V_hasXname_markoX();
        printTraversalForm(traversal);
        assertEquals("marko", traversal.next().<String>value("name"));
        assertFalse(traversal.hasNext());
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    public void g_V_hasXname_blahX() {
        final Traversal<Vertex, Vertex> traversal = get_g_V_hasXname_blahX();
        printTraversalForm(traversal);
        assertFalse(traversal.hasNext());
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    public void g_V_hasXage_gt_30X() {
        final Traversal<Vertex, Vertex> traversal = get_g_V_hasXage_gt_30X();
        printTraversalForm(traversal);
        final List<Element> list = StreamFactory.stream(traversal).collect(Collectors.toList());
        assertEquals(2, list.size());
        for (final Element v : list) {
            assertTrue(v.<Integer>value("age") > 30);
        }
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    public void g_v1_hasXage_gt_30X() {
        Traversal<Vertex, Vertex> traversal = get_g_v1_hasXage_gt_30X(convertToVertexId("marko"));
        printTraversalForm(traversal);
        assertFalse(traversal.hasNext());
        traversal = get_g_v1_hasXage_gt_30X(convertToVertexId("josh"));
        printTraversalForm(traversal);
        assertTrue(traversal.hasNext());
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    public void g_v1_out_hasXid_2X() {
        final Traversal<Vertex, Vertex> traversal = get_g_v1_out_hasXid_2X(convertToVertexId("marko"), convertToVertexId("vadas"));
        printTraversalForm(traversal);
        assertTrue(traversal.hasNext());
        assertEquals(convertToVertexId("vadas"), traversal.next().id());
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    public void g_V_hasXblahX() {
        assumeTrue(graphMeetsTestRequirements());
        final Traversal<Vertex, Vertex> traversal = get_g_V_hasXblahX();
        printTraversalForm(traversal);
        assertFalse(traversal.hasNext());
    }


    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    public void g_e7_hasXlabelXknowsX() {
        //System.out.println(convertToEdgeId("marko", "knows", "vadas"));
        Traversal<Edge, Edge> traversal = get_g_e7_hasXlabelXknowsX(convertToEdgeId("marko", "knows", "vadas"));
        printTraversalForm(traversal);
        int counter = 0;
        while (traversal.hasNext()) {
            counter++;
            assertEquals("knows", traversal.next().label());
        }
        assertEquals(1, counter);
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    public void g_E_hasXlabelXknowsX() {
        final Traversal<Edge, Edge> traversal = get_g_E_hasXlabelXknowsX();
        printTraversalForm(traversal);
        int counter = 0;
        while (traversal.hasNext()) {
            counter++;
            assertEquals("knows", traversal.next().label());
        }
        assertEquals(2, counter);
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    @FeatureRequirement(featureClass = Graph.Features.EdgeFeatures.class, feature = Graph.Features.EdgeFeatures.FEATURE_ADD_EDGES)
    public void g_E_hasXlabelXknows_createdX() {
        final Vertex marko = (Vertex) g.V().has("name", "marko").next();
        marko.addEdge("self", marko);
        tryCommit(g);

        AbstractGremlinSuite.assertVertexEdgeCounts(6, 7);

        final Traversal<Edge, Edge> traversal = get_g_E_hasXlabelXknows_createdX();
        printTraversalForm(traversal);
        int counter = 0;
        while (traversal.hasNext()) {
            counter++;
            final String label = traversal.next().label();
            assertTrue(label.equals("knows") || label.equals("created"));
        }
        assertEquals(6, counter);
    }

    @Test
    @FeatureRequirementSet(FeatureRequirementSet.Package.VERTICES_ONLY)
    public void g_V_hasXlabelXperson_animalX() {
        this.g.addVertex(Element.LABEL, "person", "name", "a");
        this.g.addVertex(Element.LABEL, "animal", "name", "b");
        this.g.addVertex(Element.LABEL, "alien", "name", "c");
        this.g.addVertex(Element.LABEL, "spirit", "name", "d");
        tryCommit(g);

        final Traversal<Vertex, Vertex> traversal = get_g_V_hasXlabelXperson_animalX();
        printTraversalForm(traversal);
        int counter = 0;
        while (traversal.hasNext()) {
            counter++;
            final String label = traversal.next().label();
            assertTrue(label.equals("animal") || label.equals("person"));
        }
        assertEquals(2, counter);
    }

    @Test
    @LoadGraphWith(CLASSIC_DOUBLE)
    public void g_V_hasXname_equalspredicate_markoX() {
        final Traversal<Vertex, Vertex> traversal = get_g_V_hasXname_equalspredicate_markoX();
        printTraversalForm(traversal);
        assertEquals("marko", traversal.next().<String>value("name"));
        assertFalse(traversal.hasNext());
    }

    public static class JavaHasTest extends HasTest {
        public JavaHasTest() {
            requiresGraphComputer = false;
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_v1_hasXkeyX(final Object v1Id, final String key) {
            return g.v(v1Id).has(key);
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_v1_hasXname_markoX(final Object v1Id) {
            return g.v(v1Id).has("name", "marko");
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXname_markoX() {
            return g.V().has("name", "marko");
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXname_blahX() {
            return g.V().has("name", "blah");
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXblahX() {
            return g.V().has("blah");
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_v1_hasXage_gt_30X(final Object v1Id) {
            return g.v(v1Id).has("age", T.gt, 30);
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_v1_out_hasXid_2X(final Object v1Id, final Object v2Id) {
            return g.v(v1Id).out().has(Element.ID, v2Id);
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXage_gt_30X() {
            return g.V().has("age", T.gt, 30);
        }

        @Override
        public Traversal<Edge, Edge> get_g_e7_hasXlabelXknowsX(final Object e7Id) {
            return g.e(e7Id).has(Element.LABEL, "knows");
        }

        @Override
        public Traversal<Edge, Edge> get_g_E_hasXlabelXknowsX() {
            return g.E().has(Element.LABEL, "knows");
        }

        @Override
        public Traversal<Edge, Edge> get_g_E_hasXlabelXknows_createdX() {
            return g.E().has(Element.LABEL, T.in, Arrays.asList("knows", "created"));
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXlabelXperson_animalX() {
            return g.V().has(Element.LABEL, T.in, Arrays.asList("person", "animal"));
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXname_equalspredicate_markoX() {
            return g.V().has("name", (v1, v2) -> v1.equals(v2), "marko");
        }
    }

    public static class JavaComputerHasTest extends HasTest {
        public JavaComputerHasTest() {
            requiresGraphComputer = true;
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_v1_hasXkeyX(final Object v1Id, final String key) {
            return g.v(v1Id).<Vertex>has(key).submit(g.compute());
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_v1_hasXname_markoX(final Object v1Id) {
            return g.v(v1Id).<Vertex>has("name", "marko").submit(g.compute());
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXname_markoX() {
            return g.V().<Vertex>has("name", "marko").submit(g.compute());
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXname_blahX() {
            return g.V().<Vertex>has("name", "blah").submit(g.compute());
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXblahX() {
            return g.V().<Vertex>has("blah").submit(g.compute());
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_v1_hasXage_gt_30X(final Object v1Id) {
            return g.v(v1Id).<Vertex>has("age", T.gt, 30).submit(g.compute());
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_v1_out_hasXid_2X(final Object v1Id, final Object v2Id) {
            return g.v(v1Id).out().<Vertex>has(Element.ID, v2Id).submit(g.compute());
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXage_gt_30X() {
            return g.V().<Vertex>has("age", T.gt, 30).submit(g.compute());
        }

        @Override
        public Traversal<Edge, Edge> get_g_e7_hasXlabelXknowsX(final Object e7Id) {
            return g.e(e7Id).<Edge>has(Element.LABEL, "knows").submit(g.compute());
        }

        @Override
        public Traversal<Edge, Edge> get_g_E_hasXlabelXknowsX() {
            return g.E().<Edge>has(Element.LABEL, "knows").submit(g.compute());
        }

        @Override
        public Traversal<Edge, Edge> get_g_E_hasXlabelXknows_createdX() {
            return g.E().<Edge>has(Element.LABEL, T.in, Arrays.asList("knows", "created")).submit(g.compute());
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXlabelXperson_animalX() {
            return g.V().has(Element.LABEL, T.in, Arrays.asList("person", "animal"));
        }

        @Override
        public Traversal<Vertex, Vertex> get_g_V_hasXname_equalspredicate_markoX() {
            return g.V().<Vertex>has("name", (v1, v2) -> v1.equals(v2), "marko").submit(g.compute());
        }
    }
}