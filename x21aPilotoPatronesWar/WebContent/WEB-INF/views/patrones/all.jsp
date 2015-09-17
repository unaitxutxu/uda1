<%@include file="/WEB-INF/views/includes/includeTemplate.inc"%>
<h1>Todos los patrones</h1>

<div style="float:left">
	<!-- Maint (toolbar + grid + maint) -->
	<div id="error" style="display:none"></div>
	<div id="maint">
		<div id="contenido" style="margin-top:0.5em;margin-bottom:0.5em;width:600px;">
			<form id="searchForm">
				<div  class="formulario_legend" id="titleSearch_maint"><spring:message code="searchCriteria" />:</div>
				<fieldset style="border:1px solid #DADADA;" id="FIELDSET_SEARCH_maint">
					<div class="formulario_columna_cnt">
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">id:</div>
							<input type="text" name="id" class="formulario_linea_input" id="id_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">nombre:</div>
							<input type="text" name="nombre" class="formulario_linea_input" id="nombre_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">apellido1:</div>
							<input type="text" name="apellido1" class="formulario_linea_input" id="apellido1_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">apellido2:</div>
							<input type="text" name="apellido2" class="formulario_linea_input" id="apellido2_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">ejie:</div>
							<div style="float: left;"><select id="ejie_search" class="rup-combo" ></select></div>
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">fechaAlta:</div>
							<input type="text" name="fechaAlta" class="formulario_linea_input" id="fechaAlta_search" />
						</div>
						<div class="formulario_linea_izda_float" style="clear: left;">
							<div class="formulario_linea_label">fechaBaja:</div>
							<input type="text" name="fechaBaja" class="formulario_linea_input" id="fechaBaja_search" />
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		<div id="RUP_grid">
			<table id="grid" cellpadding="0" cellspacing="0"></table>
			<div id="pager" style="text-align:center;"></div>
		</div>
	</div>
	<br><br>
	<button id="dialog">Dialog</button>
</div>

<div style="float: right; margin-right: 2em;">
	<!-- Feedback -->
	<div id="feedback"></div><br>

	<div style="margin-bottom: 15.5em;">
		<!-- Fecha --> 
		<div id="date" style="float: left;"></div>
		<!-- Hora -->
		<div id="time" style="float: left; margin-left: 2em;"></div>
	</div><br>
	
	<!-- Combo -->
	<div style="margin-bottom: 1.5em;">
		<div style="float: left;"><select id="comboProvincia" name="provincia" class="rup-combo" ></select></div>
		<div style="float: left; margin-left: 2em;"><select id="comboComarca" class="rup-combo"></select></div>
	</div><br>
	
	<!-- Autocomplete / Tooltip -->
	Autocomplete (lenguaje):<input id="autocomplete" name="autocomplete" />&nbsp; Tooltip: <input id="tooltip" name="tooltip" title="Introduzca su nombre."/><br><br>
	
	<!-- Pestañas -->
	<div id="tabs" style="width: 40em;"></div><br>
</div>