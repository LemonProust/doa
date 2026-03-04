package pt.ipp.estg.doa.store.model.dto.request;

import java.math.BigDecimal;

public record CreateManagerRequest(
        String name,
        String nif,
        String email,
        String phone,
        String address,
        BigDecimal salary
) {
}
