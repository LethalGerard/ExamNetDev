import javax.swing.*;
import java.awt.*;

public class ChatWindowGUI extends JFrame {

    private ChatClient chatClient;

    private JButton disconnectBtn;
    private JTextArea chatArea;
    private JTextArea memberList;
    private JTextField userInput;

    public ChatWindowGUI(String userName, ChatClient chatClient) {
        this.chatClient = chatClient;
        setTitle("Chat - " + userName);
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        disconnectBtn = new JButton("Exit chat");
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        memberList = new JTextArea(10, 15);
        memberList.setEditable(false);
        userInput = new JTextField();
        JScrollPane memberScroll = new JScrollPane(memberList);
        JScrollPane chatScroll = new JScrollPane(chatArea);

        mainPanel.add(disconnectBtn, BorderLayout.NORTH);
        mainPanel.add(chatScroll, BorderLayout.CENTER);
        mainPanel.add(memberScroll, BorderLayout.EAST);
        mainPanel.add(userInput, BorderLayout.SOUTH);

        add(mainPanel);
        userInput.addActionListener(e->{
            chatClient.sendMessage( chatClient.userName + ": " + userInput.getText());
            userInput.setText("");
        });
        setVisible(true);
    }

    public void addMsgToChat(String message) {
        chatArea.append(message + "\n");
    }

    public void addMemberToList(String member) {
        memberList.append(member);
    }
}
