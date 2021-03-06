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
    <h2 class="page-header">Tipografía</h2>


    <p>El tema por defecto de UDA define una serie de estilos predeterminados para las aplicaciones.</p>

    <p>Así pues, se consideran los siguientes valores:</p>

    <ul>
      <li><code>font-family</code> : El tipo de letra utilizado por defecto es "Open Sans".</li>
      <li><code>font-size</code> : Se establece un valor de <strong>12px</strong> para el tamaño de letra</li>
    </ul>

    <div class="example">
      <h1>h1. Encabezado <small>Texto secundario</small></h1>
      <h2>h2. Encabezado <small>Texto secundario</small></h2>
      <h3>h3. Encabezado <small>Texto secundario</small></h3>
      <h4>h4. Encabezado <small>Texto secundario</small></h4>
      <h5>h5. Encabezado <small>Texto secundario</small></h5>
      <h6>h6. Encabezado <small>Texto secundario</small></h6>
    </div>

  </section>

  <section>
    <h2 class="page-header">Botones</h2>

    <p>Se determina una misma apariencia para los botones nativos HTML, de bootstrap y UDA.</p>

    <p>Así pues la apariencia de los botones nativos <code>button</code>, <code>&lt;input type="text" /&gt;</code> y <code>&lt;input type="submit" /&gt;</code>sería:</p>

    <div class="example">
      <div class="row">
        <div class="col-md-12">
          <button class="" type="submit">Submit</button>
          <button class="" >Botón defecto</button>
          <input type="button" value="Input button" />
          <input type="submit" value="Input submit" />
        </div>
      </div>
    </div>

    <p>Los botones de bootstrap <code>.btn</code> se visualizarían del siguiente modo:</p>

    <div class="example">
      <div class="row">
        <div class="col-md-12">
          <!-- Standard button -->
          <button type="button" class="btn btn-primary">Principal</button>

          <!-- Provides extra visual weight and identifies the primary action in a set of buttons -->
          <button type="button" class="btn btn-secondary">Secundario</button>

          <!-- Indicates a successful or positive action -->
          <button type="button" class="btn btn-success">Success</button>

          <!-- Contextual button for informational alert messages -->
          <button type="button" class="btn btn-info">Info</button>

          <!-- Indicates caution should be taken with this action -->
          <button type="button" class="btn btn-warning">Warning</button>

          <!-- Indicates a dangerous or potentially negative action -->
          <button type="button" class="btn btn-danger">Danger</button>

          <!-- Deemphasize a button by making it look like a link while maintaining button behavior -->
          <button type="button" class="btn btn-link">Link</button>
        </div>
      </div>
    </div>

    <p>Este sería el aspecto de los botones <code>rup_button</code> de UDA:</p>

    <div class="example">
      <div class="row">
        <div class="col-md-12">
          <!-- Standard button -->
          <button type="button" class="rup-button">Default</button>

          <button type="button" id="dropdownHtmlListButton">Lista HTML</button>
          <ul id="dropdownHtmlList" class="rup-dropdown-option-list">
          	<li><a href="#" id="dropdownElem1">Elemento 1</a></li>
          	<li><a href="#" id="dropdownElem2">Elemento 2</a></li>
          	<li><a href="#" id="dropdownElem3">Elemento 3</a></li>
          	<li class="divider"></li>
          	<li><a href="#" id="dropdownElem4">Elemento 4</a></li>
          </ul>
        </div>
      </div>
    </div>
  </section>

  <section>
    <h2 class="page-header">Formulario</h2>

    <p>El tema de UDA proporciona una apariencia unificada de los diferentes elementos que pueden estar presentes en un formulario.</p>

    <h3>Campos de texto</h3>

    <p>Se busca normalizar la apariencia de los campos de texto por defecto así como de los que hacen uso de los estilos de bootstrap.</p>
    <p>Un campo de texto por defecto hace uso de las siguientes propiedades de css:</p>

    <ul>
      <li><code>font-family</code> : El tipo de letra utilizado por defecto es "Open Sans".</li>
      <li><code>font-size</code> : Se establece un valor de <strong>1rem</strong> para el tamaño de letra. Como es relativo al tamaño base definido para <code>html</code> el valor por defecto será de <strong>12px</strong>.</li>
      <li><code>height</code> : Se establece un valor de <strong>2rem</strong> para la altura del campo de texto. Como es relativo al tamaño base definido para <code>html</code> el valor por defecto será de <strong>24px</strong>.</li>
    </ul>

    <div class="example">
      <label for="textFieldDefault">Campo de texto por defecto</label>
      <input type="text" id="textFieldDefault" placeholder="default"/>
    </div>

    <p>En caso de utilizar los estilos de bootstrap a la hora de diseñar los campos de los formularios el estilo será similar.</p>

    <div class="example">
      <div class="form-group">
        <label for="textFieldBootstrap">Campo de texto Bootstrap</label>
        <input type="text" class="form-control" id="textFieldBootstrap" placeholder=".form-control">
      </div>
      <div class="form-group">
        <label for="passwordFieldBootstrap">Password</label>
        <input type="password" class="form-control" id="passwordFieldBootstrap" placeholder="Password">
      </div>
    </div>

    <h3>Select</h3>

    <p>Del mismo modo se trata de obtener una apariencia similar en el uso de los controles <code>select</code>.</p>

    <p>Las propiedades aplicadas a los estilos son:</p>

    <ul>
      <li><code>font-family</code> : El tipo de letra utilizado por defecto es "Open Sans".</li>
      <li><code>font-size</code> : Se establece un valor de <strong>1rem</strong> para el tamaño de letra. Como es relativo al tamaño base definido para <code>html</code> el valor por defecto será de <strong>12px</strong>.</li>
      <li><code>height</code> : Se establece un valor de <strong>2rem</strong> para la altura del combo. Como es relativo al tamaño base definido para <code>html</code> el valor por defecto será de <strong>24px</strong>.</li>
    </ul>

    <p>Así pués la apariencia de los diferentes combos que pueden ser incluidos en el formulario es la siguiente:</p>

    <p>Combo por defecto:</p>

    <div class="example">
      <select  id="selectDefault">
        <option>Opción 1</option>
        <option>Opción 2</option>
        <option>Opción 3</option>
        <option>Opción 4</option>
        <option>Opción 5</option>
      </select>
    </div>

    <p>Combo Bootstrap:</p>

    <div class="example">
      <select  id="selectBootstrap" class="form-control">
        <option>Opción 1</option>
        <option>Opción 2</option>
        <option>Opción 3</option>
        <option>Opción 4</option>
        <option>Opción 5</option>
      </select>
    </div>

    <p>Combo UDA:</p>

    <div class="example">
      <select  id="rupCombo">
        <option>Opción 1</option>
        <option>Opción 2</option>
        <option>Opción 3</option>
        <option>Opción 4</option>
        <option>Opción 5</option>
      </select>
    </div>
  </section>

