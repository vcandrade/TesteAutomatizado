/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.utfpr.pg.es.exceptions;

/**
 *
 * @author Vinicius
 */
public class FilmeIndisponivelException extends Exception {

    public FilmeIndisponivelException() {
    
        super("O filme encontra-se indisponível.");
    }
}
