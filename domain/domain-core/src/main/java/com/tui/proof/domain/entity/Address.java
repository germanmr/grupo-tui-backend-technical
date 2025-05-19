package com.tui.proof.domain.entity;

import com.tui.proof.domain.valueobject.City;
import com.tui.proof.domain.valueobject.Country;
import com.tui.proof.domain.valueobject.PostalCode;
import com.tui.proof.domain.valueobject.Street;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class Address {
  private final Street street;
  private final PostalCode postcode;
  private final City city;
  private final Country country;
}
