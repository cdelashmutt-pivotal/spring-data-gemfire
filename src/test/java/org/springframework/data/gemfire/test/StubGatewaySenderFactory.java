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
package org.springframework.data.gemfire.test;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.gemstone.gemfire.cache.util.Gateway.OrderPolicy;
import com.gemstone.gemfire.cache.wan.GatewayEventFilter;
import com.gemstone.gemfire.cache.wan.GatewaySender;
import com.gemstone.gemfire.cache.wan.GatewaySenderFactory;
import com.gemstone.gemfire.cache.wan.GatewayTransportFilter;

/**
 * The StubGatewaySenderFactory class for testing purposes.
 *
 * @author David Turanski
 * @see com.gemstone.gemfire.cache.wan.GatewaySenderFactory
 */
public class StubGatewaySenderFactory implements GatewaySenderFactory {

	private int alertThreshold;
	private boolean batchConflationEnabled;
	private int batchSize;
	private int batchTimeInterval;
	private String diskStoreName;
	private boolean diskSynchronous;
	private int dispatcherThreads;
	private boolean manualStart;
	private int maxQueueMemory;
	private OrderPolicy orderPolicy;
	private boolean parallel;
	private boolean persistenceEnabled;
	private int socketBufferSize;
	private int socketReadTimeout;
	private List<GatewayEventFilter> eventFilters;
	private List<GatewayTransportFilter> transportFilters;

	private String name;

    private boolean running = true;

	private int remoteSystemId;

	public StubGatewaySenderFactory() {
		this.eventFilters = new ArrayList<GatewayEventFilter>();
		this.transportFilters = new ArrayList<GatewayTransportFilter>();

	}

	@Override
	public GatewaySenderFactory addGatewayEventFilter(GatewayEventFilter filter) {
		eventFilters.add(filter);
		return this;
	}

	@Override
	public GatewaySenderFactory addGatewayTransportFilter(GatewayTransportFilter filter) {
		transportFilters.add(filter);
		return this;
	}

	@Override
	public GatewaySender create(String name, int remoteSystemId) {
		GatewaySender gatewaySender = mock(GatewaySender.class);
		this.name = name;
		this.remoteSystemId = remoteSystemId;
		when(gatewaySender.getId()).thenReturn(this.name);
		when(gatewaySender.getRemoteDSId()).thenReturn(this.remoteSystemId);
		when(gatewaySender.getAlertThreshold()).thenReturn(this.alertThreshold);
		when(gatewaySender.getBatchSize()).thenReturn(this.batchSize);
		when(gatewaySender.getBatchTimeInterval()).thenReturn(this.batchTimeInterval);
		when(gatewaySender.getDiskStoreName()).thenReturn(this.diskStoreName);
		when(gatewaySender.getDispatcherThreads()).thenReturn(this.dispatcherThreads);
		when(gatewaySender.getGatewayEventFilters()).thenReturn(this.eventFilters);
		when(gatewaySender.getGatewayTransportFilters()).thenReturn(this.transportFilters);
		when(gatewaySender.getMaximumQueueMemory()).thenReturn(this.maxQueueMemory);
		when(gatewaySender.getOrderPolicy()).thenReturn(this.orderPolicy);
		when(gatewaySender.getSocketBufferSize()).thenReturn(this.socketBufferSize);
		when(gatewaySender.getSocketReadTimeout()).thenReturn(this.socketReadTimeout);
		when(gatewaySender.isManualStart()).thenReturn(this.manualStart);
		when(gatewaySender.isBatchConflationEnabled()).thenReturn(this.batchConflationEnabled);
		when(gatewaySender.isDiskSynchronous()).thenReturn(this.diskSynchronous);
		when(gatewaySender.isParallel()).thenReturn(this.parallel);
		when(gatewaySender.isPersistenceEnabled()).thenReturn(this.persistenceEnabled);
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                running = true;
               return null;
            }
        }).when(gatewaySender).start();
        when(gatewaySender.isRunning()).thenAnswer(new Answer<Boolean>(){
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                return running;
            }
        });
		return gatewaySender;
	}

	@Override
	public GatewaySenderFactory removeGatewayEventFilter(GatewayEventFilter filter) {
		this.eventFilters.remove(filter);
		return this;
	}

	@Override
	public GatewaySenderFactory setAlertThreshold(int alertThreshold) {
		this.alertThreshold = alertThreshold;
		return this;
	}

	@Override
	public GatewaySenderFactory setBatchConflationEnabled(boolean batchConflationEnabled) {
		this.batchConflationEnabled = batchConflationEnabled;
		return this;
	}

	@Override
	public GatewaySenderFactory setBatchSize(int batchSize) {
		this.batchSize = batchSize;
		return this;
	}

	@Override
	public GatewaySenderFactory setBatchTimeInterval(int batchTimeInterval) {
		this.batchTimeInterval = batchTimeInterval;
		return this;
	}

	@Override
	public GatewaySenderFactory setDiskStoreName(String diskStoreName) {
		this.diskStoreName = diskStoreName;
		return this;
	}

	@Override
	public GatewaySenderFactory setDiskSynchronous(boolean diskSynchronous) {
		this.diskSynchronous = diskSynchronous;
		return this;
	}

	@Override
	public GatewaySenderFactory setDispatcherThreads(int dispatcherThreads) {
		this.dispatcherThreads = dispatcherThreads;
		return this;
	}

	@Override
	public GatewaySenderFactory setManualStart(boolean manualStart) {
		this.manualStart = manualStart;
		return this;
	}

	@Override
	public GatewaySenderFactory setMaximumQueueMemory(int maxQueueMemory) {
		this.maxQueueMemory = maxQueueMemory;
		return this;
	}

	@Override
	public GatewaySenderFactory setOrderPolicy(OrderPolicy orderPolicy) {
		this.orderPolicy = orderPolicy;
		return this;
	}

	@Override
	public GatewaySenderFactory setParallel(boolean parallel) {
		this.parallel = parallel;
		return this;
	}

	@Override
	public GatewaySenderFactory setPersistenceEnabled(boolean persistenceEnabled) {
		this.persistenceEnabled = persistenceEnabled;
		return this;
	}

	@Override
	public GatewaySenderFactory setSocketBufferSize(int socketBufferSize) {
		this.socketBufferSize = socketBufferSize;
		return this;
	}

	@Override
	public GatewaySenderFactory setSocketReadTimeout(int socketReadTimeout) {
		this.socketReadTimeout = socketReadTimeout;
		return this;
	}

	@Override
	public GatewaySenderFactory removeGatewayTransportFilter(GatewayTransportFilter arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
