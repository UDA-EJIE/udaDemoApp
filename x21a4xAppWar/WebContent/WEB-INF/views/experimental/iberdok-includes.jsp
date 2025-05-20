<%@include file="/WEB-INF/includeTemplate.inc"%>
<script type="text/javascript">
	var IBERDOK = (function() {
		var constants = {
			'lang' : '${lang}',
			'token' : '${token}',
			'idUsuario' : '${idUsuario}',
			'idModelo' : '${idModelo}',
			'urlRetorno' : '${urlRetorno}',
			'urlFinalizacion' : '${urlFinalizacion}',
			'urlNuevoDocumento' : '${urlNuevoDocumento}',
			'urlEditarDocumento' : '${urlEditarDocumento}',
			'urlEditorDocumentos':'${urlEditorDocumentos}'
		};

		var result = {};
		for ( var n in constants)
			if (constants.hasOwnProperty(n))
				Object.defineProperty(result, n, {
					value : constants[n]
				});

		return result;
	}());
</script>

<script src="${staticsUrl}/x21a/scripts/x21aApp/iberdok.js"
	type="text/javascript"></script>
