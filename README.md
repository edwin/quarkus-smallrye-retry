# Quarkus and Retry mechanism using SmallRye

# About
Quarkus provide a convenient library to connect to an unreliable third party external system, and that is SmallRye Fault Tolerance. In this sample, we are trying to simulate a connection to `https://reqres.in/` while creating a random IOException. 

# Random IOException
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

# Retry Mechanism
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