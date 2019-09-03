package com.test.design_pattern;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者模式
 * observable 被观察者
 * observer 观察者
 */
public class ObserverPatternByJavaAPI {
    public static void main(String[] args) {
        //被观察者初始化
        ObservableTemp temp = new ObservableTemp();
        //初始化观察者
        new ObserverTemp(temp);
        //被观察者状态变化
        temp.stateChange();
    }
}

/**
 * 被观察者
 */
class ObservableTemp extends Observable {
    private int value;

    public int getValue() {
        return value;
    }

    /*
        被观察者 属性变化
     */
    public void stateChange() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            value = i;
            setChanged(); // 值发生改变
            //notifyObservers(); // 调用所有注册的观察者
            notifyObservers("1");
        }
    }
}

/**
 * 观察者
 */
class ObserverTemp implements Observer {

    public ObserverTemp(Observable observable) {
        super();
        observable.addObserver(this);//注册加入观察者
    }

    /**
     *  观察者必须实现的方法
     * @param o the observable object
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("观察者收到被观察者状态变化"+ o +"   "+ arg);
    }
}