package com.mobiauto.resalemanagement;


public record Cnpj(String value) {
    private static final CNPJValidator VALIDATOR = new CNPJValidator();

    public Cnpj {
        if (!VALIDATOR.isValid(value)) {
            throw new IllegalArgumentException("invalid cnpj: "+value);
        }
    }
}
