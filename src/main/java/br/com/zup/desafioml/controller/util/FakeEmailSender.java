package br.com.zup.desafioml.controller.util;

import br.com.zup.desafioml.model.Compra;
import br.com.zup.desafioml.model.Pagamento;
import br.com.zup.desafioml.model.PerguntaProduto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FakeEmailSender implements EmailSender, EventoPosCompra, EventoPosFalha {

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

    public void enviarEmailCompra(Compra compra) {
        System.out.println("Um usuário está interessado em comprar seu produto! Esta mensagem estaria melhor com" +
                " getters na classe para eu pegar as características do produto e do comprador!");
    }

    public void processaPagamento(Compra compra) {
        System.out.println("Sua compra de " + compra.getProduto().getNome() +
                " foi concluída com sucesso!" +
                "\n\n" +
                "Informações: \n" +
                "Quantidade: " + compra.getQuantidade() +
                "\nValor unitário: R$" + compra.getValor() +
                "\nValor total da compra: R$" + compra.getValor().multiply(BigDecimal.valueOf(compra.getQuantidade())) +
                "\nPlataforma de pagamento: " + compra.getGatewayCompra());
    }

    public void processaFalhaPagamento(Pagamento pagamento) {
        System.out.println("Sua compra de " + pagamento.getCompra().getProduto().getNome() +
                " não foi aprovada. Sugerimos que revise suas informações de pagamento e tente novamente." +
                "\n\n" +
                "Informações: \n" +
                "Quantidade: " + pagamento.getCompra().getQuantidade() +
                "\nValor unitário: R$" + pagamento.getCompra().getValor() +
                "\nValor total da compra: R$" + pagamento.getCompra().getValor().multiply(BigDecimal.valueOf(pagamento.getCompra().getQuantidade())) +
                "\nPlataforma de pagamento: " + pagamento.getCompra().getGatewayCompra() +
                "\nData/hora da transação: " + pagamento.getInstanteCompra());
    }
}
