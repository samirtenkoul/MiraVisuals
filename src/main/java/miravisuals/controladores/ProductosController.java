package miravisuals.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import miravisuals.modelo.Producto;
import miravisuals.modelo.Usuario;
import miravisuals.servicios.ProductoServicio;
import miravisuals.servicios.UsuarioServicio;
import miravisuals.subida.StorageSerivce;

@Controller
@RequestMapping("/app")
public class ProductosController {

	@Autowired
	ProductoServicio productos;
	@Autowired
	UsuarioServicio usuarios;
	@Autowired
	StorageSerivce storageService;
	private Usuario usuario;

	@ModelAttribute("misproductos")
	public List<Producto> misProductos() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		usuario = usuarios.buscarPorEmail(email);
		return productos.productosDeUnPropietario(usuario);
	}

	@GetMapping("/misproductos")
	public String listado(Model model, @RequestParam(name = "q", required = false) String query) {
		if (query != null) {
			model.addAttribute("misproductos", productos.buscarMisProductos(query, usuario));
		}
		return "app/producto/lista";
	}

	@GetMapping("/misproductos/{id}/eliminar")
	public String eliminar(@PathVariable Long id) {
		Producto p = productos.findById(id);
		if (p.getCompra() == null) {
			productos.borrar(p.getId());
		}
		return "redirect:/app/misproductos";
	}

	@GetMapping("/producto/nuevo")
	public String nuevo(Model model) {
		model.addAttribute("producto", new Producto());
		return "app/producto/form";
	}

	@PostMapping("/producto/nuevo/submit")
	public String nuevoSubmit(@ModelAttribute Producto producto, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			String imagen = storageService.store(file);
			producto.setImagen(MvcUriComponentsBuilder.fromMethodName(FilesController.class, "serveFile", imagen)
					.build().toUriString());
		}
		producto.setPropietario(usuario);
		productos.insertar(producto);
		return "redirect:/app/misproductos";
	}

}
