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
						"�α� �м��� �켱 �������ּ���!", "���� ���� ����", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource().equals(sglm.getJbView())) {
			sglm.getJtaInstrouction().setText("\n   ���Ŀ� �´� �α�������\n   �ҷ��ͼ� �м��մϴ�.");
		}
		
		if(e.getSource().equals(sglm.getJbReport())) {
			sglm.getJtaInstrouction().setText("\n   �м������ �����մϴ�.\n   �̸����Ⱑ ����Ǿ��\n   �մϴ�.");
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource().equals(sglm.getJbView())) {
			sglm.getJtaInstrouction().setText("\n   ���Ͻô� �۾���\n   �������ּ���");
		}
		
		if(e.getSource().equals(sglm.getJbReport())) {
			sglm.getJtaInstrouction().setText("\n   ���Ͻô� �۾���\n   �������ּ���");
		}
	}
	
	public void viewLogFile() {
		FileDialog fdOpen = new FileDialog(sglm, "���� ����", FileDialog.LOAD);
		
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
