package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener {

    private JButton helloButton;
    private JButton goodByeButton;
    private TypingListener typingListener;

    public TypingListener getTypingListener() {
        return typingListener;
    }

    public void setTypingListener(TypingListener typingListener) {
        this.typingListener = typingListener;
    }

    public Toolbar () {
        this.helloButton = new JButton("Hello");
        helloButton.addActionListener(this);
        this.goodByeButton = new JButton("Bye");
        goodByeButton.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(helloButton);
        add(goodByeButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (typingListener != null) {
            if (clicked == helloButton) {
                typingListener.textTyped("Hello button clicked\n");
            } else if (clicked == goodByeButton) {
                typingListener.textTyped("Good bye button clicked\n");
            } else {
                typingListener.textTyped("Unknown button clicked\n");
            }
        }
    }
}
