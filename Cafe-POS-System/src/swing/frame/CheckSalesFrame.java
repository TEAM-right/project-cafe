package swing.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import action.BackButtonMouseAction;
import action.ChangePageActionForChooseFrame;
import action.CheckSalesSelectButton;
import jdbc.method.TotalComboAddData;
import jdbc.method.TotalInfo;
import swing.method.BackButtonImgScale;
import swing.method.CurrentTimeClock;
import swing.method.LoginEmployeeInfoLabel;

public class CheckSalesFrame extends DefaultFrame {
	private String order_name, grade;
	private JPanel center;
	private JPanel center_top;
	private JPanel top_panel_body;

	public CheckSalesFrame(String grade, String order_name) {
		this.grade = grade;
		this.order_name = order_name;
		setLayout(new BorderLayout());
		setTitle("Check Sales");

		center = new JPanel(new BorderLayout());
		center_top = new JPanel(new GridLayout());

		JLabel sortation_txt = new JLabel("구　　분");
		setJLabelStyle(sortation_txt);

		JComboBox year_combo = new TotalComboAddData().year_getComboBox();
		setJComboBoxStyle(year_combo);

		JComboBox month_combo = new TotalComboAddData().month_getComboBox();
		setJComboBoxStyle(month_combo);

		// 일반 매출 조회
		DefaultTableModel model = new TotalInfo().getTotalInfo();
		JTable jt = new JTable(model);
		model.fireTableDataChanged();
		JScrollPane jscroll = new JScrollPane(jt);

		// JScrollPane테두리 없애기
		jscroll.setBorder(BorderFactory.createEmptyBorder());
		jscroll.getViewport().setBackground(Color.WHITE);
		setJTableStyle(jt);

		// 총 매출 조회
		DefaultTableModel total_model = new TotalInfo().getTotalInfoTotal();
		JTable jt2 = new JTable(total_model);
		total_model.fireTableDataChanged();
		JScrollPane jscroll2 = new JScrollPane(jt2);
		jscroll2.setBorder(BorderFactory.createEmptyBorder());
		setJTableStyle(jt2);
		jt2.setRowHeight(80);
		jt2.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		jt2.setPreferredScrollableViewportSize(new Dimension(200, 80));
		jscroll2.getViewport().setBackground(Color.WHITE);

		JButton select_btn = new JButton("조　　회");
		setJButtonStyle(select_btn); // 기본 버튼 스타일 지정 메소드 호출
		select_btn.addActionListener(
				new CheckSalesSelectButton(this, select_btn, model, total_model, jt, jt2, year_combo, month_combo));
		select_btn.setBackground(new Color(200, 50, 0));

		center_top.add(sortation_txt);
		center_top.add(year_combo);
		center_top.add(month_combo);
		center_top.add(select_btn);

		center.add(center_top, BorderLayout.NORTH);
		center.add(jscroll);

		// -- [CENTER-TOP] --
		top_panel_body = new JPanel(new GridLayout());

		// 뒤로 가기 버튼
//		JButton back_btn = new JButton("<<");
		JButton back_btn = new BackButtonImgScale().getBackBtn();
		back_btn.setPreferredSize(new Dimension(100, 70));
		back_btn.setFont(new Font("궁서", Font.BOLD, 23));
		back_btn.addMouseListener(new BackButtonMouseAction(this, grade, order_name));

		// GridLayout에 맞춘 버튼 글씨 왼쪽 정렬
		back_btn.setHorizontalAlignment(SwingConstants.LEFT);
		back_btn.setBackground(new Color(3, 102, 53));
		back_btn.setForeground(Color.WHITE);

		// 버튼 테두리 없애기
		back_btn.setBorderPainted(false);
		back_btn.addActionListener(new ChangePageActionForChooseFrame(this, grade, order_name));
		top_panel_body.add(back_btn, BorderLayout.WEST);

		// 가운데 시스템시계
		JLabel clock = new CurrentTimeClock().setClock();
		clock.setFont(new Font("맑은 고딕", Font.BOLD, 23));
		clock.setHorizontalAlignment(JLabel.CENTER);
		clock.setOpaque(true);
		clock.setBackground(new Color(3, 102, 53));
		clock.setForeground(Color.WHITE);
		top_panel_body.add(clock, BorderLayout.CENTER);

		// 오른쪽에 로그인한 사람 정보
		JPanel empInfo = new JPanel(new GridLayout(2, 1));
		empInfo.setBackground(new Color(3, 102, 53));

		JLabel label = new JLabel();
		label.setText("* 환영합니다. 현재 로그인 *");
		label.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		label.setBackground(new Color(3, 102, 53));

		JLabel login_name = LoginEmployeeInfoLabel.getLabel();
		login_name.setFont(new Font("맑은 고딕", Font.BOLD, 19));
		login_name.setForeground(Color.WHITE);
		login_name.setHorizontalAlignment(JLabel.CENTER);
		login_name.setOpaque(true);
		login_name.setBackground(new Color(3, 102, 53));

		empInfo.add(label);
		empInfo.add(login_name);

		top_panel_body.add(empInfo, BorderLayout.EAST);

		add(top_panel_body, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(jscroll2, BorderLayout.SOUTH);

		setVisible(true);
	}

	// JLabel 기본 스타일 지정 메서드
	public static void setJLabelStyle(JLabel txt) {
		txt.setPreferredSize(new Dimension(200, 50));
		txt.setOpaque(true); // setBackground 적용하기 위한 선행 설정
		txt.setBackground(new Color(175, 162, 38)); // 백그라운드 색상 정의
		txt.setForeground(Color.WHITE); // 텍스트 색상 정의
		txt.setFont(new Font("맑은 고딕", Font.BOLD, 30)); // 폰트 정의
		txt.setHorizontalAlignment(JLabel.CENTER); // 텍스트 센터 표시 설정
	}

	// JComboBox 기본 스타일 지정 메서드
	public static void setJComboBoxStyle(JComboBox<String> combo) {
		combo.setBackground(Color.LIGHT_GRAY); // 백그라운드 색상 정의
		combo.setForeground(Color.WHITE); // 텍스트 색상 정의
		combo.setFont(new Font("맑은 고딕", Font.BOLD, 20)); // 폰트 정의
	}

	// JButton 기본 스타일 지정 메서드
	public static void setJButtonStyle(JButton btn) {
		btn.setPreferredSize(new Dimension(200, 50));
		btn.setBackground(new Color(255, 224, 140)); // 백그라운드 색상 정의
		btn.setForeground(Color.WHITE); // 텍스트 색상 정의
		btn.setFont(new Font("맑은 고딕", Font.BOLD, 30)); // 폰트 정의
		btn.setHorizontalAlignment(JLabel.CENTER); // 텍스트 센터 표시 설정
	}

	// JTable 기본 스타일 지증 메서드
	public static void setJTableStyle(JTable table) {
		// 테이블 헤더 크기 변경
		JTableHeader header = table.getTableHeader();
		header.setPreferredSize(new Dimension(100, 40));
		header.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		header.setBackground(new Color(0, 66, 56));
		header.setForeground(Color.WHITE);

		// 컬럼 이동 불가
		table.getTableHeader().setReorderingAllowed(false);

		// 셀 글씨 키우기
		table.setFont(new Font("맑은 고딕", Font.PLAIN, 15));

		// 셀 높이 설정
		table.setRowHeight(25);

		// 테이블 컬럼 가운데 정렬
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table.getColumnModel();
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
	}
}
