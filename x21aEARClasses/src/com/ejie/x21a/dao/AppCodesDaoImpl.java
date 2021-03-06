/*
 * Copyright 2011 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 * http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
 * el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */
package com.ejie.x21a.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * * AppCodesDaoImpl generated by UDA, 11-dic-2012 9:53:43.
 * 
 * @author UDA
 */
@Repository
@Transactional
public class AppCodesDaoImpl implements AppCodesDao {
    private JdbcTemplate jdbcTemplate;
    private RowMapper<String> rwMap = new RowMapper<String>() {
		public String mapRow(ResultSet resultSet, int rowNum) throws SQLException {
           return new String(
               resultSet.getString("APPCODE")
           ); } } ;
	
	/**
     * Method use to set the datasource.
     *
     * @param dataSource DataSource
     * @return
     */
    @Resource
    public void setDataSource(DataSource dataSource) {
    	this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Inserts a single row in the Appcodes table.
     *
     * @param userName String
     * @param appCode String
     * @return
     */
	public void add(String userName, String appCode) {
    	String query = "INSERT INTO APPCODES (USERNAME, APPCODE) VALUES (?,?)";
		this.jdbcTemplate.update(query, userName, appCode);
	}

    /**
     * Updates a single row in the Appcodes table.
     *
     * @param userName String
     * @param appCode String
     * @param newAppCode String
     * @return
     */
    public void update(String userName, String appCode, String newAppCode) {
		String query = "UPDATE APPCODES SET USERNAME=?, newAppCode=? WHERE USERNAME=? APPCODE=?";
		this.jdbcTemplate.update(query, userName, newAppCode, userName, appCode);
	}

    /**
     * Finds a single row in the Appcodes table.
     *
     * @param userName String
     * @return ArrayList<String>
     */
    @Transactional (readOnly = true)
    public List<String> find(String userName) {
		String query = "SELECT t1.APPCODE APPCODE FROM APPCODES t1  WHERE t1.USERNAME = ?";
		
		return this.jdbcTemplate.query(query, this.rwMap, userName);
    }

    /**
     * Removes a single row in the Appcodes table.
     *
     * @param userName String
     * @param appCode String
     * @return
     */
    public void remove(String userName, String appCode) {
		String query = "DELETE FROM APPCODES WHERE USERNAME=? APPCODE=?";
		this.jdbcTemplate.update(query, userName, appCode);
    }
    
}


