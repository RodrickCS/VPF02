package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class ManutencaoDAO {

	BufferedReader br;
	BufferedWriter bw;
	private String path = "F:\\SENAI\\WorkSpace\\VPF02\\dados\\Manutencoes.csv";

	public ArrayList<Manutencao> ler() {
		ArrayList<Manutencao> linhas = new ArrayList<>();
		Manutencao m;

		try {
			br = new BufferedReader(new FileReader(path));
			String linha = br.readLine();
			while (linha != null) {
				m = new Manutencao(linha);
				linhas.add(m);
				linha = br.readLine();
			}
			br.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return linhas;
	}

	public void escrever(ArrayList<Manutencao> linhas) {
		try {
			bw = new BufferedWriter(new FileWriter(path));
			for (Manutencao m : linhas) {
				bw.write(m.toCSV());
			}
			bw.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
