package br.com.vitalis.cidalia.dto;

import jakarta.validation.constraints.NotBlank;

public record ConsultaAvancadaRequest(@NotBlank String comando) {
}
