package com.example.Thanh.repository;

import com.example.Thanh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


//Taị sao chúng ta không cần phải viết JpaRepository => vì nó có 1 số  phươngq thức tự tạo hàm
// => Do là tự tạo 1 câu truy vấn

//

@Repository
public interface UserRepository extends JpaRepository<User, String> {
//     đây là 1 hàm có sẵn mà chúng ta không cần phải element bất kì đoạn code nào
//     chỉ cần lấy ra và sử dụng thoi
    boolean existsUserByUsername(String username);

//
    Optional<User> findUserByUsername(String username);

}
