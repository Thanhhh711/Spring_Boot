package com.example.Thanh.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;



// Mình có thể sử dụng annotation Getter và Setter
// thì minh không cần phải tạo hàm getter và setter
//@Getter
//@Setter


// annotation thằng này giúp hỗ trợ chúng ta tạo ra các  gettter và setter mà không cần phải
// import ở trên
@Data //  tự động sinh các phương thức getter và setter\
//------------------------------
@Builder // Dễ dàng bằng cách sử dụng mẫu thiết kế Builder

/*
*  UserUpdateRequest request = UserUpdateRequest.builder()
                             .passWord("newPassword")
                             .firstName("John")
                             .lastName("Doe")
                             .dob(LocalDate.of(1990, 1, 1))
                             .build();
* */



//-------------------------------
@NoArgsConstructor // Tạo constructor không có tham số cho lớp


//- ------------------
@AllArgsConstructor
// Tạo constructor với tất cả các tham số, giúp dễ dàng khởi tạo đối tượng với tất cả thuộc tính
// new UserUpdateRequest(passWord, firstName, lastName, dob)

//------------------------------------
//  được gán trị
@FieldDefaults(level = AccessLevel.PRIVATE) // Đặt quyền truy cập cho những thuộc tinhs là private





public class  UserCreationRequest {
     String id;
    //     chỗ này là mình validate password nè
    @Size(min = 3, message = "USERNAME_INVALID") // chỗ này mình dùng các key của enum
     String username;

//     chỗ này là mình validate password nè
    @Size(min = 8, message = "INVALID_PASSWORD") // chỗ này mình dùng các key của enum
     String password;
     String firstName;
     String lastName;
     LocalDate dob;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
