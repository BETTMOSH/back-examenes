package es.roberto.gestionexamenesbackend.service;

import es.roberto.gestionexamenesbackend.dto.usuario.UsuarioMapper;
import es.roberto.gestionexamenesbackend.repository.UsuarioRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RolService {
    private final UsuarioRolRepository usuarioRolRepository;
    private final UsuarioMapper mapper;


}
