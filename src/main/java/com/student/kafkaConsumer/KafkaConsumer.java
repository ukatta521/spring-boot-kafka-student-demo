package com.student.kafkaConsumer;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Arrays;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "quickstartEvents")
    public void consumeMessage(String message){
        System.out.println("Consumed Message ==== " + message);
    }

    //    @Autowired
    //    public void wordCount(StreamsBuilder builder){
    //
    //        KStream<String, String> textLines = builder.stream("streams-plaintext-input");
    //        System.out.println("text Lines == "+ textLines);
    //        KTable<String, Long> wordCounts = textLines
    //                .flatMapValues(textLine -> Arrays.asList(textLine.toLowerCase().split("\\W")))
    //                        .groupBy((key, word) -> word)
    //                .count(Materialized.as("counts-store"));
    //        wordCounts.toStream().to("wordCountsWithTopic", Produced.with(Serdes.String(), Serdes.Long()));
    //
    //     //   KafkaStreams streams = new KafkaStreams(builder.build(), props);
    //      //  streams.start();
    //
    //        System.out.println();
    //    }
}
