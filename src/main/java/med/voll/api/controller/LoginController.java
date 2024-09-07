package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping
    public ResponseEntity<?> checkLogin(@RequestBody @Valid LoginDto login) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(login.login(), login.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);
        return ResponseEntity.ok(authentication.getPrincipal());
    }
}