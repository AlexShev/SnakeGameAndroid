package com.example.snakegame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.snakegame.Controllers.GUIcontroller;
import com.example.snakegame.Game.Game;

public class MainActivity extends AppCompatActivity {

    View[][] field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        int height = 40;
        int width = 40;

        initField(height, width);

        GUIcontroller controller = new GUIcontroller(field, this);

        Game game = new Game(controller, height, width);

        new Thread() {
            @Override
            public void run() {
                game.Start();
            }
        }.start();
    }

    private void initField(int height, int width) {
        field = new View[height][width];

        TableLayout stk = findViewById(R.id.field);
        stk.setWeightSum(height);

        TableLayout.LayoutParams param = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                1.0f
        );

        TableRow.LayoutParams rowparam = new TableRow.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                1.0f
        );

        for (int i = 0; i < height; i++) {
            TableRow tbrow = new TableRow(this);
            tbrow.setWeightSum(width);
            tbrow.setLayoutParams(param);

            for (int j = 0; j < width; j++) {
                View rect = new View(this);
                field[i][j] = rect;
                rect.setLayoutParams(rowparam);
                rect.setBackgroundColor(Color.rgb(100, 0, 0));
                tbrow.addView(rect);
            }
            stk.addView(tbrow);
        }
        stk.setWeightSum(height);


//        TextView tv0 = new TextView(this);
//        tv0.setText(" Sl.No ");
//        tv0.setTextColor(Color.WHITE);
//        tbrow0.addView(tv0);
//        TextView tv1 = new TextView(this);
//        tv1.setText(" Product ");
//        tv1.setTextColor(Color.WHITE);
//        tbrow0.addView(tv1);
//        TextView tv2 = new TextView(this);
//        tv2.setText(" Unit Price ");
//        tv2.setTextColor(Color.WHITE);
//        tbrow0.addView(tv2);
//        TextView tv3 = new TextView(this);
//        tv3.setText(" Stock Remaining ");
//        tv3.setTextColor(Color.WHITE);
//        tbrow0.addView(tv3);
//        stk.addView(tbrow0);
//        for (int i = 0; i < height; i++) {
//            TableRow tbrow = new TableRow(this);
//            TextView t1v = new TextView(this);
//            t1v.setText("" + i);
//            t1v.setTextColor(Color.WHITE);
//            t1v.setGravity(Gravity.CENTER);
//            tbrow.addView(t1v);
//            TextView t2v = new TextView(this);
//            t2v.setText("Product " + i);
//            t2v.setTextColor(Color.WHITE);
//            t2v.setGravity(Gravity.CENTER);
//            tbrow.addView(t2v);
//            TextView t3v = new TextView(this);
//            t3v.setText("Rs." + i);
//            t3v.setTextColor(Color.WHITE);
//            t3v.setGravity(Gravity.CENTER);
//            tbrow.addView(t3v);
//            TextView t4v = new TextView(this);
//            t4v.setText("" + i * 15 / 32 * 10);
//            t4v.setTextColor(Color.WHITE);
//            t4v.setGravity(Gravity.CENTER);
//            tbrow.addView(t4v);
//            stk.addView(tbrow);
    }
}