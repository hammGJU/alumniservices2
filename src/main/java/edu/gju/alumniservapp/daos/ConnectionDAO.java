/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gju.alumniservapp.daos;

import java.sql.Connection;
import javax.sql.DataSource;

/**
 *
 * @author hesham
 */
public interface ConnectionDAO {

    public DataSource getDataSource();

    public void closeConnection();

    public Connection openSessionConnection();

}
