package com.edw.client;

import com.edw.bean.Users;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;

/**
 * <pre>
 *     com.edw.client.UserRestClient
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 02 Mei 2023 11:39
 */
@ApplicationScoped
@RegisterRestClient
@Path("/api")
public interface UserRestClient {
    @GET
    @Path("/users/{id}")
    Users get(@PathParam("id") Integer id);
}
