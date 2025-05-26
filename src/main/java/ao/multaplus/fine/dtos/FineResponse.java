package ao.multaplus.fine.dtos;

import java.time.LocalDateTime;

public record FineResponse(
        Long id,
        String description,
        LocalDateTime dateIssued
) {
}
