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

 <div class="container">
  <section>
    <h2 class="page-header">Listas de tareas</h2>
    
   
   	<div id="feedback"></div>
   	
    <div id="board">
	
    		
   		 
    
    
    </div>
 
    
    
    <button type="button" id="fabButton" data-fab="true"
					data-fixed="true" data-list="fabButtonFixedList"
					class="rup-button-fab rup-button-fixed ui-button ui-corner-all ui-widget rup-button">
					<i class="fa fa-plus" aria-hidden="true"></i>
				</button>
</section>

<div id="taskListDetailDialog" >
<div class="container">
	<form >
		<div class="row">
			<div class="form-group">
		    	<label for="exampleInputEmail1">Titulo</label>
		    	<input type="text" class="form-control" id="taskListName" placeholder="Introduzca el título de la lista de tareas">
		  	</div>
		  	
		  	<div class="form-group">
		    	<label for="exampleInputEmail1">Descripcion</label>
		    	<input type="text" class="form-control" id="taskListDescription" placeholder="Introduzca la descripción de la lista de tareas">
		  	</div>
		  	<input type="hidden" id="taskListId"/>
		
		</div>
	
	</form>	
	</div>
</div>

   </div>



<script id="board-template" type="text/x-handlebars-template">
	<div class="row">
  {{#each this}}
	<div class="col-md-4">


    <div id="list_{{id}}" data-list-id="{{id}}" class="card listItem shadow-2" >
	
	      
    </div>


{{#showHr @index}}
    	</div>
		<div class="row">
	{{/showHr}}
</div>
  {{/each}}

	</div>
</script>


<script id="list-template" type="text/x-handlebars-template">
      <div class="card-block" data-value="{{id}}">
        <h4 class="card-title">{{name}}
			<div class="dropdown align-right"> 
			<button type="button" id="btnOptions_{{id}}" class="btn btn-secondary " " data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
				<i class="fa fa-ellipsis-v" aria-hidden="true"></i>
			</button>
			<div class="dropdown-menu" aria-labelledby="btnOptions_{{id}}">
    			<a class="dropdown-item list-modify" data-listId="{{id}}" href="#">Modificar</a>
    			<a class="dropdown-item list-delete" data-listId="{{id}}" href="#">Eliminar</a>
  			</div>
			</div>

		</h4> 
		<h6 class="card-subtitle mb-2 text-muted">{{taskNum}} Tareas</h6>
		<p class="card-text">{{description}}</p>
	  	<a class="card-link" id="><span>Mostrar tareas</span></a>
      	<a class="card-link" href="/x21aModulesWar/task/maint/{{id}}"><span>Ir detalle</span></a>
      </div>
</script>