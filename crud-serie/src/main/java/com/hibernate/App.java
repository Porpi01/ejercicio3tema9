package com.hibernate;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hibernate.dao.SerieDAO;
import com.hibernate.model.Serie;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class App {

	private JFrame frmCrudSeries;
	private JTable tableSeries;
	private JTextField textFieldSerie;
	private JTextField textFieldTemporadas;
	private JTextField textFieldCapitulos;
	private JTextField textFieldID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmCrudSeries.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		SerieDAO serieDAO = new SerieDAO ();
		
		frmCrudSeries = new JFrame();
		frmCrudSeries.getContentPane().setBackground(SystemColor.inactiveCaption);
		frmCrudSeries.setTitle("CRUD series");
		frmCrudSeries.setBounds(100, 100, 579, 528);
		frmCrudSeries.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrudSeries.getContentPane().setLayout(null);
		
		
		DefaultTableModel modelSerie = new DefaultTableModel();
		modelSerie.addColumn("Id");
		modelSerie.addColumn("Nombre");
		modelSerie.addColumn("Temporadas");
		modelSerie.addColumn("Capítulos");
		
		tableSeries = new JTable(modelSerie);
		
		tableSeries.setBounds(28, 119, 506, -90);
		frmCrudSeries.getContentPane().add(tableSeries);
		
		
		
		List<Serie> serieSelect = serieDAO.selectAllSerie();
		for (Serie s : serieSelect) {
		    Object[] fila = { s.getId(), s.getNombre(), s.getTemporadas(), s.getCapitulos() };
		    modelSerie.addRow(fila);
		}
				
	  
		
		
		JScrollPane scrollPaneSerie = new JScrollPane(tableSeries);
		scrollPaneSerie.setBounds(10, 11, 543, 220);
		frmCrudSeries.getContentPane().add(scrollPaneSerie);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblId.setBounds(10, 278, 29, 14);
		frmCrudSeries.getContentPane().add(lblId);
		
		JLabel lblDatos = new JLabel("INTRODUCIR DATOS");
		lblDatos.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDatos.setBounds(10, 242, 387, 25);
		frmCrudSeries.getContentPane().add(lblDatos);
		
		JLabel lblSerie = new JLabel("SERIE:");
		lblSerie.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSerie.setBounds(10, 322, 46, 14);
		frmCrudSeries.getContentPane().add(lblSerie);
		
		JLabel lblTemporadas = new JLabel("TEMPORADAS:");
		lblTemporadas.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTemporadas.setBounds(10, 364, 123, 14);
		frmCrudSeries.getContentPane().add(lblTemporadas);
		
		JLabel lblCapitulos = new JLabel("CAPITULOS");
		lblCapitulos.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCapitulos.setBounds(10, 406, 101, 14);
		frmCrudSeries.getContentPane().add(lblCapitulos);
		
		textFieldID = new JTextField();
		textFieldID.setEnabled(false);
		textFieldID.setBounds(164, 276, 179, 20);
		frmCrudSeries.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		textFieldSerie = new JTextField();
		textFieldSerie.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldSerie.setBounds(164, 320, 300, 20);
		frmCrudSeries.getContentPane().add(textFieldSerie);
		textFieldSerie.setColumns(10);
		
		textFieldTemporadas = new JTextField();
		textFieldTemporadas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldTemporadas.setBounds(164, 362, 179, 20);
		frmCrudSeries.getContentPane().add(textFieldTemporadas);
		textFieldTemporadas.setColumns(10);
		
		textFieldCapitulos = new JTextField();
		textFieldCapitulos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldCapitulos.setBounds(164, 404, 179, 20);
		frmCrudSeries.getContentPane().add(textFieldCapitulos);
		textFieldCapitulos.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
				
				String nombreSerie = textFieldSerie.getText();
				int numeroTemporadas =  Integer.parseInt(textFieldTemporadas.getText());
				int numeroCapitulos = Integer.parseInt(textFieldCapitulos.getText());
				
				Serie serieGuardar = new Serie (nombreSerie, numeroTemporadas, numeroCapitulos );
				serieDAO.insertSerie(serieGuardar);
				
				JOptionPane.showMessageDialog(null, "Serie guardada correctamente");
				textFieldSerie.setText("");
				textFieldTemporadas.setText("");
				textFieldCapitulos.setText("");
				
				modelSerie.setRowCount(0);
				List<Serie> serieSelect = serieDAO.selectAllSerie();
				for (Serie s : serieSelect) {
				    Object[] fila = { s.getId(), s.getNombre(), s.getTemporadas(), s.getCapitulos() };
				    modelSerie.addRow(fila);
				}
					
				
			}catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Hay casillas vacías o se ingresó un dato incorrectamente");
			}
			}
			
		});
		btnGuardar.setBackground(SystemColor.text);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGuardar.setBounds(10, 455, 89, 23);
		frmCrudSeries.getContentPane().add(btnGuardar);
		
		//Boton Actualizar
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
				int filaSeleccionada = tableSeries.getSelectedRow();
				Serie serieActualizada = serieDAO.selectSerieById((int)tableSeries.getValueAt(filaSeleccionada, 0));
				
				serieActualizada.setNombre(textFieldSerie.getText()); 
				serieActualizada.setTemporadas(Integer.parseInt(textFieldTemporadas.getText())); 
				serieActualizada.setCapitulos(Integer.parseInt(textFieldCapitulos.getText())); 
	
				
				serieDAO.updateSerie(serieActualizada);
				
				tableSeries.setValueAt(serieActualizada.getNombre(), filaSeleccionada, 1);
				tableSeries.setValueAt(serieActualizada.getTemporadas(), filaSeleccionada, 2);
				tableSeries.setValueAt(serieActualizada.getCapitulos(), filaSeleccionada, 3);
				JOptionPane.showMessageDialog(null, "Serie acualizada correctamente");
				textFieldSerie.setText("");
				textFieldTemporadas.setText("");
				textFieldCapitulos.setText("");
				textFieldID.setText("");
				
			}catch(ArrayIndexOutOfBoundsException e1) {
				JOptionPane.showMessageDialog(null, "No hay ninguna serie o no se ha seleccionado ninguna");
			}catch(NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "Hay casillas vacías");
			}
		}
		});
		btnActualizar.setBackground(SystemColor.text);
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnActualizar.setBounds(227, 455, 109, 23);
		frmCrudSeries.getContentPane().add(btnActualizar);
		
		//Botón borrar según el id seleccionado
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				int filaSeleccionada = tableSeries.getSelectedRow();
				int idSerie = (int)tableSeries.getValueAt(filaSeleccionada, 0);
				serieDAO.deleteSerie(idSerie);
				
				modelSerie.removeRow(filaSeleccionada);
				JOptionPane.showMessageDialog(null, "Serie borrada correctamente");
				textFieldSerie.setText("");
				textFieldTemporadas.setText("");
				textFieldCapitulos.setText("");
				textFieldID.setText("");
				
				
				}catch(ArrayIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "No hay ninguna serie o no se ha seleccionado ninguna");
				}
			}
		});
		btnBorrar.setBackground(SystemColor.text);
		btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBorrar.setBounds(464, 455, 89, 23);
		frmCrudSeries.getContentPane().add(btnBorrar);
		
		//Función utilizada para seleccionar el id de la tabla y poder realizar distintas operaciones con él
		tableSeries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int índice = tableSeries.getSelectedRow();
				TableModel model = tableSeries.getModel();
				textFieldID.setText(model.getValueAt(índice, 0).toString());
				textFieldSerie.setText(model.getValueAt(índice, 1).toString());
				textFieldTemporadas.setText(model.getValueAt(índice, 2).toString());
				textFieldCapitulos.setText(model.getValueAt(índice, 3).toString());
			}
		});
	}
	
	
}
