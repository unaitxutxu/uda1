<%--  
 -- Copyright 2011 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, VersiÃ³n 1.1 exclusivamente (la Â«LicenciaÂ»);
 -- Solo podrÃ¡ usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislaciÃ³n aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye Â«TAL CUALÂ»,
 -- SIN GARANTÃAS NI CONDICIONES DE NINGÃN TIPO, ni expresas ni implÃ­citas.
 -- VÃ©ase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
 
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>	
<section>
	<h2>
		Hora
	</h2>
	<p>
		El usuario puede introducir y seleccionar una hora tanto de forma manual como visual, moviéndose fácilmente por las horas y los minutos, recibiendo ayudas y sugerencias para minimizar las posibilidades de introducir una hora incorrecta.
	</p>
	
	<h4>
		Hora simple
	</h4>
	<p>
		El uso más básico del componente es mostrar un campo de texto simple con un control que permite desplegar el control de selección de hora.
		Permite mostrar al usuario a modo de ayuda, la máscara de entrada que se va a emplear para la hora a introducir. Existen dos modos de hacerlo:
	</p>
	<ul>
		<li><code>labelMaskId</code>: Permite indicar el id del elemento en el que se indicará el valor de la máscara.</li>
		<li><code>placeholderMask</code>: Muestra en el placeholder del elemento la máscara de hora.</li>
	</ul>
	
	<div class="example">
		<div class="form-group">
			<label for="hora">Hora </label><label for="hora" id="hora-mask"></label>:
			<input id="hora" type="text"/>
		</div>
		<div class="form-group">
			<label for="horaPlaceholder">Hora </label>:
			<input id="horaPlaceholder" type="text"/>
		</div>
	</div>
	
	<p>
		El componente permite configurar muchos aspectos de su comportamiento. Un ejemplo de personalización sería:	
	</p>
	<div class="example">
		<div class="form-group">
			<label for="hora2">Hora </label>:
			<input id="hora2" type="text"/>
		</div>
	</div>
	
	<h4>
		Selector en línea
	</h4>
	
	<p>
		El componente hora permite su visualización en modo directo, mostrando el selector de hora completo en vez de estar asociado a un campo concreto. 
	</p>
	
	<div class="example">
		<div id="hora_inline"></div>
	</div>
	
</section>
