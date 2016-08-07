package com.shsnc.util.appswitch;

import java.sql.*;

public class DataBase
{
    private Connection conn = null;
    private final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    private final String username = "mon";
    private final String password = "mon";
    int count = 0;
    private ResultSet resultSet = null;
    private PreparedStatement pstmt = null;

    public DataBase()
    {
        this.conn = connectionDB();
    }

    public Connection connectionDB()
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver").newInstance();
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mon", "mon");

            return this.conn;
        }
        catch (Exception localException)
        {
            localException =localException;localException.printStackTrace();return this.conn;
        }
        finally {

            return this.conn;
        }

    }

    public ResultSet query(String paramString)
    {
        try
        {
            this.pstmt = this.conn.prepareStatement(paramString);
            this.resultSet = this.pstmt.executeQuery();



            return this.resultSet;
        }
        catch (SQLException localSQLException)
        {
            localSQLException =


                    localSQLException;localSQLException.printStackTrace();return this.resultSet;
        }
        finally {
            return this.resultSet;
        }

    }

    public boolean coles()
    {
        boolean bool = false;
        if (this.resultSet != null) {
            try
            {
                this.resultSet.close();
                this.resultSet = null;
                bool = true;
            }
            catch (SQLException localSQLException1)
            {
                bool = false;
                localSQLException1.printStackTrace();
            }
        }
        if (this.pstmt != null) {
            try
            {
                this.pstmt.close();
                this.pstmt = null;
                bool = true;
            }
            catch (SQLException localSQLException2)
            {
                bool = false;
                localSQLException2.printStackTrace();
            }
        }
        if (this.conn != null) {
            try
            {
                this.conn.close();
                this.conn = null;
                bool = true;
            }
            catch (Exception localException)
            {
                bool = false;
                localException.printStackTrace();
            }
        }
        return bool;
    }
}
