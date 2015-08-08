package com.decent.aidlserver.service;
import com.decent.aidlserver.bean.Info;
interface IDecentInfoService{
    int getPid();
    Info getInfo();
    void setInfo(in Info info);
}