package view;

import javax.swing.JOptionPane;

import controller.KillController;

public class Main {
	
	public static void main(String args[]) {
		
		 KillController KController = new  KillController ();
		
		int opc = 0;
		do {
		opc = Integer.parseInt(JOptionPane.showInputDialog("Menu Principal\n\n 1- Mostrar processos Ativos\n 2 - Matar processo\n 9 - fim"));
		switch(opc) {
	
		case 1: 
			KController.readProcess();
			break;
		case 2:
			String param = JOptionPane.showInputDialog("Digite a chamada por nome ou pid");
			KController.KillProcess(param, KController.os());
			break;
		case 9: 
			JOptionPane.showInputDialog(null, "FIM");
			break;
			default:
				JOptionPane.showInputDialog(null, "Opção Invalida!, digite 1,2 ou 9");		
		}
		}
		while(opc!= 9);
	}
}

			


