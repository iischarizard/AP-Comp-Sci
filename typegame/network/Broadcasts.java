package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import utils.Constants;

public class Broadcasts {

    private final Runnable receiver;
    private final Runnable sender;
    private Thread receiverThread;
    private Thread senderThread;
    private boolean run = true;

    public Broadcasts(Server parent) {
        receiver = new Runnable() {
            public void run() {
                byte data[] = new byte[0];
                DatagramSocket socket = null;
                try {
                    socket = new DatagramSocket(Constants.UDPPORT);
                } catch (SocketException ex) {
                    ex.printStackTrace();
                    parent.quit();
                }
                DatagramPacket packet = new DatagramPacket(data, data.length);
                while (run) {
                    try {
                        socket.receive(packet);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        parent.quit();
                    }
                    parent.newAddress(packet.getAddress());
                }
            }
        };
        sender = new Runnable() {
            public void run() {
                byte data[] = new byte[0];
                DatagramSocket socket = null;
                try {
                    socket = new DatagramSocket();
                } catch (SocketException ex) {
                    ex.printStackTrace();
                    parent.quit();
                }
                DatagramPacket packet = new DatagramPacket(
                        data, 
                        data.length, 
                        Constants.broadcastAddress, 
                        Constants.UDPPORT);
                while (run) {
                    try {
                        socket.send(packet);
                        Thread.sleep(Constants.UDPINTERVAL);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        parent.quit();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                        parent.quit();
                    }
                }
            }
        };
        receiverThread = new Thread(receiver);
        senderThread = new Thread(sender);
    }

	public void start(){
		receiverThread.start();
		senderThread.start();
	}
    public void quit() {
        run = false;
    }
}