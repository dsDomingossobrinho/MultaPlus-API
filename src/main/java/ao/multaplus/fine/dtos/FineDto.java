package ao.multaplus.fine.dtos;

import ao.multaplus.infringement.dtos.InfringementSenderDto;

import java.util.List;

public record FineDto(
        Long userId,
        String MotoristBi,
        String matricula,
        List<InfringementSenderDto> infringement
) {
}
