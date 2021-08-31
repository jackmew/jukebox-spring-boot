# metal - spring boot

### jar   
`./mvnw clean package`  
`java -jar ./target/*.jar`    
### image    
`./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=zestlifia/pop`  
`docker run -p 8082:8082 --env ARTISTS=zest,mew zestlifia/pop`