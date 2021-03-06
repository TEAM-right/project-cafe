package swing.method;

import java.awt.TextArea;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.swing.JTable;

import jdbc.method.DeletereceiptInfo;
import jdbc.method.InsertOrderTable;
import jdbc.method.ReceiptUpNumber;
import jdbc.method.selectReceiptNumber;
import swing.frame.ReceiptDefaultFrame;

public class ReceiptLabel extends ReceiptDefaultFrame {
	
	Random ran;
	TextArea area;
	String order_name, grade;
	int receiptNumber;
	JTable table;
	StringBuilder str_menu;
	int sum;
	
	private SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat f2 = new SimpleDateFormat("yyyyMMdd");
	private String html, totalVat, vat, totalPrice;
	
	public ReceiptLabel(JTable table,String grade, String order_name) {
		GetMenuInfo getmenu = new GetMenuInfo(table, grade, order_name);
		this.grade = grade;
		this.order_name = order_name;
		new ReceiptUpNumber();
		receiptNumber = new selectReceiptNumber().getReceiptNumber();
		ran = new Random();
		sum = getmenu.getSum();
		str_menu = getmenu.getMenuInfo();
		this.table = table;
		totalVat = String.format("%s\t\t\t\t\t\t\t%11d\n", "부가세  과세물품가액", sum);
		vat = String.format("%s\t\t\t\t\t\t%11d\n", "부          가          세 ", (int)(sum * 0.1));
		totalPrice = String.format("%s\t\t\t\t\t\t\t\t%11d\n", "합 계 금 액", sum);
		html = "\t\t\t\t  영　수　증\n"
				+ "[매장명]　커피포레스트강남효성점\n"
				+ "[사업자]　123-45-67890\n"
				+ "[주   소]　서울특별시 강남구 역삼동 826-27 11, 12층\n"
				+ "[대표자]　서예림\t\t\t\t\t[T  E  L] 031-123-4678	\n"
				+ "[매출일]　" + f1.format(Calendar.getInstance().getTime()) + "\t\t\t[결제자] " + order_name + "\n"
				+ "[영수증]　" + f2.format(Calendar.getInstance().getTime()) + "A-" + receiptNumber + "\n"
				+ "=====================================================================\n"
				+ "\t\t상  품  명\t\t단  가\t\t수  량\t\t      금  액\n"
				+ "---------------------------------------------------------------------\n"
				+ str_menu
				+ "---------------------------------------------------------------------\n"
				+ totalPrice
				+ "---------------------------------------------------------------------\n"
				+ totalVat
				+ vat
				+ "---------------------------------------------------------------------\n"
				+ "결  제  방  법 : 일 시 불\n"
				+ "결  제  금  액 : " + sum + "\n"
				+ "승  인  번  호 : " + ran.nextInt(89999999) + 10000000 + "\n"
				+ "결  제  일  시 : " + f1.format(Calendar.getInstance().getTime());
		
		add(new ReceiptTextArea(html));
		new InsertOrderTable(sum);
		
		if (receiptNumber == 99) {
			new DeletereceiptInfo();
		}
	}
}
