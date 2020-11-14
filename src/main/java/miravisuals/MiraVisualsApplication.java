package miravisuals;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import miravisuals.subida.StorageSerivce;

@SpringBootApplication
public class MiraVisualsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiraVisualsApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageSerivce ss) {
		return (args -> {
			ss.deleteAll();
			ss.init();
		});
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
	
	
//	@Bean
//    public CommandLineRunner initData(UsuarioServicio usuarioServicio, ProductoServicio productoServicio) {
//        return args -> {
//
//    	    Usuario usuario = usuarioServicio.findById(1);
//    	    
//    	    Producto prod = new Producto("Cachorrito", (float) 2.50, "https://images.freeimages.com/images/large-previews/ce3/puppies-1-1308839.jpg", usuario);
//    	    Producto prod2 = new Producto("Cachorrito2", (float) 12.00, "https://images.freeimages.com/images/large-previews/8d7/sad-puppy-1554093.jpg", usuario);
//    	    Producto prod3 = new Producto("Cachorrito3", (float) 4.75, "https://images.freeimages.com/images/large-previews/370/puppies-dogs-5-1531181.jpg", usuario);
//
//    	    productoServicio.insertar(prod);
//    	    productoServicio.insertar(prod2);
//    	    productoServicio.insertar(prod3);
//        
//        };
//	}
	
}	

