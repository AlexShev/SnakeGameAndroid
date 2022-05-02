package com.example.snakegame.Controllers;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Toast;

import com.example.snakegame.Enums.GamerType;
import com.example.snakegame.Enums.LevelDifficulty;
import com.example.snakegame.Enums.PointType;
import com.example.snakegame.PartsOfGame.Field;
import com.example.snakegame.PartsOfGame.Point;
import com.example.snakegame.PartsOfGame.Reduction;
import com.example.snakegame.R;

import java.util.LinkedList;
import java.util.Queue;

public class GUIcontroller implements IController
{
    View[][] fieldView;
    Context context;

    public GUIcontroller(View[][] field, Context context) {
        fieldView = field;
        this.context = context;
    }

    @Override
    public boolean AskRestart() {
        return false;
    }

    @Override
    public GamerType AskGamerType() {
        return GamerType.artificial;
    }

    @Override
    public LevelDifficulty AskLevelDifficulty() {
        return LevelDifficulty.easy;
    }

    @Override
    public boolean IsInterrupt() {
        return false;
    }

    @Override
    public void ShowFullFrame(Field field) {
        PointType[][] frame = field.GetField();


        for (int i = 0; i < frame.length; i++) {
            for (int j = 0; j < frame[i].length; j++) {
                Reflect(fieldView[i][j], frame[i][j]);
            }
        }
    }

    @Override
    public void Reflection(Field field) {
        Queue<Reduction> changes = new LinkedList();

        field.MoveReductions(changes);

        while (!changes.isEmpty())
        {
            Reduction reduction = changes.poll();
            Point location = reduction.point;
            Reflect(fieldView[location.y][location.x], reduction.type);
        }
    }

    private void Reflect(View rectangle, PointType type)
    {
        switch (type) {
            case emptiness:
                rectangle.setBackgroundResource(R.color.emptiness);
                break;
            case border:
                rectangle.setBackgroundResource(R.color.border);
                break;
            case snakeBody:
                rectangle.setBackgroundResource(R.color.snakeBody);
                break;
            case snakeHead:
                rectangle.setBackgroundResource(R.color.snakeHead);
                break;
            case food:
                rectangle.setBackgroundResource(R.color.food);
                break;
        }
    }

    @Override
    public void ShowScore(int score, int timeToDeleteTail, LevelDifficulty level) {
        // Toast.makeText(context, String.format("баллы %d ", score), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ShowGameOver() {

    }

    @Override
    public void ShowMessage(String message) {

    }
}
