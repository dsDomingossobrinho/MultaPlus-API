package ao.multaplus.typeInfringement.service;


import ao.multaplus.typeInfringement.dtos.TypeInfringementsUpdateDto;
import ao.multaplus.typeInfringement.response.TypeInfringementsResponse;

import java.util.List;

public interface TypeInfrigimentService {
    TypeInfringementsResponse create(TypeInfringementsUpdateDto requestdtos);
    TypeInfringementsResponse findById(Long id);
    List<TypeInfringementsResponse> findAll();
    TypeInfringementsResponse update(Long id, TypeInfringementsUpdateDto requestdtos);
    void delete(Long id);
    void migration();
}
