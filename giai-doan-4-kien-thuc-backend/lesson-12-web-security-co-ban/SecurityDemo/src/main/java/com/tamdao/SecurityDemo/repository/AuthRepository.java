package com.tamdao.SecurityDemo.repository;

import com.tamdao.SecurityDemo.dto.UserCreateRequest;
import com.tamdao.SecurityDemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {
//    // LỖ HỔNG: Xâu chuỗi dữ liệu trực tiếp trong truy vấn
//    @Query(value = "SELECT * FROM users WHERE username = '\" + ?1 + \"' AND password = '\" + ?2 + \"'", nativeQuery = true)

    //An toàn dung JPQL
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    User secureLogin(@Param("username") String username, @Param("password") String password);

    Optional<User> findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);
}
