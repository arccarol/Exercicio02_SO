package controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;


public class KillController {
		
		public KillController() {
			super();
		}
		// Nome do sistema operacional
		public String os() {
			String os = System.getProperty("os.name");
			String arch = System.getProperty("os.arch");
			String version = System.getProperty("os.version");
			return os + " - v." + version + "- arch." + arch;
		}
		
	//para matar processo	
		
		public void callProcess(String process) {
			try {
				Runtime.getRuntime().exec(process);
			}catch (Exception e) {
				String msgErro = e.getMessage();
				System.err.println(msgErro);
				if(msgErro.contains("740")){
					StringBuffer buffer = new StringBuffer();
					buffer.append("cmd / c");
					buffer.append(" ");
					buffer.append(process);
					try {
						Runtime.getRuntime().exec(buffer.toString());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					}
				else {
					System.err.println(msgErro);
				
			}
			}
		}
		//lista de processos
		public void readProcess() {
			if(os().contains("Windows")){
			try {
				Process p = Runtime.getRuntime().exec("TASKLIST /FO TABLE");
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				while (linha != null) {
					System.out.println(linha);
					linha = buffer.readLine();
				}
				
				buffer.close();
				leitor.close();
				fluxo.close();
			}catch (IOException e) {
				e.printStackTrace();
				
			}
			} else {
				if(os().contains("Linux")){
					try {
						Process p = Runtime.getRuntime().exec("ps -ef");
						InputStream fluxo = p.getInputStream();
						InputStreamReader leitor = new InputStreamReader(fluxo);
						BufferedReader buffer = new BufferedReader(leitor);
						String linha = buffer.readLine();
						while (linha != null) {
							System.out.println(linha);
							linha = buffer.readLine();
						}
						
						buffer.close();
						leitor.close();
						fluxo.close();
					}catch (IOException e) {
						e.printStackTrace();
						
					}
				}
			}
			}
		
		public void KillProcess(String param, String os) {
		
			
			 
			if(os.contains("Windows")){
			String cmdPid = "TASKKILL /PID";
			String cmdNome = "TASKKILL /IM";
			int pid = 0;
			StringBuffer buffer = new StringBuffer();
			
			try {
				pid = Integer.parseInt(param);
				buffer.append(cmdPid);
				buffer.append(" ");
				buffer.append(pid);	
			} catch (NumberFormatException e) {
				buffer.append(cmdNome);
				buffer.append(" ");
				buffer.append(param);
			}
			callProcess(buffer.toString());
		}
			else {
				if(os.contains("Linux")){
					String cmdPid = "kill -9";
					String cmdNome = "pkill -f";
					int pid = 0;
					StringBuffer buffer = new StringBuffer();
					
					try {
						pid = Integer.parseInt(param);
						buffer.append(cmdPid);
						buffer.append(" ");
						buffer.append(pid);	
					} catch (NumberFormatException e) {
						buffer.append(cmdNome);
						buffer.append(" ");
						buffer.append(param);
					}
					callProcess(buffer.toString());
				
			}
}
		
		}
}

