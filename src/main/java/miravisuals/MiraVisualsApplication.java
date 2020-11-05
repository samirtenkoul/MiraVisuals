package miravisuals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiraVisualsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiraVisualsApplication.class, args);
	}
	
//	@Bean
//    public CommandLineRunner initData(UsuarioServicio usuarioServicio, ProductoServicio productoServicio) {
//        return args -> {
//
//    	    Usuario usuario = new Usuario("Ivan", "Macia García", null, "ivan@miravisuals.com", "ivan");
//    	    usuarioServicio.registrar(usuario);
//    	    Usuario usuario2 = new Usuario("Samir", "Tenkoul González", null, "samir@miravisuals.com", "samir");
//    	    usuarioServicio.registrar(usuario2);
//        };
//	}
	
}	

