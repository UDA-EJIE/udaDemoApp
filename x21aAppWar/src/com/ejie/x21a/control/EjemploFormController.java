package com.ejie.x21a.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping (value = "/ejemplo")
public class EjemploFormController {

	@RequestMapping(value="/form", method = RequestMethod.POST)
	public @ResponseBody Map<String,String> addFormSimple(@RequestParam HashMap<String,String> data){
		
		return null;
	}
}