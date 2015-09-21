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

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.model.Usuario;
import com.ejie.x38.dao.RowNumResultSetExtractor;
import com.ejie.x38.dto.JQGridManager;
import com.ejie.x38.dto.JQGridManagerJerarquia;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JerarquiaDto;
import com.ejie.x38.dto.TableRowDto;

/**
 * UsuarioDaoImpl generated by UDA, 14-ago-2012 12:59:38.
 * @author UDA
 */
 
@Repository
@Transactional
public class JQGridUsuarioJerarquiaDaoImpl implements JQGridUsuarioJerarquiaDao {
    private JdbcTemplate jdbcTemplate;
	private RowMapper<Usuario> rwMap = new RowMapper<Usuario>() {
		public Usuario mapRow(ResultSet resultSet, int rowNum) throws SQLException {
           Usuario usuario =  new Usuario(
               resultSet.getString("ID"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO1"), resultSet.getString("APELLIDO2"), resultSet.getString("EJIE"), resultSet.getDate("FECHAALTA"), resultSet.getDate("FECHABAJA"), resultSet.getString("ROL")
           ); 
           return usuario;
          } 
	};
	
	private RowMapper<Usuario> ususarioRowMapper = new RowMapper<Usuario>() {
		public Usuario mapRow(ResultSet resultSet, int rowNum) throws SQLException {
           Usuario usuario =  new Usuario(
               resultSet.getString("ID"), resultSet.getString("NOMBRE"), resultSet.getString("APELLIDO1"), resultSet.getString("APELLIDO2"), resultSet.getString("EJIE"), resultSet.getDate("FECHAALTA"), resultSet.getDate("FECHABAJA"), resultSet.getString("ROL")
           ); 
           
           usuario.setIdPadre(resultSet.getString("ID_PADRE"));
           
           return usuario;
          } 
	};

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
     * Inserts a single row in the Usuario table.
     *
     * @param usuario Pagination
     * @return Usuario
     */
	public Usuario add(Usuario usuario) {
		
		final long nextId = jdbcTemplate
				.queryForLong("SELECT USUARIO_JERARQUIA_SEQ.NEXTVAL FROM DUAL");
		
    	String query = "INSERT INTO USUARIO_JERARQUIA (ID, NOMBRE, APELLIDO1, APELLIDO2, EJIE, FECHA_ALTA, FECHA_BAJA, ID_PADRE, ROL) VALUES (?,?,?,?,?,?,?,?,?)";
		this.jdbcTemplate.update(query, nextId, usuario.getNombre(), usuario.getApellido1(), usuario.getApellido2(), usuario.getEjie(), usuario.getFechaAlta(), usuario.getFechaBaja(), usuario.getIdPadre(), usuario.getRol());
		
		usuario.setId(String.valueOf(nextId));
		
		return usuario;
	}

    /**
     * Updates a single row in the Usuario table.
     *
     * @param usuario Pagination
     * @return Usuario
     */
    public Usuario update(Usuario usuario) {
		String query = "UPDATE USUARIO_JERARQUIA SET NOMBRE=?, APELLIDO1=?, APELLIDO2=?, EJIE=?, FECHA_ALTA=?, FECHA_BAJA=?, ROL=?, ID_PADRE=? WHERE ID=?";
		this.jdbcTemplate.update(query, usuario.getNombre(), usuario.getApellido1(), usuario.getApellido2(), usuario.getEjie(), usuario.getFechaAlta(), usuario.getFechaBaja(), usuario.getRol(), usuario.getIdPadre(), usuario.getId());
		return usuario;
	}

    /**
     * Finds a single row in the Usuario table.
     *
     * @param usuario Pagination
     * @return Usuario
     */
    @Transactional (readOnly = true)
    public Usuario find(Usuario usuario) {
		String query = "SELECT t1.ID ID, t1.NOMBRE NOMBRE, t1.APELLIDO1 APELLIDO1, t1.APELLIDO2 APELLIDO2, t1.EJIE EJIE, t1.FECHA_ALTA FECHAALTA, t1.FECHA_BAJA FECHABAJA, t1.ROL ROL, t1.ID_PADRE ID_PADRE FROM USUARIO_JERARQUIA t1  WHERE t1.ID = ?  ";
		
		List<Usuario> usuarioList = this.jdbcTemplate.query(query, this.ususarioRowMapper, usuario.getId());
		return (Usuario) DataAccessUtils.uniqueResult(usuarioList);
    }

    /**
     * Removes a single row in the Usuario table.
     *
     * @param usuario Pagination
     * @return
     */
    public void remove(Usuario usuario) {
		String query = "DELETE FROM USUARIO_JERARQUIA WHERE ID=?";
		this.jdbcTemplate.update(query, usuario.getId());
    }
    
   /**
    * Finds a List of rows in the Usuario table.
    * 
    * @param usuario Usuario
    * @param pagination Pagination
    * @return List 
    */
	@Transactional (readOnly = true)
    public List<Usuario> findAll(Usuario usuario, JQGridRequestDto jqGridRequestDto) {
		StringBuilder query = new StringBuilder("SELECT  t1.ID ID, t1.NOMBRE NOMBRE, t1.APELLIDO1 APELLIDO1, t1.APELLIDO2 APELLIDO2, t1.EJIE EJIE, t1.FECHA_ALTA FECHAALTA, t1.FECHA_BAJA FECHABAJA, t1.ROL ROL "); 
		query.append("FROM USUARIO_JERARQUIA t1 ");
		
		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereMap(usuario); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);
		
		List<?> params = (List<?>) mapaWhere.get("params");

		if (jqGridRequestDto != null) {
			query = JQGridManager.getPaginationQuery(jqGridRequestDto, query);
		}
		
		return (List<Usuario>) this.jdbcTemplate.query(query.toString(), this.rwMap, params.toArray());
	}
	
    /**
     * Counts rows in the Usuario table.
     * 
     * @param usuario Usuario
     * @return Long
     */
    @Transactional (readOnly = true)
    public Long findAllCount(Usuario usuario) {
		StringBuilder query = new StringBuilder("SELECT COUNT(1) FROM USUARIO_JERARQUIA t1 ");
		
		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereMap(usuario); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);		
		
		List<?> params = (List<?>) mapaWhere.get("params");
		
		return this.jdbcTemplate.queryForLong(query.toString(), params.toArray());
	}
	
	/**
	 * Finds rows in the Usuario table using like.
     * 
     * @param usuario Usuario
     * @param pagination Pagination
     * @param startsWith Boolean
     * @return List 
     * 
     * 
     */
	@Transactional (readOnly = true)
    public List<Usuario> findAllLike(Usuario usuario, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		StringBuilder query = new StringBuilder("SELECT  t1.ID ID,t1.NOMBRE NOMBRE,t1.APELLIDO1 APELLIDO1,t1.APELLIDO2 APELLIDO2,t1.EJIE EJIE,t1.FECHA_ALTA FECHAALTA,t1.FECHA_BAJA FECHABAJA,t1.ROL ROL "); 
        query.append("FROM USUARIO_JERARQUIA t1 ");
      	
		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereLikeMap(usuario,startsWith); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		if (jqGridRequestDto != null) {
			query = JQGridManager.getPaginationQuery(jqGridRequestDto, query);
		}
		
		return (List<Usuario>) this.jdbcTemplate.query(query.toString(), this.rwMap, params.toArray());
	}
	
	/**
	 * Counts rows in the Usuario table using like.
     * 
     * @param usuario Usuario
     * @param startsWith Boolean
     * @return Long 
     */
	@Transactional (readOnly = true)
    public Long findAllLikeCount(Usuario usuario, Boolean startsWith) {
		StringBuilder query = new StringBuilder("SELECT COUNT(1) FROM USUARIO_JERARQUIA t1 ");

		//Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereLikeMap(usuario,startsWith); 
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		return this.jdbcTemplate.queryForLong(query.toString(), params.toArray());
	}
	
	/**
	 * Returns a map with the needed value to create the conditions to filter by 
	 * the Usuario entity 
	 * 
	 * @param usuario Usuario
	 *            Bean with the criteria values to filter by.
	 * @return Map created with two keys
	 *         key query stores the sql query syntax
	 *         key params stores the parameter values to be used in the condition sentence.
	 */
	// CHECKSTYLE:OFF CyclomaticComplexity - Generación de código de UDA
	private Map<String, ?> getWhereMap (Usuario usuario){
		
		StringBuffer where = new StringBuffer(JQGridUsuarioJerarquiaDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();

		if (usuario  != null  && usuario.getId() != null ) {
			where.append(" AND t1.ID = ?");
			params.add(usuario.getId());
		}
		if (usuario  != null  && usuario.getNombre() != null ) {
			where.append(" AND t1.NOMBRE = ?");
			params.add(usuario.getNombre());
		}
		if (usuario  != null  && usuario.getApellido1() != null ) {
			where.append(" AND t1.APELLIDO1 = ?");
			params.add(usuario.getApellido1());
		}
		if (usuario  != null  && usuario.getApellido2() != null ) {
			where.append(" AND t1.APELLIDO2 = ?");
			params.add(usuario.getApellido2());
		}
		if (usuario  != null  && usuario.getEjie() != null ) {
			where.append(" AND t1.EJIE = ?");
			params.add(usuario.getEjie());
		}
		if (usuario  != null  && usuario.getFechaAlta() != null ) {
			where.append(" AND t1.FECHA_ALTA = ?");
			params.add(usuario.getFechaAlta());
		}
		if (usuario  != null  && usuario.getFechaBaja() != null ) {
			where.append(" AND t1.FECHA_BAJA = ?");
			params.add(usuario.getFechaBaja());
		}
		if (usuario  != null  && usuario.getRol() != null ) {
			where.append(" AND t1.ROL = ?");
			params.add(usuario.getRol());
		}

		Map<String,Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);
		
		return mapWhere;		
	}
	// CHECKSTYLE:ON CyclomaticComplexity - Generación de código de UDA
	
	/**
	 * Returns a map with the needed value to create the conditions to filter by  
	 * the Usuario entity 
	 * 
	 * @param usuario Usuario
	 *            Bean with the criteria values to filter by.
     * @param startsWith Boolean	 
	 * @return Map created with two keys
	 *         key query stores the sql query syntax
	 *         key params stores the parameter values to be used in the condition sentence.
	 */
	// CHECKSTYLE:OFF CyclomaticComplexity - Generación de código de UDA
	private Map<String, Object> getWhereLikeMap (Usuario usuario, Boolean startsWith){
		
		StringBuffer where = new StringBuffer(JQGridUsuarioJerarquiaDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();

		if (usuario  != null  && usuario.getId() != null ) {
			where.append(" AND UPPER(t1.ID) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuario.getId().toUpperCase() +"%");
			}else{
				params.add("%"+usuario.getId().toUpperCase() +"%");
			}
			where.append(" AND t1.ID IS NOT NULL");
	     }			
		if (usuario  != null  && usuario.getNombre() != null ) {
			where.append(" AND UPPER(t1.NOMBRE) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuario.getNombre().toUpperCase() +"%");
			}else{
				params.add("%"+usuario.getNombre().toUpperCase() +"%");
			}
			where.append(" AND t1.NOMBRE IS NOT NULL");
	     }			
		if (usuario  != null  && usuario.getApellido1() != null ) {
			where.append(" AND UPPER(t1.APELLIDO1) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuario.getApellido1().toUpperCase() +"%");
			}else{
				params.add("%"+usuario.getApellido1().toUpperCase() +"%");
			}
			where.append(" AND t1.APELLIDO1 IS NOT NULL");
	     }			
		if (usuario  != null  && usuario.getApellido2() != null ) {
			where.append(" AND UPPER(t1.APELLIDO2) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuario.getApellido2().toUpperCase() +"%");
			}else{
				params.add("%"+usuario.getApellido2().toUpperCase() +"%");
			}
			where.append(" AND t1.APELLIDO2 IS NOT NULL");
	     }			
		if (usuario  != null  && usuario.getEjie() != null ) {
			where.append(" AND UPPER(t1.EJIE) like ? ESCAPE  '\\'");
			if (startsWith){
				params.add(usuario.getEjie().toUpperCase() +"%");
			}else{
				params.add("%"+usuario.getEjie().toUpperCase() +"%");
			}
			where.append(" AND t1.EJIE IS NOT NULL");
	     }			
		if (usuario  != null  && usuario.getFechaAlta() != null ) {
			where.append(" AND t1.FECHA_ALTA = ?");
			params.add(usuario.getFechaAlta());
	     }			
		if (usuario  != null  && usuario.getFechaBaja() != null ) {
			where.append(" AND t1.FECHA_BAJA = ?");
			params.add(usuario.getFechaBaja());
	    }		
		if (usuario  != null  && usuario.getRol() != null ) {
			where.append(" AND t1.ROL = ?");
			params.add(usuario.getRol());
	    }

		Map<String,Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);
		
		return mapWhere;		
	}
	// CHECKSTYLE:ON CyclomaticComplexity - Generación de código de UDA
	
	
	@Override
	public List<TableRowDto<Usuario>> reorderSelection(Usuario usuario, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith) {
		
		// SELECT
		StringBuilder sbSQL = new StringBuilder("SELECT  t1.ID ID,t1.NOMBRE NOMBRE,t1.APELLIDO1 APELLIDO1,t1.APELLIDO2 APELLIDO2,t1.EJIE EJIE,t1.FECHA_ALTA FECHAALTA,t1.FECHA_BAJA FECHABAJA,t1.ROL ROL ");
		
		// FROM
        sbSQL.append("FROM USUARIO_JERARQUIA t1 ");
        
		// FILTRADO 
		Map<String, ?> mapaWhere = this.getWhereLikeMap(usuario, startsWith);
		// Claula where  de filtrado
		sbSQL.append(" WHERE 1=1 ").append(mapaWhere.get("query"));
		// Parámetros de filtrado
		@SuppressWarnings("unchecked")
		List<Object> filterParamList = (List<Object>) mapaWhere.get("params");		
		
		// SQL para la reordenación
		StringBuilder sbReorderSelectionSQL = JQGridManager.getReorderQuery(sbSQL, jqGridRequestDto, Usuario.class, filterParamList, "ID");
		
		return this.jdbcTemplate.query(sbReorderSelectionSQL.toString(), new RowNumResultSetExtractor<Usuario>(this.rwMapPK, jqGridRequestDto), filterParamList.toArray());
	}
	
	
	
	@Override
	public List<TableRowDto<Usuario>> search(Usuario filterParams, Usuario searchParams, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		
		// SELECT 
		StringBuilder sbSQL = new StringBuilder("SELECT  t1.ID ID,t1.NOMBRE NOMBRE,t1.APELLIDO1 APELLIDO1,t1.APELLIDO2 APELLIDO2,t1.EJIE EJIE,t1.FECHA_ALTA FECHAALTA,t1.FECHA_BAJA FECHABAJA,t1.ROL ROL ");
		
		// FROM
		sbSQL.append("FROM USUARIO_JERARQUIA t1 ");
      	
		//TABLAS_ALIAS
		List<String> from_alias = new ArrayList<String>();
		from_alias.add("t1");
		
		// FILTRADO
		// Mapa de filtrado
		Map<String, Object> mapaWhereFilter = this.getWhereLikeMap(filterParams, startsWith); 
		// Claula where  de filtrado
		sbSQL.append(" WHERE 1=1 ").append(mapaWhereFilter.get("query"));
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
		StringBuilder sbReorderSelectionSQL = JQGridManager.getSearchQuery(sbSQL, jqGridRequestDto, Usuario.class, filterParamList, searchSQL, searchParamList, from_alias, "ID");
				
		return this.jdbcTemplate.query(sbReorderSelectionSQL.toString(), new RowNumResultSetExtractor<Usuario>(this.rwMapPK, jqGridRequestDto), filterParamList.toArray());
	}


	/**
	 * StringBuilder initilization value
	 */
	 public static final int STRING_BUILDER_INIT = 4096;

	 
	 private RowMapper<Usuario> rwMapPK = new RowMapper<Usuario>() {
			public Usuario mapRow(ResultSet resultSet, int rowNum) throws SQLException {
	           return new Usuario(resultSet.getString("ID")); 
	        } 
	 };

	 private RowMapper<JerarquiaDto<Usuario>> rwMapJerarquia = new RowMapper<JerarquiaDto<Usuario>>() {
			public JerarquiaDto<Usuario> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				
				Usuario usuario = new Usuario(
						resultSet.getString("ID"), 
						resultSet.getString("NOMBRE"), 
						resultSet.getString("APELLIDO1"), 
						resultSet.getString("APELLIDO2"), 
						resultSet.getString("EJIE"), 
						resultSet.getDate("FECHAALTA"), 
						resultSet.getDate("FECHABAJA")
				);
				usuario.setIdPadre(resultSet.getString("ID_PADRE"));
	          
				JerarquiaDto<Usuario> jerarquia = new JerarquiaDto<Usuario>();
//				jerarquia.setParent(resultSet.getString("IDPADRE"));
				jerarquia.setModel(usuario);
				jerarquia.setLevel(resultSet.getBigDecimal("LEVEL").intValue());
				jerarquia.setParentNodes(resultSet.getString("PARENTNODES"));
				jerarquia.setIsLeaf(Boolean.parseBoolean(resultSet.getString("ISLEAF")));
				jerarquia.setFilter(Boolean.parseBoolean(resultSet.getString("FILTER")));
	  			return jerarquia;
			} 
	 };
	 
	@Override
	public List<JerarquiaDto<Usuario>> findAllLikeJerarquia(Usuario filterUsuario, JQGridRequestDto jqGridRequestDto) {
		
		//SELECT
		StringBuilder sbSQL = new StringBuilder("SELECT t1.ID ID, t1.ID_PADRE ID_PADRE, t1.NOMBRE NOMBRE, t1.APELLIDO1 APELLIDO1, t1.APELLIDO2 APELLIDO2, t1.EJIE EJIE, t1.FECHA_ALTA FECHAALTA, t1.FECHA_BAJA FECHABAJA, t1.ID_PADRE IDPADRE, t1.GRUPO GRUPO ");
		
		//TABLAS
		List<String> from = new ArrayList<String>();
		from.add("USUARIO_JERARQUIA");
		
		//TABLAS_ALIAS
		List<String> from_alias = new ArrayList<String>();
		from_alias.add("t1");
		
		//JOINS TABLAS
		StringBuilder joins = new StringBuilder("");
		joins.append("AND 666=666");
		
		//CONDICIONES (negocio)
		StringBuilder businessFilters = new StringBuilder();
		List<Object> businessParams = new ArrayList<Object>();
		businessFilters.append("   AND t1.EJIE = ? AND t1.EJIE = ? AND t1.EJIE = ?   ");
		businessParams.add("1");
		businessParams.add("1");
		businessParams.add("1");

		//FILTRO
		Map<String, ?> mapaWhere = this.getWhereLikeMap(filterUsuario, false);
		
		//JERARQUIA
		sbSQL = JQGridManagerJerarquia.getQuery(jqGridRequestDto, sbSQL, mapaWhere, "ID", "ID_PADRE", "NOMBRE", from, from_alias);
//		sbSQL = PaginationManagerJerarquia.getQuery(pagination, sbSQL, mapaWhere, "ID", "ID_PADRE", "NOMBRE", from, from_alias, joins, businessFilters, businessParams);

		//PAGINACIÓN
		if (jqGridRequestDto != null) {
			sbSQL = JQGridManagerJerarquia.getPaginationQuery(jqGridRequestDto, sbSQL);
		}

		List<?> params = (List<?>) mapaWhere.get("params");
		
		return this.jdbcTemplate.query(sbSQL.toString(), this.rwMapJerarquia, params.toArray());
		
	}

	@Override
	public Long findAllLikeCountJerarquia(Usuario filterUsuario, JQGridRequestDto jqGridRequestDto) {
		
		//TABLAS
		List<String> from = new ArrayList<String>();
		from.add("USUARIO_JERARQUIA");
		
		//TABLAS_ALIAS
		List<String> from_alias = new ArrayList<String>();
		from_alias.add("t1");
		
		//JOINS TABLAS
		StringBuilder joins = new StringBuilder("");
		joins.append("AND 666=666");
		
		//CONDICIONES (negocio)
		StringBuilder businessFilters = new StringBuilder();
		List<Object> businessParams = new ArrayList<Object>();
		businessFilters.append("   AND t1.EJIE = ? AND t1.EJIE = ? AND t1.EJIE = ?   ");
		businessParams.add("1");
		businessParams.add("1");
		businessParams.add("1");

		//FILTRO
		Map<String, ?> mapaWhere = this.getWhereLikeMap(filterUsuario, false);
		
		//JERARQUIA
		StringBuilder sbSQL = JQGridManagerJerarquia.getQueryCount(jqGridRequestDto, mapaWhere, "ID", "ID_PADRE", from, from_alias);
//		StringBuilder sbSQL = PaginationManagerJerarquia.getQueryCount(pagination, mapaWhere, "ID", "ID_PADRE", from, from_alias, joins, businessFilters, businessParams);

		List<?> params = (List<?>) mapaWhere.get("params");
		
		return this.jdbcTemplate.queryForLong(sbSQL.toString(), params.toArray());
	}

	@Override
	public List<TableRowDto<Usuario>> findAllChild(Usuario filterUsuario, JQGridRequestDto jqGridRequestDto) {
		
		//TABLAS
		List<String> from = new ArrayList<String>();
		from.add("USUARIO_JERARQUIA");
		
		//TABLAS_ALIAS
		List<String> from_alias = new ArrayList<String>();
		from_alias.add("t1");
		
		//JOINS TABLAS
		StringBuilder joins = new StringBuilder("");
		joins.append("AND 666=666");
		
		//CONDICIONES (negocio)
		StringBuilder businessFilters = new StringBuilder();
		List<Object> businessParams = new ArrayList<Object>();
		businessFilters.append("   AND t1.EJIE = ? AND t1.EJIE = ? AND t1.EJIE = ?   ");
		businessParams.add("1");
		businessParams.add("1");
		businessParams.add("1");

		//FILTRO
		Map<String, ?> mapaWhere = this.getWhereLikeMap(filterUsuario, false);
		
		//MULTISELECCION
		StringBuilder sbSQL = JQGridManagerJerarquia.getQueryChildren(jqGridRequestDto, mapaWhere, "ID", "ID_PADRE", from, from_alias);
//		StringBuilder sbSQL = PaginationManagerJerarquia.getQueryChildren(pagination, mapaWhere, "ID", "ID_PADRE", from, from_alias, joins, businessFilters, businessParams);

		List<?> params = (List<?>) mapaWhere.get("params");
		
		return this.jdbcTemplate.query(sbSQL.toString(), new RowNumResultSetExtractor<Usuario>(this.rwMapPK, "id"), params.toArray());

	}
}