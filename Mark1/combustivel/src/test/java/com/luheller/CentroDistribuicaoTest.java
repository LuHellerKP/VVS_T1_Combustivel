package com.luheller;

import com.luheller.CentroDistribuicaoLuiza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CentroDistribuicaoTest {
    private CentroDistribuicaoLuiza centroDistribuicaoLuiza;

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
}