package com.thanwer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Thanwer on 15/05/2017.
 */
public class LocalPeerDiscovery implements Runnable {

    DatagramSocket socket;
    String name;

    public LocalPeerDiscovery(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            //Keep a socket open to listen to all the UDP trafic that is destined for this port
            socket = new DatagramSocket(8888, InetAddress.getByName("0.0.0.0"));
            socket.setBroadcast(true);

            while (true) {
                //System.out.println(getClass().getName() + ">>>Ready to receive broadcast packets!");

                //Receive a packet
                byte[] recvBuf = new byte[15000];
                DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
                socket.receive(packet);

                //Packet received
                //System.out.println(getClass().getName() + ">>>Discovery packet received from: " + packet.getAddress().getHostAddress());
                //System.out.println(getClass().getName() + ">>>Packet received; data: " + new String(packet.getData()));

                //See if the packet holds the right command (message)
                String message = new String(packet.getData()).trim();
                if (message.equals("MORSE")) {
                    byte[] sendData = name.getBytes();

                    //Send a response
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
                    socket.send(sendPacket);

                    //System.out.println(getClass().getName() + ">>>Sent packet to: " + sendPacket.getAddress().getHostAddress());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(LocalPeerDiscovery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*public static LocalPeerDiscovery getInstance() {
        return LocalPeerDiscoveryHolder.INSTANCE;
    }

    private static class LocalPeerDiscoveryHolder {
        private static final LocalPeerDiscovery INSTANCE = new LocalPeerDiscovery();
    }*/
}
