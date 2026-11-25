package cl.duoc.gasvolcan.controller;

import cl.duoc.gasvolcan.model.Usuario;
import cl.duoc.gasvolcan.repository.UsuarioRepository;
import cl.duoc.gasvolcan.security.JwtService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    private final UsuarioRepository usuarios;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthController(UsuarioRepository usuarios, PasswordEncoder encoder, JwtService jwt) {
        this.usuarios = usuarios;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public record Login(@NotBlank String username, @NotBlank String password) { }
    public record Token(String token, String rol, String tipo) { }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody Login request) {
        var encontrado = usuarios.findByUsername(request.username());
        if (encontrado.isEmpty() || !encoder.matches(request.password(), encontrado.get().getPassword())) {
            return ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas"));
        }
        Usuario u = encontrado.get();
        return ResponseEntity.ok(new Token(jwt.crear(u), u.getRol().name(), "Bearer"));
    }

    // Solo responde si el JwtFilter autenticó la petición; React lo usará al recargar (F5).
    @GetMapping("/me")
    public Map<String, String> me(Authentication authentication) {
        String rol = authentication.getAuthorities().stream()
            .map(a -> a.getAuthority())
            .filter(r -> r.startsWith("ROLE_"))
            .map(r -> r.substring("ROLE_".length()))
            .findFirst().orElse("SIN_ROL");
        return Map.of("username", authentication.getName(), "rol", rol);
    }
}