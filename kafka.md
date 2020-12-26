# Kafka 실행법

## 설치
* 사이트 참조  
    https://seungwoo0429.tistory.com/28
  
## 실행
### Zookeeper 실행
```
cd C:\kafka\kafka_2.13-2.7.0  
bin\windows\zookeeper-server-start.bat config\zookeeper.properties
```

### Kafka 실행
```
cd C:\kafka\kafka_2.13-2.7.0  
bin\windows\kafka-server-start.bat config\server.properties
```

### Producer 실행
```
cd C:\kafka\kafka_2.13-2.7.0  
bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic {topic}
```

### Consumer 실행
```
cd C:\kafka\kafka_2.13-2.7.0  
bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic {topic} --from-beginning
```
----

## 코드 참조
https://www.baeldung.com/spring-kafka