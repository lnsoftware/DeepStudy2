
## ribbon call server info

if use ribbon, and multi server porvide. How to get backend server ip.

### ribbonResponse

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

the follow code set ribbon info.
```
		context.set("ribbonResponse", response);
```


### get

```
 RequestContext ctx = RequestContext.getCurrentContext();
        URI ribbonResponseURI = ((IResponse) ctx.get("ribbonResponse")).getRequestedURI();
```   
		
		
http://stackoverflow.com/questions/36981525/zuul-proxy-with-ribbon-listofservers-and-logging-requirement