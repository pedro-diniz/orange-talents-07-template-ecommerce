package br.com.zup.desafioml.controller.util;

import br.com.zup.desafioml.controller.dto.request.ImagensRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Component
public class ImageUploader {

    public Set<String> upaImagensRetornaUrls(@Valid ImagensRequest imagensRequest) {

        Set<String> linksImagens = new HashSet<>();
        for (MultipartFile imagem: imagensRequest.getImagens()) {
            String linkImagem = "http://zUploader.io/" + imagem.getOriginalFilename();
            linksImagens.add(linkImagem);
        }

        return linksImagens;

    }

}
