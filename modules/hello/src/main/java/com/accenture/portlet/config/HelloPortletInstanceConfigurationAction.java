package com.accenture.portlet.config;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.accenture.portlet.HelloPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * class FilesystemAccessConfigurationAction: Configuration action for the filesystem access portlet.
 * @author yadavjpr
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + HelloPortletKeys.HELLO
	},
	service = ConfigurationAction.class
)
public class HelloPortletInstanceConfigurationAction extends DefaultConfigurationAction {
	/**
	 * getJspPath: Return the path to our configuration jsp file.
	 * @param request The servlet request.
	 * @return String The path
	 */
	@Override
	public String getJspPath(HttpServletRequest request) {
		return "/configuration.jsp";
	}

	/**
	 * processAction: This is used to process the configuration form submission.
	 * @param portletConfig The portlet configuration.
	 * @param actionRequest The action request.
	 * @param actionResponse The action response.
	 * @throws Exception in case of error.
	 */
	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		// extract all of the parameters from the request.

		String rootPath = ParamUtil.getString(actionRequest, "rootPath");
		
		String downloadsAllowed = ParamUtil.getString(
			actionRequest, "downloadsAllowed");
		String editsAllowed = ParamUtil.getString(
			actionRequest, "editsAllowed");
		String viewSizeLimit = ParamUtil.getString(
			actionRequest, "viewSizeLimit");
		// lets log them for fun and giggles

		if (_log.isDebugEnabled()) {
			_log.debug("Configuration setting:");
			_log.debug("  rootPath = [" + rootPath + "]");
			_log.debug("  editsAllowed = " + editsAllowed);
			_log.debug("  viewSizeLimit = " + viewSizeLimit);
		}

		// Set the preference values

		setPreference(actionRequest, "rootPath", rootPath);
		setPreference(actionRequest, "editsAllowed", editsAllowed);
		setPreference(actionRequest, "viewSizeLimit", viewSizeLimit);
		setPreference(actionRequest, "downloadsAllowed", downloadsAllowed);

		// fall through to the super action for the rest of the handling

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	/**
	 * setServletContext: Sets the servlet context, use your portlet's bnd.bnd Bundle-SymbolicName value.
	 * @param servletContext The servlet context to use.
	 */
	@Override
	@Reference(
		target = "(osgi.web.symbolicname=hello)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		HelloPortletInstanceConfigurationAction.class);

}
