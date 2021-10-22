package mk.ukim.finki.emt.librarymanagement.domain.model;

import mk.ukim.finki.emt.sharedkernel.domain.base.DomainObjectId;

public class BookSampleId extends DomainObjectId {
    private BookSampleId() {
        super(BookSampleId.randomId(BookSampleId.class).getId());
    }

    public BookSampleId(String uuid) {
        super(uuid);
    }

}
