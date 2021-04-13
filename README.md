# LFRoboticsDemoLogin

LFRoboticsDemoLogin is a portlet module for Liferay DXP 7.2 I used in my LFRobotics showcase to prevent repetitive logins for demo personas.
So, when using this module, after configuring it, you can change your currently signed in user by button klick.

![Demo Login portlet](/img/LFRoboticsDemoLogin.png)

### Features
* Unlimited personas can be used (but who wants that?)
* Displays user name, job title, sign in button and user comments
* No need for manual logout, current session gets invalidated if already signed in
* Authentication by Email, ID or screen name based on instance "Authenticate by" setting
* You can add a redirect URL to each user
* Introduces a gaping security hole, as it transmits clear text passwords to your browser. You have been warned. It's insecure, but sooooooo convenient
 
### Install
You can download and deploy the jar from build/libs.
Alternatively, you can clone/download this project, build & deploy.
In this context, consider this module to be created in Liferay Workspace.
If you do want to extend it, put it in your Liferay Workkspace modules folder.

### Configure
For each persona you want to use in your demo, just configure their Mail address, Password and a redirect target in Control Panel / System Settings / Third Party / Demo Login Configuration. The three values need to be separated by Semicolon.

![Demo Login configuration](/img/LFRoboticsDemoLoginConfig.png)

### Embed in your theme
Contrary to the earlier version, you don't need to embed this in your theme any more. It's now living as a ControlMenuEntry (the little padlock) that opens a popup dialog. Means: You'll need to log in manually for the first user, then can use its comfort

### Look & feel
There is actually no CSS behaviour included within this module. For my demo look & feel, I do "design" also this portlet in my theme module.
What I did for LFRobotics showcase is to resize the avatar image and to apply a color for the buttons:
```
#portlet_com_liferay_lfrobotics_demologin_DemoLoginPortlet_INSTANCE_LFRobotics {
	.sticker-lg {
		height:4rem;
		width:4rem;
	}
	.button-holder {
		margin: 1.5rem 0;
		button {
			background-color: rgb(0,206,209);
			border-color: rgb(0,206,209);
			&:hover {
				background-color: rgb(0,133,136);
				border-color: rgb(0,133,136);
			}
		}
	}
}
```

