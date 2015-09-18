package com.ejie.x21a.service;


import java.util.List;

import com.ejie.x21a.model.NoraPais;
import com.ejie.x38.dto.Pagination;

/**
 *  * NoraPaisService generated by UDA, 18-ene-2012 11:57:48.
 * @author UDA
 */

public interface NoraPaisService {


	/**
	 * Finds a single row in the NoraPais table.
	 *
	 * @param pais NoraPais
	 * @return NoraPais
	 */
	NoraPais find(NoraPais pais);

	/**
	 * Finds a List of rows in the NoraPais table.
	 *
	 * @param pais NoraPais
	 * @param pagination Pagination
	 * @return List
	 */
	List<NoraPais> findAll(NoraPais pais, Pagination pagination);

	/**
	 * Counts rows in the NoraPais table.
	 *
	 * @param pais NoraPais
	 * @return Long
	 */
	Long findAllCount(NoraPais pais);
	
	/**
	 * Finds rows in the NoraPais table using like.
	 *
	 * @param pais NoraPais
	 * @param pagination Pagination
     * @param startsWith Boolean	 
	 * @return List
	 */
	List<NoraPais> findAllLike(NoraPais pais, Pagination pagination, Boolean startsWith) ;
  
    
}


