package model;

import sqlConnection.SQLExecutorURL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Estudiante
{
    private int id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String codigoPostal;
    private String telefono;

    public Estudiante() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Estudiante> cargarDatos()
    {
        List<Estudiante> list = new ArrayList<>();
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "VentanaCRUD", "sa", "password");
        sqlExecutorURL.abreConexion();
        ResultSet rs2 = sqlExecutorURL.ejecutaSQL("select * from VentanaCRUD.dbo.Estudiante");
        try
        {
            while (rs2.next())
            {
                Estudiante comodin = new Estudiante();
                comodin.setId(rs2.getInt("ID"));
                comodin.setNombre(rs2.getString("Nombre"));
                comodin.setApellido(rs2.getString("Apellido"));
                comodin.setDireccion(rs2.getString("Direccion"));
                comodin.setCodigoPostal(rs2.getString("CodigoPostal"));
                comodin.setTelefono(rs2.getString("Telefono"));

                list.add(comodin);
            }
        }catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        sqlExecutorURL.cierraConexion();
        return list;
    }

    public void insertarEstudiante()
    {
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "VentanaCRUD", "sa", "password");
        sqlExecutorURL.abreConexion();
        String valores[] = new String [7];
        valores[0] = "insert into Estudiante(ID, Nombre, "+
                "Apellido, Direccion, CodigoPostal, Telefono) values"+
                "(?,?,?,?,?,?)";
        valores[1] = Integer.toString(getId());
        valores[2] = getNombre();
        valores[3] = getApellido();
        valores[4] = getDireccion();
        valores[5] = getCodigoPostal();
        valores[6] = getTelefono();
        sqlExecutorURL.prepareStatment(valores);
    }

    public void actualizarEstudiante()
    {
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "VentanaCRUD", "sa", "password");
        sqlExecutorURL.abreConexion();
        String valores[] = new String[8];
        valores[0] = "update Estudiante set ID = ?, Nombre = ?, Apellido = ?, Direccion = ?, CodigoPostal = ?, Telefono = ? where ID = ?";
        //Si no se pone la clausula where se actualizan todas las filas de la base de datos
        valores[1] = Integer.toString(getId());
        valores[2] = getNombre();
        valores[3] = getApellido();
        valores[4] = getDireccion();
        valores[5] = getCodigoPostal();
        valores[6] = getTelefono();
        valores[7] = Integer.toString(getId());
        sqlExecutorURL.prepareStatment(valores);

    }

    public void borrarEstudiante()
    {
        SQLExecutorURL sqlExecutorURL = new SQLExecutorURL("1433", "VentanaCRUD", "sa", "password");
        sqlExecutorURL.abreConexion();
        String valores[] = new String[2];
        valores[0] = "DELETE FROM Estudiante WHERE ID =?";
        valores[1] = Integer.toString(getId());
        sqlExecutorURL.prepareStatment(valores);
    }
}
