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
package com.ejie.x21a.dao;
/**
 * Localidad generated by UDA 1.0, 29-jul-2011 9:08:11.
 */

import com.ejie.x38.dto.Pagination;
import java.util.List;

import com.ejie.x21a.model.Localidad;
/**
 * LocalidadDao generated by UDA 1.0, 29-jul-2011 9:08:11.
* @author UDA
*/
public interface LocalidadDao {
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
     * Deletes a single row in the Localidad table.
     *
     * @param localidad Localidad
     * 
     */
    void remove(Localidad localidad);

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
     * @return List
     */
    Long findAllCount(Localidad localidad);
	 /**
     * Finds rows in the Localidad table using like.
     *
     * @param localidad Localidad
     * @return List
     */
	List<Localidad> findAllLike(Localidad localidad, Pagination pagination, Boolean startsWith);
}

