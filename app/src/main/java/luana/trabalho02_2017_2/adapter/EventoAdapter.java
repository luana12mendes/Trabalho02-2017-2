package luana.trabalho02_2017_2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import luana.trabalho02_2017_2.model.Evento;
import luana.trabalho02_2017_2.R;



public class EventoAdapter extends RecyclerView.Adapter {

    private List<Evento> eventos;
    private Context context;
    private static ClickRecyclerViewListener clickRecyclerViewListener;

    public EventoAdapter(List<Evento> eventos, Context context, ClickRecyclerViewListener clickRecyclerViewListener) {

        this.eventos = eventos;
        this.context = context;
        this.clickRecyclerViewListener = clickRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_evento, parent, false);
        EventoViewHolder eventoViewHolder = new EventoViewHolder(view);
        return eventoViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
                                 int position) {

        EventoViewHolder holder = (EventoViewHolder) viewHolder;

        Evento evento  = eventos.get(position) ;

        holder.nomeEvento.setText(evento.getNome());
        holder.dataEvento.setText(evento.getData());
        holder.local.setText(evento.getLocal());



    }

    @Override
    public int getItemCount() {

        return eventos.size();
    }

    private void updateItem(int position) {

    }

    // Método responsável por remover um usuário da lista.
    private void removerItem(int position) {

    }

    public class EventoViewHolder extends RecyclerView.ViewHolder {

        private final TextView nomeEvento;
        private final TextView dataEvento;
        private final TextView local;


        public EventoViewHolder(View itemView) {
            super(itemView);
            nomeEvento = (TextView) itemView.findViewById(R.id.nomeEvento);
            dataEvento = (TextView) itemView.findViewById(R.id.dataEvento);
            local = (TextView) itemView.findViewById(R.id.local);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickRecyclerViewListener.onClick(eventos.get(getLayoutPosition()));

                }
            });


        }
    }
}