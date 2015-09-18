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

import com.ejie.x21a.model.Departamento;
import com.ejie.x21a.model.Provincia;
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

import com.ejie.x21a.model.DepartamentoProvincia;
/**   * DepartamentoProvinciaDaoImpl generated by UDA 1.0, 26-may-2011 13:45:00.
*  @author UDA
*/
@Repository
@Transactional
public class DepartamentoProvinciaDaoImpl implements DepartamentoProvinciaDao {
    private SimpleJdbcTemplate jdbcTemplate;
	private RowMapper<DepartamentoProvincia> rwMap = new RowMapper<DepartamentoProvincia>() {
          public DepartamentoProvincia mapRow(ResultSet resultSet, int rowNum) throws SQLException {
           return new DepartamentoProvincia(
               resultSet.getBigDecimal("CODE"), resultSet.getString("DESCES"), resultSet.getString("DESCEU"), resultSet.getString("CSS"), new Provincia(resultSet.getBigDecimal("ProvinciaCODE"), resultSet.getString("ProvinciaDESCES"), resultSet.getString("ProvinciaDESCEU"), resultSet.getString("ProvinciaCSS")), new Departamento(resultSet.getBigDecimal("DepartamentoCODE"), resultSet.getString("DepartamentoDESCES"), resultSet.getString("DepartamentoDESCEU"), resultSet.getString("DepartamentoCSS"))
           ); } } ;

     /**
     * Method use to set the datasource.
     *
     * @param dataSource DataSource
     *
     */
    @Resource
     public void setDataSource(DataSource dataSource) {
      this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
    }

    /**
     * Inserts a single row in the DepartamentoProvincia table.
     *
     * @param departamentoprovincia Pagination
     * @return DepartamentoProvincia
     */
     public DepartamentoProvincia add(DepartamentoProvincia departamentoprovincia) {

      String query = "INSERT INTO DEPARTAMENTO_PROVINCIA( CODE,CODE_PROVINCIA,CODE_DEPARTAMENTO,DESC_ES,DESC_EU,CSS)"
        + "VALUES (?,?,?,?,?,?)";

				   Object getProvinciaCodeAux=null;
		     if (departamentoprovincia.getProvincia()!= null  && departamentoprovincia.getProvincia().getCode()!=null ){
			     getProvinciaCodeAux=departamentoprovincia.getProvincia().getCode();
		   	  }
				   Object getDepartamentoCodeAux=null;
		     if (departamentoprovincia.getDepartamento()!= null  && departamentoprovincia.getDepartamento().getCode()!=null ){
			     getDepartamentoCodeAux=departamentoprovincia.getDepartamento().getCode();
		   	  }
      this.jdbcTemplate.update(query, departamentoprovincia.getCode(), getProvinciaCodeAux, getDepartamentoCodeAux, departamentoprovincia.getDescEs(), departamentoprovincia.getDescEu(), departamentoprovincia.getCss());
      return departamentoprovincia;
    }

    /**
     * Updates a single row in the DepartamentoProvincia table.
     *
     * @param departamentoprovincia Pagination
     * @return DepartamentoProvincia
     */
    public DepartamentoProvincia update(DepartamentoProvincia departamentoprovincia) {
      String query = "UPDATE DEPARTAMENTO_PROVINCIA SET CODE_PROVINCIA=?,CODE_DEPARTAMENTO=?,DESC_ES=?,DESC_EU=?,CSS=? WHERE CODE=?";
				   Object getProvinciaCodeAux=null;
		     if (departamentoprovincia.getProvincia()!= null   && departamentoprovincia.getProvincia().getCode()!=null ){
			     getProvinciaCodeAux=departamentoprovincia.getProvincia().getCode();
		   	  }
				   Object getDepartamentoCodeAux=null;
		     if (departamentoprovincia.getDepartamento()!= null   && departamentoprovincia.getDepartamento().getCode()!=null ){
			     getDepartamentoCodeAux=departamentoprovincia.getDepartamento().getCode();
		   	  }
      this.jdbcTemplate.update(query, getProvinciaCodeAux, getDepartamentoCodeAux, departamentoprovincia.getDescEs(), departamentoprovincia.getDescEu(), departamentoprovincia.getCss(), departamentoprovincia.getCode());
      return departamentoprovincia;

    }

    /**
     * Finds a single row in the DepartamentoProvincia table.
     *
     * @param departamentoprovincia Pagination
     * @return DepartamentoProvincia
     */
    @Transactional (readOnly = true)
    public DepartamentoProvincia find(DepartamentoProvincia departamentoprovincia) {
      String query = "SELECT t1.CODE CODE, t1.DESC_ES DESCES, t1.DESC_EU DESCEU, t1.CSS CSS, t2.CODE PROVINCIACODE, t2.DESC_ES PROVINCIADESCES, t2.DESC_EU PROVINCIADESCEU, t2.CSS PROVINCIACSS, t3.CODE DEPARTAMENTOCODE, t3.DESC_ES DEPARTAMENTODESCES, t3.DESC_EU DEPARTAMENTODESCEU, t3.CSS DEPARTAMENTOCSS " 
         + "FROM DEPARTAMENTO_PROVINCIA t1 , PROVINCIA t2 , DEPARTAMENTO t3  " 
         + "WHERE t1.CODE = ?   AND t1.CODE_PROVINCIA= t2.CODE(+) AND t1.CODE_DEPARTAMENTO= t3.CODE(+)  ";
		  return (DepartamentoProvincia) this.jdbcTemplate.queryForObject(query, 
        rwMap , departamentoprovincia.getCode());	 
    }

    /**
     * Removes a single row in the DepartamentoProvincia table.
     *
     * @param departamentoprovincia Pagination
     *
     */
    public void remove(DepartamentoProvincia departamentoprovincia) {
      String query = "DELETE  FROM DEPARTAMENTO_PROVINCIA WHERE CODE=?";
      this.jdbcTemplate.update(query, departamentoprovincia.getCode());

    }
   /**
    * Finds a List of rows in the DepartamentoProvincia table.
    * 
    * @param departamentoprovincia DepartamentoProvincia
    * @param pagination Pagination
    * @return List 
    */
@Transactional (readOnly = true)
    public List<DepartamentoProvincia> findAll(DepartamentoProvincia departamentoprovincia, Pagination pagination) {
      StringBuffer where = new StringBuffer(3000);
      List<Object> params = new ArrayList<Object>();
      where.append(" WHERE 1=1 AND t1.CODE_PROVINCIA= t2.CODE(+) AND t1.CODE_DEPARTAMENTO= t3.CODE(+) 	");
      StringBuffer query = new StringBuffer("SELECT  t1.CODE CODE,t1.DESC_ES DESCES,t1.DESC_EU DESCEU,t1.CSS CSS,t2.CODE PROVINCIACODE,t2.DESC_ES PROVINCIADESCES,t2.DESC_EU PROVINCIADESCEU,t2.CSS PROVINCIACSS,t3.CODE DEPARTAMENTOCODE,t3.DESC_ES DEPARTAMENTODESCES,t3.DESC_EU DEPARTAMENTODESCEU,t3.CSS DEPARTAMENTOCSS " 
        + "FROM DEPARTAMENTO_PROVINCIA t1 ,PROVINCIA t2 ,DEPARTAMENTO t3 ");
        if (departamentoprovincia  != null  && departamentoprovincia.getCode() != null ) {
           where.append(" AND t1.CODE = ?");
           params.add(departamentoprovincia.getCode());
        }
        if (departamentoprovincia!=null && departamentoprovincia.getProvincia() != null  && departamentoprovincia.getProvincia().getCode() != null ) {
           where.append(" AND t2.CODE = ?");
           params.add(departamentoprovincia.getProvincia().getCode());
        }
        if (departamentoprovincia!=null && departamentoprovincia.getProvincia() != null  && departamentoprovincia.getProvincia().getDescEs() != null ) {
           where.append(" AND t2.DESC_ES = ?");
           params.add(departamentoprovincia.getProvincia().getDescEs());
        }
        if (departamentoprovincia!=null && departamentoprovincia.getProvincia() != null  && departamentoprovincia.getProvincia().getDescEu() != null ) {
           where.append(" AND t2.DESC_EU = ?");
           params.add(departamentoprovincia.getProvincia().getDescEu());
        }
        if (departamentoprovincia!=null && departamentoprovincia.getProvincia() != null  && departamentoprovincia.getProvincia().getCss() != null ) {
           where.append(" AND t2.CSS = ?");
           params.add(departamentoprovincia.getProvincia().getCss());
        }
        if (departamentoprovincia!=null && departamentoprovincia.getDepartamento() != null  && departamentoprovincia.getDepartamento().getCode() != null ) {
           where.append(" AND t3.CODE = ?");
           params.add(departamentoprovincia.getDepartamento().getCode());
        }
        if (departamentoprovincia!=null && departamentoprovincia.getDepartamento() != null  && departamentoprovincia.getDepartamento().getDescEs() != null ) {
           where.append(" AND t3.DESC_ES = ?");
           params.add(departamentoprovincia.getDepartamento().getDescEs());
        }
        if (departamentoprovincia!=null && departamentoprovincia.getDepartamento() != null  && departamentoprovincia.getDepartamento().getDescEu() != null ) {
           where.append(" AND t3.DESC_EU = ?");
           params.add(departamentoprovincia.getDepartamento().getDescEu());
        }
        if (departamentoprovincia!=null && departamentoprovincia.getDepartamento() != null  && departamentoprovincia.getDepartamento().getCss() != null ) {
           where.append(" AND t3.CSS = ?");
           params.add(departamentoprovincia.getDepartamento().getCss());
        }
        if (departamentoprovincia  != null  && departamentoprovincia.getDescEs() != null ) {
           where.append(" AND t1.DESC_ES = ?");
           params.add(departamentoprovincia.getDescEs());
        }
        if (departamentoprovincia  != null  && departamentoprovincia.getDescEu() != null ) {
           where.append(" AND t1.DESC_EU = ?");
           params.add(departamentoprovincia.getDescEu());
        }
        if (departamentoprovincia  != null  && departamentoprovincia.getCss() != null ) {
           where.append(" AND t1.CSS = ?");
           params.add(departamentoprovincia.getCss());
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
		
		return (List<DepartamentoProvincia>) this.jdbcTemplate.query(query.toString(),rwMap, params.toArray());
    }
    /**
     * Counts rows in the DepartamentoProvincia table.
     * 
     * @param departamentoprovincia DepartamentoProvincia
     * @return Long
     */
    @Transactional (readOnly = true)
     public Long findAllCount(DepartamentoProvincia departamentoprovincia) {

       StringBuffer where = new StringBuffer(3000);
       List<Object> params = new ArrayList<Object>();
       where.append(" WHERE 1=1  and t1.CODE_PROVINCIA= t2.CODE(+) and t1.CODE_DEPARTAMENTO= t3.CODE(+) ");

      StringBuffer query = new StringBuffer("SELECT COUNT(1) FROM  DEPARTAMENTO_PROVINCIA t1   ,  PROVINCIA t2   ,  DEPARTAMENTO t3  ");
          if (departamentoprovincia  != null  && departamentoprovincia.getCode() != null ) {
            where.append(" AND t1.CODE = ?");
            params.add(departamentoprovincia.getCode());
          }
          if (departamentoprovincia!=null && departamentoprovincia.getProvincia() != null  && departamentoprovincia.getProvincia().getCode() != null ) {
            where.append(" AND t2.CODE = ?");
            params.add(departamentoprovincia.getProvincia().getCode());
          }
          if (departamentoprovincia!=null && departamentoprovincia.getProvincia() != null  && departamentoprovincia.getProvincia().getDescEs() != null ) {
            where.append(" AND t2.DESC_ES = ?");
            params.add(departamentoprovincia.getProvincia().getDescEs());
          }
          if (departamentoprovincia!=null && departamentoprovincia.getProvincia() != null  && departamentoprovincia.getProvincia().getDescEu() != null ) {
            where.append(" AND t2.DESC_EU = ?");
            params.add(departamentoprovincia.getProvincia().getDescEu());
          }
          if (departamentoprovincia!=null && departamentoprovincia.getProvincia() != null  && departamentoprovincia.getProvincia().getCss() != null ) {
            where.append(" AND t2.CSS = ?");
            params.add(departamentoprovincia.getProvincia().getCss());
          }
          if (departamentoprovincia!=null && departamentoprovincia.getDepartamento() != null  && departamentoprovincia.getDepartamento().getCode() != null ) {
            where.append(" AND t3.CODE = ?");
            params.add(departamentoprovincia.getDepartamento().getCode());
          }
          if (departamentoprovincia!=null && departamentoprovincia.getDepartamento() != null  && departamentoprovincia.getDepartamento().getDescEs() != null ) {
            where.append(" AND t3.DESC_ES = ?");
            params.add(departamentoprovincia.getDepartamento().getDescEs());
          }
          if (departamentoprovincia!=null && departamentoprovincia.getDepartamento() != null  && departamentoprovincia.getDepartamento().getDescEu() != null ) {
            where.append(" AND t3.DESC_EU = ?");
            params.add(departamentoprovincia.getDepartamento().getDescEu());
          }
          if (departamentoprovincia!=null && departamentoprovincia.getDepartamento() != null  && departamentoprovincia.getDepartamento().getCss() != null ) {
            where.append(" AND t3.CSS = ?");
            params.add(departamentoprovincia.getDepartamento().getCss());
          }
          if (departamentoprovincia  != null  && departamentoprovincia.getDescEs() != null ) {
            where.append(" AND t1.DESC_ES = ?");
            params.add(departamentoprovincia.getDescEs());
          }
          if (departamentoprovincia  != null  && departamentoprovincia.getDescEu() != null ) {
            where.append(" AND t1.DESC_EU = ?");
            params.add(departamentoprovincia.getDescEu());
          }
          if (departamentoprovincia  != null  && departamentoprovincia.getCss() != null ) {
            where.append(" AND t1.CSS = ?");
            params.add(departamentoprovincia.getCss());
          }

        query.append(where);

         return this.jdbcTemplate.queryForLong(query.toString(), params.toArray());


    }
  /**
    * Finds rows in the DepartamentoProvincia table using like.
    * 
    * @param departamentoprovincia DepartamentoProvincia
    * @param pagination Pagination
    * @return List 
    */
@Transactional (readOnly = true)
    public List<DepartamentoProvincia> findAllLike(DepartamentoProvincia departamentoprovincia, Pagination pagination, Boolean startsWith) {
      StringBuffer where = new StringBuffer(3000);
      List<Object> params = new ArrayList<Object>();
      where.append(" WHERE 1=1 AND t1.CODE_PROVINCIA= t2.CODE(+) AND t1.CODE_DEPARTAMENTO= t3.CODE(+) 	");
      StringBuffer query = new StringBuffer("SELECT  t1.CODE CODE,t1.DESC_ES DESCES,t1.DESC_EU DESCEU,t1.CSS CSS,t2.CODE PROVINCIACODE,t2.DESC_ES PROVINCIADESCES,t2.DESC_EU PROVINCIADESCEU,t2.CSS PROVINCIACSS,t3.CODE DEPARTAMENTOCODE,t3.DESC_ES DEPARTAMENTODESCES,t3.DESC_EU DEPARTAMENTODESCEU,t3.CSS DEPARTAMENTOCSS " 
        + "FROM DEPARTAMENTO_PROVINCIA t1 ,PROVINCIA t2 ,DEPARTAMENTO t3 ");
        if (departamentoprovincia  != null  && departamentoprovincia.getCode() != null ) {
			where.append(" AND t1.CODE like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(departamentoprovincia.getCode()  +"%");
			}else{
				params.add("%"+departamentoprovincia.getCode() +"%");
			}	
				where.append(" AND t1.CODE IS NOT NULL");
		
        }
        if (departamentoprovincia!=null && departamentoprovincia.getProvincia() != null  && departamentoprovincia.getProvincia().getCode() != null ) {
			where.append(" AND t2.CODE like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(departamentoprovincia.getProvincia().getCode()  +"%");
			}else{
				params.add("%"+departamentoprovincia.getProvincia().getCode() +"%");
			}	
				where.append(" AND t2.CODE IS NOT NULL");
		
        }
        if (departamentoprovincia!=null && departamentoprovincia.getProvincia() != null  && departamentoprovincia.getProvincia().getDescEs() != null ) {
			where.append(" AND UPPER(t2.DESC_ES) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(departamentoprovincia.getProvincia().getDescEs().toUpperCase()  +"%");
			}else{
				params.add("%"+departamentoprovincia.getProvincia().getDescEs().toUpperCase() +"%");
			}	
				where.append(" AND t2.DESC_ES IS NOT NULL");
		
        }
        if (departamentoprovincia!=null && departamentoprovincia.getProvincia() != null  && departamentoprovincia.getProvincia().getDescEu() != null ) {
			where.append(" AND UPPER(t2.DESC_EU) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(departamentoprovincia.getProvincia().getDescEu().toUpperCase()  +"%");
			}else{
				params.add("%"+departamentoprovincia.getProvincia().getDescEu().toUpperCase() +"%");
			}	
				where.append(" AND t2.DESC_EU IS NOT NULL");
		
        }
        if (departamentoprovincia!=null && departamentoprovincia.getProvincia() != null  && departamentoprovincia.getProvincia().getCss() != null ) {
			where.append(" AND UPPER(t2.CSS) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(departamentoprovincia.getProvincia().getCss().toUpperCase()  +"%");
			}else{
				params.add("%"+departamentoprovincia.getProvincia().getCss().toUpperCase() +"%");
			}	
				where.append(" AND t2.CSS IS NOT NULL");
		
        }
        if (departamentoprovincia!=null && departamentoprovincia.getDepartamento() != null  && departamentoprovincia.getDepartamento().getCode() != null ) {
			where.append(" AND t3.CODE like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(departamentoprovincia.getDepartamento().getCode()  +"%");
			}else{
				params.add("%"+departamentoprovincia.getDepartamento().getCode() +"%");
			}	
				where.append(" AND t3.CODE IS NOT NULL");
		
        }
        if (departamentoprovincia!=null && departamentoprovincia.getDepartamento() != null  && departamentoprovincia.getDepartamento().getDescEs() != null ) {
			where.append(" AND UPPER(t3.DESC_ES) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(departamentoprovincia.getDepartamento().getDescEs().toUpperCase()  +"%");
			}else{
				params.add("%"+departamentoprovincia.getDepartamento().getDescEs().toUpperCase() +"%");
			}	
				where.append(" AND t3.DESC_ES IS NOT NULL");
		
        }
        if (departamentoprovincia!=null && departamentoprovincia.getDepartamento() != null  && departamentoprovincia.getDepartamento().getDescEu() != null ) {
			where.append(" AND UPPER(t3.DESC_EU) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(departamentoprovincia.getDepartamento().getDescEu().toUpperCase()  +"%");
			}else{
				params.add("%"+departamentoprovincia.getDepartamento().getDescEu().toUpperCase() +"%");
			}	
				where.append(" AND t3.DESC_EU IS NOT NULL");
		
        }
        if (departamentoprovincia!=null && departamentoprovincia.getDepartamento() != null  && departamentoprovincia.getDepartamento().getCss() != null ) {
			where.append(" AND UPPER(t3.CSS) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(departamentoprovincia.getDepartamento().getCss().toUpperCase()  +"%");
			}else{
				params.add("%"+departamentoprovincia.getDepartamento().getCss().toUpperCase() +"%");
			}	
				where.append(" AND t3.CSS IS NOT NULL");
		
        }
        if (departamentoprovincia  != null  && departamentoprovincia.getDescEs() != null ) {
			where.append(" AND UPPER(t1.DESC_ES) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(departamentoprovincia.getDescEs().toUpperCase()  +"%");
			}else{
				params.add("%"+departamentoprovincia.getDescEs().toUpperCase() +"%");
			}	
				where.append(" AND t1.DESC_ES IS NOT NULL");
		
        }
        if (departamentoprovincia  != null  && departamentoprovincia.getDescEu() != null ) {
			where.append(" AND UPPER(t1.DESC_EU) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(departamentoprovincia.getDescEu().toUpperCase()  +"%");
			}else{
				params.add("%"+departamentoprovincia.getDescEu().toUpperCase() +"%");
			}	
				where.append(" AND t1.DESC_EU IS NOT NULL");
		
        }
        if (departamentoprovincia  != null  && departamentoprovincia.getCss() != null ) {
			where.append(" AND UPPER(t1.CSS) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(departamentoprovincia.getCss().toUpperCase()  +"%");
			}else{
				params.add("%"+departamentoprovincia.getCss().toUpperCase() +"%");
			}	
				where.append(" AND t1.CSS IS NOT NULL");
		
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
		
		return (List<DepartamentoProvincia>) this.jdbcTemplate.query(query.toString(),rwMap, params.toArray());
    }

}

