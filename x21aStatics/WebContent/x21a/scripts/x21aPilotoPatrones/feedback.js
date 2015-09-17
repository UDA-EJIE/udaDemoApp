jQuery(document).ready(function(){
	
	$("#boton_create").click(feedback_create);
	$("#boton_destroy").click(feedback_destroy);
	$("#boton_set").click(feedback_set);
	$("#boton_setOptions").click(feedback_setOptions);
	$("#boton_setType").click(feedback_setType);
	$("#boton_setImgClass").click(feedback_setImgClass);
	$("#boton_hide").click(feedback_hide);
	$("#boton_close").click(feedback_close);
	$("#boton_show").click(feedback_show);

	function getOptions(){
		//Recuperar variables
		var my_type = $("#feedback_type").val()==""?null:$("#feedback_type").val(),
			my_imgClass = $("#feedback_imgClass").val()==""?null:$("#feedback_imgClass").val(),
			my_delay =$("#feedback_delay").val()==""?null:$("#feedback_delay").val(),
			my_fadeSpeed = $("#feedback_fadeSpeed").val()==""?null:$("#feedback_fadeSpeed").val(),
			my_gotoTop = eval($("#feedback_gotoTop").val()),
			my_block = eval($("#feedback_block").val()),
			my_closeLink = eval($("#feedback_closeLink").val()),
			//Parametros feedback
			properties = { 
				type: my_type,
				imgClass: my_imgClass,
				delay: my_delay,
				fadeSpeed: my_fadeSpeed,
				gotoTop: my_gotoTop,
				block: my_block,
				closeLink: my_closeLink
			}; 
		return properties;
	}
	
	function feedback_create(){
		$("#feedback").rup_feedback(getOptions());
		$("#boton_create").attr("disabled",true);
		$("#boton_destroy").attr("disabled",false);
	}
	function feedback_destroy(){
		$("#feedback").rup_feedback("destroy"); 
		$("#boton_destroy").attr("disabled",true);
		$("#boton_create").attr("disabled",false);
	}
	function feedback_set(){
		$("#feedback").rup_feedback("set","Este es un ejemplo de <b>Feedback</b>");
	}
	function feedback_setOptions(){
		$("#feedback").rup_feedback("option",getOptions());
	}
	function feedback_setType(){
		$("#feedback").rup_feedback("set",
				"Este es un ejemplo de cambio de tipo de <b>Feedback</b>",
				$("#feedback_type").val()==""?null:$("#feedback_type").val()
		);
	}
	function feedback_setImgClass(){
		$("#feedback").rup_feedback("set",
				"Este es un ejemplo de cambio de tipo por imgClass del <b>Feedback</b>", 
				null, 
				$("#feedback_imgClass").val()
		);
	}
	function feedback_hide(){
		$("#feedback").rup_feedback("hide", 
				$("#feedback_delay").val()==""?null:$("#feedback_delay").val(), 
				$("#feedback_fadeSpeed").val()==""?null:$("#feedback_fadeSpeed").val()
		);
	}
	function feedback_close(){
		$("#feedback").rup_feedback("close");
	}
	function feedback_show(){
		$("#feedback").rup_feedback("show");
	}
});