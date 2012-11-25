/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.user.internal;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.xwiki.component.annotation.Component;
import org.xwiki.configuration.ConfigurationSource;
import org.xwiki.user.RegistrationNotifierConfiguration;


@Component
public class DefaultRegistrationNotifierConfiguration implements RegistrationNotifierConfiguration
{
    /**
     * Common prefix for all registration notifier property keys.
     */
    private static final String KEY_PREFIX = "registrationNotifier.";

    /**
     * Default email template for the notification email.
     */
    private static final String DEFAULT_MAIL_TEMPLATE = "XWiki.RegistrationNotificationMail";

    @Inject
    private ConfigurationSource configurationSource;

    /**
     * {@inheritDoc}
     */
    public String[] getEmailAddressesToNotify()
    {
        String addressesAsString = this.configurationSource.getProperty(KEY_PREFIX + "emailAddresses", new String());
        if (StringUtils.isBlank(addressesAsString)) {
            return new String[0];
        }
        return addressesAsString.split(",");
    }

    /**
     * {@inheritDoc}
     */
    public String getEmailTemplateDocumentName()
    {
        return this.configurationSource.getProperty(KEY_PREFIX + "emailTemplate", DEFAULT_MAIL_TEMPLATE);
    }

}
