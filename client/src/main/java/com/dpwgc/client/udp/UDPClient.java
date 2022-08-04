package com.dpwgc.client.udp;

import com.dpwgc.common.util.LogUtil;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

    public static void send(String address,Integer port,String data) {
        try {
            //创建InetAddress对象，封装自己的IP地址
            InetAddress inet=InetAddress.getByName(address);
            DatagramPacket dp=new DatagramPacket(data.getBytes(),data.getBytes().length,inet,port);
            //创建DatagramSocket对象，数据包的发送和接受对象
            DatagramSocket ds=new DatagramSocket();
            //调用ds对象的方法send，发送数据包
            ds.send(dp);
            ds.close();
        } catch (Exception e) {
            LogUtil.error("UDPClient send error",e.toString());
        }
    }
}
