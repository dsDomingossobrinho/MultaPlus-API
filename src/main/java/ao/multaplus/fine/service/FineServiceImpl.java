package ao.multaplus.fine.service;

import ao.multaplus.Infringement.dtos.InfrigementNameAndAIDto;
import ao.multaplus.Infringement.dtos.TypeInfringementsDto;
import ao.multaplus.Infringement.entity.Infringements;
import ao.multaplus.Infringement.service.InfringementService;
import ao.multaplus.Infringement.service.InfringementServiceImpl;
import ao.multaplus.auth.service.AuthServiceImpl;
import ao.multaplus.exception.model.ResourceNotFound;
import ao.multaplus.fine.dtos.*;
import ao.multaplus.fine.entity.Fines;
import ao.multaplus.fine.repository.FineRepository;
import ao.multaplus.motorist.entity.Motorists;
import ao.multaplus.motorist.service.MotoristServiceImpl;
import ao.multaplus.payment.service.PaymentServiceImpl;
import ao.multaplus.typeVehicle.dtos.VehiclesTypeDto;
import ao.multaplus.vehicle.dtos.VehicleResponseDto;
import ao.multaplus.vehicle.entity.Vehicles;
import ao.multaplus.vehicle.service.VehicleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class FineServiceImpl implements FineService {
    private final FineRepository fineRepository;
    private final MotoristServiceImpl motoristService;
    private final VehicleServiceImpl vehicleService;
    private final AuthServiceImpl authService;
    private final PaymentServiceImpl paymentService;
    private final InfringementServiceImpl infringementServiceImpl;
    private final InfringementService infringementService;

    @Override
    public FineResponseDto AddFine(AddFineDto fine) {
        Motorists motorists = motoristService.getMotorist(fine.motoristIdentifier());
        Fines.FinesBuilder<?, ?> fines = Fines.builder()
                .infringements(toInfringements(fine))
                .motorists(motorists)
                .description(fine.description())
                .daysTOPay(fine.daysTOPay())
                .users(authService.currentUser());
        if (fine.vehicleDetails() != null)
            fines.vehicles(vehicleService.GetOrRegistVehicle(fine.vehicleDetails()));

        Fines finesSaved = fineRepository.save(fines.build());

        return new FineResponseDto(
                finesSaved.getId(),
                finesSaved.getDescription(),
                finesSaved.getCreatedAt(),
                finesSaved.getDaysTOPay(),
                new NameAndIdDto(finesSaved.getMotorists().getId(),
                        finesSaved.getMotorists().getName()),
                new NameAndIdDto(finesSaved.getUsers().getId(),
                        finesSaved.getUsers().getName()),
                vehicleResponseDto(finesSaved.getVehicles()),
                toInfreimentDto(finesSaved.getInfringements()),
                paymentService.generatereference()
        );
    }

    @Override
    public void UpdateFine(AddFineDto fine) {

    }

    @Override
    public FineResponseDto getFineDetails(Long fineIdentifier) {
        Fines fine = fineRepository.getFineDetails(fineIdentifier).orElseThrow(
                () -> new ResourceNotFound("fine with %d  Not found".formatted(fineIdentifier)));
        return new FineResponseDto(
                fine.getId(),
                fine.getDescription(),
                fine.getCreatedAt(),
                fine.getDaysTOPay(),
                new NameAndIdDto(fine.getMotorists().getId(),
                        fine.getMotorists().getName()),
                new NameAndIdDto(fine.getUsers().getId(),
                        fine.getUsers().getName()),
                vehicleResponseDto(fine.getVehicles()),
                toInfreimentDto(fine.getInfringements()),
                paymentService.generatereference()
                );
    }

    @Override
    public void DeleteFine(Long id) {

    }

    @Override
    public PageDto<FineResponse> getFines(String vehiclePlante, int pageNumber,
                                          int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<FineResponse> fines = fineRepository.GetFines(
                vehiclePlante, pageRequest);
        return new PageDto<>(fines.getContent(),
                fines.getNumber(),
                fines.getSize(),
                fines.getTotalPages(),
                fines.getTotalPages(), fines.getSort().toString(), fines.isLast(),
                fines.isFirst(), fines.hasNext(), fines.hasPrevious());
    }

    private List<Infringements> toInfringements(AddFineDto fine) {
        List<Long> infringementIds = fine.infringements()
                .stream()
                .map(InfrigementNameAndAIDto::InfringementId)
                .toList();
                return infringementServiceImpl.infringementsById(infringementIds);
    }


    private List<TypeInfringementsDto> toInfreimentDto(
            List<Infringements> infringements) {
        return infringements.stream().map(
                infringement -> new TypeInfringementsDto(infringement.getId(),
                        infringement.getName(),
                        infringement.getDescription(), infringement.getPrice())).toList();
    }

    VehicleResponseDto vehicleResponseDto(Vehicles vehicles) {
        VehicleResponseDto response = null;
        if (vehicles != null) {
            response = new VehicleResponseDto(vehicles.getPlateNumber(),
                    vehicles.getColor(), vehicles.getBrand(),
                    new VehiclesTypeDto(vehicles.getTypeVehicles().getType(),
                            vehicles.getTypeVehicles().getDescription()));
        }
        return response;
    }

}
