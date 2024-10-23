package vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class PanelHistorico extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tablaHistorico;
	private DefaultTableModel defaultTableModel;
	private JButton btnAtras;

	/**
	 * Create the panel.
	 */
	public PanelHistorico() {
		setBackground(Color.LIGHT_GRAY);
		setBounds(10, 71, 966, 481);
		setLayout(null);

		// ************************************** TABLA
		// ************************************************************
		JScrollPane jScrollPanel;
		jScrollPanel = new JScrollPane();
		jScrollPanel.setBounds(56, 78, 845, 304);
		add(jScrollPanel);

		String columnas[] = { "Nombre Workout", "Nivel", "Tiempo Total", "Tiempo Previsto" , "Fecha" , "%Ejercicios Completados" };

		defaultTableModel = new DefaultTableModel(columnas, 0);

		tablaHistorico = new JTable(defaultTableModel);
		tablaHistorico.setAutoCreateRowSorter(true);
		tablaHistorico.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tablaHistorico.setDefaultEditor(Object.class, null); // Anulamos la edici�n en la propia celda

		jScrollPanel.setViewportView(tablaHistorico);
		
		btnAtras = new JButton("Atras");
		btnAtras.setBounds(56, 399, 89, 23);
		add(btnAtras);

	}

	public JTable getTablaHistorico() {
		return tablaHistorico;
	}

	public void setTablaHistorico(JTable tablaHistorico) {
		this.tablaHistorico = tablaHistorico;
	}

	public DefaultTableModel getDefaultTableModel() {
		return defaultTableModel;
	}

	public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
		this.defaultTableModel = defaultTableModel;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public void setBtnAtras(JButton btnAtras) {
		this.btnAtras = btnAtras;
	}
}
