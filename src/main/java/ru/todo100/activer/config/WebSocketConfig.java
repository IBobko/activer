package ru.todo100.activer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * @author Igor Bobko
 */
@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer
{
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry)
	{
		/* There is a built-in interceptor for passing HTTP session attributes to the WebSocket session. */
		registry.addEndpoint("/message1/{id}").withSockJS();//.setInterceptors(new HttpSessionHandshakeInterceptor());
		registry.addEndpoint("/wall/{id}").withSockJS();//.setInterceptors(new HttpSessionHandshakeInterceptor());
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
//		registry.setApplicationDestinationPrefixes("/topic");

	}

}