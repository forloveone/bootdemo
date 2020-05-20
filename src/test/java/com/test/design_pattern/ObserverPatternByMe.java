package com.test.design_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 手工实现的观察者模式
 * 观察者 B
 * 被观察者 A
 * A的状态改变只有A知道,所以需要A去通知B
 */
public class ObserverPatternByMe {
    public static void main(String[] args) {
        ObservableTest a = new ObservableTest(new ObserverTest());
        a.setState(1100);
        System.out.println("--------------------");
        ObservableTest2 a2 = new ObservableTest2();
        ObserverTest2 b2 = new ObserverTest2(a2);
        a2.setState(1);
    }
}

//-----------------------------------Level_1---------------------------------------------
// level_1 有问题,被观察者的业务太重而且一对一锁定了,持有了所有观察者对象, 被观察者对象总是在状态发生变化后通知观察者,所以可以把这两个动作抽取到父类中

/**
 * 观察者 B
 */
class ObserverTest {
    /**
     * 观察者应该看到 被观察者状态变化,才能继续他的业务.
     */
    public void seeStatusChang(ObservableTest test) {
        System.out.println("观察者看到被观察者状态变化");
    }
}

/**
 * 被观察者 A
 */
class ObservableTest {
    /*
        被观察者持有 观察者列表
     */
    private List<ObserverTest> list = new ArrayList<ObserverTest>();

    /**
     * 被观察者初始化的时候,需要把被观察者也初始化了
     */
    public ObservableTest(ObserverTest observerTest) {
        list.add(observerTest);
    }

    private int state;

    public int getState() {
        return state;
    }

    /**
     * set 应该触发 观察者的方法
     *
     * @param state
     */
    public void setState(int state) {
        this.state = state;
        //状态改变,通知所有观察者
        for (ObserverTest test : list) {
            test.seeStatusChang(this);
        }
    }
}
// -----------------------------------Level_1---------------------------------------------

//Level_2 也就是javaAPI的实现,我看完源码,把主要的模仿写了一遍 ,但是我觉得状态发生改变,并且通知所有观察者,这两个方法应该,封装到一个方法,这样更容易使用.
/**
 * 和javaApi的实现思路一样,就是方法没有完善,没有做同步
 */
class ObservableClass {
//    private boolean statusChangeFlag = false;

    private List<ObserverClass> list = new ArrayList<>();

    public void notifyAllObserver(Object args) {
        for (ObserverClass observerClass : list) {
            observerClass.seeStatusChang(this, args);
        }
    }

    //添加监听者
    public void addObserver(ObserverClass o) {
        if (!list.contains(o)) {
            list.add(o);
        }
    }

}

interface ObserverClass {
    void seeStatusChang(ObservableClass o, Object args);
}

class ObservableTest2 extends ObservableClass {
    private int state;

    public int getState() {
        return state;
    }

    /**
     * set 应该触发 观察者的方法
     *
     * @param state
     */
    public void setState(int state) {
        this.state = state;
        //状态改变,通知所有观察者
        notifyAllObserver("temp");
    }
}

class ObserverTest2 implements ObserverClass {

    public ObserverTest2(ObservableClass observableClass) {
        observableClass.addObserver(this);
    }

    @Override
    public void seeStatusChang(ObservableClass o, Object args) {
        System.out.println("观察者看到被观察者状态变化");
    }
}