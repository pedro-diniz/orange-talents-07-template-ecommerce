package br.com.zup.desafioml.controller;

import br.com.zup.desafioml.dto.request.UsuarioRequest;
import br.com.zup.desafioml.model.Usuario;
import br.com.zup.desafioml.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioRequest usuarioRequest) {

        Usuario usuario = usuarioRequest.toModel();
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

}
