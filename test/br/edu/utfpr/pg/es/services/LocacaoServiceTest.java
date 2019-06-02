package br.edu.utfpr.pg.es.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.edu.utfpr.pg.es.entities.Cliente;
import br.edu.utfpr.pg.es.entities.Filme;
import br.edu.utfpr.pg.es.entities.Locacao;
import br.edu.utfpr.pg.es.exceptions.FilmeIndisponivelException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vinicius
 */
public class LocacaoServiceTest {

    private Cliente cliente;
    private Filme filme;
    private LocacaoService locacaoService;

    @Before
    public void setUp() {

        // ==================== MONTAGEM DO CENÁRIO ==================== //
        this.cliente = new Cliente(123, "João da Silva");
        this.filme = new Filme(1, "Vingadores", "Ação", 5.00);

        this.locacaoService = new LocacaoService();
    }

    @After
    public void tearDown() {

        this.cliente = null;
        this.filme = null;
        this.locacaoService = null;
    }

    @Test
    public void alugarFilmeTest() {

        // ========================= EXECUÇÃO ========================= //
        Locacao locacao = null;

        try {

            locacao = this.locacaoService.alugarFilme(this.cliente, this.filme);

        } catch (FilmeIndisponivelException ex) {

            fail(); // Se a FilmeIndisponivelException for disparada, o teste falhou.
        }

        // ======================= VERIFICAÇÃO ======================== //
        assertFalse(locacao.getFilme().isDisponivel());
    }

    @Test
    public void alugarFilmeIndisponivelTest() {

        // ========================= EXECUÇÃO ========================= //
        Locacao locacao1 = null;
        Locacao locacao2 = null;

        try {

            locacao1 = this.locacaoService.alugarFilme(this.cliente, this.filme);
            locacao2 = this.locacaoService.alugarFilme(this.cliente, this.filme);

            fail(); // Se o bloco try executou sem disparar exceções, o teste falhou

        // ======================= VERIFICAÇÃO ======================== //
        } catch (FilmeIndisponivelException fie) {
            
            assertEquals("O filme encontra-se indisponível.", fie.getMessage());
        }
    }

    @Test
    public void verificarDiasAtrasoTest() {

        // ==================== MONTAGEM DO CENÁRIO ==================== //
        Locacao locacao = new Locacao(this.cliente, this.filme, "10/10/2010", "13/10/2010", this.filme.getPreco());
        locacao.setDataDevolucao("15/10/2010");

        long diasAtraso = 0;

        // ========================= EXECUÇÃO ========================= //
        try {

            diasAtraso = this.locacaoService.verificarDiasAtraso(locacao);

        } catch (ParseException ex) {

            fail(); // Se a ParseException for disparada, o teste falhou.
        }

        // ======================= VERIFICAÇÃO ======================== //
        assertEquals(2, diasAtraso);
    }

    @Test
    public void calcularDataDevolucaoTest() {

        // ==================== MONTAGEM DO CENÁRIO ==================== //
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dataEmprestimo = new Date("2010/10/10"); // 10/10/2010

        // ========================= EXECUÇÃO ========================= //
        String dataPrevistaDevolucao = df.format(this.locacaoService.calcularDataDevolucao(dataEmprestimo, 3));

        // ======================= VERIFICAÇÃO ======================== //
        assertEquals("13/10/2010", dataPrevistaDevolucao);
    }
}
