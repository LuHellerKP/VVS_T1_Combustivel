package com.luheller;

public class CentroDistribuicaoLuiza {
    private int tAditivo;
    private int tGasolina;
    private int tAlcool1;
    private int tAlcool2;
    private SITUACAO situacao;

    public enum SITUACAO {
        NORMAL, SOBRAVISO, EMERGENCIA
    }

    public enum TIPOPOSTO {
        COMUM, ESTRATEGICO
    }

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;

    public CentroDistribuicaoLuiza(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        this.tAditivo = tAditivo;
        this.tGasolina = tGasolina;
        this.tAlcool1 = tAlcool1;
        this.tAlcool2 = tAlcool2;
    }

    public void defineSituacao() {
        if ((tAditivo > (MAX_ADITIVO * 50) / 100) && (tAlcool1 > (MAX_ALCOOL * 50) / 100)
                && (tAlcool2 > (MAX_ALCOOL * 50) / 100) && (tGasolina > (MAX_GASOLINA * 50) / 100)) {
            situacao = SITUACAO.NORMAL;
        } else if (((tAditivo < (MAX_ADITIVO * 50) / 100) && (tAditivo > (MAX_ADITIVO * 25) / 100))
                && ((tAlcool1 < (MAX_ALCOOL * 50) / 100) && (tAlcool1 > (MAX_ALCOOL * 25) / 100))
                && ((tAlcool2 < (MAX_ALCOOL * 50) / 100) && (tAlcool2 > (MAX_ALCOOL * 25) / 100))
                && ((tGasolina < (MAX_GASOLINA * 50) / 100) && (tGasolina > (MAX_GASOLINA * 25) / 100))) {
            situacao = SITUACAO.SOBRAVISO;

        } else if (((tAditivo < (MAX_ADITIVO * 25) / 100) && (tAlcool1 < (MAX_ALCOOL * 25) / 100)
                && (tAlcool2 < (MAX_ALCOOL * 25) / 100) && (tGasolina < (MAX_GASOLINA * 25) / 100))) {
            situacao = SITUACAO.EMERGENCIA;

        }
    }

    public SITUACAO getSituacao() {
        defineSituacao();
        return situacao;
    }

    public int gettGasolina() {
        return tGasolina;
    }

    public int gettAditivo() {
        return tAditivo;
    }

    public int gettAlcool1() {
        return tAlcool1;
    }

    public int gettAlcool2() {
        return tAlcool2;
    }

    public int recebeAditivo(int qtdadeRecebida) {
        if (qtdadeRecebida <= 0 || (qtdadeRecebida) > MAX_ADITIVO) {
            return -1; // erro; quantidade invalida
        }
        int espacoLivre = MAX_ADITIVO - this.tAditivo;
        int sobra = espacoLivre - qtdadeRecebida;// sobra é o espaço livre menos o que foi recebido, ou seja, mostra
                                                 // se cabe ou não cabe o que foi recebido
        if (sobra >= 0) {// se sobra for maior ou igual a zero, significa que tudo que foi recebido cabe
                         // no tanque
            tAditivo = tAditivo + qtdadeRecebida;
            return qtdadeRecebida; // retorna o que foi recebido (pq cabe tudo no tanque)
        } else { // se sobra for menor que zero, significa que o que foi recebido não cabe no
                 // tanque
            tAditivo = tAditivo + espacoLivre;
            return espacoLivre; // retorna o que cabe no tanque
        }

    }

    public int recebeGasolina(int qtdadeRecebida) {
        if (qtdadeRecebida <= 0 || (qtdadeRecebida) > MAX_GASOLINA) {
            return -1; // erro; quantidade invalida
        }
        int espacoLivre = MAX_GASOLINA - this.tGasolina;
        int sobra = espacoLivre - qtdadeRecebida;
        if (sobra >= 0) {
            tGasolina = tGasolina + qtdadeRecebida;
            return qtdadeRecebida; // novo valor do tanque
        } else {
            tGasolina = tGasolina + espacoLivre;
            return espacoLivre;
        }

    }

    public int recebeAlcool(int qtdadeRecebida) {
        if (qtdadeRecebida <= 0 || (qtdadeRecebida) > MAX_ALCOOL) {
            return -1; // erro; quantidade invalida
        }
        int armazenatAlcool1 = 0;
        qtdadeRecebida = qtdadeRecebida / 2;
        int armazenatAlcool2 = 0;
        // if (tAlcool1 == tAlcool2) {
        // armazenatAlcool1 = qtdadeRecebida / 2;
        // int armazenatAlccol2 = qtdadeRecebida / 2;
        // }

        int espacoLivre = MAX_ALCOOL / 2 - this.tAlcool1;
        int sobra = espacoLivre - qtdadeRecebida;
        if (sobra >= 0) {// tudo recebido cabe no tanque
            armazenatAlcool1 = qtdadeRecebida;
            armazenatAlcool2 = qtdadeRecebida;
            tAlcool1 = tAlcool1 + armazenatAlcool1;
            tAlcool2 = tAlcool2 + armazenatAlcool2;
            return qtdadeRecebida; // retorna o que foi colocado em cada tanque (eles são iguais)
        } else {
            tAlcool1 = tAlcool1 + espacoLivre;
            tAlcool2 = tAlcool2 + espacoLivre;
            return espacoLivre;
        }

    }

    public int[] encomendaCombustivel(int qtdadeRecebida, TIPOPOSTO tipoPosto) {
        int[] resultado = new int[4];
        // verificar se a quantidade recebida é válida
        if (qtdadeRecebida <= 0) {// se a quantidade recebida for menor ou igual a zero, ou seja, inválida
            resultado[0] = -7;
            return resultado;
        }
        // verificar se o tipo do posto é válido
        if (!tipoPosto.equals(TIPOPOSTO.COMUM) && !tipoPosto.equals(TIPOPOSTO.ESTRATEGICO)) { // se o tipo do posto não for comum nem estratégico
            resultado[0] = -7; // erro
            return resultado; // retorna o erro
        }
        // verificar se o posto tem combustível suficiente para atender a encomenda
        int totalAlcool = tAlcool1 + tAlcool2;
        if (qtdadeRecebida > (tGasolina + tAditivo + totalAlcool)) { // se a quantidade recebida for maior que a soma de todos os combustíveis
            resultado[0] = -7;
            return resultado;
        }

        return new int[] { qtdadeRecebida, qtdadeRecebida, qtdadeRecebida, qtdadeRecebida };
    }
}