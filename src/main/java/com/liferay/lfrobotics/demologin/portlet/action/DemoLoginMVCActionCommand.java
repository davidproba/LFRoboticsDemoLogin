package com.liferay.lfrobotics.demologin.portlet.action;

import com.liferay.lfrobotics.demologin.constants.DemoLoginPortletKeys;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		property = {
			"javax.portlet.name=" + DemoLoginPortletKeys.DEMOLOGIN,
			"mvc.command.name=/lfdemologin/login"
		},
		service = MVCActionCommand.class
	)

public class DemoLoginMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		// TODO Auto-generated method stub
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
				WebKeys.THEME_DISPLAY);
		HttpServletRequest request = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(actionRequest));
		HttpServletResponse response = PortalUtil.getHttpServletResponse(actionResponse);
		if(themeDisplay.isSignedIn()) {
			try {
				AuthenticatedSessionManagerUtil.logout(
						request, response);
				request.setAttribute(WebKeys.LOGOUT, Boolean.TRUE);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		String login = "";
		String uMail = ParamUtil.getString(actionRequest, "uMail");
		String uID = ParamUtil.getString(actionRequest, "uID");
		String uSN = ParamUtil.getString(actionRequest, "uSN");
		String password = ParamUtil.getString(actionRequest, "uPass");
		String redirect = ParamUtil.getString(actionRequest, "uRedirect");
		if(redirect=="DEFAULT") {
			redirect = PrefsPropsUtil.getString(themeDisplay.getCompanyId(),
					   PropsKeys.DEFAULT_LANDING_PAGE_PATH);
		}
		String authType = PortalUtil.getCompany(request).getAuthType();
		if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
			login = uMail;
		} else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
			login = uID;
		} else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
			login = uSN;
		}		
		try {
			AuthenticatedSessionManagerUtil.login(request, response, login, password, false, authType);
			actionResponse.sendRedirect(redirect);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
