package com.edw.controller;

import com.edw.bean.Hello;
import com.edw.bean.Users;
import com.edw.service.UserService;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * <pre>
 *     com.edw.controller.HelloWorldController
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 02 Mei 2023 11:10
 */
@Path("/")
public class HelloWorldController {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Inject
    UserService userService;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response index() {
        return Response
                .status(200)
                .entity(new Hello("world"))
                .build();
    }

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Retry(maxRetries = 1, retryOn= IOException.class)
    @Fallback(fallbackMethod = "getEmptyUser")
    public Response getUser(@PathParam("id") Integer id) throws IOException {
        Users users = userService.getUser(id);
        return Response
                .status(200)
                .entity(users)
                .build();
    }

    public Response getEmptyUser(Integer id) throws IOException {
        logger.debug("==== giving default response ====");
        return Response
                .status(200)
                .entity(new Users())
                .build();
    }
}
