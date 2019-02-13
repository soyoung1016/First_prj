package kr.co.sist.log.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import kr.co.sist.log.evt.SistGetLogMainEvt;

@SuppressWarnings("serial")
public class SistGetLogMain extends JFrame {
	private JButton jbView;
	private JButton jbReport;
	
	private JTextArea jtaInstrouction;
	
	public SistGetLogMain() {
		super("SGL MAIN");
		
		try {
			setContentPane(new JLabel(
					new ImageIcon(ImageIO.read(new File("C:\\dev\\workspace\\sistgetlog_prj\\src"
							+ "\\kr\\co\\sist\\log\\images\\Main_Background_ryan.png")))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ImageIcon iconLoad = new ImageIcon("C:\\dev\\workspace\\sistgetlog_prj\\src"
				+ "\\kr\\co\\sist\\log\\images\\Main_View_default.png");
		ImageIcon iconLoadT = new ImageIcon("C:\\dev\\workspace\\sistgetlog_prj\\src"
				+ "\\kr\\co\\sist\\log\\images\\Main_View_mouse.png");
		ImageIcon iconReport = new ImageIcon("C:\\dev\\workspace\\sistgetlog_prj\\src"
				+ "\\kr\\co\\sist\\log\\images\\Main_Save_default.png");
		ImageIcon iconReportT = new ImageIcon("C:\\dev\\workspace\\sistgetlog_prj\\src"
				+ "\\kr\\co\\sist\\log\\images\\Main_Save_mouse.png");
		ImageIcon iconLogoMain = new ImageIcon("C:\\dev\\workspace\\sistgetlog_prj\\src"
				+ "\\kr\\co\\sist\\log\\images\\Main_Logo_SiSt.png");
		
		Font font = null;
		
		try {
		font = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\dev\\workspace\\sistgetlog_prj\\src"
				+ "\\kr\\co\\sist\\log\\images\\NanumSquareRoundB.ttf")).deriveFont(20f);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		jbView = new JButton("", iconLoad);
		jbView.setBorderPainted(false);
		jbView.setContentAreaFilled(false);
		jbView.setRolloverIcon(iconLoadT);
		jbView.setFocusable(false);
		
		jbReport = new JButton("", iconReport);
		jbReport.setBorderPainted(false);
		jbReport.setContentAreaFilled(false);
		jbReport.setRolloverIcon(iconReportT);
		jbReport.setFocusable(false);
		
		JLabel lblLogo = new JLabel(iconLogoMain);
		
		jtaInstrouction = new JTextArea("\n   원하시는 작업을\n   선택해주세요");
		jtaInstrouction.setFont(font);
		jtaInstrouction.setEditable(false);
		jtaInstrouction.setFocusable(false);
		jtaInstrouction.setOpaque(false);
		
		setLayout(new GridLayout(5, 1));
		
		JPanel panelLogo = new JPanel();
		panelLogo.setLayout(new BorderLayout());
		panelLogo.add("Center",lblLogo);
		panelLogo.setOpaque(false);
		
		JPanel panelBtn = new JPanel();
		panelBtn.setLayout(new GridLayout(1, 2));
		panelBtn.setOpaque(false);
		panelBtn.add(jbView);
		panelBtn.add(jbReport);
		
		JPanel panelInsLayout = new JPanel();
		panelInsLayout.setLayout(null);
		panelInsLayout.add(jtaInstrouction);
		
		JPanel panelIns = new JPanel();
		
		panelInsLayout.add(panelIns);
		panelInsLayout.setOpaque(false);
		panelIns.setBounds(panelInsLayout.getX()+30, panelInsLayout.getY(), 224, 96);
		panelIns.setOpaque(false);
		panelIns.setLayout(new BorderLayout());
		panelIns.add("Center",jtaInstrouction);
		
		add(panelLogo);
		add(panelBtn);
		add(panelInsLayout);
		
		SistGetLogMainEvt sglme = new SistGetLogMainEvt(this);
		
		jbView.addActionListener(sglme);
		jbReport.addActionListener(sglme);
		jbView.addMouseListener(sglme);
		jbReport.addMouseListener(sglme);
		
		setSize(300, 520);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
	} // SistGetLogMain
	
	
	public JButton getJbView() {
		return jbView;
	}

	public JButton getJbReport() {
		return jbReport;
	}

	public JTextArea getJtaInstrouction() {
		return jtaInstrouction;
	}
	
} // class