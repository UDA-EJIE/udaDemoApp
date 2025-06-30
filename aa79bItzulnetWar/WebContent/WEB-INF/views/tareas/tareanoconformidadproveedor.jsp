<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container-fluid aa79b-fade in  aa79b-content" id="divEjecutarTareaCapa">
	<div id="tareaNoConformidadProveedor_div">
		<div id="ejecutarTareaDialog_toolbar"></div>
		<div id="ejecutarTareaDialog_feedback"></div>
		<div class="row margen1TB">
			<div class="col-xs-2">
				<label class="control-label"><spring:message code="comun.tipoDeTarea" />:</label>
			</div>
			<div class="col-xs-9">
				<b id="descTarea"><c:out value="${descripcionTarea}"></c:out></b>
			</div>
		</div>
	
			<!-- 			Si la tarea de no conformidad parte de una tarea de traduccion se mostrara este div -->
		<div id="noConformidadTraducir">
			<div id="pestanasModalNoConformidadProveedor">
				<div id="tabsModalNoConformidadProveedor" class="tabsEjecutarTarea"></div>
			</div>
			<div id="pestDocsTraducir">
				<div id="docsTraducir_div">
					<div id="docsTraducir_feedback"></div>						<!-- Feedback de la tabla --> 
					<div id="contenFormulariosTraducir" class="filterForm oculto">	
						<form id="docsTraducir_filter_form" class="form-horizontal">						<!-- Formulario de filtrado -->
							<div id="docsTraducir_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
							<fieldset id="docsTraducir_filter_fieldset" class="rup-table-filter-fieldset">
								<input type="text" name="idTarea" id="idTareaTraducir_filter">
							</fieldset>
						</form>
					</div>
					<input type="hidden" id="idiomaDestTraducir" name="idiomaDest" />
					<div class="horizontal_scrollable_table">
						<!-- Tabla -->
						<table id="docsTraducir"></table>
						<!-- Barra de paginación -->
						<div id="docsTraducir_pager"></div>
					</div>
					<div class="row form-group">
						<div class="col-xs-5">
							<div class="col-xs-1">
								<i class="fa fa-unlock" aria-hidden="true"></i>
							</div>
							<label class="control-label col-xs-10">
								<spring:message code="label.documento.docNoEncriptado"/>
							</label>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-xs-5">	
							<div class="col-xs-1">				
								<i class='fa fa-lock'></i>
							</div>	
							<label class="control-label col-xs-10">
								<spring:message code="label.documento.docEncriptado"/>
							</label>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-xs-5">
							<div class="col-xs-1">
								<i class="fa fa-file" aria-hidden="true"></i>
							</div>
							<label class="control-label col-xs-10">
								<spring:message code="label.documento.docOriginalTrad"/>
							</label>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-xs-5">	
							<div class="col-xs-1">				
								<i class='fa fa-file-text'></i>
							</div>	
							<label class="control-label col-xs-10">
								<spring:message code="label.documento.docTrabajo"/>
							</label>
						</div>
					</div>
				</div>
			</div>
			
			<div id="pestDocsXliff">
				<div id="docsXliff_div">
					<div id="docsXliff_feedback"></div>						<!-- Feedback de la tabla --> 
					<div id="docsXliff_toolbar"></div>							<!-- Botonera de la tabla -->
					<div id="contenFormulariosXliff" class="filterForm oculto">	
						<form id="docsXliff_filter_form" class="form-horizontal">						<!-- Formulario de filtrado -->
							<div id="docsXliff_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
							<fieldset id="docsXliff_filter_fieldset" class="rup-table-filter-fieldset">
								<input type="text" name="idTarea" id="idTarea_filter_XliffTraducir">
							</fieldset>
						</form>
					</div>
					<div class="horizontal_scrollable_table">
						<!-- Tabla -->
						<table id="docsXliff"></table>
						<!-- Barra de paginación -->
						<div id="docsXliff_pager"></div>
					</div>
				</div>	
			</div>
		</div>
		<div id="noConformidadRevisar">
			<div id="pestanasModalNoConformidadProveedorRevisar">
				<div id="tabsModalNoConformidadProveedorRevisar" class="tabsEjecutarTarea"></div>
			</div>
			<div id="pestDocsRevisar">
				<div id="docsRevisar_div">
					<div id="docsRevisar_feedback"></div>						<!-- Feedback de la tabla --> 
					<div id="contenFormulariosRevisar" class="filterForm oculto">	
						<form id="docsRevisar_filter_form" class="form-horizontal">						<!-- Formulario de filtrado -->
							<div id="docsRevisar_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
							<fieldset id="docsRevisar_filter_fieldset" class="rup-table-filter-fieldset">
								<input type="text" name="idTarea" id="idTareaRevisar_filter">
							</fieldset>
						</form>
					</div>
					<input type="hidden" id="idiomaDestRevisar" name="idiomaDest" />
					<div class="horizontal_scrollable_table">
						<!-- Tabla -->
						<table id="docsRevisar"></table>
						<!-- Barra de paginación -->
						<div id="docsRevisar_pager"></div>
					</div>
					<div class="row form-group">
						<div class="col-xs-5">
							<div class="col-xs-1">
								<i class="fa fa-unlock" aria-hidden="true"></i>
							</div>
							<label class="control-label col-xs-10">
								<spring:message code="label.documento.docNoEncriptado"/>
							</label>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-xs-5">	
							<div class="col-xs-1">				
								<i class='fa fa-lock'></i>
							</div>	
							<label class="control-label col-xs-10">
								<spring:message code="label.documento.docEncriptado"/>
							</label>
						</div>
					</div>
					<div class="row form-group">
						<div class="col-xs-5">
							<div class="col-xs-1">
								<i class="fa fa-file" aria-hidden="true"></i>
							</div>
							<label class="control-label col-xs-10">
								<spring:message code="label.documento.docOriginalRev"/>
							</label>
						</div>
					</div>
				</div>
			</div>
			<div id="pestDocsXliffRevisar">
				<div id="docsXliffRevisar_div">
					<div id="docsXliffRevisar_feedback"></div>						<!-- Feedback de la tabla --> 
					<div id="docsXliffRevisar_toolbar"></div>							<!-- Botonera de la tabla -->
					<div id="contenFormulariosXliffRevisar" class="filterForm oculto">	
						<form id="docsXliffRevisar_filter_form" class="form-horizontal">						<!-- Formulario de filtrado -->
							<div id="docsXliffRevisar_filter_toolbar" class="formulario_legend"></div>	<!-- Barra de herramientas del formulario de filtrado -->
							<fieldset id="docsXliffRevisar_filter_fieldset" class="rup-table-filter-fieldset">
								<input type="text" name="idTarea" id="idTarea_filter_XliffRevisar">
							</fieldset>
						</form>
					</div>
					<div class="horizontal_scrollable_table">
						<!-- Tabla -->
						<table id="docsXliffRevisar"></table>
						<!-- Barra de paginación -->
						<div id="docsXliffRevisar_pager"></div>
					</div>
				</div>	
			</div>
		</div>
	</div>
	<form id="ejecutarTareaDialog_form">
		<input type="hidden" name="numExp" id="numExp_form" >
		<input type="hidden" name="anyo" id="anyo_form" >
		<input type="hidden" name="idTarea" id="idTarea_form" >
		<input type="hidden" name="tipoTarea.id015" id="idTipoTarea_form" >
		<input type="hidden" name="ejecucionTareas.horasTarea" id="horasTarea_form" >
	</form>
</div>




