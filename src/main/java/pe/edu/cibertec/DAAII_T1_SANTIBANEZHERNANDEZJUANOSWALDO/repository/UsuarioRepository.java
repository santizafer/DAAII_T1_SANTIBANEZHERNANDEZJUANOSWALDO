package pe.edu.cibertec.DAAII_T1_SANTIBANEZHERNANDEZJUANOSWALDO.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.cibertec.DAAII_T1_SANTIBANEZHERNANDEZJUANOSWALDO.model.bd.Usuario;

public interface UsuarioRepository extends
        JpaRepository<Usuario, Integer> {
    Usuario findByNomusuario(String nomusuario);

    @Transactional
    @Modifying
    @Query(value = "UPDATE usuario SET password=:password where idusuario=:idusuario", nativeQuery = true)

    void actualizarPassword(@Param("password") String password,
                           @Param("idusuario") Integer idusuario);

}
