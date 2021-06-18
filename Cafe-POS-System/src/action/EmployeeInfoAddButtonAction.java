package action;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import jdbc.hikari.HikariCP;
import swing.method.RenewalToTable;

public class EmployeeInfoAddButtonAction implements ActionListener {
	
	private String sql = "INSERT INTO employees_table VALUES(?, ?, ?, ?)";
	private String employee_name;
	private String employee_pw;
	private String employee_grade;
	private ArrayList<TextField> fields = new ArrayList<>();
	private JComboBox<String> grade_box;
	private JTable table;
	
	public EmployeeInfoAddButtonAction(ArrayList<TextField> fields, JComboBox<String> grade_box, JTable table) {
		this.fields = fields;
		this.grade_box = grade_box;
		this.table = table;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// �Է¹��� ������ null�� �ƴ� ��� ���� �߰����� �ʵ��� ���� ����
		if (fields.get(0).getText().isEmpty() || fields.get(1).getText().isEmpty()) {
			String check = String.format("�̸��� �н����� �� �Է����� ���� ���� �ֽ��ϴ�.\n�Է� ������ Ȯ�����ּ���.");
			JOptionPane.showMessageDialog(null, check, "Guide Message", JOptionPane.INFORMATION_MESSAGE);
		} else {
			String check = String.format("�� ��: %s\n�н�����: %s\n�� ��: %s\n����Ͻðڽ��ϱ�?", fields.get(0).getText(), fields.get(1).getText(),
					(String)grade_box.getSelectedItem());
			int result = JOptionPane.showConfirmDialog(null, check, "Confirm Message", JOptionPane.YES_NO_OPTION);
			
			if (result == JOptionPane.YES_OPTION) {
				try (
						Connection conn = HikariCP.getConnection();
						PreparedStatement pstmt = conn.prepareStatement(sql);
					) {
					
					SimpleDateFormat f1 = new SimpleDateFormat("yyMM");
					String employee_id = f1.format(Calendar.getInstance().getTime()) + String.valueOf(new Random().nextInt(8999) + 1000);
					this.employee_name = fields.get(0).getText().trim();
					this.employee_pw = fields.get(1).getText().trim();
					this.employee_grade = (String)grade_box.getSelectedItem();
					
					pstmt.setString(1, employee_id);
					pstmt.setString(2, employee_name);
					pstmt.setString(3, employee_pw);
					pstmt.setString(4, employee_grade);
					
					pstmt.executeQuery();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
		
		new RenewalToTable(table);
	}
}