package med.voll.api.service;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.model.Usuario;
import med.voll.api.model.UsuarioLogado;
import med.voll.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByLogin(login).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return new UsuarioLogado(usuario, List.of());
    }
}
