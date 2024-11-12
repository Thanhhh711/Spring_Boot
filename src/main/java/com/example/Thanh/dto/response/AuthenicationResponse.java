package com.example.Thanh.dto.response;


import lombok.*;
import lombok.experimental.FieldDefaults;

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




public class AuthenicationResponse {
        String token;
        boolean authenicated;

}
