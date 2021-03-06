package com.ejie.x21a.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejie.x21a.dao.NoraPaisDao;
import com.ejie.x21a.model.NoraPais;
import com.ejie.x38.dto.Pagination;

/**
 *  * NoraPaisServiceImpl generated by UDA, 18-ene-2012 11:57:48.
 * @author UDA
 */

@Service(value = "noraPaisService")
public  class NoraPaisServiceImpl implements NoraPaisService {

	private static final  Logger logger = LoggerFactory.getLogger(NoraPaisServiceImpl.class);
	@Autowired
	private NoraPaisDao paisDao;



	/**
	 * Finds a single row in the NoraPais table.
	 *
	 * @param pais NoraPais
	 * @return NoraPais
	 */
	public NoraPais find(NoraPais pais) {
		return (NoraPais) this.paisDao.find(pais);
	}

	/**
	 * Finds a List of rows in the NoraPais table.
	 *
	 * @param pais NoraPais
	 * @param pagination Pagination
	 * @return List
	 */
	public List<NoraPais> findAll(NoraPais pais, Pagination pagination) {
		return (List<NoraPais>) this.paisDao.findAll(pais, pagination);
	}
    
	/**
	 * Counts rows in the NoraPais table.
	 *
	 * @param pais NoraPais
	 * @return Long
	 */
	public Long findAllCount(NoraPais pais) {        
		return  this.paisDao.findAllCount(pais);
	}

	/**
	 * Finds rows in the NoraPais table using like.
	 *
	 * @param pais NoraPais
	 * @param pagination Pagination
	 * @param startsWith Boolean
	 * @return List
	 */
	public List<NoraPais> findAllLike(NoraPais pais, Pagination pagination, Boolean startsWith) {
		return (List<NoraPais>) this.paisDao.findAllLike(pais, pagination, startsWith);
	}
    

	/**
	 * Getter method for NoraPaisDao
	 *
	 * @return NoraPaisDao
	 */
	public NoraPaisDao getT17ProvinciaDao() {
		return this.paisDao;
	}
    
	/**
	 * Setter method for NoraPaisDao.
	 *
	 * @param  paisDao NoraPaisDao
	 * @return
	 */
	public void setT17ProvinciaDao(NoraPaisDao paisDao) {
		logger.info("Setting Dependency "+paisDao);
		this.paisDao = paisDao;
	}
}

