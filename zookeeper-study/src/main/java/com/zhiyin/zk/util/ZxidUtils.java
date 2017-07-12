package com.zhiyin.zk.util;

//http://blog.csdn.net/quhongwei_zhanqiu/article/details/45647771

public class ZxidUtils {

     static public long getEpochFromZxid(long zxid) {

         return zxid >> 32L;

     }

     static public long getCounterFromZxid(long zxid) {

         return zxid & 0xffffffffL;

     }

     static public long makeZxid(long epoch,long counter) {

         return (epoch << 32L) | (counter & 0xffffffffL);

     }

     static public String zxidToString(long zxid) {

         return Long.toHexString(zxid);

     }

    public static void main(String[] args) {
        Long val = Long.parseLong("40000058c",16);
        System.out.println(val);

        System.out.println(ZxidUtils.zxidToString(val));
        System.out.println(ZxidUtils.getEpochFromZxid(val));
        System.out.println(ZxidUtils.getCounterFromZxid(val));

        System.out.println(Long.toBinaryString(val));

        Long val2 = Long.parseLong("1022a3d1d",16);

        System.out.println(Long.toBinaryString(val2));
    }
}

