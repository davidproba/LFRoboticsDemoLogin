# LFRoboticsDemoLogin

LFRoboticsDemoLogin is a portlet module for Liferay DXP 7.3 (earlier version for 7.2 available) from the 
LFRobotics showcase to prevent repetitive logins for demo personas. During demos it helps watchers keep context
as it clearly shows whom you're logging in as, when you change login. And if you've set a user's "job title" 
and "comment" to an appropriate description, your audience will have a short reminder about the persona's 
purpose and expected use cases/permissions.

### Features
* Unlimited personas can be used (but who wants that?)
* Displays user name, job title, sign in button and user comments
* No need for manual logout, current session gets invalidated if already signed in
* Authentication by Email, ID or screen name based on instance "Authenticate by" setting
* You can add a redirect URL to each user
* Introduces a gaping security hole, as it transmits clear text passwords to your browser. You have been warned. It's insecure, but sooooooo convenient
 
### Install
Alternatively, clone/download this project into a Liferay Workspace's `modules` folder, build & deploy.

### Configure
For each persona you want to use in your demo, just configure their Mail address, Password and a redirect target in Control Panel / System Settings / Third Party / Demo Login Configuration. The three values need to be separated by Semicolon. Add as many of these repeatable fields as you like.

The configuration link that you see in an unconfigured system is known to work in DXP 7.3 FP1 and known to _not_ work in DXP 7.3 GA1.

### Embed in your theme
Contrary to the earlier version, you don't need to embed this in your theme any more. It's now living as a ControlMenuEntry (the little padlock) that opens a popup dialog. Means: You'll need to log in manually for the first user, then can use its comfort

### Look & feel
The portlet doesn't come with any design, so its sizes, fonts and colors follow the current theme/stylebook.
