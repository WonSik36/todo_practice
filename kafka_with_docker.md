# Docker 기반 Kafka 실행법 

---

###깃허브 클론
```
git clone https://github.com/wurstmeister/kafka-docker
```

###docker-compose-single-broker.yml 수정
* Windows에서 실행시 `/bin/sh: illegal option -` 에러 발생

    ```
    kafka:
        build: .
    ```
    ~~build: .~~ -> image: wurstmeister/kafka
    ```
    kafka:
        image: wurstmeister/kafka
    ```
* IP 주소 변경
    ```
    environment:
      KAFKA_ADVERTISED_HOST_NAME: 127.0.0.1
    ```

### 실행
```
docker-compose -f docker-compose-single-broker.yml up -d
```

### 중지
```
docker-compose stop
```

---

## 참조
- https://github.com/wurstmeister/kafka-docker
- https://github.com/wurstmeister/kafka-docker/issues/529#issuecomment-564561402
- https://jobc.tistory.com/213