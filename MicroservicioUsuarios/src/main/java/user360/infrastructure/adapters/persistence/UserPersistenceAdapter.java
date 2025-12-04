package user360.infrastructure.adapters.persistence;

import user360.domain.model.UserFilterModel;
import user360.domain.model.UserModel;
import user360.domain.ports.out.UserPersistencePort;
import user360.domain.utils.pagination.PagedResult;
import user360.domain.utils.pagination.PaginationUtils;
import user360.infrastructure.entities.UserEntity;
import user360.infrastructure.mappers.UserEntityMapper;
import user360.infrastructure.repositories.mysql.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public UserModel saveUser(UserModel userModel) {
        UserEntity userEntity = userEntityMapper.modelToEntity(userModel);
        UserEntity savedUser = userRepository.save(userEntity);
        return userEntityMapper.entityToModel(savedUser);
    }


    @Override
    public PagedResult<UserModel> getAllUsers(UserFilterModel filter) {

        var pageable = PaginationUtils.createPageable(
                filter.page(),
                filter.size(),
                filter.sortField(),
                filter.direction()
        );

        var page = userRepository.findAll(pageable);
        return PaginationUtils.toPagedResult(page.map(userEntityMapper::entityToModel));
    }

    @Override
    public Optional<UserModel> getUserById(Long id) {
        return userRepository.findById(id).map(userEntityMapper::entityToModel);
    }

    @Override
    public Optional<UserModel> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(userEntityMapper::entityToModel);
    }

    @Override
    public Optional<UserModel> getUserByDocumentId(String documentId) {
        return userRepository.findByDocumentId(documentId).map(userEntityMapper::entityToModel);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByDocumentId(String documentId) {
        return userRepository.existsByDocumentId(documentId);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}