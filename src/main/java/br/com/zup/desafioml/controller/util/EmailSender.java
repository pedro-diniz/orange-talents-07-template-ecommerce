package br.com.zup.desafioml.controller.util;

import br.com.zup.desafioml.model.PerguntaProduto;

public interface EmailSender {

    public String gerarEmail(PerguntaProduto perguntaProduto);
    public void enviarEmail(PerguntaProduto perguntaProduto);

}
