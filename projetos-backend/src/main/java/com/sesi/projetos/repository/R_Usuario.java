package com.sesi.projetos.repository;

import com.sesi.projetos.auth.spring_security.model.UserRole;
import com.sesi.projetos.model.usuario.classes.GetUsuariosResponse;
import com.sesi.projetos.model.usuario.interfaces.I_UsuarioSafe;
import com.sesi.projetos.model.usuario.classes.M_Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface R_Usuario extends JpaRepository<M_Usuario, Long> {
    M_Usuario findByUsername(String username);

    @Query(value = "select id, nome, role from usuario where is_active = true order by role asc;", nativeQuery = true)
    List<I_UsuarioSafe> findAllUsersSafeInfo();

    @Transactional
    @Modifying
    @Query(value = "update usuario set role = :role where username = :username and is_active = true;", nativeQuery = true)
    void updateUserRole(String username, UserRole role);

    @Query(value = "select * from usuario where cpf = :cpf and is_active = true;", nativeQuery = true)
    M_Usuario findByCpf(@Param("cpf") String cpf);
}