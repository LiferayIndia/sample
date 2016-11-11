package com.accenture.portlet.config;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import aQute.bnd.annotation.metatype.Meta;

/**
 * class FilesystemAccessPortletInstanceConfiguration: Instance configuration for
 * the portlet configuration.
 * @author yadavjpr
 */
@ExtendedObjectClassDefinition(
	category = "Platform",
	scope = ExtendedObjectClassDefinition.Scope.PORTLET_INSTANCE
)
@Meta.OCD(
	localization = "content/Language",
	name = "HelloPortlet.portlet.instance.configuration.name",
	id = "com.accenture.portlet.config.HelloPortletInstanceConfiguration"
)
public interface HelloPortletInstanceConfiguration {
	/**
	 * rootPath: This is the root path that constrains all filesystem access.
	 */
	@Meta.AD(deflt = "${LIFERAY_HOME}", required = false)
	public String rootPath();

	/**
	 * downloadsAllowed: This is a flag that determines whether file/folder downloads are allowed or not.
	 */
	@Meta.AD(deflt = "true", required = false)
	public boolean downloadsAllowed();


	/**
	 * viewSizeLimit: This is a size limit that determines whether a file can be viewed in the browser.
	 */
	@Meta.AD(deflt = "-1", required = false)
	public long viewSizeLimit();

}
