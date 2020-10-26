package miravisuals.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import miravisuals.modelo.Compra;
import miravisuals.modelo.Usuario;

public interface CompraRepository extends JpaRepository<Compra, Long> {
	
	//Busqueda compras de un propietario
	List<Compra> findByPropietario(Usuario Propietario);
}
