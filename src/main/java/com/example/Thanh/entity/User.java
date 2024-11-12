package com.example.Thanh.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@Builder
//-------------------------------
@NoArgsConstructor // Tạo constructor không có tham số cho lớp


//- ------------------
@AllArgsConstructor
// Tạo constructor với tất cả các tham số, giúp dễ dàng khởi tạo đối tượng với tất cả thuộc tính
// new UserUpdateRequest(passWord, firstName, lastName, dob)

//------------------------------------
//  được gán trị
@FieldDefaults(level = AccessLevel.PRIVATE) // Đặt quyền truy cập cho những thuộc tinhs là private




// để đánh dấu đây là Entity
@Entity
public class User {
    @Id // Đây là 1 annotation được dùng để đánh dấu hoặc chỉnh định thông tin đặc biệt
    @GeneratedValue(strategy = GenerationType.UUID) // đây là kiểu gender giá trị mà không trùng lặp
      String id;
      String username;
      String password;
      String firstName;
      String lastName;
      LocalDate dob;


//    public User() {}
//
//    public User(  String username, String password, String firstName, String lastName, LocalDate dob) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.dob = dob;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//
////    -----------
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    //    -----------
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    //    -----------
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    //    -----------
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    //    -----------
//
//    public LocalDate getDob() {
//        return dob;
//    }
//
//    public void setDob(LocalDate dob) {
//        this.dob = dob;
//    }
}
