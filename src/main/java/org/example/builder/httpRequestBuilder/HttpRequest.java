package org.example.builder.httpRequestBuilder;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class HttpRequest {

    private String method;
    private String url;
    private Map<String, Object> headers;
    private String body;
    private int timeout = 10; // default timeout in seconds

    private HttpRequest(Builder builder) {
        this.method = builder.method;
        this.url = builder.url;
        this.headers = builder.headers;
        this.body = builder.body;
        this.timeout = builder.timeout;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public int getTimeout() {
        return timeout;
    }

    public static class Builder {

        private String method;
        private String url;
        private Map<String, Object> headers;
        private String body;
        private int timeout = 10; // default timeout in seconds

        public Builder method(String method) {
            if (method == null || (!method.equals("GET") && !method.equalsIgnoreCase("POST") && !method.equalsIgnoreCase("PUT") && !method.equalsIgnoreCase("DELETE") && !method.equalsIgnoreCase("PATCH"))) {
                throw new IllegalArgumentException("Method must be one of GET, POST, PUT, DELETE, PATCH: '" + method + "' given.");
            }
            this.method = method;
            return this;
        }

        public Builder url(String url) {
            if (url == null || url.isEmpty()) {
                throw new IllegalArgumentException("URL cannot be null or empty");
            }
            try {
                new URL(url);
            } catch (MalformedURLException e) {
                throw new RuntimeException("url is not a valid absolute url " + url + "given.");
            }
            this.url = url;
            return this;
        }

        public Builder headers(Map<String, Object> headers) {
            this.headers = headers;
            return this;
        }

        public Builder body(String body) {
            if (this.method.equals("GET") && body != null) {
                throw new IllegalArgumentException("GET requests must not include a body");
            }
            this.body = body;
            return this;
        }

        public Builder timeout(int timeout) {
            if (timeout < 0) {
                throw new IllegalArgumentException("Timeout must be non-negative");
            }
            this.timeout = timeout;
            return this;
        }

        public HttpRequest build() {
            if (this.method == null || this.url == null) {
                throw new IllegalStateException("Method and URL are required fields");
            }
            return new HttpRequest(this);
        }

        public Builder get(String URL) {
            return new Builder().method("GET").url(URL);
        }

        public Builder post(String URL) {
            return new Builder().method("POST").url(URL);
        }

        public void addHeader(String k, Object v) {
            this.headers.put(k, v);
        }

    }
}
