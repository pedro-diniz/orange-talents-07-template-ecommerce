package br.com.zup.desafioml.controller;

import br.com.zup.desafioml.controller.dto.request.ProdutoRequest;
import br.com.zup.desafioml.model.Produto;
import br.com.zup.desafioml.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest) {

        Produto produto = produtoRequest.toModel();
        produtoRepository.save(produto);

        return ResponseEntity.ok().build();

    }
}
