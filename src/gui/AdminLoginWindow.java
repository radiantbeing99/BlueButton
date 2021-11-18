package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import boardgamecafe.Admin;
import boardgamecafe.Administrator;
import boardgamecafe.BoardGameCafe;
import mgr.Manageable;

public class AdminLoginWindow extends Template{
	private static final long serialVersionUID = 1L;
	private static Administrator adm = new Administrator();
	@Override
	void addComponents() {
		JPanel adminLoginPanel = new BasicPanel();
		setLayout(null);
		adminLoginWindow(adminLoginPanel);
		add(adminLoginPanel);
	}
	
	private void adminLoginWindow(JPanel adminLoginPanel) {
		
		// About loginPanel
		adminLoginPanel.setLayout(null);
		adminLoginPanel.setBounds(480, 90, 320, 400);

		BasicLabel adminLabel = new BasicLabel("관리자 로그인");
		adminLabel.setForeground(Color.WHITE);
		adminLabel.setFontAttribute(20, true);
		adminLabel.setBounds(100, 40, 150, 40);
		adminLoginPanel.add(adminLabel);

		BasicLabel adminIdLabel = new BasicLabel("ID");
		adminIdLabel.setFontAttribute(15);
		adminIdLabel.setBounds(25, 120, 70, 53);
		adminLoginPanel.add(adminIdLabel);

		JTextField adminIdField = new JTextField(60);
		adminIdField.setBounds(105, 130, 190, 33);
		adminLoginPanel.add(adminIdField);

		BasicLabel adminPwdLabel = new BasicLabel("PW");
		adminPwdLabel.setFontAttribute( 15);
		adminPwdLabel.setBounds(25, 227, 70, 53);
		adminLoginPanel.add(adminPwdLabel);
		
		JTextField adminPwdField = new JTextField(40);
		adminPwdField.setBounds(105, 237, 190, 33);
		adminLoginPanel.add(adminPwdField);

		BasicButton adminLoginBtn = new BasicButton("로그인");
		adminLoginBtn.setFontAttribute( 18);
		adminLoginBtn.setBounds(25, 320, 270, 40);
		adminLoginPanel.add(adminLoginBtn);

		adminLoginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String adminId = adminIdField.getText();
				String adminPwd = adminPwdField.getText();
				
				for(Manageable ad : BoardGameCafe.adminMgr.getList()) {
					Admin admin = (Admin)ad;
					if(admin.id.equals(adminId) && admin.pwd.equals(adminPwd)) {
						JOptionPane.showMessageDialog(null, "로그인 성공!");
						adm.run();
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "로그인 실패!");
			}
		});
		
		// About prevBtn
		BasicButton prevBtn = new BasicButton("이전");
		prevBtn.setFontAttribute( 18);
		prevBtn.setBounds(160,580,320,40);
		
		// if you click this button, go to roomViewWindow.
		prevBtn.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				MainGUI.changeWindow(MainGUI.roomViewWindow);
			}
		});
		
		add(prevBtn);
	}
}
