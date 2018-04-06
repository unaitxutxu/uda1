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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.x21a.dao.JQGridUsuarioDao;
import com.ejie.x21a.model.Usuario;
import com.ejie.x38.rss.RssContent;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;
import com.ejie.x38.dto.JerarquiaDto;
import com.ejie.x38.dto.TableRowDto;

/**
 * UsuarioServiceImpl generated by UDA, 14-ago-2012 12:59:39.
 * @author UDA
 */

@Service(value = "jqGridUsuarioService")
public class JQGridUsuarioServiceImpl implements JQGridUsuarioService {

	@Autowired
	private JQGridUsuarioDao jqGridUsuarioDao;
	
	/*
	 * OPERACIONES CRUD
	 */
	
	/**
	 * Inserts a single row in the Usuario table.
	 *
	 * @param usuario Usuario
	 * @return Usuario
	 */
	@Transactional(rollbackFor = Throwable.class)
	public Usuario add(Usuario usuario) {
		return this.jqGridUsuarioDao.add(usuario);
	}

	/**
	 * Updates a single row in the Usuario table.
	 *
	 * @param usuario Usuario
	 * @return Usuario
	 */
	@Transactional(rollbackFor = Throwable.class)
	public Usuario update(Usuario usuario) {
		return this.jqGridUsuarioDao.update(usuario);
	 }

	/**
	 * Finds a single row in the Usuario table.
	 *
	 * @param usuario Usuario
	 * @return Usuario
	 */
	public Usuario find(Usuario usuario) {
		return (Usuario) this.jqGridUsuarioDao.find(usuario);
	}
	
	/**
	 * Deletes a single row in the Usuario table.
	 *
	 * @param usuario Usuario
	 * @return
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void remove(Usuario usuario) {
		this.jqGridUsuarioDao.remove(usuario);
	}
	
	/**
	 * Finds a List of rows in the Usuario table.
	 *
	 * @param usuario Usuario
	 * @param pagination Pagination
	 * @return List
	 */
	public List<Usuario> findAll(Usuario usuario, JQGridRequestDto jqGridRequestDto) {
		return (List<Usuario>) this.jqGridUsuarioDao.findAll(usuario, jqGridRequestDto);
	}
    

	/**
	 * Finds rows in the Usuario table using like.
	 *
	 * @param usuario Usuario
	 * @param pagination Pagination
	 * @param startsWith Boolean
	 * @return List
	 */
	public List<Usuario> findAllLike(Usuario usuario, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		return (List<Usuario>) this.jqGridUsuarioDao.findAllLike(usuario, jqGridRequestDto, startsWith);
	}

	
	/*
	 * OPERACIONES RUP_TABLE
	 */
	
	/**
	 * Deletes multiple rows in the Usuario table.
	 *
	 * @param usuarioList List
	 * @return
	 */
	@Transactional(rollbackFor = Throwable.class)
	public void removeMultiple(Usuario filterUsuario, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		this.jqGridUsuarioDao.removeMultiple(filterUsuario, jqGridRequestDto, startsWith);
	}
	
	@Transactional(rollbackFor = Throwable.class)
	public List<Usuario> getMultiple(Usuario filterUsuario, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		return this.jqGridUsuarioDao.getMultiple(filterUsuario, jqGridRequestDto, startsWith);
	}

	@Override
	public Object reorderSelection(Usuario usuario, JQGridRequestDto jqGridRequestDto,
			Boolean startsWith) {
		return this.jqGridUsuarioDao.reorderSelection(usuario, jqGridRequestDto, startsWith);
	}

	@Override
	public List<TableRowDto<Usuario>> search(Usuario filterParams, Usuario searchParams, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		return this.jqGridUsuarioDao.search(filterParams, searchParams, jqGridRequestDto, startsWith);
	}

	public JQGridResponseDto<Usuario> filter(Usuario filterUsuario, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<Usuario> listaUsuario =  this.jqGridUsuarioDao.findAllLike(filterUsuario, jqGridRequestDto, false);
		Long recordNum =  this.jqGridUsuarioDao.findAllLikeCount(filterUsuario != null ? filterUsuario: new Usuario (),false);
		if (jqGridRequestDto.getMultiselection().getSelectedIds()!=null){
			List<TableRowDto<Usuario>> reorderSelection = this.jqGridUsuarioDao.reorderSelection(filterUsuario, jqGridRequestDto, startsWith);
			return new JQGridResponseDto<Usuario>(jqGridRequestDto, recordNum, listaUsuario, reorderSelection);
		}
		return new JQGridResponseDto<Usuario>(jqGridRequestDto, recordNum, listaUsuario);  
	}
	
	/*
	 * OPERACIONES RUP_TABLE JERARQUIA
	 */

	public JQGridResponseDto<JerarquiaDto<Usuario>> jerarquia (Usuario filterUsuario, JQGridRequestDto jqGridRequestDto, Boolean startsWith) {
		List<JerarquiaDto<Usuario>> listaUsuario =  this.jqGridUsuarioDao.findAllLikeJerarquia(filterUsuario, jqGridRequestDto);
		Long recordNum = this.jqGridUsuarioDao.findAllLikeCountJerarquia(filterUsuario, jqGridRequestDto);
		return new JQGridResponseDto<JerarquiaDto<Usuario>>(jqGridRequestDto, recordNum, listaUsuario);
	}
	
	public JQGridResponseDto<JerarquiaDto<Usuario>> jerarquiaChildren (Usuario filterUsuario, JQGridRequestDto jqGridRequestDto){
		JQGridResponseDto<JerarquiaDto<Usuario>> jqGridResponseDto = new JQGridResponseDto<JerarquiaDto<Usuario>>();
		jqGridResponseDto.addAdditionalParam(JQGridResponseDto.CHILDREN, this.jqGridUsuarioDao.findAllChild(filterUsuario, jqGridRequestDto));
		return jqGridResponseDto;
	}

	/*
	 * RSS
	 */
	public List<RssContent> getRssFeed(){
		return this.jqGridUsuarioDao.getRssFeed();
	}
	
	
}

