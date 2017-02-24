<%@include file="/WEB-INF/includeTemplate.inc"%>

<form id="table_filter_form">
  <div id="table_filter_toolbar" class="formulario_legend"></div>
  <fieldset id="table_filter_fieldset" class="rup-table-filter-fieldset">
    <div class="row">
      <div class="col-xs-6 col-md-3">
        <div class="form-group form-group-sm">
          <label for="id_filter_table" class="formulario_linea_label"><spring:message code="id" />:</label>
          <input type="text" name="id" class="formulario_linea_input form-control" id="id_filter_table" />
        </div>
      </div>
      <div class="col-xs-6 col-md-3">
        <div class="form-group form-group-sm">
          <label for="nombre_filter_table" class="formulario_linea_label"><spring:message code="nombre" />:</label>
          <input type="text" name="nombre" class="formulario_linea_input form-control" id="nombre_filter_table" />
        </div>
      </div>
      <div class="col-xs-6 col-md-3">
        <div class="form-group form-group-sm">
          <label for="apellido1_filter_table" class="formulario_linea_label"><spring:message code="apellido1" />:</label>
          <input type="text" name="apellido1" class="formulario_linea_input form-control" id="apellido1_filter_table" />
        </div>
      </div>
      <div class="col-xs-6 col-md-3">
        <div class="form-group form-group-sm">
          <label for="apellido2_filter_table" class="formulario_linea_label"><spring:message code="apellido2" />:</label>
          <input type="text" name="apellido2" class="formulario_linea_input form-control" id="apellido2_filter_table" />
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-xs-6 col-md-3">
        <div class="form-group form-group-sm">
          <label for="ejie_filter_table" class="formulario_linea_label"><spring:message code="ejie" />:</label>
          <div style="float: left;"><select id="ejie_filter_table" name="ejie" class="rup-combo form-control" ></select></div>
        </div>
      </div>
      <div class="col-xs-6 col-md-3">
        <div class="form-group form-group-sm">
          <label for="fechaAlta_filter_table" class="formulario_linea_label"><spring:message code="fechaAlta" />:</label>
          <input type="text" name="fechaAlta" class="formulario_linea_input form-control" id="fechaAlta_filter_table" />
        </div>
      </div>
      <div class="col-xs-6 col-md-3">
        <div class="form-group form-group-sm">
          <label for="fechaBaja_filter_table" class="formulario_linea_label"><spring:message code="fechaBaja" />:</label>
          <input type="text" name="fechaBaja" class="formulario_linea_input form-control" id="fechaBaja_filter_table" />
        </div>
      </div>
      <div class="col-xs-6 col-md-3">
        <div class="form-group form-group-sm">
          <label for="rol_filter_table" class="formulario_linea_label"><spring:message code="rol" />:</label>
          <input type="text" name="rol" class="formulario_linea_input form-control" id="rol_filter_table" />
        </div>
      </div>
    </div>
    <div id="table_filter_buttonSet" class="right_buttons">

        <a id="table_filter_cleanLink" href="javascript:void(0)" class="rup-enlaceCancelar"><spring:message code="clear" /></a>
        <button id="table_filter_filterButton" type="button" class="ui-button ui-widget ui-state-default ui-corner-all" ><spring:message code="filter" /></button>
    </div>
  </fieldset>
</form>
