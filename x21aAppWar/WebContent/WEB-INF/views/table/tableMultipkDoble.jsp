<%@include file="/WEB-INF/includeTemplate.inc"%>

<div class="row">
    <div class="col-xl-6">
		<div class="row">
			<div class="col-12">
				<h2 class="title mb-3">Tabla con múltiples PKs</h2>
			</div>
		</div>
    	<div class="row">
			<div class="col-xl-12">
				<jsp:include page="includes/tableMultiPkFilterForm.jsp"></jsp:include>
			</div>
		</div>
        <div class="row">
        	<div class="col-xl-12">
				<table id="MultiPk" class="tableFit table table-striped table-bordered table-material align-middle" 
					data-url-base="../multipk"
					data-filter-form="#MultiPk_filter_form">
				    <thead>
						<tr>
							<th data-col-prop="nombre" data-col-sidx="NOMBRE">Nombre</th>
							<th data-col-prop="apellido1" data-col-sidx="APELLIDO1">Primer apellido</th>
							<th data-col-prop="apellido2" data-col-sidx="APELLIDO2">Segundo apellido</th>
						</tr>
					</thead>
				</table>
            </div>
        </div>
		<jsp:include page="includes/tableMultiPkEdit.jsp"></jsp:include>
    </div>

    <div class="mt-lg-5 mt-xl-0 col-xl-6 tableDoubleBorder">
		<div class="row">
			<div class="col-12">
				<h2 class="title mb-3">Tabla Simple</h2>
			</div>
		</div>
		<div class="row">
			<div class="col-xl-12">
				<jsp:include page="includes/tableFilterForm.jsp"></jsp:include>
		  	</div>
		</div>
        <div class="row">
			<div class="col-xl-12">
				<table id="example" class="tableFit table table-striped table-bordered table-material align-middle"
					data-url-base=".."
					data-filter-form="#example_filter_form">
				        <thead>
				            <tr>
				                <th data-col-prop="nombre" data-col-edit="true">Nombre</th>
				                <th data-col-prop="apellido1">Primer apellido</th>
				                <th data-col-prop="ejie" data-col-type="Checkbox">Ejie</th>
				                <th data-col-prop="fechaAlta" data-col-sidx="fecha_alta" data-col-type="Datepicker">Fecha alta</th>
				                <th data-col-prop="fechaBaja" data-col-sidx="fecha_baja" data-col-type="Datepicker">Fecha baja </th>
				                <th data-col-prop="rol" data-col-type="combo">Rol</th>
				            </tr>
				        </thead>
				</table>
        	</div>
        </div>
		<jsp:include page="includes/tableEdit.jsp"></jsp:include>
    </div>
</div>
