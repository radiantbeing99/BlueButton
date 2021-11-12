package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminLoginWindow extends Template{
	private static final long serialVersionUID = 1L;
	
	@Override
	void addComponents() {
		JPanel adminLoginPanel = new JPanel();
		setLayout(null);
		adminLoginWindow(adminLoginPanel);
		add(adminLoginPanel);
	}
	
	private void adminLoginWindow(JPanel adminLoginPanel) {
		
		// About loginPanel
		adminLoginPanel.setLayout(null);
		adminLoginPanel.setPreferredSize(new Dimension(500, 500));
		adminLoginPanel.setBackground(Color.WHITE);
		adminLoginPanel.setBounds(490, 90, 300, 400);
		
		JLabel adminLabel = new JLabel("Admin Login");
		adminLabel.setForeground(new Color(48, 87, 232));
		adminLabel.setFont(new Font("맑은고딕", Font.BOLD, 30));
		adminLabel.setBounds(40, 10, 300, 100);
		adminLoginPanel.add(adminLabel);
		
		JLabel adminIdLabel = new JLabel("ID");
		adminIdLabel.setFont(new Font("맑은고딕", Font.BOLD, 15));
		adminIdLabel.setForeground(new Color(48, 87, 232));
		adminIdLabel.setBounds(20, 120, 100, 50);
		adminLoginPanel.add(adminIdLabel);
		
		JTextField idField = new JTextField(60);
		idField.setBounds(80, 140, 200, 30);
		adminLoginPanel.add(idField);
		
		JLabel adminPwdLabel = new JLabel("PW");
		adminPwdLabel.setFont(new Font("맑은고딕", Font.BOLD, 15));
		adminPwdLabel.setForeground(new Color(48, 87, 232));
		adminPwdLabel.setBounds(20, 200, 100, 50);
		adminLoginPanel.add(adminPwdLabel);
		
		JTextField adminPwdField = new JTextField(40);
		adminPwdField.setBounds(80, 210, 200, 30);
		adminLoginPanel.add(adminPwdField);
		
		JButton adminLoginBtn = new JButton("Log in");
		adminLoginBtn.setBorderPainted(false);
		adminLoginBtn.setFocusPainted(false);
		adminLoginBtn.setBackground(Color.WHITE);
		adminLoginBtn.setForeground(new Color(48, 87, 232));
		adminLoginBtn.setFont(new Font("맑은고딕", Font.BOLD, 18));
		adminLoginBtn.setBounds(30, 300, 250, 40);
		adminLoginPanel.add(adminLoginBtn);
		
		// About prevBtn
		JButton prevBtn = new JButton("prev");
		prevBtn.setBackground(Color.WHITE);
		prevBtn.setForeground(new Color(48, 87, 232));
		prevBtn.setFont(new Font("맑은고딕", Font.BOLD, 18));
		prevBtn.setFocusPainted(false);
		prevBtn.setBorderPainted(false);
		prevBtn.setBounds(150, 620, 150, 50);
		
		// if you click this button, go to roomViewWindow
		prevBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				MainGUI.changeWindow(MainGUI.roomViewWindow);
			}
		});
		
		add(prevBtn);
	}
}