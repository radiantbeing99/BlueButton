package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RoomViewWindow extends Template {
    @Override
    void addComponentsToPane() {
        primaryPanel.setLayout(null);

        // About roomViewPanel
        JPanel roomViewPanel = new JPanel();
        primaryPanel.add(roomViewPanel);
        roomViewPanel.setBounds(150,70,1000,500);
        roomViewPanel.setBackground(Color.WHITE);
        roomViewPanel.setBorder(BorderFactory.createLineBorder(secondaryColor, 1));
        roomViewPanel.setLayout(new GridLayout(2, 5));

        // About roomObjectPanel
        ArrayList<JPanel> roomPanelArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JPanel roomPanel = new JPanel(null);
            roomPanel.setPreferredSize(new Dimension(200, 250));
            roomPanel.setBorder(new LineBorder(primaryColor, 1));

            JLabel roomNumberLabel = new JLabel("" + (i + 1));
            roomNumberLabel.setFont(new Font("맑은고딕", Font.PLAIN, 20));
            roomNumberLabel.setForeground(primaryColor);
            roomNumberLabel.setBounds(95,75,100,100);
            roomPanel.add(roomNumberLabel);

            roomPanelArrayList.add(roomPanel);
        }
        for (JPanel rPanel: roomPanelArrayList) {
            roomViewPanel.add(rPanel);
        }

        // About loginButton
        JButton loginButton = new JButton();
        primaryPanel.add(loginButton);
        loginButton.setText("LOGIN");
        loginButton.setBounds(150,580,1000,40);
        loginButton.setBackground(Color.WHITE);
        loginButton.setFont(new Font("맑은고딕", Font.BOLD, 18));
        loginButton.setForeground(new Color(69,116,203));
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                // new LogInWindow().createAndShowGUI();
            }
        });
    }
}