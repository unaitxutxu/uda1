<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2>USUARIO</h2>

<jsp:include page="includes/usuarioFilterForm.jsp"></jsp:include>

<table id="usuario" class="table table-striped table-bordered" 
	data-url-base="./tableUsuario" <%-- Table usuario se sustitulle por usuario que corresponde al controller,este es de pruebas --%>
	data-filter-form="#usuario_filter_form" 
	cellspacing="0" width="100%">
        <thead>
            <tr>
	                <th data-col-prop="id" data-col-sidx="ID" >id</th>
	                <th data-col-prop="nombre" data-col-sidx="NOMBRE" >nombre</th>
	                <th data-col-prop="apellido1" data-col-sidx="APELLIDO1" >apellido1</th>
	                <th data-col-prop="apellido2" data-col-sidx="APELLIDO2" >apellido2</th>
	                <th data-col-prop="ejie" data-col-sidx="EJIE" data-col-type="Checkbox">ejie</th>
	                <th data-col-prop="fechaAlta" data-col-sidx="FECHA_ALTA" >fechaAlta</th>
	                <th data-col-prop="fechaBaja" data-col-sidx="FECHA_BAJA" >fechaBaja</th>
	                <th data-col-prop="rol" data-col-sidx="ROL" data-col-type="Combo">rol</th>
	                <th data-col-prop="fechaModif" data-col-sidx="FECHA_MODIF" >fechaModif</th>
            </tr>
        </thead>
        <tfoot>
          <tr>
	              <th>id</th>
	              <th>nombre</th>
	              <th>apellido1</th>
	              <th>apellido2</th>
	              <th>ejie</th>
	              <th>fechaAlta</th>
	              <th>fechaBaja</th>
	              <th>rol</th>
	              <th>fechaModif</th>
          </tr>
        </tfoot>
</table>

<jsp:include page="includes/usuarioEdit.jsp"></jsp:include>