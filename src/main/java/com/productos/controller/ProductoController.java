package com.productos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productos.model.Producto;
import com.productos.service.ProductoService;

@RestController
@RequestMapping("/productos")
@CrossOrigin
public class ProductoController {
    
  @Autowired
  private ProductoService service;

  @GetMapping("/{id}")
public Producto obtenerPorId(@PathVariable Long id) {
    return service.obtenerPorId(id);
}

  @GetMapping
  public List<Producto> listar() {
    return service.listar();
  }

  @PostMapping
  public Map<String, Object> guardar(@RequestBody Producto p) {

  Producto guardado = service.guardar(p);

  Map<String, Object> response = new HashMap<>();
  response.put("mensaje", service.obtenerMensaje(200));
  response.put("estado", service.obtenerEstadoProducto(guardado));
  response.put("producto", guardado);

  return response;

  } 

  @PutMapping("/{id}")
  public Map<String, Object> actualizar(@PathVariable Long id, @RequestBody Producto p)  {
  
  Producto actualizado = service.actualizar(id, p);

  Map<String, Object> response = new HashMap<>();
  response.put("mensaje", service.obtenerMensaje(200));
  response.put("estado", service.obtenerEstadoProducto(actualizado));
  response.put("producto", actualizado);

  return response;
  }

  @DeleteMapping("/{id}")
  public Map<String, String> eliminar(@PathVariable Long id) {
    
  Map<String, String> response = new HashMap<>();

  if (service.existe(id)) {
    service.eliminar(id);
    response.put("mensaje", service.obtenerMensaje(200));
  } else {
    response.put("mensaje", service.obtenerMensaje(404));
  }

  return response;
  }
}
