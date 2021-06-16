package jdbc.method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.hikari.HikariCP;

// �������� ������ ���� �� �ٷ� ����
public class DropSequenceReceiptInfo {
	private String sql = "DROP SEQUENCE receipt_id_seq";

	public DropSequenceReceiptInfo() {
		try (
				Connection conn = HikariCP.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
			pstmt.executeUpdate();
			new CreateSequenceReceiptInfo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}