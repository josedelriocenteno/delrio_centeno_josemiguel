/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pagos.excepciones;

/**
 *
 * @author delcenjo
 */
public class SaldoInsuficienteException extends Exception{
    public SaldoInsuficienteException(String mensaje){
        super(mensaje);
    }         
}
