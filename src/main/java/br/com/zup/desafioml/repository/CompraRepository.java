package br.com.zup.desafioml.repository;

import br.com.zup.desafioml.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Long> {
}
