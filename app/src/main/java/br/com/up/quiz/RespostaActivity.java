package br.com.up.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RespostaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resposta);
        getSupportActionBar().hide();

        final MediaPlayer soundEfect = MediaPlayer.create(this, R.raw.applause);
        final MediaPlayer erouu = MediaPlayer.create(this, R.raw.errou);
        ImageView imgResposta = (ImageView)findViewById(R.id.imgResposta);
        TextView resposta = (TextView)findViewById(R.id.resposta);
        Button btnJogarNovamente = (Button)findViewById(R.id.btnJogarNovamente);

        Intent intent = getIntent();
        int pontos = intent.getIntExtra("pontos", 0);

        if(intent.hasExtra("acertou")) {
            btnJogarNovamente.setVisibility(View.INVISIBLE);
            boolean acertou = intent.getBooleanExtra("acertou", false);
            if (acertou) {
                soundEfect.start();
                imgResposta.setImageResource(R.drawable.acertou);
                resposta.setText("Acertou! Pontos: " + pontos);

            } else {
                erouu.start();
                imgResposta.setImageResource(R.drawable.errou);
                resposta.setText("Errou! Pontos: " + pontos);

            }

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            thread.start();
        }
        else{
            btnJogarNovamente.setVisibility(View.VISIBLE);
            resposta.setText("Fez " + pontos + " pontos!");

            if(pontos >= 3)
                imgResposta.setImageResource(R.drawable.acertou);
            else
                imgResposta.setImageResource(R.drawable.errou);
        }
    }

    public void btnJogarNovamenteOnClick(View v){
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
        finish();
    }
}