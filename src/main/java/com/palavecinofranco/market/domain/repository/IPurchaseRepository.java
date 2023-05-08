package com.palavecinofranco.market.domain.repository;

import com.palavecinofranco.market.domain.dto.PurchaseDTO;

import java.util.List;
import java.util.Optional;

public interface IPurchaseRepository {
    List<PurchaseDTO> getAll();
    Optional<List<PurchaseDTO>> getByClient(Long clientId);
    PurchaseDTO save(PurchaseDTO purchase);
}
