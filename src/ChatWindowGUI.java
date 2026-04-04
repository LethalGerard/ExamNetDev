import javax.swing.*;
import java.awt.*;

public class ChatWindowGUI extends JFrame {

    public ChatWindowGUI(String userName) {
        setTitle("Chat - " + userName);
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JButton exitChat = new JButton("Exit chat");
        JTextArea textArea = new JTextArea();


        mainPanel.add(exitChat, BorderLayout.NORTH);
        mainPanel.add(textArea, BorderLayout.WEST);


        add(mainPanel);
        setVisible(true);
    }
}

void main() {
    ChatWindowGUI chatClient = new ChatWindowGUI("Kim");
}
