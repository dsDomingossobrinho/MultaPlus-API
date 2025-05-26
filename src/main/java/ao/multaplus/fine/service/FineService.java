package ao.multaplus.fine.service;

import ao.multaplus.fine.dtos.AddFineDto;
import ao.multaplus.fine.dtos.FineResponse;
import ao.multaplus.fine.dtos.FineResponseDto;
import ao.multaplus.fine.dtos.PageDto;

public interface FineService {
    void AddFine(AddFineDto fine);
    void UpdateFine(AddFineDto fine);
    FineResponseDto getFineDetails(Long fineIdentifier);
    void DeleteFine(Long id);
    PageDto<FineResponse> getFines(String vehiclePlante, int pageNumber, int pageSize);

}
