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
jQuery(document).ready(function(){
	
	$('#multicombo').rup_combo({
		//source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"],
		source : [
		    {i18nCaption: "rubya", value:"ruby_value"},
			{i18nCaption: "c", value:"c_value"},
			{i18nCaption: "scala", value:"scala_value"},
			{i18nCaption: "javascript", value:"javascript_value"},
			{i18nCaption: "c++", value:"c++_value"},
			{i18nCaption: "haskell", value:"haskell_value"},
			{i18nCaption: "asp", value:"asp_value"},
			{i18nCaption: "java", value:"java_value"},
			{i18nCaption: "php", value:"php_value"},
			{i18nCaption: "groovy", value:"groovy_value"},
			{i18nCaption: "python", value:"python_value"},
			{i18nCaption: "coldfusion", value:"coldfusion_value"},
			{i18nCaption: "perl", value:"perl_value"}
		],
		selected: ["perl_value", "javascript_value", "ruby_value"], //value && index
		ordered: false,
		width: 400,
		multiselect: true,
		rowStriping : true
	});
	
	$('#multicomboRemoto').rup_combo({
		source : "comboSimple/remote",
		sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
		selected: [1], //index
		width: 350,
		height: 75,
		multiselect: true
	});
	
	$('#multicomboGrupos').rup_combo({
		/*sourceGroup : [ 
			{"Futbol" : ["Alaves", "Athletic", "Real Sociedad"]},
			{"Baloncesto" : ["Caja Laboral", "BBB", "Lagun Aro"]},
			{"Formula 1" : [ "Fernando Alonso", "Hamilton", "Vettel"]}
		],
		*/
		sourceGroup : [ 
			{"futbol" : [
				{i18nCaption: "alaves", value:"alaves_value", style:"delete"},
				{i18nCaption: "ath", value:"ath_value", style:"filter"},
				{i18nCaption: "real", value:"real_value", style:"print"}
			]},
			{"baloncesto" : [
				{i18nCaption: "laboral", value:"laboral_value"},
				{i18nCaption: "bilbao", value:"bilbao_value"},
				{i18nCaption: "lagun aro", value:"lagun aro_value"}
			]},
			{"formula1" : [
				{i18nCaption: "falonso", value:"falonso_value"},
				{i18nCaption: "hamilton", value:"hamilton_value"},
				{i18nCaption: "vettel", value:"vettel_value"}
			]}
		],
		width: 500,
		height: 300,
		multiselect: true,
		multiOptgroupIconText: false,
		rowStriping : true
	});
	
	$('#multicomboGruposRemoto').rup_combo({
		sourceGroup : "comboSimple/remoteGroupEnlazado",
		sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
		width: 500,
		multiselect: true
	});
	
	$('#multicomboInput').rup_combo({
		source : ["asp", "c", "c++", "coldfusion", "groovy", "haskell", "java", "javascript", "perl", "php", "python", "ruby", "scala"],
		width: 350,
		multiselect: true
	});

	$('#multicomboLoadFromSelect').rup_combo({
		loadFromSelect: true,
		width: 350,
		height: 75,
		multiselect: true
	});
	
	
});
