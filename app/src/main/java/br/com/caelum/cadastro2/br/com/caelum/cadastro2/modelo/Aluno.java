package br.com.caelum.cadastro2.br.com.caelum.cadastro2.modelo;

import java.io.Serializable;

/**
 * Created by SIDNEY on 27/01/2016.
 */
public class Aluno implements Serializable{

    private  Long id;
    private String nome;
    private String telefone;
    private String endereco;
    private String site;
    private int nota;
    private String caminhoFoto;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }


    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }


    @Override
    public String toString() {
        return id + " - " + nome;
    }
}
