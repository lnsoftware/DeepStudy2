package switchcase;

public class SwitchStringTest {

    public static void main(String[] args) throws Exception {
        String str = "test";
        System.out.println("AaAa".hashCode());
        System.out.println("BBBB".hashCode());
        System.out.println("AaBB".hashCode());

        // 对于hashCode冲突情况
        switch (str) {
        case "AaAa":
            System.out.println("a");
            break;
        case "BBBB":
            System.out.println("b");
            break;
        case "AaBB":
            System.out.println("c");
            break;case "AaBB1":
                System.out.println("c");
                break;
        default:
            System.out.println("c");
            break;
        }
    }
}