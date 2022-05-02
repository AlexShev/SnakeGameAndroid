package com.example.snakegame.Enums;

public enum LevelDifficulty
{
    easy(1),
    middle(2),
    hard(3),
    levelDifficultyError(-1);

    private final int value;

    LevelDifficulty(int value)
    {
        this.value = value;
    }

    public int value()
    {
        return value;
    }

    public static LevelDifficulty fromId(int id) {
        for (LevelDifficulty type : values()) {
            if (type.value == id) {
                return type;
            }
        }
        return levelDifficultyError;
    }
}
