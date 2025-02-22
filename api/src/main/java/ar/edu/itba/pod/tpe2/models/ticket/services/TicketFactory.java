package ar.edu.itba.pod.tpe2.models.ticket.services;

import ar.edu.itba.pod.tpe2.models.City;
import ar.edu.itba.pod.tpe2.models.ticket.adapters.Ticket;
import ar.edu.itba.pod.tpe2.models.ticket.adapters.TicketCHI;
import ar.edu.itba.pod.tpe2.models.ticket.adapters.TicketNYC;
import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import lombok.Getter;

@Getter
public class TicketFactory implements DataSerializableFactory {

    public static final int FACTORY_ID = 1;

    @Override
    public IdentifiedDataSerializable create(int i) {
        switch (City.values()[i]) {
            case CHI -> new TicketCHI();
            case NYC -> new TicketNYC();
            default -> throw new IllegalArgumentException("Invalid type: " + i);
        }
        return null;
    }

}
