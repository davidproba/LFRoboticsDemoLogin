<%--
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
--%>
<%@ include file="init.jsp" %>

<div class="row" style="padding:20px;">

<% if(loginUsers != null && ! loginUsers.isEmpty()) { %>

<%
  try {
	int i = 0;
	for (Object[] loginUserInfo : loginUsers) {
		User loginUser = ((User) loginUserInfo[0]).toEscapedModel();
		String password = (String) loginUserInfo[1];
		String redirect = (String) loginUserInfo[2];
		int colspan = Math.max(3, 12/loginUsers.size());  
		%>
		<div class="col-md-<%=colspan %> text-center">
			<span class="sticker sticker-xl">
				<span class="inline-item">
					<liferay-ui:user-portrait
						cssClass="sticker-xl"
						user="<%= loginUser %>"
					/>
				</span>
			</span>
			<br/>
			<h3><%= loginUser.getFullName() %></h3>
			<h4><%= loginUser.getJobTitle() %></h4>
		<% if(loginUser.getUserId() == user.getUserId()) { %>
			<p><b>(that's you!)</b></p>
		<% } else { %>	
			<portlet:actionURL name="/lfdemologin/login" var="loginURL">
				<portlet:param name="mvcRenderCommandName" value="/lfdemologin/login" />
			</portlet:actionURL>
			<% 
			String tmpSubmitAction = "window.setTimeout(function(){window.parent.location = '" + redirect + "';},2000); return true;";
			String loginForm = "loginForm"+(i++);
			%>
			<aui:form action="<%= loginURL %>" method="post" name="<%= loginForm %>" target="_parent" onSubmit="<%=tmpSubmitAction %>">     
				<aui:input name="uMail" type="hidden" value="<%= loginUser.getEmailAddress() %>" />
				<aui:input name="uID" type="hidden" value="<%= loginUser.getUserId() %>" />
				<aui:input name="uSN" type="hidden" value="<%= loginUser.getScreenName() %>" />
				<aui:input name="uPass" type="hidden" value="<%= password %>" />
				<aui:input name="uRedirect" type="hidden" value="<%= redirect %>" />
				<aui:button type="submit" value="sign-in" target="_parent"/>
			</aui:form>			
		<% } %>	
			<p><%= loginUser.getComments() %></p>	
		</div>
<%	}
  }
  catch(Throwable t) {
	t.printStackTrace(renderResponse.getWriter());
  }
%>									
<%  } else if(loggedInRedirect != null && loggedInUser != null){ %>
	<div class="col-md-4 text-center">
	</div>
	<div class="col-md-4 text-center">
	<h1>Logged in as <%=loggedInUser %></h1>
	<p>Stand by, you'll be redirected to <a href="<%=loggedInRedirect %>" target="_top"><%=loggedInRedirect %></a> in 3, 2, 1, ...</p>
	</div>
	<div class="col-md-4 text-center">
	</div>
<%  } else { %>
	<div class="col-md-4 text-center">
	</div>
	<div class="col-md-4 text-center">	<h1>Demo Login</h1>
	<p>Please configure available Logins in System Settings / Third Party / 
	<a title="this link works in 7.3 FP1, not in GA1 (other versions: unknown)" 
	   target="_blank" 
	   href="/group/control_panel/manage?p_p_id=com_liferay_configuration_admin_web_portlet_SystemSettingsPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_configuration_admin_web_portlet_SystemSettingsPortlet_factoryPid=com.liferay.lfrobotics.demologin.portlet.configuration.DemoLoginConfiguration&_com_liferay_configuration_admin_web_portlet_SystemSettingsPortlet_mvcRenderCommandName=%2Fconfiguration_admin%2Fedit_configuration&_com_liferay_configuration_admin_web_portlet_SystemSettingsPortlet_pid=com.liferay.lfrobotics.demologin.portlet.configuration.DemoLoginConfiguration"
	   >Demo Login</a>
	</p>
	<p>Note: This feature is <i>horribly</i> insecure but <i>very</i> convenient. It will cause your UI - and thus the users of this system - to have access to the clear text passwords of all users that you configure to sign in automatically!</p>
	<p>Use at your own risk - you have been warned.</p>
	</div>
	<div class="col-md-4 text-center">
	</div><%  }  %>

</div>
