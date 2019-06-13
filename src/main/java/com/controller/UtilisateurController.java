package main.java.com.controller;

import com.google.gson.Gson;
import main.java.com.models.Utilisateur;
import main.java.com.utils.PrvideDataForTest;
import main.java.com.utils.UserLoginModel;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/utilisateur")
public class UtilisateurController {
    private final Gson gson = new Gson();

    @GET
    @Path("/list-user")
    @Produces(MediaType.APPLICATION_JSON)
    public String listUtilisateur() {
        try {
            return gson.toJson(PrvideDataForTest.getAllUser());
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(HttpServletResponse.SC_EXPECTATION_FAILED);
        }
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(String requestBody) {
        UserLoginModel userLoginModel = gson.fromJson(requestBody, UserLoginModel.class);
        Utilisateur utilisateur = PrvideDataForTest.login(userLoginModel);
        if (utilisateur != null) {
            return gson.toJson(utilisateur);
        } else return gson.toJson(HttpServletResponse.SC_FORBIDDEN);
    }

    @POST
    @Path("/add-user")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String addUser(String requestBody) {
        Utilisateur utilisateur = gson.fromJson(requestBody, Utilisateur.class);
        boolean resultat = PrvideDataForTest.addUser(utilisateur);
        if (resultat) {
            return gson.toJson(HttpServletResponse.SC_OK);
        } else return gson.toJson(HttpServletResponse.SC_BAD_REQUEST);
    }
}
