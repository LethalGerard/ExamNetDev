import javax.swing.*;
import java.net.*;

public class Receiver implements Runnable {

    MulticastSocket socket;
    ChatWindowGUI gui;
    ChatClient client;

    public Receiver(MulticastSocket socket, ChatWindowGUI gui, ChatClient client) {
        this.client = client;
        this.socket = socket;
        this.gui = gui;
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                SwingUtilities.invokeLater(() -> {
                    if (message.startsWith("Joined:")) {
                        gui.addMemberToList(message.substring(8));
                        client.sendPingback();
                    } else if (message.startsWith("Pingback:")) {
                        gui.addMemberToList(message.substring(10));
                    } else if (message.startsWith("Disconnected:")) {
                        String removeUser = message.substring(14);
                        gui.removeUserFromList(removeUser);
                        gui.addMsgToChat(message);
                    } else {
                        gui.addMsgToChat(message);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
