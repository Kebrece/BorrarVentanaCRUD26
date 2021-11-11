package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class VentanaCRUD extends JFrame{
    private JPanel panel1;
    private JTextField IdText;
    private JTextField NombreText;
    private JTextField ApellidoText;
    private JTextField DireccionText;
    private JTextField CPText;
    private JTextField TelText;
    private JButton agregarButton;
    private JButton actualizarButton;
    private JButton imprimirButton;
    private JButton resetButton;
    private JButton borrarButton;
    private JButton salirButton;
    private JTable table1;
    private JPanel principal;
    private DefaultTableModel model;

    public VentanaCRUD() throws HeadlessException
    {
        setTitle("Bases de Datos y CRUD");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addComponents(getContentPane());
        setVisible(true);
    }

    private void addComponents(Container contentPane)
    {
        Object [] columns = {"ID", "NOMBRE", "APELLIDO", "DIRECCION", "CODIGO-POSTAL", "TELEFONO"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);
        table1.setModel(model);
        contentPane.add(principal);
    }


    public int showYesNoMessage(String s) {
        return JOptionPane.showConfirmDialog(this, s, "Sistemas Bases de Datos", JOptionPane.YES_NO_OPTION);

    }

    public void salir()
    {
        System.exit(0);
    }

    public void addListener(ActionListener al)
    {
        agregarButton.addActionListener(al);
        actualizarButton.addActionListener(al);
        imprimirButton.addActionListener(al);
        resetButton.addActionListener(al);
        borrarButton.addActionListener(al);
        salirButton.addActionListener(al);
    }

    public void limpiaTXT()
    {
        IdText.setText("");
        NombreText.setText("");
        ApellidoText.setText("");
        DireccionText.setText("");
        CPText.setText("");
        TelText.setText("");
    }

    public void agregarTabla(Object[] row) {
        model.addRow(row);
    }

    public JTable getTable1() {
        return table1;
    }

    public void setTextId(String valor)
    {
        IdText.setText(valor);
    }

    public void setTextNombre(String valor)
    {
        NombreText.setText(valor);
    }

    public void setTextApellido(String valor)
    {
        ApellidoText.setText(valor);
    }

    public void setTextDireccion(String valor)
    {
        DireccionText.setText(valor);
    }

    public void setTextCP(String valor)
    {
        CPText.setText(valor);
    }

    public void setTextTel(String valor)
    {
        TelText.setText(valor);
    }


    public String getTextId()
    {
        return IdText.getText();
    }

    public String getTextNombre()
    {
        return NombreText.getText();
    }

    public String getTextApellido()
    {
        return ApellidoText.getText();
    }

    public String getTextDireccion()
    {
        return DireccionText.getText();
    }

    public String getTextCP()
    {
        return CPText.getText();
    }

    public String getTextTel()
    {
        return TelText.getText();
    }


    public void addTableMouseListener(MouseAdapter ma)
    {
        table1.addMouseListener(ma);
    }

    public void showMessage(String s) {
        JOptionPane.showMessageDialog(this, s, "Mensaje Sistema BD", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorMessage(String s)
    {
        JOptionPane.showMessageDialog(this, s, "Mensaje de error", JOptionPane.ERROR_MESSAGE);
    }

    public void eliminarFila()
    {
        model.removeRow(table1.getSelectedRow());
    }
}
