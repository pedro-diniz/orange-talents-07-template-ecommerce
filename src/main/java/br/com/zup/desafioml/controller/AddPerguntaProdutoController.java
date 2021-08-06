package br.com.zup.desafioml.controller;

import br.com.zup.desafioml.controller.dto.request.PerguntaRequest;
import br.com.zup.desafioml.controller.util.EmailSender;
import br.com.zup.desafioml.controller.util.FakeEmailSender;
import br.com.zup.desafioml.model.PerguntaProduto;
import br.com.zup.desafioml.model.Produto;
import br.com.zup.desafioml.model.Usuario;
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
public class AddPerguntaProdutoController {

    private ProdutoRepository produtoRepository;
    private EmailSender emailSender;

    public AddPerguntaProdutoController(ProdutoRepository produtoRepository, FakeEmailSender emailSender) {
        this.produtoRepository = produtoRepository;
        this.emailSender = emailSender;
    }

    @PostMapping(value="/produtos/{id}/perguntas")
    @Transactional
    public ResponseEntity<?> perguntar(Authentication authentication, @PathVariable Long id,
                                       @RequestBody @Valid PerguntaRequest perguntaRequest) {

        Optional<Produto> possivelProduto = produtoRepository.findById(id);

        if (possivelProduto.isPresent()) {
            Produto produto = possivelProduto.get();

            PerguntaProduto perguntaProduto = perguntaRequest.toModel(authentication, produto);

            produto.adicionaPergunta(perguntaProduto);

            emailSender.enviarEmail(perguntaProduto);

            produtoRepository.save(produto);

            return ResponseEntity.ok().build();

        }

        return ResponseEntity.badRequest().build();
    }

}
