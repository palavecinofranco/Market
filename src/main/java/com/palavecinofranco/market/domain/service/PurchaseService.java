package com.palavecinofranco.market.domain.service;

import com.palavecinofranco.market.domain.dto.PurchaseDTO;
import com.palavecinofranco.market.domain.repository.IPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private IPurchaseRepository purchaseRepository;

    public List<PurchaseDTO> getAll(){
        return purchaseRepository.getAll();
    }

    public Optional<List<PurchaseDTO>> getByClient(Long clientId){
        return purchaseRepository.getByClient(clientId);
    }

    public PurchaseDTO save(PurchaseDTO purchase){
        return purchaseRepository.save(purchase);
    }

}
