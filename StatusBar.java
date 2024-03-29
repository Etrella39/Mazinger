import javax.swing.*;

class StatusBar extends JPanel {
    private JTextField position;
    private JTextField clickPosition;

    public StatusBar() {
        position = new JTextField("posision",8);
        position.setEditable(false);
        clickPosition = new JTextField("clickPosition",8);
        clickPosition.setEditable(false);
        add(position);
        add(clickPosition);
   
    }

    public void setStatus(String text) {
        position.setText(text);
    }
    public void setStatus1(String text) {
        clickPosition.setText(text);
    }
}