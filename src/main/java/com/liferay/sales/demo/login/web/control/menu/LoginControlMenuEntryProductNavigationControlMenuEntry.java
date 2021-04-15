package com.liferay.sales.demo.login.web.control.menu;

import com.liferay.lfrobotics.demologin.portlet.configuration.DemoLoginConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.product.navigation.control.menu.BaseJSPProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.ProductNavigationControlMenuEntry;
import com.liferay.product.navigation.control.menu.constants.ProductNavigationControlMenuCategoryKeys;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author olaf
 */
@Component(
	immediate = true,
	property = {
		"product.navigation.control.menu.category.key=" + ProductNavigationControlMenuCategoryKeys.USER,
		"product.navigation.control.menu.entry.order:Integer=10"
	},
	service = ProductNavigationControlMenuEntry.class
)
public class LoginControlMenuEntryProductNavigationControlMenuEntry
	extends BaseJSPProductNavigationControlMenuEntry
	implements ProductNavigationControlMenuEntry {

	@Override
	public String getIconJspPath() {
		return "/login.jsp";
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "open-demo-login-dialog");
	}

	@Override
	public boolean isShow(HttpServletRequest request) throws PortalException {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		return themeDisplay.isSignedIn()
				&& _demoLoginConfiguration != null 
				&& _demoLoginConfiguration.demoUsers() != null 
				&& _demoLoginConfiguration.demoUsers().length > 0;
	}

	@Override
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.lfrobotics.demologin)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		super.setServletContext(servletContext);
	}
	
	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		_demoLoginConfiguration = ConfigurableUtil.createConfigurable(DemoLoginConfiguration.class, properties);
	}

	@Reference
	protected void setConfigurationProvider(ConfigurationProvider configurationProvider) {
		// configuration update will actually be handled in the @Modified event,
		// which will only be triggered in case we have a @Reference to the
		// ConfigurationProvider
	}

	private static final Log _log = LogFactoryUtil.getLog(LoginControlMenuEntryProductNavigationControlMenuEntry.class);

	private volatile DemoLoginConfiguration _demoLoginConfiguration;
}