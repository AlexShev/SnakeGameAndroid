package com.example.snakegame.Enums;

public enum GamerType
{
    human(1),
    artificial(2),

    gamerTypeError(-1);

    private final int value;

    private GamerType(int value)
    {
        this.value = value;
    }

    public int value()
    {
        return value;
    }

    public static GamerType fromId(int id) {
        for (GamerType type : values()) {
            if (type.value == id) {
                return type;
            }
        }
        return gamerTypeError;
    }
}
