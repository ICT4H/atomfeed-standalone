package org.ict4h.atomfeed.client;

import org.ict4h.atomfeed.client.domain.Event;
import org.ict4h.atomfeed.client.service.AtomFeedClient;
import org.ict4h.atomfeed.client.service.EventWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URI;

@Controller
public class ClientProcessController {

    private static Logger logger = LoggerFactory.getLogger(ClientProcessController.class);

    private AtomFeedClient atomFeedClient;

    @Autowired
    public ClientProcessController(AtomFeedClient atomFeedClient) {
        this.atomFeedClient = atomFeedClient;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/clientprocessevents")
    public void processEvents(URI feedUri, String processType) {
        EventWorker eventWorker = (processType.equalsIgnoreCase("fail"))
                ? new SuccessEventWorker() : new FailEventWorker();

        atomFeedClient.processEvents(feedUri, eventWorker);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/clientprocessfailedevents")
    public void processFailedEvents(URI feedUri, String processType) {
        EventWorker eventWorker = (processType.equalsIgnoreCase("fail"))
                ? new SuccessEventWorker() : new FailEventWorker();

        atomFeedClient.processFailedEvents(feedUri, eventWorker);
    }

    class FailEventWorker implements EventWorker {
        @Override
        public void process(Event event) {
            logger.info("Failing Event" + event);
            throw new RuntimeException();
        }
    }

    class SuccessEventWorker implements EventWorker {
        @Override
        public void process(Event event) {
            logger.info("Successfully processing " + event);
        }
    }
}
