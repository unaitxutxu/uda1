/*
* Copyright 2012 E.J.I.E., S.A.
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

import com.ejie.x38.dto.Pagination;
import com.ejie.x38.util.PaginationManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.model.NoraProvincia;

/**
 *  * NoraProvinciaDaoImpl generated by UDA, 18-ene-2012 11:57:47.
 * @author UDA
 */
 
@Repository
@Transactional
public class NoraProvinciaDaoImpl implements NoraProvinciaDao {
    private SimpleJdbcTemplate jdbcTemplate;
	private RowMapper<NoraProvincia> rwMap = new RowMapper<NoraProvincia>() {
		public NoraProvincia mapRow(ResultSet resultSet, int rowNum) throws SQLException {
           return new NoraProvincia(
               resultSet.getString("ID"), resultSet.getString("DSO"), resultSet.getString("AUTONOMIAID")
           ); } } ;

	/**
     * Method use to set the datasource.
     *
     * @param dataSource DataSource
     * @return
     */
    @Resource
    public void setDataSource(DataSource dataSource) {
    	this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    /**
     * Inserts a single row in the NoraProvincia table.
     *
     * @param provincia Pagination
     * @return NoraProvincia
     */
	public NoraProvincia add(NoraProvincia provincia) {

    	String query = "INSERT INTO T17_PROVINCIA( ID,DS_O,AUTONOMIA_ID)"
        + "VALUES (?,?,?)";

		this.jdbcTemplate.update(query, provincia.getId(), provincia.getDsO(), provincia.getAutonomiaId());
		return provincia;
	}

    /**
     * Updates a single row in the NoraProvincia table.
     *
     * @param provincia Pagination
     * @return NoraProvincia
     */
    public NoraProvincia update(NoraProvincia provincia) {
		String query = "UPDATE T17_PROVINCIA SET  WHERE ID=? AND DS_O=? AND AUTONOMIA_ID=?";
		this.jdbcTemplate.update(query, provincia.getId(), provincia.getDsO(), provincia.getAutonomiaId());
		return provincia;
	}

    /**
     * Finds a single row in the NoraProvincia table.
     *
     * @param provincia Pagination
     * @return NoraProvincia
     */
    @Transactional (readOnly = true)
    public NoraProvincia find(NoraProvincia provincia) {
		String query = "SELECT t1.ID ID, t1.DS_O DSO, t1.AUTONOMIA_ID AUTONOMIAID " 
         + "FROM T17_PROVINCIA t1  " 
         + "WHERE t1.ID = ?   AND t1.DS_O = ?   AND t1.AUTONOMIA_ID = ?    ";
		return (NoraProvincia) this.jdbcTemplate.queryForObject(query, 
			rwMap , provincia.getId() , provincia.getDsO() , provincia.getAutonomiaId());	 
    }

    /**
     * Removes a single row in the NoraProvincia table.
     *
     * @param provincia Pagination
     * @return
     */
    public void remove(NoraProvincia provincia) {
		String query = "DELETE  FROM T17_PROVINCIA WHERE ID=? AND DS_O=? AND AUTONOMIA_ID=?";
		this.jdbcTemplate.update(query, provincia.getId() , provincia.getDsO() , provincia.getAutonomiaId());
    	}
    
   /**
    * Finds a List of rows in the NoraProvincia table.
    * 
    * @param provincia NoraProvincia
    * @param pagination Pagination
    * @return List 
    */
	@Transactional (readOnly = true)
    public List<NoraProvincia> findAll(NoraProvincia provincia, Pagination pagination) {
		StringBuffer where = new StringBuffer(3000);
		List<Object> params = new ArrayList<Object>();
		where.append(" WHERE 1=1 	");
		
		StringBuffer query = new StringBuffer("SELECT  t1.ID ID,t1.DS_O DSO,t1.AUTONOMIA_ID AUTONOMIAID " 
			+ "FROM T17_PROVINCIA t1 ");
		
		if (provincia  != null  && provincia.getId() != null ) {
			where.append(" AND t1.ID = ?");
			params.add(provincia.getId());
		}
		if (provincia  != null  && provincia.getDsO() != null ) {
			where.append(" AND t1.DS_O = ?");
			params.add(provincia.getDsO());
		}
		if (provincia  != null  && provincia.getAutonomiaId() != null ) {
			where.append(" AND t1.AUTONOMIA_ID = ?");
			params.add(provincia.getAutonomiaId());
		}
		query.append(where);

		StringBuffer order = new StringBuffer(3000);
		if (pagination != null) {
			if (pagination.getSort() != null) {
				order.append(" ORDER BY " + pagination.getSort() + " " + pagination.getAscDsc());
				query.append(order);
			}
			query = new StringBuffer(PaginationManager.getQueryLimits(pagination,query.toString()));
		}
		return (List<NoraProvincia>) this.jdbcTemplate.query(query.toString(),rwMap, params.toArray());
	}
	
    /**
     * Counts rows in the NoraProvincia table.
     * 
     * @param provincia NoraProvincia
     * @return Long
     */
    @Transactional (readOnly = true)
    public Long findAllCount(NoraProvincia provincia) {

		StringBuffer where = new StringBuffer(3000);
		List<Object> params = new ArrayList<Object>();
		where.append(" WHERE 1=1  ");

		StringBuffer query = new StringBuffer("SELECT COUNT(1) FROM  T17_PROVINCIA t1  ");
		if (provincia  != null  && provincia.getId() != null ) {
			where.append(" AND t1.ID = ?");
			params.add(provincia.getId());
		}
		if (provincia  != null  && provincia.getDsO() != null ) {
			where.append(" AND t1.DS_O = ?");
			params.add(provincia.getDsO());
		}
		if (provincia  != null  && provincia.getAutonomiaId() != null ) {
			where.append(" AND t1.AUTONOMIA_ID = ?");
			params.add(provincia.getAutonomiaId());
		}
		query.append(where);
		return this.jdbcTemplate.queryForLong(query.toString(), params.toArray());
	}
	
	/**
	 * Finds rows in the NoraProvincia table using like.
     * 
     * @param provincia NoraProvincia
     * @param pagination Pagination
     * @param startsWith Boolean
     * @return List 
     */
	@Transactional (readOnly = true)
    public List<NoraProvincia> findAllLike(NoraProvincia provincia, Pagination pagination, Boolean startsWith) {
		StringBuffer where = new StringBuffer(3000);
		List<Object> params = new ArrayList<Object>();
		where.append(" WHERE 1=1 	");
		
		StringBuffer query = new StringBuffer("SELECT  t1.ID ID,t1.DS_O DSO,t1.AUTONOMIA_ID AUTONOMIAID " 
        	+ "FROM T17_PROVINCIA t1 ");
      	
		if (provincia  != null  && provincia.getId() != null ) {
			where.append(" AND UPPER(t1.ID) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(provincia.getId().toUpperCase()  +"%");
			}else{
				params.add("%"+provincia.getId().toUpperCase() +"%");
			}	
			where.append(" AND t1.ID IS NOT NULL");
        }
		if (provincia  != null  && provincia.getDsO() != null ) {
			where.append(" AND UPPER(t1.DS_O) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(provincia.getDsO().toUpperCase()  +"%");
			}else{
				params.add("%"+provincia.getDsO().toUpperCase() +"%");
			}	
			where.append(" AND t1.DS_O IS NOT NULL");
        }
		if (provincia  != null  && provincia.getAutonomiaId() != null ) {
			where.append(" AND UPPER(t1.AUTONOMIA_ID) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(provincia.getAutonomiaId().toUpperCase()  +"%");
			}else{
				params.add("%"+provincia.getAutonomiaId().toUpperCase() +"%");
			}	
			where.append(" AND t1.AUTONOMIA_ID IS NOT NULL");
        }
        query.append(where);

		StringBuffer order = new StringBuffer(3000);
		if (pagination != null) {
			if (pagination.getSort() != null) {
				order.append(" ORDER BY " + pagination.getSort() + " " + pagination.getAscDsc());
				query.append(order);
			}
			query = new StringBuffer(PaginationManager.getQueryLimits(pagination,query.toString()));
		}
		return (List<NoraProvincia>) this.jdbcTemplate.query(query.toString(),rwMap, params.toArray());
	}
}

