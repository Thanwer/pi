package com.thanwer;

import rice.p2p.commonapi.NodeHandle;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.net.InetAddress;

/**
 * Created by Thanwer on 02/04/2017.
 */

@Entity
public class Peer {

    //@Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    //private long id;



    private String name;
    //private InetAddress ipWAN;
    @Id
    private InetAddress ipLAN;


    private NodeHandle nh;
    //private int port;

    public Peer() {}

    public Peer(String name, InetAddress ipLAN) {
        this.name = name;
        this.ipLAN = ipLAN;
    }

    public Peer(String name, InetAddress ipLAN, InetAddress id) {
        this.name = name;
        this.ipLAN = ipLAN;
    }

    public Peer(String name, InetAddress ipLAN, NodeHandle nh) {
        this.name = name;
        this.ipLAN = ipLAN;
        this.nh = nh;
    }

    /*public Peer(String name, InetAddress ipWAN, InetAddress ipLAN, int port) {
        this.name = name;
        this.ipWAN = ipWAN;
        this.ipLAN = ipLAN;
        this.port = port;
    }*/

    public String getName() {
        return name;
    }

    public InetAddress getIpLAN() { return ipLAN; }

    /*public InetAddress getIpWAN() {
        return ipWAN;
    }*/

    /*public int getPort() {
        return port;
    }*/

    public InetAddress getId() {
        return ipLAN;
    }

    @Override
    public String toString() {
        return "Name=" + name + " IP=" + ipLAN;
    }

    public NodeHandle getNh() {
        return nh;
    }
}
