package MicroservicioCasas.infrastructure.mappers;


import MicroservicioCasas.domain.model.LocationModel;
import MicroservicioCasas.infrastructure.entities.LocationEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface LocationEntityMapper {
    LocationEntity modelToEntity(LocationModel locationModel);
    LocationModel entityToModel(LocationEntity categoryEntity);
    List<LocationModel> entityListToModelList(List<LocationEntity> locations);
}
