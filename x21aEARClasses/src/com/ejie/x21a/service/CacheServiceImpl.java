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
package com.ejie.x21a.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ejie.x21a.dao.JQGridUsuarioDao;
import com.ejie.x21a.model.Usuario;
import com.ejie.x38.dto.JQGridRequestDto;

/**
 * ComarcaServiceImpl generated by UDA, 14-ago-2012 12:59:39.
 * @author UDA
 */
@Repository
@Service(value = "cacheService")
public class CacheServiceImpl implements CacheService {

	@Autowired
	private JQGridUsuarioDao jQGridUsuarioDao;

	/**
	 * Inserts a single row in the Comarca table.
	 *
	 * @param comarca Comarca
	 * @return Comarca
	 */
	
	
	@Cacheable(value = "listaUsuarios", key = "#cache")
	public List<Usuario> getAll(Usuario usuario, JQGridRequestDto jqGridRequestDto, Boolean cache) {
		return this.jQGridUsuarioDao.findAll(usuario, jqGridRequestDto);
	}

	@Override
	@CacheEvict(value = "listaUsuarios", key="#cache" )
	public Usuario update(Usuario usuario, Boolean cache) {
		return jQGridUsuarioDao.update(usuario);
	}
	
	
	
}

