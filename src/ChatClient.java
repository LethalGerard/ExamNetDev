import javax.swing.*;
import java.net.*;
import java.io.*;


public class ChatClient {
    ChatWindowGUI chatGUI;
    int port = 44444;
    String userName;

    MulticastSocket multicastSocket;
    InetAddress inetAddress;

    public ChatClient(String userName) throws IOException {
        this.userName = userName;
        multicastSocket = new MulticastSocket(port);
        try {
            inetAddress = InetAddress.getByName("234.234.234.234");
            multicastSocket.joinGroup(inetAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        chatGUI = new ChatWindowGUI(userName, this);
        Receiver receiver = new Receiver(multicastSocket, chatGUI, this);
        new Thread(receiver).start();
        sendMessage("Just joined: " + userName);
        sendMessage("Joined: " + userName);
    }

    public void sendPingback() {
        sendMessage("Pingback: " + userName);
    }

    public void sendMessage(String message) {
        try {
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, inetAddress, port);
            multicastSocket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
