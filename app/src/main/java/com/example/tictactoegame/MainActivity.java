package com.example.tictactoegame;

import static com.example.tictactoegame.R.drawable.*;

import android.media.Image;
import android.net.http.UrlRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
   // player representation
   //    0 - x image
   //    1 - o image
    boolean gameactive=true;
    int activeplayer = 0;
    int gamestate[]={2,2,2,2,2,2,2,2,2};
    //gamestate
    //0-x image
    //1-o image
    //2-null
    int[][] winpositions={ {0,1,2},{3,4,5},{6,7,8},
                          {0,3,6},{1,4,7},{2,5,8},
                          {0,4,8},{2,4,6}

    };
public void ontap(View view) {
    ImageView img = (ImageView) view;
    int tappedImage = Integer.parseInt(img.getTag().toString());
    if (!gameactive) {
        gameReset(view);
    }
    if (gamestate[tappedImage] == 2) {
        gamestate[tappedImage] = activeplayer;
        img.setTranslationX(-1000f);

        if (activeplayer == 0) {
            img.setImageResource(R.drawable.ximage);
            activeplayer = 1;
            TextView Status = findViewById(R.id.status);
            Status.setText("o'turn tap to play");
        } else {
            img.setImageResource(R.drawable.oimage);
            activeplayer = 0;
            TextView Status = findViewById(R.id.status);
            Status.setText("x'turn tap to play");

        }
        img.animate().translationXBy(1000f).setDuration(300);
    }

    for (int[] winposition : winpositions) {
        if (gamestate[winposition[0]] == gamestate[winposition[1]] &&
                (gamestate[winposition[1]] == gamestate[winposition[2]]) &&
                gamestate[winposition[0]] != 2) {
            //to find who is won
            String printstatestr;

            if (gamestate[winposition[0]] == 0) {
                printstatestr = "x has won";
            } else {
                printstatestr = "o has won";
            }
            TextView Status = findViewById(R.id.status);
            Status.setText(printstatestr);
            gameactive=false;
        }
    }
            //game draw check
    boolean ifdraw=true;
            for(int i=0;i<gamestate.length;i++)
            {
                if(gamestate[i]==2){
                    ifdraw=false;
                    break;
                }

            }
if(ifdraw)
{
    TextView Status = findViewById(R.id.status);
    Status.setText("match draw");
    gameactive=false;
}
}





    public void gameReset (View view){
        gameactive = true;
        activeplayer = 0;
        for (int i=0; i < gamestate.length; i++) {
            gamestate[i] = 2;
        }
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        TextView Status = findViewById(R.id.status);
        Status.setText("x's turn tap to play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}