
### zuul ribbon 分析

### ribbon接入

org.springframework.cloud.netflix.zuul.filters.route.RibbonRoutingFilter

```
	@Override
	public String filterType() {
		return "route";
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		return (ctx.getRouteHost() == null && ctx.get("serviceId") != null
				&& ctx.sendZuulResponse());
	}
```

设置了route的类型及serviceId

### command创建

```
	protected ClientHttpResponse forward(RibbonCommandContext context) throws Exception {
		Map<String, Object> info = this.helper.debug(context.getMethod(),
				context.getUri(), context.getHeaders(), context.getParams(),
				context.getRequestEntity());

		RibbonCommand command = this.ribbonCommandFactory.create(context);
		try {
			ClientHttpResponse response = command.execute();
			this.helper.appendDebug(info, response.getStatusCode().value(),
					response.getHeaders());
			return response;
		}
		catch (HystrixRuntimeException ex) {
			return handleException(info, ex);
		}

	}
```

使用ribbonCommandFactory创建，ribbonCommandFactory的实现有:HttpClientRibbonCommandFactory OkHttpRibbonCommandFactory 等

### ribbonCommandFactory 创建

org.springframework.cloud.netflix.zuul.ZuulProxyConfiguration

```

	@Configuration
	@ConditionalOnProperty(name = "zuul.ribbon.httpclient.enabled", matchIfMissing = true)
	protected static class HttpClientRibbonConfiguration {

		@Bean
		@ConditionalOnMissingBean
		public RibbonCommandFactory<?> ribbonCommandFactory(
				SpringClientFactory clientFactory, ZuulProperties zuulProperties) {
			return new HttpClientRibbonCommandFactory(clientFactory, zuulProperties);
		}
	}
```

默认使用 HttpClientRibbonCommandFactory

### command create

```

	@Override
	public HttpClientRibbonCommand create(final RibbonCommandContext context) {
		final String serviceId = context.getServiceId();
		final RibbonLoadBalancingHttpClient client = this.clientFactory.getClient(
				serviceId, RibbonLoadBalancingHttpClient.class);
		client.setLoadBalancer(this.clientFactory.getLoadBalancer(serviceId));

		return new HttpClientRibbonCommand(serviceId, client, context, zuulProperties);
	}
```

HttpClientRibbonCommand run

org.springframework.cloud.netflix.zuul.filters.route.support.AbstractRibbonCommand

```

	@Override
	protected ClientHttpResponse run() throws Exception {
		final RequestContext context = RequestContext.getCurrentContext();

		RQ request = createRequest();
		RS response = this.client.executeWithLoadBalancer(request);

		context.set("ribbonResponse", response);

		// Explicitly close the HttpResponse if the Hystrix command timed out to
		// release the underlying HTTP connection held by the response.
		//
		if (this.isResponseTimedOut()) {
			if (response != null) {
				response.close();
			}
		}

		return new RibbonHttpResponse(response);
	}
```
run include find eureka server id, invoke reomote server

