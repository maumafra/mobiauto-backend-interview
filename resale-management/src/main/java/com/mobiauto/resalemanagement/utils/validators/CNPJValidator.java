package com.mobiauto.resalemanagement.utils.validators;

import java.io.Serializable;

public class CNPJValidator implements Serializable {
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    public boolean isValid(final String cnpjParameter) {
        String cnpj = cnpjParameter
                .trim()
                .replace(".", "")
                .replace("/", "")
                .replace("-", "");
        if (cnpj.length() != 14) {
            return false;
        }
        int dig1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
        int dig2 = calcularDigito(cnpj.substring(0,12) + dig1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0,12) + dig1 + dig2);
    }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }
}
