package com.test.design_pattern;

/**
 * 状态模式
 * 一个对象内在状态改变时允许其改变行为
 */
public class StateTest {
    public static void main(String[] args) {
        Context context = new Context();
        context.setCurrentState(new ConcreteState1());
        context.handle1();
        context.handle2();
    }
}

abstract class State {
    //定义一个环境角色,提供子类访问
    protected Context context;

    public void setContext(Context context) {
        this.context = context;
    }

    //行为1
    public abstract void handle1();

    //行为2
    public abstract void handle2();
}

/**
 * 把状态对象声明为静态常量,有几个状态对象就声明几个静态常量,就是全部枚举出来
 * 环境角色具有状态抽象定义的所有行为,具体执行使用委托方式.
 */
class Context {
    //定义状态
    public final static State s1 = new ConcreteState1();
    public final static State s2 = new ConcreteState2();
    //当前状态
    private State currentState;

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
        //切换状态
        this.currentState.setContext(this);
    }

    //行为委托
    public void handle1() {
        currentState.handle1();
    }

    public void handle2() {
        currentState.handle2();
    }
}

class ConcreteState1 extends State {

    @Override
    public void handle1() {
        System.out.println("S1");
    }

    @Override
    public void handle2() {
        //设置当前状态为s2
        super.context.setCurrentState(Context.s2);
        super.context.handle1();
    }
}

class ConcreteState2 extends State {

    @Override
    public void handle1() {
        //设置当前状态为s1
        super.context.setCurrentState(Context.s1);
        super.context.handle1();
    }

    @Override
    public void handle2() {
        //本状态下必须处理的逻辑
        System.out.println("S2");
    }
}
