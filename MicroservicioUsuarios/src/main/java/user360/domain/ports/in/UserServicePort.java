package user360.domain.ports.in;

import user360.domain.model.UserFilterModel;
import user360.domain.model.UserModel;
import user360.domain.utils.pagination.PagedResult;

import java.util.Optional;

public interface UserServicePort {
    UserModel registerUser(UserModel userModel);


    PagedResult<UserModel> getAllUsers(UserFilterModel filter);

    Optional<UserModel> getUserById(Long id);

    Optional<UserModel> getUserByEmail(String email);

    Optional<UserModel> getUserByDocumentId(String documentId);

    boolean existsByEmail(String email);

    boolean existsByDocumentId(String documentId);

    boolean deleteUser(Long id);
}