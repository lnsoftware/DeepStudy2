package baby.btrace;

public class CaseObject {

    private static int sleepTotalTime = 0;
    private int sleepTotalTime2 = 0;

    public boolean execute(int sleepTime) throws Exception {
        System.out.println("sleep: " + sleepTime);
        sleepTotalTime += sleepTime;
        sleepTotalTime2 = sleepTotalTime + 1;
        sleep(sleepTime);
        if (sleepTime % 2 == 0)
            return true;
        else
            return false;
    }

    public void sleep(int sleepTime) throws Exception {
        Thread.sleep(sleepTime);
    }

}