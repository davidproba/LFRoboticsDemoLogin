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



<div class="row">
<%
				long tmpID;
				String[] varUser = new String[3];
				varUser[0] = StringPool.BLANK;
				varUser[1] = StringPool.BLANK;
				varUser[2] = StringPool.BLANK;
				for(int i=0;i<10;i++) {
					if(Validator.isNotNull(demoUsers)) {
						if(Validator.isNotNull(demoUsers[i])&&demoUsers[i].contains(StringPool.PIPE)){
							varUser = StringUtil.split(demoUsers[i], StringPool.PIPE);
							if(varUser[0]!=StringPool.BLANK) {
								tmpID = Long.parseLong(varUser[0]);
								if(tmpID>0) {
%>
	<div class="col-md-3 text-center">
<% User lfuser = UserLocalServiceUtil.getUser(tmpID); %>
			<span class="sticker sticker-lg">
				<span class="inline-item">
					<liferay-ui:user-portrait
						cssClass="sticker-lg"
						user="<%= lfuser %>"
					/>
				</span>
			</span>
			<br/>
			<h3><%= lfuser.getFullName() %></h3>
			<h4><%= lfuser.getJobTitle() %></h4>
			<portlet:actionURL name="/lfdemologin/login" var="loginURL">
				<portlet:param name="mvcRenderCommandName" value="/lfdemologin/login" />
			</portlet:actionURL>

			<aui:form action="<%= loginURL %>" method="post" name="<%= "uForm"+i %>">
				<aui:input name="uMail" type="hidden" value="<%= lfuser.getEmailAddress() %>" />
				<aui:input name="uID" type="hidden" value="<%= tmpID %>" />
				<aui:input name="uSN" type="hidden" value="<%= lfuser.getScreenName() %>" />
				<aui:input name="uPass" type="hidden" value="<%= varUser[1] %>" />
				<aui:input name="uRedirect" type="hidden" value="<%= varUser[2] %>" />
				<aui:button-row>
					<aui:button type="submit" value="sign-in" />
				</aui:button-row>
			</aui:form>			
			<p><%= lfuser.getComments() %></p>	
	</div>

<%									
								}
							}
						}
					}
				}
				
%>									

</div>
