/*
 * Copyright 2012-2017 the original author or authors.
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

package org.springframework.boot.actuate.metrics.cache;

import java.util.Collections;

import com.hazelcast.core.IMap;
import com.hazelcast.spring.cache.HazelcastCache;
import io.micrometer.core.instrument.binder.MeterBinder;
import io.micrometer.core.instrument.binder.cache.HazelcastCacheMetrics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link HazelcastCacheMeterBinderProvider}.
 *
 * @author Stephane Nicoll
 */
@RunWith(MockitoJUnitRunner.class)
public class HazelcastCacheMeterBinderProviderTests {

	@Mock
	private IMap<Object, Object> nativeCache;

	@Test
	public void hazelcastCacheProvider() {
		HazelcastCache cache = new HazelcastCache(this.nativeCache);
		MeterBinder meterBinder = new HazelcastCacheMeterBinderProvider()
				.getMeterBinder(cache, "test", Collections.emptyList());
		assertThat(meterBinder).isInstanceOf(HazelcastCacheMetrics.class);
	}

}
