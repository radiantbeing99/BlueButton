package gui;

import gui.template.BasicButton;
import gui.template.BasicPanel;
import gui.template.Template;

import javax.swing.*;
import javax.swing.border.LineBorder;

import boardgamecafe.NonMember;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SampleOptionWindow extends Template {
    BasicPanel basicPanel;
    BasicButton roomButton;
    BasicButton timeButton;
    BasicButton gameButton;
    BasicButton foodButton;
    BasicButton payButton;

    @Override
    public void addComponents() {
        basicPanel = new BasicPanel();
        setMenu();
        setLayout(null);
        addButton();
        add(basicPanel);
    }

    void setMenu() {
    	NonMember m = LogInWindow.getNowLoginMember();
    	
        //basicPanel 세팅
        basicPanel.setBounds(new Rectangle(160, 15, 960, 550));
        basicPanel.setBorder(new LineBorder(new Color(30, 31, 33), 3));
        basicPanel.setLayout(null);

        //가운데 주사위 이미지
        JLabel centerLabel = new JLabel();
        centerLabel.setIcon(MainGUI.scaleImageIcon("imgs/menu/dices.png",150,150));
        centerLabel.setBounds(new Rectangle(415,100,300,300));
        basicPanel.add(centerLabel);

        //가운데 글씨
        JLabel blueLabel = new JLabel();
        blueLabel.setIcon(MainGUI.scaleImageIcon("imgs/menu/bluebutton.png",400,450));
        blueLabel.setBounds(new Rectangle(290,240,300,300));
        basicPanel.add(blueLabel);


        //방선택
        BasicPanel roomPanel = new BasicPanel();
        roomPanel.setBounds(new Rectangle(60,5,300,300));
        roomPanel.setLayout(null);
        JLabel roomLabel = new JLabel();
        roomLabel.setBounds(new Rectangle(50,0,200,200));
        roomButton = new BasicButton("방 선택");
        roomButton.setBounds(new Rectangle(0,200,200,40));
        if(m.getRoomNumber() != 0) {		// 이미 방을 가지고 있는 경우 방선택 버튼 선택이 불가능하게
        	roomButton.setEnabled(false);
        }
        roomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.roomSelectWindow);
            }
        });
        roomLabel.setIcon(MainGUI.scaleImageIcon("imgs/menu/room.png",100,100));
        roomPanel.add(roomLabel);
        roomPanel.add(roomButton);
        basicPanel.add(roomPanel);

        //시간선택
        BasicPanel timePanel = new BasicPanel();
        timePanel.setBounds(new Rectangle(700,5,200,300));
        timePanel.setLayout(null);
        JLabel timeLabel = new JLabel();
        timeLabel.setBounds(new Rectangle(50,0,200,200));
        timeButton = new BasicButton("시간선택");
        timeButton.setBounds(new Rectangle(0,200,200,40));
        timeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.timeSelectWindow);
            }
        });
        timeLabel.setIcon(MainGUI.scaleImageIcon("imgs/menu/clock.png",100,100));
        timePanel.add(timeLabel);
        timePanel.add(timeButton);
        basicPanel.add(timePanel);

        //게임선택
        BasicPanel gamePanel = new BasicPanel();
        gamePanel.setBounds(new Rectangle(60,270,200,240));
        gamePanel.setLayout(null);
        JLabel gameLabel = new JLabel();
        gameLabel.setBounds(new Rectangle(50,0,200,200));
        gameButton = new BasicButton("게임선택");
        gameButton.setBounds(new Rectangle(0,200,200,40));
        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.gameSelectWindow);
            }
        });
        gameLabel.setIcon(MainGUI.scaleImageIcon("imgs/menu/boardgame.png",100,100));
        gamePanel.add(gameLabel);
        gamePanel.add(gameButton);
        basicPanel.add(gamePanel);

        //음식선택
        BasicPanel foodPanel = new BasicPanel();
        foodPanel.setBounds(new Rectangle(700,270,200,240));
        foodPanel.setLayout(null);
        JLabel foodLabel = new JLabel();
        foodLabel.setBounds(new Rectangle(50,0,200,200));
        foodButton = new BasicButton("음식선택");
        foodButton.setBounds(new Rectangle(0,200,200,40));
        foodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.snackOrderWindow);
            }
        });
        foodLabel.setIcon(MainGUI.scaleImageIcon("imgs/menu/food.png",100,100));
        foodPanel.add(foodLabel);
        foodPanel.add(foodButton);
        basicPanel.add(foodPanel);
    }

    void addButton(){
        payButton = new BasicButton("결제");
        payButton.setBackground(new Color(121, 117, 117));

        payButton.setBounds(new Rectangle(800,580,320,40));
        payButton.setEnabled(false);
        add(payButton);
        payButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	NonMember m = LogInWindow.getNowLoginMember();
            	if(m.getPlayingGame() == null) {
            		JOptionPane.showMessageDialog(null, "게임이 선택되지 않아 결제가 불가능 합니다.");
            		return;
            	}
            	MainGUI.myPageWindow = new MypageWindow();
                MainGUI.changeWindow(MainGUI.myPageWindow);
            }
        });

        BasicButton logOutButton = new BasicButton("로그아웃");
        logOutButton.setBounds(new Rectangle(160,580,320,40));
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogInWindow.nowLoginMember = null;
                LogInWindow.nowLoginNonMember = null;
                MainGUI.changeWindow(MainGUI.logInWindow);
            }
        });
        add(logOutButton);
    }

    void grayScaleButton(JButton button) {
        button.setBackground(new Color(121, 117, 117));
    }

    void decideEnablePayButton() {
    	NonMember m = LogInWindow.getNowLoginMember();
        if((m.getRoomNumber() != 0)
        && m.getRemainingTime() > 0
        && m.getPlayingGame() != null) {
            payButton.setBackground(new Color(0, 120, 242));
            payButton.setEnabled(true);
        }

    }

    //로그아웃이나, 다음사용자 로그인시 처음 버튼의 상태를 유지합니다,
    public void resetAllButton(){
        roomButton.setBackground(new Color(0, 120, 242));
        timeButton.setBackground(new Color(0, 120, 242));
        gameButton.setBackground(new Color(0, 120, 242));
        foodButton.setBackground(new Color(0, 120, 242));

        payButton.setBackground(new Color(121, 117, 117));
        payButton.setEnabled(false);

    }

}
