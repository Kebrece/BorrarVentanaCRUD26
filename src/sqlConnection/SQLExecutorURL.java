package sqlConnection;

import java.sql.*;

public class SQLExecutorURL
{
    private String sqlServerConnectionURL;
    private Connection  dbConn = null;
    private PreparedStatement stmt = null; //Instruccion ejemplo de instruccion (SELECT* FROM NOMBRE TABLA WHERE CAMPO = 1)
    private ResultSet rs = null;

    private String  port;
    private String dbName;
    private String user;
    private String pwd;

    public SQLExecutorURL(String port, String dbName, String user, String pwd)
    {
        //El puerto es 1433
        //dbName es CURSO
        //user sa
        //password password
        this.port = port;
        this.dbName = dbName;
        this.user = user;
        this.pwd = pwd;
        sqlServerConnectionURL = //"jdbc:sqlserver://localhost:1433;user=sa;password={password}";
                "jdbc:sqlserver://localhost:"+port+";dataBaseName="+dbName+";user="+user+";password="+pwd;
    }

    public void abreConexion()
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//Driver Correcto
            //Get Connection
            dbConn = DriverManager.getConnection(sqlServerConnectionURL);//Aqui se construye la conexion
        }catch (SQLException | ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }

    public void prepareStatment(String[] parametros)
    {
        try{
            stmt = dbConn.prepareStatement(parametros[0]);
            for(int i = 1; i< parametros.length; i++)
            {
                stmt.setString(i, parametros[i]);
            }
            stmt.executeUpdate();//Delete, Update, Insert
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public ResultSet ejecutaSQL(String sql)
    {
        try
        {
            stmt = dbConn.prepareStatement(sql);
            rs = stmt.executeQuery();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return rs; //Retorna una tabla de la base de datos
    }

    public void cierraConexion()
    {
        if(rs!=null)
        {
            try{
                rs.close();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }

        if(stmt != null)
        {
            try
            {
                stmt.close();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }

        if(dbConn != null)
        {
            try{
                dbConn.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
}
