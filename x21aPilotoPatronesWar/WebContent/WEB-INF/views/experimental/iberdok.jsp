<%@include file="/WEB-INF/includeTemplate.inc"%>
<h1>
	<spring:message code="iberdok.title" />
</h1>

<div>
	<div id='divFormEditorIberdok' style="display: none">


		<form:form modelAttribute="randomForm" id="editorDocumentosForm" action="" method="post"
			target="_blank">
			<div class="">
				<div class="header">
					<h3>
						<spring:message code="iberdok.dialog.editor" />
					</h3>
				</div>
				<div id='feedbackError'></div>
				<div class="espaciodo">

					<div class="enLinea widthFijo">
						<div class="enLinea labelsIberdok">
							<label><spring:message code="iberdok.lang" /></label>
						</div>
						<div class="enLinea componentsIberdok">
							<form:select id="lang" path="lang"/>
						</div>
					</div>
					<div class="enLinea">
						<div class="enLinea labelsIberdok">
							<label><spring:message code="iberdok.modo" /></label>
						</div>
						<div class="enLinea componentsIberdok">
							<form:select id="modo" path="modo" class="rup-combo"/>
						</div>
					</div>
					<div class="enLinea" style='display: none;'>
						<div class="enLinea labelsIberdok">
							<label><spring:message code="iberdok.token" /></label>
						</div>
						<div class="enLinea componentsIberdok">
							<form:input id="token" path="token" />
						</div>
					</div>
				</div>
				<div class="espaciodo">
					<div class="enLinea widthFijo " style='display: none;'>
						<div class="enLinea labelsIberdok">
							<label><spring:message code="iberdok.idUsuario" /></label>
						</div>
						<div class="enLinea componentsIberdok">
							<form:input id="idUsuario" path="idUsuario"/>
						</div>
					</div>

				</div>

				<div class="espaciodo">


					<div class="enLinea widthFijoFila">
						<div class="enLinea labelsIberdok">
							<label><spring:message code="iberdok.urlRetorno" /></label>
						</div>
						<div class="enLinea componentsIberdokGrande">
							<form:input id="urlRetorno" path="urlRetorno"/>
						</div>
					</div>

				</div>

				<div class="espaciodoEjemplo ">
					<div class=" label100">
						<spring:message code="iberdok.ejemplo" />
						<label id='urlRetornoEjemplo'></label>
					</div>
				</div>
				<div class="espaciodo">
					<div class="enLinea widthFijoFila">
						<div class="enLinea labelsIberdok">
							<label for="urlFinalizacion"><spring:message
									code="iberdok.urlFinalizacion" />*</label>
						</div>

						<div class="enLinea componentsIberdokGrande">
							<form:input id="urlFinalizacion" path="urlFinalizacion" value=""/>
						</div>
					</div>
				</div>
				<div class="espaciodo" id="datosNecesarios">
					<div class="header">
						<h3>
							<spring:message code="iberdok.datosNecesarios" />
						</h3>
					</div>
				</div>
				<div id="divModo1">
						<div class="espaciodo ">
							<div class="enLinea widthFijo">
								<div class="enLinea labelsIberdok">
									<label for="idModelo"><spring:message
											code="iberdok.idModelo" />*</label>
								</div>
								<div class="enLinea componentsIberdok">
									<form:input type="text" id="idModelo" path="idModelo"/>
								</div>
							</div>
							<div class="enLinea widthFijo ">
								<div class="enLinea labelsIberdok">
									<label class="label" for="nombre"><spring:message
											code="iberdok.nombre" />*</label>
								</div>
								<div class="enLinea componentsIberdok">
									<form:input type="text" id="nombre" path="nombre"/>
								</div>
							</div>
						</div>
					</div>
					
			
				<div id="divModo2">
					<div class="enLinea labelsIberdok">
						<label><spring:message code="iberdok.xhtml64" /></label>
					</div>
					<div class="enLinea componentsIberdok">
						<form:input id="xhtml64" path="xhtml64" readonly="readonly"/>
					</div>
				</div>
				<div id="divModo7">
					<div class="enLinea labelsIberdok">
						<label><spring:message code="iberdok.idDocumento" /></label>
					</div>
					<div class="enLinea componentsIberdok">
						<form:input id="idDocumento" path="idDocumento" readonly="readonly"/>
					</div>

				</div>
				<div id="semillas">
						<div class="espaciodo">
							<div class="enLinea widthFijo">
								<div class="enLinea labelsIberdok">
									<label><spring:message code="iberdok.semilla" /></label>
								</div>
								<div class="enLinea componentsIberdok">
									<form:textarea id="semilla" path="semilla"/>
								</div>
							</div>
							<div class="enLinea widthFijo">
								<div class="enLinea labelsIberdok">
									<label><spring:message code="iberdok.semillatxt" /></label>
								</div>

								<div class="enLinea componentsIberdok">
									<form:textarea path="semillaTXT"/>
								</div>
							</div>
						</div>
						<div class="espaciodo">
							<div class="enLinea labelsIberdok">
								<label><spring:message code="iberdok.semillaxml" /></label>
							</div>
							<div class="enLinea componentsIberdok">
								<form:textarea path="semillaXML"/>
							</div>
						</div>
					</div>
			</div>


			<div class="espaciodoSubmit">
				<input type="submit" id="lanzarEditor"
					value="<spring:message code="iberdok.botonSubmit" />"></input>
			</div>

	</form:form>

</div>
<!-- 	PRUEBA WEBSERVICE REST -->


<!-- 	<div class="espaciodo" id="datosNecesarios"> -->
<!-- 		<div class="header"> -->
<!-- 			<h3>Test URL FINALIZACION</h3> -->
<!-- 		</div> -->
<!-- 		<form -->
<!-- 			action="http://local.ejiedes.net:7001/x21aPilotoPatronesWar/iberdok/urlFinalizacion2" -->
<!-- 			method="post" target="_blank"> -->
<!-- 			<div class="espaciodo"> -->
<!-- 				<label>File</label> -->
<!-- 				<textarea id="fileTest" name="file"></textarea> -->
<!-- 			</div> -->
<!-- 			<div class="espaciodo"> -->
<!-- 				<label>idDocumento</label> <input type="text" name="idDocumento"></input> -->
<!-- 			</div> -->

<!-- 			<div class="espaciodo"> -->
<!-- 				<input type="submit" id="" value="URl finalizacion"></input> -->
<!-- 			</div> -->

<!-- 		</form> -->
<!-- 	</div> -->

<!-- </div> -->

<!-- 		TABLA DOCUMENTOS	 -->
<div>
	<div id="divTablaDocumentos">
		<div class="header">
			<h3>
				<spring:message code="iberdok.tableTitle" />
			</h3>
		</div>
		<div id="iberdokTable_div" class="rup-table-container">
			<div id="iberdokTable_feedback"></div>
			<div id="iberdokTable_toolbar"></div>
			<!-- 			<div id="iberdokTable_filter_div" class="rup-table-filter"> -->
			<!-- 				<form id="iberdokTable_filter_form"> -->
			<!-- 					<div id="iberdokTable_filter_toolbar" class="formulario_legend"></div> -->
			<!-- 					<fieldset id="iberdokTable_filter_fieldset" -->
			<!-- 						class="rup-table-filter-fieldset"> -->

			<!-- 						Campos del formulario de detalle -->
			<!-- 						<div id="iberdokTable_filter_buttonSet" class="right_buttons"> -->
			<!-- 							<input id="iberdokTable_filter_filterButton" type="button" -->
			<!-- 								class="uibutton -->
			<!-- ui-widget ui-state-default ui-corner-all" -->
			<%-- 								value='<spring:message code="filter" />' /> <a --%>
			<!-- 								id="iberdokTable_filter_cleanLink" href="javascript:void(0)" -->
			<%-- 								class="rup-enlaceCancelar"><spring:message code="clear" /></a> --%>
			<!-- 						</div> -->
			<!-- 					</fieldset> -->
			<!-- 				</form> -->
			<!-- 			</div> -->


			<div id="iberdokTable_grid_div">
				<!-- Tabla -->
				<table id="iberdokTable"></table>
				<!-- Barra de paginación -->
				<div id="iberdokTable_pager"></div>
			</div>
		</div>
	</div>
</div>
</div>