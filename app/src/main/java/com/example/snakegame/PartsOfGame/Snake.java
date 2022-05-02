package com.example.snakegame.PartsOfGame;


import com.example.snakegame.Enums.Condition;
import com.example.snakegame.Enums.Direction;
import com.example.snakegame.Enums.LevelDifficulty;
import com.example.snakegame.Enums.PointType;

import java.util.LinkedList;
import java.util.Queue;

public class Snake
{
    public Snake(int x, int y, Direction dir)
    {
        _dir = dir;
        _hungryLevel = 0;
        _maxHungryLevel = 1;

        _head = new Point(x, y);

        _tail = new LinkedList<>();
        _reductions = new LinkedList<>();

        _reductions.add(new Reduction(_head, PointType.snakeHead));
    }

    public void SetMaxHungryLevel(LevelDifficulty level, int height, int width)
    {
        if (level == LevelDifficulty.easy)
        {
            _maxHungryLevel = height * width;
        }
        else
        {
            _maxHungryLevel = (height + width) / level.value();
        }
    }

    private void AddTail(Point newHead)
    {
        _reductions.add(new Reduction(newHead, PointType.snakeHead));
        _reductions.add(new Reduction(_head, PointType.snakeBody));

        _tail.addFirst(_head);
        _head = newHead;
    }

    private void MoveTail(Point newHead)
    {
        _reductions.add(new Reduction(newHead, PointType.snakeHead));
        _reductions.add(new Reduction(_head, PointType.snakeBody));

        _tail.addFirst(_head);
        _head = newHead;

        _reductions.add(new Reduction(_tail.getLast(), PointType.emptiness));
        _tail.removeLast();
    }

    private boolean DeleteTail()
    {
        if (_tail == null || _tail.size() == 0)
        {
            return false;
        }
        else
        {
            _reductions.add(new Reduction(_tail.getLast(), PointType.emptiness));
            _tail.removeFirst();
            return true;
        }
    }

    public Condition Move(Field field, Direction dir)
    {
        Condition res = Condition.live;

        if (dir != Direction.nothing)
        {
            _dir = dir;
        }

        Point newHead = new Point(_head);

        switch (_dir)
        {
            case left:
                --newHead.x;
                break;
            case right:
                ++newHead.x;
                break;
            case up:
                --newHead.y;
                break;
            case down:
                ++newHead.y;
                break;
        }

        if ((newHead.x < 1) || (newHead.x >= field.GetWidth() - 1) || (newHead.y < 1) || (newHead.y >= field.GetHeight() - 1))
        {
            _hungryLevel = 0;
            res = Condition.dedth;
        }
        else if (field.TypeAt(newHead.y, newHead.x) == PointType.food)
        {
            AddTail(newHead);
            _hungryLevel = 0;
            field.DisappearFood(newHead);
        }
        else if (field.TypeAt(newHead.y, newHead.x) == PointType.emptiness)
        {
            MoveTail(newHead);
            ++_hungryLevel;
        }
        else
        {
            res = Condition.dedth;
        }

        if (_hungryLevel > _maxHungryLevel)
        {
            _hungryLevel = 0;
            res = DeleteTail() ? Condition.live : Condition.dedth;
        }

        field.ChangeField(_reductions);

        return res;
    }

    public Queue<Reduction> GetReductions() { return _reductions; }

    public int GetLenght() { return _tail.size(); }

    public int GetTimeToDeleteTail() { return _maxHungryLevel - _hungryLevel; }

    public Point GetHead() { return _head; }

    final LinkedList<Point> GetTail() { return _tail; }

    private Direction _dir;
    private Point _head;
    private LinkedList<Point> _tail;
    private Queue<Reduction> _reductions;
    private int _hungryLevel;
    private int _maxHungryLevel;
}

