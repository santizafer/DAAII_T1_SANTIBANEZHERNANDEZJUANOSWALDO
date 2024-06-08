package pe.edu.cibertec.DAAII_T1_SANTIBANEZHERNANDEZJUANOSWALDO.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAAII_T1_SANTIBANEZHERNANDEZJUANOSWALDO.model.bd.Rol;
import pe.edu.cibertec.DAAII_T1_SANTIBANEZHERNANDEZJUANOSWALDO.model.bd.Usuario;
import pe.edu.cibertec.DAAII_T1_SANTIBANEZHERNANDEZJUANOSWALDO.repository.RolRepository;
import pe.edu.cibertec.DAAII_T1_SANTIBANEZHERNANDEZJUANOSWALDO.repository.UsuarioRepository;

import java.util.Arrays;
import java.util.HashSet;
@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public Usuario buscarUsuarioXNomUsuario(String nomusuario) {
        return usuarioRepository.findByNomusuario(nomusuario);
    }

    public Usuario buscarUsuarioXIdUsuario(Integer idusuario) {
        return usuarioRepository.findById(idusuario).orElse(null);
    }

    public Usuario saveUser(Usuario usuario) {
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        usuario.setActivo(true);
        Rol usuarioRol = rolRepository.findByNomrol("ADMIN");
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));
        return usuarioRepository.save(usuario);
    }

    public Usuario updatePassword(Usuario usuario) {
        usuarioRepository.actualizarPassword(
                bCryptPasswordEncoder.encode(usuario.getPassword()),  this.buscarUsuarioXNomUsuario(usuario.getNomusuario()).getIdusuario()
        );
        return usuario;

    }



}