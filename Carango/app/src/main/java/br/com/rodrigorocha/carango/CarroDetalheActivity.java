package br.com.rodrigorocha.carango;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import br.com.rodrigorocha.carango.model.Carro;

public class CarroDetalheActivity extends AppCompatActivity {

    private ImageView imagem ;
    private TextView desc ;
    private CollapsingToolbarLayout collapsingToolbar ;
    private Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState) ;
        setContentView(R.layout. activity_carro_detalhe) ;
        toolbar = (Toolbar) findViewById(R.id.toolbar) ;
        setSupportActionBar( toolbar ) ;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true) ;
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar) ;
        imagem = (ImageView)findViewById(R.id.imagem) ;
        desc = (TextView)findViewById(R.id.desc) ;
        if (getIntent() != null ) {
            try {
                Carro carro = (Carro)getIntent().getSerializableExtra( "carro" ) ;
                InputStream ims = getAssets().open(carro.getFoto()) ;
                Drawable d = Drawable.createFromStream(ims , null ) ;
                imagem .setImageDrawable(d) ;
                collapsingToolbar .setTitle(carro.getNome()) ;
                desc .setText(carro.getDesc()) ;
            } catch (IOException ioe) {
                ioe.printStackTrace() ;
            }
        }
    }

}
