package com.ejie.x21a.control;

import com.ejie.x21a.model.Usuario;
import com.ejie.x21a.service.TableUsuarioService;
import com.ejie.x38.control.bind.annotation.RequestJsonBody;
import com.ejie.x38.dto.TableRequestDto;
import com.ejie.x38.dto.TableResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/lista")
public class ListaController {
    private static final Logger logger = LoggerFactory.getLogger(ListaController.class);

    @Autowired
    private TableUsuarioService tableUsuarioService;

    @GetMapping (value = "/simple")
    public String getListaSimpleView(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "listaSimple";
    }

    @PostMapping(value = "/filter")
    public @ResponseBody
    TableResponseDto<Usuario> filter(
            @RequestJsonBody(param="filter") Usuario filterUsuario,
            @RequestJsonBody TableRequestDto tableRequestDto) {
        ListaController.logger.info("[POST - jqGrid] : Obtener Usuarios");
        return tableUsuarioService.filter(filterUsuario, tableRequestDto, false);
    }
}
