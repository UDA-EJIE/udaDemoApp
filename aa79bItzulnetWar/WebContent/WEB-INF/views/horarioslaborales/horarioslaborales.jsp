<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in" id="divHorariosLaborales"> 
	<h2><spring:message code="menu.horarioLaboralServicio"/></h2>
		<hr class="mb-2">
		<div id="horarioslaborales_div" class="rup-table-container">
		<div id="horarioslaborales_feedback"></div>						<!-- Feedback de la tabla --> 
		<div id="horarioslaborales_toolbar"></div>							<!-- Botonera de la tabla -->			
		<div id="horarioslaborales_grid_div">
			<!-- Tabla -->
			<table id="horarioslaborales"></table>
			<!-- Barra de paginación -->
			<div id="horarioslaborales_pager"></div>
		</div>
	</div>	
</div>


<!-- Formulario de detalle -->
<div class="container-fluid">
<div id="horarioslaborales_detail_div" class="rup-table-formEdit-detail">
	<div class="ui-dialog-content ui-widget-content" >
		<form id="horarioslaborales_detail_form">					<!-- Formulario -->
			<div id ="horarioslaborales_detail_feedback"></div>		<!-- Feedback del formulario de detalle -->
			<div class="floating_left_pad_right col-xs-12">
				<!-- Campos del formulario de detalle -->
				<input type="hidden" name="id038" id="id038_detail_table"/>
				<!-- div class="row">
					<div class="form-group col-md-6 no-padding">						
							<label for="indActivo038_detail_table" class="control-label"><spring:message code="label.activo"/>:</label>
							<input class="form-control" type="checkbox" name="indActivo038" id="indActivo038_detail_table" value="S" data-switch="true" >									
					</div>
				</div-->
											
				<c:forEach begin="0" end="1" varStatus="status">
					
					<div class="row">
						<div class="col-xs-12 no-padding" style="clear: left; margin-top:1rem">
							<c:if test="${status.index == 0}">
								<label class="col-xs-2 no-padding" for="indVerano039_detail_table${status.index}"><spring:message code="label.horario.invierno"/>:</label>
							</c:if>
							<c:if test="${status.index != 0}">
								<label class="col-xs-2 no-padding" for="indVerano039_detail_table${status.index}"><spring:message code="label.horario.verano"/>:</label>
							</c:if>
							<label class="col-xs-5" for="indVerano021_detail_table${status.index}"><spring:message code="label.horario.manana"/></label>
							<label class="col-xs-5" for="indVerano021_detail_table${status.index}"><spring:message code="label.horario.tarde"/></label>
						</div>
					</div>
					<input type="hidden" name="horasHorarioses[${status.index}].indVerano039" class="formulario_linea_input" id="indVerano039_detail_table" value="${status.index}" />					
					<input type="hidden" name="horasHorarioses[${status.index}].idHorario039" class="formulario_linea_input" id="idHorario039_detail_table" />
					<div class="row">
						<div class="col-xs-12">
							<div class="col-xs-12 col-lg-2 no-padding">
								<label for="indVerano039_detail_table"><spring:message code="label.lunesJueves"/>:</label>
							</div>
							<div class="col-xs-12 col-md-6 col-lg-5 no-padding">
								<div class="form-group col-xs-3 no-padding">							
									<label class="oculto control-label" for="horaIniLjM039_${status.index}_detail_table"> <c:if test="${status.index == 0}"><spring:message code="label.horario.invierno"/></c:if><c:if test="${status.index != 0}"><spring:message code="label.horario.verano"/></c:if>, <spring:message code="label.lunesJueves"/>, <spring:message code="label.hinim"/></label>
									<input type="text" name="horasHorarioses[${status.index}].horaIniLjM039" class="form-control" id="horaIniLjM039_${status.index}_detail_table" maxlength="5"/>
								</div>							
								<div class="col-xs-1 no-padding">(*)</div>
								<div class="col-xs-2"><label for="indVerano039_detail_table" class="label-left">-</label></div>	
								<div class="form-group col-xs-3 no-padding">
									<label class="oculto control-label" for="horaFinLjM039_${status.index}_detail_table"> <c:if test="${status.index == 0}"><spring:message code="label.horario.invierno"/></c:if><c:if test="${status.index != 0}"><spring:message code="label.horario.verano"/></c:if>, <spring:message code="label.lunesJueves"/>, <spring:message code="label.hfinm"/></label>
									<input  type="text" name="horasHorarioses[${status.index}].horaFinLjM039" class="form-control" id="horaFinLjM039_${status.index}_detail_table" maxlength="5"/>
								</div>
								<div class="col-xs-1 no-padding">(*)</div>							
							</div>
							<div class="col-xs-12 col-md-6 col-lg-5 no-padding">
								<div class="form-group col-xs-3 no-padding">
									<label class="oculto control-label" for="horaIniLjT039_${status.index}_detail_table"> <c:if test="${status.index == 0}"><spring:message code="label.horario.invierno"/></c:if><c:if test="${status.index != 0}"><spring:message code="label.horario.verano"/></c:if>, <spring:message code="label.lunesJueves"/>, <spring:message code="label.hinit"/></label>
									<input type="text" name="horasHorarioses[${status.index}].horaIniLjT039" class="form-control" id="horaIniLjT039_${status.index}_detail_table" maxlength="5"/></div>
								<div class="col-xs-2"><label for="indVerano039_detail_table" class="label-left">-</label></div>	
								<div class="form-group col-xs-3 no-padding">
									<label class="oculto control-label" for="horaFinLjT039_${status.index}_detail_table"> <c:if test="${status.index == 0}"><spring:message code="label.horario.invierno"/></c:if><c:if test="${status.index != 0}"><spring:message code="label.horario.verano"/></c:if>, <spring:message code="label.lunesJueves"/>, <spring:message code="label.hfint"/></label>
									<input type="text" name="horasHorarioses[${status.index}].horaFinLjT039" class="form-control" id="horaFinLjT039_${status.index}_detail_table" maxlength="5"/></div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-xs-12">
							<div class="col-xs-12 col-lg-2 no-padding">
								<label for="indVerano039_detail_table"><spring:message code="label.viernes"/>:</label>
							</div>
							<div class="col-xs-12 col-md-6 col-lg-5 no-padding">
								<div class="form-group col-xs-3 no-padding">
									<label class="oculto control-label" for="horaIniVM039_${status.index}_detail_table"> <c:if test="${status.index == 0}"><spring:message code="label.horario.invierno"/></c:if><c:if test="${status.index != 0}"><spring:message code="label.horario.verano"/></c:if>, <spring:message code="label.viernes"/>, <spring:message code="label.hinim"/></label>
									<input type="text" name="horasHorarioses[${status.index}].horaIniVM039" class="form-control" id="horaIniVM039_${status.index}_detail_table" maxlength="5"/>
								</div>	
								<div class="col-xs-1 no-padding">(*)</div>	
								<div class="col-xs-2"><label for="indVerano039_detail_table" class="label-left">-</label></div>
								<div class="form-group col-xs-3 no-padding">
									<label class="oculto control-label" for="horaFinVM039_${status.index}_detail_table"> <c:if test="${status.index == 0}"><spring:message code="label.horario.invierno"/></c:if><c:if test="${status.index != 0}"><spring:message code="label.horario.verano"/></c:if>, <spring:message code="label.viernes"/>, <spring:message code="label.hfinm"/></label>
									<input type="text" name="horasHorarioses[${status.index}].horaFinVM039" class="form-control" id="horaFinVM039_${status.index}_detail_table" maxlength="5"/>
								</div>	
								<div class="col-xs-1 no-padding">(*)</div>	
							</div>
							<div class="col-xs-12 col-md-6 col-lg-5 no-padding">
								<div class="form-group col-xs-3 no-padding">
									<label class="oculto control-label" for="horaIniVT039_${status.index}_detail_table"> <c:if test="${status.index == 0}"><spring:message code="label.horario.invierno"/></c:if><c:if test="${status.index != 0}"><spring:message code="label.horario.verano"/></c:if>, <spring:message code="label.viernes"/>, <spring:message code="label.hinit"/></label>
									<input type="text" name="horasHorarioses[${status.index}].horaIniVT039" class="form-control" id="horaIniVT039_${status.index}_detail_table" maxlength="5"/>
								</div>	
								<div class="col-xs-2"><label for="indVerano039_detail_table" class="label-left">-</label></div>
								<div class="form-group col-xs-3 no-padding">
									<label class="oculto control-label" for="horaFinVT039_${status.index}_detail_table"> <c:if test="${status.index == 0}"><spring:message code="label.horario.invierno"/></c:if><c:if test="${status.index != 0}"><spring:message code="label.horario.verano"/></c:if>, <spring:message code="label.viernes"/>, <spring:message code="label.hfint"/></label>
									<input type="text" name="horasHorarioses[${status.index}].horaFinVT039" class="form-control" id="horaFinVT039_${status.index}_detail_table" maxlength="5"/>
								</div>	
							</div>
						</div>
					</div>
				</c:forEach>
				<!-- Fin campos del formulario de detalle -->
				
			</div>
		</form>
	</div>
	<!-- Botonera del formulario de detalle -->
	<div class="rup-table-buttonpane ui-widget-content ui-helper-clearfix">
		<div class="ui-dialog-buttonset">
			<!-- Botón Guardar -->
			<button id="horarioslaborales_detail_button_save" type="button">
				<spring:message code="save" />
			</button>
			<!-- Enlace cancelar -->
			<a href="javascript:void(0)" id="horarioslaborales_detail_link_cancel"
				class="rup-enlaceCancelar"><spring:message code="cancel" /></a>
		</div>
	</div>
</div>
</div>
