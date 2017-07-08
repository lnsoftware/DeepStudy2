package sss;

/**
 * Created by hg on 2017/7/7.
 */
public class GetFieldTest {
    private Integer val;

    public static void main(String[] args) {
        GetFieldTest test = new GetFieldTest();
        test.setVal(1);
        Integer tmp = test.getVal();
        System.out.println(tmp);
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
}
