package br.com.rodrigorocha.carango.model;

import java.io.Serializable;

/**
 * Created by rm48236 on 19/10/2016.
 */
public class Carro implements Serializable {
    private String nome;
    private String desc;
    private String foto;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
