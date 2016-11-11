package com.accenture.portlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

/**
 * class FilesystemAccessPortlet: This portlet is used to provide filesystem
 * access.  Allows an administrator to grant access to users to access local
 * filesystem resources, useful in those cases where the user does not have
 * direct OS access.
 *
 * This portlet will provide access to download, upload, view, 'touch' and
 * edit files.
 * @author yadavjpr
 */

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=hello Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.name=" + HelloPortletKeys.HELLO,
	},
	service = Portlet.class
)
public class HelloPortlet extends MVCPortlet {
}