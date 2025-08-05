package com.cadastro.cadastro.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.cadastro.cadastro.dto.CadastroDTO;


@Controller
public class CadastroResource{

    private List<CadastroDTO> cadastros = new ArrayList<>();

    // Methodo
    @PostMapping("cadastroPost") //quando chamar o metodo, vai bater nesse cara aqui.
    public String doPost(CadastroDTO dto, Model model){ //classe controladora, classe que vai receber as informações conforme o MVC
        
        cadastros.add(dto);
        model.addAttribute("cadastros", cadastros);
        return "lista";
    }

}