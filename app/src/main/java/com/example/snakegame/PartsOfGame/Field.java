package com.example.snakegame.PartsOfGame;

import com.example.snakegame.Enums.LevelDifficulty;
import com.example.snakegame.Enums.PointType;

import java.util.*;

public class Field
{
    public Field(LevelDifficulty level, int height, int width)
    {
        _level = level;
        _height= height;
        _width = width;

        _field = InitField(height, width);

        _randomGenerator = new Random();
        _randomGenerator.setSeed(123456789);

        _foods = new Set[_height];

        _counterFood = 0;

        _reductions = new LinkedList<>();

        _maxFoodNumbers = (int)(Math.max(1., Math.sqrt(width + height) / level.value()));
    }

    public void ChangeField(Queue<Reduction> reductions)
    {
        while (!reductions.isEmpty())
        {
            Reduction red = reductions.poll();

            _reductions.add(red);

            assert red != null;
            _field[red.point.y][red.point.x] = red.type;
        }
    }

    public void MoveFood(int snakeLength)
    {
        if (_level.value() >= LevelDifficulty.hard.value() && RandomInt(0, _height) == 0)
        {
            DisappearFood();
            GenerateFood(snakeLength);
        }
    }

    public void DisappearFood(Point pointToDisappear)
    {
        --_counterFood;

        _foods[pointToDisappear.y].remove(pointToDisappear.x);

        _field[pointToDisappear.y][pointToDisappear.x] = PointType.emptiness;
    }

    public void DisappearFood()
    {
        Point pointToDisappear = new Point();

        do {
            pointToDisappear.y = RandomInt(1, _height - 1);
        } while (_foods[pointToDisappear.y].isEmpty());

        pointToDisappear.x = _foods[pointToDisappear.y].iterator().next();

        _reductions.add(new Reduction(pointToDisappear, PointType.emptiness));

        DisappearFood(pointToDisappear);
    }

    public void GenerateFood(int snakeLength)
    {
        if (snakeLength == (_height - 2) * (_width - 2) - _maxFoodNumbers)
        {
            _maxFoodNumbers = 1;
        }

        if(snakeLength == (_height - 2) * (_width - 2))
        {
            _maxFoodNumbers = 0;
        }

        for (; _counterFood < _maxFoodNumbers; _counterFood++)
        {
            Point food = GeneratePoint();

            if (_field[food.y][food.x] == PointType.emptiness)
            {
                _field[food.y][food.x] = PointType.food;

                _reductions.add(new Reduction(food, PointType.food));

                if (_foods[food.y] == null)
                {
                    _foods[food.y] = new HashSet<>();
                }

                _foods[food.y].add(food.x);
            }
            else
            {
                --_counterFood;
            }
        }
    }

    public void MoveReductions(Queue<Reduction> toMove)
    {
        toMove.addAll(_reductions);
        _reductions.clear();
    }

    public PointType TypeAt(int height, int width)
    {
        assert(height < _height && width < _width);

        return _field[height][width];
    }

    public final PointType[][] GetField() { return _field; }
    public int GetWidth() { return _width; }
    public int GetHeight() { return _height; }

    private static PointType[][] InitField(int height, int width)
    {
        PointType[][] field = new PointType[height][width];

        Arrays.stream(field).forEach(pointTypes -> Arrays.fill(pointTypes, PointType.emptiness));

        for (int i = 0; i < width; i++)
        {
            field[0][i] = PointType.border;
            field[height - 1][i] = PointType.border;
        }

        for (int i = 1; i < height - 1; i++)
        {
            field[i][0] = field[i][width - 1] = PointType.border;
        }

        return field;
    }

    private int RandomInt(int min, int max)
    {
        return min + Math.abs(_randomGenerator.nextInt()) % (max - min);
    }

    private Point GeneratePoint()
    {
        return new Point(RandomInt(1, _width - 1), RandomInt(1, _height - 1));
    }

    private final PointType[][] _field;
    private final Set<Integer>[] _foods;
    private final Queue<Reduction> _reductions;

    private int _counterFood;
    private int _maxFoodNumbers;
    private final LevelDifficulty _level;

    private final int _width;
    private final int _height;

    private final Random _randomGenerator;
}
