/*
 * Copyright 2002-2013 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.springframework.data.gemfire.function.execution;

import org.springframework.util.Assert;

import com.gemstone.gemfire.cache.execute.Execution;
import com.gemstone.gemfire.cache.execute.FunctionService;

/**
 * @author David Turanski
 *
 */
class GroupMembersFunctionExecution extends AbstractFunctionExecution {
	
	private final String groups[];

	/**
	 * 
	 * @param groups
	 */
	public GroupMembersFunctionExecution(String... groups) {
		super();
		Assert.notEmpty(groups, "groups cannot be null or empty.");
		this.groups = groups; 
	}
	
	@Override
	protected Execution getExecution() {
		return FunctionService.onMember(this.groups);
	}

}
