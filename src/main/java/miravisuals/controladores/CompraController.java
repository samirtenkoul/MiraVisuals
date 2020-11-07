package miravisuals.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import miravisuals.modelo.Compra;
import miravisuals.modelo.Producto;
import miravisuals.modelo.Usuario;
import miravisuals.servicios.CompraServicio;
import miravisuals.servicios.ProductoServicio;
import miravisuals.servicios.UsuarioServicio;

@Controller
@RequestMapping("/app")
public class CompraController {

	@Autowired
	CompraServicio compraServicio;
	@Autowired
	ProductoServicio productoServicio;
	@Autowired
	UsuarioServicio usuarioServicio;
	@Autowired
	HttpSession session;
	
	private Usuario usuario;
	
	@SuppressWarnings("unchecked")
	@ModelAttribute("carrito")
	public List<Producto> productosCarrito(){
		List<Long> contenido = (List<Long>) session.getAttribute("carrito");
		return (contenido == null) ? null : productoServicio.variosPorId(contenido);
	}

	@ModelAttribute("total_carrito")
	public Double totalCarrito() {
		List<Producto> productosCarrito = productosCarrito();
		if (productosCarrito != null) {
			return productosCarrito.stream().mapToDouble(p -> p.getPrecio()).sum();
		}
		return 0.0;
	}

	@ModelAttribute("mis_compras")
	public List<Compra> misCompras(){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		usuario = usuarioServicio.buscarPorEmail(email);
		return compraServicio.porPropietario(usuario);
	}
	
	@GetMapping("/carrito")
	public String verCarrito(Model model) {
		return "app/compra/carrito";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("carrito/add/{id}")
	public String addCarrito(Model model, @PathVariable Long id) {
		List<Long> contenido = (List<Long>) session.getAttribute("carrito");
		if (contenido == null) {
			contenido = new ArrayList<>();
		}
		if (!contenido.contains(id)) {
			contenido.add(id);
		}
		session.setAttribute("carrito", contenido);
		return "redirect:/app/carrito";
	}
	
	@SuppressWarnings({"unchecked"})
	@GetMapping("carrito/eliminar/{id}")
	public String borrarDelCarrito(Model model, @PathVariable Long id) {
		List<Long> contenido = (List<Long>) session.getAttribute("carrito");
		if (contenido == null) {
			return "redirec:/public";
		}
		contenido.remove(id);
		if (contenido.isEmpty()) {
			session.removeAttribute("carrito");
		}else {
			session.setAttribute("carrito", contenido);
		}
		return "redirect:/app/carrito";
	}
}