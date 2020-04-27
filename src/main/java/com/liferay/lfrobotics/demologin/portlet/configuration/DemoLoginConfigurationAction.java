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

package com.liferay.lfrobotics.demologin.portlet.configuration;

import com.liferay.lfrobotics.demologin.constants.DemoLoginPortletKeys;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Liferay
 */
@Component(
	configurationPid = "com.liferay.lfrobotics.demologin.portlet.configuration.DemoLoginConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	property = "javax.portlet.name=" + DemoLoginPortletKeys.DEMOLOGIN,
	service = ConfigurationAction.class
)
public class DemoLoginConfigurationAction
	extends DefaultConfigurationAction {

	@Override
	public void include(
			PortletConfig portletConfig, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		httpServletRequest.setAttribute(
			DemoLoginConfiguration.class.getName(),
			_demoLoginConfiguration);

		super.include(portletConfig, httpServletRequest, httpServletResponse);
	}

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {
		
		String[] demoUsers = new String[10];
		
		demoUsers[0] = StringPool.BLANK;		
		for(int i=0;i<10;i++) {
			String tmpUserID = ParamUtil.getString(actionRequest, "demoUserID"+i);
			String tmpUserPass = ParamUtil.getString(actionRequest, "demoUserPass"+i);
			String tmpUserRedirectURL = ParamUtil.getString(actionRequest, "demoUserRedirectURL"+i);
			if(tmpUserRedirectURL==null||tmpUserRedirectURL.equalsIgnoreCase(StringPool.BLANK)||tmpUserRedirectURL.length()==0) {
				tmpUserRedirectURL="DEFAULT";
			}
			demoUsers[i] = StringPool.BLANK;
			if(!tmpUserID.isEmpty())
			{
				demoUsers[i] = tmpUserID+StringPool.PIPE+tmpUserPass+StringPool.PIPE+tmpUserRedirectURL;
			}
		}

		setPreference(actionRequest, "demoUsers", demoUsers);

		super.processAction(portletConfig, actionRequest, actionResponse);
	}

	@Activate
	@Modified
	protected void activate(Map<Object, Object> properties) {
		_demoLoginConfiguration = ConfigurableUtil.createConfigurable(
			DemoLoginConfiguration.class, properties);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DemoLoginConfigurationAction.class);

	private volatile DemoLoginConfiguration _demoLoginConfiguration;

}