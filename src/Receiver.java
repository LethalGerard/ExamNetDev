import java.net.*;

public class Receiver implements Runnable {

    MulticastSocket socket;
    ChatWindowGUI gui;

    public Receiver(MulticastSocket socket, ChatWindowGUI gui) {
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
                gui.addMsgToChat(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
