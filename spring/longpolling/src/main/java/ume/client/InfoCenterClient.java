package ume.client;

import ume.TableChangeLongPollService;

/**
 * Created by hg on 2017/5/18.
 */
public class InfoCenterClient {

    public static void main(String[] args) {

        new TableChangeLongPollService("http://localhost:8080/defer");

        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
