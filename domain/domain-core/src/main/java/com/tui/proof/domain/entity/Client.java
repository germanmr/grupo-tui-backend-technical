package com.tui.proof.domain.entity;

import com.tui.proof.domain.valueobject.ClientId;
import com.tui.proof.domain.valueobject.Name;
import com.tui.proof.domain.valueobject.Telephone;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public final class Client {
    private final ClientId clientId;
    private final Name firstName;
    private final Name lastName;
    private final Telephone telephone;
    private final Address deliveryAddress;
}
