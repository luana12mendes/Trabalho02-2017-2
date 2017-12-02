package luana.trabalho02_2017_2.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import luana.trabalho02_2017_2.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button eventoBT = (Button) findViewById(R.id.bt_eventos);

        eventoBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EventoActivity.class);
                startActivity(intent);

            }
        });


    }
    private Context getContext(){
        return this;
    }

}
