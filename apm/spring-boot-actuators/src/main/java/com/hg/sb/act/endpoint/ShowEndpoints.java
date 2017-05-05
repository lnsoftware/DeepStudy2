package com.hg.sb.act.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowEndpoints extends AbstractEndpoint<List<Endpoint>> {
    private static final Logger LOG = LoggerFactory.getLogger(ShowEndpoints.class);

    private List<Endpoint> endpoints;

    @Autowired
    public ShowEndpoints(List<Endpoint> endpoints) {
        super("showEndpoints");
        this.endpoints = endpoints;
    }

    public List<Endpoint> invoke() {
        LOG.info("show all endpoints.");
        return this.endpoints;
    }
}