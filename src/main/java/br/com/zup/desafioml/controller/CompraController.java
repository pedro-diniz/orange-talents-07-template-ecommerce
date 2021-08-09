package br.com.zup.desafioml.controller;

import br.com.zup.desafioml.config.exception.NegocioException;
import br.com.zup.desafioml.controller.dto.request.CompraRequest;
import br.com.zup.desafioml.controller.util.FakeEmailSender;
import br.com.zup.desafioml.model.Compra;
import br.com.zup.desafioml.model.Produto;
import br.com.zup.desafioml.repository.CompraRepository;
import br.com.zup.desafioml.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class CompraController {

    private CompraRepository compraRepository;
    private ProdutoRepository produtoRepository;
    private FakeEmailSender fakeEmailSender;

    public CompraController(CompraRepository compraRepository, ProdutoRepository produtoRepository,
                            FakeEmailSender fakeEmailSender) {
        this.compraRepository = compraRepository;
        this.produtoRepository = produtoRepository;
        this.fakeEmailSender = fakeEmailSender;
    }

    @PostMapping(value="/produtos/{id}/compras")
    public ResponseEntity<?> comprar(Authentication authentication, @PathVariable Long id,
                                     @RequestBody @Valid CompraRequest compraRequest) {

        Produto produto = produtoRepository.findById(id).orElseThrow(
                () -> new NegocioException("Produto não encontrado"));

        if (produto.getQuantidade() >= compraRequest.getQuantidade()) {

            Compra compra = compraRequest.toModel(authentication, produto);

            produto.abateEstoque(compraRequest.getQuantidade());
            produtoRepository.save(produto);

            fakeEmailSender.enviarEmailCompra(compra);

            compraRepository.save(compra);

            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(
                    compraRequest.getGateway().trim().toLowerCase() + ".com?buyerId=" + compra.getId() +
                            "&redirectUrl=localhost:8080/produtos/" + id + "/compras"
            )).build();

        } else {
            throw new NegocioException("Quantidade do produto em estoque: " + produto.getQuantidade() +
                    ". Escolha uma quantia igual ou menor do que a quantidade em estoque.");
        }

    }

}
