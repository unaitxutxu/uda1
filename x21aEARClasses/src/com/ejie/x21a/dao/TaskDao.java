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

import com.ejie.x21a.model.Task;
import com.ejie.x38.dto.Pagination;

/**
 * ListDao generated by UDA, 14-ago-2012 12:59:38.
 * @author UDA
 */

public interface TaskDao {
    
    /**
     * Inserts a single row in the List table.
     *
     * @param task List
     * @return List
     */
    Task add(Task task);

    /**
     * Updates a single row in the List table.
     *
     * @param List List
     * @return List
     */
    Task update(Task task);
    
  
    /**
     * Finds a single row in the List table.
     *
     * @param List List
     * @return List
     */
    Task find(Task task);

    /**
     * Deletes a single row in the List table.
     *
     * @param List List
     * @return 
     */
    void remove(Task task);
    
    /**
     * Deletes a single row in the List table.
     *
     * @param List List
     * @return 
     */
    void done(Task task);

    /**
     * Finds a List of rows in the List table.
     *
     * @param List List
     * @param pagination Pagination
     * @return List
     */
    List<Task> findAll(Task task, Pagination pagination);
    
    Integer count(Task task, Pagination pagination);

   
}

