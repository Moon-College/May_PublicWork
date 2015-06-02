package com.lin.myobserver.app.observeable;

import com.lin.myobserver.app.Observer.ClickObserver;

/**
 * Created by Administrator on 2015/6/2.
 * 真实的被观察者
 */
public class RealClickObserveable extends ClickObserveable {

    /**
     * 通知观察者数据发生变化
     *
     * @param observer
     */
    public void notifyObserver(ClickObserver observer) {
        observer.onClick();
    }


    /**
     * 通过所有观察者数据发生变化
     */
    public void notifyAllObserver() {
        for (ClickObserver observer : list) {
            observer.onClick();
        }
    }

}
