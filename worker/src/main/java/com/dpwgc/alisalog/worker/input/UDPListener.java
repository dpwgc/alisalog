package com.dpwgc.alisalog.worker.input;

import com.dpwgc.alisalog.common.util.LogUtil;
import com.dpwgc.alisalog.worker.buffer.BufferProducer;
import com.dpwgc.alisalog.worker.config.UDPConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * UDP监听服务
 */
@WebListener
public class UDPListener implements ServletContextListener {

    /**
     * 启动UDP监听线程
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        new Thread(() -> {
            //等待spring boot加载完后再运行UDP监听线程（避免配置文件中的参数来不及加载进内存）
            for (int i=0;i<10;i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (i == 4 && UDPConfig.UDP_PORT == 0) {
                    LogUtil.error("UDP listener start error","UDP port null");
                    break;
                }
                if(UDPConfig.UDP_PORT != 0) {
                    LogUtil.info("UDP listener thread",String.format("%s%s","UDP listener port: ",UDPConfig.UDP_PORT));
                    //开启监听
                    listener();
                    break;
                }
            }

        }).start();
    }

    /**
     * 监听UDP消息
     */
    private void listener() {

        try {
            //UDP socket
            DatagramSocket socket = new DatagramSocket(UDPConfig.UDP_PORT);

            //持续监听UDP
            while (true) {
                try {
                    byte[] logInput = new byte[UDPConfig.UDP_MAX_DATA_SIZE];
                    DatagramPacket packet = new DatagramPacket(logInput, logInput.length);

                    //接收客户端发来的字节数组
                    socket.receive(packet);
                    logInput = packet.getData();

                    BufferProducer.publish(logInput);

                } catch (IOException e) {
                    LogUtil.info("UDP listener error",e.toString());
                }
            }
        } catch (Exception e) {
            LogUtil.info("DatagramSocket error",e.toString());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LogUtil.info("UDP close","UDP listener stop");
    }
}

