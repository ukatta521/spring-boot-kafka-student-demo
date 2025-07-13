package com.student.controllers;

import com.student.kafkaProducer.KafkaProducer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    private final KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer){
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public boolean publishMessage(@RequestBody String message){
        kafkaProducer.sendMessage(message);
        return true;
    }

    @PostMapping("PublishStream")
    public boolean publishStreamMessage(@RequestBody String messageStream){
        kafkaProducer.sendMessageStream("streams-plaintext-input" , messageStream);
        return true;
    }

    /*@GetMapping("/count/{word}")
    public Long getWordCount(@PathVariable String word) {
        KafkaStreams kafkaStreams = factoryBean.getKafkaStreams();
        ReadOnlyKeyValueStore<String, Long> counts = kafkaStreams.store(
                StoreQueryParameters.fromNameAndType("counts", QueryableStoreTypes.keyValueStore())
        );
        return counts.get(word);
    }*/

}
