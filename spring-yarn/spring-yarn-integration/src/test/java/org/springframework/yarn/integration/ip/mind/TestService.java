/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.yarn.integration.ip.mind;

import org.springframework.core.convert.ConversionService;
import org.springframework.yarn.integration.ip.mind.binding.BaseObject;

/**
 * Custom testing service responding requests send from
 * a {@link TestServiceClient}.
 *
 * @author Janne Valkealahti
 *
 */
public class TestService extends MindAppmasterService {

	@Override
	protected MindRpcMessageHolder handleMindMessageInternal(MindRpcMessageHolder message) {

		ConversionService conversionService = getConversionService();
		SimpleTestRequest request = (SimpleTestRequest) conversionService.convert(message, BaseObject.class);

		SimpleTestResponse response = new SimpleTestResponse();
		response.stringField = "echo:" + request.stringField;

		MindRpcMessageHolder holder = conversionService.convert(response, MindRpcMessageHolder.class);
		return holder;

	}

}
