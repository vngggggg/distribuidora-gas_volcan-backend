package cl.duoc.gasvolcan.config;

import cl.duoc.gasvolcan.model.Rol;
import cl.duoc.gasvolcan.model.Usuario;
import cl.duoc.gasvolcan.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

// Usuarios de demostración, solo para laboratorio. Las contraseñas se guardan con BCrypt.
@Configuration
public class DatosIniciales {

    @Bean
    CommandLineRunner cargarUsuarios(UsuarioRepository usuarios, PasswordEncoder encoder) {
        return args -> {
            crear(usuarios, encoder, "admin", "Admin123!", Rol.ADMIN);
            crear(usuarios, encoder, "cliente", "Cliente123!", Rol.CLIENTE);
        };
    }

    private void crear(UsuarioRepository usuarios, PasswordEncoder encoder,
                       String username, String password, Rol rol) {
        if (usuarios.findByUsername(username).isPresent()) return;
        Usuario u = new Usuario();
        u.setUsername(username);
        u.setPassword(encoder.encode(password));
        u.setRol(rol);
        usuarios.save(u);
    }
}