<%--  
 -- Copyright 2011 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>	

<section>
	<h2>
		Diálogo
	</h2>
	<p>
		Permite lanzar un subproceso o un mensaje de confirmación dentro de un proceso principal sin salirse de este. Es una evolución del patrón mensaje.
	</p>
	
	<p>
		El componente permite varios modos de indicar el contenido del diálogo: 
	</p>
	<ul>
		<li><code>$.rup.dialog.DIV</code> : El contenido del diálogo se especifica dentro del propio <code>&lt;div&gt;</code>.</li>
		<li><code>$.rup.dialog.AJAX</code> : El contenido del diálogo se obtine a partir de la respuesta de una petición <b>XHR</b>.</li>
		<li><code>$.rup.dialog.TEXT</code> : El contenido del diálogo se especifica mediante la propiedad <code>message</code>.</li>
	</ul>
	<p>
		Estos serían unos ejemplos de las diferentes configuraciones: 
	</p>
	<div class="example">
		
		<div class="row">
		    <div class="col-md-3">
		    	<button id="btnDialog" class="btn btn-block btn-secondary"><spring:message code="dialogNormal" /></button>
		    </div>
		    <div class="col-md-3">
		    	<button id="btnAjaxDialogWAR" class="btn btn-block btn-secondary"><spring:message code="dialogAjaxWAR" /></button>
		    </div>
		    <div class="col-md-3">
		    	<button id="btnAjaxDialogStatics" class="btn btn-block btn-secondary"><spring:message code="dialogAjaxStatics" /></button>
		    </div>
		    <div class="col-md-3">
		    	<button id="btnTextDialog" class="btn btn-block btn-secondary"><spring:message  code="dialogText" /></button>
		    </div>
	  	</div>
	</div>
</section>

<div id="idDialog" style="display:none"><spring:message code="divDialogContent" /></div>
<div id="idDialogAjaxWar" style="display:none"></div>
<div id="idDialogAjaxStatics" style="display:none"></div>
<div id="idDialogText" style="display:none"></div>
