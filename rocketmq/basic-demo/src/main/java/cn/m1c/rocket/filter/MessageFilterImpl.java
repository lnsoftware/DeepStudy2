package cn.m1c.rocket.filter;


import org.apache.rocketmq.common.filter.FilterContext;
import org.apache.rocketmq.common.filter.MessageFilter;
import org.apache.rocketmq.common.message.MessageExt;

public class MessageFilterImpl implements MessageFilter {

//	@Override
	public boolean match(MessageExt msg) {
		String property = msg.getUserProperty("SequenceId");
		System.out.println(property);
		if (property != null) {
			int id = Integer.parseInt(property);
			if((id % 2) == 0) { 
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean match(MessageExt msg, FilterContext context) {
		return false;
	}
}
