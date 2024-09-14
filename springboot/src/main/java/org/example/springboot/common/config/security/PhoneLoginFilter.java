//package org.example.springboot.common.config.security;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.example.springboot.common.Constants;
//import org.example.springboot.domain.model.LoginBody;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import java.io.IOException;
//
//public class PhoneLoginFilter extends AbstractAuthenticationProcessingFilter {
//    public PhoneLoginFilter() {
//        super(new AntPathRequestMatcher("/login/phone", "POST"));
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//        String contentType = request.getContentType();
//        if (!MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(contentType) && !Constants.APPLICATION_JSON_UTF8.equalsIgnoreCase(contentType)) {
//            throw new AuthenticationServiceException("不支持的请求方式：" + request.getMethod());
//        }
//        String username;
//        String password;
//        try {
//            LoginBody body = new ObjectMapper().readValue(request.getInputStream(), LoginBody.class);
//            username = body.getUsername();
//            username = (username != null) ? username.trim() : "";
//            password = body.getPassword();
//            password = (password != null) ? password : "";
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
////        PhoneCodeLoginAuthticationToken token = new PhoneCodeLoginAuthticationToken(username);
//
//        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username, password);
////        setDetails(request, authRequest);
//        return this.getAuthenticationManager().authenticate(authRequest);
//    }
//
//
//}
