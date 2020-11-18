package miravisuals.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import miravisuals.modelo.Compra;
import miravisuals.modelo.Producto;
import miravisuals.modelo.Usuario;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	// Buscar todos los productos de un propietario
	List<Producto> findByPropietario(Usuario propietario);

	// Buscar todos los productos de una compra
	List<Producto> findByCompra(Compra compra);

	// Buscar todos los productos que estén todavía en venta
	List<Producto> findByCompraIsNull();

	// Buscar todos los productos que estén todavía en venta y el sea este el nombre
	List<Producto> findByNombreContainsIgnoreCaseAndCompraIsNull(String nombre);

	// Buscar todos los productos que tengan este nombre y este propietario
	List<Producto> findByNombreContainsIgnoreCaseAndPropietario(String nombre, Usuario propietario);

}