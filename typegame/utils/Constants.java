package utils;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;

public class Constants {
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final int FONT_SIZE = 24;
	
	public static final String TITLE = "Typing Game";
	public static final String WORD_LISTS_FILE_NAME = "WordLists";

	
    public static final int UDPPORT = 9090;
    public static final int TCPPORT = 9091;
    // delay in milliseconds between broadcasts
    public static final int UDPINTERVAL = 1000;
    public static final InetAddress broadcastAddress;
    static {
        // create broadcast address object refrencing the local machine's
        // broadcasting address for use with UDP
        broadcastAddress = getBroadcastAddress();
        assert (broadcastAddress != null);
    }
    private static InetAddress getBroadcastAddress() {
        ArrayList<NetworkInterface> interfaces = new ArrayList<>();
        try {
            interfaces.addAll(Collections.list(
                    NetworkInterface.getNetworkInterfaces()));
        } catch (SocketException ex) {
            ex.printStackTrace();
            return null;
        }
        for (NetworkInterface nic: interfaces) {
            try {
                if (!nic.isUp() || nic.isLoopback())
                    continue;
            } catch (SocketException ex) {
                continue;
            }
            for (InterfaceAddress ia: nic.getInterfaceAddresses()) {
                if (ia == null || ia.getBroadcast() == null)
                    continue;
                return ia.getBroadcast();
            }
        }
        return null;
    }
	
}
