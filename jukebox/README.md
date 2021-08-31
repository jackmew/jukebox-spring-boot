# jukebox - spring boot

### jar   
`./mvnw clean package`  
`java -jar ./target/*.jar`    
### image    
`./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=zestlifia/jukebox`  
`docker run -p 8083:8083 zestlifia/jukebox`  
`docker run -p 8083:8083 --env METAL_HOST=172.31.0.1 zestlifia/jukebox`