package cl.duoc.gasvolcan.service;

import cl.duoc.gasvolcan.model.Pedido;
import cl.duoc.gasvolcan.model.Producto;
import cl.duoc.gasvolcan.model.Usuario;
import cl.duoc.gasvolcan.repository.PedidoRepository;
import cl.duoc.gasvolcan.repository.ProductoRepository;
import cl.duoc.gasvolcan.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidos;
    private final ProductoRepository productos;
    private final UsuarioRepository usuarios;

    public PedidoService(PedidoRepository pedidos, ProductoRepository productos, UsuarioRepository usuarios) {
        this.pedidos = pedidos;
        this.productos = productos;
        this.usuarios = usuarios;
    }

    @Transactional
    public Pedido crear(String username, Long productoId, int cantidad) {
        Usuario usuario = usuarios.findByUsername(username).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no encontrado"));
        Producto producto = productos.findById(productoId).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

        if (cantidad > producto.getStock()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                "Stock insuficiente: disponible " + producto.getStock());
        }

        producto.setStock(producto.getStock() - cantidad);
        productos.save(producto);

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setProducto(producto);
        pedido.setCantidad(cantidad);
        pedido.setTotal(cantidad * producto.getPrecioResidencial());
        pedido.setEstado("CONFIRMADO");
        pedido.setFecha(LocalDateTime.now());
        return pedidos.save(pedido);
    }

    public List<Pedido> todos() {
        return pedidos.findAllByOrderByFechaDesc();
    }

    public List<Pedido> delUsuario(String username) {
        return pedidos.findByUsuarioUsernameOrderByFechaDesc(username);
    }
}