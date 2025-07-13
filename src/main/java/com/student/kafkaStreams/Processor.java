package com.student.kafkaStreams;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.Arrays;

@Component
public class Processor {

    @Autowired
    public void process(StreamsBuilder builder){
        final Serde<Integer> integerSerde = Serdes.Integer();
        final Serde<String> stringSerde = Serdes.String();
        final Serde<Long> longSerde = Serdes.Long();

        KStream<Integer, String> textLines = builder.stream("streams-plaintext-input", Consumed.with(integerSerde, stringSerde));
        System.out.println("textLines:   " + textLines );
        KTable<String, Long> wordCounts = textLines
                .flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
                .groupBy((key, value) -> value, Grouped.with(stringSerde, stringSerde))
                .count();
        System.out.println("wordCounts -- "+ wordCounts);
        wordCounts.toStream().to("streams-wordcount-output", Produced.with(stringSerde, longSerde));
    }
}
