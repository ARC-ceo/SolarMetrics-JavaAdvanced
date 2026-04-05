package com.oracle.solarmetrics.services;

import com.oracle.solarmetrics.domains.*;
import com.oracle.solarmetrics.gateways.repositories.PainelSolarRepository;
import com.oracle.solarmetrics.gateways.repositories.SistemaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PainelSolarService
{

    private final PainelSolarRepository painelSolarRepository;
    private final SistemaRepository sistemaRepository;

    public PainelSolar create(PainelSolar painelSolar) {
        getSistema(painelSolar.getSistema().getId());
        return painelSolarRepository.save(painelSolar);
    }

    public PainelSolar update(PainelSolar painelSolar) {
        getId(painelSolar.getId());
        getSistema(painelSolar.getSistema().getId());
        return painelSolarRepository.save(painelSolar);
    }

    public PainelSolar getId(String id) {
        Optional<PainelSolar> painelSolar = painelSolarRepository.findById(id);
        if (painelSolar.isEmpty()) {
            throw new EntityNotFoundException("Painel Solar não encontrado.");
        }
        return painelSolar.get();
    }

    public void getSistema(String id) {
        Optional<Sistema> sistema = sistemaRepository.findById(id);
        if (sistema.isEmpty()) {
            throw new EntityNotFoundException("Sistema não encontrado.");
        }
    }

    public List<PainelSolar> getPainelSolarSistema(String sistemaId) {
        getSistema(sistemaId);
        return painelSolarRepository.findBySistema_Id(sistemaId);
    }

    public List<PainelSolar> getAll() {
        return painelSolarRepository.findAll();
    }

    public void delete(String id) {
        getId(id);
        painelSolarRepository.deleteById(id);
    }

}
