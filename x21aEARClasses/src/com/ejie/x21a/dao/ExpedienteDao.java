package com.ejie.x21a.dao;

import java.util.List;

import com.ejie.x21a.model.Expediente;

/**
 *  * AlumnoDao generated by UDA, 01-mar-2012 9:33:10.
 * @author UDA
 */

public interface ExpedienteDao {
    
	/*
	 * OPERACIONES CRUD
	 */


	List<Expediente> getAll();

	Boolean putFase (Expediente expediente);
	
}

