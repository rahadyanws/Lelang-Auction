FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8082
ADD /build/libs/Lelang-Auction-0.0.1-SNAPSHOT.jar Lelang-Lelang-Auction-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Lelang-Auction-0.0.1-SNAPSHOT.jar"]