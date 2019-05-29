/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.pg.es.services;

import br.edu.utfpr.pg.es.entities.Cliente;
import br.edu.utfpr.pg.es.entities.Filme;
import br.edu.utfpr.pg.es.entities.Locacao;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.util.Calendar.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vinicius
 */
public class LocacaoService {

    private final Double VALOR_MULTA_DIARIA;

    public LocacaoService() {

        this.VALOR_MULTA_DIARIA = 2.00;
    }

    public Locacao alugarFilme(Cliente cliente, Filme filme) {

        Locacao locacao = null;

        if (filme.isDisponivel()) {

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

            Date dataAtual = new Date();

            String dataLocacao = df.format(dataAtual);
            String dataPrevistaDevolucao = df.format(calcularDataDevolucao(dataAtual, 3));

            locacao = new Locacao(cliente, filme, dataLocacao, dataPrevistaDevolucao, filme.getPreco());
            
            filme.setDisponivel(false);
        }

        return locacao;
    }

    public void devolverFilme(Locacao locacao) {

        try {

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            Date dataAtual = new Date();

            locacao.setDataDevolucao(df.format(dataAtual));

            this.calcularValorLocacao(locacao);
            
            locacao.getFilme().setDisponivel(true);

        } catch (ParseException ex) {
            
            Logger.getLogger(LocacaoService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void calcularValorLocacao(Locacao locacao) throws ParseException {
        
        locacao.setValorLocacao(locacao.getFilme().getPreco() + this.calcularValorMulta(locacao));
    }
    
    public Double calcularValorMulta(Locacao locacao) throws ParseException {

        long diasAtraso = this.verificarDiasAtraso(locacao);

        if (diasAtraso > 0) {

            return diasAtraso * this.VALOR_MULTA_DIARIA;
        }

        return 0.0;
    }
    
    public Date calcularDataDevolucao(Date data, int diasLocacao) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(DAY_OF_MONTH, diasLocacao);

        return calendar.getTime();
    }

    public long verificarDiasAtraso(Locacao locacao) throws ParseException {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);

        Date dataPrevistaDevolucao = df.parse(locacao.getDataPrevistaDevolucao());
        Date dataDevolucao = df.parse(locacao.getDataDevolucao());

        long dt = (dataDevolucao.getTime() - dataPrevistaDevolucao.getTime()) + 3600000;
        return (dt / 86400000L);
    }
}
