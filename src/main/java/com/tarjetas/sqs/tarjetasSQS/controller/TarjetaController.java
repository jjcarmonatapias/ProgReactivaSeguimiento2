package com.tarjetas.sqs.tarjetasSQS.controller;

import com.tarjetas.sqs.tarjetasSQS.model.Tarjeta;
import com.tarjetas.sqs.tarjetasSQS.services.TarjetaSQSServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
@RequestMapping("/tarjetas-credito")
public class TarjetaController {
    TarjetaSQSServices tarjetaSQSServices;

    @PostMapping("/aws")
    public Mono<String> postMessageQueue(@RequestBody Tarjeta tarjeta) {
        return Mono.just(tarjetaSQSServices.publishStandardQueueMessage(10, tarjeta));
    }

}
