package kr.co.sist.log.evt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import kr.co.sist.log.view.GetLogMainView;

public class GetLogMainViewEvt implements ActionListener, KeyListener {
	private GetLogMainView glmv;
	private String filePath, fileName, file;
	private List<String> resList;
	private Map<String, Integer> responseMap;
	private Set<String> responseSet;
	private int minLine, maxLine;
	
	public GetLogMainViewEvt(GetLogMainView glmv, String filePath, String fileName)
			throws FileNotFoundException, IOException {
		this.glmv = glmv;

		this.filePath = filePath;
		this.fileName = fileName;

		file = filePath + fileName;

		resList = new ArrayList<String>();
		
		getMaxUseKeyInfo();

		getBrowseConnInfo();

		successServiceInfo();

		getHugeRequestTime();

		abnormalRequestInfo();
		
		partMaxUseKeyInfo();
		
	} // GetLogMainViewEvt

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == glmv.getjbtSearch()) {
			try {
				String line = JOptionPane.showInputDialog(glmv, "범위를 입력하세요.\n예) 50~100");
				minLine = Integer.parseInt(line.substring(0, line.indexOf("~")));
				maxLine = Integer.parseInt(line.substring(line.indexOf("~") + 1));

				partMaxUseKeyInfo();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} catch (StringIndexOutOfBoundsException sioobe) {
				JOptionPane.showMessageDialog(glmv, 
						"범위를 올바르게 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
			} catch (NullPointerException npe) {
				JOptionPane.showMessageDialog(glmv, 
						"범위를 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
			} catch (IndexOutOfBoundsException ioobe) {
				JOptionPane.showMessageDialog(glmv, 
						"입력 가능한 수를 벗어났습니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(glmv, 
						"숫자를 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
			}
			
			glmv.getJtfSearch().setText("");
			glmv.getJtfSearch().requestFocus();
		}
	} // actionPerformed

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
			if (!glmv.getJtfSearch().getText().equals("")) {
				try {
					String[] line = glmv.getJtfSearch().getText().split("~");
					minLine = Integer.parseInt(line[0]);
					maxLine = Integer.parseInt(line[1]);

					partMaxUseKeyInfo();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} catch (StringIndexOutOfBoundsException sioobe) {
					JOptionPane.showMessageDialog(glmv, 
							"범위를 올바르게 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
				} catch (NullPointerException npe) {
					JOptionPane.showMessageDialog(glmv, 
							"범위를 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
				} catch (IndexOutOfBoundsException ioobe) {
					JOptionPane.showMessageDialog(glmv, 
							"입력 가능한 수를 벗어났습니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(glmv, 
							"숫자를 입력해주세요.", "입력 오류", JOptionPane.ERROR_MESSAGE);
				}
			}

			glmv.getJtfSearch().setText("");
			glmv.getJtfSearch().requestFocus();
		}
	} // keyReleased
	
	public void getMaxUseKeyInfo() throws FileNotFoundException, IOException {
		Map<String, Integer> map = new HashMap<String, Integer>();

		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(file));
			
			String temp = "";
			String keyValue = "";
			
			while ((temp = br.readLine()) != null) {

				if (temp.contains("key=")) {
					keyValue = temp.substring(temp.indexOf("key=") + 4, temp.indexOf("&"));
					
					if (!map.containsKey(keyValue)) {
						map.put(keyValue, 1);
					} else {
						map.put(keyValue, map.get(keyValue) + 1);
					}
				}
			}

			ArrayList<String> list = new ArrayList<String>(map.keySet());
			
			String tempSpace;
			
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < i; j++) {
					if (map.get(list.get(j)) < map.get(list.get(i))) {
						tempSpace = list.get(i);
						list.set(i, list.get(j));
						list.set(j, tempSpace);
					}
				}
			}
			glmv.getJtaView1().setText(list.get(0) + " " + map.get(list.get(0)) + "회");
		} finally {
			if (br != null) { br.close(); }
		}

	} // getMaxUseKeyInfo

	public void getBrowseConnInfo() throws IOException {
		Map<String, Integer> browseMap = new HashMap<>();
		
		Set<String> getSet = new HashSet<String>();

		File file = null;
		
		BufferedReader br = null;
		BufferedReader br2 = null;

		try {
			String key = "";

			file = new File(filePath + fileName);

			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			
			while ((key = br.readLine()) != null) {
				if (key.contains("t][")) {
					int dot1 = key.indexOf("t][") + 3;
					int dot2 = key.indexOf("][2");
					
					getSet.add(key.substring(dot1, dot2));
				}
			}

			br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

			String[] browseKey = { "opera", "firefox", "Safari", "Chrome", "ie" };

			for (int i = 0; i < browseKey.length; i++) {
				browseMap.put(browseKey[i], 0);
			}

			String temp = "";
			int cnt = 0;
			
			while ((temp = br2.readLine()) != null) {
				if (temp.contains("t][")) {
					int dot1 = temp.indexOf("t][") + 3;
					int dot2 = temp.indexOf("][2");
					key = temp.substring(dot1, dot2);
				}
				
				browseMap.put(key, browseMap.get(key) + 1);
				
				cnt++;
			}

			Set<String> browseSet = browseMap.keySet();
			
			Iterator<String> ita = browseSet.iterator();

			Integer value = 0;
			double percent = 0;
			
			while (ita.hasNext()) {
				key = ita.next();
				value = browseMap.get(key);
				
				percent = (double) (value / (double) cnt) * 100;

				glmv.getJtaView2().append(
						key + " - " + value + " (" + Double.parseDouble(
								String.format("%.2f", percent)) + "%) \r\n");
				
				if (key.equals("opera")) {
					glmv.getJpbOpera().setString(key + " " + value + 
							"회 (" + Double.parseDouble(
									String.format("%.2f", percent)) + "%)");
					
					glmv.getJpbOpera().setValue((int)percent * 100);
				}
				
				if (key.equals("firefox")) {
					glmv.getJpbFirefox().setString(key + " " + value + 
							"회 (" + Double.parseDouble(
									String.format("%.2f", percent)) + "%)");
					glmv.getJpbFirefox().setValue((int)percent * 100);
				}
				
				if (key.equals("Safari")) {
					glmv.getJpbSafari().setString(key + " " + value + 
							"회 (" + Double.parseDouble(
									String.format("%.2f", percent)) + "%)");
					glmv.getJpbSafari().setValue((int)percent * 100);
				}
				
				if (key.equals("Chrome")) {
					glmv.getJpbChrome().setString(key + " " + value + 
							"회 (" + Double.parseDouble(
									String.format("%.2f", percent)) + "%)");
					glmv.getJpbChrome().setValue((int)percent * 100);
				}
				
				if (key.equals("ie")) {
					glmv.getJpbIe().setString(key + " " + value + 
							"회 (" + Double.parseDouble(
									String.format("%.2f", percent)) + "%)");
					glmv.getJpbIe().setValue((int)percent * 100);
				}
			}
		} finally {
			if (br != null) { br.close(); }
			if (br2 != null) { br2.close(); }
		}
	} // getBrowseConnInfo

	public void successServiceInfo() throws FileNotFoundException, IOException {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(file));

			responseMap = new HashMap<String, Integer>();

			String[] result = { "200", "403", "404", "500" };

			for (int i = 0; i < result.length; i++) {
				responseMap.put(result[i], 0);
			}

			String temp = "";
			String key = "";

			while ((temp = br.readLine()) != null) {
				key = temp.substring(1, 4);

				responseMap.put(key, responseMap.get(key) + 1);
			}

			responseSet = responseMap.keySet();

			Iterator<String> ita = responseSet.iterator();

			Integer value = 0;

			while (ita.hasNext()) {
				key = ita.next();
				value = responseMap.get(key);

				if (key.equals("200")) {
					glmv.getJtaView3().setText("성공 " + value + "회 \r\n");
				}

				if (key.equals("404")) {
					glmv.getJtaView3().append("실패 " + value + "회");
				}
			}
		} finally {
			if (br != null) { br.close(); }
		}
	} // successServiceInfo

	public void getHugeRequestTime() throws FileNotFoundException, IOException {
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(file));
			
			String readLineTemp = "";
			
			ArrayList<String> timeData = new ArrayList<>();

			while ((readLineTemp = br.readLine()) != null) {
				timeData.add(readLineTemp.substring(
						readLineTemp.lastIndexOf("[") + 12, readLineTemp.lastIndexOf("[") + 14));
			}

			int[] index = new int[24];

			String mostFluentTime = "";
			int mostFluentTimeTime = 0;

			for (int i = 0; i < timeData.size(); i++) {
				index[Integer.parseInt(timeData.get(i))] += 1;
			}

			for (int i = 0; i < index.length; i++) {
				if (mostFluentTimeTime < index[i]) {
					mostFluentTimeTime = index[i];
					mostFluentTime = String.valueOf(i);
				}
			}

			glmv.getJtaView4().setText(mostFluentTime + "시 " + mostFluentTimeTime + "회");
		} finally {
			if (br != null) { br.close(); }
		}
	} // getHugeRequestTime

	public void abnormalRequestInfo() {
		Iterator<String> ita = responseSet.iterator();

		String key = "";
		Integer sum = 0;

		while (ita.hasNext()) {
			key = ita.next();
			sum += responseMap.get(key);
		}

		for (String s : responseSet) {
			if (s.equals("403")) {
				glmv.getJtaView5().setText(responseMap.get(s) + "회 ("
						+ Double.parseDouble(
								String.format("%.2f", (responseMap.get(s) / (double) sum * 100))) + "%)");
			}
		}
	} // abnormalRequestInfo

	public void partMaxUseKeyInfo() throws IOException {
		if (filePath != null) {
			BufferedReader br = null;
			
			List<String> list = null;
			
			String keyValue = "";
			String[] keyValueArr = null;

			try {
				File newFile = new File(file);
				
				
				FileReader fr = new FileReader(newFile);

				Set<String> keyValueSet = new HashSet<String>();
				
				list = new ArrayList<String>();

				br = new BufferedReader(fr);
				
				String data = "";
				
				while ((data = br.readLine()) != null) {
					if (data.contains("key")) {
						keyValue = data.substring(data.indexOf("key") + 4, data.indexOf("&"));
						
						if (keyValue.equals("javascript")) {
							keyValue = "script";
							
							keyValueSet.add(keyValue);
							
							
							list.add(keyValue);
						} else {
							keyValueSet.add(keyValue);
							
							
							list.add(keyValue);
						}
					}
				}

				keyValueArr = new String[keyValueSet.size()];
				
				keyValueSet.toArray(keyValueArr);

				int[] keyCount = new int[keyValueArr.length];
				
				for (int i = minLine; i <= maxLine; i++) {
					for (int j = 0; j < keyValueArr.length; j++) {
						if (list.get(i).equals(keyValueArr[j])) {
							keyCount[j]++;
						}
					}
				}

				int max = keyCount[0];
				int index = 0;

				for (int i = 1; i < keyCount.length; i++) {
					if (keyCount[i] > max) {
						max = keyCount[i];
						index = i;
					}
				}

				if (keyValueArr[index].equals("script")) {
					glmv.getJtaView6().setText("java" + keyValueArr[index] + 
							" " + String.valueOf(max) + "회");
				} else {
					glmv.getJtaView6().setText(keyValueArr[index] + 
							" " + String.valueOf(max) + "회");
				}

			} finally {
				if (br != null) { br.close(); }
			}
		}
	} // partMaxUseKeyInfo

	public void createReport() throws FileNotFoundException, IOException {
		BufferedWriter bw = null;

		try {
			String newPath = "c:/dev/report";

			File newFile = new File(newPath);

			if (!newFile.exists()) {
				newFile.mkdirs();
			}

			newPath = createFileName();

			bw = new BufferedWriter(new FileWriter(newFile + newPath));

			addResultList();

			String[] result = new String[resList.size()];

			resList.toArray(result);

			for (String s : result) {
				bw.write(s + "\r\n");
			}

			bw.flush();

			JOptionPane.showMessageDialog(null, 
					"파일 생성 완료!", "파일 생성 성공", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			if (bw != null) { bw.close(); }
		}
	} // createReport

	public String createFileName() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		return "/report_" + sdf.format(new Date()) + ".dat";
	} // createFileName

	public void addResultList() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		resList.add("-----------------------------------------------------------------");
		resList.add("파일명 (" + fileName + ") log ( 생성된 날짜 " + sdf.format(new Date()) + " )");
		resList.add("-----------------------------------------------------------------");
		resList.add("1. " + glmv.getJtaView1().getText());
		resList.add("2. " + glmv.getJtaView2().getText());
		resList.add("3. " + glmv.getJtaView3().getText());
		resList.add("4. " + glmv.getJtaView4().getText());
		resList.add("5. " + glmv.getJtaView5().getText());
		resList.add("6. " + glmv.getJtaView6().getText());
	} // addResultList

} // class
