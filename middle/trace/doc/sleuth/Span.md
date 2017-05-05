
Sleuth tracer could create child span.

org.springframework.cloud.sleuth.trace.DefaultTracer.createSpan(java.lang.String)

```
	@Override
	public Span continueSpan(Span span) {
		if (span != null) {
			this.spanLogger.logContinuedSpan(span);
		}
		else {
			return null;
		}
		Span newSpan = createContinuedSpan(span, SpanContextHolder.getCurrentSpan());
		SpanContextHolder.setCurrentSpan(newSpan);
		return newSpan;
	}
```

when create new span, first get current span, and put current span in new span.



