package conexion;

//import com.sun.org.apache.xml.internal.security.Init;
import java.sql.*;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;

public class helper {

    private String errorSQL = "";
    private Connection conex = null;
    CallableStatement cmd;
    BasicDataSource bds;

    public helper() {
        try 
        {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/sgautomotriz");
            this.conex = ds.getConnection();
        }
        catch (Exception e) 
        {
            errorSQL = e.getMessage();
        }
    }

    public String executeNonQuery(String proc, Object[] args) throws Exception {
        String result = "";

        try {
            cmd = this.conex.prepareCall(proc);

            for (int i = 0; i < args.length; i++) {
                cmd.setObject(i + 1, args[i]);
            }

            int f = cmd.executeUpdate();
            result = "";
        } catch (SQLException se) {
            errorSQL = se.getMessage();
            result = se.getMessage();
        } catch (Exception e) {
            result = e.getMessage();
            errorSQL = e.getMessage();
        }

        return result;
    }

    public ResultSet executeDataSet(String proc, Object[] args) throws Exception {
        ResultSet result = null;
        try {
            cmd = this.conex.prepareCall(proc);

            for (int i = 0; i < args.length; i++) {
                cmd.setObject(i + 1, args[i]);
            }

            result = cmd.executeQuery();
        } catch (Exception e) {
            errorSQL = e.getMessage();
        }

        return result;
    }

    public Integer executeDataSetOut(String proc, Object[] args) throws Exception {
        int result = 0;
        try {
            cmd = this.conex.prepareCall(proc);
            for (int i = 0; i < args.length; i++) {
                cmd.setObject(i + 1, args[i]);
            }

            cmd.registerOutParameter(args.length + 1, java.sql.Types.INTEGER);

            cmd.executeUpdate();

            result = cmd.getInt(args.length + 1);
        } catch (Exception e) {
            errorSQL = e.getMessage();
        }

        return result;
    }

    public String executeDataSetOutStr(String proc, Object[] args) throws Exception {
        String result = "abcd";
        try {
            cmd = this.conex.prepareCall(proc);
            for (int i = 0; i < args.length; i++) {
                cmd.setObject(i + 1, args[i]);
            }

            cmd.registerOutParameter(args.length + 1, java.sql.Types.VARCHAR);

            cmd.executeUpdate();

            result = cmd.getString(args.length + 1);
        } catch (Exception e) {
            errorSQL = e.getMessage();
        }

        return result;
    }

    /**
     * @return the conex
     */
    public Connection getConex() {
        return conex;
    }

    /**
     * @param conex the conex to set
     */
    public void setConex(Connection conex) {
        this.conex = conex;
    }

    public void returnConnect() {
        try {
            cmd.close();
            conex.close();
            //getDbPool().returnConnection(this.conex);
        } catch (Exception e) {
        }
    }

    /**
     * @return the errorConect
     */
    public String getErrorSQL() {
        return errorSQL.trim();
    }

    /**
     * @param errorConect the errorConect to set
     */
    public void setErrorSQL(String errorSQL) {
        this.errorSQL = errorSQL;
    }
}
