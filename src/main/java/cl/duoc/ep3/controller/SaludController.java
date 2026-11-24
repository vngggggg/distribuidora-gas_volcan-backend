package cl.duoc.ep3.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class SaludController {
    @GetMapping("/salud")
    public Map<String, String> salud() {
        return Map.of("estado", "ok", "mensaje", "Backend EP3 funcionando");
    }
}
