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
import com.ejie.x21a.dao.ProvinciaDao;
import com.ejie.x38.dto.Pagination;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.model.Provincia;
/**
 * ProvinciaServiceImpl generated by UDA 1.0, 29-jul-2011 9:08:11.
* @author UDA
*/
@Service(value = "provinciaService")
public  class ProvinciaServiceImpl implements ProvinciaService {
    /**
    * Final static logger.
    */
    private static final  Logger  logger = LoggerFactory.getLogger(ProvinciaServiceImpl.class);
@Autowired
    private ProvinciaDao provinciaDao;

   /**
    * Inserts a single row in the Provincia table.
    *
    * @param provincia Provincia
    * @return Provincia
    */
	@Transactional(rollbackFor = Throwable.class)
    public Provincia add(Provincia provincia) {
      return this.provinciaDao.add(provincia);
    }

    /**
    * Updates a single row in the Provincia table.
    *
    * @param provincia Provincia
    * @return Provincia
    */
	@Transactional(rollbackFor = Throwable.class)
    public Provincia update(Provincia provincia) {
      return this.provinciaDao.update(provincia);
    }

    /**
    * Finds a single row in the Provincia table.
    *
    * @param provincia Provincia
    * @return Provincia
    */
    public Provincia find(Provincia provincia) {
        return (Provincia) this.provinciaDao.find(provincia);
    }

    /**
     * Finds a List of rows in the Provincia table.
     *
     * @param provincia Provincia
     * @param pagination Pagination
     * @return List
     */
    public List<Provincia> findAll(Provincia provincia, Pagination pagination) {
       return (List<Provincia>) this.provinciaDao.findAll(provincia, pagination);
    }
    /**
    * Counts rows in the Provincia table.
    *
    * @param provincia Provincia
    * @return Long
    */
    public Long findAllCount(Provincia provincia) {        
        return  this.provinciaDao.findAllCount(provincia);
    }

	 /**
     * Finds rows in the Provincia table using like.
     *
     * @param provincia Provincia
     * @param pagination Pagination
     * @return List
     */
    public List<Provincia> findAllLike(Provincia provincia, Pagination pagination, Boolean startsWith) {
       return (List<Provincia>) this.provinciaDao.findAllLike(provincia, pagination, startsWith);
    }
    /**
     * Deletes a single row in the Provincia table.
     *
     * @param provincia Provincia
     */
	@Transactional(rollbackFor = Throwable.class)
    public void remove(Provincia provincia) {
        this.provinciaDao.remove(provincia);
    }
	
    /**
     * Deletes multiple rows in the Provincia table.
     *
     * @param provinciaList ArrayList
     */
	@Transactional(rollbackFor = Throwable.class)
    public void removeMultiple(ArrayList<Provincia> provinciaList) {
		for (Provincia  provinciaAux:provinciaList) {
            this.provinciaDao.remove(provinciaAux);
		}        
    }	

    /**
     *
     * @return ProvinciaDao
     */
    public ProvinciaDao getProvinciaDao() {
      return this.provinciaDao;
    }
    /**
     *
     * @param  provinciaDao ProvinciaDao
     */
    public void setProvinciaDao(ProvinciaDao provinciaDao) {
        logger.info("Setting Dependency "+provinciaDao);
        this.provinciaDao = provinciaDao;
    }
}

