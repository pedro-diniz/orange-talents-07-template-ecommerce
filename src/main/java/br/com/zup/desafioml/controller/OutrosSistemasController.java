package br.com.zup.desafioml.controller;

import br.com.zup.desafioml.controller.dto.request.NotaFiscalRequest;
import br.com.zup.desafioml.controller.dto.request.RankingVendedorRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosSistemasController {

    @PostMapping(value = "/notas-fiscais")
    public void criaNF(@RequestBody @Valid NotaFiscalRequest notaFiscalRequest) throws InterruptedException {
        System.out.println("Criando nota fiscal para a compra: " + notaFiscalRequest.toString());
        Thread.sleep(150);
    }

    @PostMapping(value = "/ranking-vendedores")
    public void atualizaRanking(@RequestBody @Valid RankingVendedorRequest rankingRequest) throws InterruptedException {
        System.out.println("Atualizando o ranking para a compra: " + rankingRequest.toString());
        Thread.sleep(150);
    }

}
