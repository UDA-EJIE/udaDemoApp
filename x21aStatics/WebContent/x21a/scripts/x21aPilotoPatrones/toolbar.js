jQuery(document).ready(function () {
	$("#toolbar").rup_toolbar({
		width: 360,
		buttons:[
			{i18nCaption:"buscar", css:"buscar", click: handlerBoton }
		],
		mbuttons:[
			{id: "mbuton1", i18nCaption:"otros", buttons:[
				{i18nCaption:"nuevo", css:"nuevo", click: handlerMButtons},
				{i18nCaption:"editar", css:"editar", click: handlerMButtons},
				{i18nCaption:"cancelar", css:"cancelar", click: handlerMButtons},
				{i18nCaption:"borrar", css:"borrar", click: handlerMButtons},
				{i18nCaption:"filtrar", css:"filtrar", click: handlerMButtons},
				{i18nCaption:"imprimir", css:"imprimir", click: handlerMButtons}
			]},
			{id : "mbuton2", i18nCaption:"ficheros", buttons:[
				{i18nCaption:"DLL", css:"dll", click: handlerMButtons },
				{i18nCaption:"DOC", css:"doc", click: handlerMButtons},
				{i18nCaption:"EXE", css:"exe", click: handlerMButtons},
				{i18nCaption:"GIF", css:"gif", click: handlerMButtons},
				{i18nCaption:"JPG", css:"jpg", click: handlerMButtons},
				{i18nCaption:"JS",  css:"js",  click: handlerMButtons},
				{i18nCaption:"PDF", css:"pdf", click: handlerMButtons},
				{i18nCaption:"PPT", css:"ppt", click: handlerMButtons},
				{i18nCaption:"TXT", css:"txt", click: handlerMButtons},
				{i18nCaption:"XLS", css:"xls", click: handlerMButtons},
				{i18nCaption:"ZIP", css:"zip", click: handlerMButtons}
			]}
		]
	});
	
	function handlerBoton(){
		alert ("Se ha pulsado el boton");
	}
	function handlerMButtons(event){
		alert ("MButton:" + event.data.i18nCaption);
	}
});