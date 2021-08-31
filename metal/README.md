# metal - spring boot

### jar   
`./mvnw clean package`  
`java -jar ./target/*.jar`    
### image    
`./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=zestlifia/metal`  
`docker run -p 8081:8081 --env ARTISTS=zest,mew zestlifia/metal`