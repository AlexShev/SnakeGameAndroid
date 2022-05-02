package com.example.snakegame.Gamers;

import com.example.snakegame.Enums.Direction;
import com.example.snakegame.PartsOfGame.Field;
import com.example.snakegame.PartsOfGame.Point;
import com.example.snakegame.PartsOfGame.Snake;

import java.util.LinkedList;
import java.util.Queue;

public class StupidArtificialGamer implements IGamer
{
    public StupidArtificialGamer(Field field, Snake snake)
    {
        _field = field;
        _snake = snake;
        _startPosition = new Point(1, _field.GetHeight() - 1);

        _commands = new LinkedList<>();

        MoveToStartPosition();
    }

    private void MoveToStartPosition()
    {
        int x = _snake.GetHead().x;

        // есть стартовая позиция, в которую мы должны перейти

        if (x > _startPosition.x)
        {
            for (int i = x - 1; i > _startPosition.x; i--)
            {
                _commands.add(Direction.left);
            }
        }
        else
        {
            for (int i = x; i < _startPosition.x; i++)
            {
                _commands.add(Direction.right);
            }
        }

        int y = _snake.GetHead().y;

        if (y > _startPosition.y)
        {
            for (int i = y - 1; i >= _startPosition.y; i--)
            {
                _commands.add(Direction.up);
            }
        }
        else
        {
            for (int i = y + 1; i < _startPosition.y; i++)
            {
                _commands.add(Direction.down);
            }
        }
    }

    public Direction Command()
    {
        if (_commands.isEmpty())
        {
            BuildRoute();
        }

        return GetCommand();
    }

    private void BuildRoute()
    {
        if (_snake.GetHead().equals(new Point(2, 1)))
        {
            _commands.add(Direction.left);

            for (int i = 1; i < _startPosition.y - 1; i++)
            {
                _commands.add(Direction.down);
            }

            _commands.add(Direction.right);
        }
        else
        {
            for (int i = _startPosition.x; i < _field.GetWidth() - 3; ++i)
            {
                _commands.add(Direction.right);
            }

            if (_snake.GetHead().y != 1)
            {
                _commands.add(Direction.up);
            }

            for (int i = _field.GetWidth() - 3; i > _startPosition.x; --i)
            {
                _commands.add(Direction.left);
            }

            if (_snake.GetHead().y != 2)
            {
                _commands.add(Direction.up);
            }
        }
    }

    private Direction GetCommand()
    {
        return _commands.poll();
    }

    private final Queue<Direction> _commands;
    private final Field _field;
    private final Snake _snake;
    private final Point _startPosition;
}