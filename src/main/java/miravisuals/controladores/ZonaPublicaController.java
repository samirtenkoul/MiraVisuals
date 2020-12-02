package miravisuals.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import miravisuals.modelo.Producto;
import miravisuals.servicios.ProductoServicio;

@Controller
@RequestMapping("/public")
public class ZonaPublicaController {

	@Autowired
	ProductoServicio productoServicio;

	@ModelAttribute("productos")
	public List<Producto> productos() {
		return productoServicio.findAll();
	}

	@GetMapping({ "/", "/index" })
	public String index(Model model, @RequestParam(name = "q", required = false) String query) {
		if (query != null) {
			model.addAttribute("productos", productoServicio.buscar(query));
		}
		return "index";
	}

	@GetMapping("/producto/{id}")
	public String showProducto(Model model, @PathVariable Long id) {
		Producto result = productoServicio.findById(id);
		if (result != null) {
			model.addAttribute("producto", result);
			return "producto";
		}
		return "redirect:/public";
	}

	@GetMapping("/contacto")
	public String showContacto(Model model) {
		return "Contacto";
	}
	
	@GetMapping("/avisolegal")
	public String showAvisoLegal(Model model) {
		return "AvisoLegal";
	}
}
