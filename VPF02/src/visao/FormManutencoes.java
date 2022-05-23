package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;

import controle.ProcessaManutencoes;
import modelo.Manutencao;

public class FormManutencoes extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JLabel id, data, equipamento, custo, tempo;
	private JTextField tfId, tfData, tfCusto, tfTempo;
	private JButton btBuscar, btExcluir, btCadastrar, btAlterar;
	private JTextArea listar;
	private JComboBox<String> cbEquipamento;
	private int autoId = ProcessaManutencoes.man.size() + 1;
	String texto = "";

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	FormManutencoes() {

		setTitle("Manutenções");
		setBounds(100, 100, 800, 600);
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 204));
		setContentPane(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		id = new JLabel("id");
		id.setBounds(20, 10, 500, 20);
		panel.add(id);

		data = new JLabel("Data");
		data.setBounds(20, 40, 500, 20);
		panel.add(data);

		equipamento = new JLabel("Equipamento");
		equipamento.setBounds(20, 80, 500, 20);
		panel.add(equipamento);

		custo = new JLabel("Custo");
		custo.setBounds(20, 120, 500, 20);
		panel.add(custo);

		tempo = new JLabel("Tempo");
		tempo.setBounds(20, 160, 500, 20);
		panel.add(tempo);

		listar = new JTextArea();
		listar.setBounds(100, 300, 600, 500);
		listar.setEditable(false);
		listar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		panel.add(listar);
		preencherArea();

		tfId = new JTextField(String.format("%d", autoId));
		tfId.setBounds(100, 10, 200, 20);
		tfId.setEditable(false);
		panel.add(tfId);

		tfData = new JTextField();
		tfData.setBounds(100, 40, 200, 25);
		panel.add(tfData);

		cbEquipamento = new JComboBox<String>(
				new String[] { "Equipamento1", "Equipamento2", "Equipamento3", "Equipamento4" });
		cbEquipamento.setBounds(100, 80, 200, 25);
		panel.add(cbEquipamento);

		tfCusto = new JTextField();
		tfCusto.setBounds(100, 120, 200, 25);
		panel.add(tfCusto);

		tfTempo = new JTextField();
		tfTempo.setBounds(100, 160, 200, 25);
		panel.add(tfTempo);

		btCadastrar = new JButton("Cadastrar");
		btCadastrar.setBounds(400, 50, 100, 20);
		panel.add(btCadastrar);

		btExcluir = new JButton("Excluir");
		btExcluir.setBounds(505, 50, 100, 20);
		panel.add(btExcluir);
		btExcluir.setEnabled(false);

		btBuscar = new JButton("Buscar");
		btBuscar.setBounds(610, 50, 100, 20);
		panel.add(btBuscar);
		btBuscar.setEnabled(false);

		btAlterar = new JButton("Alterar");
		btAlterar.setBounds(505, 80, 100, 20);
		panel.add(btAlterar);
		btAlterar.setEnabled(false);

		btBuscar.addActionListener(this);
		btCadastrar.addActionListener(this);
		btAlterar.addActionListener(this);
		btExcluir.addActionListener(this);

	}

	int obterIdEquipamento(String especie) {
		switch (especie) {
		case "Equipamento1":
			return 0;
		case "Equipamento2":
			return 1;
		case "Equipamento3":
			return 2;
		case "Equipamento4":
			return 3;
		default:
			return -1;
		}
	}

	private void cadastrar() {

		if (tfData.getText().length() != 0 && tfCusto.getText().length() != 0 && tfTempo.getText().length() != 0) {

			try {
				ProcessaManutencoes.man
						.add(new Manutencao(autoId, converterData(), cbEquipamento.getSelectedItem().toString(),
								Double.parseDouble(tfCusto.getText()), Double.parseDouble(tfTempo.getText())));

			} catch (NumberFormatException e) {

				System.out.println(e.toString());
			} catch (ParseException e) {

				System.out.println(e.toString());
			}
			autoId++;
			preencherArea();
			limparCampos();

		} else {
			JOptionPane.showMessageDialog(this, "Favor preencher todos os campos");
		}

	}

	public Date converterData() throws ParseException {

		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		date = sdf.parse(tfData.getText());

		return date;
	}

	private void limparCampos() {
		tfData.setText(null);
		tfCusto.setText(null);
		tfTempo.setText(null);
	}

	private void preencherArea() {

		texto = "";
		for (Manutencao m : ProcessaManutencoes.man) {
			texto += m.toString();
		}
		listar.setText(texto);
	}

	private void buscar() {
		String entrada = JOptionPane.showInputDialog(this, "Digite o id");

		boolean isNumeric = true;
		if (entrada != null) {
			for (int i = 0; i < entrada.length(); i++) {
				if (!Character.isDigit(entrada.charAt(i))) {
					isNumeric = false;
				}
			}
		} else {
			isNumeric = false;
		}
		if (isNumeric) {
			int id = Integer.parseInt(entrada);
			Manutencao man = new Manutencao(id);
			if (ProcessaManutencoes.man.contains(man)) {
				int indice = ProcessaManutencoes.man.indexOf(man);

				tfId.setText(ProcessaManutencoes.man.get(indice).getId("s"));
				cbEquipamento
						.setSelectedIndex(obterIdEquipamento(ProcessaManutencoes.man.get(indice).getEquipamento()));
				tfData.setText(ProcessaManutencoes.man.get(indice).getId("s"));
				tfCusto.setText(ProcessaManutencoes.man.get(indice).getId("s"));
				tfTempo.setText(ProcessaManutencoes.man.get(indice).getId("s"));

			} else {
				JOptionPane.showMessageDialog(this, "Não encontrado");
			}
		}
	}

	private void excluir() {
		int id = Integer.parseInt(tfId.getText());
		Manutencao man = new Manutencao(id);
		int indice = ProcessaManutencoes.man.indexOf(man);
		ProcessaManutencoes.man.remove(indice);
		preencherArea();
		limparCampos();
		tfId.setText(String.format("%d", autoId));
		ProcessaManutencoes.salvar();
	}

	private void alterar() {
		int id = Integer.parseInt(tfId.getText());
		Manutencao man = new Manutencao(id);
		int indice = ProcessaManutencoes.man.indexOf(man);

		if (tfData.getText().length() != 0 && tfCusto.getText().length() != 0 && tfTempo.getText().length() != 0) {

			try {
				ProcessaManutencoes.man.set(indice,
						new Manutencao(autoId, converterData(), cbEquipamento.getSelectedItem().toString(),
								Double.parseDouble(tfCusto.getText()), Double.parseDouble(tfTempo.getText())));

			} catch (NumberFormatException e) {

				System.out.println(e.toString());
			} catch (ParseException e) {

				System.out.println(e.toString());
			}
			autoId++;
			preencherArea();
			limparCampos();

		} else {
			JOptionPane.showMessageDialog(this, "Favor preencher todos os campos");
		}
		tfId.setText(String.format("%d", autoId));
		ProcessaManutencoes.salvar();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btCadastrar) {
			cadastrar();
			btBuscar.setEnabled(true);
			btExcluir.setEnabled(false);
			btExcluir.setEnabled(false);
		}
		if (e.getSource() == btBuscar) {
			buscar();
			btCadastrar.setEnabled(false);
			btExcluir.setEnabled(true);
			btAlterar.setEnabled(true);
		}
		if (e.getSource() == btExcluir) {
			excluir();
			btCadastrar.setEnabled(true);
			btAlterar.setEnabled(false);
			btExcluir.setEnabled(false);
		}
		if (e.getSource() == btAlterar) {
			alterar();
			btAlterar.setEnabled(false);
			btCadastrar.setEnabled(true);
		}

	}

	public static void main(String[] args) throws ParseException {
		ProcessaManutencoes.abrir();
		new FormManutencoes().setVisible(true);
	}

}
