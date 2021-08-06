package br.com.zup.desafioml.controller.dto.request;

import br.com.zup.desafioml.model.ImagemProduto;
import br.com.zup.desafioml.model.Produto;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ImagensRequest {

    @NotNull
    @Size(min = 1)
    private List<MultipartFile> imagens;

    public List<MultipartFile> getImagens() {
        return imagens;
    }

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public Set<ImagemProduto> toModel(Set<String> listaUrls, Long id) {

        Set<ImagemProduto> imagensProduto = new HashSet<>();

        for (String urlImagem: listaUrls) {
            ImagemProduto imagemProduto = new ImagemProduto(urlImagem, new Produto(id));
            imagensProduto.add(imagemProduto);
        }

        return imagensProduto;
    }

}
