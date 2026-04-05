import javax.swing.*;

public class Main {

    void main() {
        String name = JOptionPane.showInputDialog(null, "Enter username: ", "Username", JOptionPane.QUESTION_MESSAGE);
        try {
            ChatClient myChat = new ChatClient(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
