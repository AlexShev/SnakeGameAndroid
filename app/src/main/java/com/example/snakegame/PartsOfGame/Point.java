package com.example.snakegame.PartsOfGame;

import java.util.Objects;

public class Point
{
    public int x;
    public int y;

    public Point(Point point)
    {
        this(point.x, point.y);
    }

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Point()
    {
        x = 0;
        y = 0;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }
}
