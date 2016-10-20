package br.com.rodrigorocha.carango.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.rodrigorocha.carango.R;
import br.com.rodrigorocha.carango.model.Carro;

/**
 * Created by rm48236 on 19/10/2016.
 */
public class CarroListAdapter extends RecyclerView.Adapter<CarroListAdapter.CarrosViewHolder> {

    private final List<Carro> carros;
    private final Context context;
    private CarroOnClickListener carroOnClickListener ;
    public CarroListAdapter (Context context, List<Carro> carros , CarroOnClickListener
            carroOnClickListener) {
        this.context = context ;
        this.carros = carros ;
        this.carroOnClickListener = carroOnClickListener ;
    }
    @Override
    public int getItemCount () {
        return this . carros != null ? this . carros .size() : 0 ;
    }
    @Override
    public CarrosViewHolder onCreateViewHolder (ViewGroup viewGroup , int viewType) {
// Infla a view do layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_carro, viewGroup, false) ;
        return new CarrosViewHolder(view) ;
    }
    @Override
    public void onBindViewHolder( final CarrosViewHolder holder, final int position) {
        Carro c = carros.get(position) ;
        holder.tNome.setText(c.getNome()) ;
        holder.desc.setText(c.getDesc()) ;
        Picasso.with(context)
                .load(c.getFoto())
                .placeholder(R.mipmap. ic_launcher)
                .error(R.mipmap. ic_launcher)
                .into(holder. img ) ;
        holder. progress .setVisibility(View.VISIBLE) ;
        if ( carroOnClickListener != null ) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    carroOnClickListener.onClickCarro(holder.itemView, position);
                    // A variável position é final
                }
            }) ;
        }
        holder. progress .setVisibility(View.GONE) ;
    }
    //Acao a ser executada ao clicar em um item da lista - Devera ser implementada na  Activity ou Fragment
    public interface CarroOnClickListener {
        public void onClickCarro (View view , int idx) ;
    }
    //Acao a ser executada ao clicar no botao options - Devera ser implementada na Activity ou Fragment
    public interface OptionsOnClickListener {
        public void onClickOptions (View view , int idx) ;
    }
    // ViewHolder com as views
    public static class CarrosViewHolder extends RecyclerView.ViewHolder {
        public TextView tNome ;
        ImageView img ;
        ProgressBar progress ;
        TextView desc ;
        public CarrosViewHolder (View view) {
            super(view) ;
            // Cria as views para salvar no ViewHolder
            tNome = (TextView) view.findViewById(R.id.text) ;
            img = (ImageView) view.findViewById(R.id.img) ;
            progress = (ProgressBar) view.findViewById(R.id.progressImg) ;
            desc = (TextView) view.findViewById(R.id.desc) ;
        }
    }
}
