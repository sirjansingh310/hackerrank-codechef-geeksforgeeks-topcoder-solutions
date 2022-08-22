// https://leetcode.com/problems/reconstruct-itinerary/
class Solution {
    private List<String> finalList;
    
    public List<String> findItinerary(List<List<String>> tickets) {
        finalList = new ArrayList<>();
        Map<String, List<List<String>>> graph = new HashMap<>();
        
        int ticketId = 0;
        for (List<String> ticket : tickets) {
            List<List<String>> list = graph.computeIfAbsent(ticket.get(0), x -> new ArrayList<>());
            List<String> ticketDetails = new ArrayList<>();
            ticketDetails.add(ticket.get(0));
            ticketDetails.add(ticket.get(1));
            ticketDetails.add("" + ticketId);
            list.add(ticketDetails);
            ticketId++;
        }
        
        for (Map.Entry<String, List<List<String>>> entry : graph.entrySet()) {
            Collections.sort(entry.getValue(), (l1, l2) -> l1.get(1).compareTo(l2.get(1)));// sort by destination
        }
        
        
        dfs("JFK", tickets.size(), new LinkedList<>(), graph, new HashSet<>());
        return finalList;
    }
    
    private boolean dfs(String airport, int allTicketsSize, LinkedList<List<String>> visitedList, Map<String, List<List<String>>> graph, Set<String> seenTicketIds) {
        
        if (visitedList.size() == allTicketsSize) {
            if (finalList.size() > 1) {
                return true; // as we found it already and it was lexigraphically sorted
            }
            
            int count = 0;
            List<String> resultAirports = new ArrayList<>();
            for (List<String> list : visitedList) {
                resultAirports.add(list.get(0));
                if (count == visitedList.size() - 1) {
                    resultAirports.add(list.get(1));
                }
                count++;
            }
            
            finalList = resultAirports;
            return true;
        }
        
        boolean didFly = false;
        if (graph.containsKey(airport)) {
            for (List<String> next : graph.get(airport)) {
                if (!seenTicketIds.contains(next.get(2))) {
                    seenTicketIds.add(next.get(2));
                    visitedList.add(next);
                    if (!dfs(next.get(1), allTicketsSize, visitedList, graph, seenTicketIds)) {
                        // backtrack if we don't get an ans
                        visitedList.removeLast();
                        seenTicketIds.remove(next.get(2));
                        
                    }
                }
            }            
        }
        
        return didFly;
    }
    
}
