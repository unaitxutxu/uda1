package com.ejie.x21a.service;


import com.ejie.x38.dto.Pagination;
import java.util.ArrayList;
import java.util.List;

import com.ejie.x21a.model.NoraMunicipio;

/**
 *  * NoraMunicipioService generated by UDA, 18-ene-2012 11:57:48.
 * @author UDA
 */

public interface NoraMunicipioService {

	/**
	 * Inserts a single row in the NoraMunicipio table.
	 *
	 * @param municipio NoraMunicipio
	 * @return NoraMunicipio
	 */
    NoraMunicipio add(NoraMunicipio municipio);

	/**
	 * Updates a single row in the NoraMunicipio table.
	 *
	 * @param municipio NoraMunicipio
	 * @return NoraMunicipio
	 */
	NoraMunicipio update(NoraMunicipio municipio);

	/**
	 * Finds a single row in the NoraMunicipio table.
	 *
	 * @param municipio NoraMunicipio
	 * @return NoraMunicipio
	 */
	NoraMunicipio find(NoraMunicipio municipio);

	/**
	 * Finds a List of rows in the NoraMunicipio table.
	 *
	 * @param municipio NoraMunicipio
	 * @param pagination Pagination
	 * @return List
	 */
	List<NoraMunicipio> findAll(NoraMunicipio municipio, Pagination pagination);

	/**
	 * Counts rows in the NoraMunicipio table.
	 *
	 * @param municipio NoraMunicipio
	 * @return Long
	 */
	Long findAllCount(NoraMunicipio municipio);
	
	/**
	 * Finds rows in the NoraMunicipio table using like.
	 *
	 * @param municipio NoraMunicipio
	 * @param pagination Pagination
     * @param startsWith Boolean	 
	 * @return List
	 */
	List<NoraMunicipio> findAllLike(NoraMunicipio municipio, Pagination pagination, Boolean startsWith) ;
  
	/**
	 * Deletes a single row in the NoraMunicipio table.
	 *
	 * @param municipio NoraMunicipio
	 * @return 
	 */
	void remove(NoraMunicipio municipio);
	
	/**
	 * Deletes multiple rows in the NoraMunicipio table.
	 *
	 * @param t17MunicipioList  ArrayList
	 * @return 
	 */	
	void removeMultiple(ArrayList<NoraMunicipio> t17MunicipioList);
    
}


