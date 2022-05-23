package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.Objects;

public class Manutencao {

	private int id;
	private Date date;
	private String equipamento;
	private Double custoHora;
	private Double tempoGasto;

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Manutencao(int id) {
		this.id = id;
	}

	public Manutencao(int id, Date date, String equipamento, Double custoHora, Double tempoGasto) {
		this.id = id;
		this.date = date;
		this.equipamento = equipamento;
		this.custoHora = custoHora;
		this.tempoGasto = tempoGasto;
	}

	public Manutencao(String linha) {
		try {
			this.date = sdf.parse(linha.split(";")[0]);
		} catch (ParseException e) {
			System.out.println(e.toString());
		}
		this.equipamento = linha.split(";")[1];
		this.custoHora = Double.parseDouble(linha.split(";")[2]);
		this.tempoGasto = Double.parseDouble(linha.split(";")[3]);
	}

	

	public int getId() {
		return id;
	}

	public String getId(String s) {
		return String.format("%d", id);
	}

	public String getDate(String s) {
		return sdf.format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	public Double getCustoHora() {
		return custoHora;
	}

	public void setCustoHora(Double custoHora) {
		this.custoHora = custoHora;
	}

	public Double getTempoGasto() {
		return tempoGasto;
	}

	public void setTempoGasto(Double tempoGasto) {
		this.tempoGasto = tempoGasto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manutencao other = (Manutencao) obj;
		return id == other.id;
	}

	public Double getTotal() {

		Double total = tempoGasto * custoHora;

		return total;
	}

	@Override
	public String toString() {
		return id + "\t" + date + "\t" + equipamento + "\t" + String.format("%.2f", custoHora) + "\t" + tempoGasto
				+ "\t" + getTotal();

	}

	public String toCSV() {
		return id + ";" + date + ";" + equipamento + ";" + String.format("%.2f", custoHora) + ";" + tempoGasto + ";"
				+ getTotal();
	}
}
