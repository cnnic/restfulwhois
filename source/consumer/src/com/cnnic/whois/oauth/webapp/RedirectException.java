package com.cnnic.whois.oauth.webapp;

import net.oauth.OAuthException;

/**
 * Indicates that the server should redirect the HTTP client.
 */
public class RedirectException extends OAuthException {

    public RedirectException(String targetURL) {
        super(targetURL);
    }

    public String getTargetURL() {
        return getMessage();
    }

    private static final long serialVersionUID = 1L;

}
