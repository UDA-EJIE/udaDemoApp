package com.ejie.x21a.control;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ejie.x38.util.StaticsContainer;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/welcome")
public class WelcomeController {
	private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);
	
	@Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void logBeans() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            if (beanName.contains("webMvc")) { // Filtra beans relacionados
                System.out.println("Bean encontrado: " + beanName);
            }
        }
    }
    
    @GetMapping
    public String showWelcomePage(Model model) {
    	String staticsUrl = StaticsContainer.getStaticsUrl();
    	WelcomeController.logger.info("[ENTROOO] : showWelcomePage"+staticsUrl);
    	model.addAttribute("prueba", "/prueba");
        return "welcomePage"; // Esto busca el archivo welcome.html en templates/
    }
    
    @GetMapping("/x21aAppWar/welcomePage")
    public String welcomePage() {
        return "welcomePage"; // Busca welcome.html en /WEB-INF/templates/
    }
}
