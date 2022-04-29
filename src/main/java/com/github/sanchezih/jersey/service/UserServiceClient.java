package com.github.sanchezih.jersey.service;

import com.github.sanchezih.jersey.entity.User;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class UserServiceClient {

    public static void main(String[] args) {
        //getUsers();
        //getUser();
       // createUser();
       // updateUser();
       //deleteUser();
    }

    private static void getUsers() {
        Client client = ClientBuilder.newClient();

        String entity = client.target("http://localhost:8080/poc-jersey-crud-2/api").path("users")
        .request(MediaType.APPLICATION_JSON).header("some-header", "true").get(String.class);

        System.out.println(entity);
    }

    private static void getUser() {
        Client client = ClientBuilder.newClient();

        String entity = client.target("http://localhost:8080/poc-jersey-crud-2/api").path("users").path("user/100")
       .request(MediaType.APPLICATION_JSON).header("some-header", "true").get(String.class);

        System.out.println(entity);
    }
 
    private static void createUser() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/poc-jersey-crud-2/api").path("users");

        User user = new User();
        user.setId(1);
        user.setName("Ramesh");

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON));

        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));
    }

    private static void updateUser() {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("http://localhost:8080/poc-jersey-crud-2/api").path("users")
        .path("user/1");

        User user = new User();
        user.setId(1);
        user.setName("Ramesh");

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.put(Entity.entity(user, MediaType.APPLICATION_JSON));

        String userJson = response.readEntity(String.class);

        System.out.println(response.getStatus());
        System.out.println(userJson);
    }

    private static void deleteUser() {

         Client client = ClientBuilder.newClient();
         WebTarget webTarget = client.target("http://localhost:8080/poc-jersey-crud-2/api").path("users")
        .path("user/100");

         User user = new User();
         user.setId(1);
         user.setName("Ramesh");

         Invocation.Builder invocationBuilder = webTarget.request();
         Response response = invocationBuilder.delete();

         System.out.println(response.getStatus());
         System.out.println(response.readEntity(String.class));
    }
}
