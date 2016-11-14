import java.util.*;

public class ReconstructItinerary {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][] tickets = {{"JFK","ATL"},{"JFK","SFO"},{"SFO","JFK"}};
		ReconstructItinerary r = new ReconstructItinerary();
		List<String> list = r.findItinerary(tickets);
		for (String str : list) {
			System.out.println(str);
		}

	}
	
	LinkedList<String> list = new LinkedList<String>();
	
	public List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
        readTicket(tickets, map);

        helper(map, "JFK");
        return list;
    }
    
    private void helper(Map<String, PriorityQueue<String>> map, String start) {
        while (map.containsKey(start) && !map.get(start).isEmpty()) {
        	String next = map.get(start).poll();
            helper(map, next);
        }
        list.addFirst(start);
        //return true;
    }
    
    private void readTicket(String[][] tickets, Map<String, PriorityQueue<String>> map){
        for (String[] ticket : tickets) {

            if (map.containsKey(ticket[0])) {
                map.get(ticket[0]).add(ticket[1]);
            } else {
                PriorityQueue<String> val = new PriorityQueue<String>();
                val.add(ticket[1]);
                map.put(ticket[0], val);
            }
        }

    }
    

}
