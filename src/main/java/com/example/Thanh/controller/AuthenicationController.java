package com.example.Thanh.controller;


import com.example.Thanh.dto.request.ApiResponse;
import com.example.Thanh.dto.request.AuthenicationRequest;
import com.example.Thanh.dto.request.IntrospectRequest;
import com.example.Thanh.dto.response.AuthenicationResponse;
import com.example.Thanh.dto.response.IntrospectResponse;
import com.example.Thanh.service.AuthenicationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class AuthenicationController {
    AuthenicationService authenicationService;

@PostMapping("/log-in")
    ApiResponse<AuthenicationResponse> authenticate(@RequestBody AuthenicationRequest request){
    var result =  authenicationService.authenicate(request);


//  AuthenicationResponse =>  boolean authenicated;
    return ApiResponse.<AuthenicationResponse>builder()
//             result này trong ApiResponse
            .result(result).build();
//   AuthenicationResponse =>

//                             còn result này  kết quả ở trên


}


    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> verifyToken(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result =  authenicationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder().result(result).build();

    }

}
