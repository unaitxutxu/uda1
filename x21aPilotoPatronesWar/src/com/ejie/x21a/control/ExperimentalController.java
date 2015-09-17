package com.ejie.x21a.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ejie.x21a.control.genericObjectUtils.GenericObject;
import com.ejie.x21a.control.genericObjectUtils.RUPBean;
import com.ejie.x21a.model.Comarca;
import com.ejie.x21a.model.DepartamentoProvincia;
import com.ejie.x21a.model.Localidad;
import com.ejie.x21a.model.Provincia;
import com.ejie.x21a.service.ComarcaService;
import com.ejie.x21a.service.DepartamentoProvinciaService;
import com.ejie.x21a.service.LocalidadService;
import com.ejie.x38.control.exception.ResourceNotFoundException;
import com.ejie.x38.control.exception.ServiceUnavailableException;
import com.ejie.x38.dto.JQGridJSONModel;
import com.ejie.x38.dto.Pagination;

/**
 * UsuarioServiceImpl generated by UDA 1.0, 26-may-2011 10:46:22.
 * 
 * @author UDA
 */
@Controller
@RequestMapping(value = "/experimental")
public class ExperimentalController {

	private static final Logger logger = Logger.getLogger(ExperimentalController.class);

	@Autowired
	private Properties appConfiguration;
	
	//Tabla Maestro-Detalle
	@RequestMapping(value = "maestro_detalle", method = RequestMethod.GET)
	public ModelAndView getMD(Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
		return new ModelAndView("maestro_detalle", "model", model);
	}
	
	//Generic Object
	@RequestMapping(value = "generic_object", method = RequestMethod.GET)
	public ModelAndView getGenericObject(Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
		return new ModelAndView("generic_object", "model", model);
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
		
		@Autowired 
		private DepartamentoProvinciaService departamentoProvinciaService;
		
	/**
	 * MAESTRO-DETALLE (Comarca)
	 */
		@RequestMapping(value = "comarca",method = RequestMethod.GET)
		public @ResponseBody Object getAll(
			@RequestParam(value = "code", required = false) BigDecimal code,
			@RequestParam(value = "descEs", required = false) String descEs,
			@RequestParam(value = "descEu", required = false) String descEu,
			@RequestParam(value = "css", required = false) String css,
			@RequestParam(value = "codeProvincia", required = false) BigDecimal codeProvincia,
			HttpServletRequest request) {
				try{
					Comarca filterComarca = new Comarca(code, descEs, descEu, css, new Provincia());
	                Pagination pagination = null;
				    if (request.getHeader("JQGridModel") != null &&  request.getHeader("JQGridModel").equals("true")) {
					    pagination = new Pagination();
					    pagination.setPage(Long.valueOf(request.getParameter("page")));
					    pagination.setRows(Long.valueOf(request.getParameter("rows")));
					    pagination.setSort(request.getParameter("sidx"));
					    pagination.setAscDsc(request.getParameter("sord"));
	                    List<Comarca> comarcas =  this.comarcaService.findAll(filterComarca, pagination);

	     			    if (comarcas == null) {
		    	            throw new Exception("No data Found.");
			            }
						
				        Long total =  getAllCount(filterComarca, request);
					    JQGridJSONModel data = new JQGridJSONModel();
					    data.setPage(request.getParameter("page"));
					    data.setRecords(total.intValue());
					    data.setTotal(total, pagination.getRows());
					    data.setRows(comarcas);
					    return data;
					}else{
					    List<Comarca> comarcas =  this.comarcaService.findAll(filterComarca, pagination);
						if (comarcas == null) {
		    	            throw new Exception("No data Found.");
			            }
					    return comarcas;
					}
	            }catch(Exception e){
				    throw new ResourceNotFoundException("No data Found.");
				}
		}

		@RequestMapping(value = "comarca/count", method = RequestMethod.GET)
		public @ResponseBody Long getAllCount(
		@RequestParam(value = "comarca", required = false) Comarca  filterComarca, HttpServletRequest request) {
		    try {
				return comarcaService.findAllCount(filterComarca != null ? filterComarca: new Comarca ());
			} catch (Exception e) {
				throw new ServiceUnavailableException("Count Service is not responding.");
			}
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
				try{
					Localidad filterLocalidad = new Localidad(code, descEs, descEu, css, new Comarca(comarcaCode, null, null, null, null, null));
	                Pagination pagination = null;
				    if (request.getHeader("JQGridModel") != null &&  request.getHeader("JQGridModel").equals("true")) {
					    pagination = new Pagination();
					    pagination.setPage(Long.valueOf(request.getParameter("page")));
					    pagination.setRows(Long.valueOf(request.getParameter("rows")));
					    pagination.setSort(request.getParameter("sidx"));
					    pagination.setAscDsc(request.getParameter("sord"));
	                    List<Localidad> localidads =  this.localidadService.findAll(filterLocalidad, pagination);

	     			    if (localidads == null) {
		    	            throw new Exception("No data Found.");
			            }
						
				        Long total =  getAllCount(filterLocalidad, request);
					    JQGridJSONModel data = new JQGridJSONModel();
					    data.setPage(request.getParameter("page"));
					    data.setRecords(total.intValue());
					    data.setTotal(total, pagination.getRows());
					    data.setRows(localidads);
					    return data;
					}else{
					    List<Localidad> localidads =  this.localidadService.findAll(filterLocalidad, pagination);
						if (localidads == null) {
		    	            throw new Exception("No data Found.");
			            }
					    return localidads;
					}
	            }catch(Exception e){
				    throw new ResourceNotFoundException("No data Found.");
				}
		}
		@RequestMapping(value = "localidad/count", method = RequestMethod.GET)
		public @ResponseBody Long getAllCount(
				@RequestParam(value = "localidad", required = false) Localidad  filterLocalidad, HttpServletRequest request) {
		    try {
				return localidadService.findAllCount(filterLocalidad != null ? filterLocalidad: new Localidad ());
			} catch (Exception e) {
				throw new ServiceUnavailableException("Count Service is not responding.");
			}
		}
			
			
	/**
	 * GENERIC OBJECT
	 */
		@RequestMapping(value="genericObject",method=RequestMethod.POST)
		public @ResponseBody List<RUPBean> patronPruebaEnvio(@RequestBody GenericObject genericObject) throws Exception{
			
			logger.log(Level.TRACE, "ENTIDADES:");
			Map<String, Object> entidades = genericObject.getEntidades();
			Set<String> keys = entidades.keySet();
			for (Iterator<?> iterator = keys.iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				logger.log(Level.TRACE, "key: "+key+" / value: "+ entidades.get(key));
			}
			
			logger.log(Level.TRACE, "EXTRA DATA:");
			logger.log(Level.TRACE, "label:"+genericObject.getData().get("label"));
			logger.log(Level.TRACE, "value:"+genericObject.getData().get("value"));
			logger.log(Level.TRACE, "style:"+genericObject.getData().get("style"));
			
			List<RUPBean> retorno = new ArrayList<RUPBean>();
			DepartamentoProvincia departamentoProvincia = (DepartamentoProvincia) genericObject.getEntidades().get("departamentoProvincia");
			for (DepartamentoProvincia element : departamentoProvinciaService.findAll(departamentoProvincia, null)) {
				retorno.add(new RUPBean(element.getCode().toString(), element.getDescEs(), ""));
			}
			return retorno;
		}
			
}
