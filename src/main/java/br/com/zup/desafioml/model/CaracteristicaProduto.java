package br.com.zup.desafioml.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class CaracteristicaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @ManyToOne(targetEntity = Produto.class)
    private Produto produto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaracteristicaProduto that = (CaracteristicaProduto) o;
        return id.equals(that.id) && nome.equals(that.nome) && descricao.equals(that.descricao) && produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, produto);
    }

    public CaracteristicaProduto() {
    }

    public CaracteristicaProduto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }
}
