package br.com.zup.desafioml.controller;

import br.com.zup.desafioml.config.exception.NegocioException;
import br.com.zup.desafioml.controller.dto.request.PagseguroPagamentoRequest;
import br.com.zup.desafioml.controller.dto.request.PaypalPagamentoRequest;
import br.com.zup.desafioml.controller.util.EventoPosCompra;
import br.com.zup.desafioml.controller.util.EventoPosFalha;
import br.com.zup.desafioml.model.Compra;
import br.com.zup.desafioml.model.Pagamento;
import br.com.zup.desafioml.repository.CompraRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping
public class PagamentoController {

    public PagamentoController(CompraRepository compraRepository, Set<EventoPosCompra> eventosPosCompra,
                               Set<EventoPosFalha> eventosPosFalha) {
        this.compraRepository = compraRepository;
        this.eventosPosCompra = eventosPosCompra;
        this.eventosPosFalha = eventosPosFalha;
    }

    private CompraRepository compraRepository;

    // observers
    private Set<EventoPosCompra> eventosPosCompra;
    private Set<EventoPosFalha> eventosPosFalha;



    @PostMapping(value = "/retorno-pagseguro/{id}")
    public ResponseEntity<?> pagarPagSeguro(@PathVariable Long id,
                                   @RequestBody @Valid PagseguroPagamentoRequest pagseguroPagamentoRequest) {

        Compra compra = compraRepository.findById(id).orElseThrow(() -> new NegocioException("Compra não encontrada!"));

        if (Objects.equals(compra.getGatewayCompra().toString(), "PAGSEGURO")) {

            Pagamento pagamento = pagseguroPagamentoRequest.toModel(compra);

            if (compra.adicionaPagamento(pagamento)) {

                eventosPosCompra.forEach(evento -> evento.processaPagamento(compra));

            } else {

                eventosPosFalha.forEach(evento -> evento.processaFalhaPagamento(pagamento));

            }

            compraRepository.save(compra);

            return ResponseEntity.ok(pagseguroPagamentoRequest.getStatusRetornoPagamento());
        }
        else {
            throw new NegocioException("Esta compra não foi iniciada pelo PagSeguro. " +
                    "Finalize-a em sua respectiva plataforma.");
        }

    }

    @PostMapping(value = "/retorno-paypal/{id}")
    public ResponseEntity<?> pagarPayPal(@PathVariable @NotNull Long id,
                                   @RequestBody @Valid PaypalPagamentoRequest paypalPagamentoRequest) {

        Compra compra = compraRepository.findById(id).orElseThrow(() -> new NegocioException("Compra não encontrada!"));

        if (Objects.equals(compra.getGatewayCompra().toString(), "PAYPAL")) {

            Pagamento pagamento = paypalPagamentoRequest.toModel(compra);

            if (compra.adicionaPagamento(pagamento)) {

                eventosPosCompra.forEach(evento -> evento.processaPagamento(compra));

            } else {
                eventosPosFalha.forEach(evento -> evento.processaFalhaPagamento(pagamento));
            }

            compraRepository.save(compra);

            return ResponseEntity.ok(paypalPagamentoRequest.getStatusRetornoPagamento());

        }
        else {
            throw new NegocioException("Esta compra não foi iniciada pelo PayPal. " +
                    "Finalize-a em sua respectiva plataforma.");
        }

    }

}
