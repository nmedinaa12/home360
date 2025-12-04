package user360.application.mappers;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import user360.application.dto.request.RegisterUserRequest;
import user360.application.dto.response.UserResponse;
import user360.domain.model.UserModel;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-03T22:30:36-0500",
    comments = "version: 1.6.3, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.3.jar, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class UserDtoMapperImpl implements UserDtoMapper {

    @Override
    public UserModel requestToModel(RegisterUserRequest request) {
        if ( request == null ) {
            return null;
        }

        UserModel userModel = new UserModel();

        userModel.setFirstName( request.firstName() );
        userModel.setLastName( request.lastName() );
        userModel.setDocumentId( request.documentId() );
        userModel.setPhoneNumber( request.phoneNumber() );
        if ( request.birthDate() != null ) {
            userModel.setBirthDate( LocalDateTime.ofInstant( request.birthDate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }
        userModel.setEmail( request.email() );
        userModel.setPassword( request.password() );

        return userModel;
    }

    @Override
    public UserResponse modelToResponse(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        Long id = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        String role = null;
        Boolean active = null;
        LocalDateTime createdAt = null;

        id = userModel.getId();
        firstName = userModel.getFirstName();
        lastName = userModel.getLastName();
        email = userModel.getEmail();
        role = userModel.getRole();
        active = userModel.getActive();
        if ( userModel.getCreatedAt() != null ) {
            createdAt = userModel.getCreatedAt().atStartOfDay();
        }

        UserResponse userResponse = new UserResponse( id, firstName, lastName, email, role, active, createdAt );

        return userResponse;
    }

    @Override
    public List<UserResponse> modelListToResponseList(List<UserModel> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( users.size() );
        for ( UserModel userModel : users ) {
            list.add( modelToResponse( userModel ) );
        }

        return list;
    }
}
