
如果不明白dashboard上展示的数据是什么，可以查看代码。

spring-cloud-netflix-hystrix-dashboard-1.2.0.RELEASE.jar
static.hystrix.components包含command及threadpool的实现

```
	<div class="tableRow">
			<div class="cell header left">Active</div>
			<div class="cell data left"><%= currentActiveCount%></div>
			
			<div class="cell header right">Max Active</div>
			<div class="cell data right"><%= addCommas(rollingMaxActiveThreads)%></div>
		</div>

		<div class="tableRow">
			<div class="cell header left">Queued</div>
			<div class="cell data left"><%= currentQueueSize %></div>
			<div class="cell header right">Executions</div>
			<div class="cell data right"><%= addCommas(rollingCountThreadsExecuted)%></div>
		</div>
		<div class="tableRow">
			<div class="cell header left">Pool Size</div>
			<div class="cell data left"><%= currentPoolSize %></div>
			<div class="cell header right">Queue Size</div>
			<div class="cell data right"><%= propertyValue_queueSizeRejectionThreshold %></div>
		</div>
```