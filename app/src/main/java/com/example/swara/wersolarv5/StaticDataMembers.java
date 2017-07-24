package com.example.swara.wersolarv5;

/**
 * Created by swara on 7/3/2017.
 */
public class StaticDataMembers {


    public static String serverInsertDataUrl = "http://192.168.2.198/wersolar/insertUser.php";
    public static String serverAuthDataUrl =   "http://192.168.2.198/wersolar/loginAuth.php";
    public static String serverInsertCustomerDataUrl =   "http://192.168.2.198/wersolar/insertCustomerData.php";
    public static String serverInsertPictureUrl =   "http://192.168.2.198/wersolar/imageHandler.php";
    public static String serverTestUrl =   "http://192.168.2.198/wersolar/test.php";
    public static String currentUserPhone =    new String();


    public static String [] clientFirstNamesArray = new String[0];//reinitialize array at runtime
    public static String [] clientLastNamesArray = new String[0];//reinitialize array at runtime
    public static String [] clientAddressArray = new String[0];//reinitialize array at runtime
    public static String [] clientTimeStampArray = new String[0];//reinitialize array at runtime
    public static String [] clientInputDateArray = new String[0];//reinitialize array at runtime

    public static String jsonResults =   new String();


}
