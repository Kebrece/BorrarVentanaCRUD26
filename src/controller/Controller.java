package controller;

import model.Estudiante;
import view.VentanaCRUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.List;

public class Controller {
    private Estudiante estudiante;
    private VentanaCRUD ventanaCRUD;

    public Controller()
    {
        this.estudiante = new Estudiante();
        this.ventanaCRUD = new VentanaCRUD();
        ventanaCRUD.addListener(new ButtonListener());
        ventanaCRUD.addTableMouseListener(new TableMouseListener());
        List<Estudiante> lista = estudiante.cargarDatos();
        for(Estudiante estudiante: lista)
        {
            Object[] row = new Object[6];
            row[0] = estudiante.getId();
            row[1] = estudiante.getNombre();
            row[2] = estudiante.getApellido();
            row[3] = estudiante.getDireccion();
            row[4] = estudiante.getCodigoPostal();
            row[5] = estudiante.getTelefono();
            ventanaCRUD.agregarTabla(row);
        }
    }

    private class ButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String valor = e.getActionCommand();
            switch (valor)
            {
                case "Salir":
                    if(ventanaCRUD.showYesNoMessage("Esta seguro que desea salir?")== JOptionPane.YES_OPTION) {
                        ventanaCRUD.salir();
                    }
                    break;
                case "Reset":
                    ventanaCRUD.limpiaTXT();
                    break;
                case "Agregar":
                    cargaEstudiantedeVentana();
                    estudiante.insertarEstudiante();
                    ventanaCRUD.showMessage("Estudiante agregado correctamente");
                    Object [] row = new Object[6];
                    row[0] = estudiante.getId();
                    row[1] = estudiante.getNombre();
                    row[2] = estudiante.getApellido();
                    row[3] = estudiante.getDireccion();
                    row[4] = estudiante.getCodigoPostal();
                    row[5] = estudiante.getTelefono();
                    ventanaCRUD.agregarTabla(row);
                    ventanaCRUD.limpiaTXT();
                    break;
                case "Imprimir":
                    imprimirTabla();
                    break;
                case "Actualizar":
                    cargaEstudiantedeVentana();
                    estudiante.actualizarEstudiante();
                    ventanaCRUD.showMessage("Estudiante modificado correctamente");
                    actualizaFila();
                    break;
                case "Borrar":
                    cargaEstudiantedeVentana();
                    if(ventanaCRUD.showYesNoMessage("Esta seguro que desea borrar al estudiante?")==JOptionPane.YES_OPTION)
                    {
                        estudiante.borrarEstudiante();
                        ventanaCRUD.limpiaTXT();
                        eliminarFila();
                    }
                    break;

            }
        }
    }

    private void eliminarFila()
    {
        ventanaCRUD.eliminarFila();
    }

    private void actualizaFila()//Se puede hacer en la vista como lo hace eliminarFila()
    {
        JTable table = ventanaCRUD.getTable1();
        int i = table.getSelectedRow();
        if(i>=0)
        {
            table.getModel().setValueAt(ventanaCRUD.getTextId(), i, 0);
            table.getModel().setValueAt(ventanaCRUD.getTextNombre(), i, 1);
            table.getModel().setValueAt(ventanaCRUD.getTextApellido(), i, 2);
            table.getModel().setValueAt(ventanaCRUD.getTextDireccion(), i, 3);
            table.getModel().setValueAt(ventanaCRUD.getTextCP(), i, 4);
            table.getModel().setValueAt(ventanaCRUD.getTextTel(), i, 5);
        }
    }

    private void imprimirTabla()
    {
        JTable table = ventanaCRUD.getTable1();
        MessageFormat header = new MessageFormat("DATOS DE LOS ESTUDIANTES");
        MessageFormat footer = new MessageFormat("Pagina {0, number, integer}");
        try {
            table.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        }catch (PrinterException e)
        {
            ventanaCRUD.showErrorMessage("No se encontro ninguna impresora"+ e.getMessage());
        }
    }

    private void cargaEstudiantedeVentana()
    {
        /*
        Me toca hacer todas las revisiones necesarias
        -Que los campos no esten en blanco
        -Que el id sea estrictamente numerico
        -Verificar formatos: Telefono, Codigo Postal, Si tuvieramos correo o password
        -En este metodo se pueden hacer esas verificaciones
        */
        estudiante.setId(Integer.parseInt(ventanaCRUD.getTextId()));
        estudiante.setNombre(ventanaCRUD.getTextNombre());
        estudiante.setApellido(ventanaCRUD.getTextApellido());
        estudiante.setDireccion(ventanaCRUD.getTextDireccion());
        estudiante.setCodigoPostal(ventanaCRUD.getTextCP());
        estudiante.setTelefono(ventanaCRUD.getTextTel());
    }

    private class TableMouseListener extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent event)
        {
            //JTable table = (JTable)event.getSource();
            JTable table = ventanaCRUD.getTable1();
            int i = table.getSelectedRow();
            ventanaCRUD.setTextId(table.getModel().getValueAt(i,0).toString());
            ventanaCRUD.setTextNombre(table.getModel().getValueAt(i, 1).toString());
            ventanaCRUD.setTextApellido(table.getModel().getValueAt(i, 2).toString());
            ventanaCRUD.setTextDireccion(table.getModel().getValueAt(i, 3).toString());
            ventanaCRUD.setTextCP(table.getModel().getValueAt(i, 4).toString());
            ventanaCRUD.setTextTel(table.getModel().getValueAt(i, 5).toString());
        }
    }
}
