package com.ejie.aa79b.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping(value = "/comun")
public class ComunController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComunController.class);

	/**
	 * The method informacionLegal.
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/infoLegal", method = RequestMethod.GET)
	public ModelAndView informacionLegal() {
		ComunController.LOGGER.debug("[GET] /comun/infoLegal");
		return new ModelAndView("infoLegal");
	}

}
