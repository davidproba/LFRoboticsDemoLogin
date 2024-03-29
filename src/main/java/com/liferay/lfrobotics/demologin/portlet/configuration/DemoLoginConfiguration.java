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

import aQute.bnd.annotation.metatype.Meta;

/**
 * @author David Proba, Olaf Kock
 */

@Meta.OCD(
	id = "com.liferay.lfrobotics.demologin.portlet.configuration.DemoLoginConfiguration"
    , localization = "content/Language"
    , name = "demo-login-configuration-name"
    , description = "demo-login-configuration-description"
)
public interface DemoLoginConfiguration {

	@Meta.AD(
            deflt = "",
			description = "demo-login-configuration-demousers-description",
            name = "demo-login-configuration-demousers-name",
            required = false
        )
	public String[] demoUsers();
}




