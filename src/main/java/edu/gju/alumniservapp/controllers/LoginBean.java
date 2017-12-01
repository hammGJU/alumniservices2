/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumniservapp.controllers;

import edu.gju.alumniservapp.daos.ConnectionDAO;
import edu.gju.alumniservapp.daos.ConnectionDAOImpl;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author hesham
 */
@SessionScoped
@Named(value = "loginBean")
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private String empType;
    private Connection conn;

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private ConnectionDAO ds = new ConnectionDAOImpl();

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public ConnectionDAO getDs() {
        return ds;
    }

    public void setDs(ConnectionDAO ds) {
        this.ds = ds;
    }

    public void login() throws SQLException {
        FacesContext facesContect = FacesContext.getCurrentInstance();
        boolean success = false;
        this.conn = ds.getDataSource().getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from employees");
        String user;
        String passw;
        String type = "";
//        this.username
        while (rs.next()) {
            user = rs.getString("username");
            passw = rs.getString("password");
            type = rs.getString("EMPLOYEETYPE");
//            System.err.print(username + ", " + password);
            if (this.username.equals(user) && this.password.equals(passw)) {
                success = true;
                break;
            } else {
                success = false;
            }
        }
        if (success) {
            if (facesContect != null) {
                NavigationHandler navigationHandler = facesContect.getApplication().getNavigationHandler();
                if (type.equalsIgnoreCase("st")) {
                    navigationHandler.handleNavigation(facesContect, null, "/alumni_first_page?faces-redirect=true");
                } else {
                    navigationHandler.handleNavigation(facesContect, null, "/admin_first_page?faces-redirect=true");
                }
            }
        }
    }

    public void logout() throws Exception {
        try {
            // Release and close database resources and connections 
            if (conn != null) {
                if (!conn.getAutoCommit()) {
                    conn.rollback();
                    conn.setAutoCommit(true);
                }

                conn.close();
                conn = null;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            setPassword(null);
            setUsername(null);

            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().invalidateSession();
        }
    }

}
