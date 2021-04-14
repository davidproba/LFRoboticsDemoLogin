<%@page import="com.liferay.portal.kernel.service.LayoutLocalServiceUtil"%>
<%@page import="com.liferay.lfrobotics.demologin.constants.DemoLoginPortletKeys"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="javax.portlet.WindowState"%>
<%@page import="javax.portlet.PortletRequest"%>
<%@page import="com.liferay.portal.kernel.portlet.PortletURLFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletURL"%>
<%@page import="com.liferay.portal.kernel.util.PortalUtil"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
LiferayPortletURL modalPortletURL = PortletURLFactoryUtil.create(request, DemoLoginPortletKeys.DEMOLOGIN, 
		LayoutLocalServiceUtil.getDefaultPlid(scopeGroupId), PortletRequest.RENDER_PHASE);
modalPortletURL.setWindowState(LiferayWindowState.POP_UP);
%>

<li class="control-menu-nav-item">
<a href="javascript:Liferay.Util.openModal({title:'Demo Login', url:'<%=modalPortletURL %>'});" class="control-menu-icon icon-monospaced"><clay:icon symbol="lock-dots"/></a>
</li>

