package shiro.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.util.WebUtils;

import shiro.utils.StringUtils;

public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

    public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";

    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    public String getCaptchaParam() {
        return captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, getCaptchaParam());
    }

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = StringUtils.toUnicodeString(getUsername(request));
        String password = StringUtils.toUnicodeString(getPassword(request));
        if (password == null) {
            password = "";
        }
        boolean rememberMe = isRememberMe(request);
        String host = getHost(request);
        String captcha = StringUtils.toUnicodeString(getCaptcha(request));
        return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha);
    }

}