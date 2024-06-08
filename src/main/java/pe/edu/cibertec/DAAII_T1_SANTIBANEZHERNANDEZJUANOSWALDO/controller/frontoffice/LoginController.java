package pe.edu.cibertec.DAAII_T1_SANTIBANEZHERNANDEZJUANOSWALDO.controller.frontoffice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.cibertec.DAAII_T1_SANTIBANEZHERNANDEZJUANOSWALDO.model.bd.Usuario;
import pe.edu.cibertec.DAAII_T1_SANTIBANEZHERNANDEZJUANOSWALDO.service.UsuarioService;

import javax.net.ssl.HandshakeCompletedEvent;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class LoginController {
    private UsuarioService usuarioService;
    @GetMapping("/login")
    public String login(){
        return "frontoffice/auth/login";
    }

    @GetMapping("/cambiarpassword")
    public String cambiarpassword(){
        return "frontoffice/auth/cambiarpassword";
    }

    @PostMapping("/updatePassword")
    public String actualizarPassword(HttpServletRequest request, @ModelAttribute Usuario usuario){
        usuarioService.updatePassword(usuario);
        return "frontoffice/auth/login";
    }

    @GetMapping("/registrar")
    public String registro(){
        return "frontoffice/auth/registro";
    }

    @PostMapping("/guardarusuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario){
        usuarioService.saveUser(usuario);
        return "frontoffice/auth/login";
    }

    @GetMapping("/login-success")
    public String loginsuccess(HttpServletRequest request){
        UserDetails usuario = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        HttpSession session = request.getSession();
        session.setAttribute("usuario", usuario.getUsername());
        return "frontoffice/auth/dashboard";
    }


    @GetMapping("/cerrarsesion")
    public String cerrarsesion(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "frontoffice/auth/login";
    }

}