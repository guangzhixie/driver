cd driver-service
./gradlew build
docker build -t driver-service .
cd ..
docker-compose up