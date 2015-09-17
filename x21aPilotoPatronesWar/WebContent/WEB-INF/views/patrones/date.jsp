<h1>Date</h1>

<div>
	<!-- Fecha -->
	<label for="fecha">Fecha </label><label for="fecha" id="fecha-mask"></label>:
	<input id="fecha" type="text" />
	&nbsp;&nbsp;<input id="fecha_button" type="button" value="getDate()" />
	<br/><br/>
	
	<!-- Fecha múltiple-->
	<label for="fecha_multi">Fecha multiple</label><label for="fecha_multi" id="fecha_multi-mask"></label>:
	<input id="fecha_multi" type="text" />
	&nbsp;&nbsp;<input id="fecha_multi_button" type="button" value="getDates()" />
	<br/><br/>
	
	<!-- Intervalo -->
	<label for="desde">Intervalo desde </label><label for="desde" id="intervalo-mask"></label>:
	<input type="text" id="desde" />
	<label for="hasta"> hasta: </label>
	<input type="text" id="hasta"/>
	<br/><br/>
		
	<!-- Fecha desplegada --> 
	<div id="fecha_inline" style="float:left;"></div>
	<div style="float:right; margin-right: 33em;"><input id="fecha_inline_button" type="button" value="getDate()" /></div>
</div>	
