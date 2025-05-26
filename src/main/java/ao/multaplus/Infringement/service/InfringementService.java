package ao.multaplus.Infringement.service;


import ao.multaplus.Infringement.dtos.InfringementsUpdateDto;
import ao.multaplus.Infringement.entity.Infringements;
import ao.multaplus.Infringement.response.TypeInfringementsResponse;

import java.util.List;

public interface InfringementService {
    TypeInfringementsResponse create(InfringementsUpdateDto InfringementsDetails);
    TypeInfringementsResponse findById(Long id);
    List<TypeInfringementsResponse> findAll();
    TypeInfringementsResponse update(Long id, InfringementsUpdateDto InfringementsDetails);
    void delete(Long id);
    void migration();
    Float getPrice(Long id);
    Infringements getInfringement(String InfringementName);
}
