/*
* Copyright 2012 E.J.I.E., S.A.
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
package com.ejie.x21a.dao;

import java.util.List;

import com.ejie.x21a.model.Usuario;
import com.ejie.x38.rss.RssContent;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JerarquiaDto;
import com.ejie.x38.dto.TableRowDto;

/**
 * UsuarioDao generated by UDA, 14-ago-2012 12:59:38.
 * @author UDA
 */

public interface JQGridUsuarioDao {
    
	/*
	 * OPERACIONES CRUD
	 */
	
    /**
     * Inserts a single row in the Usuario table.
     *
     * @param usuario Usuario
     * @return Usuario
     */
    Usuario add(Usuario usuario);

    /**
     * Updates a single row in the Usuario table.
     *
     * @param usuario Usuario
     * @return Usuario
     */
    Usuario update(Usuario usuario);

    /**
     * Finds a single row in the Usuario table.
     *
     * @param usuario Usuario
     * @return Usuario
     */
    Usuario find(Usuario usuario);

    /**
     * Deletes a single row in the Usuario table.
     *
     * @param usuario Usuario
     * @return 
     */
    void remove(Usuario usuario);

    /**
     * Finds a List of rows in the Usuario table.
     *
     * @param usuario Usuario
     * @param pagination Pagination
     * @return List
     */
    List<Usuario> findAll(Usuario usuario, JQGridRequestDto jqGridRequestDto);

	/**
     * Finds rows in the Usuario table using like.
     *
     * @param usuario Usuario
     * @param pagination Pagination
     * @param startsWith Boolean
     * @return List
     */
	List<Usuario> findAllLike(Usuario usuario, JQGridRequestDto jqGridRequestDto, Boolean startsWith);
	
	
	/*
	 * OPERACIONES RUP_TABLE
	 */
	
	
	void removeMultiple(Usuario filterUsuario, JQGridRequestDto jqGridRequestDto, Boolean startsWith);
	
	List<Usuario> getMultiple(Usuario filterUsuario, JQGridRequestDto jqGridRequestDto, Boolean startsWith);
	
    /**
     * Counts rows in the Usuario table using like.
     *
     * @param usuario Usuario
     * @param startsWith Boolean
     * @return Long
     */
    Long findAllLikeCount(Usuario usuario, Boolean startsWith);
    
    /**
     * Counts rows in the Usuario table.
     *
     * @param usuario Usuario
     * @return Long
     */
    Long findAllCount(Usuario usuario);
    
    List<TableRowDto<Usuario>> reorderSelection(Usuario usuario, JQGridRequestDto jqGridRequestDto, Boolean startsWith);
    
    List<TableRowDto<Usuario>> search(Usuario filterParams, Usuario searchParams, JQGridRequestDto jqGridRequestDto, Boolean startsWith);

    /*
	 * OPERACIONES RUP_TABLE JERARQUIA
	 */
    
	List<JerarquiaDto<Usuario>> findAllLikeJerarquia(Usuario filterUsuario,JQGridRequestDto jqGridRequestDto);
	
	Long findAllLikeCountJerarquia(Usuario filterUsuario, JQGridRequestDto jqGridRequestDto);
	
	List<TableRowDto<Usuario>> findAllChild(Usuario filterUsuario,JQGridRequestDto jqGridRequestDto);
	
	/*
	 * RSS
	 */
	List<RssContent> getRssFeed();
}

