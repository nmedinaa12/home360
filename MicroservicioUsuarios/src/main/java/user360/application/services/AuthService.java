package user360.application.services;


import com.nimbusds.jose.JOSEException;
import user360.application.dto.request.SignUpRequest;
import user360.application.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse signIn(String email, String password) throws JOSEException;

    void signUp(SignUpRequest request);
}
