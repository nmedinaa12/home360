package user360.application.services;

import user360.application.dto.request.RegisterUserRequest;
import user360.application.dto.request.filters.UserFilterRequest;
import user360.application.dto.response.PaginatedResponse;
import user360.application.dto.response.UserResponse;
import user360.domain.model.UserModel;

import java.util.Optional;

public interface UserService {

    UserResponse getUserById(Long id);

    Optional<UserModel> getUserByEmail(String email);

    UserResponse saveUser(RegisterUserRequest request);

    UserResponse updateUser(Long id, RegisterUserRequest request);

    void deleteUser(Long id);

    PaginatedResponse<UserResponse> getAllUsers(UserFilterRequest filter);
}