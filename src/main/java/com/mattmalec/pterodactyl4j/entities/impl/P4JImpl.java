/*
 *    Copyright 2021-2022 Matt Malec, and the Pterodactyl4J contributors
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.mattmalec.pterodactyl4j.entities.impl;

import com.mattmalec.pterodactyl4j.application.entities.PteroApplication;
import com.mattmalec.pterodactyl4j.application.entities.impl.PteroApplicationImpl;
import com.mattmalec.pterodactyl4j.client.entities.PteroClient;
import com.mattmalec.pterodactyl4j.client.entities.impl.PteroClientImpl;
import com.mattmalec.pterodactyl4j.entities.P4J;
import com.mattmalec.pterodactyl4j.requests.Requester;
import com.mattmalec.pterodactyl4j.utils.config.EndpointConfig;
import com.mattmalec.pterodactyl4j.utils.config.SessionConfig;
import com.mattmalec.pterodactyl4j.utils.config.ThreadingConfig;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import okhttp3.OkHttpClient;

public class P4JImpl implements P4J {

	private final Requester requester;

	private final EndpointConfig endpointConfig;
	private final ThreadingConfig threadingConfig;
	private final SessionConfig sessionConfig;

	public P4JImpl(EndpointConfig endpointConfig, ThreadingConfig threadingConfig, SessionConfig sessionConfig) {
		this.endpointConfig = endpointConfig;
		this.threadingConfig = threadingConfig;
		this.sessionConfig = sessionConfig;
		this.requester = new Requester(this);
	}

	@Override
	public String getToken() {
		return endpointConfig.getToken();
	}

	@Override
	public Requester getRequester() {
		return requester;
	}

	@Override
	public String getApplicationUrl() {
		return endpointConfig.getUrl();
	}

	@Override
	public OkHttpClient getHttpClient() {
		return sessionConfig.getHttpClient();
	}

	@Override
	public ExecutorService getCallbackPool() {
		return threadingConfig.getCallbackPool();
	}

	@Override
	public ExecutorService getActionPool() {
		return threadingConfig.getActionPool();
	}

	@Override
	public ScheduledExecutorService getRateLimitPool() {
		return threadingConfig.getRateLimitPool();
	}

	@Override
	public ExecutorService getSupplierPool() {
		return threadingConfig.getSupplierPool();
	}

	@Override
	public OkHttpClient getWebSocketClient() {
		return sessionConfig.getWebSocketClient();
	}

	@Override
	public String getUserAgent() {
		return sessionConfig.getUserAgent();
	}

	@Override
	public PteroApplication asApplication() {
		return new PteroApplicationImpl(this);
	}

	@Override
	public PteroClient asClient() {
		return new PteroClientImpl(this);
	}
}
