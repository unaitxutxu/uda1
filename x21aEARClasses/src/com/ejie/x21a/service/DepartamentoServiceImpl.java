package com.ejie.x21a.service;
import com.ejie.x21a.dao.DepartamentoDao;
import com.ejie.x38.dto.Pagination;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.model.Departamento;
/**
 * DepartamentoServiceImpl generated by UDA 1.0, 26-may-2011 13:45:00.
* @author UDA
*/
@Service(value = "departamentoService")
public  class DepartamentoServiceImpl implements DepartamentoService {
    /**
    * Final static logger.
    */
    private static final  Logger  logger = LoggerFactory.getLogger(DepartamentoServiceImpl.class);
@Autowired
    private DepartamentoDao departamentoDao;

   /**
    * Inserts a single row in the Departamento table.
    *
    * @param departamento Departamento
    * @return Departamento
    */
	@Transactional(rollbackFor = Throwable.class)
    public Departamento add(Departamento departamento) {
      return this.departamentoDao.add(departamento);
    }

    /**
    * Updates a single row in the Departamento table.
    *
    * @param departamento Departamento
    * @return Departamento
    */
	@Transactional(rollbackFor = Throwable.class)
    public Departamento update(Departamento departamento) {
      return this.departamentoDao.update(departamento);
    }

    /**
    * Finds a single row in the Departamento table.
    *
    * @param departamento Departamento
    * @return Departamento
    */
    public Departamento find(Departamento departamento) {
        return (Departamento) this.departamentoDao.find(departamento);
    }

    /**
     * Finds a List of rows in the Departamento table.
     *
     * @param departamento Departamento
     * @param pagination Pagination
     * @return List
     */
    public List<Departamento> findAll(Departamento departamento, Pagination pagination) {
       return (List<Departamento>) this.departamentoDao.findAll(departamento, pagination);
    }
    /**
    * Counts rows in the Departamento table.
    *
    * @param departamento Departamento
    * @return Long
    */
    public Long findAllCount(Departamento departamento) {        
        return  this.departamentoDao.findAllCount(departamento);
    }

	 /**
     * Finds rows in the Departamento table using like.
     *
     * @param departamento Departamento
     * @param pagination Pagination
     * @return List
     */
    public List<Departamento> findAllLike(Departamento departamento, Pagination pagination, Boolean startsWith) {
       return (List<Departamento>) this.departamentoDao.findAllLike(departamento, pagination, startsWith);
    }
    /**
     * Deletes a single row in the Departamento table.
     *
     * @param departamento Departamento
     */
	@Transactional(rollbackFor = Throwable.class)
    public void remove(Departamento departamento) {
        this.departamentoDao.remove(departamento);
    }
	
    /**
     * Deletes multiple rows in the Departamento table.
     *
     * @param departamentoList ArrayList
     */
	@Transactional(rollbackFor = Throwable.class)
    public void removeMultiple(ArrayList<Departamento> departamentoList) {
		for (Departamento  departamentoAux:departamentoList) {
            this.departamentoDao.remove(departamentoAux);
		}        
    }	

    /**
     *
     * @return DepartamentoDao
     */
    public DepartamentoDao getDepartamentoDao() {
      return this.departamentoDao;
    }
    /**
     *
     * @param  departamentoDao DepartamentoDao
     */
    public void setDepartamentoDao(DepartamentoDao departamentoDao) {
        logger.info( "Setting Dependency "+departamentoDao);
        this.departamentoDao = departamentoDao;
    }
}

