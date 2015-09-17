package com.ejie.x21a.service;
import com.ejie.x21a.dao.LocalidadDao;
import com.ejie.x38.dto.Pagination;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.model.Localidad;
/**
 * LocalidadServiceImpl generated by UDA 1.0, 26-may-2011 13:45:00.
* @author UDA
*/
@Service(value = "localidadService")
public  class LocalidadServiceImpl implements LocalidadService {
    /**
    * Final static logger.
    */
    private static final  Logger  logger = Logger.getLogger(LocalidadServiceImpl.class);
@Autowired
    private LocalidadDao localidadDao;

   /**
    * Inserts a single row in the Localidad table.
    *
    * @param localidad Localidad
    * @return Localidad
    */
	@Transactional(rollbackFor = Throwable.class)
    public Localidad add(Localidad localidad) {
      return this.localidadDao.add(localidad);
    }

    /**
    * Updates a single row in the Localidad table.
    *
    * @param localidad Localidad
    * @return Localidad
    */
	@Transactional(rollbackFor = Throwable.class)
    public Localidad update(Localidad localidad) {
      return this.localidadDao.update(localidad);
    }

    /**
    * Finds a single row in the Localidad table.
    *
    * @param localidad Localidad
    * @return Localidad
    */
    public Localidad find(Localidad localidad) {
        return (Localidad) this.localidadDao.find(localidad);
    }

    /**
     * Finds a List of rows in the Localidad table.
     *
     * @param localidad Localidad
     * @param pagination Pagination
     * @return List
     */
    public List<Localidad> findAll(Localidad localidad, Pagination pagination) {
       return (List<Localidad>) this.localidadDao.findAll(localidad, pagination);
    }
    /**
    * Counts rows in the Localidad table.
    *
    * @param localidad Localidad
    * @return Long
    */
    public Long findAllCount(Localidad localidad) {        
        return  this.localidadDao.findAllCount(localidad);
    }

	 /**
     * Finds rows in the Localidad table using like.
     *
     * @param localidad Localidad
     * @param pagination Pagination
     * @return List
     */
    public List<Localidad> findAllLike(Localidad localidad, Pagination pagination, Boolean startsWith) {
       return (List<Localidad>) this.localidadDao.findAllLike(localidad, pagination, startsWith);
    }
    /**
     * Deletes a single row in the Localidad table.
     *
     * @param localidad Localidad
     */
	@Transactional(rollbackFor = Throwable.class)
    public void remove(Localidad localidad) {
        this.localidadDao.remove(localidad);
    }
	
    /**
     * Deletes multiple rows in the Localidad table.
     *
     * @param localidadList ArrayList
     */
	@Transactional(rollbackFor = Throwable.class)
    public void removeMultiple(ArrayList<Localidad> localidadList) {
		for (Localidad  localidadAux:localidadList) {
            this.localidadDao.remove(localidadAux);
		}        
    }	

    /**
     *
     * @return LocalidadDao
     */
    public LocalidadDao getLocalidadDao() {
      return this.localidadDao;
    }
    /**
     *
     * @param  localidadDao LocalidadDao
     */
    public void setLocalidadDao(LocalidadDao localidadDao) {
        logger.log(Level.INFO, "Setting Dependency "+localidadDao);
        this.localidadDao = localidadDao;
    }
}

