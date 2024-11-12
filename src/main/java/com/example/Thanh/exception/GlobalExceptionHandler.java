package com.example.Thanh.exception;


import com.example.Thanh.dto.request.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// thằng này dùng để bắt các exception
@ControllerAdvice
public class GlobalExceptionHandler {
    // tất cả lỗi ở khắp mọi nơi sẽ đổ về đây
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRuntimeExpection(RuntimeException exception){
        ApiResponse apiResponse = new ApiResponse();

        // trả về lỗi
        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());


        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppExpection(AppException exception){
       ErrorCode errorCode = exception.getErrorCode();

        ApiResponse apiResponse = new ApiResponse();

        // trả về lỗi
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());


        return ResponseEntity.badRequest().body( apiResponse);
    }


//     này là check đúng valid nè (password > 8)
        @ExceptionHandler(value = MethodArgumentNotValidException.class)
        ResponseEntity<ApiResponse> handlingRuntimeExpection(MethodArgumentNotValidException exception){

//         cái này là sau khi mình có cái enum rồi thì mình

            String enumKey = exception.getFieldError().getDefaultMessage(); // lấy message
            ErrorCode errorCode = ErrorCode.INVALID_KEY;

    try {

        errorCode = ErrorCode.valueOf(enumKey);
    } catch (IllegalArgumentException e) {
    }

//  n
            ApiResponse apiResponse = new ApiResponse();

            // trả về lỗi
            apiResponse.setCode(errorCode.getCode());
            apiResponse.setMessage(errorCode.getMessage());


            return ResponseEntity.badRequest().body(apiResponse);
        }




}
