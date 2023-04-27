package com.luheller;


public class CentroDistribuicao {

    private TIPOPOSTO tipoDePosto;
    private SITUACAO situacao;
    private int capacidadeDeCombustivel;
    private double combustivelUtilizavel;
    private int gasolina = 0;
    private int aditivo = 0;
    private int alcool1 = 0;
    private int alcool2 = 0;

    public enum SITUACAO {
        NORMAL, SOBRAVISO, EMERGENCIA
    }

    public enum TIPOPOSTO {
        COMUM, ESTRATEGICO
    }

    public static final int MAX_ADITIVO = 500;
    public static final int MAX_ALCOOL = 2500;
    public static final int MAX_GASOLINA = 10000;

    /**
     * Adiciona os combustiveis no posto, fazendo uma rapida verificação para saber
     * se
     * eles estao dentro da capacidade maxima e se o alcool esta bem distribuido
     * 
     * @param tAditivo
     * @param tGasolina
     * @param tAlcool1
     * @param tAlcool2
     */
    public CentroDistribuicao(int tAditivo, int tGasolina, int tAlcool1, int tAlcool2) {
        if (tAditivo <= MAX_ADITIVO && tGasolina <= MAX_GASOLINA && tAlcool1 == tAlcool2
                && tAlcool1 + tAlcool2 <= MAX_ALCOOL) {
            this.gasolina = this.gasolina + tGasolina;
            this.aditivo = this.aditivo + tAditivo;
            this.alcool1 = this.alcool1 + tAlcool1;
            this.alcool2 = this.alcool2 + tAlcool2;
        }
    }

    /**
     * Metodo que define quantos Litro de combustivel pronto para uso o posto vai
     * ter
     */
    public void defineCombustivelUsavel() {
        boolean naoTemMaisCombustivelParaMistura = false;
        double parametroGasolina = this.gasolina;
        double parametroAditivo = this.aditivo;
        double parametroAlcool = this.alcool1 + this.alcool2;

        do {
            if (parametroGasolina >= 0.7 && parametroAditivo >= 0.05 && parametroAlcool >= 0.25) {
                this.combustivelUtilizavel = this.combustivelUtilizavel + 1;
                parametroGasolina = parametroGasolina - 0.7;
                parametroAditivo = parametroAditivo - 0.05;
                parametroAlcool = parametroAlcool - 0.25;
            } else {
                naoTemMaisCombustivelParaMistura = true;
            }

        } while (naoTemMaisCombustivelParaMistura == true);
    }
    public double getCombustivelUtilizavel() {
        return combustivelUtilizavel;
    }

    public void defineSituacao() {
        combustivelUtilizavel = gasolina + aditivo + alcool1 + alcool2 + 0.0;
        if (tipoDePosto.equals(TIPOPOSTO.COMUM)) {
            if (combustivelUtilizavel >= capacidadeDeCombustivel / 2) {
                this.situacao = SITUACAO.NORMAL;
            } else if (combustivelUtilizavel >= capacidadeDeCombustivel / 4) {
                this.situacao = SITUACAO.SOBRAVISO;
            } else {
                this.situacao = SITUACAO.EMERGENCIA;
            }
        } else {
        }
        if (combustivelUtilizavel >= capacidadeDeCombustivel / 4) {
            this.situacao = SITUACAO.NORMAL;
        } else {
            this.situacao = SITUACAO.SOBRAVISO;
        }
    }

    public SITUACAO getSituacao() {
        return situacao;
    }

    public int gettGasolina() {
        return gasolina;
    }

    public int gettAditivo() {
        return aditivo;
    }

    public int gettAlcool1() {
        return alcool1;
    }

    public int gettAlcool2() {
        return alcool2;
    }

    public int recebeAditivo(int qtdade) {
        int capacidade = 500 - this.aditivo;
        if (qtdade >= capacidade) {
            this.aditivo = 500;
            return capacidade;
        } else if (qtdade < capacidade && qtdade > 0) {
            this.aditivo = this.aditivo + qtdade;
            return qtdade;
        }
        return -1;
    }

    public int recebeGasolina(int qtdade) {
        int capacidade = 10000 - this.gasolina;
        if (qtdade >= capacidade) {
            this.gasolina = 10000;
            return capacidade;
        } else if (qtdade < capacidade && qtdade > 0) {
            this.gasolina = this.gasolina + qtdade;
            return qtdade;
        }
        return -1;
    }

    public int recebeAlcool(int qtdade) {
        int capacidade1 = 1250 - this.alcool1;
        int capacidade2 = 1250 - this.alcool2;
        if (qtdade <= 0) {
            return -1;
        }

        if (qtdade / 2 >= capacidade1 && qtdade / 2 >= capacidade2) {
            this.alcool1 = 1250;
            this.alcool2 = 1250;
            return capacidade1 + capacidade2;
        } else if (qtdade / 2 < capacidade1 && qtdade / 2 < capacidade2) {
            this.alcool1 = this.alcool1 + qtdade / 2;
            this.alcool2 = this.alcool2 + qtdade / 2;
            return qtdade;
        }
        return -1;
    }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        int[] vetorFinal = new int[5];

        int quantidadeAtual = qtdade;
        int parametroAditivo = quantidadeAtual - (500 - this.aditivo);
        quantidadeAtual = quantidadeAtual - parametroAditivo;
        this.aditivo = this.aditivo + parametroAditivo;
        int parametroGasolina = quantidadeAtual - (10000 - this.gasolina);
        quantidadeAtual = quantidadeAtual - parametroGasolina;
        this.gasolina = this.gasolina + parametroGasolina;
        int parametroAlcool1 = quantidadeAtual - (1250 - this.alcool1);
        quantidadeAtual = quantidadeAtual - parametroAlcool1;
        this.alcool1 = this.alcool1 + parametroAlcool1;
        int parametroAlcool2 = quantidadeAtual - (1250 - this.alcool2);
        quantidadeAtual = quantidadeAtual - parametroAlcool2;
        this.alcool2 = this.alcool2 + parametroAlcool2;

        vetorFinal[1] = gettAditivo();
        vetorFinal[2] = gettGasolina();
        vetorFinal[3] = gettAlcool1();
        vetorFinal[4] = gettAlcool2();

        if (qtdade <= 0) {

            vetorFinal[0] = -7;

        } else if (tipoPosto != TIPOPOSTO.ESTRATEGICO && tipoPosto != TIPOPOSTO.COMUM) {

            vetorFinal[0] = -14;
        } else if (parametroAditivo < 0 || parametroAlcool1 < 0 || parametroAlcool2 < 0 || parametroGasolina < 0) {

            vetorFinal[0] = -21;
        }
        return vetorFinal;
        

    }
}