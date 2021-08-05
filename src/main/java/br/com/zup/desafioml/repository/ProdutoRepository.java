package br.com.zup.desafioml.repository;

import br.com.zup.desafioml.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
