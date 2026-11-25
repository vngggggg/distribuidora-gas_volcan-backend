package cl.duoc.gasvolcan.service;

import cl.duoc.gasvolcan.model.Producto;
import cl.duoc.gasvolcan.repository.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> listar() {
        return repository.findAll();
    }

    public Producto buscar(Long id) {
        return repository.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }

    public Producto crear(Producto producto) {
        producto.setId(null);
        return repository.save(producto);
    }

    public Producto actualizar(Long id, Producto cambios) {
        Producto actual = buscar(id);
        actual.setCodigo(cambios.getCodigo());
        actual.setCategoria(cambios.getCategoria());
        actual.setNombre(cambios.getNombre());
        actual.setDescripcion(cambios.getDescripcion());
        actual.setUnidad(cambios.getUnidad());
        actual.setPrecioResidencial(cambios.getPrecioResidencial());
        actual.setPrecioComercial(cambios.getPrecioComercial());
        actual.setStock(cambios.getStock());
        actual.setImagen(cambios.getImagen());
        return repository.save(actual);
    }

    public void eliminar(Long id) {
        repository.delete(buscar(id));
    }
}