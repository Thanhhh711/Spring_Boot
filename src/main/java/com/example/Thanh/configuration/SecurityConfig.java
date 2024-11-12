    package com.example.Thanh.configuration;



    /*
    *   Đây là nơi sẽ check xem là những request nào hợp lệ thì cho qua
    *  Thì khi mà 1 cái class mà có Configuration thì nó sẽ tự động
    *   được khởi tạo khi mà
    * */


    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
    import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
    import org.springframework.security.oauth2.jwt.JwtDecoder;
    import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
    import org.springframework.security.web.SecurityFilterChain;

    import javax.crypto.spec.SecretKeySpec;

    @Configuration


    //https://docs.spring.io/spring-security/reference/servlet/architecture.html
    // Đoạn code lấy ở đây


    public class SecurityConfig {


//      Đây là các public end point mà chúng ta muốn người dùng có thể truy cập

//         path user phải cấu hình như vầy
//         nếu mà để user không thì đấy là đường dẫn gốc
//         còn /** là đường dẫn con
            private final String[] PUBLIC_ENDPOINTS = {"/users/**","/auth/log-in","auth/introspect"};
        @Value("${spring.jpa.jwt.signerKey}")
        protected  String SIGNER_KEY;






        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity ) throws Exception {

            System.out.println(HttpMethod.POST);


            httpSecurity.csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(request ->
                            request.requestMatchers(HttpMethod.POST,PUBLIC_ENDPOINTS)
                                    .permitAll().anyRequest().authenticated());
//
                httpSecurity.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(jwtConfigurer
                        -> jwtConfigurer.decoder(jwtDecoder()))) ;




            return httpSecurity.build();
        }


        @Bean
        JwtDecoder jwtDecoder() {
//            ow

            SecretKeySpec secretKeySpec = new SecretKeySpec(SIGNER_KEY.getBytes(), "HS256");

            return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS256).build();
        }
    }