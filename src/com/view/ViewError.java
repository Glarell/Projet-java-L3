package com.view;

import com.controller.ControllerViewError;

import javax.swing.*;
import java.awt.*;

/**
 * The type View error.
 */
public class ViewError extends JFrame {

    /**
     * Instantiates a new View error.
     *
     * @param message the message
     */
    public ViewError(String message) {
        JPanel jPanel = new JPanel(new BorderLayout());
        JTextArea jTextArea = new JTextArea(message);
        jTextArea.setOpaque(true);
        jTextArea.setBackground(Color.red);
        jTextArea.setForeground(Color.white);
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        jTextArea.setEditable(false);
        jTextArea.setFont(new Font("Arial", Font.BOLD, 26));
        jPanel.add(jTextArea, BorderLayout.CENTER);
        JButton button = new JButton("OK");
        button.addActionListener(new ControllerViewError(this));
        jPanel.add(button, BorderLayout.SOUTH);
        this.setTitle("Erreur");
        this.setContentPane(jPanel);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(250, 250));
        this.pack();
    }
}
