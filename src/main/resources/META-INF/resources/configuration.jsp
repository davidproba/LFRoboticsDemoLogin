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

<liferay-portlet:actionURL
	portletConfiguration="<%= true %>"
	var="configurationActionURL"
/>

<liferay-portlet:renderURL
	portletConfiguration="<%= true %>"
	var="configurationRenderURL"
/>

<liferay-frontend:edit-form
	action="<%= configurationActionURL %>"
	method="post"
	name="fm"
>
		<aui:input
			name="<%= Constants.CMD %>"
			type="hidden"
			value="<%= Constants.UPDATE %>"
		/>
	
		<aui:input
			name="redirect"
			type="hidden"
			value="<%= configurationRenderURL %>"
		/>
		<liferay-frontend:edit-form-body>
			<liferay-frontend:fieldset-group>
				<liferay-frontend:fieldset>

					<table class="lfr-table">
						<tr>
							<td class="lfr-label">User ID</td>
							<td class="lfr-label">User Password</td>
							<td class="lfr-label">User RedirectURL</td>
						</tr>
<%
			for(int i=0;i<10;i++) {
					String[] varUser = new String[3];
					varUser[0] = StringPool.BLANK;
					varUser[1] = StringPool.BLANK;
					varUser[2] = StringPool.BLANK;
				if(Validator.isNotNull(demoUsers)) {
						if(Validator.isNotNull(demoUsers[i])&&demoUsers[i].contains(StringPool.PIPE)){
							varUser = StringUtil.split(demoUsers[i], StringPool.PIPE);					
						}
					}
				
%>									
						<tr>
							<td>
								<aui:input label="" name="<%= "demoUserID"+i %>" size="15" value="<%= varUser[0] %>" />
							</td>
							<td>
								<aui:input label="" name="<%= "demoUserPass"+i %>" size="25" value="<%= varUser[1] %>" />
							</td>
							<td>
								<aui:input label="" name="<%= "demoUserRedirectURL"+i %>" size="35" value="<%= varUser[2] %>" />
							</td>
						</tr>										
<%
			}

%>						
					</table>										
				</liferay-frontend:fieldset>
			</liferay-frontend:fieldset-group>
		</liferay-frontend:edit-form-body>

		<liferay-frontend:edit-form-footer>
			<aui:button type="submit" />
			<aui:button type="cancel" />
		</liferay-frontend:edit-form-footer>	
</liferay-frontend:edit-form>