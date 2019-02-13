package kr.co.sist.log.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import kr.co.sist.log.evt.SistGetLoginEvt;

@SuppressWarnings("serial")
public class SistGetLogin extends JFrame {
	private JTextField jtfId;
	private JPasswordField jpfPasswd;
	private RoundedButton rbLogin;
	
	public JFrame jfBack;
	
	public SistGetLogin() {
		jfBack = new JFrame("SGL LOGIN");
		
		ImageIcon logo = 
				new ImageIcon("C:\\dev\\workspace\\sistgetlog_prj\\src"
						+ "\\kr\\co\\sist\\log\\images\\Login_Logo_SiSt.png");
		
		JLabel jllogo = new JLabel(logo); 
		
		jtfId = new JTextField();
		jpfPasswd = new JPasswordField();
		rbLogin = new RoundedButton("LOGIN");
		
		Font font = null;
		
		try {
			jfBack.setContentPane(new JLabel(
					new ImageIcon(ImageIO.read(new File("C:\\dev\\workspace\\sistgetlog_prj\\src"
							+ "\\kr\\co\\sist\\log\\images\\Login_Background_apeach.png")))));
			
			font = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\dev\\workspace\\sistgetlog_prj\\src"
					+ "\\kr\\co\\sist\\log\\images\\NanumSquareRoundB.ttf")).deriveFont(16f);
		} catch (FontFormatException ffe) {
			ffe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		setLayout(null);
		
		jfBack.add(jllogo);
		jfBack.add(jtfId);
		jfBack.add(jpfPasswd);
		jfBack.add(rbLogin);
		
		jllogo.setBounds(75,13,220,98);
		
		jtfId.setBounds(75,120,220,45);
		jtfId.setFont(font);
		
		jpfPasswd.setBounds(75,190,220,45);
		
		rbLogin.setBounds(135,260,100,35);
		rbLogin.setBackground(new Color(0x4491D4));
		rbLogin.setForeground(new Color(0xFFFFFF));
		rbLogin.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		SistGetLoginEvt sgle = new SistGetLoginEvt(this);
		
		jtfId.addActionListener(sgle);
		
		jpfPasswd.addKeyListener(sgle);
		jpfPasswd.addActionListener(sgle);
		
		rbLogin.addActionListener(sgle);
		
		jfBack.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jfBack.setSize(300, 520);
		jfBack.setLocationRelativeTo(null);
		jfBack.setResizable(false);
		jfBack.pack();
		jfBack.setVisible(true);
	} // SistGetLogin
	
	public class RoundedButton extends JButton {
		public RoundedButton() {
			super();
			decorate();
		}
		
		public RoundedButton(String text) {
			super(text);
			decorate();
		}
		
		protected void decorate() {
			setBorderPainted(false);
			setOpaque(false);
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			Dimension arcs = new Dimension(15, 15);
			
			int width = getWidth();
			int height = getHeight();
			
			Graphics2D graphics = (Graphics2D) g;
					
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			
			graphics.setColor(getBackground());
			graphics.fillRoundRect(0, 0, width, height, 20, 20);
			graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
			
			FontMetrics fmFont = graphics.getFontMetrics();
			Rectangle stringBounds = fmFont.getStringBounds(this.getText(), graphics).getBounds();
			
			int textX = (width - stringBounds.width) / 2;
			int textY = (height - stringBounds.height) / 2 + fmFont.getAscent();
			
			graphics.setColor(getForeground());
			graphics.drawString(getText(), textX, textY);
			graphics.dispose();
		}
	} // RoundedButton
	
	public JFrame getJfBack() {
		return jfBack;
	}
	
	public JTextField getJtfId() {
		return jtfId;
	}

	public JPasswordField getJpfPasswd() {
		return jpfPasswd;
	}

	public RoundedButton getRbLogin() {
		return rbLogin;
	}
	
} // class
