package sss;

public class TestPrimitive {

    public static void main(String[] args) {
        Process process = new Process();
        int age = 18;
        System.out.println(age);
        process.function3(age);
        System.out.println(age);

        int a = 2;
        System.out.println(a);
    }
}

class Process {

    private Integer val = 10;

    public void function3(int a) {
        a = 2;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }
}