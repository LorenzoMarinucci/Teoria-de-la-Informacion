import math
def calculoEntropiaMarkov(vectorV, matriz):
    tamanio = len(vectorV);
    total = 0;
    for j in range(tamanio):
        sumFilas = 0;
        for i in range(tamanio):
            probAct = matriz[i][j];
            if(probAct != 0):
                sumFilas += probAct * math.log(1/probAct,2);
        total += sumFilas * vectorV[j];
    return total;

vector = [1/3,0.5,1/6];
matriz = [[0.5,1/3,0],[0.5,1/3,1],[0,1/3,0]];
print(calculoEntropiaMarkov(vector, matriz));