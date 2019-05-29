/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.utfpr.pg.es.entities;

/**
 *
 * @author Vinicius
 */
public class Locacao {

    private Cliente cliente;
    private Filme filme;
    private String dataLocacao;
    private String dataPrevistaDevolucao;
    private String dataDevolucao;
    private Double valorLocacao;

    public Locacao(Cliente cliente, Filme filme, String dataLocacao, String dataPrevistaDevolucao, Double valorLocacao) {
        
        this.cliente = cliente;
        this.filme = filme;
        this.dataLocacao = dataLocacao;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.valorLocacao = valorLocacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public String getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(String dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public String getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public void setDataPrevistaDevolucao(String dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Double getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(Double valorLocacao) {
        this.valorLocacao = valorLocacao;
    }
}
