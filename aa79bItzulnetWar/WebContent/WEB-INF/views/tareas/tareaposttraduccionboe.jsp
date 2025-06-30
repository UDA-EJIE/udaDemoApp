<%@page import="com.ejie.aa79b.model.enums.ActivoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in aa79b-content" id="divPreciosPublicos">
	<div id="preciospublicos_div" class="rup-table-container">
		<!-- Feedback de la tabla -->
		<div id="ejecutarTareaDialog_toolbar"></div>
		<div id="ejecutarTareaDialog_feedback"></div>
		<input type="hidden" id="idiomaDest" name="idiomaDest" />
		<!-- Botonera de la tabla -->
		<div id="preciospublicos_filter_div" class="container">
			<!-- Capa contenedora del formulario de filtrado -->
			<form id="ejecutarTareaDialog_form">
				<!-- Barra de herramientas del formulario de filtrado -->
				<div class="form-group row">
					<input type="hidden" name="numExp" id="numExp_form"> 
					<input type="hidden" name="anyo" id="anyo_form"> 
					<input type="hidden" name="idTarea" id="idTarea_form"> 
					<input type="hidden" name="tipoTarea.id015" id="idTipoTarea_form">
					<input type="hidden" name="ejecucionTareas.horasTarea"
					
						id="horasTarea_form">
					<div class="form-group col-xs-2">
						<spring:message code="comun.tipoDeTarea" />:
					</div>
					<div class="form-group col-xs-9">
						<b><c:out value="${descripcionTarea}"></c:out></b>
					</div>
				</div>
				<div class="form-group row">
					<div class="form-group col-md-5 col-xl-5 ">
						<label for="indVigor_filter_table" class="control-label col-xs-12 noNegrita" style="font-size: 1em;"><spring:message code="label.haHabidoCorr" />?</label>
						<div class="col-xs-12">
							<input type="checkbox" name="indDesglose" class="form-control" id="ind_correcciones_filter" value="S" data-switch-pestana="true" />
						</div>
					</div>
				</div>
				<div class="form-group row">
					<div class="col-lg-6">
						<label for="nombreFicheroInfo_1" class="control-label col-xs-12"><spring:message code="label.informeDeCorr" /> (*):</label>
						<div class="col-xs-12 aa79bObsTradRevContainer">
							<span id="enlaceDescargaDetalle_1" style="display:none;margin-right: 1rem;"></span>           
							<div id="divFicheroFileCorreciones" class="form-group">                      
				                <button id="pidButtonFileCorreciones" type="button" class="ui-button ui-corner-all ui-widget" style="margin-top:0"><spring:message code="label.adjuntarFichero"/></button>
<!-- 				                <span id="divNomFicheroFileCorreciones"> -->
				                	<input type="text" name="nombreFicheroInfo_1" class="form-control" id="nombreFicheroInfo_1" readonly="readonly" style="width:200px; display:inline-block" />
<!-- 				                </span> -->
							</div>
						</div>
					</div>	
					
					<div class="col-lg-6">
						<label for="nombreFicheroInfo_2" class="control-label col-xs-12"><spring:message code="label.documentoDeCorr" /> (PDF):</label>
						<div class="col-xs-12 aa79bObsTradRevContainer">
							<span id="enlaceDescargaDetalle_2" style="display:none;margin-right:1rem;"></span>           
							<div id="divDocumentoCorreciones" class="form-group col-lg-12">                       
				                <button id="pidButtonDocumentoCorreciones" type="button" class="ui-button ui-corner-all ui-widget" style="margin-top:0"><spring:message code="label.adjuntarFichero"/></button>
				                <input type="text" name="nombreFicheroInfo_2" class="form-control" id="nombreFicheroInfo_2" readonly="readonly" style="width:200px; display:inline-block" />
							</div>
						</div>
					</div>	
				</div>
			</form>
		</div>
	</div>
	<div id="subidaFicheroPidFinal" class="capaPIDenPestanaDoc oculto">
		<fieldset id="subidaFicheroPidFinal_fieldset" >			
			<div id="subidaFicheroPidFinal_feedback"></div>
			<form id="subidaFicheroPidFinal_form" enctype="multipart/form-data" method="POST" class="form-horizontal">
				<div class="form-group row">
					<label for="ficheroFinal" class="control-label col-md-3"><spring:message code="label.fichero" /> (*):</label>
					<div class="col-md-6" id="divFicheroFinal">
						<input type="file" name="fichero" id="ficheroFinal"  class="form-control">
					</div>
					<input type="hidden" id="reqEncriptado" name="reqEncriptado" />
				</div>
			</form>
			<p class="scheduler-border" style="text-align: center;" id="txtEncriptadoFinal"><em><strong><spring:message code="mensaje.fichero.encriptadoRequerido" /></strong></em></p>
		</fieldset>
	</div>
</div>

