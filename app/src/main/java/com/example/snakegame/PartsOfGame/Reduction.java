package com.example.snakegame.PartsOfGame;

import com.example.snakegame.Enums.PointType;

/// <summary>
/// Структура для описания редакционных правок.
/// </summary>
public class Reduction
{
    /// <value>Точка, где производиться изменение</value>
    public Point point;

    /// <value>Новый тип точки</value>
    public PointType type;


    /// <summary>
    /// Конструктор, создающий редакционную правку
    /// </summary>
    /// <param name="point">Где происходит изменение</param>
    /// <param name="type">Какого типа должна стать точка</param>
    public Reduction(Point point, PointType type)
    {
        this.point = point;
        this.type = type;
    }
}
