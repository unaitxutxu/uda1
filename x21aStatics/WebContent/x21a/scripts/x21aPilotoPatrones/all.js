/*!
 * Copyright 2011 E.J.I.E., S.A.
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
jQuery(function($){
	
	
	$("#table").rup_table({
		url: "../jqGridUsuario",
		colNames: tableColNames,
		colModel: tableColModels,
        primaryKey:["id"],
        
        usePlugins:[
			"formEdit",
        	"feedback",
			"toolbar",
        	"contextMenu",
        	"fluid",
        	"filter",
        	"search",
        	"report",
        ],
        rowNum:10, 
        rowList:[10,20,30], 
        sortname: 'id',
        core:{
        	operations:{
             
          }
        },
        toolbar:{
        	

        },
		contextMenu:{
			colNames:["nombre","apellido1","apellido2","ejie","fechaAlta"],
			items: {
				"sep1": "---------",
		        "opContextual1": {name: "Op. contextual 1", icon: "edit", disabled: false, colNames:["fechaAlta","fechaBaja","rol"]},
		        "opContextual2": {name: "Op. contextual 2", icon: "cut", disabled: true},
		        "opContextual3" :{name: "Op. contextual 3", icon: "cut", colNames:["nombre","apellido1"], items:{
		        	"subOpContextual1": {name: "Sub Op. contextual 1", icon: "edit", disabled: false},
		            "opContextual2": {name: "Sub Op. contextual 2", icon: "cut", disabled: true}
		        	}
		        }
		    }
			
		},
        formEdit:{
        	detailForm: "#table_detail_div",
        	validate:{
    			rules:{
    				"nombre":{required:true},
    				"apellido1":{required:true},
    				"fechaAlta":{date:true},
    				"fechaBaja":{date:true}
    			}
    		}
        },
        filter:{
        	validate:{
        		rules:{
    				"fechaAlta":{date:true},
    				"fechaBaja":{date:true}
    			}
        	},
//        	fncSearchCriteria: function(searchString){
//    			//SsearchString+="NombreCampo=Valores de filtrado del campo";
//    			return searchString;
//    		}
        },
        search:{
        	validate:{
        		rules:{
    				"fechaAlta":{date:true},
    				"fechaBaja":{date:true}
    			}
        	}
        },
        report: options_table_report//,
       // loadOnStartUp:false
        
	});
	
	
	
	
	$("#feedback").rup_feedback({ type: "ok", message:"Este es un ejemplo de <b>Feedback</b>"});
	
	$("#date").rup_date({
		changeMonth : false,
		changeYear	: false,
		numberOfMonths : 1
	});
	
	$("#time").rup_time({});
	
	$('#comboProvincia').rup_combo({
		source : "comboEnlazadoSimple/remoteEnlazadoProvincia",
		sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
		selected: 3
	});
	$('#comboComarca').rup_combo({
		parent: ["comboProvincia"],
		source : "comboEnlazadoSimple/remoteEnlazadoComarca",
		sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"}
	});
	
	$('#multicomboGruposRemoto').rup_combo({
		sourceGroup : "comboSimple/remoteGroup",
		sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
		width: 500,
		multiselect: true
	});
	
	$("#autocomplete").rup_autocomplete({
		source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"]
	});
	
	$("#tooltip").rup_tooltip(); 
	
	$("#tabs").rup_tabs({
		tabs : [
			{i18nCaption:"pestana0", tabs: [
				{i18nCaption:"sub01", url:"fragmento1"},
				{i18nCaption:"sub02", url:"fragmento1"}
			]},
			{i18nCaption:"pestana1", tabs: [
				{i18nCaption:"sub10", url:"tab2Fragment"},
				{i18nCaption:"sub11", url:"tab3Fragment"}
			]}
		]
	});
	
	//dialog
	$("#dialog").bind("click", function () {
		$(document).rup_dialog({
			type: jQuery.rup.dialog.AJAX,
			url: "../patrones/allDialog" ,
				autoOpen: true,
				modal: true,
				width: "1200",
				height: "750",
				resizable: true,
				title: "Todos los patrones",
				buttons: [{
					text: "Aceptar",
					click: function () { 
						$(this).dialog("close");
					}					
				}],
			open : function(event, ui) { 
				//Configurar dialog
				
				$("#tableDialog").rup_table({
					url: "../jqGridUsuario",
					colNames: tableColNames,
					colModel: tableColModels,
			        primaryKey:["id"],
			        
			        usePlugins:[
						"formEdit",
			        	"feedback",
						"toolbar",
			        	"contextMenu",
			        	"fluid",
			        	"filter",
			        	"search",
			        	"report",
			        ],
			        rowNum:10, 
			        rowList:[10,20,30], 
			        sortname: 'id',
			        core:{
			        	operations:{
			             
			          }
			        },
			        toolbar:{
			        	

			        },
					contextMenu:{
						colNames:["nombre","apellido1","apellido2","ejie","fechaAlta"],
						items: {
							"sep1": "---------",
					        "opContextual1": {name: "Op. contextual 1", icon: "edit", disabled: false, colNames:["fechaAlta","fechaBaja","rol"]},
					        "opContextual2": {name: "Op. contextual 2", icon: "cut", disabled: true},
					        "opContextual3" :{name: "Op. contextual 3", icon: "cut", colNames:["nombre","apellido1"], items:{
					        	"subOpContextual1": {name: "Sub Op. contextual 1", icon: "edit", disabled: false},
					            "opContextual2": {name: "Sub Op. contextual 2", icon: "cut", disabled: true}
					        	}
					        }
					    }
						
					},
			        formEdit:{
			        	detailForm: "#tableDialog_detail_div",
			        	validate:{
			    			rules:{
			    				"nombre":{required:true},
			    				"apellido1":{required:true},
			    				"fechaAlta":{date:true},
			    				"fechaBaja":{date:true}
			    			}
			    		}
			        },
			        filter:{
			        	validate:{
			        		rules:{
			    				"fechaAlta":{date:true},
			    				"fechaBaja":{date:true}
			    			}
			        	},
//			        	fncSearchCriteria: function(searchString){
//			    			//SsearchString+="NombreCampo=Valores de filtrado del campo";
//			    			return searchString;
//			    		}
			        },
			        search:{
			        	validate:{
			        		rules:{
			    				"fechaAlta":{date:true},
			    				"fechaBaja":{date:true}
			    			}
			        	}
			        },
			        report: options_table_report//,
			       // loadOnStartUp:false
			        
				});
			
				
				
				
			
				
				$("#feedbackDialog").rup_feedback({ type: "ok", message:"Este es un ejemplo de <b>Feedback</b>"});
				
				$("#dateDialog").rup_date({
					changeMonth : false,
					changeYear	: false,
					numberOfMonths : 1
				});
				
				$("#timeDialog").rup_time({});
				
				$('#comboDialogProvincia').rup_combo({
					source : "comboEnlazadoSimple/remoteEnlazadoProvincia",
					sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
					selected: 3
				});
				
				$('#comboDialogComarcaDialog').rup_combo({
					parent: ["comboDialogProvincia"],
					source : "comboEnlazadoSimple/remoteEnlazadoComarca",
					sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"}
				});
				
				$('#multicomboGruposRemotoDialog').rup_combo({
					sourceGroup : "comboSimple/remoteGroup",
					sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
					width: 500,
					multiselect: true
				});
				
				$("#autocompleteDialog").rup_autocomplete({
					source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"]
				});
				
				$("#tooltipDialog").rup_tooltip(); 
				
				$("#tabsDialog").rup_tabs({
					tabs : [
						{i18nCaption:"pestana0", tabs: [
							{i18nCaption:"sub01", url:"fragmento1"},
							{i18nCaption:"sub02", url:"fragmento1"}
						]},
						{i18nCaption:"pestana1", tabs: [
							{i18nCaption:"sub10", url:"tab2Fragment"},
							{i18nCaption:"sub11", url:"tab3Fragment"}
						]}
					]
				});
			}
		});	
	});
	
});