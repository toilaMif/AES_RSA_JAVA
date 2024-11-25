import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class frmRSA extends JFrame implements ActionListener {

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
	private JPanel jpTaoKhoa;
	private JButton jbtXoaKhoa;
	private JButton jbtTaokhoa;
	private JPanel jpkhoanut;
	private JLabel jln;
	private JTextField jtfN;
	private JButton jbCpn;
	private JPanel jpCenkhoa;
	private JLabel jlkhoaCK;
	private JTextField jtfkhoaCK;
	private JButton jbCpkhoaCK;
	private JLabel jlkhoaBM;
	private JTextField jtfkhoaBM;
	private JButton jbCpkhoaBM;
	private JPanel jpTaoKhoa1;
	private JLabel jlp;
	private JTextField jtfp;
	private JLabel jlq;
	private JTextField jtfq;
	private JLabel jlphi;
	private JTextField jtfphi;

	public frmRSA() {

		super("RSA");
		setSize(1200, 650);
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
		jlTitle = new JLabel("MÃ HÓA RSA");
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
		jlTitle1 = new JLabel("GIẢI MÃ RSA");
		jlTitle1.setForeground(Color.blue);
		jlTitle1.setFont(fn);
		jpTitle1.add(jlTitle1);
		jpTitle1.setBorder(BorderFactory.createLineBorder(Color.blue, 2));

		jpGiaihoa = new JPanel();
		jpGiaihoa.setLayout(null);
		jpGiaihoa.setBounds(10, 350, 390, 200);
		jpGiaihoa.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
		jpkqGiaihoa = new JPanel();
		jpkqGiaihoa.setLayout(null);
		jpkqGiaihoa.setBounds(410, 350, 370, 200);
		jpkqGiaihoa.setBorder(BorderFactory.createLineBorder(Color.blue, 1));

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

		jpTaoKhoa = new JPanel();
		jpTaoKhoa.setLayout(new BorderLayout());
		jpTaoKhoa.setBorder(BorderFactory.createTitledBorder("Tạo khóa"));
		jpTaoKhoa.setBounds(800, 110, 370, 390);

		Font fn3 = new Font("Arial", Font.BOLD, 10);

		jln = new JLabel("n:        ");
		jln.setFont(fn3);

		jtfN = new JTextField();
		jtfN.setEditable(false);

		jbCpn = new JButton("Copy");

		jlkhoaCK = new JLabel("Khóa Công Khai(n,e): ");
		jlkhoaCK.setFont(fn3);

		jtfkhoaCK = new JTextField();
		jtfkhoaCK.setEditable(false);

		jbCpkhoaCK = new JButton("Copy");

		jlkhoaBM = new JLabel("Khóa Bí Mật(n,d):        ");
		jlkhoaBM.setFont(fn3);

		jtfkhoaBM = new JTextField();
		jtfkhoaBM.setEditable(false);

		jbCpkhoaBM = new JButton("Copy");

		jlp = new JLabel("p:        ");
		jlp.setFont(fn3);

		jtfp = new JTextField();
		jtfp.setEditable(false);

		jlq = new JLabel("q:        ");
		jlq.setFont(fn3);

		jtfq = new JTextField();
		jtfq.setEditable(false);

		jlphi = new JLabel("phi:     ");
		jlphi.setFont(fn3);

		jtfphi = new JTextField();
		jtfphi.setEditable(false);

		jpCenkhoa = new JPanel(new GridLayout(5, 1, 5, 5));

		Box b1, b2, b3, b4, b5;
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		b5 = Box.createHorizontalBox();

		b1.add(jln);
		b1.add(jtfN);
		b1.add(Box.createHorizontalStrut(5));
		b1.add(jbCpn);

		b2.add(jlkhoaCK);
		b2.add(jtfkhoaCK);
		b2.add(Box.createHorizontalStrut(5));
		b2.add(jbCpkhoaCK);

		b3.add(jlkhoaBM);
		b3.add(jtfkhoaBM);
		b3.add(Box.createHorizontalStrut(5));
		b3.add(jbCpkhoaBM);

		b4.add(jlp);
		b4.add(jtfp);
		b4.add(jlq);
		b4.add(jtfq);

		b5.add(jlphi);
		b5.add(jtfphi);

		jpCenkhoa.add(b4);
		jpCenkhoa.add(b5);
		jpCenkhoa.add(b1);
		jpCenkhoa.add(b2);
		jpCenkhoa.add(b3);

		jpkhoanut = new JPanel();

		jbtTaokhoa = new JButton("Tạo khóa");

		jbtXoaKhoa = new JButton("Xóa Khóa");

		jpkhoanut.add(jbtTaokhoa);
		jpkhoanut.add(jbtXoaKhoa);

		jpTaoKhoa.add(jpkhoanut, BorderLayout.SOUTH);
		jpTaoKhoa.add(jpCenkhoa, BorderLayout.CENTER);

		jcen.add(jpTaoKhoa);
		jcen.add(jbThoat);
//		jcen.add(jbLuu);

		jbtTaokhoa.addActionListener(this);
		jbtXoaKhoa.addActionListener(this);
		jbCpn.addActionListener(this);
		jbCpkhoaCK.addActionListener(this);
		jbCpkhoaBM.addActionListener(this);
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
		} else if (o.equals(jbCpn)) {
			String text = jtfN.getText();
			StringSelection stringSelection = new StringSelection(text);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		} else if (o.equals(jbCpkhoaBM)) {
			String text = jtfkhoaBM.getText();
			StringSelection stringSelection = new StringSelection(text);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		} else if (o.equals(jbCpkhoaCK)) {
			String text = jtfkhoaCK.getText();
			StringSelection stringSelection = new StringSelection(text);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		} else if (o.equals(jbtTaokhoa)) {
			taokhoa();
		} else if (o.equals(jbtXoaKhoa)) {
			jtfN.setText("");
			jtfkhoaCK.setText("");
			jtfkhoaBM.setText("");

			jtfN.setText("");
			jtfphi.setText("");
			jtfp.setText("");
			jtfq.setText("");

		}

	}

	void taokhoa() {
		TaoKhoa tknew = new TaoKhoa();
		tknew.Taokhoanew();

		jtfN.setText(tknew.n + "");
		jtfphi.setText(tknew.phi + "");
		jtfp.setText(tknew.p + "");
		jtfq.setText(tknew.q + "");
		jtfkhoaCK.setText(tknew.khoaPublic[0] + " " + tknew.khoaPublic[1]);
//		jtfkhoaBM.setText(tknew.khoaPrivate[0] + " " + tknew.khoaPrivate[1]);
		System.out.println("Khóa bí mật: " + tknew.khoaPrivate[0] + " " + tknew.khoaPrivate[1]);

	}

	void maHoa() {
		TaoKhoa tknew = new TaoKhoa();
		String ChuoiMH = jtfChuoi.getText();
		String khoa = jcbKhoa.getSelectedItem().toString();
		
		String[] parts = khoa.split("\\s+");
        int[] numbers = new int[parts.length];
        
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }
        int n = numbers[0];
        int e = numbers[1];
        
       
		
		int[] ciphertext = tknew.MaHoaRSA(n, e, ChuoiMH);
		String chuoiDaMaHoa ="";
		for (int c : ciphertext) {
			chuoiDaMaHoa += c+" "; 
		}

		jtfKqmahoa.setText(chuoiDaMaHoa);
		System.out.println(chuoiDaMaHoa);

	}

	void giaiMA() {
		TaoKhoa tknew = new TaoKhoa();
		String ChuoiGM = jtfChuoigm.getText();
		String khoa = jcbKhoagm.getSelectedItem().toString();

		
		// tách chuỗi mã hóa
		String[] DSChuoiGM = ChuoiGM.split("\\s+");
        int[] MA = new int[DSChuoiGM.length];
        
        for (int i = 0; i < DSChuoiGM.length; i++) {
        	MA[i] = Integer.parseInt(DSChuoiGM[i]);
        }
		
		//tách khóa
		String[] parts = khoa.split("\\s+");
        int[] numbers = new int[parts.length];
        
        for (int i = 0; i < parts.length; i++) {
            numbers[i] = Integer.parseInt(parts[i]);
        }
        int n = numbers[0];
        int d = numbers[1];
		
		String chuoiDaGM = tknew.GiaiMaRSA(n, d, MA);
		jtfKqmahoagm.setText(chuoiDaGM);
		System.out.println(chuoiDaGM);
	}

	

}
