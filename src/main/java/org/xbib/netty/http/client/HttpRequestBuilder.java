/*
 * Copyright 2017 Jörg Prante
 *
 * Jörg Prante licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.xbib.netty.http.client;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 */
public interface HttpRequestBuilder {

    HttpRequestBuilder setTimeout(int timeout);

    HttpRequestBuilder setVersion(String httpVersion);

    HttpRequestBuilder setURL(String url);

    HttpRequestBuilder setHeader(String name, Object value);

    HttpRequestBuilder addHeader(String name, Object value);

    HttpRequestBuilder removeHeader(String name);

    HttpRequestBuilder contentType(String contentType);

    HttpRequestBuilder acceptGzip(boolean gzip);

    HttpRequestBuilder setFollowRedirect(boolean followRedirect);

    HttpRequestBuilder setMaxRedirects(int maxRedirects);

    HttpRequestBuilder setUserAgent(String userAgent);

    HttpRequestBuilder setBody(CharSequence charSequence, String contentType) throws IOException;

    HttpRequestBuilder text(String text) throws IOException;

    HttpRequestBuilder json(String jsonText) throws IOException;

    HttpRequestBuilder xml(String xmlText) throws IOException;

    HttpRequestBuilder setBody(byte[] buf, String contentType) throws IOException;

    HttpRequestBuilder setBody(ByteBuf body, String contentType) throws IOException;

    HttpRequestBuilder onError(ExceptionListener exceptionListener);

    HttpRequestBuilder onResponse(HttpResponseListener httpResponseListener);

    HttpRequest build();

    HttpRequestContext execute();

    <T> CompletableFuture<T> execute(Function<FullHttpResponse, T> supplier);
}