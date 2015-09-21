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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.model.Comarca;
import com.ejie.x21a.model.Provincia;
import com.ejie.x21a.model.Usuario;
import com.ejie.x38.dao.RowNumResultSetExtractor;
import com.ejie.x38.dto.JQGridManager;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.Pagination;
import com.ejie.x38.dto.TableRowDto;

/**
 * ComarcaDaoImpl generated by UDA, 14-ago-2012 12:59:38.
 * @author UDA
 */
 
@Repository
@Transactional
public class JQGridComarcaDaoImpl implements JQGridComarcaDao {
    private JdbcTemplate jdbcTemplate;
	private RowMapper<Comarca> rwMap = new RowMapper<Comarca>() {
		public Comarca mapRow(ResultSet resultSet, int rowNum) throws SQLException {
           return new Comarca(
               resultSet.getBigDecimal("CODE"), resultSet.getString("DESCES"), resultSet.getString("DESCEU"), resultSet.getString("CSS"), new Provincia(resultSet.getBigDecimal("PROVINCIACODE"), resultSet.getString("PROVINCIADESCES"), resultSet.getString("PROVINCIADESCEU"), resultSet.getString("PROVINCIACSS"))
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
     * Inserts a single row in the Comarca table.
     *
     * @param comarca Pagination
     * @return Comarca
     */
	public Comarca add(Comarca comarca) {
    	String query = "INSERT INTO COMARCA (CODE, CODE_PROVINCIA, DESC_ES, DESC_EU, CSS) VALUES (?,?,?,?,?)";
				   Object getProvinciaCodeAux=null;
		     if (comarca.getProvincia()!= null  && comarca.getProvincia().getCode()!=null ){
			     getProvinciaCodeAux=comarca.getProvincia().getCode();
		   	  }
		this.jdbcTemplate.update(query, comarca.getCode(), getProvinciaCodeAux, comarca.getDescEs(), comarca.getDescEu(), comarca.getCss());
		return comarca;
	}

    /**
     * Updates a single row in the Comarca table.
     *
     * @param comarca Pagination
     * @return Comarca
     */
    public Comarca update(Comarca comarca) {
		String query = "UPDATE COMARCA SET CODE_PROVINCIA=?, DESC_ES=?, DESC_EU=?, CSS=? WHERE CODE=?";
				Object getProvinciaCodeAux=null;
				if (comarca.getProvincia()!= null   && comarca.getProvincia().getCode()!=null ){
					getProvinciaCodeAux=comarca.getProvincia().getCode();
				}
		this.jdbcTemplate.update(query, getProvinciaCodeAux, comarca.getDescEs(), comarca.getDescEu(), comarca.getCss(), comarca.getCode());
		
		return comarca;
	}

    /**
     * Finds a single row in the Comarca table.
     *
     * @param comarca Pagination
     * @return Comarca
     */
    @Transactional (readOnly = true)
    public Comarca find(Comarca comarca) {
		String query = "SELECT t1.CODE CODE, t1.DESC_ES DESCES, t1.DESC_EU DESCEU, t1.CSS CSS, t2.CODE PROVINCIACODE, t2.DESC_ES PROVINCIADESCES, t2.DESC_EU PROVINCIADESCEU, t2.CSS PROVINCIACSS FROM COMARCA t1 , PROVINCIA t2  WHERE t1.CODE = ?   AND t1.CODE_PROVINCIA= t2.CODE(+)";
		
		List<Comarca> comarcaList = this.jdbcTemplate.query(query, this.rwMap, comarca.getCode());
		return (Comarca) DataAccessUtils.uniqueResult(comarcaList);
    }

    /**
     * Removes a single row in the Comarca table.
     *
     * @param comarca Pagination
     * @return
     */
    public void remove(Comarca comarca) {
		String query = "DELETE FROM COMARCA WHERE CODE=?";
		this.jdbcTemplate.update(query, comarca.getCode());
    }
    
   /**
    * Finds a List of rows in the Comarca table.
    * 
    * @param comarca Comarca
    * @param pagination Pagination
    * @return List 
    */
	@Transactional (readOnly = true)
    public List<Comarca> findAll(Comarca comarca, Pagination pagination) {
		StringBuilder query = new StringBuilder("SELECT  t1.CODE CODE,t1.DESC_ES DESCES,t1.DESC_EU DESCEU,t1.CSS CSS,t2.CODE PROVINCIACODE,t2.DESC_ES PROVINCIADESCES,t2.DESC_EU PROVINCIADESCEU,t2.CSS PROVINCIACSS "); 
		query.append("FROM COMARCA t1 ,PROVINCIA t2 ");
		
		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereMap(comarca); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 AND t1.CODE_PROVINCIA= t2.CODE(+) ");
		where.append(mapaWhere.get("query"));
		query.append(where);
		
		List<?> params = (List<?>) mapaWhere.get("params");

		if (pagination != null) {
			query = pagination.getPaginationQuery(query);
		}
		
		return (List<Comarca>) this.jdbcTemplate.query(query.toString(), this.rwMap, params.toArray());
	}
	
    /**
     * Counts rows in the Comarca table.
     * 
     * @param comarca Comarca
     * @return Long
     */
    @Transactional (readOnly = true)
    public Long findAllCount(Comarca comarca) {
		StringBuilder query = new StringBuilder("SELECT COUNT(1) FROM COMARCA t1 , PROVINCIA t2 ");
		
		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereMap(comarca); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 AND t1.CODE_PROVINCIA= t2.CODE(+) ");
		where.append(mapaWhere.get("query"));
		query.append(where);		
		
		List<?> params = (List<?>) mapaWhere.get("params");
		
		return this.jdbcTemplate.queryForLong(query.toString(), params.toArray());
	}
	
	/**
	 * Finds rows in the Comarca table using like.
     * 
     * @param comarca Comarca
     * @param pagination Pagination
     * @param startsWith Boolean
     * @return List 
     */
	@Transactional (readOnly = true)
    public List<Comarca> findAllLike(Comarca comarca, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		StringBuilder query = new StringBuilder("SELECT  t1.CODE CODE,t1.DESC_ES DESCES,t1.DESC_EU DESCEU,t1.CSS CSS,t2.CODE PROVINCIACODE,t2.DESC_ES PROVINCIADESCES,t2.DESC_EU PROVINCIADESCEU,t2.CSS PROVINCIACSS "); 
        query.append("FROM COMARCA t1 ,PROVINCIA t2 ");
      	
		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereLikeMap(comarca,startsWith); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 AND t1.CODE_PROVINCIA= t2.CODE(+) ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		if (jqGridRequestDto != null) {
			query = JQGridManager.getPaginationQuery(jqGridRequestDto, query);
		}
		
		return (List<Comarca>) this.jdbcTemplate.query(query.toString(), this.rwMap, params.toArray());
	}
	
	/**
	 * Counts rows in the Comarca table using like.
     * 
     * @param comarca Comarca
     * @param startsWith Boolean
     * @return Long 
     */
	@Transactional (readOnly = true)
    public Long findAllLikeCount(Comarca comarca, Boolean startsWith) {
		StringBuilder query = new StringBuilder("SELECT COUNT(1) FROM COMARCA t1 ,PROVINCIA t2 ");

		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereLikeMap(comarca,startsWith); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 AND t1.CODE_PROVINCIA= t2.CODE(+) ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		return this.jdbcTemplate.queryForLong(query.toString(), params.toArray());
	}
	
	/**
	 * Returns a map with the needed value to create the conditions to filter by 
	 * the Comarca entity 
	 * 
	 * @param comarca Comarca
	 *            Bean with the criteria values to filter by.
	 * @return Map created with two keys
	 *         key query stores the sql query syntax
	 *         key params stores the parameter values to be used in the condition sentence.
	 */
	// CHECKSTYLE:OFF CyclomaticComplexity - Generación de código de UDA
	private Map<String, ?> getWhereMap (Comarca comarca){
		
		StringBuffer where = new StringBuffer(JQGridComarcaDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();

		if (comarca  != null  && comarca.getCode() != null ) {
			where.append(" AND t1.CODE = ?");
			params.add(comarca.getCode());
		}
		if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getCode() != null ) {
			where.append(" AND t2.CODE = ?");
			params.add(comarca.getProvincia().getCode());
		}
		if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getDescEs() != null ) {
			where.append(" AND t2.DESC_ES = ?");
			params.add(comarca.getProvincia().getDescEs());
		}
		if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getDescEu() != null ) {
			where.append(" AND t2.DESC_EU = ?");
			params.add(comarca.getProvincia().getDescEu());
		}
		if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getCss() != null ) {
			where.append(" AND t2.CSS = ?");
			params.add(comarca.getProvincia().getCss());
		}
		if (comarca  != null  && comarca.getDescEs() != null ) {
			where.append(" AND t1.DESC_ES = ?");
			params.add(comarca.getDescEs());
		}
		if (comarca  != null  && comarca.getDescEu() != null ) {
			where.append(" AND t1.DESC_EU = ?");
			params.add(comarca.getDescEu());
		}
		if (comarca  != null  && comarca.getCss() != null ) {
			where.append(" AND t1.CSS = ?");
			params.add(comarca.getCss());
		}

		Map<String,Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);
		
		return mapWhere;		
	}
	// CHECKSTYLE:ON CyclomaticComplexity - Generación de código de UDA
	
	/**
	 * Returns a map with the needed value to create the conditions to filter by  
	 * the Comarca entity 
	 * 
	 * @param comarca Comarca
	 *            Bean with the criteria values to filter by.
     * @param startsWith Boolean	 
	 * @return Map created with two keys
	 *         key query stores the sql query syntax
	 *         key params stores the parameter values to be used in the condition sentence.
	 */
	// CHECKSTYLE:OFF CyclomaticComplexity - Generación de código de UDA
	private Map<String, Object> getWhereLikeMap (Comarca comarca, Boolean startsWith){
		
		StringBuffer where = new StringBuffer(JQGridComarcaDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();

		if (comarca  != null  && comarca.getCode() != null ) {
			where.append(" AND t1.CODE = ?");
			params.add(comarca.getCode());
	     }			
		if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getCode() != null ) {
			where.append(" AND t2.CODE = ?");
			params.add(comarca.getProvincia().getCode());
	     }			
		if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getDescEs() != null ) {
			where.append(" AND UPPER(t2.DESC_ES) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(comarca.getProvincia().getDescEs().toUpperCase() +"%");
			}else{
				params.add("%"+comarca.getProvincia().getDescEs().toUpperCase() +"%");
			}
			where.append(" AND t2.DESC_ES IS NOT NULL");
	     }			
		if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getDescEu() != null ) {
			where.append(" AND UPPER(t2.DESC_EU) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(comarca.getProvincia().getDescEu().toUpperCase() +"%");
			}else{
				params.add("%"+comarca.getProvincia().getDescEu().toUpperCase() +"%");
			}
			where.append(" AND t2.DESC_EU IS NOT NULL");
	     }			
		if (comarca!=null && comarca.getProvincia() != null  && comarca.getProvincia().getCss() != null ) {
			where.append(" AND UPPER(t2.CSS) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(comarca.getProvincia().getCss().toUpperCase() +"%");
			}else{
				params.add("%"+comarca.getProvincia().getCss().toUpperCase() +"%");
			}
			where.append(" AND t2.CSS IS NOT NULL");
	     }			
		if (comarca  != null  && comarca.getDescEs() != null ) {
			where.append(" AND UPPER(t1.DESC_ES) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(comarca.getDescEs().toUpperCase() +"%");
			}else{
				params.add("%"+comarca.getDescEs().toUpperCase() +"%");
			}
			where.append(" AND t1.DESC_ES IS NOT NULL");
	     }			
		if (comarca  != null  && comarca.getDescEu() != null ) {
			where.append(" AND UPPER(t1.DESC_EU) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(comarca.getDescEu().toUpperCase() +"%");
			}else{
				params.add("%"+comarca.getDescEu().toUpperCase() +"%");
			}
			where.append(" AND t1.DESC_EU IS NOT NULL");
	     }			
		if (comarca  != null  && comarca.getCss() != null ) {
			where.append(" AND UPPER(t1.CSS) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(comarca.getCss().toUpperCase() +"%");
			}else{
				params.add("%"+comarca.getCss().toUpperCase() +"%");
			}
			where.append(" AND t1.CSS IS NOT NULL");
	     }			

		Map<String,Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);
		
		return mapWhere;		
	}
	
	 private RowMapper<Comarca> rwMapPK = new RowMapper<Comarca>() {
			public Comarca mapRow(ResultSet resultSet, int rowNum) throws SQLException {
	           return new Comarca(resultSet.getBigDecimal("CODE")); 
	        } 
	 };
	
	@Override
	public List<TableRowDto<Comarca>> reorderSelection(Comarca comarca, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith) {
		
		// SELECT
		StringBuilder sbSQL = new StringBuilder("SELECT  t1.ID ID,t1.NOMBRE NOMBRE,t1.APELLIDO1 APELLIDO1,t1.APELLIDO2 APELLIDO2,t1.EJIE EJIE,t1.FECHA_ALTA FECHAALTA,t1.FECHA_BAJA FECHABAJA,t1.ROL ROL ");
		
		// FROM
        sbSQL.append("FROM USUARIO t1 ");
        
		// FILTRADO 
		Map<String, ?> mapaWhere = this.getWhereLikeMap(comarca, startsWith);
		// Claula where  de filtrado
		sbSQL.append(" WHERE 1=1 ").append(mapaWhere.get("query"));
		// Parámetros de filtrado
		@SuppressWarnings("unchecked")
		List<Object> filterParamList = (List<Object>) mapaWhere.get("params");		
		
		// SQL para la reordenación
		StringBuilder sbReorderSelectionSQL = JQGridManager.getReorderQuery(sbSQL, jqGridRequestDto, Comarca.class, filterParamList, "ID");
		
		return this.jdbcTemplate.query(sbReorderSelectionSQL.toString(), new RowNumResultSetExtractor<Comarca>(this.rwMapPK, jqGridRequestDto), filterParamList.toArray());
	}
	
	
	
	@Override
	public List<TableRowDto<Comarca>> search(Comarca filterParams, Comarca searchParams, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		
		// SELECT 
		StringBuilder sbSQL = new StringBuilder("SELECT t1.CODE CODE, t1.DESC_ES DESC_ES, t1.DESC_EU DESC_EU, t1.CSS CSS, t2.CODE PROVINCIACODE ");
		
		// FROM
		sbSQL.append("FROM COMARCA t1, PROVINCIA t2 ");
      	
		//TABLAS_ALIAS
		List<String> from_alias = new ArrayList<String>();
		from_alias.add("t1");
		from_alias.add("t2");
		
		// FILTRADO
		// Mapa de filtrado
		Map<String, Object> mapaWhereFilter = this.getWhereLikeMap(filterParams, startsWith); 
		// Claula where  de filtrado
		sbSQL.append(" WHERE t1.CODE_PROVINCIA = t2.CODE  ").append(mapaWhereFilter.get("query"));
		// Parámetros de filtrado
		@SuppressWarnings("unchecked")
		List<Object> filterParamList = (List<Object>) mapaWhereFilter.get("params");
		
		// BUSQUEDA
		Map<String, Object> mapaWhereSearch = this.getWhereLikeMap(searchParams, startsWith);
		// Claula where  de búsqueda
		String searchSQL = ((StringBuffer) mapaWhereSearch.get("query")).toString();
		// Parámetros de búsqueda
		@SuppressWarnings("unchecked")
		List<Object> searchParamList = (List<Object>) mapaWhereSearch.get("params");
		

		// SQL para la busqueda
		StringBuilder sbReorderSelectionSQL = JQGridManager.getSearchQuery(sbSQL, jqGridRequestDto, Comarca.class, filterParamList, searchSQL, searchParamList, from_alias, "CODE");
				
		return this.jdbcTemplate.query(sbReorderSelectionSQL.toString(), new RowNumResultSetExtractor<Comarca>(this.rwMapPK, jqGridRequestDto), filterParamList.toArray());
	}
	
	// CHECKSTYLE:ON CyclomaticComplexity - Generación de código de UDA
	
	/**
	 * StringBuilder initilization value
	 */
	 public static final int STRING_BUILDER_INIT = 4096;}

