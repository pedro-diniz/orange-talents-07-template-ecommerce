package br.com.zup.desafioml.controller.util;

import br.com.zup.desafioml.model.Compra;

public interface EventoPosCompra {

    void processaPagamento(Compra compra);

}
