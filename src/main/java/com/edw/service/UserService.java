package com.edw.service;

import com.edw.bean.Users;
import com.edw.client.UserRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Random;

/**
 * <pre>
 *     com.edw.service.UserService
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 02 Mei 2023 11:50
 */
@ApplicationScoped
public class UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Inject
    @RestClient
    UserRestClient userRestClient;

    public Users getUser(Integer id) throws IOException {
        Random random = new Random();
        if(random.nextBoolean()) {
            logger.debug("==== simulate random exception ====");
            throw new IOException();
        }

        return userRestClient.get(id);
    }
}
