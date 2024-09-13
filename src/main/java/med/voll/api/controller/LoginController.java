package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.TokenDto;
import med.voll.api.dto.LoginDto;
import med.voll.api.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<TokenDto> checkLogin(@RequestBody @Valid LoginDto login) {
        return ResponseEntity.ok(new TokenDto(loginService.checkLogin(login.login(), login.senha())));
    }
}