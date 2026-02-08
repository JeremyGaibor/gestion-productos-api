package com.productos.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productos.model.Producto;
import com.productos.repository.ProductoRepository;

@Service
public class ProductoService {


  @Autowired
  private ProductoRepository repo;

  public List<Producto> listar() {
    return repo.findAll();
  }

  public Producto guardar(Producto producto) {
    String estado = obtenerEstadoProducto(producto);
    System.out.println("Estado del producto: " + estado);
    return repo.save(producto);
  }

  public Producto actualizar(Long id, Producto p) {
    Producto prod = repo.findById(id).orElseThrow();
    prod.setNombre(p.getNombre());
    prod.setPrecio(p.getPrecio());
    prod.setStock(p.getStock());
    prod.setDescripcion(p.getDescripcion());
    return repo.save(prod);
  }

    public void eliminar(Long id) {
    if (repo.existsById(id)) {
        repo.deleteById(id);
        System.out.println(obtenerMensaje(200));
    } else {
        System.out.println(obtenerMensaje(404));
    }
  }


  public String obtenerEstadoProducto(Producto producto) {
    if (producto.getStock() > 0) {
        return "DISPONIBLE";
    } else {
        return "SIN STOCK";
    }
  }

  public Producto obtenerPorId(Long id) {
    return repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
  }

  private static final Map<Integer, String> MENSAJES = Map.of(
        200, "Operación realizada con éxito",
        400, "Datos incorrectos",
        404, "Producto no encontrado",
        500, "Error interno del servidor"
  );

    public String obtenerMensaje(int codigo) {
    return MENSAJES.getOrDefault(codigo, "Código no definido");
  }

  public boolean existe(Long id) {
    return repo.existsById(id);
  }

  public List<Producto> obtenerProductosConMayorStock() {
    return repo.findAll()
            .stream()
            .sorted((p1, p2) -> Integer.compare(p2.getStock(), p1.getStock()))
            .collect(Collectors.toList());
  }
}
