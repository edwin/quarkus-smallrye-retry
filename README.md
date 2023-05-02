# Quarkus and Retry mechanism using SmallRye

## About
Quarkus provide a convenient library to connect to an unreliable third party external system, and that is `SmallRye Fault Tolerance`. In this sample, we are trying to simulate a connection to `https://reqres.in/` while creating a random IOException. 

## Random IOException
```java
    public Users getUser(Integer id) throws IOException {
        Random random = new Random();
        if(random.nextBoolean()) {
            logger.debug("==== simulate random exception ====");
            throw new IOException();
        }

        return userRestClient.get(id);
    }
```

## Retry Mechanism
Retries for at most 4times, and only when `IOException` occurs.
```java
    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 4, retryOn= IOException.class)
    public Response getUser(@PathParam("id") Integer id) throws IOException {
        Users users = userService.getUser(id);
        return Response
                .status(200)
                .entity(users)
                .build();
    }
```

## How to Run
```
$ mvn quarkus:dev
```

## Logs
```
__  ____  __  _____   ___  __ ____  ______ 
 --/ __ \/ / / / _ | / _ \/ //_/ / / / __/ 
 -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
--\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
2023-05-02 12:08:50,391 INFO  [io.quarkus] (Quarkus Main Thread) quarkus-retry 1.0-SNAPSHOT on JVM (powered by Quarkus 2.16.6.Final) started in 0.283s. Listening on: http://localhost:8080
2023-05-02 12:08:50,392 INFO  [io.quarkus] (Quarkus Main Thread) Profile dev activated. Live Coding activated.
2023-05-02 12:08:50,392 INFO  [io.quarkus] (Quarkus Main Thread) Installed features: [cdi, rest-client, resteasy, resteasy-jackson, smallrye-context-propagation, smallrye-fault-tolerance, vertx]
2023-05-02 12:08:50,392 INFO  [io.qua.dep.dev.RuntimeUpdatesProcessor] (vert.x-worker-thread-0) Live reload total time: 0.303s 
2023-05-02 12:08:50,397 DEBUG [com.edw.ser.UserService] (executor-thread-0) ==== simulate random exception ====
2023-05-02 12:08:50,397 DEBUG [com.edw.ser.UserService] (executor-thread-0) ==== simulate random exception ====
2023-05-02 12:08:50,398 DEBUG [com.edw.ser.UserService] (executor-thread-0) ==== simulate random exception ====
```

As we can see, there are 3 exceptions when we are accessing `http://localhost:8080/user/2` but we still got success response because application is retrying it at most for 4 times until succeeded.