package mk.ukim.finki.emt.librarymanagement.domain.model;

import lombok.NonNull;
import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class RentId extends DomainObjectId {

    private RentId() {
        super(RentId.randomId(RentId.class).getId());
    }

    public RentId(@NonNull String uuid) {
        super(uuid);
    }

}
