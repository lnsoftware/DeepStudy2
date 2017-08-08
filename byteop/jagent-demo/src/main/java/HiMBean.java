/**
 *定义一个普通的接口
 *
 * @author zhangwei_david
 * @version $Id: HiMBean.java, v 0.1 2015年1月24日 下午1:16:15 zhangwei_david Exp $
 */
public interface HiMBean {
    /**
     *打招呼
     */
    public void sayHello();

    /**
     * 加法计算器
     *
     * @param x
     * @param y
     * @return
     */
    public int add(int x, int y);

    /**
     * 获取名称
     *
     * @return
     */
    public String getName();

    /**
     *获取缓存大小
     *
     * @return
     */
    public int getCacheSize();

    /**
     *设置缓存大小
     *
     * @param size
     */
    public void setCacheSize(int size);
}