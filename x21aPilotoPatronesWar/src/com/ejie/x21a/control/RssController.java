/*
* Copyright 2012 E.J.I.E., S.A.
*
* Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
* Solo podrá usarse esta obra si se respeta la Licencia.
* Puede obtenerse una copia de la Licencia en
*
* http://ec.europa.eu/idabc/eupl.html
*
* Salvo cuando lo exija la legislación aplicable o se acuerde por escrito,
* el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
* SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
* Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
* que establece la Licencia.
*/
package com.ejie.x21a.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ejie.x38.rss.RssContent;
import com.ejie.x21a.service.RssService;

/**
 * ExperimentalController
 * 
 * @author UDA
 */
@Controller
public class RssController {

	@Autowired
	private RssService rssService;
	
	@RequestMapping(value="/rssfeed", method = RequestMethod.GET)
	public ModelAndView getFeedInRss(HttpServletRequest request) {
		
		
//		List<SampleContent> items = new ArrayList<SampleContent>();
 
//		SampleContent content  = new SampleContent();
//		content.setTitle("Spring MVC Tutorial 1");
//		content.setUrl("http://www.mkyong.com/spring-mvc/tutorial-1");
//		content.setSummary("Tutorial 1 summary ...");
//		content.setCreatedDate(new Date());
//		items.add(content);
// 
//		SampleContent content2  = new SampleContent();
//		content2.setTitle("Spring MVC Tutorial 2");
//		content2.setUrl("http://www.mkyong.com/spring-mvc/tutorial-2");
//		content2.setSummary("Tutorial 3 summary ...");
//		content2.setCreatedDate(new Date());
//		items.add(content2);
//		
//		SampleContent content3  = new SampleContent();
//		content3.setTitle("Spring MVC Tutorial 4");
//		content3.setUrl("http://www.mkyong.com/spring-mvc/tutorial-2");
//		content3.setSummary("Tutorial 5 summary ...");
//		content3.setCreatedDate(new Date());
//		items.add(content3);
// 
 
		List <RssContent> items = this.rssService.getAll();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("rssViewer");
		mav.addObject("feedContent", items);
		
		return mav;
 
	}
}
