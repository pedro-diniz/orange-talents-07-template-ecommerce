package br.com.zup.desafioml.controller.util;

import br.com.zup.desafioml.model.PerguntaProduto;
import org.springframework.stereotype.Component;

@Component
public class FakeEmailSender implements EmailSender {

    @Override
    public String gerarEmail(PerguntaProduto perguntaProduto) {
        String email = "Um usuário fez uma pergunta sobre o seu produto " + perguntaProduto.getProduto().getNome() + "."
                + "\n\n" +
                perguntaProduto.getTitulo()
                + "\n" +
                "às " + perguntaProduto.getInstanteCriacao();

        return email;
    }

    @Override
    public void enviarEmail(PerguntaProduto perguntaProduto) {
        String email = gerarEmail(perguntaProduto);
        System.out.println("E-mail enviado para " + perguntaProduto.getVendedor().getUsername() + "\n\n" + email);
    }
}
