package MicroservicioCasas.infrastructure.adapters.persistence;


import MicroservicioCasas.domain.model.LocationModel;
import MicroservicioCasas.domain.ports.out.LocationPersistencePort;
import MicroservicioCasas.infrastructure.mappers.LocationEntityMapper;
import MicroservicioCasas.infrastructure.repositories.mysql.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class LocationPersistenceAdapter implements LocationPersistencePort {
    private final LocationRepository locationRepository;
    private final LocationEntityMapper locationEntityMapper;

    @Override
    public void save(LocationModel locationModel) {
        locationRepository.save(locationEntityMapper.modelToEntity(locationModel));
    }

    @Override
    public LocationModel findByDepartmentAndCity(String locationDepartment, String locationCity) {
        return locationEntityMapper.entityToModel(locationRepository.findByDepartmentAndCity(locationDepartment,locationCity).orElse(null));
    }

    @Override
    public List<LocationModel> searchByText(String searchText, Integer page, Integer size, boolean orderAsc) {
        Pageable pagination;
        if (orderAsc) pagination = PageRequest.of(page, size, Sort.by("department").ascending().and(Sort.by("city").ascending()));
        else pagination =  PageRequest.of(page, size, Sort.by("department").descending().and(Sort.by("city").descending()));

        String[] parts = searchText.split("[,\\s]+");
        String word1 = parts.length > 0 ? parts[0] : "";
        String word2 = parts.length > 1 ? parts[1] : "";

        return locationEntityMapper.entityListToModelList(
                locationRepository.searchByText(searchText, word1, word2, pagination).getContent()
        );
    }
}