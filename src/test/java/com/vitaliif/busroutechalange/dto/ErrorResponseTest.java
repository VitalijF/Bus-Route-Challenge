package com.vitaliif.busroutechalange.dto;

import com.google.code.beanmatchers.BeanMatchers;
import org.junit.Assert;
import org.junit.Test;

public class ErrorResponseTest {

    @Test
    public void testBeanStructure() {
        Assert.assertThat(ErrorResponse.class, BeanMatchers.hasValidBeanConstructor());
        Assert.assertThat(ErrorResponse.class, BeanMatchers.hasValidGettersAndSetters());
        Assert.assertThat(ErrorResponse.class, BeanMatchers.hasValidBeanEquals());
        Assert.assertThat(ErrorResponse.class, BeanMatchers.hasValidBeanHashCode());
        Assert.assertThat(ErrorResponse.class, BeanMatchers.hasValidBeanToString());
    }
}
