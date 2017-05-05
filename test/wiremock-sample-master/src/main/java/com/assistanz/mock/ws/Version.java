package com.assistanz.mock.ws;

import java.util.Date;

/**
 * The version of the REST API.
 * 
 * @author Sujai SD <sujai@assistanz.com>
 */
public class Version {
    
    /**
     * Identifier for the version.
     */
    private String id;
    
    /**
     * Status of the version. 
     * Is the version supported or not.
     */
    private String status;
    
    /**
     * Last updated date.
     */
    private Date updated;

    /**
     * Provides the id.
     * 
     * @return id of the version.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id.
     * 
     * @param id unique identifier.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Provides the status.
     * 
     * @return Status of the verison.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status.
     * 
     * @param status new status.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Finds the last updated date.
     * 
     * @return date of update.
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * Set the updated date.
     * 
     * @param updated date of update.
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
