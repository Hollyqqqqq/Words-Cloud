package project.FrequencyCounts;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map.Entry;

class MapComparator implements Comparator<Entry<String, Integer>>
{

	@Override
	public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
		// TODO Auto-generated method stub
		return -(arg0.getValue()-arg1.getValue());
	}
	
}

public class FrequencyCounts {

     public FrequencyCounts(){
     }
        
     public ArrayList count(ArrayList<String> contents) {

         //计算频率
         //得到一个map映射
         Map<String, Integer> map = new HashMap<String, Integer>();
         for (String content : contents) {
             if (map.containsKey(content)) {
                 map.put(content, map.get(content) + 1);
             } else {
                 map.put(content, 1);
             }
         }
         //将map映射转换成list降序排列
         List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(map.entrySet());
         MapComparator c = new MapComparator();
         list.sort(c);

         ArrayList<String> resultOrder = new ArrayList<>();
         for (Entry<String, Integer> e : list) {
             //System.out.println(e.getKey() + ":" + e.getValue());
             resultOrder.add(e.getKey());
         }
         System.out.println("There have " + resultOrder.size() + " Chinese words to be generate");
         return resultOrder;
	}
}
