package com.example.Thanh.dto.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder /*
*  UserUpdateRequest request = UserUpdateRequest.builder()
                             .passWord("newPassword")
                             .firstName("John")
                             .lastName("Doe")
                             .dob(LocalDate.of(1990, 1, 1))
                             .build();
* */

@NoArgsConstructor // thằng này giúp tạo obj mà không cần phải tạo cons
@AllArgsConstructor // thằng này giúp tạo obj có tất cả các props
@FieldDefaults(level = AccessLevel.PRIVATE)


@JsonInclude(JsonInclude.Include.NON_NULL) // cách này giúp chúng ta loại bỏ những thằng nào trả về mà nó bị null
// ví dụ như message bị null thì mình dùng thằng này để khỏi trả nó về res

public class ApiResponse <T> {
      int code = 1000;
      String message;
       T  result;

}
