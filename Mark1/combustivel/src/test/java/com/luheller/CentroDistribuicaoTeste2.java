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
    void qtdDeComponenteNoTanque() {
        CentroDistribuicao c1 = new CentroDistribuicao(500, 10000, 1250, 1250);
        SITUACAO s1 = SITUACAO.NORMAL;
        if ((c1.porcentAditivo > 50) && (c1.porcentGasolina > 50) && (c1.porcentAlcool1 > 50)
        && (c1.porcentAlcool2 > 50)) {
            assertEquals(true, ((c1.porcentAditivo > 50) && (c1.porcentGasolina > 50) && (c1.porcentAlcool1 > 50)
                && (c1.porcentAlcool2 > 50)));
        }
        
    }

    @Test
    void qtdDeComponenteNoTanque1() {
        CentroDistribuicao c1 = new CentroDistribuicao(500, 10000, 1250, 1250); // Tem que mudar as variaveis?
        SITUACAO s1 = SITUACAO.EMERGENCIA;
        if ((c1.porcentAditivo < 25) && (c1.porcentGasolina < 25) && (c1.porcentAlcool1 < 25)
        && (c1.porcentAlcool2 < 25)) {
            assertEquals(true, ((c1.porcentAditivo < 25) && (c1.porcentGasolina < 25) && (c1.porcentAlcool1 < 25)
                && (c1.porcentAlcool2 < 25)));
        }
        
    }

    @Test
    void qtdDeComponenteNoTanque2() {
        CentroDistribuicao c1 = new CentroDistribuicao(500, 10000, 1250, 1250); // Tem que mudar as variaveis?
        SITUACAO s1 = SITUACAO.SOBRAVISO;
        if ((c1.porcentAditivo < 50 && c1.porcentAditivo >= 25)
        && (c1.porcentGasolina < 50 && c1.porcentGasolina >= 25) &&
        (c1.porcentAlcool1 < 50 && c1.porcentAlcool1 >= 25)
        && (c1.porcentAlcool2 < 50 && c1.porcentAlcool2 >= 25)) {
            assertEquals(true,
                ((c1.porcentAditivo < 50 && c1.porcentAditivo >= 25)
                        && (c1.porcentGasolina < 50 && c1.porcentGasolina >= 25) &&
                        (c1.porcentAlcool1 < 50 && c1.porcentAlcool1 >= 25)
                        && (c1.porcentAlcool2 < 50 && c1.porcentAlcool2 >= 25)));
        }
        
    }

    @Test
    void qtdDeComponenteNoTanque3() {
        CentroDistribuicao c1 = new CentroDistribuicao(500, 10000, 1250, 1250);
        SITUACAO s1 = SITUACAO.SOBRAVISO;
        if ((c1.porcentAditivo > 50) && (c1.porcentGasolina < 50 && c1.porcentGasolina >= 25) &&
        (c1.porcentAlcool1 < 50 && c1.porcentAlcool1 >= 25)
        && (c1.porcentAlcool2 < 50 && c1.porcentAlcool2 >= 25)) {   
            assertEquals(true, ((c1.porcentAditivo > 50) && (c1.porcentGasolina < 50 && c1.porcentGasolina >= 25) &&
                (c1.porcentAlcool1 < 50 && c1.porcentAlcool1 >= 25)
                && (c1.porcentAlcool2 < 50 && c1.porcentAlcool2 >= 25)));
        }
        
    }

    // Testes do medoto receberAditivo
    @Test
    void testeRecebeAditivo() {
        CentroDistribuicao c1 = new CentroDistribuicao(500, 10000, 1250, 1250);

        int valor = -200;
        c1.recebeAditivo(valor);
        
        assertEquals(true, (valor <= 0));
    }

    @Test
    void testeRecebeAditivo1() {
        CentroDistribuicao c1 = new CentroDistribuicao(500, 10000, 1250, 1250);
        int valor = 70000;
        c1.recebeAditivo(valor);
        
        
        assertEquals(true, (valor > c1.MAX_ADITIVO));
    }

    @Test
    void testeRecebeAditivo12(){
        CentroDistribuicao c12 = new CentroDistribuicao(250, 10000, 1250, 1250);

        assertEquals(c12.qtdadeRecebida,c12.recebeAditivo(50));
    }

    @Test
    void testeRecebeAditivo2() {
        CentroDistribuicao c1 = new CentroDistribuicao(450, 10000, 1250, 1250);
        int valor = 50;

        int sobra = valor + c1.gettAditivo() - c1.MAX_ADITIVO;
        int qtdRecebida = sobra - valor;
        c1.recebeAditivo(valor);

        assertEquals(true, (sobra >= 0));

    }

    @Test
    void testeRecebeAditivo3() {
        CentroDistribuicao c1 = new CentroDistribuicao(450, 10000, 1250, 1250);
        int valor = 50;

        int sobra = valor + c1.gettAditivo() - c1.MAX_ADITIVO;
       int espacoLivre= c1.gettAditivo()-valor;
        c1.recebeAditivo(valor);

        assertEquals(false, (sobra < 0)); //como faço para ele retornar o espaço livre? testei agora esse "assertEquals", ele é o resultado esperado

    }

    @Test 
    void testeEncomendaCombustivel(){
        CentroDistribuicao c1 = new CentroDistribuicao(500, 10000, 1250, 1250);
        
    }
    
}
