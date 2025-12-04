package user360.infrastructure.mappers;

import user360.domain.model.UserModel;
import user360.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntity modelToEntity(UserModel userModel);


    UserModel entityToModel(UserEntity userEntity);

    List<UserModel> entityListToModelList(List<UserEntity> users);

    List<UserEntity> modelListToEntityList(List<UserModel> users);
}
