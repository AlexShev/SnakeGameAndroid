package com.example.snakegame.Game;

import static java.lang.Thread.sleep;

import com.example.snakegame.Controllers.IController;
import com.example.snakegame.Enums.Condition;
import com.example.snakegame.Enums.Direction;
import com.example.snakegame.Enums.GamerType;
import com.example.snakegame.Enums.LevelDifficulty;
import com.example.snakegame.Gamers.GamerFactory;
import com.example.snakegame.Gamers.IGamer;
import com.example.snakegame.PartsOfGame.Field;
import com.example.snakegame.PartsOfGame.Snake;

public class Game
{
   private final IController _controler;
    private LevelDifficulty _level;
    private GamerType _type;
    private final int _height;
    private final int _width;

    public Game(IController controller, int height, int width)
    {
        _controler = controller;
        _height = height;
        _width = width;
    }

    public void Start()
    {
        if (AskParam())
        {
            do
            {
                int timeSliping = CalculateTactTime(_level, _type);

                Snake snake = new Snake (_width / 2, _height / 2, Direction.right);

                snake.SetMaxHungryLevel(_level, _height, _width);

                Field field = new Field(_level, _height, _width);

                IGamer gamer = new GamerFactory().CreateIGamer(_type, field, snake, _level);

                if (gamer == null)
                {
                    _controler.ShowMessage(IController.error);

                    continue;
                }

                field.ChangeField(snake.GetReductions());
                field.GenerateFood(1);

                _controler.ShowFullFrame(field);

                Condition condition = Condition.live;

                while (condition == Condition.live && !_controler.IsInterrupt())
                {
                    Direction dir = gamer.Command();

                    condition = snake.Move(field, dir);

                    field.GenerateFood(snake.GetLenght() + 1);
                    field.MoveFood(snake.GetLenght() + 1);

                    _controler.Reflection(field);
                    _controler.ShowScore(snake.GetLenght(), snake.GetTimeToDeleteTail(), _level);

                    mySleep(timeSliping);
                }

                mySleep(1000);

                _controler.ShowGameOver();

            } while (_controler.AskRestart() && AskParam());
        }

    }

    private void mySleep(int time)
    {
        try
        {
            sleep(time);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    private int CalculateTactTime(LevelDifficulty level, GamerType type)
    {
        if (type == GamerType.human)
        {
            switch (level)
            {
                case easy:
                    return 450;
                case middle:
                    return 300;
                case hard:
                    return 150;
            }
        }

        return 100;
    }

    private boolean AskParam()
    {
        _level = _controler.AskLevelDifficulty();

        while (_level == LevelDifficulty.levelDifficultyError)
        {
            _controler.ShowMessage(IController.inputError);

            if (!_controler.AskRestart())
            {
                return false;
            }

            _level = _controler.AskLevelDifficulty();
        }

        _type = _controler.AskGamerType();

        while (_type == GamerType.gamerTypeError)
        {
            _controler.ShowMessage(IController.inputError);

            if (!_controler.AskRestart())
            {
                return false;
            }

            _type = _controler.AskGamerType();
        }

        return true;
    }

}
