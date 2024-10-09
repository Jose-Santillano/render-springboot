package com.tienda.productos.controller;

import com.tienda.productos.model.Producto;
import com.tienda.productos.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id){
        return productoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto producto){
        Producto existingProducto = productoRepository.findById(id).orElse(null);

        if(existingProducto != null){
            existingProducto.setNombre(producto.getNombre());
            existingProducto.setPrecio(producto.getPrecio());
            return productoRepository.save(existingProducto);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id){
        productoRepository.deleteById(id);
    }
}
