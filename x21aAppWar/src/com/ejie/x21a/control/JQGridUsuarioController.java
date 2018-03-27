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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.ejie.x21a.model.Usuario;
import com.ejie.x21a.service.JQGridUsuarioService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.JQGridRequestDto;
import com.ejie.x38.dto.JQGridResponseDto;
import com.ejie.x38.dto.JerarquiaDto;
import com.ejie.x38.dto.TableRowDto;
import com.ejie.x38.reports.ReportData;
import com.ejie.x38.rup.jqgrid.filter.model.Filter;
import com.ejie.x38.rup.jqgrid.filter.service.FilterService;
import com.ejie.x38.util.DateTimeManager;
/**
 * UsuarioServiceImpl generated by UDA 1.0, 26-may-2011 13:46:35.
 * 
 * @author UDA
 */
@Controller
@RequestMapping (value = "/jqGridUsuario")
public class JQGridUsuarioController  {

	private static final Logger logger = LoggerFactory.getLogger(JQGridUsuarioController.class);

	@Autowired
	private JQGridUsuarioService jqGridUsuarioService; 
	
	@Autowired
	private FilterService filterService;
	
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,new ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor(DateTimeManager.getDateTimeFormat(LocaleContextHolder.getLocale()), true));
		NumberFormat numberFormat = NumberFormat.getInstance(LocaleContextHolder.getLocale());
		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, numberFormat, true));
	}

	/*
	 * OPERACIONES CRUD
	 * 
	 * Metodos correspondientes a las operaciones CRUD (Create, Read, Update, Delete). 
	 * 
	 */
	
	/**
	 * Operación CRUD Read. Devuelve el bean correspondiente al identificador
	 * indicado.
	 * 
	 * @param id
	 *            Identificador del objeto que se desea recuperar.
	 * @return Objeto correspondiente al identificador indicado.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Usuario get(@PathVariable String id) {
        Usuario usuario = new Usuario();
		usuario.setId(id);
        usuario = this.jqGridUsuarioService.find(usuario);
        
        return usuario;
	}
	
	/**
	 * Devuelve una lista de beans correspondientes a los valores de filtrados
	 * indicados en el objeto pasado como parámetro.
	 * 
	 * @param Usuario
	 *            Objeto que contiene los parámetros de filtrado utilizados en
	 *            la búsqueda.
	 * @return Lista de objetos correspondientes a la búsqueda realizada.
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Usuario> getAll(@ModelAttribute() Usuario usuarioFilter){
		JQGridUsuarioController.logger.info("[GET - find_ALL] : Obtener Usuarios por filtro");
		return this.jqGridUsuarioService.findAllLike(usuarioFilter, null, false);
	}
	
	/**
	 * Operación CRUD Edit. Modificación del bean indicado.
	 * 
	 * @param Usuario
	 *            Bean que contiene la información a modificar.
	 * @return Bean resultante de la modificación.
	 */
	@RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody Usuario edit(@Validated @RequestBody Usuario usuario) {
		if (usuario.getEjie()==null){
			usuario.setEjie("0");
		}
        Usuario usuarioAux = this.jqGridUsuarioService.update(usuario);
		logger.info("Entity correctly updated!");
        return usuarioAux;
    }

	/**
	 * Operación CRUD Create. Creación de un nuevo registro a partir del bean
	 * indicado.
	 * 
	 * @param Usuario
	 *            Bean que contiene la información con la que se va a crear el
	 *            nuevo registro.
	 * @return Bean resultante del proceso de creación.
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Usuario add(@Validated @RequestBody Usuario usuario) {		
		if (usuario.getEjie()==null){
			usuario.setEjie("0");
		}
        Usuario usuarioAux = this.jqGridUsuarioService.add(usuario);
        logger.info("Entity correctly inserted!");
    	return usuarioAux;
	}
	
	/**
	 * Operación CRUD Delete. Borrado del registro correspondiente al
	 * identificador especificado.
	 * 
	 * @param id
	 *            Identificador del objeto que se desea eliminar.
	 * @return Bean eliminado.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.OK)
    public @ResponseBody Usuario remove(@PathVariable(value="id") String id, HttpServletResponse  response) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        this.jqGridUsuarioService.remove(usuario);
        logger.info("Entity correctly deleted!");
        return usuario;
    }
	
	
	/*
	 * METODOS COMPONENTE RUP_TABLE
	 * 
	 */
	
	/**
	 * Operación de filtrado del componente RUP_TABLE.
	 * 
	 * @param Usuario
	 *            Bean que contiene los parámetros de filtrado a emplear.
	 * @param JQGridRequestDto
	 *            Dto que contiene los parámtros de configuración propios del
	 *            RUP_TABLE a aplicar en el filtrado.
	 * @return Dto que contiene el resultado del filtrado realizado por el
	 *         componente RUP_TABLE.
	 * 
	 */
	//@Json(mixins={@JsonMixin(target=Usuario.class, mixin=UsuarioMixIn.class)})
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<Usuario> filter(
			@RequestJsonBody(param="filter") Usuario filterUsuario,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		JQGridUsuarioController.logger.info("[POST - jqGrid] : Obtener Usuarios");
		return jqGridUsuarioService.filter(filterUsuario, jqGridRequestDto, false);
	}
	
	
	@RequestMapping(value = "/multiFilter/add", method = RequestMethod.POST)
	public @ResponseBody Filter filterAdd(@RequestJsonBody(param="filtro") Filter filtro){
		JQGridUsuarioController.logger.info("[POST - jqGrid] : add filter");
		
		 return filterService.insert(filtro);
	}	
	

	
	@RequestMapping(value = "/multiFilter/delete", method = RequestMethod.POST)
	public @ResponseBody Filter  filterDelete(
			@RequestJsonBody(param="filtro") Filter filtro) {
		JQGridUsuarioController.logger.info("[POST - jqGrid] : delete filter");
		return  filterService.delete(filtro);
	}
	
	
	@RequestMapping(value = "/multiFilter/getDefault", method = RequestMethod.GET)
	public @ResponseBody Filter filterGetDefault(
		@RequestParam(value = "filterSelector", required = true) String filterSelector,
		@RequestParam(value = "user", required = true) String filterUser) {
		JQGridUsuarioController.logger.info("[get - jqGrid] : getDefault filter");
		 return filterService.getDefault(filterSelector, filterUser);
	}
	
	
	
	
	@RequestMapping(value = "/multiFilter/getAll", method = RequestMethod.GET)
	public @ResponseBody List<Filter> filterGetAll(
		@RequestParam(value = "filterSelector", required = true) String filterSelector,
		@RequestParam(value = "user", required = true) String filterUser) {
		JQGridUsuarioController.logger.info("[get - jqGrid] : GetAll filter");
		 return filterService.getAllFilters(filterSelector,filterUser);
	}
	
	/**
	 * Operación de búsqueda del componente RUP_TABLE.
	 * 
	 * @param filterUsuario
	 *            Bean que contiene los parámetros de filtrado a emplear.
	 * @param searchUsuario
	 *            Bean que contiene los parámetros de búsqueda a emplear.
	 * @param JQGridRequestDto
	 *            Dto que contiene los parámtros de configuración propios del
	 *            RUP_TABLE a aplicar en la búsqueda.
	 * @return Lista de lineas de la tabla que se corresponden con los registros
	 *         que se ajustan a los parámetros de búsqueda.
	 * 
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody List<TableRowDto<Usuario>> search(
			@RequestJsonBody(param="filter") Usuario filterUsuario,
			@RequestJsonBody(param="search") Usuario searchUsuario,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto){
		JQGridUsuarioController.logger.info("[POST - search] : Buscar Usuarios");
		return jqGridUsuarioService.search(filterUsuario, searchUsuario, jqGridRequestDto, false);
	}
	
	/**
	 * Borrado múltiple de registros
	 * 
	 * @param filterUsuario
	 *            Bean que contiene los parámetros de filtrado a emplear.
	 * @param JQGridRequestDto
	 *            Dto que contiene los parámtros de configuración propios del
	 *            RUP_TABLE a aplicar en la búsqueda.
	 * @return Lista de los identificadores de los registros eliminados.
	 */
	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	@ResponseStatus(value=HttpStatus.OK)
	public @ResponseBody List<String> removeMultiple(
			@RequestJsonBody(param="filter") Usuario filterUsuario,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		JQGridUsuarioController.logger.info("[POST - removeMultiple] : Eliminar multiples usuarios");
	    this.jqGridUsuarioService.removeMultiple(filterUsuario, jqGridRequestDto, false);
	    JQGridUsuarioController.logger.info("All entities correctly deleted!");
	    
	    return jqGridRequestDto.getMultiselection().getSelectedIds();
	}	
	
	/*
	 * METODOS COMPONENTE RUP_TABLE - JERARQUÍA
	 */
	
	/**
	 * Operación de filtrado del componente RUP_TABLE para presentar los
	 * registros medainte una visualización jerárquica.
	 * 
	 * @param Usuario
	 *            Bean que contiene los parámetros de filtrado a emplear.
	 * @param JQGridRequestDto
	 *            Dto que contiene los parámtros de configuración propios del
	 *            RUP_TABLE a aplicar en el filtrado.
	 * @return Dto que contiene el resultado del filtrado realizado por el
	 *         componente RUP_TABLE.
	 * 
	 */
	@RequestMapping(value = "/jerarquia/filter", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto< JerarquiaDto< Usuario>> jerarquia(
			@RequestJsonBody(param="filter") Usuario filterUsuario,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto) {
		JQGridUsuarioController.logger.info("[POST - jerarquia] : Obtener Usuarios Jerarquia");
		return this.jqGridUsuarioService.jerarquia(filterUsuario, jqGridRequestDto, false);
	}
	
	/**
	 * Recupera los hijos de los registros desplegados en la visualización jerárquica.
	 * 
	 * @param Usuario
	 *            Bean que contiene los parámetros de filtrado a emplear.
	 * @param JQGridRequestDto
	 *            Dto que contiene los parámtros de configuración propios del
	 *            RUP_TABLE a aplicar en el filtrado.
	 * @return Dto que contiene el resultado a mostrar en el componente RUP_TABLE.
	 * 
	 */
	@RequestMapping(value = "/jerarquiaChildren", method = RequestMethod.POST)
	public @ResponseBody JQGridResponseDto<JerarquiaDto<Usuario>> jerarquiaChildren (
			@RequestJsonBody(param="filter") Usuario filterUsuario,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto){
		JQGridUsuarioController.logger.info("[GET - jqGrid] : Obtener Jerarquia - Hijos");
		return this.jqGridUsuarioService.jerarquiaChildren(filterUsuario, jqGridRequestDto);
	}
	
	
	/**
	 * EXPORTERS
	 */
	@RequestMapping(value = "/clipboardReport", method = RequestMethod.POST)
	protected @ResponseBody List<Usuario> getClipboardReport(
			@RequestJsonBody(param="filter") Usuario filterUsuario,
			@RequestJsonBody JQGridRequestDto jqGridRequestDto){
		JQGridUsuarioController.logger.info("[POST - clipboardReport] : Copiar multiples usuarios");
	    JQGridUsuarioController.logger.info("All entities correctly copied!");
	    return this.jqGridUsuarioService.getMultiple(filterUsuario, jqGridRequestDto, false);
	}
	
	@RequestMapping(value = "csvReport", method = RequestMethod.POST)
	protected ModelAndView getCSVReport(@ModelAttribute Usuario filterUsuario, @ModelAttribute JQGridRequestDto jqGridRequestDto,
			ModelMap modelMap,
			@RequestParam(value = "columns", required = false) String columns){
		
		//Acceso a BD para recuperar datos
		jqGridRequestDto.setPage(null);
		jqGridRequestDto.setRows(null);
		List<Usuario> filter = this.jqGridUsuarioService.findAllLike(filterUsuario, jqGridRequestDto, false);
		 
		//Nombre fichero
		modelMap.put("fileName", "datosCSV");
			
		//Datos
		ReportData<Usuario> reportData = new ReportData<Usuario>();
			//cabeceras hoja
			reportData.setHeaderNames(ReportData.parseColumns(columns));
			//datos hoja
			reportData.setModelData(filter);
		modelMap.put("reportData", reportData);
		
		//Generación del CVS
		return new ModelAndView("csvReport", modelMap);
	}
	
	
	@RequestMapping(value = {"xlsReport" , "xlsxReport"}, method = RequestMethod.POST)
	protected ModelAndView getExcelPOI(@ModelAttribute Usuario filterUsuario, @ModelAttribute JQGridRequestDto jqGridRequestDto,
			ModelMap modelMap,
			@RequestParam(value = "columns", required = false) String columns,
			HttpServletRequest request){
		
		//Acceso a BD para recuperar datos
		List<Usuario> listUsuarioPage = this.jqGridUsuarioService.findAllLike(filterUsuario, jqGridRequestDto, false);
		jqGridRequestDto.setPage(null);
		jqGridRequestDto.setRows(null);
		List<Usuario> listUsuarioAll = this.jqGridUsuarioService.findAllLike(filterUsuario, jqGridRequestDto, false);
		
		//Nombre fichero
		modelMap.put("fileName", "datosExcel");
		
		//Datos
		List<Object> reportData = new ArrayList<Object>();
			//Hoja 1
			ReportData<Usuario> usuarioExcelDataAll = new ReportData<Usuario>();
				//nombre hoja
				usuarioExcelDataAll.setSheetName("Todos los usuarios");
				//cabeceras hoja
				usuarioExcelDataAll.setHeaderNames(ReportData.parseColumns(columns));
				//datos hoja
				usuarioExcelDataAll.setModelData(listUsuarioAll);
			reportData.add(usuarioExcelDataAll);
			//Hoja 2
			ReportData<Usuario> usuarioExcelDataPage = new ReportData<Usuario>();
				//nombre hoja
				usuarioExcelDataPage.setSheetName("Página 1 de usuarios");
				//cabeceras hoja
				usuarioExcelDataPage.setHeaderNames(ReportData.parseColumns(columns));
				//datos hoja
				usuarioExcelDataPage.setModelData(listUsuarioPage);
			reportData.add(usuarioExcelDataPage);
		modelMap.put("reportData", reportData);
		
		//Generación del XLS o XLSX
//		String reportView = request.getServletPath().substring(9);
		if (request.getServletPath().indexOf("xlsReport")!=-1){
			return new ModelAndView("xlsReport", modelMap);
		}else{
			return new ModelAndView("xlsxReport", modelMap);
		}
//		return new ModelAndView(reportView, modelMap);
		
	}
	
	
	@RequestMapping(value = "odsReport", method = RequestMethod.POST)
	protected ModelAndView getODSReport(@ModelAttribute Usuario filterUsuario, @ModelAttribute JQGridRequestDto jqGridRequestDto,
			ModelMap modelMap,
			@RequestParam(value = "columns", required = false) String columns){
		
		//Acceso a BD para recuperar datos
		List<Usuario> listUsuarioPage = this.jqGridUsuarioService.findAllLike(filterUsuario, jqGridRequestDto, false);
		jqGridRequestDto.setPage(null);
		jqGridRequestDto.setRows(null);
		List<Usuario> listUsuarioAll = this.jqGridUsuarioService.findAllLike(filterUsuario, jqGridRequestDto, false);
		
		//Nombre fichero
		modelMap.put("fileName", "datosODS");
		
		//Datos
		List<Object> reportData = new ArrayList<Object>();
			//Hoja 1
			ReportData<Usuario> usuarioExcelDataAll = new ReportData<Usuario>();
				//nombre hoja
				usuarioExcelDataAll.setSheetName("Todos los usuarios");
				//cabeceras hoja
				usuarioExcelDataAll.setHeaderNames(ReportData.parseColumns(columns));
				//datos hoja
				usuarioExcelDataAll.setModelData(listUsuarioAll);
			reportData.add(usuarioExcelDataAll);
			//Hoja 2
			ReportData<Usuario> usuarioExcelDataPage = new ReportData<Usuario>();
				//nombre hoja
				usuarioExcelDataPage.setSheetName("Página 1 de usuarios");
				//cabeceras hoja
				usuarioExcelDataPage.setHeaderNames(ReportData.parseColumns(columns));
				//datos hoja
				usuarioExcelDataPage.setModelData(listUsuarioPage);
			reportData.add(usuarioExcelDataPage);
		modelMap.put("reportData", reportData);
		
		//Generación del ODS
		return new ModelAndView("odsReport", modelMap);
	}
	
	@RequestMapping(value="/pdfReport")
	public ModelAndView generarPDFJasperReport(@ModelAttribute Usuario filterUsuario, @ModelAttribute JQGridRequestDto jqGridRequestDto,
			ModelMap modelMap,
			@RequestParam(value = "isInline", required = false) boolean isInline){
		
		//Acceso a BD para recuperar datos
		List<Usuario> usuarios = this.jqGridUsuarioService.findAll(new Usuario(), null);
		
		//Nombre fichero
		modelMap.put("fileName", "datosPDF");
		
		//En línea (no descarga fichero) ?
		modelMap.put("isInline", isInline);
		
		//Titulo y cabeceras (parameter)
		modelMap.put("TITULO", "Listado usuarios");
		modelMap.put("COL_NOMBRE", "Nombre");
		modelMap.put("COL_APE1", "Apellido 1");
		modelMap.put("COL_APE2", "Apellido 2");
		
		//Datos (field)
		modelMap.put("usuarios", usuarios);
		
		//Generación del PDF
		return new ModelAndView("pdfUsuario", modelMap);
    }

	/*
	 * DETALLE USUARIO RSS
	 */
	@RequestMapping(value = "/rssDetail/{id}", method = RequestMethod.GET)
	public String getRssDetail(@PathVariable String id, Model model) {
		JQGridUsuarioController.logger.info("[GET - getRssDEtail] : Obtener el detalle del usuario a partir del RSS");
        Usuario usuario = new Usuario();
		usuario.setId(id);
        usuario = this.jqGridUsuarioService.find(usuario);
		
		model.addAttribute("usuario",usuario);
		
		return "tableRssDetail";
	}

	
	
}