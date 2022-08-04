package com.dpwgc.alisalog.router.model;

public class Node {
    String address;
    String udpPort;
    String httpPort;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHttpPort(String httpPort) {
        this.httpPort = httpPort;
    }

    public void setUdpPort(String udpPort) {
        this.udpPort = udpPort;
    }

    public String getAddress() {
        return address;
    }

    public String getHttpPort() {
        return httpPort;
    }

    public String getUdpPort() {
        return udpPort;
    }
}
