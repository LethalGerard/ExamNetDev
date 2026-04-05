import java.net.*;
import java.io.*;


public class ChatClient {
    ChatWindowGUI chatGUI;
    int port = 8888;
    String userName;

    MulticastSocket multicastSocket;
    InetAddress inetAddress;

    public ChatClient(String userName) throws IOException {
        this.userName = userName;
        multicastSocket = new MulticastSocket(port);
        try {
            inetAddress = InetAddress.getByName("230.30.30.30");
            multicastSocket.joinGroup(inetAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        chatGUI = new ChatWindowGUI(userName, this);
        Receiver receiver = new Receiver(multicastSocket, chatGUI);
        new Thread(receiver).start();
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
