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
jQuery(document).ready(function () {

	//f(x) extra
	function acceptPulsed() { alert("Aceptar"); }	
	
	
	$("#btnError").bind("click", function () {
		$.rup_messages("msgError", {
			title: "Error grave",
			message: "<p>Se ha producido un error a la hora de intentar guardar el registro.<br>Consulte con el administrador.</p>"
		});
	});
	
	$("#btnConfirm").bind("click", function () {
		$.rup_messages("msgConfirm", {
			message: "¿Está seguro que desea cancelar?",
			title: "Confirmación",
			OKFunction : acceptPulsed
		});
	});
	
	$("#btnOK").bind("click", function () {
		$.rup_messages("msgOK", {
			title: "Correcto",
			message: "Todo ha ido OK."
		});
	});
	
	$("#btnAlert").bind("click", function () {
		$.rup_messages("msgAlert", {
			title: "Alerta",
			message: "Esto es una alerta."
		});
	});
	
	$("#btnAlertJS").bind("click", function () {
		alert("esto es un alert de javascript puro");
	});
	
});