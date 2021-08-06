package br.com.zup.desafioml.controller.dto.response;

import br.com.zup.desafioml.model.Categoria;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashSet;
import java.util.Set;

public class ProdutoResponse {

    private String nome;

    private BigDecimal valor;

    private Integer quantidade;

    private Set<CaracteristicaProdutoResponse> caracteristicas = new HashSet<>();

    private String descricao;

    private Categoria categoria;

    private Set<ImagemProdutoResponse> imagens = new HashSet<>();

    private BigDecimal notaMedia;

    private int numeroOpinioes;

    private Set<OpiniaoProdutoResponse> opinioes = new HashSet<>();

    private Set<PerguntaProdutoResponse> perguntas = new HashSet<>();

    public ProdutoResponse(String nome, BigDecimal valor, Integer quantidade,
                           Set<CaracteristicaProdutoResponse> caracteristicas, String descricao, Categoria categoria,
                           Set<ImagemProdutoResponse> imagens, Set<OpiniaoProdutoResponse> opinioes,
                           Set<PerguntaProdutoResponse> perguntas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.imagens = imagens;
        this.opinioes = opinioes;
        this.perguntas = perguntas;
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

    public Set<CaracteristicaProdutoResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Set<ImagemProdutoResponse> getImagens() {
        return imagens;
    }

    public Set<OpiniaoProdutoResponse> getOpinioes() {
        return opinioes;
    }

    public Set<PerguntaProdutoResponse> getPerguntas() {
        return perguntas;
    }

    public BigDecimal getNotaMedia() {
        Double total = 0.0;
        for (OpiniaoProdutoResponse opiniao: opinioes) {
            total += opiniao.getNota();
        }
        Double notaMedia = total / opinioes.size();

        return new BigDecimal(notaMedia).round(new MathContext(3));
    }

    public int getNumeroOpinioes() {
        return opinioes.size();
    }
}
