package swing.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class ChoosePageFrame extends DefaultFrame {
	
	public ChoosePageFrame() {
		setTitle("Choose Page");
		setBackground(new Color(188, 218, 186));
		
		String[] btns_name = {"<HTML>직원<br>관리</HTML>", "<HTML>판매<br>등록</HTML>", "<HTML>매출<br>조회</HTML>", "<HTML>재고<br>관리</HTML>"};
		ArrayList<JButton> btns = new ArrayList<>();
		
		for (int i = 0; i < btns_name.length; i++) {
			btns.add(new JButton(btns_name[i]));
		}
		
		for (int i = 0; i < btns_name.length; i++) {
			btns.get(i).setBounds(250 * (i + 1), 300, 230, 300);
			btns.get(i).setFont(new Font("맑은 고딕", Font.BOLD|Font.ITALIC, 50));
			btns.get(i).setForeground(Color.WHITE);
			btns.get(i).setBackground(new Color(53, 84, 0));
			btns.get(i).setBackground(new Color(53, 84, 0));
			btns.get(i).addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (e.getActionCommand().equals(btns_name[0])) {
						System.out.println("직원 관리 버튼");
					} else if (e.getActionCommand().equals(btns_name[1])) {
						System.out.println("판매 등록 버튼");
					} else {
						System.out.println("매출 조회 버튼");
					}
				}
			});
			
			add(btns.get(i));
		}
		this.repaint();
		// repaint() : AWT안에 구현되어있기 때문에 Frame을 상속받으면 바로 사용 가능
		// 추가된 컴포넌트들을 다시 제대로 업데이트해준다. 
		// (안해주면 프레임에 마우스를 올려야지 버튼이 뜸, setvisible(true)도 같은 역할을 해주는데 여기서는 안돼서 repaint로 함)
	}
	
	public static void main(String[] args) {
		new ChoosePageFrame();
	}
}
