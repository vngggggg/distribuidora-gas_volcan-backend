package cl.duoc.gasvolcan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código es obligatorio")
    private String codigo;

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;

    @NotBlank(message = "La unidad es obligatoria")
    private String unidad;

    @NotNull(message = "El precio residencial es obligatorio")
    @Min(value = 0, message = "El precio residencial no puede ser negativo")
    private Integer precioResidencial;

    @NotNull(message = "El precio comercial es obligatorio")
    @Min(value = 0, message = "El precio comercial no puede ser negativo")
    private Integer precioComercial;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    private String imagen;

    public Producto() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public Integer getPrecioResidencial() { return precioResidencial; }
    public void setPrecioResidencial(Integer precioResidencial) { this.precioResidencial = precioResidencial; }
    public Integer getPrecioComercial() { return precioComercial; }
    public void setPrecioComercial(Integer precioComercial) { this.precioComercial = precioComercial; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }
}