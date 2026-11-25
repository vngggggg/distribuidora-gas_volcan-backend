package cl.duoc.gasvolcan.controller;

import cl.duoc.gasvolcan.model.Pedido;
import cl.duoc.gasvolcan.service.PedidoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:5173")
public class PedidoController {
    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    public record PedidoRequest(@NotNull Long productoId, @NotNull @Min(1) Integer cantidad) { }

    public record PedidoResponse(Long id, LocalDateTime fecha, Long productoId, String producto,
                                 Integer cantidad, Integer total, String estado, String usuario) { }

    private PedidoResponse aRespuesta(Pedido p) {
        return new PedidoResponse(p.getId(), p.getFecha(), p.getProducto().getId(),
            p.getProducto().getNombre(), p.getCantidad(), p.getTotal(), p.getEstado(),
            p.getUsuario().getUsername());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponse crear(@Valid @RequestBody PedidoRequest request, Authentication auth) {
        return aRespuesta(service.crear(auth.getName(), request.productoId(), request.cantidad()));
    }

    @GetMapping("/mios")
    public List<PedidoResponse> mios(Authentication auth) {
        return service.delUsuario(auth.getName()).stream().map(this::aRespuesta).toList();
    }

    @GetMapping
    public List<PedidoResponse> todos() {
        return service.todos().stream().map(this::aRespuesta).toList();
    }
}