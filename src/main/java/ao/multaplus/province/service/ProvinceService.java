package ao.multaplus.province.service;

import ao.multaplus.province.DTO.ProvincesDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProvinceService {
    ProvincesDTO create(ProvincesDTO dto);
    ProvincesDTO findById(Long id);
    List<ProvincesDTO> findAll();
    ProvincesDTO update(Long id, ProvincesDTO dto);
    void delete(Long id);
}
