package br.com.zup.desafioml.controller;

import br.com.zup.desafioml.controller.dto.request.ImagensRequest;
import br.com.zup.desafioml.controller.dto.request.ProdutoRequest;
import br.com.zup.desafioml.controller.util.ImageUploader;
import br.com.zup.desafioml.model.ImagemProduto;
import br.com.zup.desafioml.model.Produto;
import br.com.zup.desafioml.model.Usuario;
import br.com.zup.desafioml.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ProdutoRepository produtoRepository;
    private ImageUploader imageUploader;

    public ProdutoController(ProdutoRepository produtoRepository, ImageUploader imageUploader) {
        this.produtoRepository = produtoRepository;
        this.imageUploader = imageUploader;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(Authentication authentication, @RequestBody @Valid ProdutoRequest produtoRequest) {

        Produto produto = produtoRequest.toModel(authentication);
        produtoRepository.save(produto);

        return ResponseEntity.ok().build();

    }

    @PostMapping(value="/{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionarImagens(Authentication authentication,
                                              @PathVariable Long id, @Valid ImagensRequest imagensRequest) {

        Optional<Produto> possivelProduto = produtoRepository.findById(id);

        if (possivelProduto.isPresent()) {
            Produto produto = possivelProduto.get();

            if (produto.getDono().getId() == ((Usuario) authentication.getPrincipal()).getId()) {

                Set<String> listaUrls = imageUploader.upaImagensRetornaUrls(imagensRequest);

                Set<ImagemProduto> imagensProduto = imagensRequest.toModel(listaUrls, id);

                produto.adicionaImagens(imagensProduto);
                produtoRepository.save(produto);

                return ResponseEntity.ok().build();
            }
            else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

        return ResponseEntity.badRequest().build();
    }

}
