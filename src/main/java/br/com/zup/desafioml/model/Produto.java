package br.com.zup.desafioml.model;

import br.com.zup.desafioml.controller.dto.response.*;
import org.springframework.security.core.Authentication;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private Integer quantidade;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "produto_id")
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @NotBlank
    @Size(max=1000)
    private String descricao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Usuario dono;

    private Instant instanteCadastro = Instant.now();

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "produto_id")
    private Set<ImagemProduto> imagens = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "produto_id")
    private Set<OpiniaoProduto> opinioes = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "produto_id")
    private Set<PerguntaProduto> perguntas = new HashSet<>();

    public Produto() {}

    public Produto(String nome, BigDecimal valor, Integer quantidade, Set<CaracteristicaProduto> caracteristicas, String descricao, Categoria categoria, Usuario dono) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dono = dono;
    }

    public Produto(Long id) {
        this.id = id;
    }

    public Usuario getDono() {
        return dono;
    }

    public void adicionaImagens(Set<ImagemProduto> imagensProduto) {
        for (ImagemProduto imagem: imagensProduto) {
            imagens.add(imagem);
        }

    }

    public boolean usuarioLogadoEhDonoDoProduto(Authentication authentication, Produto produto) {
        return Objects.equals(produto.getDono().getId(), ((Usuario) authentication.getPrincipal()).getId());
    }

    public void adicionaOpiniao(OpiniaoProduto opiniaoProduto) {
        opinioes.add(opiniaoProduto);
    }

    public void adicionaPergunta(PerguntaProduto perguntaProduto) {
        perguntas.add(perguntaProduto);
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

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public Set<OpiniaoProduto> getOpinioes() {
        return opinioes;
    }

    public Set<PerguntaProduto> getPerguntas() {
        return perguntas;
     }

    public ProdutoResponse toOutput() {
        return new ProdutoResponse(
                nome,
                valor,
                quantidade,
                caracteristicas.stream().map(caracteristica -> caracteristica.toOutput()).collect(Collectors.toSet()),
                descricao,
                categoria,
                imagens.stream().map(imagem -> imagem.toOutput()).collect(Collectors.toSet()),
                opinioes.stream().map(opiniao -> opiniao.toOutput()).collect(Collectors.toSet()),
                perguntas.stream().map(pergunta -> pergunta.toOutput()).collect(Collectors.toSet())
        );
    }

    public void abateEstoque(Integer qtdCompra) {
        if (qtdCompra <= quantidade) {
            quantidade -= qtdCompra;
            System.out.println(quantidade);
        }
    }
}
