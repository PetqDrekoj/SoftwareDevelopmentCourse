package web.converter;

import core.domain.BaseEntity;
import web.dto.BaseDto;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by radu.
 */

public abstract class BaseConverter<Model extends BaseEntity<Integer>, Dto extends BaseDto>
        implements Converter<Model, Dto> {


    public List<Integer> convertModelsToIDs(List<Model> models) {
        return models.stream()
                .map(model -> model.getId())
                .collect(Collectors.toList());
    }

    public List<Integer> convertDTOsToIDs(List<Dto> dtos) {
        return dtos.stream()
                .map(dto -> dto.getId())
                .collect(Collectors.toList());
    }

    public List<Dto> convertModelsToDtos(Collection<Model> models) {
        return models.stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toList());
    }
}
