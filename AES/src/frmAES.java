import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class frmAES extends JFrame implements ActionListener {

	private MaHoaAES mhaes;
	
	private JPanel jcen;
	private JPanel jpTitle;
	private JLabel jlTitle;
	private JPanel jpMahoa;
	private JPanel jpkqMahoa;
	private JPanel jpTitle1;
	private JLabel jlTitle1;
	private JPanel jpGiaihoa;
	private JPanel jpkqGiaihoa;
	private JButton jbLuu;
	private JButton jbThoat;
	private JLabel jtlChuoi;
	private JTextField jtfChuoi;
	private JLabel jtlKhoa;
	private JButton jbMaHoa;
	private JButton jbXoamahoa;
	private JComboBox<String> jcbKhoa;
	private JLabel jlKqmahoa;
	private JTextField jtfKqmahoa;
	private JButton jbXoakqmahoa;
	private JButton jbCpkqmh;
	private JLabel jtlKhoagm;
	private JComboBox<String> jcbKhoagm;
	private JButton jbGiaima;
	private JButton jbXoagiaima;
	private JLabel jtlChuoigm;
	private JLabel jlKqmahoagm;
	private JTextField jtfKqmahoagm;
	private JButton jbCpkqmhgm;
	private JButton jbXoakqmahoagm;
	private JTextField jtfChuoigm;

	public frmAES() {

		super("AES");
		setSize(800, 650);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		jcen = new JPanel();
		jcen.setLayout(null);
		jcen.setBackground(Color.darkGray);
		add(jcen);

		jpTitle = new JPanel();
		jpTitle.setBounds(300, 5, 200, 40);
		jpTitle.setBackground(Color.white);
		Font fn = new Font("Arial", Font.BOLD | Font.ITALIC, 25);
		jlTitle = new JLabel("MÃ HÓA AES");
		jlTitle.setForeground(Color.black);
		jlTitle.setFont(fn);
		jpTitle.add(jlTitle);
		jpTitle.setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));

		Font fn2 = new Font("Arial", Font.ITALIC, 15);
		jtlChuoi = new JLabel("Chuỗi: ");
		jtlChuoi.setBounds(10, 40, 50, 20);
		jtlChuoi.setFont(fn2);
		jtfChuoi = new JTextField();
		jtfChuoi.setBounds(60, 30, 320, 40);

		jtlKhoa = new JLabel("Khóa: ");
		jtlKhoa.setFont(fn2);
		jtlKhoa.setBounds(10, 100, 50, 20);
		String[] key = ";Nguyen;Thanh;Trung;IUH;Mif".split(";");
		jcbKhoa = new JComboBox<String>(key);
		jcbKhoa.setEditable(true);
		jcbKhoa.setBounds(60, 90, 320, 40);

		jbMaHoa = new JButton("Mã Hóa");
		jbMaHoa.setBounds(100, 150, 100, 30);
		jbXoamahoa = new JButton("Xóa");
		jbXoamahoa.setBounds(230, 150, 100, 30);

		jpMahoa = new JPanel();
		jpMahoa.setLayout(null);
		jpMahoa.setBounds(10, 60, 390, 200);
		jpMahoa.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));

		jpkqMahoa = new JPanel();
		jpkqMahoa.setBounds(410, 60, 370, 200);
		jpkqMahoa.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
		jpkqMahoa.setLayout(null);

		jlKqmahoa = new JLabel("Kết Quả: ");
		jlKqmahoa.setBounds(10, 70, 70, 20);
		jlKqmahoa.setFont(fn2);

		jtfKqmahoa = new JTextField();
		jtfKqmahoa.setEditable(false);
		jtfKqmahoa.setBounds(80, 40, 270, 80);

		jbCpkqmh = new JButton("Copy");
		jbCpkqmh.setBounds(100, 150, 100, 30);
		jbXoakqmahoa = new JButton("Xóa");
		jbXoakqmahoa.setBounds(230, 150, 100, 30);

		jpkqMahoa.add(jlKqmahoa);
		jpkqMahoa.add(jtfKqmahoa);
		jpkqMahoa.add(jbXoakqmahoa);
		jpkqMahoa.add(jbCpkqmh);

		jpMahoa.add(jtlChuoi);
		jpMahoa.add(jtfChuoi);
		jpMahoa.add(jtlKhoa);
		jpMahoa.add(jcbKhoa);
		jpMahoa.add(jbMaHoa);
		jpMahoa.add(jbXoamahoa);

		jpTitle1 = new JPanel();
		jpTitle1.setBounds(300, 290, 200, 40);
		jpTitle1.setBackground(Color.white);
		jlTitle1 = new JLabel("GIẢI MÃ AES");
		jlTitle1.setForeground(Color.red);
		jlTitle1.setFont(fn);
		jpTitle1.add(jlTitle1);
		jpTitle1.setBorder(BorderFactory.createLineBorder(Color.red, 2));

		jpGiaihoa = new JPanel();
		jpGiaihoa.setLayout(null);
		jpGiaihoa.setBounds(10, 350, 390, 200);
		jpGiaihoa.setBorder(BorderFactory.createLineBorder(Color.red, 1));
		jpkqGiaihoa = new JPanel();
		jpkqGiaihoa.setLayout(null);
		jpkqGiaihoa.setBounds(410, 350, 370, 200);
		jpkqGiaihoa.setBorder(BorderFactory.createLineBorder(Color.red, 1));

		jcen.add(jpTitle);
		jcen.add(jpMahoa);
		jcen.add(jpkqMahoa);

		jcen.add(jpTitle1);
		jcen.add(jpGiaihoa);
		jcen.add(jpkqGiaihoa);

		jtlChuoigm = new JLabel("Chuỗi: ");
		jtlChuoigm.setBounds(10, 40, 50, 20);
		jtlChuoigm.setFont(fn2);
		jtfChuoigm = new JTextField();
		jtfChuoigm.setBounds(60, 30, 320, 40);

		jtlKhoagm = new JLabel("Khóa: ");
		jtlKhoagm.setFont(fn2);
		jtlKhoagm.setBounds(10, 100, 50, 20);
		String[] keygm = ";Nguyen;Thanh;Trung;IUH;Mif".split(";");
		jcbKhoagm = new JComboBox<String>(keygm);
		jcbKhoagm.setEditable(true);
		jcbKhoagm.setBounds(60, 90, 320, 40);

		jbGiaima = new JButton("Giải Mã");
		jbGiaima.setBounds(100, 150, 100, 30);
		jbXoagiaima = new JButton("Xóa");
		jbXoagiaima.setBounds(230, 150, 100, 30);

		jpGiaihoa.add(jtlChuoigm);
		jpGiaihoa.add(jtfChuoigm);
		jpGiaihoa.add(jtlKhoagm);
		jpGiaihoa.add(jcbKhoagm);
		jpGiaihoa.add(jbGiaima);
		jpGiaihoa.add(jbXoagiaima);

		jlKqmahoagm = new JLabel("Kết Quả: ");
		jlKqmahoagm.setBounds(10, 70, 70, 20);
		jlKqmahoagm.setFont(fn2);

		jtfKqmahoagm = new JTextField();
		jtfKqmahoagm.setEditable(false);
		jtfKqmahoagm.setBounds(80, 40, 270, 80);

		jbCpkqmhgm = new JButton("Copy");
		jbCpkqmhgm.setBounds(100, 150, 100, 30);
		jbXoakqmahoagm = new JButton("Xóa");
		jbXoakqmahoagm.setBounds(230, 150, 100, 30);

		jpkqGiaihoa.add(jlKqmahoagm);
		jpkqGiaihoa.add(jtfKqmahoagm);
		jpkqGiaihoa.add(jbXoakqmahoagm);
		jpkqGiaihoa.add(jbCpkqmhgm);

		jbThoat = new JButton("Thoát");
		jbThoat.setBounds(350, 570, 100, 30);
		jbThoat.setBackground(Color.white);
		Font fn1 = new Font("Arial", Font.CENTER_BASELINE, 15);
		jbThoat.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		jbThoat.setFont(fn1);
		jbLuu = new JButton("Lưu");
		jbLuu.setBounds(410, 570, 100, 30);
		jbLuu.setBackground(Color.white);
		jbLuu.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		jbLuu.setFont(fn1);

		jcen.add(jbThoat);
//		jcen.add(jbLuu);

		jbLuu.addActionListener(this);
		jbThoat.addActionListener(this);
		jbCpkqmh.addActionListener(this);
		jbCpkqmhgm.addActionListener(this);
		jbGiaima.addActionListener(this);
		jbMaHoa.addActionListener(this);
		jbXoagiaima.addActionListener(this);
		jbXoakqmahoagm.addActionListener(this);
		jbXoamahoa.addActionListener(this);
		jbXoakqmahoa.addActionListener(this);
	}

	private void thoat() {
		setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(jbThoat)) {
			thoat();
		} else if (o.equals(jbMaHoa)) {
			jtfKqmahoa.setText(jtfChuoi.getText());
			maHoa();

		} else if (o.equals(jbXoamahoa)) {
			jcbKhoa.setSelectedIndex(0);
			jtfChuoi.setText("");
		} else if (o.equals(jbCpkqmh)) {
			String text = jtfKqmahoa.getText();
			StringSelection stringSelection = new StringSelection(text);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

		} else if (o.equals(jbXoakqmahoa)) {
			jtfKqmahoa.setText("");
			
		} else if (o.equals(jbGiaima)) {
			giaiMA();

		} else if (o.equals(jbXoagiaima)) {
			jcbKhoagm.setSelectedIndex(0);
			jtfChuoigm.setText("");
		} else if (o.equals(jbCpkqmhgm)) {
			String text = jtfKqmahoagm.getText();
			StringSelection stringSelection = new StringSelection(text);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

		} else if (o.equals(jbXoakqmahoagm)) {
			jtfKqmahoagm.setText("");
		}

	}
	
	void maHoa() {
		String ChuoiMH =jtfChuoi.getText();
		String khoa = jcbKhoa.getSelectedItem().toString();
		
		mhaes = new MaHoaAES();
		
		String cipherText = mhaes.MAHOA(ChuoiMH, khoa);
		jtfKqmahoa.setText(cipherText+"");
		
        
		
       
	}
	void giaiMA() {
		String ChuoiGM =jtfChuoigm.getText();
		String khoa = jcbKhoagm.getSelectedItem().toString();
		
		GiaiMaAES gmAES = new GiaiMaAES();
		
		String cipherText = gmAES.GIAIMA(ChuoiGM, khoa);
		jtfKqmahoagm.setText(hexToString(cipherText));
	}
	
		// chuyển hex sang chử
	    public static String hexToString(String hex) {
	        StringBuilder output = new StringBuilder();
	        for (int i = 0; i < hex.length(); i += 2) {
	            String str = hex.substring(i, i + 2);
	            output.append((char) Integer.parseInt(str, 16));
	        }
	        return output.toString();
	    
	}


}
