package com.luheller;

import com.luheller.CentroDistribuicao;
import com.luheller.CentroDistribuicao.SITUACAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CentroDistribuicaoTeste2 {
    private CentroDistribuicao centroDistribuicaoLuiza;

    // Testes de construtor
    /*
     * Os testes abaixo foram construidos usando o método assertThrows da classe
     * Assertions,
     * que verifica se, quando um determinado método é chamado, uma exceção é
     * lançada.
     */
    @Test
    void valorInvalidoDeParametroNoConstrutor_Aditivo() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CentroDistribuicao(-50, 10000, 1250, 1250);
        });
    }

    @Test
    void valorInvalidoDeParametroNoConstrutor_Gasolina() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CentroDistribuicao(500, -50, 1250, 1250);
        });
    }

    @Test
    void valorInvalidoDeParametroNoConstrutor_Alcool1() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CentroDistribuicao(500, 10000, -50, 1250);
        });
    }

    @Test
    void valorInvalidoDeParametroNoConstrutor_Alcool2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CentroDistribuicao(500, 10000, 1250, -50);
        });
    }

    @Test
    void valorValidoDeParametroNoConstrutor() {
        Assertions.assertDoesNotThrow(() -> {
            new CentroDistribuicao(500, 10000, 1250, 1250);
        });
    }

    // Testes do método DefineSituacao
    @Test
    void testeRecebeAditivo() {
        CentroDistribuicao c1 = new CentroDistribuicao(500, 10000, 1250, 1250);
        
        assertEquals(-1,c1.recebeAditivo(-200));
    }

    @Test
    void recebe50AditivoComQtdInicialDe250(){
        CentroDistribuicao c2 = new CentroDistribuicao(250, 10000, 1250, 1250);

        assertEquals(50,c2.recebeAditivo(50));
    }
    
}
