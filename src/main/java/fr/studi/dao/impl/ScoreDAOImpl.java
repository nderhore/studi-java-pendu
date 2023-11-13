package fr.studi.dao.impl;

import fr.studi.dao.ScoreDAO;
import fr.studi.pojo.Score;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreDAOImpl implements ScoreDAO {

    private final String DATABASE_URL = "jdbc:sqlite:score.db";

    public ScoreDAOImpl(){
        try(Connection connection = DriverManager.getConnection(DATABASE_URL)){
            String createTableQuery = "CREATE TABLE IF NOT EXISTS score (username TEXT PRIMARY KEY, score INTEGER)";
            try(PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery)){
                preparedStatement.execute();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void sauvegarderScore(Score score) {
        try(Connection connection = DriverManager.getConnection(DATABASE_URL)){
           String insertScoreQuery = "INSERT OR REPLACE INTO score (username, score) VALUES (?,?)";
           try(PreparedStatement preparedStatement = connection.prepareStatement(insertScoreQuery)){
               preparedStatement.setString(1,score.getUsername());
               preparedStatement.setInt(2,score.getScore());
               preparedStatement.executeUpdate();
           }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Score obtenirScore(String username) {
        return this.obtenirTousLesScores().stream()
                .filter(score -> score.getUsername().equals(username))
                .findFirst().orElse(null);
    }

    @Override
    public List<Score> obtenirTousLesScores() {
        List<Score> scores = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(DATABASE_URL)){
            String selectAllScoresQuery = "SELECT username, score FROM score";
            try(PreparedStatement preparedStatement = connection.prepareStatement(selectAllScoresQuery)){
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    while(resultSet.next()){
                        String username = resultSet.getString("username");
                        int score = resultSet.getInt("score");
                        scores.add(new Score(score,username));
                    }
                }
            }
        }catch (SQLException e ){
            e.printStackTrace();
        }
        return scores;
    }
}
