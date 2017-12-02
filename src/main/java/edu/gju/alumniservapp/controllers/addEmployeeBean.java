/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumniservapp.controllers;

import edu.gju.alumniservapp.daos.ConnectionDAO;
import edu.gju.alumniservapp.daos.ConnectionDAOImpl;
import edu.gju.alumniservapp.models.Employee;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author hesham
 */
@ViewScoped
@Named(value = "addEmployee")
public class addEmployeeBean implements Serializable {

    private Integer id;
    private Connection conn;
    private ConnectionDAO dataSource = new ConnectionDAOImpl();
    private Employee employee = new Employee();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    private Employee prepareEmployeeObject() {
        Employee newEmployee = new Employee();

        return newEmployee;
    }

    public void addEmployee() throws SQLException {
        this.conn = dataSource.getDataSource().getConnection();
        Statement st = conn.createStatement();
        int rs = st.executeUpdate("insert into employees (empid, firstname, lastname, employeetype) values (" + employee.getEmployeeId() + ", " + employee.getFirstNameEn() + ", " + employee.getLastNameEn() + ", " + employee.getType() + ")");
        

    }

}
