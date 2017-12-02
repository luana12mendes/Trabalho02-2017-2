package luana.trabalho02_2017_2.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import luana.trabalho02_2017_2.R;
import luana.trabalho02_2017_2.adapter.ClickRecyclerViewListener;
import luana.trabalho02_2017_2.adapter.EventoAdapter;
import luana.trabalho02_2017_2.model.Evento;

import io.realm.Realm;

public class EventoActivity extends AppCompatActivity implements ClickRecyclerViewListener {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        realm = Realm.getDefaultInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventoActivity.this,EventoDetalheActivity.class);
                intent.putExtra("id",0);
                startActivity(intent);
            }
        });
    }

    private List<Evento> getEventos(){

        return (List)realm.where(Evento.class).findAll();

    }

    @Override
    public void onClick(Object object) {
        Evento evento = (Evento) object;
        Intent intent = new Intent(EventoActivity.this,EventoDetalheActivity.class);
        intent.putExtra("id", evento.getId());
        startActivity(intent);

    }

    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_Livro);

        recyclerView.setAdapter(new EventoAdapter(getEventos(),this,this));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);


    }

    @Override
    public void finish(){
        realm.close();

    }



}
