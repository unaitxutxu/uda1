/*!
 * Copyright 2012 E.J.I.E., S.A.
 *
 * Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 * Solo podrá usarse esta obra si se respeta la Licencia.
 * Puede obtenerse una copia de la Licencia en
 *
 *      http://ec.europa.eu/idabc/eupl.html
 *
 * Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 * el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 * SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 * Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 * que establece la Licencia.
 */

(function ($) {
	
	
	//*****************************************************************************************************************
	// DEFINICIÓN BASE DEL PATRÓN (definición de la variable privada que contendrá los métodos y la función de jQuery)
	//*****************************************************************************************************************
	
	var rup_grid = {};
	
	//Se configura el arranque de UDA para que alberge el nuevo patrón 
	$.extend($.rup.iniRup, $.rup.rupSelectorObjectConstructor("rup_grid", rup_grid));
	
	//********************************
	// DEFINICIÓN DE MÉTODOS PÚBLICOS
	//********************************
	
	$.fn.rup_grid("extend",{
		addRowData : function (rowid, data, position, srcrowid) {
			var tableName = $(this)[0].id;			
			//Se aade la capa de separacion para diferenciar los nuevos elementos incluidos
			if ($("#" + tableName + " #separadorAadidos").html() === null) {
				$("#" + tableName + " tr:first-child").after($("#" + tableName + " tr:first-child").clone(false).css("display", "none").css("height", "").attr("id", "separadorAadidos"));
				
				$.each($("#" + tableName + " #separadorAadidos td") , function (index, object) {
					$(this).html("").attr("class", "tdAddSeparator");
				});
				
				$("#" + tableName + " #separadorAadidos").addClass("trAddSeparator");
				$("#" + tableName + " #separadorAadidos").css("display", "");
			}
			$(this).jqGrid("addRowData", rowid, data, position, srcrowid);
			//Añadimos los estilos de elemento añadido
			$("#" + tableName + " #" + rowid).addClass("addElement");
			//$("#" + tableName + " #" + rowid + " td").addClass("addElementBorder");
		},
		delRowData : function (rowid) {
			$(this).jqGrid("delRowData", rowid);
			if ($(this).jqGrid("getDataIDs").length === Number($(this).jqGrid("getGridParam", "rowNum"))) {
				//si tengo el mismo numero de registro que el numeroi de filas hay que quitar la barra
				//de nuevo registro
				$("#" +  $(this)[0].id + " #separadorAadidos").remove();
			}
		},
		setRowData : function (rowid, data, cssp) {
			//TODO antes de insertar
			$(this).jqGrid("setRowData", rowid, data, cssp);
			//TODO despues de insertar
		},
		getRowData : function (rowid) {
			//TODO antes de obtener los datos de una fila
			return $(this).jqGrid("getRowData", rowid);
			//TODO despues de obtener los datos de una fila
		},
		setSelection : function (selection, onsr) {
			//TODO antes de seleccionar una fila
			$(this).jqGrid("setSelection", selection, onsr);
			//TODO despues de seleccionar una fila
		},
		resetSelection : function () {
			if($.isFunction(this[0].rup_gridProps.resetSelection)){
				this[0].rup_gridProps.resetSelection.call();
			}else{
				$(this).jqGrid("resetSelection");				
			}
		},
		getDataIDs : function () {
			//TODO antes de obtener el array de datos
			return $(this).jqGrid("getDataIDs");
			//TODO despues de obtener el array de datos
		},
		getGridParam : function (pName) {
			//TODO antes de obtener el valor de la opcion que recibe como parametro
			return $(this).jqGrid("getGridParam", pName);
			//TODO despues de obtener el valor de la opcion que recibe como parametro
		},
		setGridParam : function (newParams) {
			//TODO antes de establecer el nuevo valor a cualquier opción que recibe como parametro
			$(this).jqGrid("setGridParam", newParams);
			//TODO despues de establecer el nuevo valor a cualquier opción que recibe como parametro
		},
		clearGridData : function (clearfooter) {
			//TODO antes de borrar el contenido del grid
			
			$(this).jqGrid("clearGridData", clearfooter);
			//TODO despues de borrar el contenido del grid
		},
		getSelectedRows : function () {	//Función que devuelve un array con los elementos seleccionados, comprobando si es multiselección o no
			var  isMultiselect = $(this).jqGrid("getGridParam", "multiselect");
			if (isMultiselect) { //si el grid es multiseleccion
				return $(this).jqGrid("getGridParam", "selarrrow");
			} else {
				return [$(this).jqGrid("getGridParam", "selrow")];
			}
		},
		reloadGrid : function () { //Funcion que recarga el grid
			var self =this;

			if ($.rup.browser.isIE){
				// Ejecucion especial para que se muestre correctamente el mensaje de cargando en el IE8
				$("#lui_"+self.attr("id")).show();
				$("#load_"+self.attr("id")).show();
				
				window.setTimeout(function(){
					self.trigger("reloadGrid");
				}, 0);
			}else{
				self.trigger("reloadGrid");
			}
		},
		editRow : function (rowid, keys, oneditfunc, succesfunc, url, extraparam, aftersavefunc, errorfunc, afterrestorefunc) {
			$(this).jqGrid("editRow", rowid, keys, oneditfunc, succesfunc, url, extraparam, aftersavefunc, errorfunc, afterrestorefunc);
		},
		saveRow : function (rowid, succesfunc, url, extraparam, aftersavefunc, errorfunc, afterrestorefunc) {
			
			// Funcion encargada de terminar con la accion de guardar la linea para el caso de controles rup
			function default_aftersavefunc(rowid, res){
				// se asigna al contenido de la celda el valor seleccionado en el combo
				var valor, rupCombos=$("tr#"+rowid+" select.rup_combo", this);
				
				for (var i=0;i<rupCombos.length;i=i+1){
					valor = $(rupCombos[i]).rup_combo("label");
					$(rupCombos[i]).parent().html(valor);
				}
				
				// Se ejecuta la funcion aftersavefunc indicada 
				if (aftersavefunc){
					aftersavefunc.call(rowid, res);
				}
			}
			
			$(this).jqGrid('saveRow', rowid, succesfunc, url, extraparam, default_aftersavefunc, errorfunc, afterrestorefunc);
		},
		restoreRow : function (rowid, afterrestorefunc) {

			var valor, rupCombos=$("tr#"+rowid+" select.rup_combo", this);
			
			var savedRow = $(this).jqGrid("getGridParam","savedRow");
			
			for (var i=0;i<rupCombos.length;i=i+1){
				valor = $(rupCombos[i]).rup_combo("value");
				savedRow[0][$(rupCombos[i]).attr("name")]=valor;
			}
			
			$(this).jqGrid('restoreRow', rowid, afterrestorefunc);
		},
		getColModel : function () {// Función que devuelve el colModel directamente.
			return $(this).jqGrid("getGridParam", "colModel");
		},
		GridToForm : function (rowid, formid) {
			$(this).jqGrid("GridToForm", rowid, formid);
		},
		FormToGrid : function (rowid, formid, mode, position) {
			$(this).jqGrid("FormToGrid", rowid, formid, mode, position);
		},
		isMultiselect : function () {//Función que devuelve si el mantenimiento es de tipo multi selección o no.
			return $(this).jqGrid("getGridParam", "multiselect");
		},
		getCol : function (rowid, colName) { //Función que devuelve el valor de la celda de la fila que se le pasa como paramtero. El colName puede ser o el indice de la columna o el nombre de la misma
			return $(this).jqGrid("getCell", rowid, colName);
		},
		isEditable : function () {//Función que devuelve si el grid es editable o no.
			return this[0].rup_gridProps.editable;
		},
		getEditingRowData : function (rowid) {
			var ind = this.jqGrid("getInd",rowid,true), aData = [], objData = {}, grid = this;
			if (ind === false) {
				return;
			}
			$('td',ind).each( function(i) {
				if (grid.jqGrid("getGridParam", "colModel")[i]) {
					objData[grid.jqGrid("getGridParam", "colModel")[i].name] = $(":first",this).val();
				}
			});
			return objData;
		},
		isEditing : function () {
			var rowids = this.jqGrid("getDataIDs");
			for (var i =0;i<rowids.length;i++){
				if ($(this.jqGrid("getInd",rowids[i],true)).attr("editable") === "1") {
					return true;
				}
			}
			return false;
		},
		reorderColumns : function (newOrder) {//función que lanza cuando se ordenan las columnas (Drag&Drop)
			if ($.isFunction(this[0].rup_gridProps.onAfterDragAndDrop)) { //lanza el evento onAfterDragAndDrop
				this[0].rup_gridProps.onAfterDragAndDrop.call(this, newOrder);
			}
		}
	});
	
	//********************************
	// DEFINICIÓN DE MÉTODOS PRIVADOS
	//********************************

	$.fn.rup_grid("extend", {
		_init : function(args) {
			
			if (args.length > 1) {
				$.rup.errorGestor($.rup.i18nParse($.rup.i18n.base,"rup_global.initError") + $(this).attr("id"));
			}
			else {
				
				//Se recogen y cruzan las paremetrizaciones del objeto
				var settings = $.extend({}, $.fn.rup_grid.defaults, args[0]);
				settings.groupingView = $.extend({}, $.fn.rup_grid.defaults.groupingView, settings.groupingView);
				
				var	self = this, resize_cursors = [2], text, child, porcentaje, sortableUserProperty;
				
				//Se declaran todas las variables asociadas a los eventos
				var beforeRequestUserEvent, loadBeforeSendUserEvent, serializeGridDataUserEvent, loadErrorUserEvent,
					beforeProcessingUserEvent, gridCompleteUserEvent, loadCompleteUserEvent, afterInsertRowUserEvent, 
					beforeSelectRowUserEvent, onCellSelectUserEvent, ondblClickRowUserEvent, onHeaderClickUserEvent, 
					onPagingUserEvent, onSelectAllUserEvent, onSelectRowUserEvent, onSortColUserEvent, 
					onRightClickRowUserEvent, resizeStartUserEvent, resizeStopUserEvent;
				
				//Definicion de eventos
				//
				//Orden de ejecucion de eventos en una peticion Ajax:
				//beforeRequest
				//serializeGridData
				//loadBeforeSend
				//loadError (Si se produce un error en la llamada)
				//beforeProcessing
				//gridComplete
				//loadComplete
				//
				//Orden de ejecucion de los eventos al seleccionar una fila:
				//beforeSelectRow
				//onSelectRow
				//onCellSelect
				//
				
				////////////////////////////////////////
				//Estructura funcional de los eventos	
				////////////////////////////////////////
				//
				//pre-event => In case of return false, the event is cancel 
				//user-event => In case of return false, the default/system event is not executed
				//default-event => Is the component event
				//post-event => In case of return false, where possible, any other define actions of execution are cancel
				//
				
				/////////////////////////////////////////////
				//Eventos envueltos en las peticiones Ajax		
				/////////////////////////////////////////////
					
				/* Evento de antes de la llamada */
				beforeRequestUserEvent = settings.beforeRequest;
				settings.beforeRequest = function(){
					// console.log('beforeRequest');
					if(beforeRequestUserEvent !== undefined && beforeRequestUserEvent !== null){
						if (typeof beforeRequestUserEvent === "function"){
							if(beforeRequestUserEvent() === false){
								return false;
							}  
						} else if (typeof beforeRequestUserEvent === "object"){
							if (beforeRequestUserEvent.pre !== undefined && beforeRequestUserEvent.pre() === false){return false;};
							if ($("#"+$(this).attr("id")).data("defaultEventFunctions").beforeRequest_default() === false){return false;};
							if (beforeRequestUserEvent.post !== undefined && beforeRequestUserEvent.post() === false){return false;};
							return true;
						}   
					} 
					//Comportamiento por defecto del evento
					return $("#"+$(this).attr("id")).data("defaultEventFunctions").beforeRequest_default();
				};
									
				/* Evento que devuelve los datos que serán enviados en la llamada Ajax.
				 * El evento esta enfocado a la serializacion, modificion, codificación o personalización de los datos
				 * antes de ser enviados. */
				serializeGridDataUserEvent = settings.serializeGridData;
				settings.serializeGridData = function(postData){
					var returnData;
					// console.log('serializeGridData');
					if(serializeGridDataUserEvent !== undefined && serializeGridDataUserEvent !== null){
						if (typeof serializeGridDataUserEvent === "function"){
							if(serializeGridDataUserEvent(postData) === false){
								return false;
							}  
						} else if (typeof serializeGridDataUserEvent === "object"){
							//Se ejecuta el método pre y se recogen los datos devueltos
							if (serializeGridDataUserEvent.pre !== undefined){
								returnData = serializeGridDataUserEvent.pre(postData);
								if (returnData === false){return false;};
							}
							//Se ejecuta el método default y se recoge la salida
							returnData = $("#"+$(this).attr("id")).data("defaultEventFunctions").serializeGridData_default(postData, returnData);
							if (returnData === false){return false;};
							//Se ejecuta el método post y se devuelve los datos obtenidos
							if (serializeGridDataUserEvent.post !== undefined){ returnData = serializeGridDataUserEvent.post(postData, returnData);};
							return returnData;
						}   
					} 
					//Comportamiento por defecto del evento
					return $("#"+$(this).attr("id")).data("defaultEventFunctions").serializeGridData_default(postData);
				};
				
				/* Evento del evento de antes de la carga de los datos */
				loadBeforeSendUserEvent = settings.loadBeforeSend;
				settings.loadBeforeSend = function(xhr, settings){
					// console.log('loadBeforeSend');
					if(loadBeforeSendUserEvent !== undefined && loadBeforeSendUserEvent !== null){
						if (typeof loadBeforeSendUserEvent === "function"){
							if(loadBeforeSendUserEvent(xhr, settings) === false){
								return false;
							}  
						} else if (typeof loadBeforeSendUserEvent === "object"){
							if (loadBeforeSendUserEvent.pre !== undefined && loadBeforeSendUserEvent.pre(xhr, settings) === false){return false;};
							if ($("#"+$(this).attr("id")).data("defaultEventFunctions").loadBeforeSend_default(xhr, settings) === false){return false;};
							if (loadBeforeSendUserEvent.post !== undefined && loadBeforeSendUserEvent.post(xhr, settings) === false){return false;};
							return true;
						}   
					} 
					//Comportamiento por defecto del evento
					return($("#"+$(this).attr("id")).data("defaultEventFunctions").loadBeforeSend_default(xhr, settings));
				};
								
				/* Evento ejecutado si la peticion al servidor falla */
				loadErrorUserEvent = settings.loadError;
				settings.loadError = function(xhr, status, error){
					// console.log('loadError');
					if(loadErrorUserEvent !== undefined && loadErrorUserEvent !== null){
						if (typeof loadErrorUserEvent === "function"){
							if(loadErrorUserEvent(xhr, status, error) === false){
								return false;
							}  
						} else if (typeof loadErrorUserEvent === "object"){
							if (loadErrorUserEvent.pre !== undefined && loadErrorUserEvent.pre(xhr, status, error) === false){return false;};
							if ($("#"+$(this).attr("id")).data("defaultEventFunctions").loadError_default(xhr, status, error) === false){return false;};
							if (loadErrorUserEvent.post !== undefined){loadErrorUserEvent.post(xhr, status, error);};
							return false;
						}   
					} 
					//Comportamiento por defecto del evento
					$($("#"+$(this).attr("id")).data("defaultEventFunctions").loadError_default(xhr, status, error));
				};
				
				/* Evento que se ejecuta tras procesar la respuesta recivida desde el servidor */
				beforeProcessingUserEvent = settings.beforeProcessing;
				settings.beforeProcessing = function(data, status, xhr){
					// console.log('beforeProcessing');
					if(beforeProcessingUserEvent !== undefined && beforeProcessingUserEvent !== null){
						if (typeof beforeProcessingUserEvent === "function"){
							if(beforeProcessingUserEvent(data , status , xhr) === false){
								return false;
							}  
						} else if (typeof beforeProcessingUserEvent === "object"){
							if (beforeProcessingUserEvent.pre !== undefined && beforeProcessingUserEvent.pre(data , status , xhr) === false){return false;};
							if ($("#"+$(this).attr("id")).data("defaultEventFunctions").beforeProcessing_default(data , status , xhr) === false){return false;};
							if (beforeProcessingUserEvent.post !== undefined){beforeProcessingUserEvent.post(data , status , xhr);};
							return false;
						}   
					} 
					//Comportamiento por defecto del evento
					$("#"+$(this).attr("id")).data("defaultEventFunctions").beforeProcessing_default(data , status , xhr);
				};
				
				/* Evento de la carga de la tabla */
				gridCompleteUserEvent = settings.gridComplete;
				settings.gridComplete = function(){
					// console.log('gridComplete');
					if(gridCompleteUserEvent !== undefined && gridCompleteUserEvent !== null){
						if (typeof gridCompleteUserEvent === "function"){
							if(gridCompleteUserEvent() === false){
								return false;
							}  
						} else if (typeof gridCompleteUserEvent === "object"){
							if (gridCompleteUserEvent.pre !== undefined && gridCompleteUserEvent.pre() === false){return false;};
							if ($("#"+$(this).attr("id")).data("defaultEventFunctions").gridComplete_default() === false){return false;};
							if (gridCompleteUserEvent.post !== undefined){gridCompleteUserEvent.post();};
							return false;
						}   
					} 
					//Comportamiento por defecto del evento
					$("#"+$(this).attr("id")).data("defaultEventFunctions").gridComplete_default();
					
					//Se le aplica el tooltip de uda
					$("#"+$(this).attr("id")+" [title]").rup_tooltip();
				};
				
				/* Evento de carga completada */
				loadCompleteUserEvent = settings.loadComplete;
				settings.loadComplete = function(data){
					// console.log('loadComplete');
					if(loadCompleteUserEvent !== undefined && loadCompleteUserEvent !== null){
						if (typeof loadCompleteUserEvent === "function"){
							if(loadCompleteUserEvent(data) === false){
								return false;
							}  
						} else if (typeof loadCompleteUserEvent === "object"){
							if (loadCompleteUserEvent.pre !== undefined && loadCompleteUserEvent.pre(data) === false){return false;};
							if ($("#"+$(this).attr("id")).data("defaultEventFunctions").loadComplete_default(data) === false){return false;};
							if (loadCompleteUserEvent.post !== undefined){loadCompleteUserEvent.post(data);};
							return false;
						}   
					} 
					//Comportamiento por defecto del evento
					$("#"+$(this).attr("id")).data("defaultEventFunctions").loadComplete_default(data);
				};
								
				/////////////////////////////////////////////////////////
				// Resto de eventos envueltos en el manejo de la tabla		
				/////////////////////////////////////////////////////////			
				
				/* Evento que se ejecuta despues de la insercion de cualquier fila */
				afterInsertRowUserEvent = settings.afterInsertRow;
				settings.afterInsertRow = function(rowid , rowdata, rowelem){
					// console.log('afterInsertRow');
					if(afterInsertRowUserEvent !== undefined && afterInsertRowUserEvent !== null){
						if (typeof afterInsertRowUserEvent === "function"){
							if(afterInsertRowUserEvent(rowid , rowdata, rowelem) === false){
								return false;
							}  
						} else if (typeof afterInsertRowUserEvent === "object"){
							if (afterInsertRowUserEvent.pre !== undefined && afterInsertRowUserEvent.pre(rowid , rowdata, rowelem) === false){return false;};
							if ($("#"+$(this).attr("id")).data("defaultEventFunctions").afterInsertRow_default(rowid , rowdata, rowelem) === false){return false;};
							if (afterInsertRowUserEvent.post !== undefined){afterInsertRowUserEvent.post(rowid , rowdata, rowelem);};
							return false;
						}   
					} 
					//Comportamiento por defecto del evento
					$("#"+$(this).attr("id")).data("defaultEventFunctions").afterInsertRow_default(rowid , rowdata, rowelem);
				};
				
				/* Evento que se ejecuta antes de la seleccion de una fila */
				beforeSelectRowUserEvent = settings.beforeSelectRow;
				settings.beforeSelectRow = function(rowid , e){
					// console.log('beforeSelectRow');
					if(beforeSelectRowUserEvent !== undefined && beforeSelectRowUserEvent !== null){
						if (typeof beforeSelectRowUserEvent === "function"){
							if(beforeSelectRowUserEvent(rowid , e) === false){
								return false;
							}  
						} else if (typeof beforeSelectRowUserEvent === "object"){
							if (beforeSelectRowUserEvent.pre !== undefined && beforeSelectRowUserEvent.pre(rowid , e) === false){return false;};
							if ($("#"+$(this).attr("id")).data("defaultEventFunctions").beforeSelectRow_default(rowid , e) === false){return false;};
							if (beforeSelectRowUserEvent.post !== undefined && beforeSelectRowUserEvent.post(rowid , e) === false){return false;};
							return true;
						}   
					} 
					//Comportamiento por defecto del evento
					return($("#"+$(this).attr("id")).data("defaultEventFunctions").beforeSelectRow_default(rowid , e));				
				};
				
				/* Evento de usuario que se ejecuta al seleccionar una celda */
				onCellSelectUserEvent = settings.onCellSelect;
				settings.onCellSelect = function(rowid , iCol, cellcontent, e){
					// console.log('onCellSelect');
					if(onCellSelectUserEvent !== undefined && onCellSelectUserEvent !== null){
						if (typeof onCellSelectUserEvent === "function"){
							if(onCellSelectUserEvent(rowid , iCol, cellcontent, e) === false){
								return false;
							}  
						} else if (typeof onCellSelectUserEvent === "object"){
							if (onCellSelectUserEvent.pre !== undefined && onCellSelectUserEvent.pre(rowid , iCol, cellcontent, e) === false){return false;};
							if ($("#"+$(this).attr("id")).data("defaultEventFunctions").onCellSelect_default(rowid , iCol, cellcontent, e) === false){return false;};
							if (onCellSelectUserEvent.post !== undefined){onCellSelectUserEvent.post(rowid , iCol, cellcontent, e);};
							return false;
						}   
					} 
					//Comportamiento por defecto del evento
					$("#"+$(this).attr("id")).data("defaultEventFunctions").onCellSelect_default(rowid , iCol, cellcontent, e);
				};
				
				/* Evento de doble clik sobre la tabla */
				ondblClickRowUserEvent = settings.ondblClickRow;
				settings.ondblClickRow = function(rowid, iRow, iCol, e){
					// console.log('ondblClickRow');
					if(ondblClickRowUserEvent !== undefined && ondblClickRowUserEvent !== null){
						if (typeof ondblClickRowUserEvent === "function"){
							if(ondblClickRowUserEvent(rowid, iRow, iCol, e) === false){
								return false;
							}  
						} else if (typeof ondblClickRowUserEvent === "object"){
							if (ondblClickRowUserEvent.pre !== undefined && ondblClickRowUserEvent.pre(rowid, iRow, iCol, e) === false){return false;};
							if ($("#"+$(this).attr("id")).data("defaultEventFunctions").ondblClickRow_default(rowid, iRow, iCol, e) === false){return false;};
							if (ondblClickRowUserEvent.post !== undefined){ondblClickRowUserEvent.post(rowid, iRow, iCol, e);};
							return false;
						}   
					} 
					//Comportamiento por defecto del evento
					$("#"+$(this).attr("id")).data("defaultEventFunctions").ondblClickRow_default(rowid, iRow, iCol, e);
				};
				
				/* Evento de click en la cabecera */
				onHeaderClickUserEvent = settings.onHeaderClick;
				settings.onHeaderClick = function(gridState){
					// console.log('onHeaderClick');
					if(onHeaderClickUserEvent !== undefined && onHeaderClickUserEvent !== null){
						if (typeof onHeaderClickUserEvent === "function"){
							if(onHeaderClickUserEvent(gridState) === false){
								return false;
							}  
						} else if (typeof onHeaderClickUserEvent === "object"){
							if (onHeaderClickUserEvent.pre !== undefined && onHeaderClickUserEvent.pre(gridState) === false){return false;};
							if ($("#"+$(this).attr("id")).data("defaultEventFunctions").onHeaderClick_default(gridState) === false){return false;};
							if (onHeaderClickUserEvent.post !== undefined){onHeaderClickUserEvent.post(gridState);};
							return false;
						}   
					} 
					//Comportamiento por defecto del evento
					$("#"+$(this).attr("id")).data("defaultEventFunctions").onHeaderClick_default(gridState);
				};
				
				/* Evento de click sobre cualquiera de los botones de paginacion */
				onPagingUserEvent = settings.onPaging;
				settings.onPaging = function(pgButton){
					// console.log('onPaging');
					if(onPagingUserEvent !== undefined && onPagingUserEvent !== null){
						if (typeof onPagingUserEvent === "function"){
							if(onPagingUserEvent(pgButton) === false){
								return "stop";
							}  
						} else if (typeof onPagingUserEvent === "object"){
							if (onPagingUserEvent.pre !== undefined && onPagingUserEvent.pre(pgButton) === false){return "stop";};
							if ($("#"+$(this).attr("id")).data("defaultEventFunctions").onPaging_default(pgButton) === false){return "stop";};
							if (onPagingUserEvent.post !== undefined && onPagingUserEvent.post(pgButton) === false){return "stop";};
							return "";
						}   
					} 
					//Comportamiento por defecto del evento
					return($("#"+$(this).attr("id")).data("defaultEventFunctions").onPaging_default(pgButton));
				};
				
				/* Evento de click con el boton derecho sobre una fila */
				onRightClickRowUserEvent = settings.onRightClickRow;
				settings.onRightClickRow = function(rowid, iRow, iCol, e){
					// console.log('onRightClickRow');
					if(onRightClickRowUserEvent !== undefined && onRightClickRowUserEvent !== null){
						if (typeof onRightClickRowUserEvent === "function"){
							if(onRightClickRowUserEvent(rowid, iRow, iCol, e) === false){
								return false;
							}  
						} else if (typeof onRightClickRowUserEvent === "object"){
							if (onRightClickRowUserEvent.pre !== undefined && onRightClickRowUserEvent.pre(rowid, iRow, iCol, e) === false){return false;};
							if ($("#"+$(this).attr("id")).data("defaultEventFunctions").onRightClickRow_default(rowid, iRow, iCol, e) === false){return false;};
							if (onRightClickRowUserEvent.post !== undefined){onRightClickRowUserEvent.post(rowid, iRow, iCol, e);};
							return false;
						}   
					} 
					//Comportamiento por defecto del evento
					$("#"+$(this).attr("id")).data("defaultEventFunctions").onRightClickRow_default(rowid, iRow, iCol, e);
				};
				
				/* Evento de la seleccion de todas las filas */
				onSelectAllUserEvent = settings.onSelectAll;
				settings.onSelectAll = function(aRowids, status){
					// console.log('onSelectAll');
					if(onSelectAllUserEvent !== undefined && onSelectAllUserEvent !== null){
						if (typeof onSelectAllUserEvent === "function"){
							if(onSelectAllUserEvent(aRowids, status) === false){
								return false;
							}  
						} else if (typeof onSelectAllUserEvent === "object"){
							if (onSelectAllUserEvent.pre !== undefined && onSelectAllUserEvent.pre(aRowids, status) === false){return false;};
							if ($("#"+$(this).attr("id")).data("defaultEventFunctions").onSelectAll_default(aRowids, status) === false){return false;};
							if (onSelectAllUserEvent.post !== undefined){onSelectAllUserEvent.post(aRowids, status);};
							return false;
						}   
					} 
					//Comportamiento por defecto del evento
					$("#"+$(this).attr("id")).data("defaultEventFunctions").onSelectAll_default(aRowids, status);
				};
				
				/* Evento de la seleccion de fila */
				onSelectRowUserEvent = settings.onSelectRow;
				settings.onSelectRow = function(rowid, status){
					// console.log('onSelectRow');
					if(onSelectRowUserEvent !== undefined && onSelectRowUserEvent !== null){
						if (typeof onSelectRowUserEvent === "function"){
							if(onSelectRowUserEvent(rowid, status) === false){
								return false;
							}  
						} else if (typeof onSelectRowUserEvent === "object"){
							if (onSelectRowUserEvent.pre !== undefined && onSelectRowUserEvent.pre(rowid, status) === false){return false;};
							if ($("#"+$(this).attr("id")).data("defaultEventFunctions").onSelectRow_default(rowid, status) === false){return false;};
							if (onSelectRowUserEvent.post !== undefined){onSelectRowUserEvent.post(rowid, status);};
							return false;
						}   
					} 

					//Comportamiento por defecto del evento
					$("#"+$(this).attr("id")).data("defaultEventFunctions").onSelectRow_default(rowid, status);
				};
				
				/* Evento lanzado tras el click de ordenacion pero antes de la propia ordenacion */
				onSortColUserEvent = settings.onSortCol;
				settings.onSortCol = function(index, iCol, sortorder){
					// console.log('onSortCol');
					if(onSortColUserEvent !== undefined && onSortColUserEvent !== null){
						if (typeof onSortColUserEvent === "function"){
							if(onSortColUserEvent(index, iCol, sortorder) === false){
								return "stop";
							}  
						} else if (typeof onSortColUserEvent === "object"){
							if (onSortColUserEvent.pre !== undefined && onSortColUserEvent.pre(index, iCol, sortorder) === false){return "stop";};
							if ($("#"+$(this).attr("id")).data("defaultEventFunctions").onSortCol_default(index, iCol, sortorder) === "stop"){return false;};
							if (onSortColUserEvent.post !== undefined && onSortColUserEvent.post(index, iCol, sortorder) === false){return "stop";};
							return "stop";
						}   
					} 

					//Comportamiento por defecto del evento
					return($("#"+$(this).attr("id")).data("defaultEventFunctions").onSortCol_default(index, iCol, sortorder));
				};
				
				/* Evento cuando comienza el redimensionado */
				resizeStartUserEvent = settings.resizeStart;
				settings.resizeStart = function(event, index){
					// console.log('resizeStart');
					if(resizeStartUserEvent !== undefined && resizeStartUserEvent !== null){
						if (typeof resizeStartUserEvent === "function"){
							if(resizeStartUserEvent(event, index) === false){
								return false;
							}  
						} else if (typeof resizeStartUserEvent === "object"){
							if (resizeStartUserEvent.pre !== undefined && resizeStartUserEvent.pre(event, index) === false){return false;};
							if ($("#"+$(self).attr("id")).data("defaultEventFunctions").resizeStart_default(event, index) === false){return false;};
							if (resizeStartUserEvent.post !== undefined){resizeStartUserEvent.post(event, index);};
							return false;
						}   
					} 

					//Comportamiento por defecto del evento
					$("#"+$(self).attr("id")).data("defaultEventFunctions").resizeStart_default(event, index);
				};
				
				/* Evento cuando acaba el redimensionado */
				resizeStopUserEvent = settings.resizeStop;
				settings.resizeStop = function(newwidth, index){
					// console.log('resizeStop');
					if(resizeStopUserEvent !== undefined && resizeStopUserEvent !== null){
						if (typeof resizeStopUserEvent === "function"){
							if(resizeStopUserEvent(newwidth, index) === false){
								return false;
							}  
						} else if (typeof resizeStopUserEvent === "object"){
							if (resizeStopUserEvent.pre !== undefined && resizeStopUserEvent.pre(newwidth, index) === false){return false;};
							if ($("#"+$(self).attr("id")).data("defaultEventFunctions").resizeStop_default(newwidth, index) === false){return false;};
							if (resizeStopUserEvent.post !== undefined){resizeStopUserEvent.post(newwidth, index);};
							return false;
						}   
					} 

					//Comportamiento por defecto del evento
					$("#"+$(self).attr("id")).data("defaultEventFunctions").resizeStop_default(newwidth, index);
				};

				
				//************************************************
				//   COMPORTAMIENTOS POR DEFECTO DE LOS EVENTOS
				//************************************************
				
				// Declaracion de las funciones por defectos de los eventos del grid  
				$("#"+$(this).attr("id")).data("defaultEventFunctions",{
					
					// Comportamiento por defecto del evento 'beforeRequest'
					beforeRequest_default: function(){
						var url = $(this).rup_grid("getGridParam","url");
						$(this).rup_grid("setGridParam",{url:$.rup_utils.setNoPortalParam(url)});
						return true;
					}
					
					// Comportamiento por defecto del evento 'serializeGridData'
					,serializeGridData_default: function(postData){
						if (postData.page!== undefined && postData.page !== null && Number(postData.page) > self.rup_grid("getGridParam","lastpage") && self.rup_grid("getGridParam","lastpage") > 0){//pq si laspage es 0 es la primera vez
							postData.page = self.rup_grid("getGridParam","lastpage");
						}
						return postData;
					}
					
					// Comportamiento por defecto del evento 'loadBeforeSend'
					,loadBeforeSend_default: function(xhr, settings){
						xhr.setRequestHeader("JQGridModel", "true");
						xhr.setRequestHeader("Content-Type", "application/json");
						return true;
					}
					
					// Comportamiento por defecto del evento 'loadError'
					,loadError_default: function(xhr, status, error){
						self.rup_grid("clearGridData");
						
						if ($.data(self[0],"maintName") && $.data(self[0],"maintName") !== null && $.data(self[0],"maintName") !== "") {//es que tengo un maint asociado
							$("#" + $.data(self[0],"maintName"))[0].prop.feedback.rup_feedback("option", "closeLink", true);
							$("#" + $.data(self[0],"maintName"))[0].prop.feedback.rup_feedback("option", "delay", null);
							$("#" + $.data(self[0],"maintName"))[0].prop.feedback.rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_grid.errors.errorOnGet"), "error");
							$("#" + $.data(self[0],"maintName"))[0].prop.feedback.rup_feedback("option", "delay", 1000);
						}else{
							$("#rup_feedback_" + self[0].id).rup_feedback("option", "closeLink", true);
							$("#rup_feedback_" + self[0].id).rup_feedback("option", "delay", null);
							$("#rup_feedback_" + self[0].id).rup_feedback("set", $.rup.i18nParse($.rup.i18n.base,"rup_grid.errors.errorOnGet"), "error");
							$("#rup_feedback_" + self[0].id).rup_feedback("option", "delay", 1000);
						}
					}
					
					// Comportamiento por defecto del evento 'beforeProcessing'
					,beforeProcessing_default: function(data , status , xhr){
						
					}
					
					// Comportamiento por defecto del evento 'gridComplete'
					,gridComplete_default: function(){
						var rowid = null, launchSelectEvent = true, id;
						//si esta el seleccionar todos activo lo borro
						if ($("#rup_feedback_" + self[0].id)) {
							$("#rup_feedback_" + self[0].id).rup_feedback("close");
						}
						//estilos para poder poner el pijama
						//TODO: comprobar que no vuelva a poner los estilos cuando se hace el addRowData
						$('#' + self[0].id + ' tr:nth-child(even)').addClass("rup-grid_evenRow");
						$('#' + self[0].id + ' tr:nth-child(odd)').addClass("rup-grid_oddRow");
						//estilo para hacer el cellpadding de cada celda
						$(".ui-jqgrid tr.jqgrow td").addClass("rup-grid_cellPadding");
						if (self.jqGrid("getDataIDs").length === 0) {//es que no hay registros
							$(self.jqGrid("getGridParam", "pager")).hide();
							if ($("#RUP_GRID_" + self[0].id + "_noRecords").length === 0) {
							self.before(
							'<tr class="ui-widget-content jqgrow ui-row-ltr" role="row" id="RUP_GRID_' + self[0].id + '_noRecords" aria-selected="false"><td aria-describedby="RUP_GRID_' + self[0].id + '_NO_REGISTROS" title="' + $.rup.i18nParse($.rup.i18n.base,"rup_grid.noRecordsFound") + '" style="text-align: left;width:' + self.jqGrid("getGridParam", "width") + 'px;background:white;" role="gridcell"><div id="RUP_GRID_' + self[0].id + '_noRecord_ext" class="cellLayout" style="padding-left: 0.5em ! important;">' + $.rup.i18nParse($.rup.i18n.base,"rup_grid.noRecordsFound") + '</div></td></tr>');
							}
						} else {
							if ($("#RUP_GRID_" + self[0].id + "_noRecords").length) {//si tenemos la capa de no hay registros la borramos
								$("#RUP_GRID_" + self[0].id + "_noRecords").remove();
							}
							
//							if (self[0].rup_gridProps.sourceEvent) {
//								var mnt = $("#" + self[0].rup_gridProps.sourceEvent.parentMaintName);
//								var srcElem = $(self[0].rup_gridProps.sourceEvent.target);
//								if (self[0].rup_gridProps.sourceEvent.type === "click") {
//									if (self.jqGrid("getGridParam", "multiselect")) {
//										if (mnt[0].prop.currentSelectedRow===null){
//											if($.data(mnt[0].prop.jQueryGrid[0], "detailPagAction")==='back' || $.data(mnt[0].prop.jQueryGrid[0], "detailPagAction")==='last'){
//												mnt[0].prop.currentSelectedRow="p_" + self.jqGrid("getGridParam", "page") + ";" + "id_" + self.jqGrid("getDataIDs")[self.jqGrid("getDataIDs").length-1];
//											}else{
//												mnt[0].prop.currentSelectedRow="p_" + self.jqGrid("getGridParam", "page") + ";" + "id_" + self.jqGrid("getDataIDs")[0];
//											}
//										}
//										id = mnt[0].prop.currentSelectedRow.split(";")[1];
//										rowid = id.substring(3, id.length);
//										launchSelectEvent = false;
//									} else {								
//										if (srcElem[0].id.indexOf("forward_") === 0 || srcElem[0].id.indexOf("first_") === 0) {//si es la primera o el siguiente elemento tengo que coger el primero de la pagina
//											//si no es multiselect hago lo de antes
//											rowid = self.jqGrid("getDataIDs")[0];
//										} else {
//											rowid = self.jqGrid("getDataIDs")[self.jqGrid("getDataIDs").length-1];
//										}
//									}
//								}
//								self[0].rup_gridProps.sourceEvent=null;
//							}
							$(self.jqGrid("getGridParam", "pager")).show();
							//comprobar si tenemos que seleccionar todos
//							if ($.data(self[0] , "allSelected") && $.inArray(self.jqGrid("getGridParam", "page"), $.data(self[0] , "deSelectedPages"))) {
//								
//								$('#cb_'+$.jgrid.jqID(self[0].id), "#gbox_"+self[0].id).attr("checked", true);
//								//invocamos a la funcion de seleccionar todos en este caso del maint
//								if ($.isFunction(self[0].rup_gridProps.onAfterSelectAll)) { //realiza la acción de seleccionar la filas ya sean en multiseleccion o no
//									self[0].rup_gridProps.onAfterSelectAll.call(self, self.rup_grid("getDataIDs"), true, true);
//								}
//								//$('#cb_'+$.jgrid.jqID(self[0].id), "#gbox_"+self[0].id).trigger('click');
//								//$('#cb_'+$.jgrid.jqID(self[0].id), "#gbox_"+self[0].id).attr("checked", true);
//								
//							}
							if ($.isFunction(self[0].rup_gridProps.onAfterGridComplete)) { //realiza la acción de seleccionar la filas ya sean en multiseleccion o no
								self[0].rup_gridProps.onAfterGridComplete.call(self, rowid, launchSelectEvent);
							}
						}
					}
					
					// Comportamiento por defecto del evento 'loadComplete'
					,loadComplete_default: function(data){
						if (data.rows.length > 0 && data.rows[0].id instanceof Object){//tratamiento exclusivo para jpa
							for (var i = 0; i<data.rows.length;i++) {
								if (data.rows[i].id instanceof Object) {//es que estamos en jpa y traemos una clave compuesta
									data.rows[i]["JPA_ID"] = data.rows[i].id;
									delete data.rows[i].id;
								}
							}
							this.addJSONData(data);
						}
					}
					
					// Comportamiento por defecto del evento 'afterInsertRow'
					,afterInsertRow_default: function(rowid , rowdata, rowelem){
						
					}
					
					// Comportamiento por defecto del evento 'beforeSelectRow'
					,beforeSelectRow_default: function(rowid , e){
						return true;
					}
					
					// Comportamiento por defecto del evento 'onCellSelect'
					,onCellSelect_default: function(rowid , iCol, cellcontent, e){
					}
					
					// Comportamiento por defecto del evento 'ondblClickRow'
					,ondblClickRow_default: function(rowid, iRow, iCol, e){
						//$("body").data("e_click",false);
						//window.clearTimeout($("body").data("clicktimer"));
					}
					
					// Comportamiento por defecto del evento 'onHeaderClick'
					,onHeaderClick_default: function(gridState){
						
					}
					
					// Comportamiento por defecto del evento 'onPaging'
					,onPaging_default: function(pgButton){
						
						// Si el usuario ha introducido el número de página comprobamos su valor
						if (pgButton == 'user') {
							var requestedPage = parseInt($('td.pagControls .ui-pg-input',$("#gbox_"+self[0].id)).val());
							if(requestedPage === 0){
								//Si se selecciona la página 0 colocamos la navegación en la primera
								self.rup_grid("setGridParam",{page : 1});
							}else{
								// Si la página solicitada es mayor que la última se establece la navegación en la última página
								var lastPage = parseInt(self.rup_grid("getGridParam","lastpage"));
								if (requestedPage > lastPage) {
									self.rup_grid("setGridParam",{page : lastPage});
								}
							}
						}
						
						return "";
					}
					
					// Comportamiento por defecto del evento 'onRightClickRow'
					,onRightClickRow_default: function(rowid, iRow, iCol, e){
						
					}
					
					// Comportamiento por defecto del evento 'onSelectAll'
					,onSelectAll_default: function(aRowids, status){
						if ($.data(self[0] , "allSelected") === null || $.data(self[0] , "allSelected") === undefined) {
							
							// Se ha trasladado a la creación del grid para que el grid tenga uno siempre creado
			//					$("#rup_feedback_" + self[0].id).rup_feedback({
			//						closeLink: false,
			//						gotoTop: false,
			//						block: false
			//					});
							if (status) {//si hay que seleccionar
								// TODO Mira correctamente el numero de registro a seleccionar si ya hay alguno
								$("#rup_feedback_" + self[0].id).rup_feedback("set", 
										$.rup.i18nParse($.rup.i18n.base,"rup_grid.selectedElems") + "<b>" + aRowids.length + "</b>" + $.rup.i18nParse($.rup.i18n.base,"rup_grid.ofPage") +
										' <input id="rup_grid_' + self[0].id + '_selectAll" style="margin-left: 15px;" class="botonElementosRestantes" type="button" value="' + 
										$.rup.i18nParse($.rup.i18n.base,"rup_grid.selectAll") + " " + Number(self.rup_grid("getGridParam", "records") - aRowids.length) + " "  
										+ $.rup.i18nParse($.rup.i18n.base,"rup_grid.selectAllRest")+ '" alt="' + $.rup.i18nParse($.rup.i18n.base,"rup_grid.selectAll") + '">');
								$("#rup_grid_" + self[0].id + "_selectAll").bind("click", function (ev) {
									$.data(self[0] , "selectedRowsCont", self.rup_grid("getGridParam", "records"));
									$.data(self[0] , "allSelected", true);
									$("#rup_feedback_" + self[0].id).rup_feedback("close");
									if ($.isFunction(self[0].rup_gridProps.selectAllGetPrimaryKeys)) { //realiza la acción de seleccionar la filas ya sean en multiseleccion o no
										//creamos el array para las claves primarias
										//self[0].rup_gridProps.allPksArray = [];
										self[0].rup_gridProps.selectAllGetPrimaryKeys.call(self);
									}
									//se crear el array de deseleccionados a vacio
									$.data(self[0], "deSelectedPages", []);
								});
							} else {//si deseleccionamos todos
								$("#rup_feedback_" + self[0].id).rup_feedback("close");
							}
						} else if ($.data(self[0] , "allSelected") && !status) {//si tenemos todos seleccionados y estamos deseleccionando la pagina actual
							//si hay que deseleccionar todos los registros
							$("#rup_feedback_" + self[0].id).rup_feedback("close");
							
							if ($.data(self[0] , "allSelected") === true) {//Si hemos seleccionado el resto de elementos
								//Mostramos el feedback de deseleccionar los restantes
								$("#rup_feedback_" + self[0].id).rup_feedback("set", 
										$.rup.i18nParse($.rup.i18n.base,"rup_grid.deSelectedElems") + "<b>" + aRowids.length + "</b>" + $.rup.i18nParse($.rup.i18n.base,"rup_grid.ofPage") +
										' <input id="rup_grid_' + self[0].id + '_deSelectAll" style="margin-left: 15px;" class="botonElementosRestantes" type="button" value="' + 
										$.rup.i18nParse($.rup.i18n.base,"rup_grid.deSelectAll") + " " + Number($.data(self[0] , "selectedRowsCont") - aRowids.length) + " "  
										+ $.rup.i18nParse($.rup.i18n.base,"rup_grid.selectAllRest")+ '" alt="' + $.rup.i18nParse($.rup.i18n.base,"rup_grid.deSelectAll") + '">');
								//click del deseleccionar
								$("#rup_grid_" + self[0].id + "_deSelectAll").bind("click", function (ev) {
									$("#rup_feedback_" + self[0].id).rup_feedback("close");
									//inicializamos los valores de la multiseleccion
//									$.data(self[0] , "selectedRowsCont", 0);
//									$.data(self[0] , "allSelected", false);		
//									self[0].rup_gridProps.allPksArray = [];
//									$('#' + self[0].rup_gridProps.pagerName + '_left').html("0 " + $.rup.i18nParse($.rup.i18n.base,"rup_grid.pager.selected"));
									if ($.isFunction(self[0].rup_gridProps.onAfterSelectAll)) { //realiza la acción de seleccionar la filas ya sean en multiseleccion o no
										self[0].rup_gridProps.onDeSelectAllRows.call(self, status);
									}
								});
//								var deSelectedPages = $.data(self[0] , "deSelectedPages");
//								deSelectedPages.push(self.rup_grid("getGridParam", "page"));
//								$.data(self[0] , "deSelectedPages", deSelectedPages);
							}
						} else if($.data(self[0] , "allSelected") && select) {
							$("#rup_feedback_" + self[0].id).rup_feedback("close");//porque si seleccionas todas cuando no esta seleccionado y justo anteriormente has deseleccionado la pagina se queda la capa con los mensajitos
						}
						if ($.isFunction(self[0].rup_gridProps.onAfterSelectAll)) { //realiza la acción de seleccionar la filas ya sean en multiseleccion o no
							self[0].rup_gridProps.onAfterSelectAll.call(self, aRowids, status);
						}
					}
					
					// Comportamiento por defecto del evento 'onSelectRow'
					,onSelectRow_default: function(rowid, status){
						if ($.isFunction(self[0].rup_gridProps.onAfterSelectRow)) { //realiza la acción de seleccionar la filas ya sean en multiseleccion o no
							self[0].rup_gridProps.onAfterSelectRow.call(self, rowid, status);
						}
						//si esta el seleccionar todos activo lo borro
						if ($("#rup_feedback_" + self[0].id)) {
							$("#rup_feedback_" + self[0].id).rup_feedback("close");
						}
					}
					// Comportamiento por defecto del evento 'onSortCol'
					,onSortCol_default: function(index, iCol, sortorder){
						return "";
					}
					
					// Comportamiento por defecto del evento 'resizeStart'
					,resizeStart_default: function(event, index){//TODO meter el resize y el satrt stop;
						//Cursor cabecera [th]
						resize_cursors[0] = $('#gbox_' + self[0].id + ' .ui-jqgrid-htable th:eq(1)').css('cursor');
						//Si no tiene ordenación, tendrá 'auto' y se autoasigna 'col-resize' por tanto se pone 'default'.
						if (resize_cursors[0] === 'col-resize') { 
							resize_cursors[0] = 'default';
						}
						$('#gbox_' + self[0].id + ' .ui-jqgrid-htable th').css('cursor', 'col-resize');
						
						//Cursor capa global cabecera [div]
						resize_cursors[1] = $('#gbox_' + self[0].id + ' .ui-jqgrid-sortable').css('cursor');
						$('#gbox_' + self[0].id + ' .ui-jqgrid-sortable').css('cursor', 'col-resize');
						
						//Cursor capa texto cabecera [div]
						$('#gbox_' + self[0].id + ' .ui-jqgrid-sortable div').css('cursor', 'col-resize');
						
						//Cursor span ordenación
						$('#gbox_' + self[0].id + ' .ui-grid-ico-sort').css('cssText', "cursor: col-resize !important;");
					}
					
					// Comportamiento por defecto del evento 'resizeStop'
					,resizeStop_default: function(newwidth, index){
						//Restablecer cursores
						$('#gbox_' + self[0].id + ' .ui-jqgrid-htable th').css('cursor', resize_cursors[0]);
						$('#gbox_' + self[0].id + ' .ui-jqgrid-sortable').css('cursor', resize_cursors[1]);
						$('#gbox_' + self[0].id + ' .ui-jqgrid-sortable div').css('cursor', 'pointer');
						$('#gbox_' + self[0].id + ' .ui-grid-ico-sort').css('cssText', "cursor: pointer !important;");
					}
				});
				
				
				////////////////////////
				// Definicion del Grid		
				////////////////////////
				
				//Ajuste de la url para integrar el Grid con portales y para la carga inicial
				if (settings.loadOnStartUp && !settings.hasMaint){
					settings.url = $.rup_utils.setNoPortalParam(settings.url);
				} else {
					null;
				}
				
				if (settings.sortable!==false) {//si no es false ponemos lo del drag and drop si es false no lo ponemos
					//Drag and Drop de las columnas
					sortableUserProperty = settings.sortable;
					settings.sortable = { 
							update: function (permutations) {
								self.reorderColumns(permutations);
							}
					};
				}
				
				//Ajustes generales
				settings.jsonReader = {repeatitems: false, id: "rup_id"};
				settings.prmNames = {id:"rup_id"};
				settings.loadui = "block";
				settings.pager = $('#' + settings.pagerName);
				if(settings.datatype === undefined){
					if(settings.loadOnStartUp && !settings.hasMaint){
						settings.datatype = 'json';
					} else {
						settings.datatype = 'clientSide'; 
					}
				}
				
				//Añadimos las propiedades del grid al elemento HTML para poder acceder a ellas desde el mantenimiento
				self[0].rup_gridProps = settings;
				//para la multiselección
				self[0].rup_gridProps.allPksArray = [];
				
				$.data(self[0] , "selectedRowsCont", 0);
				
				//Se crea el GRID
				self.jqGrid(settings);
				
				//Especificacion asociadas al Grid con diseño liquido
				if(settings.fluidBaseLayer!==null && !settings.hasMaint ){
					//Llamada inicial
					$("#"+self[0].id).fluidGrid({
						base : settings.fluidBaseLayer,
						offset : settings.offset
					});
					//Definicion del evento
					$(window).resize(function(){
						$("#"+self[0].id).fluidGrid({
							base : settings.fluidBaseLayer,
							offset : settings.offset
						});
					});
				}
				
				/* Si se ha configurado el grid para que no realice una busqueda al inicio, 
				una vez realizada la creacion del jqgrid, se vuelven a configurar los parametros url y datatype del grid 
				*/
				if (!settings.loadOnStartUp){
					self.setGridParam({url: (!settings.hasMaint ? settings.url : null),datatype:(!settings.hasMaint ? 'json': "clientSide")});
				}
				
				//$(".ui-jqgrid-title").text("");
				//***************************
				//   ESTILOS DE LA TABLA
				//***************************
				//se añade el feedback de la tabla
				$("<div/>").attr("id", "rup_feedback_" + self[0].id).insertBefore('#gbox_' + self[0].id);
				
				//Si la fila es ordenable cambiamos el cursor de 'pointer' a 'move' sino a 'default'
				if ($("#" + self[0].id).getGridParam('sortable')) {
					$('#gview_' + self[0].id + ' .ui-jqgrid-sortable').css("cursor", "move");
				} else {
					$('#gview_' + self[0].id + ' .ui-jqgrid-sortable').css("cursor", "default");
				}
				
				$("#rup_feedback_" + self[0].id).rup_feedback({
					closeLink: false,
					gotoTop: false,
					block: false
				});
	
				//Wrappear cada texto de la cabecera en un div
				$.each( $('#gview_' + self[0].id + ' .ui-jqgrid-sortable'), function (index, element){
						text = $(element).text();
						child = $(element).children();
						$(element).text("")
								.prepend($('<div />').css("cursor", "pointer").css("display","inline-table").html(text))
								.append(child);
				});
	
				//Creamos un tooltip para los titles de las cabeceras
				$('#gview_' + self[0].id + ' thead th[title]').rup_tooltip();
				
				//***************************
				//   PAGINADOR DE LA TABLA
				//***************************
				$('#'+settings.pagerName).css('height','auto'); //Posibilitar redimensionar paginador
			
				//Añadir clase a cada parte del paginador
				$('#'+settings.pagerName+'_left').addClass("pager_left");
				$('#'+settings.pagerName+'_center').addClass("pager_center");
				$('#'+settings.pagerName+'_right').addClass("pager_right");
				
				//pager_left
				//**********
				//Quitar posibles botones del paginador (y dejar la parte izquierda vacía)
				$('#' + settings.pagerName + '_left').html("");
			
				//Contador de seleccionados
				if (settings.multiselect === true){
					$('#' + settings.pagerName + '_left').append( $('<div/>').addClass('ui-paging-selected').html("0 " + $.rup.i18nParse($.rup.i18n.base,"rup_grid.pager.selected")));
				} 
			
				//pager_center
				//************
				$('#' + settings.pagerName + ' .pager_center table td').addClass('pagControls');
			
				//Cambiar flechas paginación por literales
				$('#' + settings.pagerName + '_center .ui-pg-table #first_'+ settings.pagerName)
					.html($('<span />').html($.rup.i18nParse($.rup.i18n.base,"rup_grid.pager.primPag")))
					.addClass('linkPaginacion')
					.removeClass('ui-pg-button');
				$('#' + settings.pagerName + '_center .ui-pg-table #prev_'+ settings.pagerName)
					.html($('<span />').html($.rup.i18nParse($.rup.i18n.base,"rup_grid.pager.anterior")))
					.addClass('linkPaginacion')
					.removeClass('ui-pg-button');
				$('#' + settings.pagerName + '_center .ui-pg-table #next_'+ settings.pagerName)
					.html($('<span />').html($.rup.i18nParse($.rup.i18n.base,"rup_grid.pager.siguiente")))
					.addClass('linkPaginacion')
					.removeClass('ui-pg-button');
				$('#' + settings.pagerName + '_center .ui-pg-table #last_'+ settings.pagerName)
					.html($('<span />').html($.rup.i18nParse($.rup.i18n.base,"rup_grid.pager.ultiPag")))
					.addClass('linkPaginacion')
					.removeClass('ui-pg-button');
		
				//Sólo permitimos introducir números en el campo de paginación
				$('td.pagControls .ui-pg-input',$("#gbox_"+self[0].id)).numeric(false);
				
				//En caso de tabla pequeña modificar los elementos y ponerlos en 2 filas
				porcentaje = settings.width*100/screen.width;
				if (porcentaje<65){
					//Añadir nueva fila
					$('#' + settings.pagerName + ' tr:eq(0)').parent().append($('<tr/>').attr('id', settings.pagerName + "_pagerNewLine"));
					
					//Mover pager_left y pager_right
					if (settings.multiselect === true){
						$('#' +settings.pagerName + '_pagerNewLine').append($('#'+settings.pagerName+' #'+settings.pagerName+'_left'));
						$('#' +settings.pagerName + '_center').attr("colspan","2");
					} else {
						$('#'+settings.pagerName + '_left').remove();
					}	
					$('#'+settings.pagerName + '_pagerNewLine').append($('#' + settings.pagerName + '_right'));
				}
				//sobre escribir el click del check
				if (settings.multiselect === true) {
					
					$('#cb_'+$.jgrid.jqID(self[0].id), "#gbox_"+self[0].id).unbind("click");
					$('#cb_'+$.jgrid.jqID(self[0].id), "#gbox_"+self[0].id).bind('click',function(){
						if (this.checked) {
							self[0].p.selarrrow = [];//esto es lo que estaba mal no reiniciaba las filas seleccionadas entonces en el selectAll habia todas las seleccioandas anteriormente y todas las filas
							$("[id^=jqg_" + self[0].id + "_" + "]").attr("checked", "checked");
							$(self[0].rows).each(function(i) {
								if ( i>0 ) {
									if(!$(this).hasClass("subgrid") && !$(this).hasClass("jqgroup")){
										$(this).addClass("ui-state-highlight").attr("aria-selected","true");
										self[0].p.selarrrow.push(this.id);
										self[0].p.selrow = this.id;
									}
								}
							});
							chk=true;
							emp=[];
						} else {
							$("[id^=jqg_"+self[0].p.id+"_"+"]").removeAttr("checked");
							$(self[0].rows).each(function(i) {
								if(i>0) {
									if(!$(this).hasClass("subgrid")){
										$(this).removeClass("ui-state-highlight").attr("aria-selected","false");
										emp.push(this.id);
									}
								}
							});
							self[0].p.selarrrow = []; self[0].p.selrow = null;
							chk=false;
						}
						if($.isFunction(self[0].p.onSelectAll)) {self[0].p.onSelectAll.call(self[0], chk ? self[0].p.selarrrow : emp,chk);}
					});
				}
		
			}
		}
	});
	
	
	
	//*******************************************************
	// DEFINICIÓN DE LA CONFIGURACION POR DEFECTO DEL PATRON  
	//*******************************************************
	
	$.fn.rup_grid.defaults = {
		altclass: "",
		altRows: false,
		autoencode: true,
		botones: null,
		checkMultiBoxOnly: false,
		colModel: [],
		colNames: [],
		editable: false,
		editurl: null,
		filterParameters: null,
		fluidBaseLayer: null,
		grouping: false,
		groupingView : {
			groupDataSorted : true,
			groupText : ['<b>{0} - {1} '+$.rup.i18nParse($.rup.i18n.base,"rup_maint.elements")+'</b>']
		},
		hasMaint: false,
		height: 'auto',
		imgpath: '',
		loadOnStartUp: true,
		menuContextual: null,
		multiboxonly: false,
		multiselect: false,
		multiselectWidth: 40,
		noMulticheckSelection: false,
		offset:0,
		pagerName: 'pager',
		rowList: [10, 20, 30],
		rowNum: 10,
		searchOnEnter: true,
		sortable: true,
		sortname: null,
		sortorder: "asc",
		tableclose: true,
		treeGrid: false,
		headertitles: false,
		treedatatype: "json",
		url: '',
		viewrecords: true,
		width: 800
	};
	
})(jQuery);