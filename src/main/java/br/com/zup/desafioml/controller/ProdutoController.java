package br.com.zup.desafioml.controller;

import br.com.zup.desafioml.controller.dto.request.ProdutoRequest;
import br.com.zup.desafioml.model.*;
import br.com.zup.desafioml.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(Authentication authentication, @RequestBody @Valid ProdutoRequest produtoRequest) {

        Produto produto = produtoRequest.toModel(authentication);
        produtoRepository.save(produto);

        return ResponseEntity.ok().build();

    }

}
