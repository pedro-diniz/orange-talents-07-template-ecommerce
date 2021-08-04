package br.com.zup.desafioml.controller;

import br.com.zup.desafioml.controller.dto.request.CategoriaRequest;
import br.com.zup.desafioml.model.Categoria;
import br.com.zup.desafioml.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaRequest categoriaRequest) {

        Categoria categoria = categoriaRequest.toModel();
        categoriaRepository.save(categoria);

        return ResponseEntity.ok().build();
    }

}
