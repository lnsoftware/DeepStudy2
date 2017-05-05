
package com.assistanz.mock.ws;

/**
 * The response from the REST API for Versions call.
 * @author Sujai SD <sujai@assistanz.com>
 */
public class VersionsResponse {
    
    /**
     * List of Version available in the server.
     */
    private Versions versions;
    
    /**
     * Version provided in the response.
     */
    private Version version;

    /**
     * Provides the versions available.
     * 
     * @return versions.
     */
    public Versions getVersions() {
        return versions;
    }

    /**
     * Set the versions.
     * 
     * @param versions new versions list.
     */
    public void setVersions(Versions versions) {
        this.versions = versions;
    }

    /**
     * Get the version.
     * 
     * @return version response.
     */
    public Version getVersion() {
        return version;
    }

    /**
     * Set the version.
     * 
     * @param version new version.
     */
    public void setVersion(Version version) {
        this.version = version;
    }
    
}
