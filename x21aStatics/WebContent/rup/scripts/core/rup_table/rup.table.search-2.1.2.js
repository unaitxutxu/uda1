/*!
 * Copyright 2013 E.J.I.E., S.A.
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

	jQuery.rup_table.registerPlugin("search",{
		loadOrder:8,
		preConfiguration: function(settings){
			var $self = this;
			return $self.rup_table("preConfigureSearch", settings);
		},
		postConfiguration: function(settings){
			var $self = this;
			return $self.rup_table("postConfigureSearch", settings);
			
		}
	});
	
	//********************************
	// DEFINICIÓN DE MÉTODOS PÚBLICOS
	//********************************
	
	/**
	 * Extensión del componente rup_table para permitir la edición de los registros mediante un formulario. 
	 * 
	 * Los métodos implementados son:
	 * 
	 * configureDetailForm(settings): Realiza la configuración interna necesaria para la gestión correcta de la edición mediante un formulario.
	 * deleteElement(rowId, options): Realiza el borrado de un registro determinado.
	 * editElement(rowId, options): Lanza la edición de un registro medainte un formulario de detalle.
	 * newElement(): Inicia el proceso de inserción de un nuevo registro.
	 * showServerValidationFieldErrors(errors): Función encargada de mostrar los errores producidos en la gestión de los datos del mantenimiento.
	 * 
	 * Las propiedades de esta extensión almacenadas en el settings son las siguientes:
	 * 
	 * settings.$detailForm : Referencia al formulario de detalle mediante el que se realizan las modificaciones e inserciones de registros.
	 * settings.$detailFormDiv : Referencia al div que arropa el formulario de detalle y sobre el que se inicializa el componente rup_dialog. 
	 *  
	 */
	jQuery.fn.rup_table("extend",{
		preConfigureSearch: function(settings){
			// Añadimos la columna por defecto para mostrar la información de registros encontrados
			settings.colNames = $.merge([""], settings.colNames);
			settings.colModel = $.merge([settings.search.defaultSearchInfoCol], settings.colModel);
		},
		/*
		 * Realiza la configuración interna necesaria para la gestión correcta de la edición mediante un formulario.
		 * 
		 * TODO: internacionalizar mensajes de error.
		 */
		postConfigureSearch: function(settings){
			var $self = this;
			
			$self.rup_table("createSearchRow", settings);
			$self._initializeSearchProps(settings);
			
			$self.on({
				"jqGridLoadComplete.rupTable.search": function(data){
					var settings = $self.data("settings"),
					page = parseInt($self.rup_table("getGridParam", "page"),10);
					
					
					if($self._hasPageMatchedElements(page)){
						$self.rup_table("highlightMatchedRowsInPage", page);
//						// TODO: Generalizar
//						$self.find("td[aria-describedby='"+settings.id+"_infoSearch'] img.ui-icon.ui-icon-search").remove();
//						for (var i=0;i<settings.search.matchedRowsPerPage[page].length;i++){
//							newIndexPos = settings.search.matchedRowsPerPage[page][i];
//							$($self.jqGrid("getInd",newIndexPos, true)).find("td[aria-describedby='"+settings.id+"_infoSearch']").html($("<img/>").addClass("ui-icon ui-icon-search")[0]);
//						}
					}
				},
				"jqGridSelectRow.rupTable.search": function(event, id, status, obj){
					$self.rup_table("updateSearchPagination", id);
				}
			});
			
		}
	});
	
	
	jQuery.fn.rup_table("extend",{
		toggleSearchForm: function(){
			var $self = this, settings = $self.data("settings");
			
			if (!settings.search.created){
				$self.jqGrid("filterToolbar", settings.search);
				settings.search.$collapseIcon.removeClass("ui-icon-triangle-1-e");
				settings.search.$collapseIcon.addClass("ui-icon-triangle-1-s");
				settings.search.created = true;
				jQuery("input","table thead tr.ui-search-toolbar").keypress(function(e){
					var key = e.charCode || e.keyCode || 0;
					if(key == 13){
						$self.rup_table("doSearch");
						return false;
					}
					return this;
				});
			}else{
				$self[0].toggleToolbar();
				if(jQuery("table thead tr.ui-search-toolbar","#gview_"+settings.id).is(":visible")){
					settings.search.$collapseIcon.removeClass("ui-icon-triangle-1-e");
					settings.search.$collapseIcon.addClass("ui-icon-triangle-1-s");
				}else{
					settings.search.$collapseIcon.removeClass("ui-icon-triangle-1-s");
					settings.search.$collapseIcon.addClass("ui-icon-triangle-1-e");
				}
				
			}
		},
		createSearchRow: function(settings){
			var $self = this, 
			$gridHead = jQuery("table thead","#gview_"+settings.id),
			// Templates
			searchRowHeaderTmpl = jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.templates.search.searchRowHeader"),
			collapseLayerTmpl = jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.templates.search.collapseLayer"),
			collapseIconTmpl = jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.templates.search.collapseIcon"),
			collapseLabelTmpl = jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.templates.search.collapseLabel"),
			matchedLayerTmpl = jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.templates.search.matchedLayer"),
			matchedLabelTmpl = jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.templates.search.matchedLabel"),
			navLayerTmpl = jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.templates.search.navLayer"),
			navLinkTmpl = jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.templates.search.navLink"),
			navSearchButtonTmpl = jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.templates.search.navSearchButton"),
			navClearLinkTmpl = jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.templates.search.navClearLink"),
			
			// Objetos
			$searchRow = $(jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.templates.search.searchRow")),
			$searchRowHeader = $(jQuery.jgrid.format(searchRowHeaderTmpl, $gridHead.find("th").length)),
			// Capa que controla el colapso del formualario
			$collapseLayer = $(jQuery.jgrid.format(collapseLayerTmpl, "searchCollapseLayer_"+settings.id)),
			$collapseIcon = $(jQuery.jgrid.format(collapseIconTmpl, "searchCollapseIcon_"+settings.id)),
			$collapseLabel = $(jQuery.jgrid.format(collapseLabelTmpl, "searchCollapsLabel_"+settings.id, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.plugins.search.searchCriteria")));
			// Capa que muestra el número de ocurrencias
			$matchedLayer = $(jQuery.jgrid.format(matchedLayerTmpl, "matchedLayer_"+settings.id)),
			$matchedLabel = $(jQuery.jgrid.format(matchedLabelTmpl, "matchedLabel_"+settings.id, jQuery.jgrid.format(jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.plugins.search.matchedRecords"),0))),
			
			// Capa que controla la navegación entre las diferentes ocurrencias
			$navLayer = $(jQuery.jgrid.format(navLayerTmpl, "searchNavLayer_"+settings.id)),
			$firstNavLink = $(jQuery.jgrid.format(navLinkTmpl, 'search_nav_first_'+settings.id, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.first"))),
			$backNavLink = $(jQuery.jgrid.format(navLinkTmpl, 'search_nav_back_'+settings.id, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.previous"))),
			$forwardNavLink = $(jQuery.jgrid.format(navLinkTmpl, 'search_nav_forward_'+settings.id, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.next"))),
			$lastNavLink = $(jQuery.jgrid.format(navLinkTmpl, 'search_nav_last_'+settings.id, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.last"))),
			$navSearchButton = $(jQuery.jgrid.format(navSearchButtonTmpl, 'search_nav_button_'+settings.id, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.search.Find")));
			$navClearLink = $(jQuery.jgrid.format(navClearLinkTmpl, 'search_nav_clear_link'+settings.id, jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.search.Reset")));

			// Construcción del objeto final
			$collapseLayer.append($collapseIcon).append($collapseLabel);
			$matchedLayer.append($matchedLabel);
			$navLayer.append($firstNavLink).append($backNavLink).append($forwardNavLink).append($lastNavLink).append($navSearchButton).append($navClearLink);
			
			$searchRowHeader.append($collapseLayer);
			$searchRowHeader.append($matchedLayer);
			$searchRowHeader.append($navLayer);
			
			$searchRow.append($searchRowHeader);
			
			$gridHead.append($searchRow);
			
			settings.search = settings.search || {};
			
			settings.search.created = false;
			settings.search.url = settings.search.url || settings.url+"/search";
			
			settings.search.$collapseIcon = $collapseIcon;
			settings.search.$searchRow = $searchRow;
			settings.search.$matchedLabel = $matchedLabel;
			settings.search.$firstNavLink = $firstNavLink;
			settings.search.$backNavLink = $backNavLink;
			settings.search.$forwardNavLink = $forwardNavLink;
			settings.search.$lastNavLink = $lastNavLink;
			
			// Creacion del enlace de mostrar/ocultar el formulario
			$collapseIcon.add($collapseLabel).on("click", function(){
				$self.rup_table("toggleSearchForm");
			});
			
			// Evento de búsqueda asociado al botón
			$navSearchButton.on("click", function(){
				$self.rup_table("doSearch");
			});
			
			// Evento asociado a limpiar el fomulario de búsqueda
			$navClearLink.on("click", function(){
				$self.rup_table("clearSearch");
			});
			
			function doSearchLinkNavigation($link, linkId){
				if (!$link.hasClass("ui-state-disabled")){
					$self.rup_table("navigateToMatchedRow", linkId);
				}
			}
			
			// Elemento primero
			$firstNavLink.on("click", function(){
				doSearchLinkNavigation(jQuery(this), 'first');
			});
			
			// Elemento anterior
			$backNavLink.on("click", function(){
				doSearchLinkNavigation(jQuery(this), 'prev');
			});
			
			// Elemento siguiente
			$forwardNavLink.on("click", function(){
				doSearchLinkNavigation(jQuery(this), 'next');
			});
			
			// Elemento ultimo
			$lastNavLink.on("click", function(){
				doSearchLinkNavigation(jQuery(this), 'last');
			});
			
		},
		navigateToMatchedRow: function(matchedRow){
			var $self = this, retNavParams  = $self.rup_table("fncGetSearchNavigationParams", matchedRow);
			$self.rup_table("doSearchNavigation", retNavParams);
		},
		doSearch: function(){
			var $self = this, settings = $self.data("settings"),jsonData={},
			page = parseInt($self.rup_table("getGridParam", "page"),10);
			jsonData.filterParams =$self.rup_table("getGridParam","postData"),
			jsonData.searchParams =form2object(jQuery("table thead tr.ui-search-toolbar","#gview_"+settings.id)[0]);
			$self._initializeSearchProps(settings);
			jQuery.rup_ajax({
				url:settings.search.url,
				type:"POST",
				dataType:"json",
				data: jQuery.toJSON(jsonData),
				contentType: 'application/json', 
				success: function(xhr,b,c){
					rowsPerPage = parseInt($self.rup_table("getGridParam", "rowNum"),10);
					
					if (xhr.length===0){
						$self._initializeSearchProps(settings);
						$self.rup_table("updateSearchPagination");
						$self.rup_table("clearHighlightedMatchedRows");
					}else{
						jQuery.each(xhr, function(index, elem){
							elem.page = Math.ceil(elem.rowNum / rowsPerPage);
							elem.lineNum = elem.rowNum - ((elem.page-1)*rowsPerPage);
							$self._processMatchedRow(settings, elem);
						});
						$self.trigger("rupTableSearchSuccess");
						$self.rup_table("goToFirstMatched", page);
					}
				}
			});
		},
		goToFirstMatched: function(paramPage){
			var $self = this, settings = $self.data("settings"), newIndexPos,
			page = (typeof paramPage ==="string"?parseInt(paramPage,10):paramPage);
			
			if ($self._hasPageMatchedElements(page)){
				// TODO: Generalizar
//				$self.find("td[aria-describedby='"+settings.id+"_infoSearch'] img.ui-icon.ui-icon-search").remove();
//				for (var i=0;i<settings.search.matchedRowsPerPage[page].length;i++){
//					newIndexPos = settings.search.matchedRowsPerPage[page][i];
//					$($self.jqGrid("getInd",newIndexPos, true)).find("td[aria-describedby='"+settings.id+"_infoSearch']").html($("<img/>").addClass("ui-icon ui-icon-search")[0]);
//				}
				
				$self.rup_table("highlightMatchedRowsInPage", page);
				
				$self.jqGrid("setSelection", settings.search.matchedRowsPerPage[page][0], false);
			}else{
				$self.rup_table("navigateToMatchedRow", 'first');
			}
			
			
		},
//		updateMatchedNumber:function(){
//			var $self = this, settings = $self.data("settings");
//			
//			if (settings.search && settings.search.numMatched){
//				settings.search.$matchedLabel.html(jQuery.jgrid.format(jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.plugins.search.matchedRecords"),settings.search.numMatched));
//			}
//		},
		fncGetSearchNavigationParams : function(linkType ){
			var $self = this, settings = $self.data("settings"), execute = false, changePage = false, index=0, newPageIndex=0,
			npos = jQuery.proxy(jQuery.jgrid.getCurrPos, $self[0])(),
			page = parseInt($self.rup_table("getGridParam", "page"),10),
			newPage=page,
			lastPage = parseInt(Math.ceil($self.rup_table("getGridParam", "records")/$self.rup_table("getGridParam", "rowNum")),10),
			currentArrayIndex, selectedLines, pageArrayIndex;
			
			$self.trigger("rupTableAfterSearchNav",[linkType]);
			
			npos[0] = parseInt(npos[0],10);
			$("#FormError", settings.$detailForm).hide();
			switch (linkType){
				case 'first':
					// Navegar al primer elemento 
					execute = true;
					// Si no se han seleccionado todos los elementos
					// Se comprueba si la página en la que nos encontramos es la primera en la que se han seleccionado registros
					if (settings.search.matchedPages[0]!==page){
						// Marcamos el flag changePage para indicar que se debe de realizar una paginación
						changePage = true;
						// La nueva página será la primera página en la que se ha realizado una selección de registros
						newPage = settings.search.matchedPages[0];
					}
					// Recuperamos el primer registro seleccionado del la página
					index = settings.search.matchedLinesPerPage[newPage][0];
					newPageIndex = index;
					break;
				case 'prev':
					// Navegar al anterior elemento seleccionado
					execute = true;
					// Obtenemos un array con los index de los registros seleccionados en la página actual
					selectedLines = settings.search.matchedLinesPerPage[page];
					// Obtenemos la posición que ocupa el elemento actual en el array de seleccionados
					currentArrayIndex = $.inArray(npos[0]+1,selectedLines);
					
					// La línea no se encuentra entre los registros que se corresponden a la búsqueda
					if (currentArrayIndex===-1){
						currentArrayIndex = $.rup_utils.insertSorted($.merge([],selectedLines), npos[0]+1);
						if(currentArrayIndex>1){
							currentArrayIndex--;
						}
					}
					
					// Se comprueba si ocupa el lugar del primer elemento seleccionado
					if (currentArrayIndex===0){
						// En caso de tratarse del primer elemento seleccionado de la página, se deberá de realizar una navegación a la página anterior que disponga de elementos seleccionados
						changePage = true;
						pageArrayIndex = $.inArray(page, settings.search.matchedPages);
						// Recorremos las páginas anteriores
						newPage = settings.search.matchedPages[pageArrayIndex-1];
						// Obtenemos los identificadores de los registros seleccionados de la nueva página
						selectedLines = settings.search.matchedLinesPerPage[newPage];
						// Obtenemos el último registro seleccionado 
						index = selectedLines[selectedLines.length-1];
					}else{
						// En caso de no tratarse del último elemento de la página, recuperamos el elemento anterior que haya sido seleccionado también
						index = selectedLines[currentArrayIndex-1];
					}
					
					newPageIndex = index;
					break;
				case 'next':
					// Navegar al siguiente elemento seleccionado
					execute = true;
					// Obtenemos un array con los index de los registros seleccionados en la página actual
					selectedLines = settings.search.matchedLinesPerPage[page];
					// Obtenemos la posición que ocupa el elemento actual en el array de seleccionados
					currentArrayIndex = $.inArray(npos[0]+1,selectedLines);
					
					// La línea no se encuentra entre los registros que se corresponden a la búsqueda
					if (currentArrayIndex===-1){
						currentArrayIndex = $.rup_utils.insertSorted($.merge([],selectedLines), npos[0]+1);
						currentArrayIndex--;
					}

					// Se comprueba si ocupa el lugar del último elemento seleccionado
					if (currentArrayIndex===selectedLines.length-1){
						// En caso de tratarse del primer elemento seleccionado de la página, se deberá de realizar una navegación a la página anterior que disponga de elementos seleccionados
						changePage = true;
						pageArrayIndex = $.inArray(page, settings.search.matchedPages);
						// Recorremos las páginas anteriores
						newPage = settings.search.matchedPages[pageArrayIndex+1];
						// Obtenemos los identificadores de los registros seleccionados de la nueva página
						selectedLines = settings.search.matchedLinesPerPage[newPage];
						// Obtenemos el primer registro de la página 
						index = selectedLines[0];
					}else{
						// En caso de no tratarse del último elemento de la página, recuperamos el elemento anterior que haya sido seleccionado también
						index = selectedLines[currentArrayIndex+1];
					}
					
					newPageIndex = index;
					break;
				case 'last':
					// Navegar al primer elemento 
					execute = true;
					// Si no se han seleccionado todos los elementos
					// Se comprueba si la página en la que nos encontramos es la primera en la que se han seleccionado registros
					if (settings.search.matchedPages[settings.search.matchedPages.length-1]!==page){
						// Marcamos el flag changePage para indicar que se debe de realizar una paginación
						changePage = true;
						// La nueva página será la primera página en la que se ha realizado una selección de registros
						newPage = settings.search.matchedPages[settings.search.matchedPages.length-1];
					}
					// Recuperamos el primer registro seleccionado del la página
					index = settings.search.matchedLinesPerPage[newPage][settings.search.matchedLinesPerPage[newPage].length-1];
					newPageIndex = index;
					break;
			}
			
			return [linkType, execute, changePage, index-1, npos, newPage, newPageIndex-1];
		},
		doSearchNavigation: function(arrParams, execute, changePage, index, npos, newPage, newPageIndex ){
			var $self = this, settings = $self.data("settings"), props = rp_ge[$self.attr("id")],
			linkType, execute, changePage, index, npos, newPage, newPageIndex, fncAfterclickPgButtons, indexAux, $row, pagePos;
			
			if ($.isArray(arrParams)){
				linkType = arrParams[0];
				execute = arrParams[1];
				changePage = arrParams[2];
				index = arrParams[3];
				npos = arrParams[4];
				newPage = arrParams[5];
				newPageIndex = arrParams[6];
				
				if (execute){
					$self.rup_table("hideFormErrors", settings.$detailForm);
//					$self.triggerHandler("jqGridAddEditClickPgButtons", [linkType, settings.$detailForm, npos[1][npos[index]]]);
					pagePos = jQuery.proxy(jQuery.jgrid.getCurrPos, $self[0])();
					if (changePage){
//						$self.jqGrid("setSelection", pagePos[1][pagePos[0]], false);
						$self.trigger("reloadGrid",[{page: newPage}]);
						$self.on("jqGridAfterLoadComplete.rupTable.serach.pagination",function(event,data){
							indexAux = jQuery.inArray(newPageIndex+1, settings.search.matchedLinesPerPage[newPage]);
//							$row = jQuery($self.jqGrid("getInd",settings.search.matchedRowsPerPage[parseInt(data.page,10)][indexAux],true));
//							if ($row.attr("aria-selected")!=="true"){
								$self.jqGrid("setSelection", settings.search.matchedRowsPerPage[parseInt(data.page,10)][indexAux], false);
//							}else{
//								$self.rup_table("highlightRowAsSelected", $row);
//							}
							$self.off("jqGridAfterLoadComplete.rupTable.serach.pagination");
						});
					}else{
						indexAux = jQuery.inArray(index+1, settings.search.matchedLinesPerPage[newPage]);
//						$self.jqGrid("setSelection", pagePos[1][pagePos[0]], false);
//						$row = jQuery($self.jqGrid("getInd",settings.search.matchedRowsPerPage[newPage][indexAux],true));
//						if ($row.attr("aria-selected")!=="true"){
							$self.jqGrid("setSelection", settings.search.matchedRowsPerPage[newPage][indexAux], false);
//						}else{
//							$self.rup_table("highlightRowAsSelected", $row);
//						}
					}
//					$self.triggerHandler("jqGridAddEditAfterClickPgButtons", [linkType,settings.$detailForm,npos[1][npos[index]]]);
//					fncAfterclickPgButtons = (props!==undefined?props.afterclickPgButtons:settings.afterclickPgButtons);
//					if(jQuery.isFunction(fncAfterclickPgButtons)) {
//						props.fncAfterclickPgButtons.call($self, linkType, settings.$detailForm,npos[1][npos[index]]);
//					}
				}
			}
		},
		clearSearch: function(){
			var $self = this, settings = $self.data("settings");
			$self._initializeSearchProps(settings);
			$self.rup_table("updateSearchPagination");
			$self.rup_table("clearHighlightedMatchedRows");
			jQuery("table thead tr.ui-search-toolbar input","#gview_"+settings.id).val("");
		},
		clearHighlightedMatchedRows: function(){
			var $self = this, settings = $self.data("settings");
			$self.find("td[aria-describedby='"+settings.id+"_infoSearch'] img.ui-icon.ui-icon-search").remove();
		},
		highlightMatchedRowsInPage:function(page){
			var $self = this, settings = $self.data("settings"), $row;
			
			$self.rup_table("clearHighlightedMatchedRows");
			
			for (var i=0;i<settings.search.matchedRowsPerPage[page].length;i++){
				newIndexPos = settings.search.matchedRowsPerPage[page][i];
				$row = $($self.jqGrid("getInd",newIndexPos, true));
				$self.rup_table("highlightMatchedRow", $row);
			}
		}, 
		highlightMatchedRow: function($row){
			var $self = this, settings = $self.data("settings");
			$row.find("td[aria-describedby='"+settings.id+"_infoSearch']").html($("<img/>").addClass("ui-icon ui-icon-search")[0]);
		},
		updateSearchPagination:function(paramRowId){
			var $self = this, settings = $self.data("settings"),
			rowId, pagePos, currentArrayIndex,
			page = parseInt($self.rup_table("getGridParam", "page"),10),
			numMatched;
			if (paramRowId!==undefined){
				rowId = paramRowId;
			}else{
				pagePos = jQuery.proxy(jQuery.jgrid.getCurrPos, $self[0])();
				rowId = (pagePos[0]!==-1?pagePos[1][pagePos[0]-1]:-1);
			}
			
			if (settings.search.numMatched===0){
				settings.search.$firstNavLink.add(settings.search.$backNavLink).add(settings.search.$forwardNavLink).add(settings.search.$lastNavLink).addClass("ui-state-disabled");
				settings.search.$matchedLabel.html(jQuery.jgrid.format(jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.plugins.search.matchedRecords"),"0"));
			}else if (rowId!==-1){
				// Comprobamos si el registro seleccionado es uno de los resultados de la busqueda
				if (jQuery.inArray(rowId, settings.search.matchedRowsPerPage[page])!==-1){
					// Calculamos el 
					numMatched = $self.rup_table("getSearchCurrentRowCount");
					
					if (settings.search && settings.search.numMatched){
						settings.search.$matchedLabel.html(jQuery.jgrid.format(jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.plugins.search.matchedRecordsCount"),numMatched, settings.search.numMatched));
					}
					
					if (numMatched===1){
						settings.search.$firstNavLink.addClass("ui-state-disabled");
						settings.search.$backNavLink.addClass("ui-state-disabled");
					}else{
						settings.search.$firstNavLink.removeClass("ui-state-disabled");
						settings.search.$backNavLink.removeClass("ui-state-disabled");
					}
					
					if (numMatched===settings.search.numMatched){
						settings.search.$lastNavLink.addClass("ui-state-disabled");
						settings.search.$forwardNavLink.addClass("ui-state-disabled");
					}else{
						settings.search.$lastNavLink.removeClass("ui-state-disabled");
						settings.search.$forwardNavLink.removeClass("ui-state-disabled");
					}
					
				}else{
					if (settings.search && settings.search.numMatched){
						settings.search.$matchedLabel.html(jQuery.jgrid.format(jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_table.plugins.search.matchedRecords"),settings.search.numMatched));
					}
					settings.search.$firstNavLink.removeClass("ui-state-disabled");
//					settings.search.$backNavLink.removeClass("ui-state-disabled");
					settings.search.$forwardNavLink.removeClass("ui-state-disabled");
					settings.search.$lastNavLink.removeClass("ui-state-disabled");
					
					// Miramos a ver si desde la posición actual hay anterior
					if (jQuery.inArray(settings.search.matchedPages, page) > 0){
						settings.search.$backNavLink.removeClass("ui-state-disabled");
					}else if (jQuery.inArray(page, settings.search.matchedPages) === -1 && $.rup_utils.insertSorted($.merge([],settings.search.matchedPages), page)===0){
						settings.search.$backNavLink.addClass("ui-state-disabled");
					}else if (jQuery.inArray(page, settings.search.matchedPages) === -1 && $.rup_utils.insertSorted($.merge([],settings.search.matchedPages), page)>0){
						settings.search.$backNavLink.removeClass("ui-state-disabled");
					}else{
						pagePos = jQuery.proxy(jQuery.jgrid.getCurrPos, $self[0])();
						currentArrayIndex = $.rup_utils.insertSorted($.merge([],settings.search.matchedLinesPerPage[page]), pagePos[0]+1);
						if (currentArrayIndex===0){
							settings.search.$backNavLink.addClass("ui-state-disabled");
						}else{
							settings.search.$backNavLink.removeClass("ui-state-disabled");
						}
					}
					
				}
			}
		},
		getSearchCurrentRowCount : function(){
			var $self = this, settings = $self.data("settings"),
			page = parseInt($self.rup_table("getGridParam", "page"),10),
			currentRow = jQuery.proxy(jQuery.jgrid.getCurrPos, $self[0])(),
			rowsPerPage = parseInt($self.rup_table("getGridParam", "rowNum"),10),
			selectedPagesArrayIndex,
			cont=0;
			
			// En caso de que no se hayan seleccionado
			// Se obtiene el indice de la página actual dentro del array de páginas deseleccionadas para  
			selectedPagesArrayIndex = jQuery.inArray(page, settings.search.matchedPages);
			
			for (var i=0;i<selectedPagesArrayIndex;i++){
				cont+=settings.search.matchedLinesPerPage[settings.search.matchedPages[i]].length;
			}
				
			cont+=$.inArray(currentRow[0]+1, settings.search.matchedLinesPerPage[settings.search.matchedPages[selectedPagesArrayIndex]])+1;
			
			return cont;
		}
	});
	
	jQuery.fn.rup_table("extend",{
		_hasPageMatchedElements: function(paramPage){
			var $self = this, settings = $self.data("settings"),
			page = (typeof paramPage ==="string"?parseInt(paramPage,10):paramPage);
			// Se comprueba si se han seleccionado todos los registros de la tabla
				// Comprobamos si en la página indicada se ha encontrado un elemento
			return (jQuery.inArray(page, settings.search.matchedPages)!== -1);
		},
		_initializeSearchProps: function(settings){
			// Se almacenan en los settings internos las estructuras de control de los registros seleccionados
			if (settings.search===undefined){
				settings.search={};
			}
			// Numero de registros encontrados
			settings.search.numMatched=0;
			// Propiedades 
			settings.search.matchedRowsPerPage=[];
			settings.search.matchedLinesPerPage=[];
			settings.search.matchedRows=[];
			settings.search.matchedIds=[];
			settings.search.matchedPages=[];
		},
		_processMatchedRow: function(settings, matchedElem){
			var $self = this, lineIndex;
			
			if (settings.search.matchedRowsPerPage[matchedElem.page]===undefined){
				settings.search.matchedRowsPerPage[matchedElem.page]=[];
				settings.search.matchedLinesPerPage[matchedElem.page]=[];
			}
			// Se almacena el Id del registro seleccionado
			if (jQuery.inArray(matchedElem.id, settings.search.matchedIds)===-1){
				settings.search.matchedIds.push(matchedElem.id);
				settings.search.matchedRows.push({id:matchedElem.id, page:matchedElem.page});
				lineIndex = $.rup_utils.insertSorted(settings.search.matchedLinesPerPage[matchedElem.page], matchedElem.lineNum);
				settings.search.matchedRowsPerPage[matchedElem.page].splice(lineIndex,0,matchedElem.id);
				if (settings.search.matchedRowsPerPage[matchedElem.page].length>0
						&& jQuery.inArray(parseInt(matchedElem.page,10), settings.search.matchedPages)===-1){
					$.rup_utils.insertSorted(settings.search.matchedPages, parseInt(matchedElem.page,10));
				}
				settings.search.numMatched++;
			}
		}
	});
		
	//*******************************************************
	// DEFINICIÓN DE LA CONFIGURACION POR DEFECTO DEL PATRON  
	//*******************************************************
	
		
	// Parámetros de configuración por defecto para la acción de eliminar un registro.
	jQuery.fn.rup_table.plugins.search = {};
	jQuery.fn.rup_table.plugins.search.defaults = {
			search:{
				autosearch: false,
				beforeSearch:function(){
					return true;
				},
				defaultSearchInfoCol:{
					name: "infoSearch", index: "infoSearch", editable:false, width:"15em", search:false
				},
				searchOnEnter:false
			}
//			bSubmit: jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_message.aceptar"),
//			cancelicon:[true, "left", "icono_cancelar"],
//			delicon:[false],
//			linkStyleButtons: ["#eData"],
//			msg: '<div id="rup_msgDIV_msg_icon" class="rup-message_icon-confirm"></div><div id="rup_msgDIV_msg" class="rup-message_msg-confirm white-space-normal">'+jQuery.rup.i18nParse(jQuery.rup.i18n.base,"rup_maint.deleteAll")+'</div>',
//			mtype:"DELETE",
//			reloadAfterSubmit:false, 
//			resize:false
	};
	
		
	
})(jQuery);