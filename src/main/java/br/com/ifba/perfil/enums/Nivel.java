/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.ifba.perfil.enums;

/**
 *
 * @author USER
 */
public enum Nivel {
    
    BASICO("Básico"),
    INTERMEDIARIO("Intermediário"),
    AVANCADO("Avançado");
    
    private final String label;

    Nivel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
