package br.com.zup.desafioml.controller.util;

import br.com.zup.desafioml.model.Compra;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class EmissorDeNotaFiscal implements EventoPosCompra{

    public void processaPagamento(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(),
                "idPagante", compra.getComprador().getId());
        restTemplate.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);
    }
}
