#!/bin/bash
set -e
cd "$(dirname "$0")/.."

echo "=== [1/4] Gradle build ==="
./gradlew bootJar

echo "=== [2/4] Docker image build ==="
docker build -t cafeteria-app . # cafeteria-app 을 원하시는 이름으로 변경 하시면 됩니다!

echo "=== [3/4] 기존 컨테이너 정리 ==="
docker stop cafeteria-app || true # cafeteria-app 을 원하시는 이름으로 변경 하시면 됩니다!
docker rm cafeteria-app || true # cafeteria-app 을 원하시는 이름으로 변경 하시면 됩니다!

echo "=== [4/4] Spring 컨테이너 실행 ==="
docker run -d \
  --name cafeteria-app \
  --network spring-net \
  -p 8080:8080 \
  -e "SPRING_DATASOURCE_URL=jdbc:mysql://mysql-compose:3306/product?rewriteBatchedStatements=true" \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=12345678 \
  -e SPRING_DATA_REDIS_HOST=redis-compose \
  -e SPRING_DATA_REDIS_PORT=6379 \
  cafeteria-app

echo "=== 로그 출력 ==="
docker logs -f cafeteria-app