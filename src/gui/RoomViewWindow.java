package gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RoomViewWindow extends Template {
    static ArrayList<JPanel> roomPanelArrayList = new ArrayList<>();

    @Override
    void addComponents() {
        // Initialize
        setLayout(null);

        // About roomViewPanel
        JPanel roomViewPanel = new JPanel();
        add(roomViewPanel);
        roomViewPanel.setBounds(160, 70, 960, 500);
        roomViewPanel.setLayout(new GridLayout(2, 5));

        // About roomObjectPanel
        for (int i = 0; i < 10; i++) {
            JPanel roomPanel = new JPanel(null);
            roomPanel.setPreferredSize(new Dimension(200, 250));
            roomPanel.setBackground(new Color(41, 42, 45));
            roomPanel.setBorder(new LineBorder(new Color(30, 31, 33), 1));

            BasicLabel roomNumberLabel = new BasicLabel("" + (i + 1));
            roomNumberLabel.setFont(new Font("NanumGothic", Font.PLAIN, 20));
            roomNumberLabel.setForeground(Color.WHITE);
            roomNumberLabel.setBounds(95, 75, 100, 100);
            roomPanel.add(roomNumberLabel);

            roomPanelArrayList.add(roomPanel);
        }
        for (JPanel rPanel: roomPanelArrayList) {
            roomViewPanel.add(rPanel);
        }

        // About loginButton
        BasicButton loginButton = new BasicButton("로그인");
        add(loginButton);
        loginButton.setBounds(160, 580, 960, 40);
        loginButton.setFontAttribute(18);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.logInWindow);
            }
        });

        // if this button click, go to adminMenu.
        JPanel iconPanel = new JPanel();
        iconPanel.setBounds(1080, 15, 50, 50);
        iconPanel.setOpaque(false);
        add(iconPanel);

        ImageIcon icon = new ImageIcon("imgs/settings.png");
        Image scaleImage = icon.getImage().getScaledInstance(28, 28,Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaleImage);

        JButton adminButton = new JButton(icon);
        adminButton.setBorderPainted(false);
        adminButton.setContentAreaFilled(false);
        adminButton.setFocusPainted(false);
        adminButton.setOpaque(false);
        iconPanel.add(adminButton);

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.adminLoginWindow);
            }
        });
    }

    void changeRoomInfo(String roomNum, boolean flag) {
        JPanel jPanel = roomPanelArrayList.get(Integer.parseInt(roomNum) - 1);
        if (flag) {//방이 이용중일때
            jPanel.setBackground(new Color(121, 117, 117));
            return;
        }
        //이용시간이 끝나거나 이용중이 아니면
        jPanel.setBackground(new Color(41, 42, 45));
    }
}
