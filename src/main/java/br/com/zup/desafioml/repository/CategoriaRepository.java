package br.com.zup.desafioml.repository;

import br.com.zup.desafioml.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
