package com.lin.myobserver.app.observeable;

import com.lin.myobserver.app.Observer.ClickObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/2.
 * ����ı��۲���
 */
public  abstract class ClickObserveable {
    List<ClickObserver> list = new ArrayList<ClickObserver>();

    public void registeObserver(ClickObserver observer) {
        this.list.add(observer);//ע��۲���
    }


    public void unregisteObserver(ClickObserver observer) {
        if (this.list.contains(observer)) {
            this.list.remove(observer);//ȡ��ע��
        }
    }


    /**
     * ȡ�����й۲���
     */
    public void unregisteAll() {
        this.list.clear();
    }


}
