//package tianchi.im.starter.mq;
//
//
//import io.vertx.core.Vertx;
//import io.vertx.kafka.client.consumer.KafkaConsumer;
//import io.vertx.kafka.client.producer.KafkaProducer;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * kafka 管理类
// */
//public class KafkaManager {
//
//    private static KafkaConsumer consumer;
//    private static KafkaProducer producer;
//
//
//    public static void configKafka(Vertx vertx) {
//        consumer = configKafkaConsumer(vertx);
//        producer = configKafkaProducer(vertx);
//    }
//
//    static KafkaConsumer configKafkaConsumer(Vertx vertx) {
//        Map<String, String> config = new HashMap<>();
//        config.put("bootstrap.servers", "localhost:9092");
//        config.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        config.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        config.put("group.id", "my_group");
//        config.put("auto.offset.reset", "earliest");
//        config.put("enable.auto.commit", "false");
//        // use consumer for interacting with Apache Kafka
//        KafkaConsumer<String, String> consumer = KafkaConsumer.create(vertx, config);
//        return consumer;
//    }
//
//    static KafkaProducer configKafkaProducer(Vertx vertx) {
//        Map<String, String> config = new HashMap<>();
//        config.put("bootstrap.servers", "localhost:9092");
//        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        config.put("acks", "1");
//
//        // use producer for interacting with Apache Kafka
//        KafkaProducer<String, String> producer = KafkaProducer.create(vertx, config);
//        return producer;
//    }
//
//
//}
