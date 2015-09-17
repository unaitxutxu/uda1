package com.ejie.x21a.control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ejie.x21a.model.Comarca;
import com.ejie.x21a.model.Departamento;
import com.ejie.x21a.model.DepartamentoProvincia;
import com.ejie.x21a.model.Localidad;
import com.ejie.x21a.model.Provincia;
import com.ejie.x21a.model.Usuario;
import com.ejie.x21a.service.ComarcaService;
import com.ejie.x21a.service.DepartamentoProvinciaService;
import com.ejie.x21a.service.DepartamentoService;
import com.ejie.x21a.service.LocalidadService;
import com.ejie.x21a.service.ProvinciaService;
import com.ejie.x21a.service.UsuarioService;
import com.ejie.x38.control.exception.MethodFailureException;
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
@RequestMapping(value = "/patrones")
public class PatronesController {

	//private static final Logger logger = Logger.getLogger(PatronesController.class);

	@Autowired
	private Properties appConfiguration;

	//Autocomplete
	@RequestMapping(value = "autocomplete", method = RequestMethod.GET)
	public ModelAndView getAutocomplete(Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
		return new ModelAndView("autocomplete", "model", model);
	}
	
	//Date
	@RequestMapping(value = "date", method = RequestMethod.GET)
	public ModelAndView getDate(Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
		return new ModelAndView("date", "model", model);
	}
	
	//Dialog
	@RequestMapping(value = "dialog", method = RequestMethod.GET)
	public ModelAndView getDialog(Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
		return new ModelAndView("dialog", "model", model);
	}

	//Combos
	@RequestMapping(value = "comboSimple", method = RequestMethod.GET)
	public ModelAndView getComboSimple(Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
		return new ModelAndView("combo", "model", model);
	}
	//CombosEnlazado - simple
	@RequestMapping(value = "comboEnlazadoSimple", method = RequestMethod.GET)
	public ModelAndView getComboEnlazadoSimple(Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
		return new ModelAndView("comboEnlazado", "model", model);
	}
	//CombosEnlazado - multiple
	@RequestMapping(value = "comboEnlazadoMultiple", method = RequestMethod.GET)
	public ModelAndView getEnlazadoMultiple(Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
		return new ModelAndView("comboEnlazadoMultiple", "model", model);
	}

	//Feedback
	@RequestMapping(value = "feedback", method = RequestMethod.GET)
	public ModelAndView getFeedback(Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
		return new ModelAndView("feedback", "model", model);
	}
	
	//Grid
	@RequestMapping(value = "grid", method = RequestMethod.GET)
	public ModelAndView getGrid(Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
		return new ModelAndView("grid", "model", model);
	}
	
	//Menu
	@RequestMapping(value = "menu", method = RequestMethod.GET)
	public ModelAndView getMenu(Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
		return new ModelAndView("menu", "model", model);
	}
	//Menu Vertical
	@RequestMapping(value = "menuVertical", method = RequestMethod.GET)
	public ModelAndView getMenuVertical(Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", "vertical");
		return new ModelAndView("menuVertical", "model", model);
	}
	//Menu Mixto
	@RequestMapping(value = "menuMixto", method = RequestMethod.GET)
	public ModelAndView getMenuMixto (Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", "mixto");
		return new ModelAndView("menuMixto", "model", model);
	}
	
	//Message
	@RequestMapping(value = "message", method = RequestMethod.GET)
	public ModelAndView getMessage(Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
		return new ModelAndView("message", "model", model);
	}
	
	//Tabs
	@RequestMapping(value = "tabs", method = RequestMethod.GET)
	public ModelAndView getTabs(Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
		return new ModelAndView("tabs", "model", model);
	}
	
	//Time
	@RequestMapping(value = "time", method = RequestMethod.GET)
	public ModelAndView getTime(Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
		return new ModelAndView("time", "model", model);
	}
	
	//Toolbar
	@RequestMapping(value = "toolbar", method = RequestMethod.GET)
	public ModelAndView getToolbar(Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
		return new ModelAndView("toolbar", "model", model);
	}
	
	//Tooltip
	@RequestMapping(value = "tooltip", method = RequestMethod.GET)
	public ModelAndView getTooltip(Model model) {
		model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
		model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
		return new ModelAndView("tooltip", "model", model);
	}
	
	/**
	 * SERVICIOS NECESARIOS:
	 * 		- Usuario 
	 * 		- Provincia
	 * 		- Comarca
	 * 		- Localidad
	 * 		- Departamento
	 * 		- DepartamentoProvincia
	 */
		@Autowired 
		private UsuarioService usuarioService;
		
		@Autowired 
		private ProvinciaService provinciaService;
		
		@Autowired 
		private ComarcaService comarcaService;
		
		@Autowired 
		private LocalidadService localidadService;
		
		@Autowired 
		private DepartamentoService departamentoService;
		
		@Autowired 
		private DepartamentoProvinciaService departamentoProvinciaService;
	
		
	/**
	 * AUTOCOMPLETE REMOTO
	 */
		@RequestMapping(value = "autocomplete/remote", method=RequestMethod.GET)
		public @ResponseBody List<DepartamentoProvincia> getRemoteAutocomplete(
				@RequestParam(value = "q", required = true) String q,
				@RequestParam(value = "c", required = true) Boolean c){
			try{
				//Filtro
				DepartamentoProvincia departamentoProvincia = new DepartamentoProvincia();
	
				//Idioma
				Locale locale = LocaleContextHolder.getLocale();
				if (com.ejie.x38.util.Constants.EUSKARA.equals(locale.getLanguage())){
					departamentoProvincia.setDescEu(q);
				}else{
					departamentoProvincia.setDescEs(q);
				}
				
				try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
				return departamentoProvinciaService.findAllLike(departamentoProvincia, null, !c);
			} catch (Exception ex){
				throw new MethodFailureException("Method getRemoteAutocomplete failed");
			}
		}
		
		
	/**
	 * COMBO SIMPLE	
	 */
		@RequestMapping(value = "comboSimple/remote", method=RequestMethod.GET)
		public @ResponseBody List<Provincia> getComboRemote(){
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			return provinciaService.findAll(null, null);
		}
		@RequestMapping(value = "comboSimple/remoteGroup", method=RequestMethod.GET)
		public @ResponseBody List<HashMap<String, List<?>>> getRemoteComboGrupos(){
			
			//Idioma
			Locale locale = LocaleContextHolder.getLocale();
			
			//Retorno del método
			List<HashMap<String, List<?>>> retorno = new ArrayList<HashMap<String, List<?>>>();
			
			//Nombres de los grupos según idioma
		   	String provincia = null, comarca = null, localidad = null;
		   	if (com.ejie.x38.util.Constants.EUSKARA.equals(locale.getLanguage())){
				provincia = "Provincia_eu";
				comarca = "Comarca_eu";
				localidad = "Localidad_eu";
			} else { 
				provincia = "Provincia";
				comarca = "Comarca";
				localidad = "Localidad";
			}
			
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			
			//Provincia
			HashMap<String, List<?>> group = new HashMap<String, List<?>>();
			group.put(provincia, provinciaService.findAll(null, null));
			retorno.add(group);
			
			//Comarca
			group = new HashMap<String, List<?>>();
			group.put(comarca, comarcaService.findAll(null, null));
			retorno.add(group);
			
			//Localidad
			group = new HashMap<String, List<?>>();
			group.put(localidad, localidadService.findAll(null, null));
			retorno.add(group);
			
			return retorno;
		}
		
		
	/**
	 * COMBO ENLAZADO SIMPLE	
	 */	
		@RequestMapping(value = "comboEnlazadoSimple/remoteEnlazadoProvincia", method=RequestMethod.GET)
		public @ResponseBody List<Provincia> getEnlazadoProvincia() {
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			return provinciaService.findAll(null, null);
		}
		
		@RequestMapping(value = "comboEnlazadoSimple/remoteEnlazadoComarca", method=RequestMethod.GET)
		public @ResponseBody List<Comarca> getEnlazadoComarca(
				@RequestParam(value = "provincia", required = false) BigDecimal provincia_code) {
			
			//Convertir parámetros en entidad para búsqueda
			Provincia provincia = new Provincia();
			provincia.setCode(provincia_code);
			Comarca comarca = new Comarca();
			comarca.setProvincia(provincia);
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			return comarcaService.findAll(comarca, null);
		}
		
		@RequestMapping(value = "comboEnlazadoSimple/remoteEnlazadoLocalidad", method=RequestMethod.GET)
		public @ResponseBody List<Localidad> getEnlazadoLocalidad(
				@RequestParam(value = "comarca", required = false) BigDecimal comarca_code) {
			
			//Convertir parámetros en entidad para búsqueda
			Comarca comarca = new Comarca();
			comarca.setCode(comarca_code);
			Localidad localidad = new Localidad();
			localidad.setComarca(comarca);
			
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			return localidadService.findAll(localidad, null);
		}
		
		
	/**
	 * COMBO ENLAZADO MULTIPLE	
	 */			
		/**
		 * Combos Enlazados (múltiple)
		 */
		@RequestMapping(value = "comboEnlazadoMultiple/departamentoRemote", method=RequestMethod.GET)
		public @ResponseBody List<Departamento> getEnlMultDpto() {
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			return departamentoService.findAll(null, null);
		}
		
		@RequestMapping(value = "comboEnlazadoMultiple/provinciaRemote", method=RequestMethod.GET)
		public @ResponseBody List<Provincia> getEnlMultProv() {
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			return provinciaService.findAll(null, null);
		}
		
		@RequestMapping(value = "comboEnlazadoMultiple/dptoProvRemote", method=RequestMethod.GET)
		public @ResponseBody List<DepartamentoProvincia> getEnlMultDptoProv(
				@RequestParam(value = "departamento", required = false) BigDecimal departamento_code,
				@RequestParam(value = "provincia", required = false) BigDecimal provincia_code) {
			
			//Convertir parámetros en entidad para búsqueda
			Departamento departamento = new Departamento();
			departamento.setCode(departamento_code);
			Provincia provincia = new Provincia();
			provincia.setCode(provincia_code);
			DepartamentoProvincia departamentoProvincia = new DepartamentoProvincia();
			departamentoProvincia.setDepartamento(departamento);
			departamentoProvincia.setProvincia(provincia);
			
			try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
			return departamentoProvinciaService.findAll(departamentoProvincia, null);
		}
		
		
	/**
	 * TABS -> Contenidos
	 */
		@RequestMapping(value = { "fragmento1", "fragmento2", "fragmento3" }, method = RequestMethod.GET)
		public ModelAndView tabsContent(Model model) {
			model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
			model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
			return new ModelAndView("tabsContent_1", "model", model);
		}
		
		@RequestMapping(value = { "tab2Fragment" }, method = RequestMethod.GET)
		public ModelAndView tabs2Content(Model model) {
			model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
			model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
			return new ModelAndView("tabsContent_2", "model", model);
		}
		
		@RequestMapping(value = { "tab3Fragment" }, method = RequestMethod.GET)
		public ModelAndView tabs3Content(Model model) {
			model.addAttribute("defaultLanguage", appConfiguration.get("x21aPilotoPatronesWar.default.language"));
			model.addAttribute("defaultLayout", appConfiguration.get("x21aPilotoPatronesWar.default.layout"));
			return new ModelAndView("tabsContent_3", "model", model);
		}
	
	/**
	 * GRID (Usuarios)
	 */
		@RequestMapping(value = "usuarios", method = RequestMethod.GET)
		public @ResponseBody Object getAll(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "apellido1", required = false) String apellido1,
			@RequestParam(value = "apellido2", required = false) String apellido2,
			@RequestParam(value = "ejie", required = false) String ejie,
			@RequestParam(value = "fechaAlta", required = false) Date fechaAlta,
			@RequestParam(value = "fechaBaja", required = false) Date fechaBaja,
			HttpServletRequest request) {
				try{
					Usuario filterUsuario = new Usuario(id, nombre, apellido1, apellido2, ejie, fechaAlta, fechaBaja);
	                Pagination pagination = null;
				    if (request.getHeader("JQGridModel") != null &&  request.getHeader("JQGridModel").equals("true")) {
					    pagination = new Pagination();
					    pagination.setPage(Long.valueOf(request.getParameter("page")));
					    pagination.setRows(Long.valueOf(request.getParameter("rows")));
					    pagination.setSort(request.getParameter("sidx"));
					    pagination.setAscDsc(request.getParameter("sord"));
	                    List<Usuario> usuarios =  this.usuarioService.findAll(filterUsuario, pagination);
	
	     			    if (usuarios == null) {
		    	            throw new Exception("No data Found.");
			            }
						
				        Long total =  getAllCount(filterUsuario, request);
					    JQGridJSONModel data = new JQGridJSONModel();
					    data.setPage(request.getParameter("page"));
					    data.setRecords(total.intValue());
					    data.setTotal(total, pagination.getRows());
					    data.setRows(usuarios);
					    return data;
					}else{
					    List<Usuario> usuarios =  this.usuarioService.findAll(filterUsuario, pagination);
						if (usuarios == null) {
		    	            throw new Exception("No data Found.");
			            }
					    return usuarios;
					}
	            }catch(Exception e){
				    throw new ResourceNotFoundException("No data Found.");
				}
		}
		@RequestMapping(value = "usuarios/count", method = RequestMethod.GET)
		public @ResponseBody Long getAllCount(
				@RequestParam(value = "usuario", required = false) Usuario  filterUsuario, HttpServletRequest request) {
		    try {
				return usuarioService.findAllCount(filterUsuario != null ? filterUsuario: new Usuario ());
			} catch (Exception e) {
				throw new ServiceUnavailableException("Count Service is not responding.");
			}
		}
}
