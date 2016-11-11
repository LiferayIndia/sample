package com.accenture.portlet.config;

import com.accenture.portlet.HelloPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.PortletInstance;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
/**
 * class HelloDisplayContext: Wrapper for accessing the portlet instance configuration,
 * can be used in JSP and action phases to gain access to the instance configuration object.
 * @author yadavjpr
 */
@Component
public class HelloDisplayContext {
	/**
	 * HelloDisplayContext: Default constructor for our component.
	 */
	public HelloDisplayContext() {
		super();
	}

	/**
	 * HelloDisplayContext: Constructor
	 * @param req The http request object.
	 */
	public HelloDisplayContext(final HttpServletRequest req) {
		super();

		// get the theme display from the request.

		ThemeDisplay themeDisplay;

		themeDisplay = (ThemeDisplay)req.getAttribute(WebKeys.THEME_DISPLAY);

		// extract the portlet display instance from the theme display.

		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

		// if the id is blank...

		if (Validator.isNull(portletDisplay.getId())) {

			// during action phase it is likely the id will not be set.  This is
			// by design, these kinds of things are not guaranteed to be
			// properly set up for the action phase.

			//

			// when this is the case, we have to fall back to the configuration
			// provider entry point.

			// find our current portlet instance

			PortletInstance instance = PortletInstance.fromPortletInstanceKey(
				HelloPortletKeys.HELLO);
			Layout l = themeDisplay.getLayout();

			try {

				// get the config

				portletInstanceConfig =
					configurationProvider.getPortletInstanceConfiguration(
						HelloPortletInstanceConfiguration.class, l,
						instance);
			}
			catch (ConfigurationException ce) {
				logger.error("Error getting instance config.", ce);
			}
		}
		else {

			// we are in a render or resource phase where portlet display is
			// good.

			try {
				portletInstanceConfig =
					portletDisplay.getPortletInstanceConfiguration(
						HelloPortletInstanceConfiguration.class);
			}
			catch (ConfigurationException ce) {
				logger.error("Error getting portlet instance config.", ce);
			}
		}
	}

	/**
	 * HelloDisplayContext: Constructor that takes a portlet request object.
	 * @param req The portlet request object.
	 */
	public HelloDisplayContext(final PortletRequest req) {
		this(PortalUtil.getHttpServletRequest(req));
	}

	/**
	 * getHelloPortletInstanceConfiguration: Returns the instance config obj.
	 * @return HelloPortletInstanceConfiguration The instance config object to use.
	 */
	public HelloPortletInstanceConfiguration
		getHelloPortletInstanceConfiguration() {

		return portletInstanceConfig;
	}

	/**
	 * setConfigurationProvider: Sets the config provider to use.
	 * @param configurationProvider The config provider to use.
	 */
	@Reference
	protected void setConfigurationProvider(
		ConfigurationProvider configurationProvider) {

		this.configurationProvider = configurationProvider;
	}

	private static final Log logger = LogFactoryUtil.getLog(
		HelloDisplayContext.class);

	private ConfigurationProvider configurationProvider;
	private HelloPortletInstanceConfiguration portletInstanceConfig =
		null;

}