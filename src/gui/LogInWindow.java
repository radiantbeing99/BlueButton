package gui;

import boardgamecafe.BoardGameCafe;
import boardgamecafe.Member;
import boardgamecafe.NonMember;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class LogInWindow extends Template {
    String phoneNumber;
    String name;
    String password;
    String id;

    @Override
    void addComponents() {
        JPanel memberPanel = new BasicPanel();
        JPanel nonMemberPanel = new BasicPanel();
        BasicLabel explainLabel = new BasicLabel();
        BasicButton signupButton = new BasicButton();
        BasicButton prevButton = new BasicButton();
        setLayout(null);
        memberLoginWindow(memberPanel);
        nonmemberLoginWindow(nonMemberPanel);
        textWindow(explainLabel);
        buttonComponent(signupButton, prevButton);
        add(memberPanel);
        add(nonMemberPanel);
        add(explainLabel);
        add(signupButton);
        add(prevButton);
    }

    void memberLoginWindow(JPanel memberPanel){
        //about memberPanel
        memberPanel.setLayout(null);
        memberPanel.setPreferredSize(new Dimension(500,500));
        memberPanel.setBounds(100,90,300,400);

        //Member Label
        BasicLabel memberNameLabel = new BasicLabel("회원");
        memberNameLabel.setFontAttribute(20, true);
        memberNameLabel.setBounds(40,10,100,100);
        memberPanel.add(memberNameLabel);

        //member ID Label
        BasicLabel memberIdLabel = new BasicLabel("전화번호");
        memberIdLabel.setFontAttribute(15);
        memberIdLabel.setBounds(30,120,100,50);
        memberPanel.add(memberIdLabel);

        JTextField memberIdTextField = new JTextField(60);
        memberIdTextField.setBounds(120,130,150,30);
        memberPanel.add(memberIdTextField);

        //member PW Label
        BasicLabel memberPWLabel = new BasicLabel("비밀번호");
        memberPWLabel.setFontAttribute(15);
        memberPWLabel.setBounds(30,200,100,50);
        memberPanel.add(memberPWLabel);

        JTextField memberPWTextField = new JTextField(40);
        memberPWTextField.setBounds(120,210,150,30);
        memberPanel.add(memberPWTextField);

        //login button
        BasicButton logInButton = new BasicButton("로그인");
        logInButton.setBounds(30,300,250,40);
        logInButton.setFontAttribute(18);
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id = memberIdTextField.getText();
                Member m = (Member) BoardGameCafe.memberMgr.find(id);
                password = memberPWTextField.getText();
                if (m == null || id.equals("")){// 잘못된 아이디 입력시
                    JOptionPane.showMessageDialog(null, "ID doesn't exist");
                    memberPWTextField.setText("");
                    memberIdTextField.setText("");
                    return;
                }
                
                if (m.matches(password)){ //로그인 성공
                    JOptionPane.showMessageDialog(null, "Login Complete");
                    MainGUI.changeWindow(MainGUI.roomSelectWindow);
                    //후에 좌석선택, 시간선택으로 넘어가야함
                }
                else{//다른 비밀번호
                    JOptionPane.showMessageDialog(null, "Wrong Password");
                    memberPWTextField.setText("");
                }
                memberPWTextField.setText("");
                memberIdTextField.setText("");
            }
        });

        memberPanel.add(logInButton);
    }

    void nonmemberLoginWindow(JPanel nonMemberPanel){
        //about nonmemberPanel
        nonMemberPanel.setLayout(null);
        nonMemberPanel.setPreferredSize(new Dimension(500,500));
        nonMemberPanel.setBounds(710,90,300,400);

        //nonMember Label
        BasicLabel nonMemberNameLabel = new BasicLabel("비회원");
        nonMemberNameLabel.setFontAttribute(20, true);
        nonMemberNameLabel.setBounds(40,10,200,100);

        nonMemberPanel.add(nonMemberNameLabel);

        //nonmember ID Label
        BasicLabel nonMemberIdLabel = new BasicLabel("이름");
        nonMemberIdLabel.setFontAttribute( 15);
        nonMemberIdLabel.setBounds(40,120,100,50);
        nonMemberPanel.add(nonMemberIdLabel);

        JTextField nonMemberIdTextField = new JTextField(60);
        nonMemberIdTextField.setBounds(120,130,150,30);
        nonMemberPanel.add(nonMemberIdTextField);

        //nonmember PW Label
        BasicLabel nonMemberPWLabel = new BasicLabel("전화번호");
        nonMemberPWLabel.setFontAttribute( 15);
        nonMemberPWLabel.setBounds(30,200,100,50);
        nonMemberPanel.add(nonMemberPWLabel);

        JTextField nonMemberPWTextField = new JTextField(40);
        nonMemberPWTextField.setBounds(120,210,150,30);
        nonMemberPanel.add(nonMemberPWTextField);

        //login button
        BasicButton logInButton = new BasicButton("로그인");
        logInButton.setBounds(30,300,250,40);
        logInButton.setFontAttribute(18);
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = nonMemberIdTextField.getText();
                phoneNumber = nonMemberPWTextField.getText();

                if (name.equals("")){// 잘못된 아이디 입력시
                    JOptionPane.showMessageDialog(null, "아이디를 정확히 입력하세요");
                    nonMemberIdTextField.setText("");
                    nonMemberPWTextField.setText("");
                    return;
                }
                //입력이 제대로 될때-> 파일이 입력이 된다.
                registerNonMember(nonMemberIdTextField, nonMemberPWTextField);
                JOptionPane.showMessageDialog(null, "Login Complete");
                MainGUI.changeWindow(MainGUI.roomSelectWindow);
                nonMemberIdTextField.setText("");
                nonMemberPWTextField.setText("");
            }
        });

        nonMemberPanel.add(logInButton);
    }

    void textWindow(BasicLabel explainLabel){
        explainLabel.setText("회원 혜택: 결제금액의 5% 적립가능, 회원 등급에 따른 할인부여");
        explainLabel.setFontAttribute(20, true);
        explainLabel.setBounds(100,500,1000,50);
    }

    void buttonComponent(BasicButton signupButton, BasicButton prevButton){
        prevButton.setText("이전");
        prevButton.setBounds(100,580,300,40);
        prevButton.setFontAttribute(18);
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.roomViewWindow);
            }
        });

        signupButton.setText("회원가입");
        signupButton.setBounds(710,580,300,40);
        signupButton.setFontAttribute(18);

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainGUI.changeWindow(MainGUI.signUpWindow);
            }
        });
    }

    void registerNonMember(JTextField idTextField, JTextField phoneTextField) {
        FileWriter writer = BoardGameCafe.memberMgr.openfileWriter("non_member.txt");
        id = idTextField.getText();
        phoneNumber = phoneTextField.getText();

        NonMember newNonMember = new NonMember();
        newNonMember.read(id, phoneNumber);
        BoardGameCafe.memberMgr.addList(newNonMember);

        try {
            writer.write("\n"+id + " " + phoneNumber + " ");
            writer.close();
        } catch (IOException e) {
            System.out.println("파일쓰기오류");
            System.exit(0);
        }
    }
}
