package ao.multaplus.fine.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * A generic class to encapsulate paginated response data.
 *
 * @param <T> The type of data contained in the paginated response.
 */
public record PageDto<T>(
        List<T> data,
       @JsonProperty("current_page")
        int currentPage,
        @JsonProperty("page_size")
        int pageSize,
        @JsonProperty("total_pages")
        int totalPages,
        @JsonProperty("total_elements")
        long totalElements,
        @JsonProperty("sort_by")
        String sortBy,
        @JsonProperty("is_last")
        boolean isLast,
        @JsonProperty("is_first")
        boolean isFirst,
        @JsonProperty("has_next")
        boolean hasNext,
        @JsonProperty("has_previous")
        boolean hasPrevious) implements Serializable {
}
