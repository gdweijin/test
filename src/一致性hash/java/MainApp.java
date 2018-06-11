package 一致性hash.java;

import java.util.HashSet;
import java.util.Set;


public class MainApp {

    public static void main(String[] args) {

        Set<String> nodes = new HashSet<String>();
        
        //服务器列表，3个服务器，放入一致性hash环
        nodes.add("10.10.80");
        nodes.add("10.10.70");
        nodes.add("10.10.60");
        //初始化一致性hash
        ConsistentHash<String> consistentHash = new ConsistentHash<String>(new HashFunction(), 160, nodes);

        consistentHash.add("D");
        System.out.println(consistentHash.getSize());  //640

        System.out.println(consistentHash.get("1"));
        System.out.println(consistentHash.get("2"));
        System.out.println(consistentHash.get("3"));
        System.out.println(consistentHash.get("4"));
    }

}