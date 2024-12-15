package es.roberto.gestionexamenesbackend.service;

import es.roberto.gestionexamenesbackend.entity.UsuarioRol;
import es.roberto.gestionexamenesbackend.repository.UsuarioRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Roberto Ledezma
 */

@RequiredArgsConstructor
@Service
public class UsuarioRolService {
    private final UsuarioRolRepository usuarioRolRepository;


}
