package com.tui.proof.domain;

import lombok.Data;

@Data
public class Order {
  private String number;
  private Address deliveryAddress;
  private int pilotes;
  private double orderTotal;

}
