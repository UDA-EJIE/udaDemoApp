<div id="detalle">
	<h2>COMPONENTE RUP LIST - EJEMPLO</h2>
	
	<div id="consultaArticulos_div" class="container aa79b-bg">
		<form id="articulos_filter_form">
		</form>
	
		<div id="rup-list-content" class="aa79b-animate" style="display: none;">
			<div id="rup-list-feedback"></div>
	
			<fieldset id="rup-list-header" class="aa79b-body-bg">
				<div class="form-inline row">
					<div
						class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-4 col-xl-4 ">
						<label for="rup-list-header-rowNum">
							<spring:message code="rupList.header.pag1" />
						</label>
						<select id="rup-list-header-rowNum" class="form-control"></select>
						<small class="text-muted">
							<spring:message code="rupList.header.pag2" />
						</small>
					</div>
					<div
						class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-4">
						<label for="rup-list-header-sidx">
							<spring:message code="rupList.header.orden" />
						</label>
						<select id="rup-list-header-sidx" class="form-control"></select>
						<button id="rup-list-header-sord" type="button" class="btn">
							<i class="fa fa-sort-amount-asc" aria-hidden="true"></i>
						</button>
					</div>
					<div
						class="form-group col-xs-12 col-sm-2 col-md-5 col-lg-4"
						style="text-align: right;">
						<nav id="rup-list-header-nav" aria-label="...">
							<ul class="pagination aa79b-noMargin">
								<li id="rup-list-header-page-prev"
									class="aa79b-rup-list-pagePrev page-item disabled">
									<a class="page-link hidden-lg-down" style="padding-top: 0.5rem;"
										href="javascript:void(0)" tabindex="-1">
										<spring:message code="rupList.header.anterior" />
									</a>
									<a class="page-link hidden-xl-up visible-lg-down"
										href="javascript:void(0)" tabindex="-1">
										<i class="fa fa-angle-double-left" style="font-size: 19px;"
											aria-hidden="true"></i>
									</a>
								</li>
								<li class="page-item page-separator disabled">
									<a class="page-link" href="javascript:void(0)" tabindex="-1">...</a>
								</li>
								<li class="page-item page-separator disabled">
									<a class="page-link" href="javascript:void(0)" tabindex="-1">...</a>
								</li>
								<li id="rup-list-header-page-next"
									class="aa79b-rup-list-pageNext page-item">
									<a class="page-link hidden-lg-down" style="padding-top: 0.5rem;"
										href="javascript:void(0)">
										<spring:message code="rupList.header.siguiente" />
									</a>
									<a class="page-link hidden-xl-up visible-lg-down"
										href="javascript:void(0)" tabindex="-1">
										<i class="fa fa-angle-double-right" style="font-size: 19px;"
											aria-hidden="true"></i>
									</a>
								</li>
							</ul>
						</nav>
					</div>
				</div>
			</fieldset>
	
			<div id="rup-list" class="list-group aa79b-animate">
				<div id="rup-list-itemTemplate" class="list-group-item list-group-item-action flex-column align-items-start">
					<div class="row aa79b-pruebas-header hidden-md-down">
						<div class="col-xs-4 col-lg-3">
							<i class="fa fa-bookmark" aria-hidden="true"></i>
							<span id="numExp_label"
								class="hidden-md-down"></span>
							<a class="btn abrirExpediente" href="javascript:void(0)"><strong id="numExp_value"></strong></a>
							<span class="numExp" style="display: none;" id="numExp_value"></span>
							<span class="anyo" style="display: none;" id="anyo_value"></span>
						</div>
						<div class="col-xs-4 col-lg-3 col-xl-3" style="text-align: right;">
							<span class="aa79b-pruebas-menu">
								<i class="fa fa-caret-down aa79b-animate" aria-hidden="true"></i>
							</span>
						</div>
					</div>
					<div class="collapsed aa79b-pruebas-detail">
						<div class="row">
							Hola
						</div>
						<div class="row">
							¿Qué tal?
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
	
	
	<button id="volverButton" type="button">
		Volver
	</button>
</div>
