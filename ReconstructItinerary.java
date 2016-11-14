import java.util.*;

public class ReconstructItinerary {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][] tickets = {{"EZE","TIA"},{"EZE","HBA"},{"AXA","TIA"},{"JFK","AXA"},{"ANU","JFK"},{"ADL","ANU"},{"TIA","AUA"},{"ANU","AUA"},{"ADL","EZE"},{"ADL","EZE"},{"EZE","ADL"},{"AXA","EZE"},{"AUA","AXA"},{"JFK","AXA"},{"AXA","AUA"},{"AUA","ADL"},{"ANU","EZE"},{"TIA","ADL"},{"EZE","ANU"},{"AUA","ANU"}};
		
		ReconstructItinerary r = new ReconstructItinerary();
		List<String> list = r.findItinerary(tickets);
		for (String str : list) {
			System.out.println(str);
		}

	}
    public List<String> findItinerary(String[][] tickets) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        readTicket(tickets, map);

        String start = "JFK";
        List<String> list = new ArrayList<String>();
        list.add(start);
        helper(map, list, start);
        return list;
    }
    
    private boolean helper(Map<String, List<String>> map, List<String> list, String start) {
        if (map.isEmpty()) return true;
        if (!map.containsKey(start)) return false;
 
        List<String> dests = map.get(start);
        //if (dests.isEmpty()) return false;
        for (String next : dests) {
           // if (map.containsKey(next)) {
                list.add(next);
                List<String> tmp = new ArrayList<String>(dests);
                tmp.remove(next);
                if (tmp.isEmpty()) map.remove(start);
                else map.put(start, tmp);
                if (helper(map, list, next)) break;
                list.remove(list.size()-1);
                map.put(start, dests);
           // }
        }
        if (map.isEmpty()) return true;
        else return false;
        //return true;
    }
    
    private void readTicket(String[][] tickets, Map<String, List<String>> map){
        for (int i = 0; i < tickets.length; i++) {
            String[] pair = tickets[i];
            String from = pair[0];
            String to = pair[1];
            if (map.containsKey(from)) {
                map.get(from).add(to);
            } else {
                List<String> val = new ArrayList<String>();
                val.add(to);
                map.put(from, val);
            }
        }
        
        for (String key : map.keySet()) {
            List<String> val = map.get(key);
            Collections.sort(val);
            map.put(key, val);
        }
    }
    

}
