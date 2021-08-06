package br.com.zup.desafioml.model;

import br.com.zup.desafioml.controller.dto.response.ImagemProdutoResponse;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @URL
    private String link;

    @ManyToOne(targetEntity = Produto.class)
    private Produto produto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagemProduto that = (ImagemProduto) o;
        return id.equals(that.id) && link.equals(that.link) && produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link, produto);
    }

    public ImagemProduto(String link, Produto produto) {
        this.link = link;
        this.produto = produto;
    }

    public ImagemProduto() {
    }

    public String getLink() {
        return link;
    }

    public ImagemProdutoResponse toOutput() {
        return new ImagemProdutoResponse(link);
    }
}
