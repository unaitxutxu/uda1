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
jQuery(function($){
	$("#GRID_simpleFluido").rup_grid({
		
		url: "../usuario",
		hasMaint: true,
		width: 650,
		pagerName: "pager",
		rowNum: "10",
		sortorder: "asc",
		sortname: "id",
		colNames: [ "id", "nombre", "apellido1", "apellido2", "ejie", "fechaAlta", "fechaBaja" ],
		colModel: [
		    //label: etiqueta del detalle
		    
			{ name: "id", index: "id", editable: true, 
				rupType: "integer", 
				key: true 
			},
			{ name: "nombre", index: "nombre", editable: true },
			{ name: "apellido1", index: "apellido1", editable: true },
			{ name: "apellido2", index: "apellido2", editable: true },
//			Definición para rup_combo 
			{ name: "ejie", index: "ejie", editable: true,
				editoptions: {
					source : [
					   {i18nCaption: "0", value:"0"},
					   {i18nCaption: "1", value:"1"}
					]
				},
				formatter: "rup_combo",
				rupType: "combo"
			},
			{ name: "fechaAlta",  index: "fechaAlta", editable: true,
				rupType: "datepicker"
			},
			{ name: "fechaBaja", index: "fechaBaja", editable: true,
				rupType: "datepicker" 
			}
        ],
        //Indica que el GRID debe tener un comportamiento fluido
        fluidBaseLayer:'#simpleFluido'
	});
	
	$("#simpleFluido").rup_maint({
		jQueryGrid: "GRID_simpleFluido",
		primaryKey: "id",
		modelObject: "Usuario",
		detailButtons: $.rup.maint.detailButtons.SAVE,
		searchForm: "searchForm",
		showMessages: true,
		//Indica que el mantenimiento debe tener un comportamiento fluido
		fluid : true
	});
	
	
	//Formulario de filtrado
	$('#ejie_search').rup_combo({
		source : [
		   {i18nCaption: "0", value:"0"},
		   {i18nCaption: "1", value:"1"}
		],
		i18nId: "GRID_simpleFluido##ejie",
		width: 120,
		blank: ""
	});
	
	$("#fechaAlta_search").rup_date();
	$("#fechaBaja_search").rup_date();
});