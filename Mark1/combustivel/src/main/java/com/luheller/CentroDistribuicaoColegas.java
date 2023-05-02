package com.luheller;

public class CentroDistribuicaoColegas {

    public enum SITUACAO {
        NORMAL, SOBRAVISO, EMERGENCIA;
    }

    public enum TIPOPOSTO {
        COMUM, ESTRATEGICO;
    }

    private static final int MAX_ADDITIONS = 500; 
    private static final int MAX_ALCOHOL = 2500; 
    private static final int MAX_GAS = 10000;

    private int gas;
    private int alcoholT1;
    private int alcoholT2;
    private int additions;
    private static SITUACAO situacao;

    public CentroDistribuicaoColegas (int additions, int gas, int alcoholT1, int alcoholT2){
        this.gas = gas;
        this.alcoholT1 = alcoholT1;
        this.alcoholT2 = alcoholT2;
        if ((alcoholT1 + alcoholT2) > MAX_ALCOHOL) {
            throw new IllegalNumberException();
        }
        this.additions = additions;
        situacao = SITUACAO.NORMAL;
    }

    public void defineSituacao(){
        if (additions < (MAX_ADDITIONS * 25) / 100 || gas < (MAX_GAS * 25) / 100
                || (alcoholT1 + alcoholT2) < (MAX_ALCOHOL * 25) / 100) {
            setSituacao(SITUACAO.EMERGENCIA);
            return;
        }
        if (additions < (MAX_ADDITIONS * 0.50) || gas < (MAX_GAS * 0.50)
                || (alcoholT1 + alcoholT2) < (MAX_ALCOHOL * 0.50)) {
            setSituacao(SITUACAO.SOBRAVISO);
            return;
        } else {
            setSituacao(SITUACAO.NORMAL);
        }
    } 

      public void setSituacao(SITUACAO situacao) {
        CentroDistribuicaoColegas.situacao = situacao;
    }

    public SITUACAO getSituacao() {
        return situacao;
    }
    
    public int getGas() {
        return gas;
    }
    
    public int getAdditions() {
        return additions;
    }
    
    public int getAlcoholT1() {
        return alcoholT1;
    }

    public int getAlcoholT2() {
        return alcoholT2;
    }

    public int recebeAditivo(int qtdade) {
        if (additions + qtdade > MAX_ADDITIONS || qtdade <= 0) {
            throw new IllegalNumberException();
        } else {
            additions += qtdade;
            return MAX_ADDITIONS - additions;
        }
    }

    public int recebeGasolina(int qtdade) {
        if (gas + qtdade > MAX_GAS || qtdade <= 0) {
            throw new IllegalNumberException();
        } else {
            gas += qtdade;
            return MAX_GAS - gas;
        }
    }

    public int recebeAlcool(int qtdade) {
        if ((alcoholT1 + qtdade + alcoholT2) > MAX_ALCOHOL || qtdade <= 0) {
            throw new IllegalNumberException();
        }
        alcoholT1 += qtdade / 2;
        alcoholT2 += qtdade / 2;
        return MAX_ALCOHOL - (alcoholT1 + alcoholT2);
    }

    public int[] encomendaCombustivel(int qtdade, TIPOPOSTO tipoPosto) {
        int[] qtdCombustivel = new int[4];

        int auxAlcohol = ((qtdade * 25) / 100);
        int auxGas = ((qtdade * 70) / 100);
        int auxAdtivo = 0;
        if (qtdade < 40 && qtdade > 10) {
            auxAdtivo = 1;
        } else {
            auxAdtivo = ((qtdade * 5) / 100);
        }

        // No caso de ser recebido um valor inválido por parâmetro deve-se retornar “-7”
        if (qtdade <= 0) {
            qtdCombustivel[0] = -7;
            return qtdCombustivel;
        }
        
         //Se o pedido não puder ser atendido em função da “situação” retorna-se “-14”
        if (tipoPosto == TIPOPOSTO.COMUM && situacao == SITUACAO.EMERGENCIA) {
            qtdCombustivel[0] = -14;
            return qtdCombustivel;
        }

        // Caso não haja combustível suficiente para completar a mistura, retorna-se “-21"
        if (auxAlcohol > (getAlcoholT1() + getAlcoholT2()) || auxGas > getGas() || auxAdtivo > getAdditions()) {
            qtdCombustivel[0] = -21;
            return qtdCombustivel;
        }

        if (tipoPosto == TIPOPOSTO.COMUM) {
            if (situacao == SITUACAO.NORMAL) {

                gas = gas - auxGas;
                additions = additions - auxAdtivo;
                qtdCombustivel[0] = (additions);
                qtdCombustivel[1] = (gas);

                alcoholT1 = alcoholT1 - auxAlcohol / 2;
                alcoholT2 = alcoholT2 - auxAlcohol / 2;

                qtdCombustivel[2] = alcoholT1;
                qtdCombustivel[3] = alcoholT2;
                defineSituacao();
                return qtdCombustivel;

            }
            if (situacao == SITUACAO.SOBRAVISO) {
                auxAlcohol = ((qtdade * 25) / 100) / 2;
                auxGas = ((qtdade * 70) / 100) / 2;
                auxAdtivo = ((qtdade * 5) / 100) / 2;

                gas = gas - auxGas;
                additions = additions - auxAdtivo;
                qtdCombustivel[0] = (additions);
                qtdCombustivel[1] = (gas);
                alcoholT1 = alcoholT1 - auxAlcohol / 2;
                alcoholT2 = alcoholT2 - auxAlcohol / 2;
                
                qtdCombustivel[2] = alcoholT1;
                qtdCombustivel[3] = alcoholT2;
                    qtdCombustivel[2] = alcoholT1;
                    qtdCombustivel[3] = alcoholT2;
                    defineSituacao();
                    return qtdCombustivel;
            }
        }
        if (tipoPosto == TIPOPOSTO.ESTRATEGICO) {
            if (situacao == SITUACAO.NORMAL || situacao == SITUACAO.SOBRAVISO) {

                gas = gas - auxGas;
                additions = additions - auxAdtivo;
                qtdCombustivel[0] = (additions);
                qtdCombustivel[1] = (gas);
                alcoholT1 = alcoholT1 - auxAlcohol / 2;
                alcoholT2 = alcoholT2 - auxAlcohol / 2;

                qtdCombustivel[2] = alcoholT1;
                qtdCombustivel[3] = alcoholT2;
                defineSituacao();
                return qtdCombustivel;
            }
            if (situacao == SITUACAO.EMERGENCIA) {
                auxAlcohol = ((qtdade * 25) / 100) / 2;
                auxGas = ((qtdade * 70) / 100) / 2;
                auxAdtivo = ((qtdade * 5) / 100) / 2;

                gas = gas - auxGas;
                additions = additions - auxAdtivo;
                qtdCombustivel[0] = (additions);
                qtdCombustivel[1] = (gas);
                alcoholT1 = alcoholT1 - auxAlcohol / 2;
                alcoholT2 = alcoholT2 - auxAlcohol / 2;

                qtdCombustivel[2] = alcoholT1;
                qtdCombustivel[3] = alcoholT2;
                defineSituacao();
                return qtdCombustivel;
            }
        }
        return qtdCombustivel;
    }
}
