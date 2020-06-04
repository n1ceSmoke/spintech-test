For start app: 
1. Upadte tmdb.apikey in src\main\resources\application.properties
2. Run mvn clean install
3. Run mvn spring-boot:run
4. Open http://localhost:8080/swagger-ui.html 

What's new?
1. Add list of actors. Can get at endpoint http://localhost:8080/tv/actors
2. Add ability to add actors to list of favorite actors. Endpoint: http://localhost:8080/user/add-to-favorites