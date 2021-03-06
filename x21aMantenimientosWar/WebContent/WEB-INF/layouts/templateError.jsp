<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--  
 -- Copyright 2012 E.J.I.E., S.A.
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="/WEB-INF/tld/tiles-jsp.tld" %>

<html>
<head>
	<%@include file="/WEB-INF/includeTemplate.inc"%>
	<title><spring:message code="app.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8" />
	
	<%-- Estilos RUP sin minimizar (DESARROLLO) --%>
<%-- <%@include file="/WEB-INF/layouts/includes/rup.styles.inc"%> --%>
	<%-- Estilos RUP sin minimizar (PRODUCCION) --%>
	<%@include file="/WEB-INF/layouts/includes/rup.styles.min.inc" %>
	<%-- Estilos Aplicacion --%>
	<%@include file="/WEB-INF/layouts/includes/x21a.styles.inc"%>
</head>	
<body>
	<div class="contenedor">	
		<!-- Cabecera -->
		<tiles:insertAttribute name="header" />
		
		<!-- Contenidos -->
		<tiles:insertAttribute name="content"/>
		
		<!-- Pie -->
		<tiles:insertAttribute name="footer" />
		
		<!-- Includes JS -->
		<tiles:insertAttribute name="base-includes" />
	</div>
</body>
</html>