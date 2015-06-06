package com.lin.myobserver.app.observeable;

import com.lin.myobserver.app.Observer.ClickObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/2.
 * 抽象的被观察者
 */
public  abstract class ClickObserveable {
    List<ClickObserver> list = new ArrayList<ClickObserver>();

    public void registeObserver(ClickObserver observer) {
        this.list.add(observer);//注册观察者
    }


    public void unregisteObserver(ClickObserver observer) {
        if (this.list.contains(observer)) {
            this.list.remove(observer);//取消注册
        }
    }


    /**
     * 取消所有观察者
     */
    public void unregisteAll() {
        this.list.clear();
    }


}
