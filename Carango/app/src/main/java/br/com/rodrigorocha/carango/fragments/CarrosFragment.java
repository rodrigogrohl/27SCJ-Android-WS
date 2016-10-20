package br.com.rodrigorocha.carango.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.rodrigorocha.carango.CarroDetalheActivity;
import br.com.rodrigorocha.carango.R;
import br.com.rodrigorocha.carango.adapter.CarroListAdapter;
import br.com.rodrigorocha.carango.api.CarroAPI;
import br.com.rodrigorocha.carango.model.Carro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarrosFragment extends Fragment implements Callback<List<Carro>> {

    protected RecyclerView recyclerView ;
    private List<Carro> carros ;
    private LinearLayoutManager mLayoutManager ;
    private String tipo ;
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super .onCreate(savedInstanceState) ;
        if (getArguments() != null ) {
            this . tipo = getArguments().getString( "tipo" ) ;
        }
    }
    @Override
    public View onCreateView (LayoutInflater inflater , ViewGroup container , Bundle
            savedInstanceState) {
        View view = inflater.inflate(R .layout. fragment_carros, container , false ) ;
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView) ;
        mLayoutManager = new LinearLayoutManager(getActivity()) ;
        recyclerView .setLayoutManager( mLayoutManager ) ;
        recyclerView .setItemAnimator( new DefaultItemAnimator()) ;
        recyclerView .setHasFixedSize( true ) ;
        return view ;
    }
    @Override
    public void onActivityCreated ( @Nullable Bundle savedInstanceState) {
        super .onActivityCreated(savedInstanceState) ;
        loadCarros() ;
    }
    private void loadCarros () {
        carros = new ArrayList<>() ;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "http://www.heiderlopes.com.br" )
                .addConverterFactory(GsonConverterFactory.create())
                .build() ;
        // prepara a chamada no Retrofit 2.0
        CarroAPI carroAPI = retrofit.create(CarroAPI. class ) ;
        Call<List<Carro>> call = carroAPI.findBy( tipo ) ;
        //chamada assincrona
        call.enqueue( this ) ;
    }
    //Listener utillizado ao clicar na linha do recyclerview
    private CarroListAdapter.CarroOnClickListener onClickCarro () {
        return new CarroListAdapter.CarroOnClickListener() {
            @Override
            public void onClickCarro (View view , int idx) {
                Carro c = carros .get(idx) ;
                Intent intent = new Intent(getContext() , CarroDetalheActivity. class ) ;
                intent.putExtra("carro", c) ;
                startActivity(intent);
            }
        } ;
    }
    //Chamada caso de de sucesso na requisicao
    @Override
    public void onResponse (Call<List<Carro>> call , Response<List<Carro>> response) {
        recyclerView .setAdapter(new CarroListAdapter(getContext(), response.body(),
                onClickCarro())) ;
    }
    //Chamada caso de erro na requisicao
    @Override
    public void onFailure (Call<List<Carro>> call , Throwable t) {
        Snackbar.make(recyclerView, t.getMessage(), Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();

    }

}
