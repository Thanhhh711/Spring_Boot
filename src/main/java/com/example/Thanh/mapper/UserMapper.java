package com.example.Thanh.mapper;

import com.example.Thanh.dto.request.UserCreationRequest;
import com.example.Thanh.dto.request.UserUpdateRequest;
import com.example.Thanh.dto.response.UserResponse;
import com.example.Thanh.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")

// thằng này giúp chúng ta Map giữa controller vs lại đối tượng User
// thằng này sẽ nhận vào requesrt để trả ra user
public interface  UserMapper {


//     thằng này là 1 interface dùng để mapping UserCreationRequest thàng đối tượng user
    User toUser(UserCreationRequest request);



//    Đôi khi chúng ta map mà chúng không có cùng props với nhau
//   thì ta sẽ sử dụng mapping này

    // thằng này sẽ lấy firstName từ User để map cho gán cho giá trị lastName của UserDTO
// @Mapping(source = "firstName",target = "lastName")

// viết vầy là không cần phải trả firstName khi get về
//@Mapping(target = "firstName",ignore = true)
    UserResponse toUserResponse(User user);



    // có nghĩa là mình sẽ map cái request đó vào User
    void  updateUser(@MappingTarget User user, UserUpdateRequest request);
}
