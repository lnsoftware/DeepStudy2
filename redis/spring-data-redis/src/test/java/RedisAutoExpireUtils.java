
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: longjuanfeng Date: 2017-11-29
 */
public class RedisAutoExpireUtils<ValueType, SetResponse> {
    private Logger logger = LoggerFactory.getLogger(RedisAutoExpireUtils.class);

    private Integer expire;

    //default size is expire
    private Integer blockSize;

    public RedisAutoExpireUtils(Integer expire) throws Exception {
        this.expire = expire;
    }

    public SetResponse setRedisValue(String key, ValueType value) {
        Integer nowTime = new Long(System.currentTimeMillis() / 1000).intValue();
        Integer blockTail = nowTime % blockSize;
        Integer stampPos = nowTime - blockTail;
        String timeStamp = String.valueOf(stampPos);
        logger.info("timeStamp of {} is {}", nowTime, timeStamp);
        String keyWithStamp = key + ":" + timeStamp;
        Boolean exists = redisExists.exists(keyWithStamp) == 1;
        SetResponse result = redisSetter.addValue(keyWithStamp, value);
        if (!exists) {
            Integer expireTime = expire + blockSize - blockTail;
            redisExpire.setExpire(keyWithStamp, expireTime);
            logger.info("set expire of {} is {}", nowTime, expireTime);
        }
        return result;
    }
