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
	
	$("#radio_summary_yes").click(function(){
		$("[name=accordion]").attr('disabled',false);
		$("[name=tabs2accordion]").attr('disabled',false);
	});
	$("#radio_summary_no").click(function(){
		$("[name=accordion]").attr('disabled',true);
		$("[name=tabs2accordion]").attr('disabled',true);
	});
		
	$("#makeWizard").click(function (){
		$("#wizardForm").rup_wizard({ 
			submitButton: 'saveForm', 
			submitFnc : function(){
				//Función que se ejecuta antes de enviar el formulario
				//console.log("Enviando formulario...");
				alert("Enviando formulario...");
			},
			//parseJSON para convertir String en Boolean
			summary: $.parseJSON($('[name=summary]:checked').val()),
			summaryWithAccordion: $.parseJSON($('[name=accordion]:checked').val()),
			summaryTabs2Accordion: $.parseJSON($('[name=tabs2accordion]:checked').val()),
			summaryFnc_PRE: function(){
				//Función adicional previa al generar el resumen (con return false se corta generación)
				//console.log("Generando resumen...");
			},
			summaryFnc_INTER: function(){
				//Función adicional intermedia al generar el resumen (con return false se corta generación)
				//console.log("Comenzando gestión elementos...");
			},
			summaryFnc_POST: function(){
				//Función adicional posterior al generar el resumen (con return false se corta generación)
				//console.log("Resumen generado.");
			},
			stepFnc : {
				//Funciones de cada paso. 
				0 : function (){
//						console.log("Ir al paso "+$("#stepDesc0").children("a").text());
				},
				1 : function (){
//						console.log("Ir al paso "+$("#stepDesc1").children("a").text());
				},
				2 : function (){
//						if ($("#wizardForm").rup_wizard("getCurrentStep")==0 && $("#username").val()!=="uda"){
//							alert("El campo 'Usuario' deber contener 'uda'");
//							return false;
//						}
//						console.log("Ir al paso "+$("#stepDesc2").children("a").text());
				
						//Petición de contenido
						$.rup_ajax({
							url: "wizard_dinamico_content",
							success: function(data, textStatus, jqXHR) {
								
								//Modificar contenido
								$("#step"+$("#wizardForm").rup_wizard("getCurrentStep")).html(data);
								
								//Funciones a ejecutar sobre el contenido
									$("#tabs").rup_tabs({
										tabs : [
											{i18nCaption:"Empleado", layer:"#empleado"},
											{i18nCaption:"Empresa", tabs:[
											    {i18nCaption:"Datos", layer:"#empresa_datos"},
											    {i18nCaption:"Delegaciones", tabs: [
											         {i18nCaption:"Araba", layer:"#empresa_araba"},
											         {i18nCaption:"Bizkaia", layer:"#empresa_bizkaia"},
											         {i18nCaption:"Gipuzkoa", layer:"#empresa_gipuzkoa"}
								                ]}
										    ]},
											{i18nCaption:"Datos adicionales", layer:"#otros_datos"}
										]
									});
									$('#provincia').rup_combo({
										source : "comboSimple/remote",
										sourceParam : {label:"desc"+$.rup_utils.capitalizedLang(), value:"code", style:"css"},
										selected: "Combo",
										width: 300
									});
								
							},
							error: function (XMLHttpRequest, textStatus, errorThrown){
								alert('Error recuperando datos del paso');
							}
						});
					
				},	
				3 : function (){
//					console.log("Ir al paso "+$("#stepDesc3").children("a").text());
				}
			}
			, disabled : [1] //Intervalos con string separado con guión (ej. "1-2")
		});
		$("#wizard_options").remove();
	});
	
	//Pasos
	$.rup_date({		
		from: "desde",
		to: "hasta",
		labelMaskId : "intervalo-mask",
		numberOfMonths: 3
	});
	
	$("#hora_entrada").rup_time({
		labelMaskId : "hora-mask",
		showSecond : true,
		timeFormat: 'hh:mm:ss',
		showButtonPanel: true
	});
	
	$("#hora_salida").rup_time({
		showSecond : true,
		timeFormat: 'hh:mm:ss',
		showButtonPanel: true
	});
	
	$('#dias').rup_combo({
		source : ["Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"],
		ordered: false,
		width: 320,
		multiselect: true,
		summaryInline : true
	});
	
	
	$('#cliente').rup_combo({
		sourceGroup : [ 
			{"Invierno" : ["Enero", "Febrero", "Marzo"]},
			{"Primavera" : ["Abril", "Mayo", "Junio"]},
			{"Verano" : [ "Julio", "Agosto", "Septiembre"]},
			{"Otoño" : [ "Octubre", "Noviembre", "Diciembre"]}
		],
		width: 400,
		height: 300,
		multiselect: true
	});
	
	$("#meses").rup_accordion({
		animated: "bounceslide",
		active: false,
		autoHeight: false,
		collapsible: true
	});
});