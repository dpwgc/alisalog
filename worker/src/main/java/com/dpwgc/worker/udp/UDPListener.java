package com.dpwgc.worker.udp;

import com.dpwgc.common.util.GzipUtil;
import com.dpwgc.common.util.LogUtil;
import com.dpwgc.worker.buffer.Buffer;
import com.dpwgc.worker.config.UDPConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

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
            try {
                //等待spring boot加载完后再运行UDP监听线程（避免配置文件中的参数来不及加载进内存）
                while (true){
                    if(UDPConfig.UDP_PORT != 0) {
                        break;
                    }
                }

                listener();
                LogUtil.info("UDP listener thread","UDP listener start");

            } catch (SocketException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 监听UDP消息
     */
    private void listener() throws SocketException {

        //UDP socket
        DatagramSocket socket = new DatagramSocket(UDPConfig.UDP_PORT);

        //持续监听UDP消息
        while (true) {
            byte[] msg = new byte[UDPConfig.UDP_MAX_DATA_SIZE];
            DatagramPacket packet = new DatagramPacket(msg, msg.length);
            try {
                //接收客户端发来的字节数组
                socket.receive(packet);
                msg = packet.getData();

                //将日志信息压缩，再插入本地缓冲队列
                Buffer.add(GzipUtil.compress(msg));

            } catch (IOException e) {
                LogUtil.info("UDP listener error",e.toString());
            }
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LogUtil.info("UDP close","UDP listener stop");
    }
}

