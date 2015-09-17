package com.ejie.x21a.service;


import com.ejie.x38.dto.Pagination;
import java.util.ArrayList;
import java.util.List;

import com.ejie.x21a.model.Localidad;

/**
 * LocalidadService generated by UDA 1.0, 26-may-2011 13:45:00.
* @author UDA
*/
public interface LocalidadService {

    /**
     * Inserts a single row in the Localidad table.
     *
     * @param localidad Localidad
     * @return Localidad
     */
    Localidad add(Localidad localidad);

    /**
     * Updates a single row in the Localidad table.
     *
     * @param localidad Localidad
     * @return Localidad
     */
    Localidad update(Localidad localidad);

    /**
    * Finds a single row in the Localidad table.
    *
    * @param localidad Localidad
    * @return Localidad
    */
    Localidad find(Localidad localidad);

    /**
     * Finds a List of rows in the Localidad table.
     *
     * @param localidad Localidad
     * @param pagination Pagination
     * @return List
     */
    List<Localidad> findAll(Localidad localidad, Pagination pagination);

    /**
    * Counts rows in the Localidad table.
    *
    * @param localidad Localidad
    * @return Long
    */
    Long findAllCount(Localidad localidad);
    /**
     * Finds rows in the Localidad table using like.
     *
     * @param localidad Localidad
     * @param pagination Pagination
     * @return List
     */
      List<Localidad> findAllLike(Localidad localidad, Pagination pagination, Boolean startsWith) ;
  
    /**
     * Deletes a single row in the Localidad table.
     *
     * @param localidad Localidad
     */
    void remove(Localidad localidad);
	
    /**
     * Deletes multiple rows in the Localidad table.
     *
     * @param localidadList  ArrayList
     */	
	void removeMultiple(ArrayList<Localidad> localidadList);
    
}


