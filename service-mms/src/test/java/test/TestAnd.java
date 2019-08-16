package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class TestAnd {
    
    @Test
    public void ttt(){
        int i =10;//1010
        System.out.println(i<<3);
    }
    
    @Test
    public void eee(){
        Map m = new HashMap();
        m.put("1", "1");
        m.put("1", "2");
        System.out.println(m.get("1"));
        
        Set keySet = m.entrySet();
        for (Object object : keySet) {
            System.out.println(object+"---");
        }
        
        String s = "2019-07";
        String[] split = s.split("-");
        int parseInt = Integer.parseInt(split[1]);
        String s1=split[0]+"-"+parseInt;
        System.out.println(s1);
        /*String s1="";
            if(split[5].equals("0")){
                for(int i =0 ;i<split.length ;i++){
                    if(i==5){
                        continue;
                    }
                    s1 += split[i];
                }
            }else{
                s1=s;
            }*/
//        System.out.println(s1);
    }
}
