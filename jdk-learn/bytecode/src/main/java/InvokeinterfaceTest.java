/**
 * 调用接口方法
 * Created by wangqinghui on 2018/2/12.
 */
public class InvokeinterfaceTest {
    public static void main(String[] args) {
        IUserService userService = new UserServiceImpl();
        //INVOKEINTERFACE IUserService.getByHello (Ljava/lang/String;)Ljava/lang/String;
        userService.getByHello("hg");
    }
}

interface IUserService{
    public String  getByHello(String name);
}

class UserServiceImpl implements IUserService{

    @Override
    public String getByHello(String name) {
        return "hello:"+name;
    }
}