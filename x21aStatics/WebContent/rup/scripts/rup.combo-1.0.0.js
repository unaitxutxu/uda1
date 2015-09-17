//NO EDITAR

(function ($) {
	
	//****************************************************************************************************************
	// DEFINICIÓN BASE DEL PATRÁN (definición de la variable privada que contendrá los métodos y la función de jQuery)
	//****************************************************************************************************************
	
	var rup_combo = {};
	
	//Se configura el arranque de UDA para que alberge el nuevo patrón 
	$.extend($.rup.iniRup, $.rup.rupSelectorObjectConstructor("rup_combo", rup_combo));
	
	//*******************************
	// DEFINICIÓN DE MÉTODOS PÚBLICOS
	//*******************************
	$.fn.rup_combo("extend",{
		select : function(param){
			if (typeof param === "string" ){
				$(this).selectmenu("value", param);
			} else if(typeof param === "number" ){
				$(this).selectmenu("index", param);
			} else {
				$(this).selectmenu("index", 0);
			}
		},
		index : function(){
			return ($(this).selectmenu("index"));
		}, 
		disable : function(){
			$(this).selectmenu("disable");
		},
		enable : function(){
			$(this).selectmenu("enable");
		},
		isDisabled : function(){
			if ($(this).attr('aria-disabled') === 'false'){
				return false;
			} else {
				return true;
			}
		},
		disableChild : function(){
			//Vaciar combo, deshabilitarlo
			$(this).empty().selectmenu("disable");
			//Eliminar texto que se muestra
			$("#"+$(this).attr("id")+"-button span:first-child").text("");
			//Propagar evento de selección a hijos (recursivo)
			var hijos = $(this).data("childs");
			for (var i in hijos){
				$("#"+hijos[i]).rup_combo("disableChild");
			}
		},
		reload: function (id){
			var settings = $(this).data("settings"),
				source;
				
			//Vaciar combo, quitarle valor y deshabilitar
			$("#"+settings.id).rup_combo("disableChild");
			
			if (typeof settings.source === "object" || typeof settings.sourceGroup === "object"){
			//LOCAL
				source = settings.source[this._getParentsValues(settings.parent, false, settings.multiValueToken)];
				if (source!==undefined){
					//Parsear datos
					this._parseLOCAL(source, settings, $("#"+settings.id));
					
					//Crear combo
					this._makeCombo(settings);

					//Lanzar cambio para que se recarguen hijos
					$("#"+settings.id).selectmenu("change");
				}
			} else if (typeof settings.source === "string" || typeof settings.sourceGroup === "string"){
			//REMOTO
				var data = this._getParentsValues(settings.parent, true),
					rupCombo = this;
				if (data===null){ return false; } //Se para la petición porque algún padre no tiene el dato cargado
				$.ajax({
					url: settings.source,
					data : data,
					beforeSend: function (xhr){
						rupCombo._ajaxBeforeSend(xhr, settings);
					},
					success: function (data, textStatus, jqXHR){
						rupCombo._ajaxSuccess(data, settings, $("#"+settings.id));
					},
					error: rupCombo._ajaxError	
				})
				delete rupCombo;
			}
		},
		order: function (orderedByValue, skipFirst){
			var combo = $(this)
				options = $('option', combo),
				arrVals = []
				skippedValue = null;
			
			//Comprobar que se ha obtenido el combo deseado
			if (combo.length>0){
				
				//Guardar elemento seleccionado
				var selectedVal = combo.val();
				
				//Obtener elementos combo
				options.each(function(){
					 //Omitir posible opción vacía
					if (skipFirst){ 
						skipFirst = false;
						skippedValue = {
							val: $(this).val(),
					        text: $(this).text()
						};
						return true; 
					}
					arrVals.push({
						val: $(this).val(),
				        text: $(this).text(),
						clazz: $(this).attr('class')
					});
				});
				
				//Ordenar elementos (segun parametros, por defecto de texto)
				if (!orderedByValue){
					arrVals.sort(function(a, b){
					    if(a.text>b.text){ return 1;
					    } else if (a.text==b.text){ return 0;
					    } else { return -1; }
					});
				} else {
					arrVals.sort(function(a, b){
					    if(a.val>b.val){ return 1;
					    } else if (a.val==b.val){ return 0;
					    } else { return -1; }
					});
				}
				
				//Actualizar combo con elementos ordenados
			    for (var i = 0, l = arrVals.length; i < l; i++) {
			        $(options[i]).val(arrVals[i].val).text(arrVals[i].text);
					if (arrVals[i].clazz){
						$(options[i]).attr('class', arrVals[i].clazz);
					}
			    }
			
				//Añadir opción vacía al inicio
				if (skippedValue){
					combo.prepend($("<option>").attr("value", skippedValue.val).text(skippedValue.text));//Añadir opción vacía
					$(options[arrVals.length]).remove();//Eliminar ultimo elemento
				}
			
				//Regenerar combo
				combo.selectmenu();
				
				//Restaurar elemento seleccionado
				this.rup_combo('select', selectedVal);
				
				//Eliminar referencias
				delete combo;
				delete options;
				delete arrVals;
			}
		}
	});
	
	//*******************************
	// DEFINICIÓN DE MÉTODOS PRIVADOS
	//*******************************
	$.fn.rup_combo("extend", {
			//Obtener la opción vacía (del fichero de la app o el por defecto)
			_getBlankLabel : function (id){
				var app = $.rup.i18n.app;
				if (app[id] && app[id]["_blank"]){
					return app[id]["_blank"];
				} 
				return $.rup.i18n.base["rup_combo"]["blankNotDefined"];
			},
			//Formateo de textos
			_defaultFormatting : function(text){
				var findreps = [
						{find:/^([^\-]+) \- /g, rep: '<span class="ui-selectmenu-item-header">$1</span>'},
						{find:/([^\|><]+) \| /g, rep: '<span class="ui-selectmenu-item-content">$1</span>'},
						{find:/([^\|><\(\)]+) (\()/g, rep: '<span class="ui-selectmenu-item-content">$1</span>$2'},
						{find:/([^\|><\(\)]+)$/g, rep: '<span class="ui-selectmenu-item-content">$1</span>'},
						{find:/(\([^\|><]+\))$/g, rep: '<span class="ui-selectmenu-item-footer">$1</span>'}
					];
				for(var i in findreps){
					text = text.replace(findreps[i].find, findreps[i].rep);
				}
				return text;
			},	
			//Obtener valores padres (si no están cargados o valores 'vacíos' devuelve null)
			_getParentsValues : function(array, remote, multiValueToken){
				var retorno="", id, texto, multiValueToken=multiValueToken!=null?multiValueToken:"";
				
				for (var i in array){
					id = array[i];
					//Si tiene seleccionado la primera opción puede que está seleccionada opción vacia
					if ($("#"+id).rup_combo("index") === 0){
						texto = $("#"+id+"-button span:first-child").text();
						//Comprobar si tiene valor por defecto (bien propio o valor base por no haberlo definido)
						if ( texto === $.rup.i18n.base["rup_combo"]["blankNotDefined"] ||
							(($.rup.i18n.app[id] !== undefined) && (texto === $.rup.i18n.app[array[i]]["_blank"])) ){
							return null;	
						}
					}
					
					//Si el valor de algún padre es null (no se ha cargado aún)
					if ($("#"+id).val()===null){ return null };
					
					if (remote){
						retorno += $("#"+id).attr("name") + "=" + $("#"+id).val() + "&"; 
					} else {
						retorno += $("#"+id).val() + multiValueToken;
					}
				}
				//Evitar & o multiValueToken finales
				if (retorno!=="") {
					if (remote){
						retorno = retorno.substring(0, retorno.length-1);
					} else {
						retorno = retorno.substring(0, retorno.length-multiValueToken.length);
					}
				}
				return retorno;
			},
			//Crear combo
			_makeCombo : function(settings) {
					
					//Opción vacía
					if (settings.blank){
						$("#"+settings.id).prepend($("<option>").attr("value", settings.blank).text(this._getBlankLabel(settings.id)));
					}
					
					//Gestionar Imagenes
					if (settings.imgs) {
						var icons = [], values = [];
						settings.imgs.map(function(item) {
							for (var key in item){
								if (key.indexOf("###")==-1){
									$("#"+settings.id+" [value='"+key+"']").addClass(item[key]);
									icons[icons.length] = { find: '.'+item[key] };
								} else {
									values = key.split("###");
									$("#"+settings.id+" > [label='"+values[0]+"'] > [value='"+values[1]+"']").addClass(item[values[0]+"###"+values[1]]);
									icons[icons.length] = { find: '.'+item[values[0]+"###"+values[1]] };
								}
							}
						});
						settings.icons = icons;
					}
					
					//Formato texto
					settings.format = settings.format==="default"?this._defaultFormatting:settings.format;
					
					//Selectmenu
					$("#"+settings.id).selectmenu(settings);
					
					//Seleccionar elemento
					$("#"+settings.id).rup_combo("select", settings.selected);
					
					//Ordenar elementos del combo
					if (settings.ordered){
						$("#"+settings.id).rup_combo("order", settings.orderedByValue, settings.blank);
					}
					
					//Habilitar/Deshabilitar combo
					if (!settings.disabled) { 
						$("#"+settings.id).rup_combo("enable");
					} else {
						$("#"+settings.id).rup_combo("disable"); 
					}
					
					//Si los padres están deshabilitados, se deshabilita el combo 
					var padres = settings.parent;
					for (var i in padres){
						if($("#"+padres[i]).rup_combo("isDisabled")){
							$("#"+settings.id).rup_combo("disable");
							break;
						}
					}
			},
			_parseLOCAL : function (array, settings, html){
				var imgs = settings.imgs?settings.imgs:[],
					label, value;
				for (var i in array){
					label = value = array[i];
					if (typeof array[i] === "object"){ //multi-idioma
						label = $.rup.i18n.app[settings.id][array[i]["i18nCaption"]]; 
						value = array[i]["value"];
					}
					if (array[i]["style"]){
						imgs[imgs.length] = {};
						imgs[imgs.length-1][value] = array[i]["style"];
						settings.imgs = imgs;
					}
					html.append($("<option>").attr("value", value).text(settings.showValue?value+settings.token+label:label));
				}
			}, 
			_parseOptGroupLOCAL : function(arrayGroup, settings, html){
				var optGroup;
				for (var i in arrayGroup){
					optGroup = arrayGroup[i];
					for (var key in optGroup){
						if (typeof (optGroup[key][0]) !== 'string'){
							html.append($("<optgroup>").attr("label",$.rup.i18n.app[settings.id][key]));
						} else {
							html.append($("<optgroup>").attr("label",key));
						}
						html = $(html).children("optgroup:last-child");
						this._parseLOCAL(optGroup[key], settings, html);
						html = $(html).parent();
					}
				}
			},
			_parseREMOTE : function(array, settings, html, optGroupKey){
				var remoteImgs = settings.imgs?settings.imgs:[],
					item;
				for (var i in array){
					item = array[i];
					if (item["style"]){
						remoteImgs[remoteImgs.length] = {};
						if (optGroupKey==null){
							remoteImgs[remoteImgs.length-1][item["value"]] = item["style"];
						} else {
							remoteImgs[remoteImgs.length-1][optGroupKey+"###"+item["value"]] = item["style"];
						}
						settings.imgs = remoteImgs;
					}
					html.append($("<option>").attr("value", item["value"]).text(settings.showValue?item["value"]+settings.token+item["label"]:item["label"]));
				}
			},
			_parseOptGroupREMOTE : function(arrayGroup, settings, html){
				var optGroup;
				for (var i in arrayGroup){
					optGroup = arrayGroup[i];
					for (var key in optGroup){
						html.append($("<optgroup>").attr("label",key));
						html = $(html).children("optgroup:last-child");
						this._parseREMOTE(optGroup[key], settings, html, key);
						html = $(html).parent();
					}
				}
			},
			_ajaxBeforeSend : function (xhr, settings, html){
				//Crear combo (vacío) y deshabilitarlo
				if (html!==undefined){ $("#"+settings.id).replaceWith(html); } //Si no es 'reload' se debe inicializar vacío
				this._makeCombo(settings);
				$("#"+settings.id).rup_combo("disable"); 
				 
				//LOADING...
				$("#"+settings.id+"-button span:first-child").addClass("rup-combo_loadingText").text($.rup.i18n.base["rup_combo"]["loadingText"]);
				var icon = $("#"+settings.id+"-button span:last-child");
				$(icon).removeClass("ui-icon-triangle-1-s");
				$(icon).addClass("rup-combo_loading");
				
				//Cabecera RUP
				xhr.setRequestHeader("RUP", $.toJSON(settings.sourceParam));
			},
			_ajaxSuccess : function (data, settings, html){
				//UNLOADING...
				$("#"+settings.id+"-button span:first-child").removeClass("rup-combo_loadingText").text("");
				var icon = $("#"+settings.id+"-button span:last-child");
				$(icon).removeClass("rup-combo_loading");
				$(icon).addClass("ui-icon-triangle-1-s");
				
				//Vaciar combo
				$("#"+settings.id).empty();
				
				//Cargar combo (si se reciben datos)
				if (data.length>0){
					if (settings.source) {
						this._parseREMOTE(data, settings, html);
					} else {
						settings.ordered = false;
						this._parseOptGroupREMOTE(data, settings, html);
					}
				
					//Crear combo
					this._makeCombo(settings);
					
					//Lanzar cambio para que se recarguen hijos
					$("#"+settings.id).selectmenu("change");
				}
			},
			_ajaxError : function (XMLHttpRequest, textStatus, errorThrown){
				alert("Se ha producido un error al recuperar los datos del servidor");
			},
			_init : function(args){
				if (args.length > 1) {
					$.rup.errorGestor($.rup.i18n.base.rup_global.initError + $(this).attr("id"));
				} else {
					//Se recogen y cruzan las paremetrizaciones del objeto
					var settings = $.extend({}, $.fn.rup_combo.defaults, args[0]), 
						html;

					//Se carga el identificador del padre del patron
					settings.id = $(this).attr("id");
					settings.name = $(this).attr("name");
					
					//Contenido combo
					html = $("<select>").attr({"id" : settings.id, "name" : settings.name});
					
					if (settings.parent){
					//DEPENDIENTE
						//Guardar referencia a hijos en cada uno de los padres (propagación de carga)
						settings.parent.map(function(item){
							var childsArray = $('#'+item).data("childs")===undefined?[]:$('#'+item).data("childs");
							childsArray[childsArray.length] = settings.id;
							$('#'+item).data("childs", childsArray);
						});  
						
						//Crear combo y deshabilitarlo
						$("#"+settings.id).replaceWith(html);
						this._makeCombo(settings);
						$("#"+settings.id).rup_combo("disable");
						 
						//Almacenar los settings para el 'reload'
						$("#"+settings.id).data("settings", settings);
						
						//Comprobar si los padres ya tienen datos seleccionados (si son LOCALES puede suceder)
						if (this._getParentsValues(settings.parent)!==null){
							$("#"+settings.id).rup_combo("reload", settings.id); 
						}
					}else if (typeof settings.source === "object" || typeof settings.sourceGroup === "object"){
					//LOCAL
						//Parsear datos
						if (settings.source) {
							this._parseLOCAL(settings.source, settings, html);
						} else {
							settings.ordered = false;
							this._parseOptGroupLOCAL(settings.sourceGroup, settings, html);
						}
						
						//Crear combo
						$("#"+settings.id).replaceWith(html);
						this._makeCombo(settings);
						
					} else if (typeof settings.source === "string" || typeof settings.sourceGroup === "string"){
					//REMOTO
						var url = settings.source?settings.source:settings.sourceGroup,
							rupCombo = this;
						$.ajax({
							url: url,
							dataType: 'json',
							contentType: 'application/json',
							beforeSend: function (xhr){
								rupCombo._ajaxBeforeSend(xhr, settings, html);
							},
							success: function (data, textStatus, jqXHR){
								rupCombo._ajaxSuccess(data, settings, html);
							},
							error: rupCombo._ajaxError
						});
						delete rupCombo;
					}
					
					//Asociar evento CHANGE para propagar cambios a los hijos
					$("#"+settings.id).bind("change", function(event, ui) {	
						if ($("#"+event.target.id).data("childs")!==undefined){
							$("#"+event.target.id).data("childs").map(function (item){
								$("#"+item).rup_combo("reload", item);
							});
						}
					})
					
					//Borrar referencia
					delete html;
				}
			}
		});
		
	//******************************************************
	// DEFINICIÓN DE LA CONFIGURACION POR DEFECTO DEL PATRON  
	//******************************************************
	$.fn.rup_combo.defaults = {
		width: 200,
		blank: null,
		style: "dropdown",
		showValue: false,
		token: "|",
		multiValueToken : "##",
		ordered:true,
		orderedByValue:false
	};	
	
	
})(jQuery);