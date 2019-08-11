package com.vitaliif.busroutechalange.graph;

import com.google.code.beanmatchers.BeanMatchers;
import org.junit.Assert;
import org.junit.Test;

public class OrientedGraphTest {

    @Test
    public void testBeanStructure() {
        Assert.assertThat(OrientedGraph.class, BeanMatchers.hasValidBeanConstructor());
    }

    @Test
    public void testAddingArc() {
        final OrientedGraph graphA = new OrientedGraph();
        graphA.addArc(1, 1);
        graphA.addArc(1, 2);
        graphA.addArc(1, 3);

        final OrientedGraph graphB = new OrientedGraph();
        graphB.addArc(1, 1);
        graphB.addArc(1, 2);
        graphB.addArc(1, 3);

        Assert.assertEquals(1, graphA.getGraph().size());
        Assert.assertEquals(3, graphA.getGraph().get(1).size());

        Assert.assertEquals(graphA, graphB);
        Assert.assertEquals(graphA.hashCode(), graphB.hashCode());

    }
}
