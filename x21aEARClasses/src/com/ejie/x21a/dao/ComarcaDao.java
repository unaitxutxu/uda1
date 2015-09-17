package com.ejie.x21a.dao;
/**
 * Comarca generated by UDA 1.0, 29-jul-2011 9:08:11.
 */

import com.ejie.x38.dto.Pagination;
import java.util.List;

import com.ejie.x21a.model.Comarca;
/**
 * ComarcaDao generated by UDA 1.0, 29-jul-2011 9:08:11.
* @author UDA
*/
public interface ComarcaDao {
    /**
     * Inserts a single row in the Comarca table.
     *
     * @param comarca Comarca
     * @return Comarca
     */
    Comarca add(Comarca comarca);

    /**
     * Updates a single row in the Comarca table.
     *
     * @param comarca Comarca
     * @return Comarca
     */
    Comarca update(Comarca comarca);

    /**
     * Finds a single row in the Comarca table.
     *
     * @param comarca Comarca
     * @return Comarca
     */
    Comarca find(Comarca comarca);

    /**
     * Deletes a single row in the Comarca table.
     *
     * @param comarca Comarca
     * 
     */
    void remove(Comarca comarca);

    /**
     * Finds a List of rows in the Comarca table.
     *
     * @param comarca Comarca
     * @param pagination Pagination
     * @return List
     */
    List<Comarca> findAll(Comarca comarca, Pagination pagination);

    /**
     * Counts rows in the Comarca table.
     *
     * @param comarca Comarca
     * @return List
     */
    Long findAllCount(Comarca comarca);
	 /**
     * Finds rows in the Comarca table using like.
     *
     * @param comarca Comarca
     * @return List
     */
	List<Comarca> findAllLike(Comarca comarca, Pagination pagination, Boolean startsWith);
}

