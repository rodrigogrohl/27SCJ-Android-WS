package br.com.rodrigorocha.carango.api;

import java.util.List;

import br.com.rodrigorocha.carango.model.Carro;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by rm48236 on 19/10/2016.
 */
public interface CarroAPI {
    @GET( "/carros/tipo/{tipo}" )
    Call<List<Carro>> findBy ( @Path( "tipo" ) String tags) ;
}
