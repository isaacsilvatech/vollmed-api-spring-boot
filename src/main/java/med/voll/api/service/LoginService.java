package med.voll.api.service;

import med.voll.api.model.UsuarioLogado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String checkLogin(String login, String password) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(login, password);
        var authentication = authenticationManager.authenticate(authenticationToken);
        return tokenService.genarateAccessToken(((UsuarioLogado) authentication.getPrincipal()).getUsuario());
    }
}
