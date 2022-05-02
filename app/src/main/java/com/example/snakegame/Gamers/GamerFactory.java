package com.example.snakegame.Gamers;


import com.example.snakegame.Enums.GamerType;
import com.example.snakegame.Enums.LevelDifficulty;
import com.example.snakegame.PartsOfGame.Field;
import com.example.snakegame.PartsOfGame.Snake;

public class GamerFactory
{
    public IGamer CreateIGamer(GamerType type, Field field, Snake snake, LevelDifficulty level)
    {
        if (type == GamerType.human)
        {
            // здесь должен был быть класс умеющий прослушивать нажатия клавиш
        }
        else if (type == GamerType.artificial)
        {
            if (level == LevelDifficulty.easy)
            {
                return new StupidArtificialGamer(field, snake);
            }
        }

        return null;
    }
}
