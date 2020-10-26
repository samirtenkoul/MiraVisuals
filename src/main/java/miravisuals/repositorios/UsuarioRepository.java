package miravisuals.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import miravisuals.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	//Búsqueda por email
	Usuario findFirstByEmail(String email);

}
