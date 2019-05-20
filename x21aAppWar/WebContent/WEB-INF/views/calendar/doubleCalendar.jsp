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
<%@include file="/WEB-INF/includeTemplate.inc"%>
<h2>Dos calendarios</h2> <!-- Titulo pagina -->

<div class="cal-1">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header w-100 mb-3">
                <div class="pull-right form-inline">
                    <div class="btn-group mb-3">
                        <span class="btn btn-primary" data-calendar-nav="prev"> <i
                            class="mdi mdi-chevron-left-circle" aria-hidden="true"></i>&nbsp;Prev.
                        </span> <span class="btn light" data-calendar-nav="today">Hoy</span> <span
                            class="btn btn-primary" data-calendar-nav="next">
                            Sig.&nbsp;<i class="mdi mdi-chevron-right-circle" aria-hidden="true"></i>
                        </span>
                    </div>
                    <div class="pl-3 d-xs-none"></div>
                    <div class="btn-group mb-3">
                        <span class="btn btn-light" data-calendar-view="year">Año</span> <span
                            class="btn btn-light active" data-calendar-view="month">Mes</span>
                        <span class="btn btn-light" data-calendar-view="week">Semana</span>
                        <span class="btn btn-light" data-calendar-view="day">Día</span>
                    </div>
                </div>
                <h3></h3>
            </div>
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-xs-12">
            <div id="calendar1" class="calendar"></div>
        </div>
    </div>
</div>
<div class="cal-2">
    <div class="row">
        <div class="col-xs-12">
            <div class="page-header w-100 mb-3">
                <div class="pull-right form-inline">
                    <div class="btn-group mb-3">
                        <span class="btn btn-primary" data-calendar-nav="prev"> <i
                            class="mdi mdi-chevron-left-circle" aria-hidden="true"></i>&nbsp;Prev.
                        </span> <span class="btn light" data-calendar-nav="today">Hoy</span> <span
                            class="btn btn-primary" data-calendar-nav="next">
                            Sig.&nbsp;<i class="mdi mdi-chevron-right-circle" aria-hidden="true"></i>
                        </span>
                    </div>
                    <div class="pl-3 d-xs-none"></div>
                    <div class="btn-group mb-3">
                        <span class="btn btn-light" data-calendar-view="year">Año</span> <span
                            class="btn btn-light active" data-calendar-view="month">Mes</span>
                        <span class="btn btn-light" data-calendar-view="week">Semana</span>
                        <span class="btn btn-light" data-calendar-view="day">Día</span>
                    </div>
                </div>
                <h3></h3>
            </div>
        </div>
    </div>
    <div class="row mb-3">
        <div class="col-xs-12">
            <div id="calendar2" class="calendar"></div>
        </div>
    </div>
</div>
<div id="divLeyenda" class="row">
	<div class="col-xs-5 noPadding">
		<div id="divLegend" class="">
			<span class="separator" style="color: #666;"> Leyenda: </span>
			<div class="row row-noPadding">
				<div class="col-md-1">
					<div class="legend bgNegro"></div>
				</div>
				<div class="col-md-11">Vigilancia de vertido</div>
			</div>
			<div class="row row-noPadding">
				<div class="col-md-1">
					<div class="legend bgNegroyRojo"></div>
				</div>
				<div class="col-md-11">Muestreo</div>
			</div>
			<div class=" row row-noPadding">
				<div class="col-md-1">
					<div class="legend bgAmarillo"></div>
				</div>
				<div class="col-md-11">Inspección de vertido</div>
			</div>
			<div class="row row-noPadding">
				<div class="col-md-1">
					<div class="legend bgNaranja"></div>
				</div>
				<div class="col-md-11">Inspección de obra</div>
			</div>
			<div class="row row-noPadding">
				<div class="col-md-1">
					<div class="legend bgAzul"></div>
				</div>
				<div class="col-md-11">Inspección de concesión</div>
			</div>
			<div class="row row-noPadding">
				<div class="col-md-1">
					<div class="legend bgCheck">
						<i class='mdi mdi-check pr-1' style='color: black'
							aria-hidden='true'></i>
					</div>
				</div>
				<div class="col-md-11">Completada</div>
			</div>
			<div class="row row-noPadding">
				<div class="col-md-1">
					<div class="legend bgExclamation">
						<i class='mdi mdi-exclamation pr-1'
							style='color: black; padding: 6px 7px;' aria-hidden='true'></i>
					</div>
				</div>
				<div class="col-md-11">La fecha fin de trámite está
					próxima/vencida</div>
			</div>
		</div>
	</div>
</div>