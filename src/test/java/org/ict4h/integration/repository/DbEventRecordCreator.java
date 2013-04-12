package org.ict4h.integration.repository;

import org.ict4h.atomfeed.server.domain.EventRecord;
import org.ict4h.atomfeed.server.repository.AllEventRecords;
import org.ict4h.atomfeed.server.repository.EventRecordCreator;

import java.net.URI;
import java.net.URISyntaxException;

public class DbEventRecordCreator extends EventRecordCreator {

    public DbEventRecordCreator(AllEventRecords allEventRecords) {
        super(allEventRecords);
    }

    public EventRecord create(String uuid, String title, String url, String contents) throws URISyntaxException {
        EventRecord eventRecord = new EventRecord(uuid, title, new URI(url), contents, null, "");
        allEventRecords.add(eventRecord);
        return eventRecord;
    }
}
