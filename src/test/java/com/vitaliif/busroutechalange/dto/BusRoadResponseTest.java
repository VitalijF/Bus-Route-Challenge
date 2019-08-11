package com.vitaliif.busroutechalange.dto;

import com.google.code.beanmatchers.BeanMatchers;
import org.junit.Assert;
import org.junit.Test;

public class BusRoadResponseTest {

    @Test
    public void testBeanStructure() {
        Assert.assertThat(BusRoadResponse.class, BeanMatchers.hasValidBeanConstructor());
        Assert.assertThat(BusRoadResponse.class, BeanMatchers.hasValidGettersAndSetters());
        Assert.assertThat(BusRoadResponse.class, BeanMatchers.hasValidBeanEquals());
        Assert.assertThat(BusRoadResponse.class, BeanMatchers.hasValidBeanToString());
    }
}
