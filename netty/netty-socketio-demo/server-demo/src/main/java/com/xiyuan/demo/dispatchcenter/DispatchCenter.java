package com.xiyuan.demo.dispatchcenter;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.xiyuan.demo.model.TestModel.Request;
import com.xiyuan.demo.util.ClassUtil;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DispatchCenter {

    private static DispatchCenter instance;

    //Netty提供的Channel管理方案
    private ChannelGroup channelGroup;

    //用于存储设备和channel的绑定关系
    private Map<String, Channel> deviceAndChannel;

    //用于存储Channel和Device的绑定关系，方便channel断开连接时，能快速地找到对应的设备和用户，并解除绑定关系
    private Map<Channel, String> channelAndDevice;

    //用于用户和设备的相互绑定，用到了BiMap，用到了google的guava包
    private BiMap<String, String> userAndDevice;

    //用于存储contoller实例
    private Map<String, BaseController> controllerMap;

    //用于存储request的method和ControllerAndMehod的对应关系
    private Map<String, ControllerAndMehod> methodMap;

    private DispatchCenter() {
        channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        deviceAndChannel = new HashMap<String, Channel>();
        channelAndDevice = new HashMap<Channel, String>();
        userAndDevice = HashBiMap.create(new HashMap<String, String>());

        controllerMap = new HashMap<String, BaseController>();
        methodMap = new HashMap<String, ControllerAndMehod>();

        Set<Class<?>> classes = ClassUtil.getClasses("com.xiyuan.demo.controller");//这里可以把路径写到配置文件里面，方便后续的重用
        for (Class<?> clas : classes) {
            if (BaseController.class == clas.getSuperclass()) {
                try {
                    BaseController controller = (BaseController) clas.newInstance();
                    controller.setDispatchCenter(this);
                    controllerMap.put(clas.getName(), controller);

                    Method[] methods = clas.getMethods();
                    for (int i = 0; i < methods.length; i++) {
                        Method temp = methods[i];
                        DispatchMethod dispatchMethod = temp.getAnnotation(DispatchMethod.class);
                        if (dispatchMethod != null) {
                            String path = dispatchMethod.path();
                            if (path.equals("")) {
                                path = temp.getName();
                            }
                            if (methodMap.containsKey(path)) {
                                try {
                                    throw (new Exception("存在名字或路径重复的dispatchMethod"));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                System.exit(1);
                            } else {
                                System.out.println("path mapping: path=" + path + "  controller=" + controller.getClass().getName() + "  method=" + temp.getName());
                                methodMap.put(path, new ControllerAndMehod(controller, temp));
                            }
                        }
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static final DispatchCenter instance() {
        if (instance == null) {
            instance = new DispatchCenter();
        }
        return instance;
    }

    /**
     * 新连接的通道，将通道添加到ChannelGroup中
     *
     * @param newChannel
     */
    public void addChannel(Channel newChannel) {
        channelGroup.add(newChannel);
    }

    /**
     * 绑定设备和通道
     *
     * @param deviceId
     * @param channel
     */
    public void bandDeviceAndChannel(String deviceId, Channel channel) {
        //存储设备和channel的绑定关系
        deviceAndChannel.put(deviceId, channel);

        //存储ChannelId和Device的绑定关系
        channelAndDevice.put(channel, deviceId);
    }

    public Channel getChannelByDevice(String deviceId) {
        return deviceAndChannel.get(deviceId);
    }

    /**
     * 绑定用户和设备
     *
     * @param userId
     * @param deviceId
     */
    public void bandUserAndDevice(String userId, String deviceId) {
        userAndDevice.put(userId, deviceId);
    }

    /**
     * channel断开连接，移除channel和相应的绑定关系
     *
     * @param channel
     */
    public void removeChannel(Channel channel) {
        if (channel == null) {
            return;
        }

        String deviceId = channelAndDevice.get(channel);
        if (deviceId != null) {
            deviceAndChannel.remove(deviceId);
            channelAndDevice.remove(channel);
            userAndDevice.inverse().remove(deviceId);
        }
        channelGroup.remove(channel);
    }

    public BaseController getController(Class<?> clas) {
        String clasName = clas.getName();
        return controllerMap.get(clasName);
    }

    public void dispatch(Request request) {
        ControllerAndMehod controllerAndMehod = methodMap.get(request.getMethod());
        if (controllerAndMehod != null
                && controllerAndMehod.controller != null
                && controllerAndMehod.method != null) {
            controllerAndMehod.controller.dispatchAction(controllerAndMehod.method, request);
        }
    }

}
