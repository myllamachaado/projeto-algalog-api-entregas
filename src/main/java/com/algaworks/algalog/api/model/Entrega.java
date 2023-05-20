package com.algaworks.algalog.api.model;

import com.algaworks.algalog.api.exceptionHandler.exceptions.NegocioException;
import com.algaworks.algalog.api.validations.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    @NotNull
    @ManyToOne
    private Cliente cliente;

    @Valid
    @NotNull
    @Embedded
    private Destinatario destinatario;

    private BigDecimal taxa;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime data_finalizacao;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    List<Ocorrencia> ocorrencias = new ArrayList<>();

    public Ocorrencia adicionaOcorrencia(String descricao){
        var ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);
        this.getOcorrencias().add(ocorrencia);
        return ocorrencia;
    }

    public void finalizar(){
        if(!this.entregaPodeSerFinalizada()){
            throw new NegocioException("Entrega não pode ser finalizada!");
        }
        setStatus(StatusEntrega.FINALIZADA);
        setData_finalizacao(OffsetDateTime.now());
    }

    public boolean entregaPodeSerFinalizada(){
        return StatusEntrega.PENDENTE.equals(getStatus());
    }


}
