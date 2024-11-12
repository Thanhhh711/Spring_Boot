package com.example.Thanh.controller;

import com.example.Thanh.dto.request.ApiResponse;
import com.example.Thanh.dto.request.UserCreationRequest;
import com.example.Thanh.dto.request.UserUpdateRequest;
import com.example.Thanh.dto.response.UserResponse;
import com.example.Thanh.entity.User;
import com.example.Thanh.repository.UserRepository;
import com.example.Thanh.service.UserService;
import jakarta.persistence.Access;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
 @RequestMapping("/users") // thấy ở dưới users được lập đi lặp lại nhiều nên là mình đem lên đây, khỏi phải gọi nữa
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true )

public class UserController {
   @Autowired
  UserService userService;


  @PostMapping("/users")
//    @PostMapping()
//    Valid này sẽ giúp chúng ta valid theo các rules mà chúng ta đã cung cấp
    ApiResponse<User> createUser(@RequestBody @Valid  UserCreationRequest request) {

        ApiResponse<User> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.createUser(request));

        return apiResponse;
    }

 @GetMapping("/users")
  //  @GetMapping()
    List<User> getUsers() {
                    return userService.getAllUsers();
    }


    @GetMapping("/{userId}")
//    @PathVariable("userId") chỗ này là mình khải báo tường minh là lấy
    // trên Params đem xuống làm props
    UserResponse getUserById(@PathVariable("userId") String userId) {
        return userService.getUserById(userId);
    }


    @PutMapping("/{userId}")
    UserResponse updateUserById(@PathVariable  String userId, @RequestBody UserUpdateRequest request){
        return userService.updateUserById(userId, request);
    }


    @DeleteMapping("/{userId}")
    String deleteUserById(@PathVariable("userId") String userId){
        userService.deleteUserById(userId);
        return "User deleted";
    }

}
