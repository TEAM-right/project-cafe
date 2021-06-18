package swing.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import action.LoginButtonListener;
import action.PasswordTextFieldListener;
import jdbc.method.LoginComboxData;

public class LoginFrame extends DefaultFrame {

	
	JPanel panel, login_panel, test_panel;
	JLabel image_label;
	JScrollPane scrollPane;
	ImageIcon icon;
	JComboBox combox;
	JPasswordField pwf;
	Font font1 = new Font("맑은 고딕", Font.BOLD, 18);
	Font font2 = new Font("맑은 고딕", Font.BOLD, 30);
	JButton login_btn;
	String cbname, password;
	
	 Color y_color = new Color(255, 255, 227);
	 LineBorder line = new LineBorder(new Color(213, 198, 182), 5 ,true);
	static int count = 0;

	public LoginFrame() {
		
		
//		icon = new ImageIcon("./image/커피사진.jpg");
//
//		// ============================================= 배경화면 이미지 삽입
//		JPanel panel = new JPanel() {
//			public void paintComponent(Graphics g) {
//
//				g.drawImage(icon.getImage(), 0, 0, null);
//				setOpaque(false);
//				super.paintComponent(g);
//			}
//		};
		JPanel panel = new JPanel();
		panel.setBackground(y_color);
		
		panel.setBorder(line);
		
		

		scrollPane = new JScrollPane(panel);
		setContentPane(scrollPane);
		panel.setLayout(null);

		// ============================================= 로그인 패널
		login_panel = new RoundedPanel();
		login_panel.setBounds(600, 250, 300, 400);
		//login_panel.setBackground(new Color(223, 208, 192, 230));
		login_panel.setBackground(new Color(173, 204, 120, 230));

		login_panel.setLayout(null);

		panel.add(login_panel);
		
		// ============================================= 이미지 라벨
		icon = new ImageIcon("./image/profile.png");
		
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);
		
		image_label = new JLabel(changeIcon);
		image_label.setBackground(Color.black);
		image_label.setBounds(0, -30, 300, 300);
		
		login_panel.add(image_label);
		// ============================================= 로그인 버튼
		login_btn = new JButton("LOGIN");
		login_btn.setBackground(new Color(0, 0, 0));
		login_btn.setFont(font1);
		login_btn.setBounds(75, 340, 150, 30);
		login_btn.setForeground(Color.white);

		login_panel.add(login_btn);

		// ============================================= 콤보 박스

		combox = new LoginComboxData().getComboBox();

		combox.setBounds(25, 230, 250, 35);
		combox.setFont(font1);

		login_panel.add(combox);

		// ============================================= 텍스트필드
		pwf = new JPasswordField(5);
		pwf.setBounds(25, 280, 250, 35);
		pwf.setFont(font1);

		login_panel.add(pwf);

		// PasswordField 엔터 누르기
		pwf.addActionListener(new PasswordTextFieldListener(combox, cbname, pwf, this));
		// 로그인 버튼 누르기
		login_btn.addActionListener(new LoginButtonListener(combox, pwf, cbname, this));

		setVisible(true);

	} // login _Frame

	public static void main(String args[]) {
		new LoginFrame();
	}

}