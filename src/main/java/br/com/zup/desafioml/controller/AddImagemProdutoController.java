package br.com.zup.desafioml.controller;

import br.com.zup.desafioml.controller.dto.request.ImagensRequest;
import br.com.zup.desafioml.controller.util.ImageUploader;
import br.com.zup.desafioml.model.ImagemProduto;
import br.com.zup.desafioml.model.Produto;
import br.com.zup.desafioml.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
public class AddImagemProdutoController {

    private ProdutoRepository produtoRepository;
    private ImageUploader imageUploader;

    public AddImagemProdutoController(ProdutoRepository produtoRepository, ImageUploader imageUploader) {
        this.produtoRepository = produtoRepository;
        this.imageUploader = imageUploader;
    }

    @PostMapping(value="/produtos/{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionarImagens(Authentication authentication,
                                              @PathVariable Long id, @Valid ImagensRequest imagensRequest) {

        Optional<Produto> possivelProduto = produtoRepository.findById(id);

        if (possivelProduto.isPresent()) {
            Produto produto = possivelProduto.get();

            if (produto.usuarioLogadoEhDonoDoProduto(authentication, produto)) {

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
