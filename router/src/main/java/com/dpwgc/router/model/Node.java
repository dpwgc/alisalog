package com.dpwgc.router.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Node {
    String address;
    String udpPort;
    String httpPort;
}
