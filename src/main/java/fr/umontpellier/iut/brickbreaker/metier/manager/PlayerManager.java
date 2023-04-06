package fr.umontpellier.iut.brickbreaker.metier.manager;

import fr.umontpellier.iut.brickbreaker.metier.entite.AuthPlayer;
import fr.umontpellier.iut.brickbreaker.stockage.Security;
import fr.umontpellier.iut.brickbreaker.stockage.sql.StockagePlayerDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerManager {

    private static PlayerManager instance = null;
    private StockagePlayerDatabase stockage = new StockagePlayerDatabase();
    private Map<String, String> departments;

    private PlayerManager() {
        departments = stockage.getDepartments();
    }

    public static PlayerManager getInstance() {
        if (instance == null) instance = new PlayerManager();
        return instance;
    }

    public void createPlayer(String login, String department, String password) {
        AuthPlayer p = new AuthPlayer(login);
        byte[] salt = Security.getSalt();
        p.setSalt(salt);
        p.setPassword(password);
        p.setDepartment(department);
        stockage.create(p);
    }

    public void updatePlayer(String login, String department, String password) {
        AuthPlayer p = stockage.getByLogin(login);
        byte[] salt = Security.getSalt();
        p.setSalt(salt);
        p.setPassword(password);
        p.setDepartment(department);
        stockage.update(p);
    }

    public void deletePlayer(String login) {
        stockage.deleteByLogin(login);
    }

    public AuthPlayer getPlayer(String login) {
        return stockage.getByLogin(login);
    }

    public List<AuthPlayer> getPlayers() { return stockage.getAll(); }

    public Map<String, String> getDepartments() { return departments; }
}