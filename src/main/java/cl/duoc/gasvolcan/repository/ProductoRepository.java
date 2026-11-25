package cl.duoc.gasvolcan.repository;

import cl.duoc.gasvolcan.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> { }