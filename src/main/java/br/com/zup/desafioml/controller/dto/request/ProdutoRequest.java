package br.com.zup.desafioml.controller.dto.request;

import br.com.zup.desafioml.config.validation.CheckIdExistence;
import br.com.zup.desafioml.model.CaracteristicaProduto;
import br.com.zup.desafioml.model.Categoria;
import br.com.zup.desafioml.model.Produto;
import br.com.zup.desafioml.model.Usuario;
import org.springframework.security.core.Authentication;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    @NotNull
    @Size(min=3, message = "tamanho mínimo de 3 características")
    private Set<CaracteristicaProdutoRequest> caracteristicasRequest;

    @NotBlank
    @Size(max=1000)
    private String descricao;

    @NotNull
    @CheckIdExistence(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;

    public Produto toModel(Authentication authentication) {
        return new Produto(
                nome,
                valor,
                quantidade,
                criaConjuntoCaracteristicas(),
                descricao,
                new Categoria(idCategoria),
                (Usuario) authentication.getPrincipal()
        );
    }

    private HashSet<CaracteristicaProduto> criaConjuntoCaracteristicas() {

        HashSet<CaracteristicaProduto> caracteristicas = new HashSet<>();

        for (CaracteristicaProdutoRequest caracteristicaProdutoRequest : caracteristicasRequest) {

            CaracteristicaProduto caracteristica = caracteristicaProdutoRequest.toModel();

            caracteristicas.add(caracteristica);
        }

        return caracteristicas;

    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Set<CaracteristicaProdutoRequest> getCaracteristicasRequest() {
        return caracteristicasRequest;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }
}
