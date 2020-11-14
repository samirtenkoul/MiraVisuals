package miravisuals.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import miravisuals.modelo.Usuario;
import miravisuals.servicios.UsuarioServicio;
import miravisuals.subida.StorageSerivce;

@Controller
public class LoginController {

	@Autowired
	UsuarioServicio usuarioServicio;
	@Autowired
	StorageSerivce storageService;

	@GetMapping("/")
	public String welcome() {
		return "redirect:/public/";
	}

	@GetMapping("/auth/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}

	@PostMapping("/auth/register")
	public String register(@ModelAttribute Usuario usuario, @RequestParam("file") MultipartFile file) {
		if (!file.isEmpty()) {
			String imagen = storageService.store(file);
			usuario.setAvatar(MvcUriComponentsBuilder.fromMethodName(FilesController.class, "serveFile", imagen).build()
					.toUriString());
		}
		usuarioServicio.registrar(usuario);

		return "redirect:/auth/login";
	}
}
