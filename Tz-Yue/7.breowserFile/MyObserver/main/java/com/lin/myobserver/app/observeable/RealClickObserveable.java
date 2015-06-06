package com.lin.myobserver.app.observeable;

import com.lin.myobserver.app.Observer.ClickObserver;

/**
 * Created by Administrator on 2015/6/2.
 * ��ʵ�ı��۲���
 */
public class RealClickObserveable extends ClickObserveable {

    /**
     * ֪ͨ�۲������ݷ����仯
     *
     * @param observer
     */
    public void notifyObserver(ClickObserver observer) {
        observer.onClick();
    }


    /**
     * ͨ�����й۲������ݷ����仯
     */
    public void notifyAllObserver() {
        for (ClickObserver observer : list) {
            observer.onClick();
        }
    }

}
