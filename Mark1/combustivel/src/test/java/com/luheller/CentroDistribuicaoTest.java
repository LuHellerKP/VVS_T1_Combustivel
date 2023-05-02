package com.luheller;

import com.luheller.CentroDistribuicao;
import com.luheller.CentroDistribuicao.SITUACAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CentroDistribuicaoTest {
    private CentroDistribuicao centroDistribuicaoLuiza;

    //Testes de construtor
    /*
    Os testes abaixo foram construidos usando o método assertThrows da classe Assertions,
    que verifica se, quando um determinado método é chamado, uma exceção é lançada.
     */
    @Test
    void valorInvalidoDeParametroNoConstrutor_Aditivo(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CentroDistribuicao(-50, 10000, 1250, 1250);
        });
    }
    @Test
    void valorInvalidoDeParametroNoConstrutor_Gasolina(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CentroDistribuicao(500, -50, 1250, 1250);
        });
    }
    @Test
    void valorInvalidoDeParametroNoConstrutor_Alcool1 (){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CentroDistribuicao(500, 10000, -50, 1250);
        });
    }
    @Test
    void valorInvalidoDeParametroNoConstrutor_Alcool2 (){
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CentroDistribuicao(500, 10000, 1250, -50);
        });
    }
    @Test
    void valorValidoDeParametroNoConstrutor (){
        Assertions.assertDoesNotThrow(() -> {
            new CentroDistribuicao(500, 10000, 1250, 1250);
        });
    }

    //Testes do método DefineSituacao
    @Test
    void qtdDeComponenteNoTanque(){
        CentroDistribuicao c1 = new CentroDistribuicao(500, 10000, 1250, 1250);
        SITUACAO s1 = SITUACAO.NORMAL;
        assertEquals(s1,((c1.porcentAditivo > 50)&&(c1.porcentGasolina > 50)&&(c1.porcentAlcool1 > 50)&&(c1.porcentAlcool2 > 50)
        ));
    }


    /*
    @BeforeEach
    void setUp() {
        centroDistribuicaoLuiza = new CentroDistribuicaoLuiza(500, 10000, 1250, 1250);
    }
    @Test
    void recebeAditivo() {
        assertEquals(0, centroDistribuicaoLuiza.recebeAditivo(50));
    }

    @Test
    void recebeGasolina() {
        assertEquals(0, centroDistribuicaoLuiza.recebeGasolina(50));
    }

    @Test
    void recebeAlcool() {
        assertEquals(0, centroDistribuicaoLuiza.recebeAlcool(50));
    }

    @Test
    void recebeAditivoNegativo() {
        Assertions.assertNotEquals(0, centroDistribuicaoLuiza.recebeAditivo(-50));
    }

    @Test
    void recebeGasolinaNegativo() {
        Assertions.assertNotEquals(0, centroDistribuicaoLuiza.recebeGasolina(-50));
    }
    @Test
    void recebeAlcoolNegativo() {
        Assertions.assertNotEquals(0, centroDistribuicaoLuiza.recebeAlcool(-50));
    }

    @Test
    void recebeGasolinaAcimaDoLimiteDoTanque(){
        Assertions.assertEquals(0, centroDistribuicaoLuiza.recebeGasolina(10000));
    }

    @Test
    void recebeGasolinaAbaixoDeCinquentaPorcento(){

    }*/
}