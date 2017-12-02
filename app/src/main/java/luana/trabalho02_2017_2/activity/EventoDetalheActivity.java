package luana.trabalho02_2017_2.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import luana.trabalho02_2017_2.R;
import luana.trabalho02_2017_2.model.Evento;


public class EventoDetalheActivity extends AppCompatActivity {
    EditText nome, data, local;
    Button btsalvar,btalterar, btdeletar;

    int id;
    Evento evento;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento_detalhe);

        nome = (EditText) findViewById(R.id.ed_nome_evento);
        data = (EditText) findViewById(R.id.ed_data_evento);
        local = (EditText) findViewById(R.id.ed_local_evento);

        btsalvar = (Button) findViewById(R.id.bt_salvar_livro);
        btalterar = (Button) findViewById(R.id.bt_alterar_livro);
        btdeletar = (Button) findViewById(R.id.bt_deletar_livro);

        Intent intent    = getIntent();
        id = (int) intent.getSerializableExtra("id");
        realm = Realm.getDefaultInstance();

        if (id !=0) {
            btsalvar.setEnabled(false);
            btsalvar.setClickable(false);
            btsalvar.setVisibility(View.INVISIBLE);

            evento = realm.where(Evento.class).equalTo("id",id).findFirst();

            nome.setText(evento.getNome());
            data.setText(evento.getData());
            local.setText(evento.getLocal());

        }else{
            btalterar.setEnabled(false);
            btalterar.setClickable(false);
            btalterar.setVisibility(View.INVISIBLE);
            btdeletar.setEnabled(false);
            btdeletar.setClickable(false);
            btdeletar.setVisibility(View.INVISIBLE);

        }



        btsalvar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                salvar();
            }
        });
        btalterar.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                alterar();
            }
        });
        btdeletar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                deletar();
            }
        });


    }

    public void deletar(){
        realm.beginTransaction();
        evento.deleteFromRealm();
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Evento deletado",Toast.LENGTH_LONG).show();
        this.finish();

    }

    public void salvar() {


        int proximoID = 1;
        if(realm.where(Evento.class).max("id") !=null)
            proximoID = realm.where(Evento.class).max("id").intValue()+1;

        realm.beginTransaction();
        Evento evento = new Evento();
        evento.setId(proximoID);
        evento.setNome(nome.getText().toString());
        evento.setData(data.getText().toString());
        evento.setLocal(local.getText().toString());

        realm.copyToRealm(evento);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Evento Cadastrado",Toast.LENGTH_LONG).show();
        this.finish();

    }
    public void alterar() {

        realm.beginTransaction();

        evento.setNome(nome.getText().toString());
        evento.setData(data.getText().toString());
        evento.setLocal(local.getText().toString());

        realm.copyToRealm(evento);
        realm.commitTransaction();
        realm.close();

        Toast.makeText(this,"Evento Alterado",Toast.LENGTH_LONG).show();
        this.finish();

    }
}
