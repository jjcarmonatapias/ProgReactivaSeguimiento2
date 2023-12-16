package com.tarjetas.sqs.tarjetasSQS.services;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.tarjetas.sqs.tarjetasSQS.model.Tarjeta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class TarjetaSQSServices {

    private final AmazonSQS sqsClient;

    private String getQueueUrl() {
        return sqsClient.getQueueUrl("tarjetas-credito").getQueueUrl();
    }

    public String publishStandardQueueMessage(Integer delaySeconds, Tarjeta tarjeta) {
        Map<String, MessageAttributeValue> atributosMensaje = new HashMap<>();
        atributosMensaje.put("numero",
                new MessageAttributeValue()
                        .withStringValue(tarjeta.numero())
                        .withDataType("String")
        );
        atributosMensaje.put("franquicia",
                new MessageAttributeValue()
                        .withStringValue(tarjeta.franquicia())
                        .withDataType("String")
        );
        atributosMensaje.put("descripcion",
                new MessageAttributeValue()
                        .withStringValue(tarjeta.descripcion())
                        .withDataType("String")
        );

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(this.getQueueUrl())
                .withMessageBody(tarjeta.franquicia())
                .withDelaySeconds(delaySeconds)
                .withMessageAttributes(atributosMensaje);

        return sqsClient.sendMessage(sendMessageRequest).getMessageId();
    }
}
