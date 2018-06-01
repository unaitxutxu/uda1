<%--  
 -- Copyright 2011 E.J.I.E., S.A.
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 -- 
 -- http://ec.europa.eu/idabc/eupl.html
 -- 
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia. 
 --%>
 <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
 <%@include file="/WEB-INF/includeTemplate.inc"%>
 
<nav class="rup-navbar navbar">
  <button type="button" class="navbar-toggler d-lg-none"  type="button" data-toggle="rup-collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"></button>
  <div id="navbarResponsive" class="collapse navbar-toggleable-md col-md-12 no-gutter">
    <a class="navbar-brand" href="/x21aAppWar/"><spring:message code="app.title" /></a>
    <ul class="nav navbar-nav">
      <li class="nav-item dropdown">
        <!-- <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="componentes" /><span class="caret"></span></a> -->
        <a class="nav-link dropdown-toggle" href="#" id="responsiveNavbarDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><spring:message code="componentes" /></a>
        <div class="dropdown-menu" aria-labelledby="responsiveNavbarDropdown">
            <a class="dropdown-item" href="/x21aAppWar/patrones/feedback"><i class="fa fa-check-square" aria-hidden="true"></i><spring:message code="feedback" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/tooltip"><i class="fa fa-comment-o" aria-hidden="true"></i><spring:message code="tooltip" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/message"><i class="fa fa-envelope" aria-hidden="true"></i><spring:message code="message" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/dialog"><i class="fa fa-window-restore" aria-hidden="true"></i><spring:message code="dialog" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/progressbar"><i class="fa fa-hourglass-half" aria-hidden="true"></i><spring:message code="progressBar" /></a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="/x21aAppWar/patrones/contextMenu"><i class="fa fa-list-alt" aria-hidden="true"></i><spring:message code="contextMenu" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/button"><i class="fa fa-hand-pointer-o" aria-hidden="true"></i><spring:message code="button" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/toolbar"><i class="fa fa-wrench" aria-hidden="true"></i><spring:message code="toolbar" /></a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="/x21aAppWar/patrones/accordion"><i class="fa fa-list" aria-hidden="true"></i><spring:message code="accordion" /></a>

            <div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#"><spring:message code="tabs" /></a>
              <div class="dropdown-menu menu-right" >
                <a class="dropdown-item" href="/x21aAppWar/patrones/tabsStatic"><spring:message code="tabsStatic" /></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/tabsAjax"><spring:message code="tabsAjax" /></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/tabsMixto"><spring:message code="tabsMixto" /></a>
                <!-- FIXME <a class="dropdown-item" href="/x21aAppWar/patrones/maintTab"><spring:message code="maintTab" /></a> -->
                <a class="dropdown-item" href="/x21aAppWar/patrones/tabsScrollable"><spring:message code="tabsScrollable" /></a>
                
              </div>
            </div>

            <div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#"><i class="fa fa-magic" aria-hidden="true"></i><spring:message code="wizard" htmlEscape="true"/></a>
              <div class="dropdown-menu menu-right" >
                <a class="dropdown-item" href="/x21aAppWar/patrones/wizard"><spring:message code="wizardA" /></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/wizard_includeFile"><spring:message code="wizardB" htmlEscape="true"/></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/wizard_jspInclude"><spring:message code="wizardC" htmlEscape="true"/></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/wizard_jstlImport"><spring:message code="wizardD" htmlEscape="true"/></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/wizard_dinamico"><spring:message code="wizardE" /></a>
              </div>
            </div>

            <div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#"><i class="fa fa-sitemap" aria-hidden="true"></i><spring:message code="tree" /></a>
              <div class="dropdown-menu menu-right" >
                <a class="dropdown-item" href="/x21aAppWar/patrones/trees"><spring:message code="tree_multiple_configuraciones" /></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/treeDAD"><spring:message code="tree_multiple_d&d"/></a>
              </div>
            </div>

            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="/x21aAppWar/patrones/autocomplete"><spring:message code="autocomplete" /></a>

            <div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#"><spring:message code="combo" /></a>
              <div class="dropdown-menu menu-right">
                <a class="dropdown-item" href="/x21aAppWar/patrones/comboSimple"><spring:message code="comboSimple" /></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/comboEnlazadoSimple"><spring:message code="comboEnlazadoSimple" /></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/comboEnlazadoMultiple"><spring:message code="comboEnlazadoMulti" /></a>
                <a class="dropdown-item" href=/x21aAppWar/patrones/multicombo><spring:message code="multicombo" /></a>
              </div>
            </div>

            <a class="dropdown-item" href="/x21aAppWar/patrones/slider"><i class="fa fa-sliders" aria-hidden="true"></i><spring:message code="slider" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/date"><i class="fa fa-calendar" aria-hidden="true"></i><spring:message code="date" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/form"><i class="fa fa-wpforms" aria-hidden="true"></i><spring:message code="form" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/time"><i class="fa fa-clock-o" aria-hidden="true"></i><spring:message code="time" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/spinner"><i class="fa fa-spinner" aria-hidden="true"></i><spring:message code="spinner" /></a>
            <a class="dropdown-item" href="/x21aAppWar/patrones/upload"><i class="fa fa-upload" aria-hidden="true"></i><spring:message code="upload" /></a>
            <div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#"><i class="fa fa-check" aria-hidden="true"></i><spring:message code="validate" /></a>
              <div class="dropdown-menu menu-right">
              	<a class="dropdown-item" href="/x21aAppWar/patrones/validate"><spring:message code="validate.configuration" /></a>
                <a class="dropdown-item" href="/x21aAppWar/patrones/validateRules"><spring:message code="validate.rules" /></a>
                
              </div>
            </div>
            
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="/x21aAppWar/patrones/charts"><i class="fa fa-bar-chart" aria-hidden="true"></i><spring:message code="charts.charts" /></a>
        </div>
      </li>
	  <li class="nav-item dropdown">
	  	<a class="nav-link dropdown-toggle" href="#" id="datatableDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></i><spring:message code="tabla" /></a>
	    <div class="dropdown-menu" aria-labelledby="datatableDropdown">
	    	<a class="dropdown-item" href="/x21aAppWar/table/simple"><spring:message code="tabla.simple" /></a>
	    	<a class="dropdown-item" href="/x21aAppWar/table/multipk"><spring:message code="tabla.multipk" /></a>
	    	<div class="dropdown-divider"></div>
	    	<div class="dropdown-submenu" >
              <a class="dropdown-item dropdown-toggle" href="#"><spring:message code="tablaLegacy" /></a>
              <div class="dropdown-menu menu-right">
			  	<a class="dropdown-item" href="/x21aAppWar/table/filtroSimple"><spring:message code="tablaLegacy.filtroSimple" /></a>
		        <a class="dropdown-item" href="/x21aAppWar/table/formEditAutogenerated"><spring:message code="tablaLegacy.edicionFormularioAuto" /></a>
		        <a class="dropdown-item" href="/x21aAppWar/table/formEdit"><spring:message code="tablaLegacy.edicionFormulario" /></a>
		        <a class="dropdown-item" href="/x21aAppWar/table/formEditMultiselection"><spring:message code="tablaLegacy.edicionFormularioMultiseleccion" /></a>
		        <a class="dropdown-item" href="/x21aAppWar/table/inlineEditExcelMode"><spring:message code="tablaLegacy.edicionLineaExcel" /></a>
		        <a class="dropdown-item" href="/x21aAppWar/table/inlineEdit"><spring:message code="tablaLegacy.edicionLinea" /></a>
	            <a class="dropdown-item" href="/x21aAppWar/table/inlineEditMultiselection"><spring:message code="tablaLegacy.edicionLineaMultiseleccion" /></a>
		        <!-- FIXME <a class="dropdown-item" href="/x21aAppWar/table/grouping"><spring:message code="tablaLegacy.agrupamiento" /></a> -->
		        <a class="dropdown-item" href="/x21aAppWar/table/masterDetail"><spring:message code="tablaLegacy.maestroDetalle" /></a>
		        <a class="dropdown-item" href="/x21aAppWar/table/tableLoadOnStartUp"><spring:message code="tablaLegacy.loadStartupFalse" /></a>
		        <a class="dropdown-item" href="/x21aAppWar/table/dialog"><spring:message code="tablaLegacy.tablaDialogo" /></a>
		        <a class="dropdown-item" href="/x21aAppWar/table/tableRadiobutton"><spring:message code="tablaLegacy.tablaRadioButton" /></a>
	            <!-- FIXME <a class="dropdown-item" href="/x21aAppWar/table/tabs"><spring:message code="tablaLegacy.tablaPestañas" /></a> -->
              </div>
            </div>
	    </div>
	  </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="gridResponsiveDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><spring:message code="gridResponsive" /></a>
        <div class="dropdown-menu" aria-labelledby="gridResponsiveDropdown">
          <a class="dropdown-item" href="/x21aAppWar/bootstrap/stackedHorizontal"><spring:message code="gridResponsive.stackedHorizontal" /></a>
          <a class="dropdown-item" href="/x21aAppWar/bootstrap/mobileDesktop"><spring:message code="gridResponsive.MobileDesktop" /></a>
          <a class="dropdown-item" href="/x21aAppWar/bootstrap/mobileTabletDesktop"><spring:message code="gridResponsive.MobileTabletDesktop" /></a>
          <a class="dropdown-item" href="/x21aAppWar/bootstrap/exampleForm"><spring:message code="gridResponsive.ExampleForm" /></a>
        </div>
      </li>
    
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="styleGuideDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><spring:message code="stylingGuide" /></a>
        <div class="dropdown-menu" aria-labelledby="styleGuideDropdown">
          <a class="dropdown-item" href="/x21aAppWar/styleGuide"><spring:message code="stylingGuide" /></a>
        </div>
      </li>
      
      
      <!-- Integración -->
    <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="integrationDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <spring:message code="integracion" /></a>
                        <div class="dropdown-menu" aria-labelledby="integrationDropdown">
                               
                                <a class="dropdown-item" href="/x21aAppWar/integracion/geoEuskadi">
                                    <spring:message code="geoEuskadi" />

                                </a>                        	
                       

                                <!-- FIXME <a class="dropdown-item" href="/x21aAppWar/integracion/nora">
                                    <spring:message code="nora" />

                                </a> -->

                                <a class="dropdown-item" href="/x21aAppWar/integracion/tiny">
                                    <spring:message code="tiny" />
                                </a>
                                <a class="dropdown-item" href="/x21aAppWar/integracion/webdav">
                                    <spring:message code="webdav" />
                                </a>

                                <!-- FIXME <a class="dropdown-item" href="/x21aAppWar/integracion/pif">
                                     <spring:message code="integracion.pif" />
                                </a> -->

                                <a class="dropdown-item" href="/x21aAppWar/integracion/cache/view">
                                   <spring:message code="integracion.cache" />
                                </a>


                            </div>
                        </li>
      
      
    </ul>
    <ul class="nav navbar-nav float-md-right rup-nav-tools">
      <li class="nav-item">
        <a class="nav-link rup-nav-tool-icon" href="#" id="x21aApp_language" data-toggle="dropdown"><i class="fa fa-globe" aria-hidden="true"></i><span data-rup-lang-current=""></span></a>
		<div class="dropdown-menu" aria-labelledby="x21aApp_language">
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link rup-nav-tool-icon" href="http://uda-ejie.github.io/" id="uda_github"><i class="fa fa-github" aria-hidden="true"></i></a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle rup-nav-tool-icon" href="#" id="x21aApp_releases" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-cog " aria-hidden="true"></i></a>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="x21aApp_releases">
          <a class="dropdown-item" href="/x21aPilotoPatronesWar/"><spring:message code="udaLegacy" /></a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link rup-nav-user rup-nav-tool-icon" href="#"><i class="fa fa-user-circle-o " aria-hidden="true"></i></a>
      </li>
      <li class="nav-item swingTop">
        <a class="nav-link rup-nav-user rup-nav-tool-icon" href="javascript:void(0)"><i class="fa fa-arrow-circle-up " aria-hidden="true"></i></a>
      </li>
    </ul>
  </div><!--/.navbar-collapse -->

</nav>

<div id="overlay"></div>
