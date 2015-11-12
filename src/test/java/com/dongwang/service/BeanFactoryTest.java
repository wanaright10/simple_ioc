package com.dongwang.service;

import com.dongwang.model.ClassA;
import com.dongwang.model.ClassB;
import com.dongwang.model.ClassD;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Dong Wang.
 * Created on 15/11/12 13:43
 */
public class BeanFactoryTest {

    @Test
    public void testGetBean() throws Exception {
        ClassA classA = BeanFactory.getBean("a", ClassA.class);
        Assert.assertNotNull(classA);
        Assert.assertNotNull(classA.getB());
        Assert.assertNotNull(classA.getB().getC());
        Assert.assertEquals("value_in_classD", classA.getB().getC().getD().getValue());

        ClassA classA_null = BeanFactory.getBean(ClassA.class);
        Assert.assertNull(classA_null);

        ClassA classA_same = BeanFactory.getBean("a", ClassA.class);
        Assert.assertSame(classA, classA_same);
    }

    @Test
    public void testGetBean1() throws Exception {
        ClassB classB = BeanFactory.getBean(ClassB.class);
        Assert.assertNotNull(classB);
        Assert.assertNotNull(classB.getC());
        Assert.assertNotNull(classB.getC().getD());
        Assert.assertEquals("value_in_classD", classB.getC().getD().getValue());

        ClassB classB_same = BeanFactory.getBean(ClassB.class);
        Assert.assertSame(classB, classB_same);
    }

    @Test
    public void testAddBean() throws Exception {
        ClassD classD = new ClassD();
        classD.setValue("new_value");

        BeanFactory.addBean("d", classD);

        ClassD getBean = BeanFactory.getBean("d", ClassD.class);

        Assert.assertEquals("new_value", getBean.getValue());
    }
}