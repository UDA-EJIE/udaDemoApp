<h1>Grid</h1>		
<div id="grid">
	<table id="grid_usuarios" cellpadding="0" cellspacing="0">
		<tbody><tr><td>&nbsp;</td></tr></tbody>
	</table>
	<div id="pager"></div>
</div>
<div style="margin-top:20px">
	<div>
		<table>
			<tr>
				<td>ID:</td>
				<td><input type="text" name="id" maxlength="25" id="id" tabindex="1" /></td>
			</tr>
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre" maxlength="25" id="nombre" tabindex="1" /></td>
			</tr>
			<tr>
				<td>Apellido 1:</td>
				<td><input type="text" name="apellido1" maxlength="25" id="apellido1" tabindex="1" /></td>
			</tr>
			<tr>
				<td>Apellido 2:</td>
				<td><input type="text" name="apellido2" maxlength="25" id="apellido2" tabindex="1" /></td>
			</tr>
			<tr>
				<td>Ejie:</td>
				<td>
					<select name="ejie" id="ejie" tabindex="1">
						<option value="0">No</option>
						<option value="1">Sí</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Fecha Alta:</td>
				<td><input type="text" name="fechaAlta" id="fechaAlta" tabindex="1" /></td>
			</tr>
			<tr>
				<td>Fecha Baja:</td>
				<td><input type="text" name="fechaBaja" id="fechaBaja" tabindex="1" /></td>
			</tr>
		</table>
	</div>
	<div style="margin-top:12px">
		<button id="btnReload">reload</button>
		<button id="btnSel">getRow</button>
		<button id="btnEditar">setRow</button>
		<button id="btnInsertar">insertRow</button>
		<button id="btnBorrar">deleteSelectedRow</button>
	</div>
</div>
