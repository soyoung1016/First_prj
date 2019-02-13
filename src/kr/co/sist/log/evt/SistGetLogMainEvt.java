package kr.co.sist.log.evt;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import kr.co.sist.log.view.SistGetLogMain;
import kr.co.sist.log.view.GetLogMainView;

public class SistGetLogMainEvt extends MouseAdapter implements ActionListener, MouseListener {
	private SistGetLogMain sglm;
	private GetLogMainView glmv;
	private boolean flagView;
	
	public SistGetLogMainEvt(SistGetLogMain sglm) {
		this.sglm = sglm;
		
		flagView = false;
	} // SistGetLogMainEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == sglm.getJbView()) {
			viewLogFile();
		}
		
		if (ae.getSource() == sglm.getJbReport()) {
			if (flagView) {
				createReport();
			} else {
				JOptionPane.showMessageDialog(sglm, 
						"로그 분석을 우선 실행해주세요!", "파일 생성 실패", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource().equals(sglm.getJbView())) {
			sglm.getJtaInstrouction().setText("\n   형식에 맞는 로그파일을\n   불러와서 분석합니다.");
		}
		
		if(e.getSource().equals(sglm.getJbReport())) {
			sglm.getJtaInstrouction().setText("\n   분석결과를 저장합니다.\n   미리보기가 선행되어야\n   합니다.");
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource().equals(sglm.getJbView())) {
			sglm.getJtaInstrouction().setText("\n   원하시는 작업을\n   선택해주세요");
		}
		
		if(e.getSource().equals(sglm.getJbReport())) {
			sglm.getJtaInstrouction().setText("\n   원하시는 작업을\n   선택해주세요");
		}
	}
	
	public void viewLogFile() {
		FileDialog fdOpen = new FileDialog(sglm, "파일 선택", FileDialog.LOAD);
		
		fdOpen.setVisible(true);
		
		String filePath = fdOpen.getDirectory();
		String fileName = fdOpen.getFile();
		
		if (fileName != null) {
			if (!flagView) {
				flagView = true;	
			}
			
			glmv = new GetLogMainView(sglm, filePath, fileName);			
		}
	}
	
	public void createReport() {
		glmv.createReport();
	}
	
} // class
