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
    <h2 class="title mb-3 page-header">Tipografía</h2>


    <p>El tema por defecto de UDA define una serie de estilos predeterminados para las aplicaciones.</p>

    <p>Así pues, se consideran los siguientes valores:</p>

    <ul>
      <li><code>font-family</code> : El tipo de letra utilizado por defecto es "Roboto".</li>
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

    <p>Este sería el aspecto de los botones <code>rup_button</code> de UDA:</p>

    <div class="example">
      <div class="row">
        <div class="col-md-12">
          <!-- Standard button -->
          <button type="button" class="rup-button">Default</button>
			
          <button type="button" id="dropdownHtmlListButton">Lista HTML</button>
          <ul id="dropdownHtmlList" class="rup-dropdown-option-list">
          	<spring:url value="/styleGuide" var="urlHashtag" htmlEscape="true"/>
          	<li><a href="${urlHashtag}" id="dropdownElem1">Elemento 1</a></li>
          	<li><a href="${urlHashtag}" id="dropdownElem2">Elemento 2</a></li>
          	<li><a href="${urlHashtag}" id="dropdownElem3">Elemento 3</a></li>
          	<li class="divider"></li>
          	<li><a href="${urlHashtag}" id="dropdownElem4">Elemento 4</a></li>
          </ul>
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

          <!-- Indicates a dangerous or potentially negative action -->
          <button type="button" class="btn btn-danger">Danger</button>

          <!-- Indicates caution should be taken with this action -->
          <button type="button" class="btn btn-warning">Warning</button>

          <!-- Contextual button for informational alert messages -->
          <button type="button" class="btn btn-info">Info</button>
          
          <button type="button" class="btn btn-light">Light</button>
          
          <button type="button" class="btn btn-dark">Dark</button>

          <!-- Deemphasize a button by making it look like a link while maintaining button behavior -->
          <button type="button" class="btn btn-link">Link</button>
        </div>
      </div>
    </div>
    
    <p>Los botones de bootstrap materializados <code>.btn-material</code> pueden ser presentados con diferentes énfasis y estados.</p>
    <p>Además, añadiendo la clase <code>.material-rounded</code> las esquinas serán redondeadas.</p>
    <h3>Énfasis alto</h3>

    <div class="example">
      <div class="row">
        <div class="col-md-12">
          <button type="button" class="btn-material btn-material-primary-high-emphasis"><span>Principal</span></button>

          <button type="button" class="btn-material btn-material-secondary-high-emphasis"><span>Secundario</span></button>

          <button type="button" class="btn-material btn-material-success-high-emphasis"><span>Success</span></button>

          <button type="button" class="btn-material material-rounded btn-material-danger-high-emphasis"><span>Danger</span></button>

          <button type="button" class="btn-material material-rounded btn-material-warning-dark-high-emphasis"><span>Warning</span></button>

          <button type="button" class="btn-material material-rounded btn-material-info-high-emphasis"><span>Info</span></button>
        </div>
      </div>
    </div>
    
    
    <h3>Énfasis medio</h3>
    
    <div class="example">
      <div class="row">
        <div class="col-md-12">
          <button type="button" class="btn-material btn-material-primary-medium-emphasis"><span>Principal</span></button>

          <button type="button" class="btn-material btn-material-secondary-medium-emphasis"><span>Secundario</span></button>

          <button type="button" class="btn-material btn-material-success-medium-emphasis"><span>Success</span></button>

          <button type="button" class="btn-material material-rounded btn-material-danger-medium-emphasis"><span>Danger</span></button>

          <button type="button" class="btn-material material-rounded btn-material-warning-dark-medium-emphasis"><span>Warning</span></button>

          <button type="button" class="btn-material material-rounded btn-material-info-medium-emphasis"><span>Info</span></button>
        </div>
      </div>
    </div>
    
    <h3>Énfasis bajo</h3>
    
    <div class="example">
      <div class="row">
        <div class="col-md-12">
          <button type="button" class="btn-material btn-material-primary-low-emphasis"><span>Principal</span></button>

          <button type="button" class="btn-material btn-material-secondary-low-emphasis"><span>Secundario</span></button>

          <button type="button" class="btn-material btn-material-success-low-emphasis"><span>Success</span></button>

          <button type="button" class="btn-material material-rounded btn-material-danger-low-emphasis"><span>Danger</span></button>

          <button type="button" class="btn-material material-rounded btn-material-warning-dark-low-emphasis"><span>Warning</span></button>

          <button type="button" class="btn-material material-rounded btn-material-info-low-emphasis"><span>Info</span></button>
        </div>
      </div>
    </div>
    
    <h3>Desactivados (Alto, medio y bajo énfasis)</h3>
    
    <div class="example">
      <div class="row">
        <div class="col-md-12">
          <button type="button" class="btn-material btn-material-primary-high-emphasis" disabled><span>Principal</span></button>

          <button type="button" class="btn-material material-rounded btn-material-secondary-high-emphasis" disabled><span>Secundario</span></button>

          <button type="button" class="btn-material btn-material-success-medium-emphasis" disabled><span>Success</span></button>

          <button type="button" class="btn-material material-rounded btn-material-danger-medium-emphasis" disabled><span>Danger</span></button>

          <button type="button" class="btn-material btn-material-warning-dark-low-emphasis" disabled><span>Warning</span></button>

          <button type="button" class="btn-material material-rounded btn-material-info-low-emphasis" disabled><span>Info</span></button>
        </div>
      </div>
    </div>
    
    <h3>Con iconos</h3>
    
    <p>En caso de querer añadir iconos a los botones, sería tan sencillo como insertarle un elemento <code>i</code> con su clase correspondiente.</p>
    <p>UDA usa los iconos material del proyecto <a href="https://materialdesignicons.com">Material Design Icons</a> donde además, pueden obtenerse ejemplos.</p>
    
    <div class="example">
      <div class="row">
        <div class="col-md-12">
          <button type="button" class="btn-material btn-material-lg btn-material-primary-high-emphasis">
          	<i class="mdi mdi-plus"></i>
          	<span>Añadir</span>
          </button>

          <button type="button" class="btn-material btn-material-lg material-rounded btn-material-secondary-high-emphasis">
          	<i class="mdi mdi-pencil"></i>
          	<span>Editar</span>
          </button>

          <button type="button" class="btn-material btn-material-lg btn-material-success-medium-emphasis">
          	<i class="mdi mdi-content-copy"></i>
          	<span>Clonar</span>
          </button>

          <button type="button" class="btn-material btn-material-lg material-rounded btn-material-danger-medium-emphasis">
          	<i class="mdi mdi-delete"></i>
          	<span>Eliminar</span>
          </button>

          <button type="button" class="btn-material btn-material-lg btn-material-warning-dark-low-emphasis">
          	<i class="mdi mdi-reload"></i>
          	<span>Recargar</span>
          </button>

          <button type="button" class="btn-material btn-material-lg material-rounded btn-material-info-low-emphasis">
          	<i class="mdi mdi-file"></i>
          	<span>Informes</span>
          </button>
        </div>
      </div>
      <div class="row mt-4">
        <div class="col-md-12">
          <button type="button" class="btn-material btn-material-primary-high-emphasis">
          	<i class="mdi mdi-plus"></i>
          	<span>Añadir</span>
          </button>

          <button type="button" class="btn-material material-rounded btn-material-secondary-high-emphasis">
          	<i class="mdi mdi-pencil"></i>
          	<span>Editar</span>
          </button>

          <button type="button" class="btn-material btn-material-success-medium-emphasis">
          	<i class="mdi mdi-content-copy"></i>
          	<span>Clonar</span>
          </button>

          <button type="button" class="btn-material material-rounded btn-material-danger-medium-emphasis">
          	<i class="mdi mdi-delete"></i>
          	<span>Eliminar</span>
          </button>

          <button type="button" class="btn-material btn-material-warning-dark-low-emphasis">
          	<i class="mdi mdi-reload"></i>
          	<span>Recargar</span>
          </button>

          <button type="button" class="btn-material material-rounded btn-material-info-low-emphasis">
          	<i class="mdi mdi-file"></i>
          	<span>Informes</span>
          </button>
        </div>
      </div>
      <div class="row mt-4">
        <div class="col-md-12">
          <button type="button" class="btn-material btn-material-sm btn-material-primary-high-emphasis">
          	<i class="mdi mdi-plus"></i>
          	<span>Añadir</span>
          </button>

          <button type="button" class="btn-material btn-material-sm material-rounded btn-material-secondary-high-emphasis">
          	<i class="mdi mdi-pencil"></i>
          	<span>Editar</span>
          </button>

          <button type="button" class="btn-material btn-material-sm btn-material-success-medium-emphasis">
          	<i class="mdi mdi-content-copy"></i>
          	<span>Clonar</span>
          </button>

          <button type="button" class="btn-material btn-material-sm material-rounded btn-material-danger-medium-emphasis">
          	<i class="mdi mdi-delete"></i>
          	<span>Eliminar</span>
          </button>

          <button type="button" class="btn-material btn-material-sm btn-material-warning-dark-low-emphasis">
          	<i class="mdi mdi-reload"></i>
          	<span>Recargar</span>
          </button>

          <button type="button" class="btn-material btn-material-sm material-rounded btn-material-info-low-emphasis">
          	<i class="mdi mdi-file"></i>
          	<span>Informes</span>
          </button>
        </div>
      </div>
    </div>
    
    <h3>Diferentes tamaños</h3>
    
    <p>Mediante la clase <code>.btn-material-sm</code> el botón será mas pequeño, en cambio si se usa la clase <code>.btn-material-lg</code>, será mas grande.</p>
    
    <div class="example">
      <div class="row">
        <div class="col-md-12">
          <button type="button" class="btn-material btn-material-lg btn-material-primary-high-emphasis"><span>Principal</span></button>

          <button type="button" class="btn-material btn-material-lg btn-material-primary-medium-emphasis"><span>Secundario</span></button>
          
          <button type="button" class="btn-material btn-material-lg btn-material-primary-low-emphasis"><span>Principal</span></button>
          
          <button type="button" class="btn-material btn-material-lg material-rounded btn-material-primary-high-emphasis"><span>Principal</span></button>

          <button type="button" class="btn-material btn-material-lg material-rounded btn-material-primary-medium-emphasis"><span>Secundario</span></button>
          
          <button type="button" class="btn-material btn-material-lg material-rounded btn-material-primary-low-emphasis"><span>Principal</span></button>
        </div>
      </div>
	  <div class="row mt-4">
        <div class="col-md-12">
          <button type="button" class="btn-material btn-material-primary-high-emphasis"><span>Principal</span></button>

          <button type="button" class="btn-material btn-material-primary-medium-emphasis"><span>Secundario</span></button>
          
          <button type="button" class="btn-material btn-material-primary-low-emphasis"><span>Principal</span></button>
          
          <button type="button" class="btn-material material-rounded btn-material-primary-high-emphasis"><span>Principal</span></button>

          <button type="button" class="btn-material material-rounded btn-material-primary-medium-emphasis"><span>Secundario</span></button>
          
          <button type="button" class="btn-material material-rounded btn-material-primary-low-emphasis"><span>Principal</span></button>
        </div>
      </div>
      <div class="row mt-4">
        <div class="col-md-12">
          <button type="button" class="btn-material btn-material-sm btn-material-primary-high-emphasis"><span>Principal</span></button>

          <button type="button" class="btn-material btn-material-sm btn-material-primary-medium-emphasis"><span>Secundario</span></button>
          
          <button type="button" class="btn-material btn-material-sm btn-material-primary-low-emphasis"><span>Principal</span></button>
          
          <button type="button" class="btn-material btn-material-sm material-rounded btn-material-primary-high-emphasis"><span>Principal</span></button>

          <button type="button" class="btn-material btn-material-sm material-rounded btn-material-primary-medium-emphasis"><span>Secundario</span></button>
          
          <button type="button" class="btn-material btn-material-sm material-rounded btn-material-primary-low-emphasis"><span>Principal</span></button>
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
      <li><code>font-family</code> : El tipo de letra utilizado por defecto es "Roboto".</li>
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
    
    <p>En cambio, si usamos los estilos de bootstrap materializados a la hora de diseñar los campos de los formularios, el estilo será el siguiente:</p>

    <div class="example">
    	<p>Input básico</p>
		<div class="form-groupMaterial">      
			<input type="text" id="textFieldBasicoBootstrapMaterialized">
			<label for="textFieldBasicoBootstrapMaterialized">Campo de texto Bootstrap Materializado</label>
		</div>
    	
    	<p>Input con required</p>
		<div class="form-groupMaterial">      
			<input type="text" id="textFieldRequiredBootstrapMaterialized" required>
			<label for="textFieldRequiredBootstrapMaterialized">Campo de texto Bootstrap Materializado</label>
		</div>
		
		<p>Input con placeholder</p>
		<div class="form-groupMaterial">
			<input type="password" id="passwordPlaceholderBootstrapMaterialized" placeholder="Password" >
			<label for="passwordPlaceholderBootstrapMaterialized">Password</label>
		</div>
		
		<p>Input con required y placeholder</p>
		<div class="form-groupMaterial">      
			<input type="text" id="textFieldRequiredPlaceholderBootstrapMaterialized" placeholder="Soy un placeholder..." required>
			<label for="textFieldRequiredPlaceholderBootstrapMaterialized">Campo de texto Bootstrap Materializado</label>
		</div>
    </div>
    
    <h3>Autocomplete</h3>

    <div class="form-group">
		<input id="autocompleteDefault" name="autocompleteDefault" /> 
		<label for="autocompleteDefault">Autocomplete</label>
	</div>
	
	<div class="form-groupMaterial">
		<input id="autocompleteMaterialized" name="autocompleteMaterialized" /> 
		<label for="autocompleteMaterialized">Autocomplete Material</label>
	</div>

    <h3>Select</h3>

    <p>Del mismo modo se trata de obtener una apariencia similar en el uso de los controles <code>select</code>.</p>

    <p>Las propiedades aplicadas a los estilos son:</p>

    <ul>
      <li><code>font-family</code> : El tipo de letra utilizado por defecto es "Roboto".</li>
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
    
    <p>Combo UDA Materializado:</p>

    <div class="example">
   	  <label for="rupComboMaterial">Opciones</label>
      <select  id="rupComboMaterial">
        <option>Opción 1</option>
        <option>Opción 2</option>
        <option>Opción 3</option>
        <option>Opción 4</option>
        <option>Opción 5</option>
      </select>
    </div>
    
    <div class="row">
        <div class="col-md-6">
		    <h3>Radio</h3>
		    
		    <p>Aplicando los estilos de bootstrap materializado a este elemento obtenemos el siguiente resultado:</p>
		    
		    <div class="radio-material">
		      <input id="opcionRadioMaterial-1" type="radio" name="materialRadio" checked>
		      <label for="opcionRadioMaterial-1">Opción 1</label>
		    </div>
		    <div class="radio-material">
		      <input id="opcionRadioMaterial-2" type="radio" name="materialRadio">
		      <label for="opcionRadioMaterial-2">Opción 2</label>
		    </div>
		</div>
        <div class="col-md-6">    
		    <h3>Checkbox</h3>
		    
		    <p>Los estilos de bootstrap materializado estilizan el elemento de la siguiente manera:</p>
		    
		    <div class="checkbox-material">
		      <input id="opcionCheckboxMaterial-1" type="checkbox" checked>
		      <label for="opcionCheckboxMaterial-1">Opción 1</label>
		    </div>
		    <div class="checkbox-material">
		      <input id="opcionCheckboxMaterial-2" type="checkbox">
		      <label for="opcionCheckboxMaterial-2">Opción 2</label>
		    </div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-6">
		    <h3>Radio Inline</h3>
		    
		    <p>Aplicando los estilos de bootstrap materializado a este elemento en su versión "inline" obtenemos el siguiente resultado:</p>
		    
		    <div class="radio-material radio-material-inline">
		      <input id="opcionRadioMaterialInline-1" type="radio" name="materialRadioInline">
		      <label for="opcionRadioMaterialInline-1">Opción 1</label>
		    </div>
		    <div class="radio-material radio-material-inline">
		      <input id="opcionRadioMaterialInline-2" type="radio" name="materialRadioInline">
		      <label for="opcionRadioMaterialInline-2">Opción 2</label>
		    </div>
		    <div class="radio-material radio-material-inline">
		      <input id="opcionRadioMaterialInline-3" type="radio" name="materialRadioInline">
		      <label for="opcionRadioMaterialInline-3">Opción 3</label>
		    </div>
		    <div class="radio-material radio-material-inline">
		      <input id="opcionRadioMaterialInline-4" type="radio" name="materialRadioInline">
		      <label for="opcionRadioMaterialInline-4">Opción 4</label>
		    </div>
		    <div class="radio-material radio-material-inline">
		      <input id="opcionRadioMaterialInline-5" type="radio" name="materialRadioInline" checked>
		      <label for="opcionRadioMaterialInline-5">Opción 5</label>
		    </div>
		    <div class="radio-material radio-material-inline">
		      <input id="opcionRadioMaterialInline-6" type="radio" name="materialRadioInline">
		      <label for="opcionRadioMaterialInline-6">Opción 6</label>
		    </div>
		</div>
        <div class="col-md-6">    
		    <h3>Checkbox Inline</h3>
		    
		    <p>Los estilos de bootstrap materializado estilizan el elemento en su versión "inline" de la siguiente manera:</p>
		    
		    <div class="checkbox-material checkbox-material-inline">
		      <input id="opcionCheckboxMaterialInline-1" type="checkbox">
		      <label for="opcionCheckboxMaterialInline-1">Opción 1</label>
		    </div>
		    <div class="checkbox-material checkbox-material-inline">
		      <input id="opcionCheckboxMaterialInline-2" type="checkbox" checked>
		      <label for="opcionCheckboxMaterialInline-2">Opción 2</label>
		    </div>
		    <div class="checkbox-material checkbox-material-inline">
		      <input id="opcionCheckboxMaterialInline-3" type="checkbox">
		      <label for="opcionCheckboxMaterialInline-3">Opción 3</label>
		    </div>
		    <div class="checkbox-material checkbox-material-inline">
		      <input id="opcionCheckboxMaterialInline-4" type="checkbox" checked>
		      <label for="opcionCheckboxMaterialInline-4">Opción 4</label>
		    </div>
		    <div class="checkbox-material checkbox-material-inline">
		      <input id="opcionCheckboxMaterialInline-5" type="checkbox">
		      <label for="opcionCheckboxMaterialInline-5">Opción 5</label>
		    </div>
		    <div class="checkbox-material checkbox-material-inline">
		      <input id="opcionCheckboxMaterialInline-6" type="checkbox" checked>
		      <label for="opcionCheckboxMaterialInline-6">Opción 6</label>
		    </div>
		</div>
	</div>
	
	<h3>Fecha</h3>
	
	<div class="example">
		<div class="form-groupMaterial"> 
			<input id="fechaMaterial" type="text" />
			<label for="fechaMaterial">Fecha 
				<span class="text-muted" id="fecha-mask"></span>
			</label>
		</div>
		<div class="form-groupMaterial"> 
			<input id="fechaMaterialPlaceholder" type="text" />
			<label for="fechaMaterialPlaceholder">Fecha</label>
		</div>
	</div>
	
	<h3>Textarea</h3>
	
	<div class="example">
		<p>Textarea básico</p>
		<div class="form-group">
			<label for="textarea">Textarea</label>
			<textarea id="textarea" class="form-control"></textarea>
		</div>
		
		<p>Textarea básico materializado</p>
		<div class="form-groupMaterial"> 
			<textarea id="textareaBasicoMaterial"></textarea>
			<label for="textareaBasicoMaterial">Textarea</label>
		</div>
		
		<p>Textarea con required materializado</p>
		<div class="form-groupMaterial"> 
			<textarea id="textareaRequiredMaterial" required></textarea>
			<label for="textareaRequiredMaterial">Textarea</label>
		</div>
		
		<p>Textarea con placeholder materializado // Textarea con required y placeholder materializado</p>
		<div class="form-row">
			<div class="form-groupMaterial col-sm-6">
				<textarea id="textareaPlaceholderMaterial" placeholder="Soy un placeholder..."></textarea>
				<label for="textareaPlaceholderMaterial">Textarea</label>
			</div>
			
			<div class="form-groupMaterial col-sm-6">
				<textarea id="textareaPlaceholderRequiredMaterial" placeholder="Soy un placeholder..." required></textarea>
				<label for="textareaPlaceholderRequiredMaterial">Textarea</label>
			</div>
		</div>
	</div>
  </section>  