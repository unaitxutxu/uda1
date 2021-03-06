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

import com.ejie.x21a.model.Dashboard;

/**
 * ProvinciaDao generated by UDA, 14-ago-2012 12:59:38.
 * @author UDA
 */

public interface DashboardDao {
    
    /**
     * Inserts a single row in the Provincia table.
     *
     * @param provincia Provincia
     * @return Provincia
     */
	List<Dashboard> getAll();

	
	Dashboard get(Dashboard dashboard);
	
	Dashboard post(Dashboard dashboard);
	
	Dashboard put(Dashboard dashboard);
}

