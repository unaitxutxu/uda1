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
package com.ejie.x21a.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ejie.x38.security.PerimetralSecurityWrapper;
import com.ejie.x38.security.UserCredentials;


/**
 * 
 * @author UDA
 *
 */
public class MyUserCredentials extends UserCredentials {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory
	.getLogger(MyUserCredentials.class);
	
	@Override
	public void afterCredentialsCreation(PerimetralSecurityWrapper perimetralSecurityWrapper, HttpServletRequest request){
		logger.info("Prueba conceptual de ejecucion");
	} 
		
	
			
}