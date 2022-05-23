package controle;

import java.util.ArrayList;


import modelo.Manutencao;
import modelo.ManutencaoDAO;

public class ProcessaManutencoes {
	
	public static ArrayList<Manutencao> man = new ArrayList<>();
	private static ManutencaoDAO md = new ManutencaoDAO();

	public static void abrir() {
		man = md.ler();

	}

	public static void salvar() {
		md.escrever(man);

	}

}
