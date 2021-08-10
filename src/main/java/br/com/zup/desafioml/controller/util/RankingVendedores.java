package br.com.zup.desafioml.controller.util;

import br.com.zup.desafioml.model.Compra;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class RankingVendedores implements EventoPosCompra{

    public void processaPagamento(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(),
                "idVendedor", compra.getProduto().getDono().getId());
        restTemplate.postForEntity("http://localhost:8080/ranking-vendedores", request, String.class);
    }
}
