package fr.studi.dao;

import fr.studi.pojo.Score;

import java.util.List;

public interface ScoreDAO {

    void sauvegarderScore(Score score);

    Score obtenirScore(String username);

    List<Score> obtenirTousLesScores();
}
