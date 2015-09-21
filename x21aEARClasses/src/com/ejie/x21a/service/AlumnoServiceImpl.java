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
package com.ejie.x21a.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import com.ejie.x21a.dao.AlumnoDao;
import com.ejie.x21a.model.Alumno;
import com.ejie.x38.dto.Pagination;

/**
 *  * AlumnoServiceImpl generated by UDA, 01-mar-2012 9:33:10.
 * @author UDA
 */

@Service(value = "alumnoService")
public  class AlumnoServiceImpl implements AlumnoService {

	/**
    * Final static logger.
    */
	private static final  Logger logger = LoggerFactory.getLogger(AlumnoServiceImpl.class);
	
	@Autowired
	private AlumnoDao alumnoDao;

	/**
	 * Inserts a single row in the Alumno table.
	 *
	 * @param alumno Alumno
	 * @return Alumno
	 */
	@Transactional(rollbackFor = Throwable.class)
	public Alumno add(Alumno alumno) {
		return this.alumnoDao.add(alumno);
	}

	/**
	 * Updates a single row in the Alumno table.
	 *
	 * @param alumno Alumno
	 * @return Alumno
	 */
	@Transactional(rollbackFor = Throwable.class)
	public Alumno update(Alumno alumno) {
		return this.alumnoDao.update(alumno);
	 }

	/**
	 * Finds a single row in the Alumno table.
	 *
	 * @param alumno Alumno
	 * @return Alumno
	 */
	public Alumno find(Alumno alumno) {
		return (Alumno) this.alumnoDao.find(alumno);
	}

	/**
	 * Finds a List of rows in the Alumno table.
	 *
	 * @param alumno Alumno
	 * @param pagination Pagination
	 * @return List
	 */
	public List<Alumno> findAll(Alumno alumno, Pagination pagination) {
		return (List<Alumno>) this.alumnoDao.findAll(alumno, pagination);
	}
    
	/**
	 * Counts rows in the Alumno table.
	 *
	 * @param alumno Alumno
	 * @return Long
	 */
	public Long findAllCount(Alumno alumno) {        
		return  this.alumnoDao.findAllCount(alumno);
	}
	
	/**
	 * Counts rows in the Alumno table.
	 *
	 * @param alumno Alumno
	 * @return Long
	 */
	public Long findAllLikeCount(Alumno alumno, Boolean startsWith) {        
		return  this.alumnoDao.findAllLikeCount(alumno, startsWith);
	}

	/**
	 * Finds rows in the Alumno table using like.
	 *
	 * @param alumno Alumno
	 * @param pagination Pagination
	 * @param startsWith Boolean
	 * @return List
	 */
	public List<Alumno> findAllLike(Alumno alumno, Pagination pagination, Boolean startsWith) {
		return (List<Alumno>) this.alumnoDao.findAllLike(alumno, pagination, startsWith);
	}
    
	/**
	 * Deletes a single row in the Alumno table.
	 *
	 * @param alumno Alumno
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void remove(Alumno alumno) {
		this.alumnoDao.remove(alumno);
	}
	
	/**
	 * Deletes multiple rows in the Alumno table.
	 *
	 * @param alumnoList ArrayList
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void removeMultiple(List<Alumno> alumnoList) {
		for (Alumno  alumnoAux:alumnoList) {
			this.alumnoDao.remove(alumnoAux);
		}
	}


	/**
	 * Getter method for AlumnoDao
	 *
	 * @return AlumnoDao
	 */
	public AlumnoDao getAlumnoDao() {
		return this.alumnoDao;
	}
    
	/**
	 * Setter method for AlumnoDao.
	 *
	 * @param  alumnoDao AlumnoDao
	 */
	public void setAlumnoDao(AlumnoDao alumnoDao) {
		logger.info("Setting Dependency "+alumnoDao);
		this.alumnoDao = alumnoDao;
	}

	/**
	 * Inserts a single row in the Alumno table.
	 *
	 * @param alumno Alumno
	 * @return Alumno
	 */
	@Transactional(rollbackFor = Throwable.class)
	public Alumno add(Alumno alumno, Errors errors) {
		this.checkUsernameValid(alumno.getUsuario(), errors);
		
		if (!errors.hasErrors()){
			return this.alumnoDao.add(alumno);
		}
		
		return alumno;
	}
	
	/**
	 * Updates a single row in the Alumno table.
	 *
	 * @param alumno Alumno
	 * @return Alumno
	 */
	@Transactional(rollbackFor = Throwable.class)
	public Alumno update(Alumno alumno, String oldPassword, Errors errors) {
		this.checkOldPasswordValid(alumno, oldPassword, errors);
		this.checkUsernameValid(alumno.getUsuario(), errors);
		
		if (!errors.hasErrors()){
			return this.alumnoDao.update(alumno);
		}
		
		return alumno;
	 }
	@Override
	public Alumno getImagen(Alumno alumno){
		return this.alumnoDao.getImagen(alumno);
	}
	
	private void checkOldPasswordValid(Alumno alumno, String oldPassword, Errors errors) {
		if (oldPassword!=null && !"".equals(oldPassword) && !alumnoDao.isOldPasswordValid(alumno, oldPassword)){
			errors.reject("oldPassword", "passwordNoValido");
		}
	}
	
	private void checkUsernameValid(String username, Errors errors) {
		if(!alumnoDao.isUsernameValid(username)){
			errors.rejectValue("usuario", "usuarioYaExistente");
		}
	}
}

