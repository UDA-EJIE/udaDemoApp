<h1>Date</h1>

<div>
	<!-- Fecha -->
	Fecha <label for="fecha" id="fecha-mask"></label>:
	<input id="fecha" type="text" />
	&nbsp;&nbsp;<input id="fecha_button" type="button" value="getDate()" />
	<br/><br/>
	
	<!-- Fecha múltiple-->
	Fecha multiple<label for="fecha_multi" id="fecha_multi-mask"/></label>:
	<input id="fecha_multi" type="text" />
	&nbsp;&nbsp;<input id="fecha_multi_button" type="button" value="getDates()" />
	<br/><br/>
	
	<!-- Intervalo -->
	Intervalo desde <label for="desde" id="intervalo-mask"/></label>:
	<input type="text" id="desde" />
	hasta: 
	<input type="text" id="hasta"/>
	<br/><br/>
		
	<!-- Fecha desplegada --> 
	<div id="fecha_inline" style="float:left;"></div>
	<div style="float:right; margin-right: 33em;"><input id="fecha_inline_button" type="button" value="getDate()" /></div>
</div>	
