package br.com.zup.desafioml.repository;

import br.com.zup.desafioml.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
