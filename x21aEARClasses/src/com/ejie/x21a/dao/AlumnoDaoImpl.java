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

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.model.Alumno;
import com.ejie.x21a.model.NoraAutonomia;
import com.ejie.x21a.model.NoraCalle;
import com.ejie.x21a.model.NoraMunicipio;
import com.ejie.x21a.model.NoraPais;
import com.ejie.x21a.model.NoraProvincia;
import com.ejie.x38.dto.JQGridManager;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.TableRowDto;

/**
 * * AlumnoDaoImpl generated by UDA, 01-mar-2012 9:33:10.
 * 
 * @author UDA
 */

// Prueba de tildes áéíóú

@Repository
@Transactional
public class AlumnoDaoImpl implements AlumnoDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	private LobHandler lobHandler;

	private RowMapper<Alumno> findAllRowMapper = new RowMapper<Alumno>() {
		public Alumno mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {

			Alumno alumno = new Alumno();
			NoraMunicipio municipio = new NoraMunicipio();
			
			alumno.setId(resultSet.getBigDecimal("ID"));
			alumno.setUsuario(resultSet.getString("USUARIO"));
			alumno.setNombre(resultSet.getString("NOMBRE"));
			alumno.setApellido1(resultSet.getString("APELLIDO1"));
			alumno.setApellido2(resultSet.getString("APELLIDO2"));
			alumno.setDni(resultSet.getString("DNI"));
			alumno.setImporteMatricula(resultSet.getBigDecimal("IMPORTE_MATRICULA"));

			municipio.setId(resultSet.getString("MunicipioID"));
			municipio.setDsO(resultSet.getString("MunicipioDSO"));
			
			alumno.setMunicipio(municipio);

			return alumno;
		}
	};

	private RowMapper<Alumno> findRowMapper = new RowMapper<Alumno>() {
		public Alumno mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {

			Alumno alumno = new Alumno();

			alumno.setId(resultSet.getBigDecimal("ID"));
			alumno.setUsuario(resultSet.getString("USUARIO"));
			alumno.setPassword(resultSet.getString("PASSWORD"));
			alumno.setNombre(resultSet.getString("NOMBRE"));
			alumno.setApellido1(resultSet.getString("APELLIDO1"));
			alumno.setApellido2(resultSet.getString("APELLIDO2"));
			alumno.setDni(resultSet.getString("DNI"));
			alumno.setFechaNacimiento(resultSet.getDate("FECHANACIMIENTO"));
			alumno.setTelefono(resultSet.getString("TELEFONO"));
			alumno.setEmail(resultSet.getString("EMAIL"));
			alumno.setIdioma(resultSet.getString("IDIOMA"));
			alumno.setSexo(resultSet.getString("SEXO"));
			alumno.setNombreImagen(resultSet.getString("NOMBRE_IMAGEN"));
			alumno.setDireccion(resultSet.getString("DIRECCION"));
			alumno.setImporteMatricula(resultSet
					.getBigDecimal("IMPORTE_MATRICULA"));

			NoraPais pais = new NoraPais();
			pais.setId(resultSet.getString("PaisID"));
			pais.setDsO(resultSet.getString("PaisDSO"));

			NoraProvincia provincia = new NoraProvincia();
			provincia.setId(resultSet.getString("ProvinciaID"));
			provincia.setDsO(resultSet.getString("ProvinciaDSO"));

			NoraAutonomia autonomia = new NoraAutonomia();
			autonomia.setId(resultSet.getString("AutonomiaID"));
			autonomia.setDsO(resultSet.getString("AutonomiaDSO"));

			NoraMunicipio municipio = new NoraMunicipio();
			municipio.setId(resultSet.getString("MunicipioID"));
			municipio.setDsO(resultSet.getString("MunicipioDSO"));

			NoraCalle calle = new NoraCalle();
			calle.setId(resultSet.getBigDecimal("CalleID"));
			calle.setDsO(resultSet.getString("CalleDSO"));
			calle.setDsE(resultSet.getString("CalleDSE"));

			alumno.setPais(pais);
			alumno.setAutonomia(autonomia);
			alumno.setProvincia(provincia);
			alumno.setMunicipio(municipio);
			alumno.setCalle(calle);

//			alumno.setImagen(defaultLobHandler.getBlobAsBytes(resultSet,
//					"IMAGEN"));

			return alumno;
		}
	};
	
	private RowMapper<Alumno> imagenRowMapper = new RowMapper<Alumno>() {
		public Alumno mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {

			Alumno alumno = new Alumno();

			alumno.setNombreImagen(resultSet.getString("NOMBRE_IMAGEN"));
			alumno.setImagen(lobHandler.getBlobAsBytes(resultSet,
					"IMAGEN"));

			return alumno;
		}
	};

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
//		this.defaultLobHandler = new DefaultLobHandler();
	}

	/**
	 * Inserts a single row in the Alumno table.
	 * 
	 * @param alumno
	 *            Pagination
	 * @return Alumno
	 */
	public Alumno add(final Alumno alumno) {

		final long nextId = jdbcTemplate
				.queryForLong("SELECT ALUMNO_SEQ.NEXTVAL FROM DUAL");

		String query = "INSERT INTO ALUMNO( ID,USUARIO,PASSWORD,NOMBRE,APELLIDO1,APELLIDO2,DNI,FECHA_NACIMIENTO,TELEFONO,EMAIL,IDIOMA,SEXO,NOMBRE_IMAGEN,PAIS_ID,AUTONOMIA_ID,PROVINCIA_ID,MUNICIPIO_ID,CALLE_ID,IMAGEN,DIRECCION,IMPORTE_MATRICULA)"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		alumno.setId(new BigDecimal(nextId));

		this.jdbcTemplate.update(query, new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				int index = 1;

				ps.setBigDecimal(index++, alumno.getId());
				ps.setString(index++, alumno.getUsuario());
				ps.setString(index++, alumno.getPassword());
				ps.setString(index++, alumno.getNombre());
				ps.setString(index++, alumno.getApellido1());
				ps.setString(index++, alumno.getApellido2());
				ps.setString(index++, alumno.getDni());
				ps.setDate(
						index++,
						(alumno.getFechaNacimiento() != null ? new java.sql.Date(
								alumno.getFechaNacimiento().getTime()) : null));
				ps.setString(index++, alumno.getTelefono());
				ps.setString(index++, alumno.getEmail());
				ps.setString(index++, alumno.getIdioma());
				ps.setString(index++, alumno.getSexo());
				ps.setString(index++, alumno.getNombreImagen());

				if (alumno.getPais() != null
						&& alumno.getPais().getId() != null
						&& !"".equals(alumno.getPais().getId())) {
					ps.setString(index++, alumno.getPais().getId());
				} else {
					ps.setNull(index++, java.sql.Types.VARCHAR);
				}
				if (alumno.getAutonomia() != null
						&& alumno.getAutonomia().getId() != null
						&& !"".equals(alumno.getAutonomia().getId())) {
					ps.setString(index++, alumno.getAutonomia().getId());
				} else {
					ps.setNull(index++, java.sql.Types.VARCHAR);
				}
				if (alumno.getProvincia() != null
						&& alumno.getProvincia().getId() != null
						&& !"".equals(alumno.getProvincia().getId())) {
					ps.setString(index++, alumno.getProvincia().getId());
				} else {
					ps.setNull(index++, java.sql.Types.VARCHAR);
				}
				if (alumno.getMunicipio() != null
						&& alumno.getMunicipio().getId() != null
						&& !"".equals(alumno.getMunicipio().getId())) {
					ps.setString(index++, alumno.getMunicipio().getId());
				} else {
					ps.setNull(index++, java.sql.Types.VARCHAR);
				}
				if (alumno.getCalle() != null
						&& alumno.getCalle().getId() != null) {
					ps.setBigDecimal(index++, alumno.getCalle().getId());
				} else {
					ps.setNull(index++, java.sql.Types.INTEGER);
				}

				lobHandler.getLobCreator().setBlobAsBytes(ps, index++,
						alumno.getImagen());
				ps.setString(index++, alumno.getDireccion());
				ps.setBigDecimal(index++, alumno.getImporteMatricula());

			}
		});

		return alumno;
	}

	/**
	 * Updates a single row in the Alumno table.
	 * 
	 * @param alumno
	 *            Pagination
	 * @return Alumno
	 */
	public Alumno update(final Alumno alumno) {
		StringBuilder query = new StringBuilder("UPDATE ALUMNO SET USUARIO=?,NOMBRE=?,APELLIDO1=?,APELLIDO2=?,DNI=?,FECHA_NACIMIENTO=?,TELEFONO=?,EMAIL=?,IDIOMA=?,SEXO=?,PAIS_ID=?,AUTONOMIA_ID=?,PROVINCIA_ID=?,MUNICIPIO_ID=?,CALLE_ID=?,DIRECCION=?,IMPORTE_MATRICULA=?");
		
		if (alumno.getPassword() != null && !"".equals(alumno.getPassword())) {
			query.append(",PASSWORD=? ");
		} 
		if (alumno.getImagen()!=null) {
			query.append(",NOMBRE_IMAGEN=?,IMAGEN=? ");
		}

		query.append(" WHERE ID=?");
		
		this.jdbcTemplate.update(query.toString(), new PreparedStatementSetter() {
			public void setValues(PreparedStatement ps) throws SQLException {
				int index = 1;

				ps.setString(index++, alumno.getUsuario());
				ps.setString(index++, alumno.getNombre());
				ps.setString(index++, alumno.getApellido1());
				ps.setString(index++, alumno.getApellido2());
				ps.setString(index++, alumno.getDni());
				ps.setDate(
						index++,
						(alumno.getFechaNacimiento() != null ? new java.sql.Date(
								alumno.getFechaNacimiento().getTime()) : null));
				ps.setString(index++, alumno.getTelefono());
				ps.setString(index++, alumno.getEmail());
				ps.setString(index++, alumno.getIdioma());
				ps.setString(index++, alumno.getSexo());
				

				if (alumno.getPais() != null
						&& alumno.getPais().getId() != null
						&& !"".equals(alumno.getPais().getId())) {
					ps.setString(index++, alumno.getPais().getId());
				} else {
					ps.setNull(index++, java.sql.Types.VARCHAR);
				}
				if (alumno.getAutonomia() != null
						&& alumno.getAutonomia().getId() != null
						&& !"".equals(alumno.getAutonomia().getId())) {
					ps.setString(index++, alumno.getAutonomia().getId());
				} else {
					ps.setNull(index++, java.sql.Types.VARCHAR);
				}
				if (alumno.getProvincia() != null
						&& alumno.getProvincia().getId() != null
						&& !"".equals(alumno.getProvincia().getId())) {
					ps.setString(index++, alumno.getProvincia().getId());
				} else {
					ps.setNull(index++, java.sql.Types.VARCHAR);
				}
				if (alumno.getMunicipio() != null
						&& alumno.getMunicipio().getId() != null
						&& !"".equals(alumno.getMunicipio().getId())) {
					ps.setString(index++, alumno.getMunicipio().getId());
				} else {
					ps.setNull(index++, java.sql.Types.VARCHAR);
				}
				if (alumno.getCalle() != null
						&& alumno.getCalle().getId() != null) {
					ps.setBigDecimal(index++, alumno.getCalle().getId());
				} else {
					ps.setNull(index++, java.sql.Types.INTEGER);
				}
				ps.setString(index++, alumno.getDireccion());
				ps.setBigDecimal(index++, alumno.getImporteMatricula());
				
				if (alumno.getPassword() != null
						&& !"".equals(alumno.getPassword())) {
					ps.setString(index++, alumno.getPassword());
				}
				if (alumno.getImagen()!=null) {
					ps.setString(index++, alumno.getNombreImagen());
					lobHandler.getLobCreator().setBlobAsBytes(ps, index++,
							alumno.getImagen());
				}
				
				ps.setBigDecimal(index++, alumno.getId());
			}
		});

		return alumno;
	}

	/**
	 * Finds a single row in the Alumno table.
	 * 
	 * @param alumno
	 *            Pagination
	 * @return Alumno
	 */
	@Transactional(readOnly = true)
	public Alumno find(Alumno alumno) {

		String query = "SELECT t1.ID ID, t1.USUARIO USUARIO, t1.PASSWORD PASSWORD, t1.NOMBRE NOMBRE, t1.APELLIDO1 APELLIDO1, t1.APELLIDO2 APELLIDO2, t1.DNI DNI, t1.FECHA_NACIMIENTO FECHANACIMIENTO, t1.TELEFONO TELEFONO, t1.EMAIL EMAIL, t1.IDIOMA IDIOMA, t1.SEXO SEXO, t1.NOMBRE_IMAGEN NOMBRE_IMAGEN, t1.DIRECCION DIRECCION, t1.IMPORTE_MATRICULA IMPORTE_MATRICULA, t2.ID PaisID, t2.DS_O PaisDSO, t3.ID AutonomiaID, t3.DS_O AutonomiaDSO, t4.ID ProvinciaID, t4.DS_O ProvinciaDSO, t5.ID MunicipioID, t5.DS_O MunicipioDSO, t6.ID CalleID, t6.DS_O CalleDSO, t6.DS_E CalleDSE FROM ALUMNO t1, T17_PAIS t2, T17_AUTONOMIA t3, T17_PROVINCIA t4, T17_MUNICIPIO t5, T17_CALLE t6 WHERE t1.ID = ? AND t1.PAIS_ID=t2.ID(+) AND t1.AUTONOMIA_ID=t3.ID(+) AND t1.PROVINCIA_ID=t4.ID(+) AND t1.MUNICIPIO_ID=t5.ID(+) AND t1.PROVINCIA_ID=t5.PROVINCIA_ID(+) AND t1.CALLE_ID=t6.ID(+) ";

		return (Alumno) this.jdbcTemplate.queryForObject(query, findRowMapper,
				alumno.getId());
	}

	/**
	 * Removes a single row in the Alumno table.
	 * 
	 * @param alumno
	 *            Pagination
	 * @return
	 */
	public void remove(Alumno alumno) {
		String query = "DELETE  FROM ALUMNO WHERE ID=?";
		this.jdbcTemplate.update(query, alumno.getId());
	}

	/**
	 * Finds a List of rows in the Alumno table.
	 * 
	 * @param alumno
	 *            Alumno
	 * @param pagination
	 *            Pagination
	 * @return List
	 */
	@Transactional(readOnly = true)
	public List<Alumno> findAll(Alumno alumno, JQGridRequestDto jqGridRequestDto) {
		StringBuffer where = new StringBuffer(3000);
		List<Object> params = new ArrayList<Object>();
		where.append(" WHERE t1.MUNICIPIO_ID=t2.ID and T1.PROVINCIA_ID=t2.PROVINCIA_ID	");

		StringBuilder query = new StringBuilder(
				"SELECT  t1.ID ID,t1.USUARIO USUARIO,t1.PASSWORD PASSWORD,t1.NOMBRE NOMBRE,t1.APELLIDO1 APELLIDO1,t1.APELLIDO2 APELLIDO2,t1.DNI DNI, t1.IMPORTE_MATRICULA IMPORTE_MATRICULA, t2.ID MunicipioID, t2.DS_O MunicipioDSO "
						+ "FROM ALUMNO t1, T17_MUNICIPIO t2 ");

		if (alumno != null && alumno.getId() != null) {
			where.append(" AND t1.ID = ?");
			params.add(alumno.getId());
		}
		if (alumno != null && alumno.getUsuario() != null) {
			where.append(" AND t1.USUARIO = ?");
			params.add(alumno.getUsuario());
		}
		if (alumno != null && alumno.getPassword() != null) {
			where.append(" AND t1.PASSWORD = ?");
			params.add(alumno.getPassword());
		}
		if (alumno != null && alumno.getNombre() != null) {
			where.append(" AND t1.NOMBRE = ?");
			params.add(alumno.getNombre());
		}
		if (alumno != null && alumno.getApellido1() != null) {
			where.append(" AND t1.APELLIDO1 = ?");
			params.add(alumno.getApellido1());
		}
		if (alumno != null && alumno.getApellido2() != null) {
			where.append(" AND t1.APELLIDO2 = ?");
			params.add(alumno.getApellido2());
		}
		if (alumno != null && alumno.getDni() != null) {
			where.append(" AND t1.DNI = ?");
			params.add(alumno.getDni());
		}

		query.append(where);

		if (jqGridRequestDto != null) {
			query = JQGridManager.getPaginationQuery(jqGridRequestDto, query);
		}
		return (List<Alumno>) this.jdbcTemplate.query(query.toString(),
				findAllRowMapper, params.toArray());
	}

	/**
	 * Counts rows in the Alumno table.
	 * 
	 * @param alumno
	 *            Alumno
	 * @return Long
	 */
	@Transactional(readOnly = true)
	public Long findAllCount(Alumno alumno) {

		StringBuffer where = new StringBuffer(3000);
		List<Object> params = new ArrayList<Object>();
		where.append(" WHERE 1=1  ");

		StringBuffer query = new StringBuffer(
				"SELECT COUNT(1) FROM  ALUMNO t1  ");
		if (alumno != null && alumno.getId() != null) {
			where.append(" AND t1.ID = ?");
			params.add(alumno.getId());
		}
		if (alumno != null && alumno.getUsuario() != null) {
			where.append(" AND t1.USUARIO = ?");
			params.add(alumno.getUsuario());
		}
		if (alumno != null && alumno.getPassword() != null) {
			where.append(" AND t1.PASSWORD = ?");
			params.add(alumno.getPassword());
		}
		if (alumno != null && alumno.getNombre() != null) {
			where.append(" AND t1.NOMBRE = ?");
			params.add(alumno.getNombre());
		}
		if (alumno != null && alumno.getApellido1() != null) {
			where.append(" AND t1.APELLIDO1 = ?");
			params.add(alumno.getApellido1());
		}
		if (alumno != null && alumno.getApellido2() != null) {
			where.append(" AND t1.APELLIDO2 = ?");
			params.add(alumno.getApellido2());
		}
		if (alumno != null && alumno.getFechaNacimiento() != null) {
			where.append(" AND t1.FECHA_NACIMIENTO = ?");
			params.add(alumno.getFechaNacimiento());
		}
		if (alumno != null && alumno.getDni() != null) {
			where.append(" AND t1.DNI = ?");
			params.add(alumno.getDni());
		}

		query.append(where);
		return this.jdbcTemplate.queryForLong(query.toString(),
				params.toArray());
	}
	
	@Transactional(readOnly = true)
	public Long findAllLikeCount(Alumno alumno, Boolean startsWith) {

		List<Object> params = new ArrayList<Object>();

		StringBuilder query = new StringBuilder(
				"SELECT COUNT(1) FROM ALUMNO t1 LEFT JOIN T17_MUNICIPIO t2 ON t1.MUNICIPIO_ID=t2.ID and T1.PROVINCIA_ID=t2.PROVINCIA_ID  ");

		query.append(this.getFindAllLikeWhere(alumno, params, startsWith));
		return this.jdbcTemplate.queryForLong(query.toString(),
				params.toArray());
	}

	/**
	 * Finds rows in the Alumno table using like.
	 * 
	 * @param alumno
	 *            Alumno
	 * @param pagination
	 *            Pagination
	 * @param startsWith
	 *            Boolean
	 * @return List
	 */
	@Transactional(readOnly = true)
	public List<Alumno> findAllLike(Alumno alumno, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith) {
		List<Object> params = new ArrayList<Object>();

//		StringBuilder query = new StringBuilder(
//				"SELECT  t1.ID ID,t1.USUARIO USUARIO,t1.PASSWORD PASSWORD,t1.NOMBRE NOMBRE,t1.APELLIDO1 APELLIDO1,t1.APELLIDO2 APELLIDO2,t1.DNI DNI, t1.IMPORTE_MATRICULA IMPORTE_MATRICULA "
//						+ "FROM ALUMNO t1 ");
		
		StringBuilder query = new StringBuilder(
				"SELECT  t1.ID ID,t1.USUARIO USUARIO,t1.PASSWORD PASSWORD,t1.NOMBRE NOMBRE,t1.APELLIDO1 APELLIDO1,t1.APELLIDO2 APELLIDO2,t1.DNI DNI, t1.IMPORTE_MATRICULA IMPORTE_MATRICULA, t2.ID MunicipioID, t2.DS_O MunicipioDSO "
						+ "FROM ALUMNO t1 LEFT JOIN T17_MUNICIPIO t2 ON t1.MUNICIPIO_ID=t2.ID and T1.PROVINCIA_ID=t2.PROVINCIA_ID ");

		query.append(this.getFindAllLikeWhere(alumno, params, startsWith));

		if (jqGridRequestDto != null) {
			query = JQGridManager.getPaginationQuery(jqGridRequestDto, query);
		}
		return (List<Alumno>) this.jdbcTemplate.query(query.toString(),
				findAllRowMapper, params.toArray());
	}

	@Override
	public Boolean isUsernameValid(Alumno alumno) {
		StringBuffer query = new StringBuffer(100);
		List<Object> params = new ArrayList<Object>();

		query.append("SELECT COUNT(1) FROM ALUMNO WHERE USUARIO=? ");
		params.add(alumno.getUsuario());
		
		if (alumno.getId()!=null){
			query.append(" AND ID<>? ");
			params.add(alumno.getId());
		}

		return (this.jdbcTemplate.queryForLong(query.toString(),
				params.toArray()) == 0);
	}

	@Override
	public Boolean isOldPasswordValid(Alumno alumno, String oldPassword) {
		StringBuffer query = new StringBuffer(100);
		List<Object> params = new ArrayList<Object>();

		query.append("SELECT COUNT(1) FROM ALUMNO WHERE ID=? AND PASSWORD=?");
		params.add(alumno.getId());
		params.add(oldPassword);

		return (this.jdbcTemplate.queryForLong(query.toString(),
				params.toArray()) == 1);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Alumno getImagen(Alumno alumno){
		
		String query = "SELECT t1.NOMBRE_IMAGEN NOMBRE_IMAGEN, t1.IMAGEN IMAGEN FROM ALUMNO t1 WHERE t1.ID = ?";
		
		List<Alumno> listaResultado = this.jdbcTemplate.query(query, this.imagenRowMapper, alumno.getId());
		return DataAccessUtils.uniqueResult(listaResultado);
		
	}
	
	
	private String getFindAllLikeWhere(Alumno alumno, List<Object> params, Boolean startsWith) {
		StringBuilder where = new StringBuilder();
		
		where.append(" WHERE 1 = 1");

		if (alumno != null && alumno.getUsuario() != null) {
			where.append(" AND UPPER(t1.USUARIO) like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(alumno.getUsuario().toUpperCase() + "%");
			} else {
				params.add("%" + alumno.getUsuario().toUpperCase() + "%");
			}
			where.append(" AND t1.USUARIO IS NOT NULL");
		}
		if (alumno != null && alumno.getNombre() != null) {
			where.append(" AND UPPER(t1.NOMBRE) like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(alumno.getNombre().toUpperCase() + "%");
			} else {
				params.add("%" + alumno.getNombre().toUpperCase() + "%");
			}
			where.append(" AND t1.NOMBRE IS NOT NULL");
		}
		if (alumno != null && alumno.getApellido1() != null) {
			where.append(" AND UPPER(t1.APELLIDO1) like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(alumno.getApellido1().toUpperCase() + "%");
			} else {
				params.add("%" + alumno.getApellido1().toUpperCase() + "%");
			}
			where.append(" AND t1.APELLIDO1 IS NOT NULL");
		}
		if (alumno != null && alumno.getApellido2() != null) {
			where.append(" AND UPPER(t1.APELLIDO2) like ? ESCAPE  '\\'");
			if (startsWith) {
				params.add(alumno.getApellido2().toUpperCase() + "%");
			} else {
				params.add("%" + alumno.getApellido2().toUpperCase() + "%");
			}
			where.append(" AND t1.APELLIDO2 IS NOT NULL");
		}

		return where.toString();
	}

	@Override
	public void removeMultiple(Alumno filterAlumno, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TableRowDto<Alumno>> reorderSelection(Alumno alumno, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TableRowDto<Alumno>> search(Alumno filterParams, Alumno searchParams, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
