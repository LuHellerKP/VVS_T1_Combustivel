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

    public int[] encomendaCombustivel(int qtdadeSolicitada, TIPOPOSTO tipoPosto) {
        int[] resultado = new int[4];
        int pcAditivo = (tAditivo*5)/100;
        int pcGasolina = (tGasolina*70)/100;
        int pcAlcoolTotal = ((tAlcool1+tAlcool2)*25)/100;
        int pcAlcool1 = pcAlcoolTotal/2;
        int pcAlcool2 = pcAlcoolTotal/2;

        if ((tAditivo > (MAX_ADITIVO*50)/100) && (tGasolina > (MAX_GASOLINA*50)/100) && (tAlcool1 > ((MAX_ALCOOL/2)*50)/100) && (tAlcool2 > ((MAX_ALCOOL/2)*50)/100) && tipoPosto == TIPOPOSTO.COMUM){
            SITUACAO situacao = SITUACAO.NORMAL;
            resultado[0] = tAditivo - pcAditivo; // subtração entre o que tinha no tanque, e a porcentagem que foi retirada para fazer a mistura de combustível
            resultado[1] = tGasolina - pcGasolina;
            resultado[2] = tAlcool1 - pcAlcool1;
            resultado[3] = tAlcool2 - pcAlcool2;
        }
        else if ((tAditivo > (MAX_ADITIVO*50)/100) && (tGasolina > (MAX_GASOLINA*50)/100) && (tAlcool1 > ((MAX_ALCOOL/2)*50)/100) && (tAlcool2 > ((MAX_ALCOOL/2)*50)/100) && tipoPosto == TIPOPOSTO.ESTRATEGICO){
            SITUACAO situacao = SITUACAO.NORMAL;
            resultado[0] = tAditivo - pcAditivo; // subtração entre o que tinha no tanque, e a porcentagem que foi retirada para fazer a mistura de combustível
            resultado[1] = tGasolina - pcGasolina;
            resultado[2] = tAlcool1 - pcAlcool1;
            resultado[3] = tAlcool2 - pcAlcool2;
        }
        else if ((tAditivo < (MAX_ADITIVO*50)/100) && (tAditivo > (MAX_ADITIVO*25/100)) && ((tGasolina< (MAX_GASOLINA*50)/100) && (tGasolina > (MAX_GASOLINA*25/100))) && ((tAlcool1 < ((MAX_ALCOOL/2)*50)/100) && (tAlcool1 > ((MAX_ALCOOL/2)*25/100))) && ((tAlcool2 < ((MAX_ALCOOL/2)*50)/100) && (tAlcool2 > ((MAX_ALCOOL/2)*25/100))) && tipoPosto == TIPOPOSTO.COMUM){
            SITUACAO situacao = SITUACAO.SOBRAVISO;
            qtdadeSolicitada = (qtdadeSolicitada*50)/100; // diminui a quantidade solicitada pela metade
            resultado[0] = tAditivo - pcAditivo;
            resultado[1] = tGasolina - pcGasolina;
            resultado[2] = tAlcool1 - pcAlcool1;
            resultado[3] = tAlcool2 - pcAlcool2;
        }
        else if ((tAditivo < (MAX_ADITIVO*50)/100) && (tAditivo > (MAX_ADITIVO*25/100)) && ((tGasolina< (MAX_GASOLINA*50)/100) && (tGasolina > (MAX_GASOLINA*25/100))) && ((tAlcool1 < ((MAX_ALCOOL/2)*50)/100) && (tAlcool1 > ((MAX_ALCOOL/2)*25/100))) && ((tAlcool2 < ((MAX_ALCOOL/2)*50)/100) && (tAlcool2 > ((MAX_ALCOOL/2)*25/100))) && tipoPosto == TIPOPOSTO.ESTRATEGICO){
            SITUACAO situacao = SITUACAO.SOBRAVISO;
            qtdadeSolicitada = (qtdadeSolicitada*50)/100; // diminui a quantidade solicitada pela metade
            resultado[0] = tAditivo - pcAditivo;
            resultado[1] = tGasolina - pcGasolina;
            resultado[2] = tAlcool1 - pcAlcool1;
            resultado[3] = tAlcool2 - pcAlcool2;
        }
        else{
            resultado[0] = -1;
        }
        return resultado;
    }
}