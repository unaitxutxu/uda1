<%@include file="/WEB-INF/includeTemplate.inc"%>
<h1>Mantenimiento multi-entidad</h1>

	<div id="error" style="display:none"></div>
	<div id="localidad">
		<div id="contenido" style="margin-top:0.5em;margin-bottom:0.5em;width:600px;">
			<form id="searchForm">
				<div  class="formulario_legend" id="titleSearch_simple"><spring:message code="searchCriteria" />:</div>
				<fieldset style="border:1px solid #DADADA;" id="FIELDSET_SEARCH_localidad">
					<div class="formulario_columna_cnt">
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">code:</div>
							<input type="text" name="code" class="formulario_linea_input" id="code_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">descEs:</div>
							<input type="text" name="descEs" class="formulario_linea_input" id="descEs_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">descEu:</div>
							<input type="text" name="descEu" class="formulario_linea_input" id="descEu_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">css:</div>
							<input type="text" name="css" class="formulario_linea_input" id="css_search" />
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">provincia:</div>
							<select name="comarca.provincia.code" id="provincia_search" ></select>
						</div>
						<div class="formulario_linea_izda_float">
							<div class="formulario_linea_label">comarca:</div>
							<select name="comarca"  class="formulario_linea_input"  id="comarca_search"  ></select>
						</div>
					</div>
				</fieldset>
			</form>
		</div>

		<div id="RUP_GRID_localidad">
			<!-- Tabla -->
			<table id="GRID_localidad" cellpadding="0" cellspacing="0"></table>
			<!-- Barra de paginación -->
			<div id="pager" style="text-align:center;"></div>
		</div>
	</div>