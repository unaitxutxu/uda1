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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ejie.x21a.model.Comarca;
import com.ejie.x21a.model.Localidad;
import com.ejie.x21a.model.Provincia;
import com.ejie.x21a.service.ComarcaService;
import com.ejie.x21a.service.LocalidadService;
import com.ejie.x38.dto.JQGridJSONModel;
import com.ejie.x38.dto.Pagination;
import com.ejie.x38.util.ObjectConversionManager;

/**
 * ExperimentalController
 * 
 * @author UDA
 */
@Controller
@RequestMapping(value = "/experimental")
public class ExperimentalController {

	private static final Logger logger = LoggerFactory.getLogger(ExperimentalController.class);

	//Tabla Maestro-Detalle
	@RequestMapping(value = "maestro_detalle", method = RequestMethod.GET)
	public String getMD(Model model) {
		return "maestro_detalle";
	}

	//multi entidad
	@RequestMapping(value = "mant_multi_entidad", method = RequestMethod.GET)
	public String getMultiEntidad(Model model) {
		return "mant_multi_entidad";
	}
	
	//mantenimiento clave compuesta multiseleccion
	@RequestMapping(value = "mant_clave_compuesta_multi", method = RequestMethod.GET)
	public String getClaveCompuestaMulti(Model model) {
		return "mant_clave_compuesta_multi";
	}
	
	//mantenimiento clave compuesta edicion en linea
	@RequestMapping(value = "mant_clave_compuesta_edlinea", method = RequestMethod.GET)
	public String getClaveCompuestaEdlinea(Model model) {
		return "mant_clave_compuesta_edlinea";
	}
	
	
	/**
	 * SERVICIOS NECESARIOS:
	 * 		- Comarca
	 * 		- Localidad
	 * 		- DepartamentoProvincia
	 */
		@Autowired 
		private ComarcaService comarcaService;
		
		@Autowired 
		private LocalidadService localidadService;
		
		//@Autowired 
		//private DepartamentoProvinciaService departamentoProvinciaService;
		
		
		
	/**
	 * MAESTRO-DETALLE (Comarca)
	 */
		/**
		 * Method 'getById'.
		 * @param  code BigDecimal
		 * @return String
		 */
		@RequestMapping(value = "comarca/{code}", method = RequestMethod.GET)
		public @ResponseBody Comarca getById(@PathVariable BigDecimal code) {
	            Comarca comarca = new Comarca();
				comarca.setCode(code);
	            comarca = this.comarcaService.find(comarca);
	            return comarca;
		}
		@RequestMapping(value = "comarca",method = RequestMethod.GET)
		public @ResponseBody Object getAll(
			@RequestParam(value = "code", required = false) BigDecimal code,
			@RequestParam(value = "descEs", required = false) String descEs,
			@RequestParam(value = "descEu", required = false) String descEu,
			@RequestParam(value = "css", required = false) String css,
			@RequestParam(value = "codeProvincia", required = false) BigDecimal codeProvincia,
			HttpServletRequest request) {
					Comarca filterComarca = new Comarca(code, descEs, descEu, css, new Provincia());
	                Pagination pagination = null;
				    if (request.getHeader("JQGridModel") != null &&  request.getHeader("JQGridModel").equals("true")) {
					    pagination = new Pagination();
					    pagination.setPage(Long.valueOf(request.getParameter("page")));
					    pagination.setRows(Long.valueOf(request.getParameter("rows")));
					    pagination.setSort(request.getParameter("sidx"));
					    pagination.setAscDsc(request.getParameter("sord"));
	                    List<Comarca> comarcas =  this.comarcaService.findAll(filterComarca, pagination);

		        Long total =  getAllCount(filterComarca);
					    JQGridJSONModel data = new JQGridJSONModel();
					    data.setPage(request.getParameter("page"));
					    data.setRecords(total.intValue());
					    data.setTotal(total, pagination.getRows());
					    data.setRows(comarcas);
					    return data;
					}else{
			    return this.comarcaService.findAll(filterComarca, pagination);
			            }
					}

		@RequestMapping(value = "comarca/count", method = RequestMethod.GET)
		public @ResponseBody Long getAllCount(
		@RequestParam(value = "comarca", required = false) Comarca  filterComarca) {
				return comarcaService.findAllCount(filterComarca != null ? filterComarca: new Comarca ());
		}
		/**
		 * Method 'edit'.
		 * @param	 comarca Comarca 
		 * @return Comarca
		 */
		@RequestMapping(value="comarca", method = RequestMethod.PUT)
	    public @ResponseBody Comarca edit(@RequestBody Comarca comarca) {		
	            Comarca comarcaAux  = this.comarcaService.update(comarca);
			logger.info("Entity correctly updated!");
	            return comarcaAux;
	        }

		 /**
		 * Method 'add'.
		 * @param	 comarca Comarca 
		 * @return Comarca
		 */
		@RequestMapping(method = RequestMethod.POST)
		public @ResponseBody Comarca add(@RequestBody Comarca comarca) {		
	            Comarca comarcaAux = this.comarcaService.add(comarca);
	            logger.info( "Entity correctly inserted!");
	        	return comarcaAux;
		}

		 /**
		 * Method 'remove'.
		 * @param  code  BigDecimal
		 * @param response  HttpServletResponse
		 *
		 */
		@RequestMapping(value = "comarca/{code}", method = RequestMethod.DELETE)
		@ResponseStatus(value=HttpStatus.OK)
	    public void remove(@PathVariable BigDecimal code) {
	            Comarca comarca = new Comarca();
	            comarca.setCode(code);
	            this.comarcaService.remove(comarca);
            logger.info("Entity correctly deleted!");
	    }
		
		 /**
		 * Method 'removeAll'.
		 * @param  comarcaIds  ArrayList
		 * @param response  HttpServletResponse
		 *
		 */	
		@RequestMapping(value = "comarca/deleteAll", method = RequestMethod.POST)
		@ResponseStatus(value=HttpStatus.OK)
		public void removeMultiple(@RequestBody ArrayList<ArrayList<String>> comarcaIds) {
	        ArrayList<Comarca> comarcaList = new ArrayList<Comarca>();
	            for (ArrayList<String> comarcaId:comarcaIds) {
				    Iterator<String> iterator = comarcaId.iterator();
					    Comarca comarca = new Comarca();
				        comarca.setCode(ObjectConversionManager.convert(iterator.next(), java.math.BigDecimal.class));
					    comarcaList.add(comarca);
			    }
	            this.comarcaService.removeMultiple(comarcaList);
            logger.info("All entities correctly deleted!");
		}	
		
	/**
	 * MAESTRO-DETALLE (Localidad)
	 */
		@RequestMapping(value = "localidad",method = RequestMethod.GET)
		public @ResponseBody Object getAll(
			@RequestParam(value = "code", required = false) BigDecimal code,
			@RequestParam(value = "comarcaCode", required = false) BigDecimal comarcaCode,
			@RequestParam(value = "descEs", required = false) String descEs,
			@RequestParam(value = "descEu", required = false) String descEu,
			@RequestParam(value = "css", required = false) String css,
			HttpServletRequest request) {
					Localidad filterLocalidad = new Localidad(code, descEs, descEu, css, new Comarca(comarcaCode, null, null, null, null));
	                Pagination pagination = null;
				    if (request.getHeader("JQGridModel") != null &&  request.getHeader("JQGridModel").equals("true")) {
					    pagination = new Pagination();
					    pagination.setPage(Long.valueOf(request.getParameter("page")));
					    pagination.setRows(Long.valueOf(request.getParameter("rows")));
					    pagination.setSort(request.getParameter("sidx"));
					    pagination.setAscDsc(request.getParameter("sord"));
	                    List<Localidad> localidads =  this.localidadService.findAll(filterLocalidad, pagination);

						
				        Long total =  getAllCount(filterLocalidad, request);
					    JQGridJSONModel data = new JQGridJSONModel();
					    data.setPage(request.getParameter("page"));
					    data.setRecords(total.intValue());
					    data.setTotal(total, pagination.getRows());
					    data.setRows(localidads);
					    return data;
					}else{
					    return this.localidadService.findAll(filterLocalidad, pagination);
				}
		}
		@RequestMapping(value = "localidad/count", method = RequestMethod.GET)
		public @ResponseBody Long getAllCount(@RequestParam(value = "localidad", required = false) Localidad  filterLocalidad, HttpServletRequest request) {
				return localidadService.findAllCount(filterLocalidad != null ? filterLocalidad: new Localidad ());
			}
		/**
		 * Method 'edit'.
		 * 
		 * @param localidad Localidad
		 * @param response HttpServletResponse
		 * @return Localidad
		 */
		@RequestMapping(value="localidad", method = RequestMethod.PUT)
		public @ResponseBody
		Localidad editLocalidad(@RequestBody Localidad localidad) {
				Localidad localidadAux = this.localidadService.update(localidad);
			logger.info("Entity correctly edited!");
				return localidadAux;
		}

		/**
		 * Method 'add'.
		 * 
		 * @param localidad Localidad
		 * @return Localidad
		 */
		@RequestMapping(value="localidad", method = RequestMethod.POST)
		public @ResponseBody
		Localidad addLocalidad(@RequestBody Localidad localidad) {
				Localidad localidadAux = this.localidadService.add(localidad);
				logger.info( "Entity correctly inserted!");
				return localidadAux;
		}

		/**
		 * Method 'remove'.
		 * 
		 * @param code BigDecimal
		 * 
		 */
		@RequestMapping(value = "localidad/{code}", method = RequestMethod.DELETE)
		@ResponseStatus(value=HttpStatus.OK)
		public void removeLocalidad(@PathVariable BigDecimal code) {
				Localidad localidad = new Localidad();
				localidad.setCode(code);
				this.localidadService.remove(localidad);
			logger.info("Entity correctly deleted!");
			}

		/**
		 * Method 'removeAll'.
		 * 
		 * @param localidadIds ArrayList
		 */
		@RequestMapping(value = "localidad/deleteAll", method = RequestMethod.POST)
		@ResponseStatus(value=HttpStatus.OK)
		public void removeMultipleLocalidad(@RequestBody ArrayList<ArrayList<String>> localidadIds) {
			ArrayList<Localidad> localidadList = new ArrayList<Localidad>();
				for (ArrayList<String> localidadId : localidadIds) {
					Iterator<String> iterator = localidadId.iterator();
					Localidad localidad = new Localidad();
					localidad.setCode(ObjectConversionManager.convert(
							iterator.next(), java.math.BigDecimal.class));
					localidadList.add(localidad);
				}
				this.localidadService.removeMultiple(localidadList);
			 logger.info("All entities correctly deleted!");
		}
		/**
		 * Method 'getById'.
		 * 
		 * @param code BigDecimal
		 * @return String
		 */
		@RequestMapping(value = "localidad/{codeLocalidad}", method = RequestMethod.GET)
		public @ResponseBody
		Localidad getByIdLocalidad(@PathVariable BigDecimal codeLocalidad) {
				Localidad localidad = new Localidad();
				localidad.setCode(codeLocalidad);
				localidad = this.localidadService.find(localidad);
				return localidad;
		}
			
}
