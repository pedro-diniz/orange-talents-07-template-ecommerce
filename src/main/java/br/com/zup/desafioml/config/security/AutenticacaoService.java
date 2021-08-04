package br.com.zup.desafioml.config.security;

import br.com.zup.desafioml.model.Usuario;
import br.com.zup.desafioml.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    public AutenticacaoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override // o Spring Security consulta login e senha por baixo dos panos
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuario = usuarioRepository.findByLogin(username);
        if (usuario.isPresent()) {
            return usuario.get();
        }

        throw new UsernameNotFoundException("Credenciais inválidas. Usuário não existente.");

    }

}
