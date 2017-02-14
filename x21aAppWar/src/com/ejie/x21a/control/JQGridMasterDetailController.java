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
package com.ejie.x21a.control;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejie.x21a.model.Comarca;
import com.ejie.x21a.model.Usuario;
import com.ejie.x21a.service.JQGridComarcaService;
import com.ejie.x21a.service.JQGridUsuarioService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;
import com.ejie.x38.dto.JerarquiaDto;
import com.ejie.x38.rup.jqgrid.filter.model.Filter;
import com.ejie.x38.rup.jqgrid.filter.service.FilterService;
/**
 * UsuarioServiceImpl generated by UDA 1.0, 26-may-2011 13:46:35.
* @author UDA
 */
@Controller
@RequestMapping (value = "/jqGridMasterDetail")
public class JQGridMasterDetailController  {

	private static final Logger logger = LoggerFactory.getLogger(JQGridMasterDetailController.class);

	@Autowired
	private JQGridUsuarioService jqGridUsuarioService;
	
	@Autowired 
	private JQGridComarcaService jqGridcomarcaService;
	
	@Autowired
	private FilterService filterService;
	
	
	 /**
	  * RUP_TABLE
	  */
		
//		@Json(mixins={@JsonMixin(target=Usuario.class, mixin=UsuarioMixIn.class)})
		@RequestMapping(value = "/comarca/filter", method = RequestMethod.POST)
		public @ResponseBody JQGridResponseDto<Comarca> filter(
				@RequestJsonBody(param="filter") Comarca comarca,
				@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
			JQGridMasterDetailController.logger.info("[GET - jqGrid] : Obtener Usuarios");
			
			return jqGridcomarcaService.filter(comarca, jqGridRequestDto, false);
		}
		
		@RequestMapping(value = "/comarca/multiFilter/add", method = RequestMethod.POST)
		public @ResponseBody Filter filterAdd(@RequestJsonBody(param="filtro") Filter filtro){
			JQGridMasterDetailController.logger.info("[POST - jqGrid] : add filter");
			
			 return filterService.insert(filtro);
		}	
		

		
		@RequestMapping(value = "/comarca/multiFilter/delete", method = RequestMethod.POST)
		public @ResponseBody Filter  filterDelete(
				@RequestJsonBody(param="filtro") Filter filtro) {
			JQGridMasterDetailController.logger.info("[POST - jqGrid] : delete filter");
			return  filterService.delete(filtro);
		}
		
		
		@RequestMapping(value = "/comarca/multiFilter/getDefault", method = RequestMethod.GET)
		public @ResponseBody Filter filterGetDefault(
			@RequestParam(value = "filterSelector", required = true) String filterSelector,
			@RequestParam(value = "user", required = true) String filterUser) {
			JQGridMasterDetailController.logger.info("[get - jqGrid] : getDefault filter");
			 return filterService.getDefault(filterSelector, filterUser);
		}
		
		
		
		
		@RequestMapping(value = "/comarca/multiFilter/getAll", method = RequestMethod.GET)
		public @ResponseBody List<Filter> filterGetAll(
			@RequestParam(value = "filterSelector", required = true) String filterSelector,
			@RequestParam(value = "user", required = true) String filterUser) {
			JQGridMasterDetailController.logger.info("[get - jqGrid] : GetAll filter");
			 return filterService.getAllFilters(filterSelector,filterUser);
		}
		
		
		
		
		@RequestMapping(value = "/search", method = RequestMethod.POST)
		public @ResponseBody Object search(
				@RequestJsonBody(param="filter") Usuario usuarioFilter,
				@RequestJsonBody(param="search") Usuario usuarioSearch,
				@RequestJsonBody JQGridRequestDto jqGridRequestDto){
			
			JQGridMasterDetailController.logger.info("[GET - find_ALL] : Obtener Usuarios por filtro");
			return jqGridUsuarioService.search(usuarioFilter, usuarioSearch, jqGridRequestDto, true);
		}
		
		@RequestMapping(value = "/jerarquia/filter", method = RequestMethod.POST)
		public @ResponseBody JQGridResponseDto<JerarquiaDto<Usuario>> jerarquia(
				@RequestJsonBody(param="filter") Usuario filterUsuario,
				@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
			JQGridMasterDetailController.logger.info("[GET - jqGrid] : Obtener Usuarios Jerarquia");
			return this.jqGridUsuarioService.jerarquia(filterUsuario, jqGridRequestDto, false);
		}
		
		@RequestMapping(value = "/jerarquiaChildren", method = RequestMethod.POST)
		public @ResponseBody JQGridResponseDto<JerarquiaDto<Usuario>> jerarquiaChildren (
				@RequestJsonBody(param="filter") Usuario filterUsuario,
				@RequestJsonBody JQGridRequestDto jqGridRequestDto){
			JQGridMasterDetailController.logger.info("[GET - jqGrid] : Obtener Jerarquia - Hijos");
			return this.jqGridUsuarioService.jerarquiaChildren(filterUsuario, jqGridRequestDto);
		}
		
	/**
	 * N/A	
	 */
		
		/**
		 * Method 'getAllCount'.
		 * @param filterUsuario Usuario 
		 * @return Long
		 */
//		@RequestMapping(value = "/count", method = RequestMethod.GET)
//		public @ResponseBody Long getAllCount(@RequestParam(value = "usuario", required = false) Usuario  filterUsuario) {
//			return jqGridUsuarioService.findAllLikeCount(filterUsuario != null ? filterUsuario: new Usuario (),false);
//		}
		
		
		
//		 /**
//		 * Method 'removeAll'.
//		 * @param  usuarioIds  ArrayList
//		 *
//		 */	
//		@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
//		@ResponseStatus(value=HttpStatus.OK)
//		public @ResponseBody List<List<String>> removeMultiple(@RequestBody List<List<String>> usuarioIds) {
//		List<Usuario> usuarioList = new ArrayList<Usuario>();
//        for (List<String> usuarioId:usuarioIds) {
//		    Iterator<String> iterator = usuarioId.iterator();
//			    Usuario usuario = new Usuario();
//		        usuario.setId(ObjectConversionManager.convert(iterator.next(), String.class));
//			    usuarioList.add(usuario);
//	    }
//        this.jqGridUsuarioService.removeMultiple(usuarioList);
//        logger.info("All entities correctly deleted!");
//        return usuarioIds;
//	}	
}