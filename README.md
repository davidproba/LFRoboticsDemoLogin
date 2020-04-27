# LFRoboticsDemoLogin

LFRoboticsDemoLogin is a portlet module for Liferay DXP 7.2 I used in my LFRobotics showcase to prevent repetitive logins for demo personas.
So, when using this module, after configuring it, you can change your currently signed in user by button klick.

![Demo Login portlet](/img/LFRoboticsDemoLogin.png)

### Features
* Up to 10 demo personas can be used
* Displays user name, job title, sign in button and user comments
* No need for manual logout, current session gets invalidated if already signed in
* Authentication by Email, ID or screen name based on instance "Authenticate by" setting
* You can add a redirect URL to each user

### Install
That should be quite easy, clone/download, build & deploy.

### Configure
For each persona you want to use in your demo, just set the (already existing) user ID, password and optionally a redirect URL.
![Demo Login configuration](/img/LFRoboticsDemoLoginConfig.png)

### Embed in your theme
Most likely, you will embed this portlet to your theme footer:
```
<#assign preferencesLog = freeMarkerPortletPreferences.getPreferences({"portletSetupPortletDecoratorId": "barebone"}) />

<@liferay_portlet["runtime"]
	defaultPreferences="${preferencesLog}"
	portletName="com_liferay_lfrobotics_demologin_DemoLoginPortlet"
	instanceId="LFRobotics"
	/>
```

### Look & feel
There is actually no CSS behaviour included within this module. For my demo look & feel, I do "design" also this portlet in my theme module.
