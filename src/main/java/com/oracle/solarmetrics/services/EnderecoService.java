package com.oracle.solarmetrics.services;


import com.oracle.solarmetrics.domains.Cidade;
import com.oracle.solarmetrics.domains.Endereco;
import com.oracle.solarmetrics.domains.Estado;
import com.oracle.solarmetrics.exceptions.ViaCepServiceException;
import com.oracle.solarmetrics.gateways.client.ViaCepClient;
import com.oracle.solarmetrics.gateways.dtos.clientsDto.ViaCepResponse;
import com.oracle.solarmetrics.gateways.repositories.CidadeRepository;
import com.oracle.solarmetrics.gateways.repositories.EnderecoRepository;
import com.oracle.solarmetrics.gateways.repositories.EstadoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService
{
    private final ViaCepClient viaCepClient;

    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;

    public Endereco cadastrarEndereco(
            Endereco endereco
    ) {

        ViaCepResponse viaCep;

        try {
            viaCep = viaCepClient.buscarCep(
                    endereco.getCep()
                );

        } catch (Exception e) {
            throw new ViaCepServiceException(
                    "Não foi possível consultar o CEP no momento."
            );
        }

        if (Boolean.TRUE.equals(viaCep.erro())) {
            throw new EntityNotFoundException("CEP não encontrado.");
        }

        Estado estado = estadoRepository
                .findByUf(viaCep.uf())
                .orElseGet(() -> {

                    Estado novoEstado =
                            Estado.builder()
                                    .uf(viaCep.uf())
                                    .nome(viaCep.estado())
                                    .build();

                    return estadoRepository.save(
                            novoEstado
                    );
                });

        Cidade cidade = cidadeRepository
                .findByNomeAndEstado(
                        viaCep.localidade(),
                        estado
                )
                .orElseGet(() -> {

                    Cidade novaCidade =
                            Cidade.builder()
                                    .nome(viaCep.localidade())
                                    .estado(estado)
                                    .build();

                    return cidadeRepository.save(
                            novaCidade
                    );
                });

        return Endereco.builder()
                .logradouro(viaCep.logradouro())
                .bairro(viaCep.bairro())
                .cep(viaCep.cep())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .cidade(cidade)
                .build();
    }
}
