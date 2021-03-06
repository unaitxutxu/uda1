package com.ejie.x21a.service;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.dao.NoraProvinciaDao;
import com.ejie.x21a.model.NoraProvincia;
import com.ejie.x38.dto.Pagination;

/**
 *  * NoraProvinciaServiceImpl generated by UDA, 18-ene-2012 11:57:48.
 * @author UDA
 */

@Service(value = "noraProvinciaService")
public  class NoraProvinciaServiceImpl implements NoraProvinciaService {

	private static final  Logger logger = LoggerFactory.getLogger(NoraProvinciaServiceImpl.class);
	@Autowired
	private NoraProvinciaDao provinciaDao;

	/**
	 * Inserts a single row in the NoraProvincia table.
	 *
	 * @param provincia NoraProvincia
	 * @return NoraProvincia
	 */
	@Transactional(rollbackFor = Throwable.class)
	public NoraProvincia add(NoraProvincia provincia) {
		return this.provinciaDao.add(provincia);
	}

	/**
	 * Updates a single row in the NoraProvincia table.
	 *
	 * @param provincia NoraProvincia
	 * @return NoraProvincia
	 */
	@Transactional(rollbackFor = Throwable.class)
	public NoraProvincia update(NoraProvincia provincia) {
		return this.provinciaDao.update(provincia);
	 }

	/**
	 * Finds a single row in the NoraProvincia table.
	 *
	 * @param provincia NoraProvincia
	 * @return NoraProvincia
	 */
	public NoraProvincia find(NoraProvincia provincia) {
		return (NoraProvincia) this.provinciaDao.find(provincia);
	}

	/**
	 * Finds a List of rows in the NoraProvincia table.
	 *
	 * @param provincia NoraProvincia
	 * @param pagination Pagination
	 * @return List
	 */
	public List<NoraProvincia> findAll(NoraProvincia provincia, Pagination pagination) {
		return (List<NoraProvincia>) this.provinciaDao.findAll(provincia, pagination);
	}
    
	/**
	 * Counts rows in the NoraProvincia table.
	 *
	 * @param provincia NoraProvincia
	 * @return Long
	 */
	public Long findAllCount(NoraProvincia provincia) {        
		return  this.provinciaDao.findAllCount(provincia);
	}

	/**
	 * Finds rows in the NoraProvincia table using like.
	 *
	 * @param provincia NoraProvincia
	 * @param pagination Pagination
	 * @param startsWith Boolean
	 * @return List
	 */
	public List<NoraProvincia> findAllLike(NoraProvincia provincia, Pagination pagination, Boolean startsWith) {
		return (List<NoraProvincia>) this.provinciaDao.findAllLike(provincia, pagination, startsWith);
	}
    
	/**
	 * Deletes a single row in the NoraProvincia table.
	 *
	 * @param provincia NoraProvincia
	 * @return
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void remove(NoraProvincia provincia) {
		this.provinciaDao.remove(provincia);
	}
	
	/**
	 * Deletes multiple rows in the NoraProvincia table.
	 *
	 * @param provinciaList ArrayList
	 * @return
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void removeMultiple(ArrayList<NoraProvincia> provinciaList) {
		for (NoraProvincia  t17ProvinciaAux:provinciaList) {
			this.provinciaDao.remove(t17ProvinciaAux);
		}
	}


	/**
	 * Getter method for NoraProvinciaDao
	 *
	 * @return NoraProvinciaDao
	 */
	public NoraProvinciaDao getT17ProvinciaDao() {
		return this.provinciaDao;
	}
    
	/**
	 * Setter method for NoraProvinciaDao.
	 *
	 * @param  provinciaDao NoraProvinciaDao
	 * @return
	 */
	public void setT17ProvinciaDao(NoraProvinciaDao provinciaDao) {
		logger.info("Setting Dependency "+provinciaDao);
		this.provinciaDao = provinciaDao;
	}
}

