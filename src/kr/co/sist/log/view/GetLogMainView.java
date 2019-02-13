package kr.co.sist.log.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import kr.co.sist.log.evt.GetLogMainViewEvt;

@SuppressWarnings("serial")
public class GetLogMainView extends JDialog {
	private SistGetLogMain sglm;
	private GetLogMainViewEvt glmve;
	private JTextArea jtaView1, jtaView2, jtaView3, jtaView4, jtaView5, jtaView6, jtaView2O;
	private JProgressBar jpbOpera, jpbFirefox, jpbSafari, jpbChrome, jpbIe;
	private JLabel jlOpera, jlFirefox, jlSafari, jlChrome, jlIe;
	private JTextField jtfSearch;
	private JButton jbtSearch;

	public GetLogMainView(SistGetLogMain sglm, String filePath, String fileName) {
		super(sglm, "SGL VIEW", true);
		
		this.sglm = sglm;
		
		inlineView();
		
		JPanel jInlinePanelV1 = new JPanel();
		jInlinePanelV1.setLayout(null);
		jInlinePanelV1.setBounds(80, 32, 245, 65);
		
		JPanel jInlinePanelV2 = new JPanel();
		jInlinePanelV2.setLayout(null);
		jInlinePanelV2.setBounds(177, 127, 231, 146);
		
		eyesView();
		
		jInlinePanelV2.add(jlOpera);
		jInlinePanelV2.add(jlFirefox);
		jInlinePanelV2.add(jlSafari);
		jInlinePanelV2.add(jlChrome);
		jInlinePanelV2.add(jlIe);
		
		jInlinePanelV2.add(jpbOpera);
		jInlinePanelV2.add(jpbFirefox);
		jInlinePanelV2.add(jpbSafari);
		jInlinePanelV2.add(jpbChrome);
		jInlinePanelV2.add(jpbIe);
		
		JPanel jInlinePanelV3 = new JPanel();
		jInlinePanelV3.setLayout(null);
		jInlinePanelV3.setBounds(80, 307, 198, 66);
		
		JPanel jInlinePanelV4 = new JPanel();
		jInlinePanelV4.setLayout(null);
		jInlinePanelV4.setBounds(205, 400, 203, 68);
		
		JPanel jInlinePanelV5 = new JPanel();
		jInlinePanelV5.setLayout(null);
		jInlinePanelV5.setBounds(80, 502, 279, 66);
		
		JPanel jInlinePanelV6 = new JPanel();
		jInlinePanelV6.setLayout(null);
		jInlinePanelV6.setBounds(102, 602, 306, 66);
		
		jInlinePanelV1.add(jtaView1);
		jInlinePanelV2.add(jtaView2O);
		jInlinePanelV3.add(jtaView3);
		jInlinePanelV4.add(jtaView4);
		jInlinePanelV5.add(jtaView5);
		jInlinePanelV6.add(jtaView6);
		
		ImageIcon iiOutline = 
				new ImageIcon("C:\\dev\\workspace\\sistgetlog_prj\\src"
						+ "\\kr\\co\\sist\\log\\images\\MainView_Background_view.png");
		
		JPanel jOutlinePanel = new JPanel();
		jOutlinePanel.setLayout(null);
		
		JLabel jlOutline = new JLabel(iiOutline);
		jlOutline.setSize(450, 885);
		
		jOutlinePanel.add(jInlinePanelV1);
		jOutlinePanel.add(jInlinePanelV2);
		jOutlinePanel.add(jInlinePanelV3);
		jOutlinePanel.add(jInlinePanelV4);
		jOutlinePanel.add(jInlinePanelV5);
		jOutlinePanel.add(jInlinePanelV6);
		jOutlinePanel.add(jtfSearch);
		jOutlinePanel.add(jbtSearch);
		jOutlinePanel.add(jlOutline);
		
		add(jOutlinePanel);
		
		try {
			glmve = new GetLogMainViewEvt(this, filePath, fileName);
			
			jbtSearch.addActionListener(glmve);
			jtfSearch.addKeyListener(glmve);
			jtaView6.setText("");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		setSize(450, 915);
		setLocationRelativeTo(sglm);
		setResizable(false);
		setVisible(true);
		
	} // GetLogMainView

	public void inlineView() {
		Color borderColorYellow = new Color(0xFFC61D);
		
		Font font = null;
		
		TitledBorder tBorder1, tBorder2, tBorder3, tBorder4, tBorder5, tBorder6;
		
		tBorder1 = new TitledBorder(new LineBorder(borderColorYellow), "1. 최다 사용 키의 이름과 횟수");
		tBorder1.setTitleFont(new Font("나눔", Font.BOLD, 12));

		tBorder2 = new TitledBorder(new LineBorder(Color.WHITE), "2. 브라우저별 접속 횟수와 비율");
		tBorder2.setTitleFont(new Font("나눔", Font.BOLD, 12));

		tBorder3 = new TitledBorder(new LineBorder(borderColorYellow), "3. 서비스 성공 횟수와 실패 횟수");
		tBorder3.setTitleFont(new Font("나눔", Font.BOLD, 12));

		tBorder4 = new TitledBorder(new LineBorder(Color.WHITE), "4. 요청이 가장 많은 시간");
		tBorder4.setTitleFont(new Font("나눔", Font.BOLD, 12));

		tBorder5 = new TitledBorder(new LineBorder(borderColorYellow), "5. 비정상적인 요청이 발생한 횟수와 비율");
		tBorder5.setTitleFont(new Font("나눔", Font.BOLD, 12));

		tBorder6 = new TitledBorder(new LineBorder(Color.WHITE), "6. 입력 라인의 최다 사용 키의 이름과 횟수");
		tBorder6.setTitleFont(new Font("나눔", Font.BOLD, 12));
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\dev\\workspace\\sistgetlog_prj\\src"
					+ "\\kr\\co\\sist\\log\\images\\NanumSquareRoundB.ttf")).deriveFont(16f);
		} catch (FontFormatException ffe) {
			ffe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		jtaView1 = new JTextArea();
		jtaView1.setBorder(tBorder1);
		jtaView1.setBackground(borderColorYellow);
		jtaView1.setFont(font);
		jtaView1.setEditable(false);
		jtaView1.setBounds(0, 0, 245, 65);
		
		jtaView2 = new JTextArea();
		jtaView2.setEditable(false);
		
		jtaView2O = new JTextArea();
		jtaView2O.setBorder(tBorder2);
		jtaView2O.setBackground(Color.WHITE);
		jtaView2O.setFont(font);
		jtaView2O.setEditable(false);
		jtaView2O.setBounds(0, 0, 231, 146);
		
		jtaView3 = new JTextArea();
		jtaView3.setBorder(tBorder3);
		jtaView3.setBackground(borderColorYellow);
		jtaView3.setFont(font);
		jtaView3.setEditable(false);
		jtaView3.setBounds(0, 0, 198, 66);
		
		jtaView4 = new JTextArea();
		jtaView4.setBorder(tBorder4);
		jtaView4.setBackground(Color.WHITE);
		jtaView4.setFont(font);
		jtaView4.setEditable(false);
		jtaView4.setBounds(0, 0, 203, 68);
		
		jtaView5 = new JTextArea();
		jtaView5.setBorder(tBorder5);
		jtaView5.setBackground(borderColorYellow);
		jtaView5.setFont(font);
		jtaView5.setEditable(false);
		jtaView5.setBounds(0, 0, 279, 66);
		
		jtaView6 = new JTextArea();
		jtaView6.setBorder(tBorder6);
		jtaView6.setBackground(Color.WHITE);
		jtaView6.setFont(font);
		jtaView6.setEditable(false);
		jtaView6.setBounds(0, 0, 306, 66);
		
		jtfSearch = new JTextField();
		jtfSearch.setFont(font);
		jtfSearch.setBorder(BorderFactory.createEmptyBorder());
		jtfSearch.setBounds(11, 785, 350, 64);
		
		jbtSearch = new JButton("검색");
		jbtSearch.setFont(font);
		jbtSearch.setForeground(Color.BLACK);
		jbtSearch.setBackground(borderColorYellow);
		jbtSearch.setBounds(363, 800, 73, 33);		
		jbtSearch.setOpaque(false);
		jbtSearch.setBorderPainted(false);
		jbtSearch.setFocusable(false);
	}
	
	public void eyesView() {
		ImageIcon iiOpera, iiFirefox, iiSafari, iiChrome, iiIe;
		
		iiOpera = 
				new ImageIcon("C:\\dev\\workspace\\sistgetlog_prj\\src"
						+ "\\kr\\co\\sist\\log\\images\\MainView_Icon_opera.png");
		
		iiFirefox = 
				new ImageIcon("C:\\dev\\workspace\\sistgetlog_prj\\src"
						+ "\\kr\\co\\sist\\log\\images\\MainView_Icon_firefox.png");
		
		iiSafari = 
				new ImageIcon("C:\\dev\\workspace\\sistgetlog_prj\\src"
						+ "\\kr\\co\\sist\\log\\images\\MainView_Icon_Safari.png");
		
		iiChrome = 
				new ImageIcon("C:\\dev\\workspace\\sistgetlog_prj\\src"
						+ "\\kr\\co\\sist\\log\\images\\MainView_Icon_Chrome.png");
		
		iiIe = 
				new ImageIcon("C:\\dev\\workspace\\sistgetlog_prj\\src"
						+ "\\kr\\co\\sist\\log\\images\\MainView_Icon_ie.png");
		
		jlOpera = new JLabel(iiOpera);
		jlOpera.setBounds(5, 20, 20, 20);
		
		jlFirefox = new JLabel(iiFirefox);
		jlFirefox.setBounds(5, 45, 20, 20);

		jlSafari = new JLabel(iiSafari);
		jlSafari.setBounds(5, 70, 20, 20);
		
		jlChrome = new JLabel(iiChrome);
		jlChrome.setBounds(5, 95, 20, 20);
		
		jlIe = new JLabel(iiIe);
		jlIe.setBounds(5, 120, 20, 20);
		
		jpbOpera = new JProgressBar(0, 10000);
		jpbOpera.setBounds(32, 20, 165, 20);
		jpbOpera.setStringPainted(true);
		
		jpbFirefox = new JProgressBar(0, 10000);
		jpbFirefox.setBounds(32, 45, 165, 20);
		jpbFirefox.setStringPainted(true);
		
		jpbSafari = new JProgressBar(0, 10000);
		jpbSafari.setBounds(32, 70, 165, 20);
		jpbSafari.setStringPainted(true);
		
		jpbChrome = new JProgressBar(0, 10000);
		jpbChrome.setBounds(32, 95, 165, 20);
		jpbChrome.setStringPainted(true);
		
		jpbIe = new JProgressBar(0, 10000);
		jpbIe.setBounds(32, 120, 165, 20);
		jpbIe.setStringPainted(true);
	}
	
	public void createReport() {
		try {
			glmve.createReport();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public SistGetLogMain getsglm() {
		return sglm;
	}

	public JTextArea getJtaView1() {
		return jtaView1;
	}

	public JTextArea getJtaView2() {
		return jtaView2;
	}

	public JTextArea getJtaView3() {
		return jtaView3;
	}

	public JTextArea getJtaView4() {
		return jtaView4;
	}

	public JTextArea getJtaView5() {
		return jtaView5;
	}

	public JTextArea getJtaView6() {
		return jtaView6;
	}

	public JTextArea getJtaView2O() {
		return jtaView2O;
	}

	public JButton getjbtSearch() {
		return jbtSearch;
	}

	public JProgressBar getJpbOpera() {
		return jpbOpera;
	}

	public JProgressBar getJpbFirefox() {
		return jpbFirefox;
	}

	public JProgressBar getJpbSafari() {
		return jpbSafari;
	}

	public JProgressBar getJpbChrome() {
		return jpbChrome;
	}

	public JProgressBar getJpbIe() {
		return jpbIe;
	}

	public JTextField getJtfSearch() {
		return jtfSearch;
	}
	
} // class
