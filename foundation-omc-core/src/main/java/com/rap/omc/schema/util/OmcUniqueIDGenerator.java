/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcUniqueIDGenerator.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 2. 3.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.terracotta.statistics.jsr166e.ThreadLocalRandom;

/**
 * <pre>
 * Class : OmcUniqueIDGenerator
 * Description : MAC Address 기반의 Unique ID를 생성함.
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcUniqueIDGenerator {
    private static final char[] ary = "012345678-9ABCDEFGHIJKLM_NOPQRSTUVW$XYZabcdefghijklm~nopqrstuvwxyz".toCharArray();
    public static String getObid(){
        OmcUniqueIDGenerator idGenerator = new OmcUniqueIDGenerator();
        Random rand = getRandomObj();
        //StringBuffer str = new StringBuffer();
        //return(str.append(idGenerator.getUniqueValue(rand,ary)).append(OmcSchemaConstants.C_SYSTEM_MAC_ADDRESS).toString());
        return(idGenerator.getUniqueValue(rand,ary));
    }
    private static synchronized Random getRandomObj() {
        return new Random(System.nanoTime());
    }
    public static List<String> getRandomName(int count,int length){
        OmcUniqueIDGenerator idGenerator = new OmcUniqueIDGenerator();
        Random rand = new Random(System.nanoTime());
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < count ; i++){
            list.add(getRandomNameSub(idGenerator,rand,length));
        }
        return list;
    }
    private static String getRandomNameSub(OmcUniqueIDGenerator idGenerator,Random rand,int length){
        
        StringBuffer str = new StringBuffer();
        for(int i= 0 ; i < length ; i++){
            str.append(ary[idGenerator.randInt(rand,0, 65)]);
        }
        return(str.toString());
    }
    /**
     * MAC Address에 대한 Unique Value를 생성하여 Return함.
     *
     * @return
     */
    public static String getMACAddress() {
        try {
            NetworkInterface network = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            byte[] macAddress        = network.getHardwareAddress();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < macAddress.length; i++) {
                sb.append(getToRadixString(macAddress[i] + 1987,62));
            }
            return(sb.toString().substring(6));
        } catch (UnknownHostException e) {
            e.printStackTrace();

        } catch (SocketException e){
            e.printStackTrace();
        }
        return("AAAAAA");
    }
    /**
     * 
     *
     * @param rand Random함수
     * @param ary 생성돠어질 Char들의 집합
     * @return
     */
    private String getUniqueValue(Random rand,char[] ary) {
        StringBuffer strBuf = new StringBuffer();
//        strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
//        strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
//        strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
//        strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
        
        if(rand.nextInt()%2 == 0){
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
            strBuf.append(ary[randInt(rand,0, 65)]);
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
            strBuf.append(ary[randInt(rand,0, 65)]);
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
        }else{
            strBuf.append(ary[randInt(rand,0, 65)]);
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
            strBuf.append(ary[randInt(rand,0, 65)]);
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
            strBuf.append(ary[randInt(rand,0, 65)]);
        }
        if(rand.nextInt()%2 == 0){
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
            strBuf.append(ary[randInt(rand,0, 65)]);
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
            strBuf.append(ary[randInt(rand,0, 65)]);
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
        }else{
            strBuf.append(ary[randInt(rand,0, 65)]);
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
            strBuf.append(ary[randInt(rand,0, 65)]);
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
            strBuf.append(ary[randInt(rand,0, 65)]);
        }
        if(rand.nextInt()%2 == 0){
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
            strBuf.append(ary[randInt(rand,0, 65)]);
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
            strBuf.append(ary[randInt(rand,0, 65)]);
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
        }else{
            strBuf.append(ary[randInt(rand,0, 65)]);
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
            strBuf.append(ary[randInt(rand,0, 65)]);
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
            strBuf.append(ary[randInt(rand,0, 65)]);
        }
        if(rand.nextInt()%2 == 0){
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
            strBuf.append(ary[randInt(rand,0, 65)]);
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
            strBuf.append(ary[randInt(rand,0, 65)]);
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
        }else{
            strBuf.append(ary[randInt(rand,0, 65)]);
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
            strBuf.append(ary[randInt(rand,0, 65)]);
            strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
            strBuf.append(ary[randInt(rand,0, 65)]);
        }
        

//        strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
//        strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
//        strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
//        strBuf.append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]).append(ary[ThreadLocalRandom.current().nextInt(0, 65)]);
//        
        
        //        
//        strBuf.append(ary[randInt(rand,0, 65)]).append(ary[randInt(rand,0, 65)]).append(ary[randInt(rand,0, 65)]).append(ary[randInt(rand,0, 65)]).append(ary[randInt(rand,0, 65)]);
//        strBuf.append(ary[randInt(rand,0, 65)]).append(ary[randInt(rand,0, 65)]).append(ary[randInt(rand,0, 65)]).append(ary[randInt(rand,0, 65)]).append(ary[randInt(rand,0, 65)]);
//        strBuf.append(ary[randInt(rand,0, 65)]).append(ary[randInt(rand,0, 65)]).append(ary[randInt(rand,0, 65)]).append(ary[randInt(rand,0, 65)]).append(ary[randInt(rand,0, 65)]);
//        strBuf.append(ary[randInt(rand,0, 65)]).append(ary[randInt(rand,0, 65)]).append(ary[randInt(rand,0, 65)]).append(ary[randInt(rand,0, 65)]).append(ary[randInt(rand,0, 65)]);
        //strBuf.append(ary[randInt(rand,0, 61)]).append(ary[randInt(rand,0, 61)]).append(ary[randInt(rand,0, 61)]).append(ary[randInt(rand,0, 61)]);
        return(strBuf.toString());
    }
    /**
     * 
     *
     * @param rand Random함수
     * @param min 최소값
     * @param max 최대값
     * @return
     */
    private int randInt(Random rand,int min, int max) {
        return(rand.nextInt((max - min) + 1) + min);
    }
    /**
     * 62진법상에서의 값을 Return함.
     *
     * @param longDecVal
     * @param inputRadix
     * @return
     */
    private static String getToRadixString(long longDecVal,int inputRadix){
        char [] remainderStrArr = {'0','1','2','3','4','5','6','7','8','9',
                                   'A','B','C','D','E','F','G','H','I','J','K','L','M',
                                   'N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
                                   'a','b','c','d','e','f','g','h','i','j','k','l','m',
                                   'n','o','p','q','r','s','t','u','v','w','x','y','z'};
        StringBuffer radixString = new StringBuffer();
        boolean loop = true;
        long    radix = inputRadix;
        long    quotientVal = longDecVal;
        int []  remainderArr = new int[255];
        int     remainderArrIndex = 0;
        while(loop){
             if(quotientVal < radix){
                  remainderArr[remainderArrIndex] = (int)(quotientVal);
                  loop = false;
             }else{
                  remainderArr[remainderArrIndex] = (int)(quotientVal%radix);
                  quotientVal = (long)(quotientVal/radix);
                  remainderArrIndex++;
             }
        }
        for(int k = remainderArrIndex; k >= 0; k--){
            radixString.append(remainderStrArr[remainderArr[k]]);
        }
        return radixString.toString();
    }
}
