package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.input.KeyEvent;
import main.TypingGame;
import utils.Constants;

public class Server implements Runnable{
	

    private final ArrayList<Socket> sockets;
    private boolean run = true;
    private StringBuilder lines;
    private TypingGame game;
    // broadcast and receive of UDP; used for TCP connection(s) to peer(s)
    private final Broadcasts broadcasts;
    
    private Thread thread;
    
    public Server(TypingGame game_){
    	game = game_;
    	sockets = new ArrayList<Socket>();
        lines = new StringBuilder();
        //Platform.runLater(this);
    	
    	thread = new Thread(this);
    	thread.start();
        broadcasts = new Broadcasts(this);
    }

    public void keyTyped(KeyEvent e){
        synchronized (sockets) {
            List<Socket> toRemove = new LinkedList<>();

            for (Socket s : sockets) {
                try {
                    PrintWriter pw = new PrintWriter(s.getOutputStream());
                    pw.print(e.getCharacter());
                    pw.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    toRemove.add(s);
                }
            }

            sockets.removeAll(toRemove);
        }
    	
    }
    
    private void socketStream(final Socket s) {
        final InputStream is;
        try {
            is = s.getInputStream();
        } catch (IOException ex) {
            return;
        }
        final InputStreamReader isr = new InputStreamReader(is);
        final BufferedReader br = new BufferedReader(isr);
        new Thread(new Runnable() {
            public void run() {
                while (run && s.isConnected()) {
                    try {
                        if (br.ready())
                            putChar(br.read());
                    } catch (IOException ex) {
                        return;
                    }
                }
                try {
					isr.close();
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }).start();
    }

    // method called by per-connection thread defined in socketStream
    public void putChar(int ch) {
        // check for backspace and space for delete,
        // otherwise put character into buffer,
        // and show updated buffer
        if (ch == 8 && lines.length() > 0)
            lines.delete(lines.length() - 1, lines.length());
        else
            lines.append((char)ch);
        synchronized (game) {
        Platform.runLater(new Runnable() {
            @Override public void run() {
            	game.test.setText(String.valueOf((char)ch));
                // etc
            }
        });
            //textArea.setText(lines.toString() + '.');
        }
    }
    
    public void quit() {
        run = false;
        broadcasts.quit();
    }
    
    protected void newAddress(InetAddress address) {
        synchronized (sockets) {
            // check if already connected to address, and exit if true
            for (Socket addr: sockets)
                if (addr.getInetAddress().getHostAddress()
                        .equals(address.getHostAddress()))
                    return;
            // create a new socket and add it to transmission pool
            Socket s;
            try {
                s = new Socket(address.getHostAddress(), Constants.TCPPORT);
            } catch (IOException ex) {
                return;
            }
            sockets.add(s);
        }
    }

    private ServerSocket ss;
    
	/*@Override
	protected Void call() throws Exception {

     
		
		
		return null;
		
	}*/
	public void close(){
		quit();
		
	}

	@Override
	public void run() {   
		try {
	        ss = new ServerSocket(Constants.TCPPORT);
	        while (ss.isBound() && run) {
	            socketStream(ss.accept());
	            System.out.println(ss.isBound());
	        }
	        System.out.println("test");
	        ss.close();
	        quit();
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        quit();
	    }
		
	}
    
	
}