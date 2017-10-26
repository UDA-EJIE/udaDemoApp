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
	<div class="row">
	
	<input type="hidden" id="idTaskList" value='${taskList.id}'/>
	  <section>
	  	<div class="col-md-2"></div>
	  	<div class="col-md-8">
	  	<h2 class="page-header"><a href="/x21aModulesWar/taskList/maint">Listas de tareas</a> > ${taskList.name}</h2>
	    
	    
	    <div id="feedback"></div>
	    
	    <div id="tasks"></div>
	    </div>
	    <div class="col-md-2"></div>
	    
	</section>
	</div>
	</div>

<script  id="task-template" type="text/x-handlebars-template">
	
	<ul class="list-group" id="taskList">
  		{{#each this}}
  			<li class="task-list-item list-group-item {{#if done}}task-done{{/if}}" data-task-id="{{id}}">
				<label class="custom-control custom-checkbox ">
  <input type="checkbox" {{#if done}}checked="checked" {{/if}} class="task-done-checkbox custom-control-input" data-task-id="{{id}}">
  <span class="custom-control-indicator"></span>
  <span class="custom-control-description">{{name}}</span>
	
</label>
	<span class="delete-task align-right" data-task-id="{{id}}"> <i class="fa fa-trash " aria-hidden="true"></i></span>				
			</li>
  		{{/each}}
			<li class="list-group-item">
				<div class="input-group">
					<input type="text" id="newTask" name="taskName"  class="form-control"/>
					
					<button id="sendTask"  class="btn btn-secondary"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
				</div>
			</li>
	</ul>
	
</script>