package com.rap.config.web.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

public class SafeDeserializationRepository<S extends Session> implements SessionRepository<S> {
	private static final Logger LOGGER = LoggerFactory.getLogger(SafeDeserializationRepository.class);
	  private final SessionRepository<S> delegate;
	  private final RedisTemplate<Object, Object> redisTemplate;

	  private static final String BOUNDED_HASH_KEY_PREFIX = "spring:session:sessions:";

	  public SafeDeserializationRepository(SessionRepository<S> delegate, RedisTemplate<Object, Object> redisTemplate) {
	    this.delegate = delegate;
	    this.redisTemplate = redisTemplate;
	  }
	  @Override
	  public void save(S session) {
	    delegate.save(session);
	  }

	  @Override
	  public S findById(String id) {
		try {
		    return delegate.findById(id);
		}catch(SerializationException e) {
			LOGGER.info("Deleting non-deserializable session with key {}", id);
			redisTemplate.delete(BOUNDED_HASH_KEY_PREFIX + id);
			return null;
		}
	}
	@Override
	public void deleteById(String id) {
		delegate.deleteById(id);
	}
	@Override
	public S createSession() {
		return delegate.createSession();
	}
}