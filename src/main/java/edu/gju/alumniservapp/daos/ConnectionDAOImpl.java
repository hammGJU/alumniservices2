/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumniservapp.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author hesham
 */
public class ConnectionDAOImpl implements Serializable, ConnectionDAO {

//    @Resource(name = "jdbc/Alumni_GJU")
    private DataSource dataSource;
    private Context ctx;

    public ConnectionDAOImpl() {
        try {
            ctx = new InitialContext();
            this.dataSource = (DataSource) ctx.lookup("jdbc/Alumni_GJU");
        } catch (NamingException ex) {
            Logger.getLogger(ConnectionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public DataSource getDataSource() {
        return this.dataSource;
    }

    @Override
    public void closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Connection openSessionConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
