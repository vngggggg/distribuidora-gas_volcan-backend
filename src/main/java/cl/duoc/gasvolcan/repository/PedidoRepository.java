package cl.duoc.gasvolcan.repository;

import cl.duoc.gasvolcan.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findAllByOrderByFechaDesc();
    List<Pedido> findByUsuarioUsernameOrderByFechaDesc(String username);
}