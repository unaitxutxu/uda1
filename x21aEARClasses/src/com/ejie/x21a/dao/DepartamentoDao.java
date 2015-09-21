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

import com.ejie.x38.dto.Pagination;
import java.util.List;

import com.ejie.x21a.model.Departamento;

/**
 * DepartamentoDao generated by UDA, 14-ago-2012 12:59:38.
 * @author UDA
 */

public interface DepartamentoDao {
    
    /**
     * Inserts a single row in the Departamento table.
     *
     * @param departamento Departamento
     * @return Departamento
     */
    Departamento add(Departamento departamento);

    /**
     * Updates a single row in the Departamento table.
     *
     * @param departamento Departamento
     * @return Departamento
     */
    Departamento update(Departamento departamento);

    /**
     * Finds a single row in the Departamento table.
     *
     * @param departamento Departamento
     * @return Departamento
     */
    Departamento find(Departamento departamento);

    /**
     * Deletes a single row in the Departamento table.
     *
     * @param departamento Departamento
     * @return 
     */
    void remove(Departamento departamento);

    /**
     * Finds a List of rows in the Departamento table.
     *
     * @param departamento Departamento
     * @param pagination Pagination
     * @return List
     */
    List<Departamento> findAll(Departamento departamento, Pagination pagination);

    /**
     * Counts rows in the Departamento table.
     *
     * @param departamento Departamento
     * @return Long
     */
    Long findAllCount(Departamento departamento);
	
	/**
     * Finds rows in the Departamento table using like.
     *
     * @param departamento Departamento
     * @param pagination Pagination
     * @param startsWith Boolean
     * @return List
     */
	List<Departamento> findAllLike(Departamento departamento, Pagination pagination, Boolean startsWith);
	
    /**
     * Counts rows in the Departamento table using like.
     *
     * @param departamento Departamento
     * @param startsWith Boolean
     * @return Long
     */
    Long findAllLikeCount(Departamento departamento, Boolean startsWith);
}

