package user360.domain.ports.out;

import user360.domain.model.UserFilterModel;
import user360.domain.model.UserModel;
import user360.domain.utils.pagination.PagedResult;

import java.util.Optional;

public interface UserPersistencePort {
    UserModel saveUser(UserModel userModel);

    Optional<UserModel> getUserById(Long id);

    Optional<UserModel> getUserByEmail(String email);

    Optional<UserModel> getUserByDocumentId(String documentId);

    boolean existsByEmail(String email);

    boolean existsByDocumentId(String documentId);

    PagedResult<UserModel> getAllUsers(UserFilterModel filter);

    void deleteUser(Long id);

}