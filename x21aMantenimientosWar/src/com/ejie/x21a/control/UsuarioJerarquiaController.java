package com.ejie.x21a.control;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.ejie.x21a.model.Usuario;
import com.ejie.x21a.model.UsuarioJerarquia;
import com.ejie.x21a.service.UsuarioJerarquiaService;
import com.ejie.x38.dto.JQGridJSONModel;
import com.ejie.x38.dto.JerarquiaDto;
import com.ejie.x38.dto.Pagination;
import com.ejie.x38.reports.JerarquiaMetadata;
import com.ejie.x38.reports.ReportData;
import com.ejie.x38.util.ObjectConversionManager;

/**
 * UsuarioJerarquiaController generated by UDA, 03-oct-2012 10:36:43.
 * @author UDA
 */
 
@Controller
@RequestMapping (value = "/usuariojerarquia")

public class UsuarioJerarquiaController  {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioJerarquiaController.class);

	@Resource
	private ReloadableResourceBundleMessageSource messageSource;
	
	@Autowired
	private UsuarioJerarquiaService usuarioJerarquiaService;
	
	/**
	 * Method 'getCreateForm'.
	 *
	 * @param model Model
	 * @return String
	 */
	@RequestMapping(value = "maint", method = RequestMethod.GET)
	public String getCreateForm(Model model) {
		UsuarioJerarquiaController.logger.info("[GET - View] : usuariojerarquia");
		model.addAttribute("tituloPagina",
				messageSource.getMessage("jerarquiaTitle", null, LocaleContextHolder.getLocale()) + " - " +
				messageSource.getMessage("jerarquia", null, LocaleContextHolder.getLocale()));
		return "usuariojerarquia";
	}
	
	/**
	 * Method 'getCreateFormGroup'.
	 *
	 * @param model Model
	 * @return String
	 */
	@RequestMapping(value = "maintgroup", method = RequestMethod.GET)
	public String getCreateFormGroup(Model model) {
		UsuarioJerarquiaController.logger.info("[GET - View] : usuariojerarquiagroup");
		model.addAttribute("tituloPagina",
				messageSource.getMessage("jerarquiaTitle", null, LocaleContextHolder.getLocale()) + " - " +
				messageSource.getMessage("jerarquiaGroup", null, LocaleContextHolder.getLocale()));
		return "usuariojerarquiagroup";
	}
	
	/**
	 * Method 'getCreateFormMulti'.
	 *
	 * @param model Model
	 * @return String
	 */
	@RequestMapping(value = "maintmulti", method = RequestMethod.GET)
	public String getCreateFormMulti(Model model) {
		UsuarioJerarquiaController.logger.info("[GET - View] : usuariojerarquiamulti");
		model.addAttribute("tituloPagina",
				messageSource.getMessage("jerarquiaTitle", null, LocaleContextHolder.getLocale()) + " - " +
				messageSource.getMessage("jerarquiaMulti", null, LocaleContextHolder.getLocale()));
		return "usuariojerarquiamulti";
	}
	
	/**
	 * Method 'getCreateFormMultiGroup'.
	 *
	 * @param model Model
	 * @return String
	 */
	@RequestMapping(value = "maintmultigroup", method = RequestMethod.GET)
	public String getCreateFormMultiGroup(Model model) {
		UsuarioJerarquiaController.logger.info("[GET - View] : usuariojerarquiamultigroup");
		model.addAttribute("tituloPagina",
				messageSource.getMessage("jerarquiaTitle", null, LocaleContextHolder.getLocale()) + " - " +
				messageSource.getMessage("jerarquiaGroupMulti", null, LocaleContextHolder.getLocale()));
		return "usuariojerarquiamultigroup";
	}
	
	/**
	 * Method 'getCreateFormCol'.
	 *
	 * @param model Model
	 * @return String
	 */
	@RequestMapping(value = "maintcol", method = RequestMethod.GET)
	public String getCreateFormCol(Model model) {
		UsuarioJerarquiaController.logger.info("[GET - View] : usuariojerarquiacol");
		model.addAttribute("tituloPagina",
				messageSource.getMessage("jerarquiaTitle", null, LocaleContextHolder.getLocale()) + " - " +
				messageSource.getMessage("jerarquiaCol", null, LocaleContextHolder.getLocale()));
		return "usuariojerarquiacol";
	}
	
	/**
	 * Method 'getById'.
	 *
	 * @param id String
	 * @return usuarioJerarquia UsuarioJerarquia
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody UsuarioJerarquia getById(@PathVariable String id) {
        UsuarioJerarquia usuarioJerarquia = new UsuarioJerarquia();
		usuarioJerarquia.setId(id);
        usuarioJerarquia = this.usuarioJerarquiaService.find(usuarioJerarquia);
        UsuarioJerarquiaController.logger.info("[GET - findBy_PK] : Obtener UsuarioJerarquia por PK");
        return usuarioJerarquia;
	}

	/**
	 * Method 'getAll'.
	 *
	 * @param filterUsuarioJerarquia UsuarioJerarquia
	 * @return List
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<UsuarioJerarquia> getAll(@ModelAttribute UsuarioJerarquia filterUsuarioJerarquia) {
		UsuarioJerarquiaController.logger.info("[GET - find_ALL] : Obtener UsuarioJerarquia por filtro");
	    return this.usuarioJerarquiaService.findAll(filterUsuarioJerarquia, null);
	}

	/**
	 * Method 'edit'.
	 *
	 * @param usuarioJerarquia UsuarioJerarquia 
	 * @return UsuarioJerarquia
	 */
	@RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody UsuarioJerarquia edit(@RequestBody UsuarioJerarquia usuarioJerarquia) {		
        UsuarioJerarquia usuarioJerarquiaAux = this.usuarioJerarquiaService.update(usuarioJerarquia);
		UsuarioJerarquiaController.logger.info("[PUT] : UsuarioJerarquia actualizado correctamente");
        return usuarioJerarquiaAux;
    }

	/**
	 * Method 'add'.
	 *
	 * @param usuarioJerarquia UsuarioJerarquia 
	 * @return UsuarioJerarquia
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody UsuarioJerarquia add(@RequestBody UsuarioJerarquia usuarioJerarquia) {		
        UsuarioJerarquia usuarioJerarquiaAux = this.usuarioJerarquiaService.add(usuarioJerarquia);
        UsuarioJerarquiaController.logger.info("[POST] : UsuarioJerarquia insertado correctamente");
    	return usuarioJerarquiaAux;
	}

	/**
	 * Method 'remove'.
	 *
	 * @param id String
	 * @return usuarioJerarquia
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody UsuarioJerarquia remove(@PathVariable String id) {
        UsuarioJerarquia usuarioJerarquia = new UsuarioJerarquia();
        usuarioJerarquia.setId(id);
        this.usuarioJerarquiaService.remove(usuarioJerarquia);
       	UsuarioJerarquiaController.logger.info("[DELETE] : UsuarioJerarquia borrado correctamente");
       	return usuarioJerarquia;
    }
	
	/**
	 * Method 'removeAll'.
	 *
	 * @param usuarioJerarquiaIds List
	 * @return usuarioJerarquiaList
	 */	
	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<List<String>> removeMultiple(@RequestBody List<List<String>> usuarioJerarquiaIds) {
        List<UsuarioJerarquia> usuarioJerarquiaList = new ArrayList<UsuarioJerarquia>();
        for (List<String> usuarioJerarquiaId:usuarioJerarquiaIds) {
		    Iterator<String> iterator = usuarioJerarquiaId.iterator();
		    UsuarioJerarquia usuarioJerarquia = new UsuarioJerarquia(); //NOPMD - Objeto nuevo en la lista (parametro del servicio)
	        usuarioJerarquia.setId(ObjectConversionManager.convert(iterator.next(), String.class));
		    usuarioJerarquiaList.add(usuarioJerarquia);
	    }
        this.usuarioJerarquiaService.removeMultiple(usuarioJerarquiaList);
		UsuarioJerarquiaController.logger.info("[POST - DELETE_ALL] : UsuarioJerarquia borrados correctamente");
		return usuarioJerarquiaIds;
	}	

	/**
	 * Method 'getAllJQGrid'.
	 *
	 * @param filterUsuarioJerarquia UsuarioJerarquia
	 * @param pagination Pagination
	 * @return JQGridJSONModel
	 */
//	@RequestMapping(method = RequestMethod.GET, headers={"JQGridModel=true"})
//	public @ResponseBody JQGridJSONModel getAllJQGrid(@ModelAttribute UsuarioJerarquia filterUsuarioJerarquia, @ModelAttribute Pagination pagination) {
//        List<UsuarioJerarquia> usuarioJerarquias = this.usuarioJerarquiaService.findAll(filterUsuarioJerarquia, pagination);
//        Long recordNum = this.usuarioJerarquiaService.findAllCount(filterUsuarioJerarquia);
//        UsuarioJerarquiaController.logger.info("[GET - jqGrid] : Obtener UsuarioJerarquia");
//		return new JQGridJSONModel(pagination, recordNum, usuarioJerarquias);
//	}
	@RequestMapping(method = RequestMethod.GET, headers={"JQGridModel=true"})
	public @ResponseBody JQGridJSONModel getAllJQGrid(@ModelAttribute UsuarioJerarquia filterUsuarioJerarquia, @ModelAttribute Pagination pagination){
		List<JerarquiaDto<UsuarioJerarquia>> listUsuarioJerarquia = this.usuarioJerarquiaService.findAllLikeJerarquia(filterUsuarioJerarquia, pagination);
		Long recordNum = this.usuarioJerarquiaService.findAllLikeCountJerarquia(filterUsuarioJerarquia, pagination);
		UsuarioJerarquiaController.logger.info("[GET - jqGrid] : Obtener Jerarquia");
		return new JQGridJSONModel(pagination, recordNum, listUsuarioJerarquia);
	}
	
	@RequestMapping(method = RequestMethod.GET, headers={"JQGridModel_selected=true"})
	public @ResponseBody TreeMap<String, TreeMap<String, String>> getAllJQGridSelected (@ModelAttribute UsuarioJerarquia filterUsuarioJerarquia, @ModelAttribute Pagination pagination){
		return this.usuarioJerarquiaService.findAllLikeSelected(filterUsuarioJerarquia, pagination);
	}
	
	/**
	 * EXPORTERS
	 */
	@RequestMapping(value = "csvReport", method = RequestMethod.POST)
	protected ModelAndView getCSVReport(@ModelAttribute UsuarioJerarquia filterUsuarioJerarquia, @ModelAttribute Pagination pagination,
			ModelMap modelMap,
			@RequestParam(value = "columns", required = false) String columns){
		
		//Acceso a BD para recuperar datos
		pagination.setPage(null);
		pagination.setRows(null);
		List<JerarquiaDto<UsuarioJerarquia>> listUsuarioAll = this.usuarioJerarquiaService.findAllLikeJerarquia(filterUsuarioJerarquia, pagination);
		
		//Nombre fichero
		modelMap.put("fileName", "datosCSV");
			
		//Datos
		ReportData<UsuarioJerarquia> reportData = new ReportData<UsuarioJerarquia>();
			//cabeceras hoja
			reportData.setHeaderNames(ReportData.parseColumns(columns));
			//datos hoja
			reportData.setModelData(listUsuarioAll);
			
			//Metadatos de Jerarquia
			JerarquiaMetadata jmd = new JerarquiaMetadata();
				//Filtro
//				jmd.setShowFiltered(true);						//Mostrar (default)
//				jmd.setFilterToken("*");						//Token * (default)
				jmd.setFilterHeaderName("Filtrados"); 			//Cabecera filtrados
				
				//Tabulacion
				jmd.setShowTabbed(true); 						//Tabular filtrados
//				jmd.setTabToken("   ");							//Toleken '   ' (default)
				jmd.setTabColumnName("nombre");					//Campo a tabular
				
				//Iconos
				jmd.setShowIcon(true);							//Mostrar
//				jmd.setIconExpanded("[-]");						//Icono expandido (default)
//				jmd.setIconUnexpanded("[+]");					//Icono contraido (default)
//				jmd.setIconExpanded("[ ]");						//Icono sin hijos (default)
				jmd.setIconColumnName("nombre");				//Campo a tabular
				jmd.setIconBeanAtribute("id");					//Campo referencia
				jmd.setIconCollapsedList(pagination.getJerarquia().getTree()); //Listado de elementos colapsados
			reportData.setJerarquiaMetadada(jmd);
		modelMap.put("reportData", reportData);
		
		//Generación del CVS
		return new ModelAndView("csvReport", modelMap);
	}
	
	@RequestMapping(value = {"xlsReport" , "xlsxReport"}, method = RequestMethod.POST)
	protected ModelAndView getExcelPOI(@ModelAttribute UsuarioJerarquia filterUsuarioJerarquia, @ModelAttribute Pagination pagination,
			ModelMap modelMap,
			@RequestParam(value = "columns", required = false) String columns,
			HttpServletRequest request){
		
		//Acceso a BD para recuperar datos
		List<JerarquiaDto<UsuarioJerarquia>> listUsuarioPage = this.usuarioJerarquiaService.findAllLikeJerarquia(filterUsuarioJerarquia, pagination);
		pagination.setPage(null);
		pagination.setRows(null);
		List<JerarquiaDto<UsuarioJerarquia>> listUsuarioAll = this.usuarioJerarquiaService.findAllLikeJerarquia(filterUsuarioJerarquia, pagination);
		
		//Nombre fichero
		modelMap.put("fileName", "datosExcel");
		
		//Metadatos de Jerarquia
		JerarquiaMetadata jmd = new JerarquiaMetadata();
			//Filtro
//			jmd.setShowFiltered(true);						//Mostrar (default)
//			jmd.setFilterToken("*");						//Token * (default)
			jmd.setFilterHeaderName("Filtrados"); 			//Cabecera filtrados
			
			//Tabulacion
			jmd.setShowTabbed(true); 						//Tabular filtrados
//			jmd.setTabToken("   ");							//Toleken '   ' (default)
			jmd.setTabColumnName("nombre");					//Campo a tabular
			
			//Iconos
			jmd.setShowIcon(true);							//Mostrar
//			jmd.setIconExpanded("[-]");						//Icono expandido (default)
//			jmd.setIconUnexpanded("[+]");					//Icono contraido (default)
//			jmd.setIconExpanded("[ ]");						//Icono sin hijos (default)
			jmd.setIconColumnName("nombre");				//Campo a tabular
			jmd.setIconBeanAtribute("id");					//Campo referencia
			jmd.setIconCollapsedList(pagination.getJerarquia().getTree()); //Listado de elementos colapsados
		
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
				//jerarquia
				usuarioExcelDataAll.setJerarquiaMetadada(jmd);
			reportData.add(usuarioExcelDataAll);
				
			//Hoja 2
			ReportData<Usuario> usuarioExcelDataPage = new ReportData<Usuario>();
				//nombre hoja
				usuarioExcelDataPage.setSheetName("Página 1 de usuarios");
				//cabeceras hoja
				usuarioExcelDataPage.setHeaderNames(ReportData.parseColumns(columns));
				//datos hoja
				usuarioExcelDataPage.setModelData(listUsuarioPage);
				//jerarquia
				usuarioExcelDataPage.setJerarquiaMetadada(jmd);
			reportData.add(usuarioExcelDataPage);
		modelMap.put("reportData", reportData);
		
		//Generación del XLS o XLSX
		String reportView = request.getServletPath().substring(18);
		return new ModelAndView(reportView, modelMap);
		
//			return new ModelAndView("xlsReport", modelMap);
//			return new ModelAndView("xlsxReport", modelMap);
	}
	
	@RequestMapping(value = "odsReport", method = RequestMethod.POST)
	protected ModelAndView getODSReport(@ModelAttribute UsuarioJerarquia filterUsuarioJerarquia, @ModelAttribute Pagination pagination,
			ModelMap modelMap,
			@RequestParam(value = "columns", required = false) String columns,
			HttpServletRequest request){
		
		//Acceso a BD para recuperar datos
		List<JerarquiaDto<UsuarioJerarquia>> listUsuarioPage = this.usuarioJerarquiaService.findAllLikeJerarquia(filterUsuarioJerarquia, pagination);
		pagination.setPage(null);
		pagination.setRows(null);
		List<JerarquiaDto<UsuarioJerarquia>> listUsuarioAll = this.usuarioJerarquiaService.findAllLikeJerarquia(filterUsuarioJerarquia, pagination);
		
		//Nombre fichero
		modelMap.put("fileName", "datosExcel");
		
		//Metadatos de Jerarquia
		JerarquiaMetadata jmd = new JerarquiaMetadata();
			//Filtro
//			jmd.setShowFiltered(true);						//Mostrar (default)
//			jmd.setFilterToken("*");						//Token * (default)
			jmd.setFilterHeaderName("Filtrados"); 			//Cabecera filtrados
			
			//Tabulacion
			jmd.setShowTabbed(true); 						//Tabular filtrados
//			jmd.setTabToken("   ");							//Toleken '   ' (default)
			jmd.setTabColumnName("nombre");					//Campo a tabular
			
			//Iconos
			jmd.setShowIcon(true);							//Mostrar
//			jmd.setIconExpanded("[-]");						//Icono expandido (default)
//			jmd.setIconUnexpanded("[+]");					//Icono contraido (default)
//			jmd.setIconExpanded("[ ]");						//Icono sin hijos (default)
			jmd.setIconColumnName("nombre");				//Campo a tabular
			jmd.setIconBeanAtribute("id");					//Campo referencia
			jmd.setIconCollapsedList(pagination.getJerarquia().getTree()); //Listado de elementos colapsados
		
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
				//jerarquia
				usuarioExcelDataAll.setJerarquiaMetadada(jmd);
			reportData.add(usuarioExcelDataAll);
				
			//Hoja 2
			ReportData<Usuario> usuarioExcelDataPage = new ReportData<Usuario>();
				//nombre hoja
				usuarioExcelDataPage.setSheetName("Página 1 de usuarios");
				//cabeceras hoja
				usuarioExcelDataPage.setHeaderNames(ReportData.parseColumns(columns));
				//datos hoja
				usuarioExcelDataPage.setModelData(listUsuarioPage);
				//jerarquia
				usuarioExcelDataPage.setJerarquiaMetadada(jmd);
			reportData.add(usuarioExcelDataPage);
		modelMap.put("reportData", reportData);
		
		//Generación del ODS
		return new ModelAndView("odsReport", modelMap);
	}
	
	/**
	 * EXPORTERS AGRUPAR
	 */
	@RequestMapping(value = "csvReportGroup", method = RequestMethod.POST)
	protected ModelAndView getCSVReportGroup(@ModelAttribute UsuarioJerarquia filterUsuarioJerarquia, @ModelAttribute Pagination pagination,
			ModelMap modelMap,
			@RequestParam(value = "columns", required = false) String columns){
		
		//Acceso a BD para recuperar datos
		pagination.setPage(null);
		pagination.setRows(null);
		List<JerarquiaDto<UsuarioJerarquia>> listUsuarioAll = this.usuarioJerarquiaService.findAllLikeJerarquia(filterUsuarioJerarquia, pagination);
		
		//Nombre fichero
		modelMap.put("fileName", "datosCSV");
			
		//Datos
		ReportData<UsuarioJerarquia> reportData = new ReportData<UsuarioJerarquia>();
			//cabeceras hoja
			reportData.setHeaderNames(ReportData.parseColumns(columns));
			//datos hoja
			reportData.setModelData(listUsuarioAll);
			
			//Metadatos de Jerarquia
			JerarquiaMetadata jmd = new JerarquiaMetadata();
				//Filtro
//				jmd.setShowFiltered(true);						//Mostrar (default)
//				jmd.setFilterToken("*");						//Token * (default)
				jmd.setFilterHeaderName("Filtrados"); 			//Cabecera filtrados
				
				//Tabulacion
				jmd.setShowTabbed(true); 						//Tabular filtrados
//				jmd.setTabToken("   ");							//Toleken '   ' (default)
				jmd.setTabColumnName("nombre");					//Campo a tabular
				
				//Iconos
				jmd.setShowIcon(true);							//Mostrar
//				jmd.setIconExpanded("[-]");						//Icono expandido (default)
//				jmd.setIconUnexpanded("[+]");					//Icono contraido (default)
//				jmd.setIconExpanded("[ ]");						//Icono sin hijos (default)
				jmd.setIconColumnName("nombre");				//Campo a tabular
				jmd.setIconBeanAtribute("id");					//Campo referencia
				jmd.setIconCollapsedList(pagination.getJerarquia().getTree()); //Listado de elementos colapsados
			reportData.setJerarquiaMetadada(jmd);
	
			//agrupacion
			reportData.setGrouping(true);
			reportData.setGroupColumnName("grupo");
//			reportData.setShowGroupColumng(false);				//Ocultar la columna de agrupacion (default)
			
		modelMap.put("reportData", reportData);
		
		//Generación del CVS
		return new ModelAndView("csvReport", modelMap);
	}
	
	@RequestMapping(value = {"xlsReportGroup" , "xlsxReportGroup"}, method = RequestMethod.POST)
	protected ModelAndView getExcelPOIGroup(@ModelAttribute UsuarioJerarquia filterUsuarioJerarquia, @ModelAttribute Pagination pagination,
			ModelMap modelMap,
			@RequestParam(value = "columns", required = false) String columns,
			HttpServletRequest request){
		
		//Acceso a BD para recuperar datos
		List<JerarquiaDto<UsuarioJerarquia>> listUsuarioPage = this.usuarioJerarquiaService.findAllLikeJerarquia(filterUsuarioJerarquia, pagination);
		pagination.setPage(null);
		pagination.setRows(null);
		List<JerarquiaDto<UsuarioJerarquia>> listUsuarioAll = this.usuarioJerarquiaService.findAllLikeJerarquia(filterUsuarioJerarquia, pagination);
		
		//Nombre fichero
		modelMap.put("fileName", "datosExcel");
		
		//Metadatos de Jerarquia
		JerarquiaMetadata jmd = new JerarquiaMetadata();
			//Filtro
//			jmd.setShowFiltered(true);						//Mostrar (default)
//			jmd.setFilterToken("*");						//Token * (default)
			jmd.setFilterHeaderName("Filtrados"); 			//Cabecera filtrados
			
			//Tabulacion
			jmd.setShowTabbed(true); 						//Tabular filtrados
//			jmd.setTabToken("   ");							//Toleken '   ' (default)
			jmd.setTabColumnName("nombre");					//Campo a tabular
			
			//Iconos
			jmd.setShowIcon(true);							//Mostrar
//			jmd.setIconExpanded("[-]");						//Icono expandido (default)
//			jmd.setIconUnexpanded("[+]");					//Icono contraido (default)
//			jmd.setIconExpanded("[ ]");						//Icono sin hijos (default)
			jmd.setIconColumnName("nombre");				//Campo a tabular
			jmd.setIconBeanAtribute("id");					//Campo referencia
			jmd.setIconCollapsedList(pagination.getJerarquia().getTree()); //Listado de elementos colapsados
		
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
				//jerarquia
				usuarioExcelDataAll.setJerarquiaMetadada(jmd);
				//agrupacion
				usuarioExcelDataAll.setGrouping(true);
				usuarioExcelDataAll.setGroupColumnName("grupo");
//				usuarioExcelDataAll(false);				//Ocultar la columna de agrupacion (default)
			reportData.add(usuarioExcelDataAll);
				
			//Hoja 2
			ReportData<Usuario> usuarioExcelDataPage = new ReportData<Usuario>();
				//nombre hoja
				usuarioExcelDataPage.setSheetName("Página 1 de usuarios");
				//cabeceras hoja
				usuarioExcelDataPage.setHeaderNames(ReportData.parseColumns(columns));
				//datos hoja
				usuarioExcelDataPage.setModelData(listUsuarioPage);
				//jerarquia
				usuarioExcelDataPage.setJerarquiaMetadada(jmd);
				//agrupacion
				usuarioExcelDataPage.setGrouping(true);
				usuarioExcelDataPage.setGroupColumnName("grupo");
//				usuarioExcelDataAll(false);				//Ocultar la columna de agrupacion (default)
			reportData.add(usuarioExcelDataPage);
		modelMap.put("reportData", reportData);
		
		//Generación del XLS o XLSX
		String reportView = request.getServletPath().substring(18, request.getServletPath().indexOf("Group"));
		return new ModelAndView(reportView, modelMap);
		
//			return new ModelAndView("xlsReport", modelMap);
//			return new ModelAndView("xlsxReport", modelMap);
	}
	
	@RequestMapping(value = "odsReportGroup", method = RequestMethod.POST)
	protected ModelAndView getODSReportGroup(@ModelAttribute UsuarioJerarquia filterUsuarioJerarquia, @ModelAttribute Pagination pagination,
			ModelMap modelMap,
			@RequestParam(value = "columns", required = false) String columns,
			HttpServletRequest request){
		
		//Acceso a BD para recuperar datos
		List<JerarquiaDto<UsuarioJerarquia>> listUsuarioPage = this.usuarioJerarquiaService.findAllLikeJerarquia(filterUsuarioJerarquia, pagination);
		pagination.setPage(null);
		pagination.setRows(null);
		List<JerarquiaDto<UsuarioJerarquia>> listUsuarioAll = this.usuarioJerarquiaService.findAllLikeJerarquia(filterUsuarioJerarquia, pagination);
		
		//Nombre fichero
		modelMap.put("fileName", "datosExcel");
		
		//Metadatos de Jerarquia
		JerarquiaMetadata jmd = new JerarquiaMetadata();
			//Filtro
//			jmd.setShowFiltered(true);						//Mostrar (default)
//			jmd.setFilterToken("*");						//Token * (default)
			jmd.setFilterHeaderName("Filtrados"); 			//Cabecera filtrados
			
			//Tabulacion
			jmd.setShowTabbed(true); 						//Tabular filtrados
//			jmd.setTabToken("   ");							//Toleken '   ' (default)
			jmd.setTabColumnName("nombre");					//Campo a tabular
			
			//Iconos
			jmd.setShowIcon(true);							//Mostrar
//			jmd.setIconExpanded("[-]");						//Icono expandido (default)
//			jmd.setIconUnexpanded("[+]");					//Icono contraido (default)
//			jmd.setIconExpanded("[ ]");						//Icono sin hijos (default)
			jmd.setIconColumnName("nombre");				//Campo a tabular
			jmd.setIconBeanAtribute("id");					//Campo referencia
			jmd.setIconCollapsedList(pagination.getJerarquia().getTree()); //Listado de elementos colapsados
		
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
				//jerarquia
				usuarioExcelDataAll.setJerarquiaMetadada(jmd);
				//agrupacion
				usuarioExcelDataAll.setGrouping(true);
				usuarioExcelDataAll.setGroupColumnName("grupo");
//				usuarioExcelDataAll(false);				//Ocultar la columna de agrupacion (default)
			reportData.add(usuarioExcelDataAll);
				
			//Hoja 2
			ReportData<Usuario> usuarioExcelDataPage = new ReportData<Usuario>();
				//nombre hoja
				usuarioExcelDataPage.setSheetName("Página 1 de usuarios");
				//cabeceras hoja
				usuarioExcelDataPage.setHeaderNames(ReportData.parseColumns(columns));
				//datos hoja
				usuarioExcelDataPage.setModelData(listUsuarioPage);
				//jerarquia
				usuarioExcelDataPage.setJerarquiaMetadada(jmd);
				//agrupacion
				usuarioExcelDataPage.setGrouping(true);
				usuarioExcelDataPage.setGroupColumnName("grupo");
//				usuarioExcelDataAll(false);				//Ocultar la columna de agrupacion (default)
			reportData.add(usuarioExcelDataPage);
		modelMap.put("reportData", reportData);
		
		//Generación del ODS
		return new ModelAndView("odsReport", modelMap);
	}
}