package ao.multaplus.province.service;

import ao.multaplus.province.DTO.ProvincesDTO;
import ao.multaplus.province.entity.Provinces;
import ao.multaplus.province.repository.ProvinceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProvinceServiceImpl implements ProvinceService {
    private final ProvinceRepository repository;

    public ProvinceServiceImpl(ProvinceRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProvincesDTO create(ProvincesDTO dto) {
        Provinces province = new Provinces();
        province.setProvince(dto.province());
        return toDto(repository.save(province));
    }

    @Override
    public ProvincesDTO findById(Long id) {
        Provinces province = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Province not found"));
        return toDto(province);
    }

    @Override
    public List<ProvincesDTO> findAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public ProvincesDTO update(Long id, ProvincesDTO dto) {
        Provinces province = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Province not found"));
        province.setProvince(dto.province());
        return toDto(repository.save(province));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ProvincesDTO toDto(Provinces province) {
        return new ProvincesDTO(province.getId(), province.getProvince());
    }
}
