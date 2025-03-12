package ao.multaplus.typeInfringement.service;

import ao.multaplus.typeInfringement.DTO.TypeInfringementsDTO;

import java.util.List;

public interface TypeInfrigimentService {
    TypeInfringementsDTO create(TypeInfringementsDTO dto);
    TypeInfringementsDTO findById(Long id);
    List<TypeInfringementsDTO> findAll();
    TypeInfringementsDTO update(Long id, TypeInfringementsDTO dto);
    void delete(Long id);
    void migration();
}
