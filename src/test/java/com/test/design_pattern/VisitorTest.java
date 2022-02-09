package com.test.design_pattern;

import org.junit.Test;

import java.util.Random;

public class VisitorTest {
    @Test
    public void visitorTest() {
        Visitor visitor = new Visitor();
        for (int i = 0; i < 100; i++) {
            Element element = makeList();
            //接受访问者访问
            element.accept(visitor);
        }
        System.out.println(visitor.numberC1);
    }

    private Element makeList() {
        Random random = new Random();
        if (random.nextInt(100) > 50) {
            return new ConcreteElement();
        } else {
            return new ConcreteElement2();
        }
    }
}

/**
 * 这个可以是List<T></>
 */
abstract class Element {
    public abstract void doSomething();

    //定义谁可以访问
    public abstract void accept(IVisitor visitor);
}

class ConcreteElement extends Element {

    @Override
    public void doSomething() {

    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

class ConcreteElement2 extends Element {

    @Override
    public void doSomething() {

    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }
}

interface IVisitor {
    //可以访问那些对象
    void visit(ConcreteElement concreteElement);

    void visit(ConcreteElement2 concreteElement2);
}

/**
 * 这个具体的访问者,
 */
class Visitor implements IVisitor {
    public int numberC1;

    //访问 concreteElement
    @Override
    public void visit(ConcreteElement concreteElement) {
        concreteElement.doSomething();
        numberC1++;
    }

    @Override
    public void visit(ConcreteElement2 concreteElement2) {

    }
}
