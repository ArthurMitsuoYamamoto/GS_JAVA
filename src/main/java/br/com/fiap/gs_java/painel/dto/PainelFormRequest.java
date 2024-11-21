package br.com.fiap.gs_java.painel.dto;

import java.math.BigDecimal;

public record PainelFormRequest(
        String nome,
        String localizacao,
        BigDecimal capacidadeProducao,
        Long idUsuario,
        char status
) { }
