1、handler执行完成后，执行下一个handler，调用ctx.fireChannelRead(msg)方法

2、向handler中写数据，必须转换成ByteBuf，除非有对应的Encoder。

3、每个handler中的context是独立的，互不影响。

4、如何触发：outboundhandler：
	encoder 必须在包含write方法的decoder之前配置
	decoder中必须调用write方法，才能触发encoder

5、inbound的执行顺序：先放入的先执行，即按照12345的顺序执行。
outbound的执行顺序：后放入的先执行，即按照54321的顺序执行。
	












