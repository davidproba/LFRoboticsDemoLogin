/**
 * Copyright 2000-present Liferay, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liferay.lfrobotics.demologin.portlet;

import com.liferay.lfrobotics.demologin.constants.DemoLoginPortletKeys;
import com.liferay.lfrobotics.demologin.portlet.configuration.DemoLoginConfiguration;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author David Proba, Olaf Kock
 */
@Component(
	configurationPid = "com.liferay.lfrobotics.demologin.portlet.configuration.DemoLoginConfiguration",
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=false",
		"com.liferay.portlet.add-default-resource=true",
		"javax.portlet.display-name=Demo Login Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.check-auth-token=false", 
		"javax.portlet.name=" + DemoLoginPortletKeys.DEMOLOGIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class DemoLoginPortlet extends MVCPortlet {

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		try {
			injectUsers(renderRequest, renderResponse);
			super.doView(renderRequest, renderResponse);
		} catch(Throwable t) {
			_log.error(t);
		}
	}

	private void injectUsers(RenderRequest renderRequest, RenderResponse renderResponse) {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String[] demoUsers = _demoLoginConfiguration.demoUsers();

		if(Validator.isNotNull(demoUsers)) {
			List<Object[]> users = getLoginUsers(themeDisplay.getCompanyId(), demoUsers);
			renderRequest.setAttribute("loginUsers", users);
		}
	}

	private List<Object[]> getLoginUsers(long companyId, String[] demoUsers) {
		long tmpID;
		String[] varUser;
		List<Object[]> users = new LinkedList<Object[]>();
		for (int i = 0; i < demoUsers .length; i++) {
			if (Validator.isNotNull(demoUsers)) {
				if (Validator.isNotNull(demoUsers[i]) && demoUsers[i].contains(StringPool.SEMICOLON)) {
					varUser = StringUtil.split(demoUsers[i], StringPool.SEMICOLON);
					if (varUser[0] != StringPool.BLANK && varUser.length == 3) {
						try {
							tmpID = _userLocalService.getUserIdByEmailAddress(companyId, varUser[0].trim());
							if (tmpID > 0) {
								User lfuser = _userLocalService.getUser(tmpID);
								users.add(new Object[] {lfuser, varUser[1].trim(), varUser[2].trim()}); 
							}
						} catch (NoSuchUserException e) {
							_log.error("Configured user " + varUser[0] + " not found. Typo?"); 
						} catch (PortalException e) {
							_log.error(e);
						}
					} else {
						_log.error("Expected 3 values (\"email;password;redirect\"), separated by semicolon. Found \"" + demoUsers[i] + "\" - ignoring"); 
					}
				} else {
					_log.error("Expected 3 values (\"email;password;redirect\"), separated by semicolon. Found \"" + demoUsers[i] + "\" - ignoring"); 
				}
			}
		}
		return users;
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

	private static final Log _log = LogFactoryUtil.getLog(DemoLoginPortlet.class);

	private volatile DemoLoginConfiguration _demoLoginConfiguration;

	@Reference
	private UserLocalService _userLocalService;
}