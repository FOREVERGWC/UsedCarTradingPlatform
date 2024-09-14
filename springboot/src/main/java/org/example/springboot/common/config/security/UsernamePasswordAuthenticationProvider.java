package org.example.springboot.common.config.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Component
public class UsernamePasswordAuthenticationProvider extends DaoAuthenticationProvider {
    private PasswordEncoder passwordEncoder;

    public UsernamePasswordAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        super(passwordEncoder);
        this.passwordEncoder = passwordEncoder;
        setUserDetailsService(userDetailsService);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
//        validateCaptcha(username, captcha, uuid); // 校验验证码
//        loginPreCheck(username, password); // 密码前置校验
//        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String code = req.getParameter("code");
//        String verify_code = (String) req.getSession().getAttribute("verify_code");
//        if (code == null || verify_code == null || !code.equals(verify_code)) {
//            throw new AuthenticationServiceException("验证码错误");
//        }
//        LoginBody body = (LoginBody) authentication.getCredentials();
        // TODO 密码校验
        // TODO 用户状态校验
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());
//        LoginUser loginUser = (LoginUser) this.getUserDetailsService().loadUserByUsername(body.getUsername());
        super.additionalAuthenticationChecks(userDetails, authentication);
        System.out.println("校验验证码");
    }
}
