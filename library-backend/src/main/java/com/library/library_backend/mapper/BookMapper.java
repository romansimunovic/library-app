package com.library.library_backend.mapper;

import com.library.library_backend.dto.BookRequest;
import com.library.library_backend.dto.BookResponse;
import com.library.library_backend.entity.BookEntity;
import org.mapstruct.*;

/**
 * MapStruct mapper for Book domain.
 *
 * Handles conversion between Entity and DTOs.
 */
@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "bookId", ignore = true)
    BookEntity toEntity(BookRequest request);

    BookResponse toResponse(BookEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(BookRequest request, @MappingTarget BookEntity entity);
}

