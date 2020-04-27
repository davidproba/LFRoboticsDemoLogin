package com.liferay.lfrobotics.demologin.portlet.action;

import com.liferay.lfrobotics.demologin.constants.DemoLoginPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		property = {
				"javax.portlet.name=" + DemoLoginPortletKeys.DEMOLOGIN,
				"mvc.command.name=/lfdemologin/login"
		},
		service = MVCRenderCommand.class
)

public class DemoLoginMVCRenderCommand implements MVCRenderCommand {

		@Override
		public String render(
			RenderRequest renderRequest, RenderResponse renderResponse) {

			return "/view.jsp";
		}

}