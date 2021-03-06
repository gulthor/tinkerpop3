package com.tinkerpop.gremlin.process.graph.step.map

import com.tinkerpop.gremlin.process.T
import com.tinkerpop.gremlin.process.Traversal
import com.tinkerpop.gremlin.structure.Direction
import com.tinkerpop.gremlin.structure.Edge
import com.tinkerpop.gremlin.structure.Vertex

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
class GroovyVertexTestImpl extends VertexTest {

    @Override
    public Traversal<Vertex, Vertex> get_g_V() {
        g.V()
    }

    @Override
    public Traversal<Vertex, Vertex> get_g_v1_out(final Object v1Id) {
        g.v(v1Id).out()
    }

    @Override
    public Traversal<Vertex, Vertex> get_g_v2_in(final Object v2Id) {
        g.v(v2Id).in()
    }

    @Override
    public Traversal<Vertex, Vertex> get_g_v4_both(final Object v4Id) {
        g.v(v4Id).both()
    }

    @Override
    public Traversal<Vertex, String> get_g_v1_outX1_knowsX_name(final Object v1Id) {
        g.v(v1Id).out(1, 'knows').value('name')
    }

    @Override
    public Traversal<Vertex, String> get_g_V_bothX1_createdX_name() {
        g.V().both(1, 'created').value('name')
    }

    @Override
    public Traversal<Edge, Edge> get_g_E() {
        g.E()
    }

    @Override
    public Traversal<Vertex, Edge> get_g_v1_outE(final Object v1Id) {
        g.v(v1Id).outE()
    }

    @Override
    public Traversal<Vertex, Edge> get_g_v2_inE(final Object v2Id) {
        g.v(v2Id).inE()
    }

    @Override
    public Traversal<Vertex, Edge> get_g_v4_bothE(final Object v4Id) {
        g.v(v4Id).bothE()
    }

    @Override
    public Traversal<Vertex, Edge> get_g_v4_bothEXcreatedX(final Object v4Id) {
        g.v(v4Id).bothE('created')
    }

    @Override
    public Traversal<Vertex, Edge> get_g_v4_bothEX1_createdX(final Object v4Id) {
        g.v(v4Id).bothE(1, 'created')
    }

    @Override
    public Traversal<Vertex, Edge> get_g_v4_bothEX1_knows_createdX(final Object v4Id) {
        g.v(v4Id).bothE(1, 'knows', 'created')
    }

    @Override
    public Traversal<Vertex, String> get_g_v4_bothX1X_name(final Object v4Id) {
        g.v(v4Id).both(1).value('name')
    }

    @Override
    public Traversal<Vertex, String> get_g_v4_bothX2X_name(final Object v4Id) {
        g.v(v4Id).both(2).value('name')
    }

    @Override
    public Traversal<Vertex, String> get_g_V_inEX2_knowsX_outV_name() {
        g.V().inE(2, 'knows').outV().value('name')
    }

    @Override
    public Traversal<Vertex, Vertex> get_g_v1_outE_inV(final Object v1Id) {
        g.v(v1Id).outE().inV()
    }

    @Override
    public Traversal<Vertex, Vertex> get_g_v2_inE_outV(final Object v2Id) {
        g.v(v2Id).inE().outV()
    }

    @Override
    public Traversal<Vertex, Vertex> get_g_V_outE_hasXweight_1X_outV() {
        g.V().outE().has('weight', 1.0d).outV()
    }

    @Override
    public Traversal<Vertex, String> get_g_V_out_outE_inV_inE_inV_both_name() {
        g.V().out().outE().inV().inE().inV().both().value('name')
    }

    @Override
    public Traversal<Vertex, String> get_g_v1_outEXknowsX_bothV_name(final Object v1Id) {
        g.v(v1Id).outE('knows').bothV().value('name')
    }

    @Override
    public Traversal<Vertex, Vertex> get_g_v1_outXknowsX(final Object v1Id) {
        g.v(v1Id).out('knows')
    }

    @Override
    public Traversal<Vertex, Vertex> get_g_v1_outXknows_createdX(final Object v1Id) {
        g.v(v1Id).out('knows', 'created')
    }

    @Override
    public Traversal<Vertex, Vertex> get_g_v1_outEXknowsX_inV(final Object v1Id) {
        g.v(v1Id).outE('knows').inV()
    }

    @Override
    public Traversal<Vertex, Vertex> get_g_v1_outEXknows_createdX_inV(final Object v1Id) {
        g.v(v1Id).outE('knows', 'created').inV()
    }

    @Override
    public Traversal<Vertex, Vertex> get_g_V_out_out() {
        g.V().out().out()
    }

    @Override
    public Traversal<Vertex, Vertex> get_g_v1_out_out_out(final Object v1Id) {
        g.v(v1Id).out().out().out()
    }

    @Override
    public Traversal<Vertex, String> get_g_v1_out_valueXnameX(final Object v1Id) {
        g.v(v1Id).out().value('name')
    }

    @Override
    public Traversal<Vertex, Vertex> get_g_v1_outE_otherV(final Object v1Id) {
        g.v(v1Id).outE().otherV()
    }

    @Override
    public Traversal<Vertex, Vertex> get_g_v4_bothE_otherV(final Object v4Id) {
        g.v(v4Id).bothE().otherV()
    }

    @Override
    public Traversal<Vertex, Vertex> get_g_v4_bothE_hasXweight_lt_1X_otherV(final Object v4Id) {
        g.v(v4Id).bothE().has('weight', T.lt, 1.0d).otherV()
    }

    @Override
    public Traversal<Vertex, Vertex> get_g_v1_to_XOUT_knowsX(final Object v1Id) {
        g.v(v1Id).to(Direction.OUT, 'knows');
    }
}
