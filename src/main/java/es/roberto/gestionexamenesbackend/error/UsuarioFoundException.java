package es.roberto.gestionexamenesbackend.error;

import lombok.extern.slf4j.Slf4j;
/**
 * @author Roberto Ledezma
 */

public class UsuarioFoundException extends Exception{

    public UsuarioFoundException(String mensaje){
        super(mensaje);
    }
}
