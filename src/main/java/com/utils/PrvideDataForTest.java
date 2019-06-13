package main.java.com.utils;

import main.java.com.models.Utilisateur;

import java.util.ArrayList;
import java.util.List;

public class PrvideDataForTest {
    private final static List<Utilisateur> UTILISATEURS = new ArrayList<>();

    public static List<Utilisateur> getAllUser() {
        if (UTILISATEURS.size() <= 0) {
            UTILISATEURS.add(new Utilisateur(1, "nom1", "prenom1", "login1", "password1"));
            UTILISATEURS.add(new Utilisateur(2, "nom2", "prenom2", "login2", "password2"));
            UTILISATEURS.add(new Utilisateur(3, "nom3", "prenom3", "login3", "password3"));
            UTILISATEURS.add(new Utilisateur(4, "nom4", "prenom4", "login4", "password4"));
            UTILISATEURS.add(new Utilisateur(5, "nom5", "prenom5", "login5", "password5"));
        }
        return UTILISATEURS;
    }

    public static Utilisateur login(UserLoginModel userLoginModel) {
        return UTILISATEURS.stream()
                .filter(user ->
                        user.getLogin().equals(userLoginModel.getLogin())
                        && user.getPassword().equals(userLoginModel.getPassword()))
                .findAny()
                .orElse(null);
    }

    public static boolean addUser(Utilisateur utilisateur) {
        try {
            utilisateur.setId(UTILISATEURS.size()+1);
            UTILISATEURS.add(utilisateur);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }
}
