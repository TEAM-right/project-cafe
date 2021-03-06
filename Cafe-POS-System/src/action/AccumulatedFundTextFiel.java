package action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import swing.method.RoundJTextField;

//텍스트필드 클릭시 기존에 있던 "전화번호를 입력하세요" 텍스트를 사라지게 만드는 클래스
public class AccumulatedFundTextFiel extends MouseAdapter {
	
	private RoundJTextField textField;
	private String order_name, grade;
	
	public AccumulatedFundTextFiel(RoundJTextField textField, String grade, String order_name) {
		this.grade = grade;
		this.order_name = order_name;
		this.textField = textField;
	}

	public void mouseClicked(MouseEvent e) {
		String fieldDefaultText = "전화번호를 입력하세요";
		
		if (textField.getText().equals(fieldDefaultText)) {
			textField.setText(null);
		} else {
			return;
		}
	}

}
