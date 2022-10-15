package com.shf.dcs.security;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class LoginAttemptService {

	public static final int MAX_ATTEMPT = 10;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private LoadingCache<String, Integer> attemptsCache;

	public LoginAttemptService() {
		super();
		attemptsCache = CacheBuilder.newBuilder()
				.expireAfterWrite(1, TimeUnit.DAYS)
				.build(new CacheLoader<String, Integer>() {
					@Override
					public Integer load(final String key) {
						return 0;
					}
				});
	}

	//

	public void loginSucceeded(final String key) {
		logger.info("An loggin attempt made for key:" + key);
		attemptsCache.invalidate(key);
	}

	public void loginFailed(final String key) {
		int attempts = 0;
		try {
			attempts = attemptsCache.get(key);
		} catch (final ExecutionException e) {
			attempts = 0;
		}
		attempts++;
		attemptsCache.put(key, attempts);
	}

	public boolean isBlocked(final String key) {
		try {
			return attemptsCache.get(key) >= MAX_ATTEMPT;
		} catch (final ExecutionException e) {
			return false;
		}
	}
}
