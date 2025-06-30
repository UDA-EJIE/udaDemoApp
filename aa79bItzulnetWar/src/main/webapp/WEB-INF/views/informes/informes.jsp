<%@page import="com.ejie.aa79b.model.enums.MesesEnum"%>
<%@page import="com.ejie.aa79b.model.enums.TipoExpedienteGrupoEnum"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<div class="container-fluid aa79b-fade in" id="divInformesGeneral">
	<div id="divInformesCapa">
		<div id="divInformes">
			<h2><spring:message code="menu.informes"/></h2>
			<div id="informes_div" class="rup-table-container">
				<div id="informes_feedback"></div>					<!-- Feedback de la tabla --> 
				<div id="informes_toolbar"></div>
				<div id="informes_filter_div" class="filterForm"> <!-- Capa contenedora del formulario de filtrado -->
					<form id="informes_filter_form" class="form-horizontal">	
						<div id="informes_filter_toolbar" class="formulario_legend"></div>
						<fieldset id="informes_filter_fieldset" class="rup-table-filter-fieldset ">
							<div class="p-2">
								<div class="row">
									<div class="form-group col-md-3">
										<label for="informe_filter_table" class="control-label "><spring:message code="label.informe"/>:</label>
										<select class="form-control" id="informe_filter_table">
											<option value="<%=TipoExpedienteGrupoEnum.TRADUCCION_REVISION.getCode()%>"><spring:message code="label.informeTradRev"/></option>
											<option value="<%=TipoExpedienteGrupoEnum.INTERPRETACION.getCode()%>"><spring:message code="label.informeInterpretacion"/></option>
										</select>
									</div>
									<div class="form-group col-md-1">
										<label for="anyo_filter_table" class="control-label"><spring:message code="label.anyo"/>:</label>
										<div>
											<input type="text" name="anyo" class=" numeric" style="width: 40px;" id="anyo_filter_table" maxlength="2"/>
										</div>
									</div>
									<div class="form-group col-md-2">
										<label for="mes_filter_table" class="control-label "><spring:message code="label.mes"/>:</label>
										<select class="form-control" id="mes_filter_table">
											<option value="0" selected="true"><spring:message code="combo.todos"/></option>
											<option value="<%=MesesEnum.ENERO.getValue()%>"><spring:message code="<%=MesesEnum.ENERO.getLabel()%>"/></option>
											<option value="<%=MesesEnum.FEBRERO.getValue()%>"><spring:message code="<%=MesesEnum.FEBRERO.getLabel()%>"/></option>
											<option value="<%=MesesEnum.MARZO.getValue()%>"><spring:message code="<%=MesesEnum.MARZO.getLabel()%>"/></option>
											<option value="<%=MesesEnum.ABRIL.getValue()%>"><spring:message code="<%=MesesEnum.ABRIL.getLabel()%>"/></option>
											<option value="<%=MesesEnum.MAYO.getValue()%>"><spring:message code="<%=MesesEnum.MAYO.getLabel()%>"/></option>
											<option value="<%=MesesEnum.JUNIO.getValue()%>"><spring:message code="<%=MesesEnum.JUNIO.getLabel()%>"/></option>
											<option value="<%=MesesEnum.JULIO.getValue()%>"><spring:message code="<%=MesesEnum.JULIO.getLabel()%>"/></option>
											<option value="<%=MesesEnum.AGOSTO.getValue()%>"><spring:message code="<%=MesesEnum.AGOSTO.getLabel()%>"/></option>
											<option value="<%=MesesEnum.SEPTIEMBRE.getValue()%>"><spring:message code="<%=MesesEnum.SEPTIEMBRE.getLabel()%>"/></option>
											<option value="<%=MesesEnum.OCTUBRE.getValue()%>"><spring:message code="<%=MesesEnum.OCTUBRE.getLabel()%>"/></option>
											<option value="<%=MesesEnum.NOVIEMBRE.getValue()%>"><spring:message code="<%=MesesEnum.NOVIEMBRE.getLabel()%>"/></option>
											<option value="<%=MesesEnum.DICIEMBRE.getValue()%>"><spring:message code="<%=MesesEnum.DICIEMBRE.getLabel()%>"/></option>
										</select>
									</div>
								</div>
								<div id="informes_filter_buttonSet" class="pull-right" style="margin:0 1rem 1rem 0">
									<!-- Botón de filtrado -->
									
									<button id="informes_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all">
										<spring:message code="label.obtener"/>
									</button>
									
									<!-- Enlace de limpiar -->
									<a id="informes_filter_cleanLink_modificado" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div> 
		</div>
	</div>
</div>