package com.ejie.x21a.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.model.IberdokFile;
import com.ejie.x38.dao.RowNumResultSetExtractor;
import com.ejie.x38.dto.JQGridManager;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.TableRowDto;
import com.ejie.x38.rss.RssContent;

@Repository
@Transactional
public class IberdokFileDaoImpl implements IberdokFileDao {

	/**
	 * StringBuilder initilization value
	 */
	public static final int STRING_BUILDER_INIT = 4099;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	private Properties appConfiguration;

	/**
	 * Method use to set the datasource.
	 *
	 * @param dataSource
	 *            DataSource
	 * @return
	 */
	@Resource
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/*
	 * OPERACIONES CRUD
	 */

	/**
	 * Inserts a single row in the IberdokFile table.
	 *
	 * @param file
	 *            Pagination
	 * @return IberdokFile
	 */
	public IberdokFile add(IberdokFile file) {

		final long nextId = jdbcTemplate
				.queryForLong("SELECT IBERDOK_SEQ.NEXTVAL FROM DUAL");

		String query = "INSERT INTO IBERDOK_FILES (ID, ID_MODELO, SEMILLA, ID_DOCUMENTO, ESTADO, NOMBRE) VALUES (?,?,?,?,?,?)";
		this.jdbcTemplate.update(query, nextId, file.getIdModelo(),
				file.getSemilla(), file.getIdDocumento(), file.getEstado(),
				file.getNombre());
		return file;
	}

	/**
	 * Updates a single row in the IberdokFile table.
	 *
	 * @param file
	 *            Pagination
	 * @return IberdokFile
	 */
	public IberdokFile update(IberdokFile file) {
		String query = "UPDATE IBERDOK_FILES SET ID_MODELO=?, SEMILLA=?, ID_DOCUMENTO=?, ESTADO=? WHERE ID=?";
		this.jdbcTemplate.update(query, file.getIdModelo(), file.getSemilla(),
				file.getIdDocumento(), file.getEstado(), file.getId());
		return file;
	}

	/**
	 * Updates a single row in the IberdokFile table.
	 *
	 * @param file
	 *            Pagination
	 * @return IberdokFile
	 */
	public IberdokFile updateIdDocumento(IberdokFile file) {

		String query = "UPDATE IBERDOK_FILES SET  ID_DOCUMENTO=?, ESTADO=? WHERE ID_DOCUMENTO=?";
		this.jdbcTemplate.update(query, file.getIdDocumento(), file.getEstado(), file.getIdDocumento());
		return file;
	}

	public IberdokFile updateLastRecord(IberdokFile file) {

		final long lastId = jdbcTemplate
				.queryForLong("select MAX(id) from IBERDOK_FILES");

		String query = "UPDATE IBERDOK_FILES SET  ID_DOCUMENTO=?, ESTADO=? WHERE ID=?";
		this.jdbcTemplate.update(query, file.getIdDocumento(),
				file.getEstado(), lastId);
		return file;
	}

	/**
	 * Finds a single row in the IberdokFile table.
	 *
	 * @param file
	 *            Pagination
	 * @return IberdokFile
	 */
	@Transactional(readOnly = true)
	public IberdokFile find(IberdokFile file) {
		String query = "SELECT t1.ID ID, t1.ID_MODELO ID_MODELO, t1.SEMILLA SEMILLA, t1.ID_DOCUMENTO ID_DOCUMENTO, t1.ESTADO ESTADO , t1.NOMBRE FROM IBERDOK_FILES t1  WHERE t1.ID_DOCUMENTO = ?  ";

		List<IberdokFile> fileList = this.jdbcTemplate.query(query, this.rwMap,
				file.getIdDocumento());
		return (IberdokFile) DataAccessUtils.uniqueResult(fileList);
	}

	/**
	 * Removes a single row in the IberdokFile table.
	 *
	 * @param file
	 *            Pagination
	 * @return
	 */
	public void remove(IberdokFile file) {
		String query = "DELETE FROM IBERDOK_FILES WHERE ID=?";
		this.jdbcTemplate.update(query, file.getId());
	}

	/**
	 * Finds a List of rows in the IberdokFile table.
	 * 
	 * @param file
	 *            IberdokFile
	 * @param pagination
	 *            Pagination
	 * @return List
	 */
	@Transactional(readOnly = true)
	public List<IberdokFile> findAll(IberdokFile file,
			JQGridRequestDto jqGridRequestDto) {
		StringBuilder query = new StringBuilder(
				"SELECT t1.ID ID, t1.ID_MODELO ID_MODELO, t1.SEMILLA SEMILLA, t1.ID_DOCUMENTO ID_DOCUMENTO, t1.ESTADO ESTADO , t1.NOMBRE NOMBRE");
		query.append("FROM IBERDOK_FILES t1 ");

		// Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereMap(file);
		StringBuilder where = new StringBuilder(" WHERE t1.ID_DOCUMENTO IS NOT NULL ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		if (jqGridRequestDto != null) {
			query = JQGridManager.getPaginationQuery(jqGridRequestDto, query);
		}

		return (List<IberdokFile>) this.jdbcTemplate.query(query.toString(),
				this.rwMap, params.toArray());
	}

	/**
	 * Finds rows in the IberdokFile table using like.
	 * 
	 * @param file
	 *            IberdokFile
	 * @param pagination
	 *            Pagination
	 * @param startsWith
	 *            Boolean
	 * @return List
	 * 
	 * 
	 */
	@Transactional(readOnly = true)
	public List<IberdokFile> findAllLike(IberdokFile file,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		StringBuilder query = new StringBuilder(
				"SELECT t1.ID ID, t1.ID_MODELO ID_MODELO, t1.SEMILLA SEMILLA, t1.ID_DOCUMENTO ID_DOCUMENTO, t1.ESTADO ESTADO , t1.NOMBRE NOMBRE ");
		query.append("FROM IBERDOK_FILES t1 ");

		// Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereLikeMap(file, startsWith);
		StringBuilder where = new StringBuilder(" WHERE t1.ID_DOCUMENTO IS NOT NULL ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		if (jqGridRequestDto != null) {
			query = JQGridManager.getPaginationQuery(jqGridRequestDto, query);
		}
		List<IberdokFile> aux = null;
		try {
			aux = (List<IberdokFile>) this.jdbcTemplate.query(query.toString(),
					this.rwMap, params.toArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// return (List<IberdokFile>) this.jdbcTemplate.query(query.toString(),
		// this.rwMap, params.toArray());
		return aux;
	}

	/*
	 * OPERACIONES RUP_TABLE
	 */

	/**
	 * Counts rows in the IberdokFile table.
	 * 
	 * @param file
	 *            IberdokFile
	 * @return Long
	 */
	@Transactional(readOnly = true)
	public Long findAllCount(IberdokFile file) {
		StringBuilder query = new StringBuilder(
				"SELECT COUNT(1) FROM IBERDOK_FILES t1 ");

		// Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereMap(file);
		StringBuilder where = new StringBuilder(" WHERE t1.ID_DOCUMENTO IS NOT NULL");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		return this.jdbcTemplate.queryForLong(query.toString(),
				params.toArray());
	}

	/**
	 * Counts rows in the IberdokFile table using like.
	 * 
	 * @param file
	 *            IberdokFile
	 * @param startsWith
	 *            Boolean
	 * @return Long
	 */
	@Transactional(readOnly = true)
	public Long findAllLikeCount(IberdokFile file, Boolean startsWith) {
		StringBuilder query = new StringBuilder(
				"SELECT COUNT(1) FROM IBERDOK_FILES t1 ");

		// Where clause & Params
		Map<String, ?> mapaWhere = this.getWhereLikeMap(file, startsWith);
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		List<?> params = (List<?>) mapaWhere.get("params");

		return this.jdbcTemplate.queryForLong(query.toString(),
				params.toArray());
	}

	@Override
	public List<TableRowDto<IberdokFile>> reorderSelection(IberdokFile file,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith) {

		// SELECT
		StringBuilder sbSQL = new StringBuilder(
				"SELECT t1.ID ID, t1.ID_MODELO ID_MODELO, t1.SEMILLA SEMILLA, t1.ID_DOCUMENTO ID_DOCUMENTO, t1.ESTADO ESTADO ");

		// FROM
		sbSQL.append("FROM IBERDOK_FILES t1 ");

		// FILTRADO
		Map<String, ?> mapaWhere = this.getWhereLikeMap(file, startsWith);
		// Claula where de filtrado
		sbSQL.append(" WHERE 1=1 ").append(mapaWhere.get("query"));
		// Parámetros de filtrado
		@SuppressWarnings("unchecked")
		List<Object> filterParamList = (List<Object>) mapaWhere.get("params");

		// SQL para la reordenación
		StringBuilder sbReorderSelectionSQL = JQGridManager.getReorderQuery(
				sbSQL, jqGridRequestDto, IberdokFile.class, filterParamList,
				"ID");

		return this.jdbcTemplate.query(sbReorderSelectionSQL.toString(),
				new RowNumResultSetExtractor<IberdokFile>(this.rwMapPK,
						jqGridRequestDto), filterParamList.toArray());
	}

	@Override
	public List<TableRowDto<IberdokFile>> search(IberdokFile filterParams,
			IberdokFile searchParams, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith) {

		// SELECT
		StringBuilder sbSQL = new StringBuilder(
				"SELECT t1.ID ID, t1.ID_MODELO ID_MODELO, t1.SEMILLA SEMILLA, t1.ID_DOCUMENTO ID_DOCUMENTO, t1.ESTADO ESTADO ");

		// FROM
		sbSQL.append("FROM IBERDOK_FILES t1 ");

		// TABLAS_ALIAS
		List<String> from_alias = new ArrayList<String>();
		from_alias.add("t1");

		// FILTRADO
		// Mapa de filtrado
		Map<String, Object> mapaWhereFilter = this.getWhereLikeMap(
				filterParams, startsWith);
		// Claula where de filtrado
		sbSQL.append(" WHERE 1=1 ").append(mapaWhereFilter.get("query"));
		// Parámetros de filtrado
		@SuppressWarnings("unchecked")
		List<Object> filterParamList = (List<Object>) mapaWhereFilter
				.get("params");

		// BUSQUEDA
		Map<String, Object> mapaWhereSearch = this.getWhereLikeMap(
				searchParams, startsWith);
		// Claula where de búsqueda
		String searchSQL = ((StringBuffer) mapaWhereSearch.get("query"))
				.toString();
		// Parámetros de búsqueda
		@SuppressWarnings("unchecked")
		List<Object> searchParamList = (List<Object>) mapaWhereSearch
				.get("params");

		// SQL para la busqueda
		StringBuilder sbReorderSelectionSQL = JQGridManager.getSearchQuery(
				sbSQL, jqGridRequestDto, IberdokFile.class, filterParamList,
				searchSQL, searchParamList, from_alias, "ID");

		return this.jdbcTemplate.query(sbReorderSelectionSQL.toString(),
				new RowNumResultSetExtractor<IberdokFile>(this.rwMapPK,
						jqGridRequestDto), filterParamList.toArray());
	}

	/*
	 * OPERACIONES RUP_TABLE
	 */

	@Override
	public void removeMultiple(IberdokFile filterIberdokFile,
			JQGridRequestDto jqGridRequestDto, Boolean startsWith) {

		StringBuilder query = new StringBuilder("SELECT  t1.ID  ");
		query.append("FROM IBERDOK_FILES t1 ");

		// Where clause & Params
		Map<String, Object> mapaWhere = this.getWhereLikeMap(filterIberdokFile,
				startsWith);
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		where.append(mapaWhere.get("query"));
		query.append(where);

		@SuppressWarnings("unchecked")
		List<Object> params = (List<Object>) mapaWhere.get("params");

		StringBuilder sbRemoveMultipleSQL = JQGridManager
				.getRemoveMultipleQuery(jqGridRequestDto, IberdokFile.class,
						query, params, "ID");

		this.jdbcTemplate.update(sbRemoveMultipleSQL.toString(),
				params.toArray());

	}

	/*
	 * MÉTODOS PRIVADOS
	 */

	/**
	 * Returns a map with the needed value to create the conditions to filter by
	 * the IberdokFile entity
	 * 
	 * @param file
	 *            IberdokFile Bean with the criteria values to filter by.
	 * @return Map created with two keys key query stores the sql query syntax
	 *         key params stores the parameter values to be used in the
	 *         condition sentence.
	 */
	// CHECKSTYLE:OFF CyclomaticComplexity - Generación de código de UDA
	private Map<String, ?> getWhereMap(IberdokFile file) {

		StringBuffer where = new StringBuffer(
				IberdokFileDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();

		if (file != null && file.getId() != null) {
			where.append(" AND t1.ID = ?");
			params.add(file.getId());
		}
		if (file != null && file.getIdModelo() != null) {
			where.append(" AND t1.ID_MODELO = ?");
			params.add(file.getIdModelo());
		}
		if (file != null && file.getSemilla() != null) {
			where.append(" AND t1.SEMILLA = ?");
			params.add(file.getSemilla());
		}
		if (file != null && file.getIdDocumento() != null) {
			where.append(" AND t1.ID_DOCUMENTO = ?");
			params.add(file.getIdDocumento());
		}
		if (file != null && file.getEstado() != null) {
			where.append(" AND t1.ESTADO = ?");
			params.add(file.getEstado());
		}

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return mapWhere;
	}

	// CHECKSTYLE:ON CyclomaticComplexity - Generación de código de UDA

	/**
	 * Returns a map with the needed value to create the conditions to filter by
	 * the IberdokFile entity
	 * 
	 * @param file
	 *            IberdokFile Bean with the criteria values to filter by.
	 * @param startsWith
	 *            Boolean
	 * @return Map created with two keys key query stores the sql query syntax
	 *         key params stores the parameter values to be used in the
	 *         condition sentence.
	 */
	// CHECKSTYLE:OFF CyclomaticComplexity - Generación de código de UDA
	private Map<String, Object> getWhereLikeMap(IberdokFile file,
			Boolean startsWith) {

		StringBuffer where = new StringBuffer(
				IberdokFileDaoImpl.STRING_BUILDER_INIT);
		List<Object> params = new ArrayList<Object>();

		if (file != null && file.getId() != null) {
			where.append(" AND UPPER(t1.ID) like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(file.getId().toUpperCase() + "%");
			} else {
				params.add("%" + file.getId().toUpperCase() + "%");
			}
			where.append(" AND t1.ID IS NOT NULL");
		}
		if (file != null && file.getIdModelo() != null) {
			where.append(" AND UPPER(t1.ID_MODELO) like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(file.getIdModelo().toUpperCase() + "%");
			} else {
				params.add("%" + file.getIdModelo().toUpperCase() + "%");
			}
			where.append(" AND t1.ID_MODELO IS NOT NULL");
		}
		if (file != null && file.getSemilla() != null) {
			where.append(" AND UPPER(t1.SEMILLA) like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(file.getSemilla().toUpperCase() + "%");
			} else {
				params.add("%" + file.getSemilla().toUpperCase() + "%");
			}
			where.append(" AND t1.SEMILLA IS NOT NULL");
		}
		if (file != null && file.getIdDocumento() != null) {
			where.append(" AND UPPER(t1.ID_DOCUMENTO) like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(file.getIdDocumento().toUpperCase() + "%");
			} else {
				params.add("%" + file.getIdDocumento().toUpperCase() + "%");
			}
			where.append(" AND t1.ID_DOCUMENTO IS NOT NULL");
		}
		if (file != null && file.getEstado() != null) {
			where.append(" AND UPPER(t1.ESTADO) like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(file.getEstado().toUpperCase() + "%");
			} else {
				params.add("%" + file.getEstado().toUpperCase() + "%");
			}
			where.append(" AND t1.ESTADO IS NOT NULL");
		}

		Map<String, Object> mapWhere = new HashMap<String, Object>();
		mapWhere.put("query", where);
		mapWhere.put("params", params);

		return mapWhere;
	}

	// CHECKSTYLE:ON CyclomaticComplexity - Generación de código de UDA

	/*
	 * ROW_MAPPERS
	 */

	private RowMapper<IberdokFile> rwMap = new RowMapper<IberdokFile>() {
		public IberdokFile mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			IberdokFile file = new IberdokFile();
			file.setId(resultSet.getString("ID"));
			file.setIdModelo(resultSet.getString("ID_MODELO"));
			file.setSemilla(resultSet.getString("SEMILLA"));
			file.setIdDocumento(resultSet.getString("ID_DOCUMENTO"));
			file.setEstado(resultSet.getString("ESTADO"));
			file.setNombre(resultSet.getString("NOMBRE"));

			return file;
		}
	};

	private RowMapper<IberdokFile> rwMapPK = new RowMapper<IberdokFile>() {
		public IberdokFile mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			return new IberdokFile(resultSet.getString("ID"));
		}
	};

	/*
	 * RSS
	 */

	private RowMapper<RssContent> rssMap = new RowMapper<RssContent>() {

		public RssContent mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			RssContent rssContent = new RssContent();

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"dd/MM/yyyy HH:mm:ss");

			StringBuilder titulo = new StringBuilder();
			titulo.append("IberdokFile : ");
			titulo.append(resultSet.getString("ID"));
			titulo.append("  (")
					.append(simpleDateFormat.format(resultSet
							.getTimestamp("FECHA_MODIF"))).append(") ");

			StringBuilder descripcion = new StringBuilder();
			descripcion.append("Nombre completo : ")
					.append(resultSet.getString("APELLIDO1"))
					.append(resultSet.getString("APELLIDO2")).append(", ")
					.append(resultSet.getString("NOMBRE")).append("<br/>");
			descripcion.append("Rol : ").append(resultSet.getString("ROL"))
					.append("<br/>");
			descripcion.append("Ejie : ").append(resultSet.getString("ROL"))
					.append("<br/>");
			descripcion.append("Fecha alta : ")
					.append(resultSet.getDate("FECHA_ALTA")).append("<br/>");
			descripcion.append("Fecha baja : ")
					.append(resultSet.getDate("FECHA_BAJA")).append("<br/>");

			StringBuilder link = new StringBuilder();
			link.append(appConfiguration.get("rss.path")).append(
					resultSet.getString("ID"));

			rssContent.setTitle(titulo.toString());
			rssContent.setDescription(descripcion.toString());
			rssContent.setLink(link.toString());
			rssContent.setPubDate(resultSet.getTimestamp("FECHA_MODIF"));

			return rssContent;
		}
	};

	public List<RssContent> getRssFeed() {

		StringBuilder query = new StringBuilder(
				"SELECT t1.ID ID, t1.ID_MODELO ID_MODELO, t1.SEMILLA SEMILLA, t1.ID_DOCUMENTO ID_DOCUMENTO, t1.ESTADO ESTADO ");
		query.append("FROM IBERDOK_FILES t1 ");

		StringBuilder filteredQuery = new StringBuilder();

		// Where clause & Params
		// Map<String, ?> mapaWhere = this.getWhereLikeMap(file,startsWith);
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		// where.append(mapaWhere.get("query"));
		query.append(where);
		query.append(" ORDER BY ID ASC ");

		// List<?> params = (List<?>) mapaWhere.get("params");

		filteredQuery.append("SELECT * FROM (SELECT rownum rnum, a.*  FROM ("
				+ query + ")a) where rnum > 0 and rnum < 30");

		return (List<RssContent>) this.jdbcTemplate.query(
				filteredQuery.toString(), this.rssMap);

	}
}