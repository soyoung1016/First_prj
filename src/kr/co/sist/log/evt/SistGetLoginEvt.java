package kr.co.sist.log.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import kr.co.sist.log.view.SistGetLogMain;
import kr.co.sist.log.view.SistGetLogin;

public class SistGetLoginEvt implements ActionListener, KeyListener {
	private SistGetLogin sgl;

	public SistGetLoginEvt(SistGetLogin sgl) {
		this.sgl = sgl;
	} // SistGetLoginEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == sgl.getRbLogin()) {
			checkLogin();
		}
	}

	public void checkLogin() {
		String pw = "";

		char[] openPw = sgl.getJpfPasswd().getPassword();

		for (char temp : openPw) {
			Character.toString(temp);

			pw += (pw.equals("")) ? "" + temp + "" : "" + temp + "";
		}

		if ((sgl.getJtfId().getText().equals("admin")) && (pw.equals("1234"))) {
				new SistGetLogMain();
				
				sgl.jfBack.dispose();
		} else if ((sgl.getJtfId().getText().equals("root")) && (pw.equals("1111"))) {
				new SistGetLogMain();
				
				sgl.jfBack.dispose();
		} else {
			JOptionPane.showMessageDialog(sgl, "아이디나 비밀번호가 일치하지 않습니다.");
			
			sgl.getJtfId().setText("");
			sgl.getJtfId().requestFocus();
			
			sgl.getJpfPasswd().setText("");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent ke) {
		int inputCode = ke.getKeyCode();

		if (inputCode == KeyEvent.VK_ENTER) {
			checkLogin();
		}
	}

} // class
