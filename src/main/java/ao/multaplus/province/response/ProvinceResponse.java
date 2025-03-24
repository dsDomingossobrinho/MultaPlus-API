package ao.multaplus.province.response;

public record ProvinceResponse(
        Long id,
        String province,
        ao.multaplus.state.dtos.StateDto state
) {

}
