package com.mystore.mystore.helper;

/**
 *
 * @author hinas
 */
public class Helper {
    
    public static String get10words(String description){
        
        String str[] = description.split(" ");
        String res="";
        if(str.length>10){
            for(int i=0;i<10;i++){
                res = res + str[i]+ " ";
            }
            
            return (res+"...");
        }else
            return (description + "...");
    }
    
}
