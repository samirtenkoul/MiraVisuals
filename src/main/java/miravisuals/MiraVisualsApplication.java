package miravisuals;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import miravisuals.modelo.Producto;
import miravisuals.modelo.Usuario;
import miravisuals.servicios.ProductoServicio;
import miravisuals.servicios.UsuarioServicio;
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
//
//	@Bean
//    public CommandLineRunner initData(UsuarioServicio usuarioServicio, ProductoServicio productoServicio) {
//        return args -> {
//        	
//    	    Usuario usuario = new Usuario("Ivan", "Macia García", null, "ivan@miravisuals.com", "mira");
//    	    usuarioServicio.registrar(usuario);
//    	    Usuario usuario2 = new Usuario("Samir", "Tenkoul González", null, "samir@miravisuals.com", "mira");
//    	    usuarioServicio.registrar(usuario2);
//    	    
//    	    Producto prod = new Producto("Cachorrito", (float) 2.50, "https://images.freeimages.com/images/large-previews/ce3/puppies-1-1308839.jpg", usuario);
//    	    Producto prod2 = new Producto("Cachorrito2", (float) 12.00, "https://images.freeimages.com/images/large-previews/8d7/sad-puppy-1554093.jpg", usuario);
//    	    Producto prod3 = new Producto("Cachorrito3", (float) 4.75, "https://images.freeimages.com/images/large-previews/370/puppies-dogs-5-1531181.jpg", usuario);
//    	    
//    	    Producto prod4 = new Producto("Boda1", (float) 4.00, "https://images.freeimages.com/images/large-previews/077/wedding-1313272.jpg", usuario2);
//    	    Producto prod5 = new Producto("Boda2", (float) 4.00, "https://images.freeimages.com/images/large-previews/61e/wedding-1311993.jpg", usuario2);
//    	    Producto prod6 = new Producto("Boda3", (float) 7.00, "https://images.freeimages.com/images/large-previews/e27/wedding-1574557.jpg", usuario2);
//
//    	    productoServicio.insertar(prod);
//    	    productoServicio.insertar(prod2);
//    	    productoServicio.insertar(prod3);
//
//    	    productoServicio.insertar(prod4);
//    	    productoServicio.insertar(prod5);
//    	    productoServicio.insertar(prod6);
//        
//        };
//=======
	@Bean
	public CommandLineRunner initData(UsuarioServicio usuarioServicio, ProductoServicio productoServicio) {
		return args -> {

			Usuario usuario = new Usuario("Ivan", "Macia García",
					"https://images.freeimages.com/images/small-previews/023/geek-avatar-1632962.jpg",
					"ivan@miravisuals.com", "mira");
			usuarioServicio.registrar(usuario);
			Usuario usuario2 = new Usuario("Samir", "Tenkoul González",
					"https://images.freeimages.com/images/small-previews/7e8/man-avatar-1632965.jpg",
					"samir@miravisuals.com", "mira");
			usuarioServicio.registrar(usuario2);

			Producto prod = new Producto("Cachorrito", (float) 2.50,
					"https://images.freeimages.com/images/large-previews/ce3/puppies-1-1308839.jpg", usuario);
			Producto prod2 = new Producto("Cachorrito2", (float) 12.00,
					"https://images.freeimages.com/images/large-previews/8d7/sad-puppy-1554093.jpg", usuario);
			Producto prod3 = new Producto("Cachorrito3", (float) 4.75,
					"https://images.freeimages.com/images/large-previews/370/puppies-dogs-5-1531181.jpg", usuario);

			Producto prod4 = new Producto("Boda1", (float) 4.00,
					"https://images.freeimages.com/images/large-previews/077/wedding-1313272.jpg", usuario2);
			Producto prod5 = new Producto("Boda2", (float) 4.00,
					"https://images.freeimages.com/images/large-previews/61e/wedding-1311993.jpg", usuario2);
			Producto prod6 = new Producto("Boda3", (float) 7.00,
					"https://images.freeimages.com/images/large-previews/e27/wedding-1574557.jpg", usuario2);

			productoServicio.insertar(prod);
			productoServicio.insertar(prod2);
			productoServicio.insertar(prod3);

			productoServicio.insertar(prod4);
			productoServicio.insertar(prod5);
			productoServicio.insertar(prod6);

		};
	}

}
