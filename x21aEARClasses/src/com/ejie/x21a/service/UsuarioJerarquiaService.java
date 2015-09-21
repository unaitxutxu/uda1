package com.ejie.x21a.service;


import java.util.List;
import java.util.TreeMap;

import com.ejie.x21a.model.UsuarioJerarquia;
import com.ejie.x38.dto.Jerarquia;
import com.ejie.x38.dto.Pagination;

/**
 * UsuarioJerarquiaService generated by UDA, 03-oct-2012 10:36:40.
 * @author UDA
 */

public interface UsuarioJerarquiaService {

	/**
	 * Inserts a single row in the UsuarioJerarquia table.
	 *
	 * @param usuarioJerarquia UsuarioJerarquia
	 * @return UsuarioJerarquia
	 */
    UsuarioJerarquia add(UsuarioJerarquia usuarioJerarquia);

	/**
	 * Updates a single row in the UsuarioJerarquia table.
	 *
	 * @param usuarioJerarquia UsuarioJerarquia
	 * @return UsuarioJerarquia
	 */
	UsuarioJerarquia update(UsuarioJerarquia usuarioJerarquia);

	/**
	 * Finds a single row in the UsuarioJerarquia table.
	 *
	 * @param usuarioJerarquia UsuarioJerarquia
	 * @return UsuarioJerarquia
	 */
	UsuarioJerarquia find(UsuarioJerarquia usuarioJerarquia);

	/**
	 * Finds a List of rows in the UsuarioJerarquia table.
	 *
	 * @param usuarioJerarquia UsuarioJerarquia
	 * @param pagination Pagination
	 * @return List
	 */
	List<UsuarioJerarquia> findAll(UsuarioJerarquia usuarioJerarquia, Pagination pagination);

	/**
	 * Counts rows in the UsuarioJerarquia table.
	 *
	 * @param usuarioJerarquia UsuarioJerarquia
	 * @return Long
	 */
	Long findAllCount(UsuarioJerarquia usuarioJerarquia);
	
	/**
	 * Finds rows in the UsuarioJerarquia table using like.
	 *
	 * @param usuarioJerarquia UsuarioJerarquia
	 * @param pagination Pagination
     * @param startsWith Boolean	 
	 * @return List
	 */
	List<UsuarioJerarquia> findAllLike(UsuarioJerarquia usuarioJerarquia, Pagination pagination, Boolean startsWith) ;

	/**
	 * Counts rows in the UsuarioJerarquia table using like.
	 *
	 * @param usuarioJerarquia UsuarioJerarquia
     * @param startsWith Boolean	 
	 * @return Long
	 */
	Long findAllLikeCount(UsuarioJerarquia usuarioJerarquia, Boolean startsWith) ;
  
	/**
	 * Deletes a single row in the UsuarioJerarquia table.
	 *
	 * @param usuarioJerarquia UsuarioJerarquia
	 * @return 
	 */
	void remove(UsuarioJerarquia usuarioJerarquia);
	
	/**
	 * Deletes multiple rows in the UsuarioJerarquia table.
	 *
	 * @param usuarioJerarquiaList List
	 * @return 
	 */	
	void removeMultiple(List<UsuarioJerarquia> usuarioJerarquiaList);

	
	
	/*************
	 * JERARQUIA *
	 *************/
	
	/**
	 * @param UsuarioJerarquia UsuarioJerarquia
	 * @param pagination Pagination
	 * @return Jerarquia<UsuarioJerarquia>
	 */
	List<Jerarquia<UsuarioJerarquia>> findAllLikeJerarquia (UsuarioJerarquia UsuarioJerarquia, Pagination pagination);

	/**
	 * @param UsuarioJerarquia UsuarioJerarquia
	 * @param pagination Pagination
	 * @return UsuarioJerarquia
	 */
	Long findAllLikeCountJerarquia (UsuarioJerarquia UsuarioJerarquia, Pagination pagination);
	
	TreeMap<String, TreeMap<String, String>> findAllLikeSelected (UsuarioJerarquia UsuarioJerarquia, Pagination pagination);
}


