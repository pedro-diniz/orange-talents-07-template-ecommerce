package br.com.zup.desafioml.controller;

import br.com.zup.desafioml.controller.dto.request.OpiniaoRequest;
import br.com.zup.desafioml.model.OpiniaoProduto;
import br.com.zup.desafioml.model.Produto;
import br.com.zup.desafioml.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AddOpiniaoProdutoController {

    private ProdutoRepository produtoRepository;

    public AddOpiniaoProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping(value="/produtos/{id}/opinioes")
    @Transactional
    public ResponseEntity<?> opinar(Authentication authentication, @PathVariable Long id,
                                    @RequestBody @Valid OpiniaoRequest opiniaoRequest) {

        Optional<Produto> possivelProduto = produtoRepository.findById(id);

        if (possivelProduto.isPresent()) {
            Produto produto = possivelProduto.get();

            OpiniaoProduto opiniaoProduto = opiniaoRequest.toModel(authentication, produto);
            produto.adicionaOpiniao(opiniaoProduto);

            produtoRepository.save(produto);

            return ResponseEntity.ok().build();

        }

        return ResponseEntity.badRequest().build();
    }

}
