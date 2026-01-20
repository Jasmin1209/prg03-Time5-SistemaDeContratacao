/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.util;

import br.com.ifba.perfil.entity.Perfil;

/**
 *
 * @author USER
 */
public class StringUtil {
    //Verifica se uma string é nula ou vazia
    public static boolean isNullOrEmpty(Perfil perfil){
        return perfil == null;
    }
    
}
