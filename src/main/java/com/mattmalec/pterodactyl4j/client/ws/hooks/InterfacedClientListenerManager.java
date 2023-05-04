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

package com.mattmalec.pterodactyl4j.client.ws.hooks;

import com.mattmalec.pterodactyl4j.client.ws.events.Event;
import com.mattmalec.pterodactyl4j.utils.P4JLogger;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;

public class InterfacedClientListenerManager implements IClientListenerManager {

	private static final Logger LISTENER_LOG = P4JLogger.getLogger(ClientSocketListener.class);

	private final List<ClientSocketListener> listeners = new CopyOnWriteArrayList<>();

	@Override
	public void register(Object listener) {
		if (!(listener instanceof ClientSocketListener)) {
			throw new IllegalArgumentException("Listener must implement ClientSocketListener");
		}
		listeners.add((ClientSocketListener) listener);
	}

	@Override
	public void unregister(Object listener) {
		listeners.remove(listener);
	}

	@Override
	public List<Object> getRegisteredListeners() {
		return Collections.unmodifiableList(new LinkedList<>(listeners));
	}

	@Override
	public void handle(Event event) {
		for (ClientSocketListener listener : listeners) {
			try {
				listener.onEvent(event);
			} catch (Throwable throwable) {
				LISTENER_LOG.error("One of the ClientSocketListeners had an uncaught exception", throwable);
			}
		}
	}
}
