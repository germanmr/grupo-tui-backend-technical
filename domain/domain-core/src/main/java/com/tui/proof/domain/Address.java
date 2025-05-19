package com.tui.proof.domain;

import lombok.Data;

@Data
public class Address {
  private String street;
  private String postcode;
  private String city;
  private String country;
}
