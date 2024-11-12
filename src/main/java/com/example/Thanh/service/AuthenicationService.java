package com.example.Thanh.service;


import com.example.Thanh.dto.request.AuthenicationRequest;
import com.example.Thanh.dto.request.IntrospectRequest;
import com.example.Thanh.dto.response.AuthenicationResponse;
import com.example.Thanh.dto.response.IntrospectResponse;
import com.example.Thanh.exception.AppException;
import com.example.Thanh.exception.ErrorCode;
import com.example.Thanh.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true ) // Đặt quyền truy cập cho những thuộc tinhs là private
public class AuthenicationService {

        UserRepository userRepository;



//        Mình sẽ dùng thằng này để verify cái key của mình
@NonFinal//         Mình sẽ dùng thằng anotation này để không set nó vào constructor
@Value("${spring.jpa.jwt.signerKey}")
        protected  String SIGNER_KEY;
        /*
AuthenicationRequest chứa
*   String userName;
    String password;

*
* */


//     hàm này là hàm verify ngược lại cái token của chúng ta
    public IntrospectResponse introspect(IntrospectRequest request)
                            throws JOSEException, ParseException {

//         lấy token cuống
                    var token = request.getToken();



//                     cung cấp signNature_Key
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());


//             mã hóa ngược lại
        SignedJWT signedSWT = SignedJWT.parse(token);

// check thời gian hết hạn
        Date expityTime =  signedSWT.getJWTClaimsSet().getExpirationTime();


        var verified = signedSWT.verify(verifier);


//         Có nghĩa là thời gian hết hạn nó phải sau thời điểm hiện tại

    return
           IntrospectResponse.builder().valid(verified && expityTime.after(new Date())).build();

    }


  public AuthenicationResponse authenicate(AuthenicationRequest request){

//         tìm uaser
            var user = userRepository.findUserByUsername((request.getUserName()).describeConstable().orElseThrow(() -> new RuntimeException("User not found.")));

      System.out.println(user);


           PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);


//             CHỖ NÀY coi8 lại nè
//       Tại sao chỗ này chúng ta không cần phải encode getPassword và vẫn có thể so sánh với kêts quả
//     được lấy từ user dưới DB, là do passwordEncoder khi sử dụng matches đã tự động encode sẵn rồi
//       nên là mình chỉ cần truyền mật khẩu nguyên thủy thôi.
    boolean authenticated =   passwordEncoder.matches(request.getPassword(), user.get().getPassword());
            if(!authenticated) throw new AppException(ErrorCode.UNAUTHENTICATED);


      String token;
      token = generateToken(request.getUserName());


      System.out.println("token"+ token);
      return AuthenicationResponse.builder().token(token).authenicated(true).build();

    }


    private String generateToken (String username){


//       Cái này dùng để sử dụng thuật toán nào
        JWSHeader header =  new JWSHeader(JWSAlgorithm.HS256);



//         Tạo các đối tượng chứa thông tin chính của 1 JWT token
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(username) // người sử dụng
                .issuer("devteria.com") // nhà phát hành token này
                .issueTime(new Date()) // thời điểm phát hành
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli())) // thời gian hết hạn
                .claim("userId", "Custom")
                .build(); // xây


//         payload cái mà ta sẽ đưa cho người dùng cụ thể là jwt
        Payload payload  = new Payload(jwtClaimsSet.toJSONObject());

//         cấu hinh jws
        JWSObject jwsObject = new JWSObject(header, payload);


        try{


//             ký token
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));



            return jwsObject.serialize(); // theo kiểu string

        } catch(JOSEException e){
            System.out.println("Cannot sign token"+ e);
            throw new RuntimeException(e);
        }


    }
}
