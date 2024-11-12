package com.example.Thanh.service;


import com.example.Thanh.dto.request.UserCreationRequest;
import com.example.Thanh.dto.request.UserUpdateRequest;
import com.example.Thanh.dto.response.UserResponse;
import com.example.Thanh.entity.User;

import com.example.Thanh.exception.AppException;
import com.example.Thanh.exception.ErrorCode;

import com.example.Thanh.mapper.UserMapper;
import com.example.Thanh.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor // thằng này giúp  tạo coinsstructor từ các thuộc tính đã cung cấp ở trong
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // Bât cứ các file nào mình khai báo con trỏ thì nó chỉ
// mặc định là props đó sẽ là private finale
public class UserService {
//     thằng này giúp tiêm các file tương ứng vào trong các filed tương ứng
  UserRepository userRepository;


// Thằng này giúp để map giữa request vs lại user
   UserMapper userMapper;



        public User createUser(UserCreationRequest request) {
//          User user = new User();

            // check trùng nè
            if (userRepository.existsUserByUsername(request.getUsername())){
                // đây là nơi mình sẽ throw lỗi
                throw new AppException(ErrorCode.USER_EXISTED);

            }


                User user = userMapper.toUser(request);

//PasswordEncoder là 1 interface hỗ trợ mã hóa và encode
//            BCryptPasswordEncoder là 1 lớp sử dụng thuật toán truyền vào tham số độ phức tạo
//            => Tham số càng lớn thì độ phức tapj càng cao => Kèm theo thời gian cũng lâu
//            =>  từ 4 => 31

          PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    //       bắt đầu mã hóa và set
            user.setPassword(passwordEncoder.encode(request.getPassword()));

//                user.setUsername(request.getUsername());
//                user.setPassword(request.getPassword());
//                user.setFirstName(request.getFirstName());
//                user.setLastName(request.getLastName());
//                user.setDob(request.getDob());


//                 đoạn return này trả ra đối tượng đc lưu
                return userRepository.save(user); // thêm nè
        }

        public List<User> getAllUsers(){
            return userRepository.findAll();
        }


//         tìm dựa trên userId
//     mình sẽ không trả về User mình chỉ trả  ra UserResponse thôi, do là có thể chúng ta sẽ không trả hết
//     chỉ lấy 1 vài thuộc tính
        public UserResponse getUserById(String userId){
//
           return userMapper.toUserResponse(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found.")));


        }

//      Cập nhật user bằng Id
    public UserResponse updateUserById(String userId, UserUpdateRequest request){
//            User user = getUserById(userId);

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
              userMapper.updateUser(user, request);



//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());

//         chỗ này trả về đối tượng đã chỉnh sửa
        return userMapper.toUserResponse(userRepository.save(user)); // chỉnh sửa
    }


//     chỗ này chúng ta có thể trả về 1 cái list
    public void deleteUserById(String userId) {
        userRepository.deleteById(userId);
    }


}
