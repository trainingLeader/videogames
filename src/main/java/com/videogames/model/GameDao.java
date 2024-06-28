package com.videogames.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GameDao {
    private Connection connection;

    public GameDao() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addGame(Game game) throws SQLException {
        String query = "INSERT INTO games (name) VALUES (?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, game.getName());
        ps.executeUpdate();
    }

    public List<Game> getAllGames() throws SQLException {
        List<Game> games = new ArrayList<>();
        String query = "SELECT * FROM games";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Game game = new Game(rs.getInt("id"), rs.getString("name"));
            games.add(game);
        }
        return games;
    }

    public void updateGame(Game game) throws SQLException {
        String query = "UPDATE games SET name = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, game.getName());
        ps.setInt(2, game.getId());
        ps.executeUpdate();
    }

    public void deleteGame(int id) throws SQLException {
        String query = "DELETE FROM games WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
