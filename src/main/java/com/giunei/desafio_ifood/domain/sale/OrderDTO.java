package com.giunei.desafio_ifood.domain.sale;

import java.time.Instant;

public record OrderDTO(SalesBag salesBag, Instant date) {
}
