package ao.multaplus.province.service;

import ao.multaplus.province.dtos.ProvincesRequestdtos;
import ao.multaplus.province.dtos.ProvincesResponsedtos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProvincesService {
    ProvincesResponsedtos create(ProvincesRequestdtos requestdtos);
    ProvincesResponsedtos update(Long id, ProvincesRequestdtos requestdtos);
    void delete(Long id);
    ProvincesResponsedtos findById(Long id);
    List<ProvincesResponsedtos> findAll();
    void migration();
}
