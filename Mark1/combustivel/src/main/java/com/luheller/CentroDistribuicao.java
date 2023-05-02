package com.luheller;

public class CentroDistribuicao {
    private int tAditivo;
    private int tGasolina;
    private int tAlcool1;
    private int tAlcool2;
    private SITUACAO situacao;
    int qtdadeRecebida = 0;
    int porcentAditivo = (tAditivo * 100) / MAX_ADITIVO;
    int porcentGasolina = (tGasolina * 100) / MAX_GASOLINA;
    int porcentAlcool1 = (tAlcool1 * 100) / (MAX_ALCOOL / 2);
    int porcentAlcool2 = (tAlcool2 * 100) / (MAX_ALCOOL / 2);

    public enum SITUACAO {
        NORMAL, SOBRAVISO, EMERGENCIA
    }

    public enum TIPOPOSTO {
        COMUM, ESTRATEGICO
    }

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;
    private static final int IllegalArgumentException = 0;

    public CentroDistribuicao(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        this.tAditivo = tAditivo;
        this.tGasolina = tGasolina;
        this.tAlcool1 = tAlcool1;
        this.tAlcool2 = tAlcool2;
        if (tAditivo < 0 || tGasolina < 0 || tAlcool1 < 0 || tAlcool2 < 0) {
            throw new IllegalArgumentException("Erro");
        }
            
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
        if (qtdadeSolicitada <= 0 || (tipoPosto != TIPOPOSTO.COMUM && tipoPosto != TIPOPOSTO.ESTRATEGICO)) {
            return new int[]{-1, tAditivo, tGasolina, tAlcool1, tAlcool2};
        }
        
        /*int porcentAditivo = (tAditivo * 100) / MAX_ADITIVO;
        int porcentGasolina = (tGasolina * 100) / MAX_GASOLINA;
        int porcentAlcool1 = (tAlcool1 * 100) / (MAX_ALCOOL / 2);
        int porcentAlcool2 = (tAlcool2 * 100) / (MAX_ALCOOL / 2);*/

        int qtdGasolinaUsadaNaMistura = 0;
        int qtdAlcoolUsadaNaMistura = 0;
        int qtdAditivoUsadaNaMistura = 0;
        
        int restaGasolinaNoTanque = 0;
        int restaAlcool1NoTanque = 0;
        int restaAlcool2NoTanque = 0;
        int restaAditivoNoTanque = 0;
        

        if (situacao == SITUACAO.NORMAL) {
            if ((tipoPosto == TIPOPOSTO.COMUM)||(tipoPosto == TIPOPOSTO.ESTRATEGICO)){
                qtdGasolinaUsadaNaMistura = (qtdadeSolicitada * 70) / 100;
                qtdAlcoolUsadaNaMistura = (qtdadeSolicitada * 25) / 100;
                qtdAditivoUsadaNaMistura = (qtdadeSolicitada * 5) / 100;

                restaGasolinaNoTanque = tGasolina - qtdGasolinaUsadaNaMistura;
                restaAlcool1NoTanque = tAlcool1 - (qtdAlcoolUsadaNaMistura / 2);
                restaAlcool2NoTanque = tAlcool2 - (qtdAlcoolUsadaNaMistura / 2);
                restaAditivoNoTanque = tAditivo - qtdAditivoUsadaNaMistura;

                tGasolina = restaGasolinaNoTanque;
                tAlcool1 = restaAlcool1NoTanque;
                tAlcool2 = restaAlcool2NoTanque;
                tAditivo = restaAditivoNoTanque;

                return new int[] {restaAditivoNoTanque, restaGasolinaNoTanque, restaAlcool1NoTanque, restaAlcool2NoTanque};
            } 
        }else if (situacao == SITUACAO.SOBRAVISO){
            if(tipoPosto == TIPOPOSTO.COMUM){
                qtdadeSolicitada = qtdadeSolicitada/2;
                qtdGasolinaUsadaNaMistura = (qtdadeSolicitada * 70) / 100;
                qtdAlcoolUsadaNaMistura = (qtdadeSolicitada * 25) / 100;
                qtdAditivoUsadaNaMistura = (qtdadeSolicitada * 5) / 100;

                restaGasolinaNoTanque = tGasolina - qtdGasolinaUsadaNaMistura;
                restaAlcool1NoTanque = tAlcool1 - (qtdAlcoolUsadaNaMistura / 2);
                restaAlcool2NoTanque = tAlcool2 - (qtdAlcoolUsadaNaMistura / 2);
                restaAditivoNoTanque = tAditivo - qtdAditivoUsadaNaMistura;

                tGasolina = restaGasolinaNoTanque;
                tAlcool1 = restaAlcool1NoTanque;
                tAlcool2 = restaAlcool2NoTanque;
                tAditivo = restaAditivoNoTanque;

                return new int[] {restaAditivoNoTanque, restaGasolinaNoTanque, restaAlcool1NoTanque, restaAlcool2NoTanque};

            }else if(tipoPosto == TIPOPOSTO.ESTRATEGICO){
                qtdGasolinaUsadaNaMistura = (qtdadeSolicitada * 70) / 100;
                qtdAlcoolUsadaNaMistura = (qtdadeSolicitada * 25) / 100;
                qtdAditivoUsadaNaMistura = (qtdadeSolicitada * 5) / 100;

                restaGasolinaNoTanque = tGasolina - qtdGasolinaUsadaNaMistura;
                restaAlcool1NoTanque = tAlcool1 - (qtdAlcoolUsadaNaMistura / 2);
                restaAlcool2NoTanque = tAlcool2 - (qtdAlcoolUsadaNaMistura / 2);
                restaAditivoNoTanque = tAditivo - qtdAditivoUsadaNaMistura;

                tGasolina = restaGasolinaNoTanque;
                tAlcool1 = restaAlcool1NoTanque;
                tAlcool2 = restaAlcool2NoTanque;
                tAditivo = restaAditivoNoTanque;

                return new int[] {restaAditivoNoTanque, restaGasolinaNoTanque, restaAlcool1NoTanque, restaAlcool2NoTanque};
            }
        }else if(situacao == SITUACAO.EMERGENCIA) {
            if (tipoPosto == TIPOPOSTO.COMUM) {
                return new int[]{-21, tAditivo, tGasolina, tAlcool1, tAlcool2};
            }
            else if(tipoPosto == TIPOPOSTO.ESTRATEGICO){
                qtdadeSolicitada = qtdadeSolicitada/2;
                qtdGasolinaUsadaNaMistura = (qtdadeSolicitada * 70) / 100;
                qtdAlcoolUsadaNaMistura = (qtdadeSolicitada * 25) / 100;
                qtdAditivoUsadaNaMistura = (qtdadeSolicitada * 5) / 100;

                restaGasolinaNoTanque = tGasolina - qtdGasolinaUsadaNaMistura;
                restaAlcool1NoTanque = tAlcool1 - (qtdAlcoolUsadaNaMistura / 2);
                restaAlcool2NoTanque = tAlcool2 - (qtdAlcoolUsadaNaMistura / 2);
                restaAditivoNoTanque = tAditivo - qtdAditivoUsadaNaMistura;

                tGasolina = restaGasolinaNoTanque;
                tAlcool1 = restaAlcool1NoTanque;
                tAlcool2 = restaAlcool2NoTanque;
                tAditivo = restaAditivoNoTanque;

                return new int[] {restaAditivoNoTanque, restaGasolinaNoTanque, restaAlcool1NoTanque, restaAlcool2NoTanque};
            }
        }else{
            return new int[]{-14, tAditivo, tGasolina, tAlcool1, tAlcool2};
        }
        return null;
           
        }

    }
