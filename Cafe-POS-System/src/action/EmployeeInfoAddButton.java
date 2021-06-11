package action;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;

import jdbc.hikari.HikariCP;

public class EmployeeInfoAddButton implements ActionListener {
	
	private String sql = "INSERT INTO employees_table VALUES(employee_id_seq.nextval, ?, ?, ?)";
	private String employee_name;
	private String employee_pw;
	private String employee_grade;
	private ArrayList<TextField> fields = new ArrayList<>();
	private JComboBox<String> grade_box;
	
	public EmployeeInfoAddButton(ArrayList<TextField> fields, JComboBox<String> grade_box) {
		this.fields = fields;
		this.grade_box = grade_box;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			this.employee_name = fields.get(0).getText();
			this.employee_pw = fields.get(1).getText();
			this.employee_grade = (String)grade_box.getSelectedItem();
			
			pstmt.setString(1, employee_name);
			pstmt.setString(2, employee_pw);
			pstmt.setString(3, employee_grade);
			
			pstmt.executeQuery();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

}
