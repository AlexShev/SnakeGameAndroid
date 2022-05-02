package com.example.snakegame.Controllers;


import com.example.snakegame.Enums.GamerType;
import com.example.snakegame.Enums.LevelDifficulty;
import com.example.snakegame.PartsOfGame.Field;

public interface IController
{
    static final String tryAgain = "Would you like try again? [y/n]";
    static final String gameOver = " Game Over!!! ";
    static final String error = "Something is not good, restart the game, please";
    static final String inputError = "The entered parameters are not correct";


    boolean AskRestart();
    GamerType AskGamerType();
    LevelDifficulty AskLevelDifficulty();

    boolean IsInterrupt();

    void ShowFullFrame(Field field);
    void Reflection(Field field);
    void ShowScore(int score, int timeToDeleteTail, LevelDifficulty level);
    void ShowGameOver();
    void ShowMessage(String message);
}
