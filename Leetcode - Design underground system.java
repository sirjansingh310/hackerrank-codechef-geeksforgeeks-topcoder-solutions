//https://leetcode.com/explore/challenge/card/march-leetcoding-challenge-2021/590/week-3-march-15th-march-21st/3678/
class Trip {
    int startTime;
    int endTime;
    String startStation;
    String endStation;
}
class UndergroundSystem {
    private Map<Integer, List<Trip>> customerToTrips; // many to one mapping bw trip and customer. Imagine a trip table with trip details and FK customerId, which is used as index
    private Map<String, List<Trip>> startStationToTrips;// indexed startStation in this table as well,
    // as it will decrease our search space in trips table to calculate avg. We can index on start & end station for faster processing
    public UndergroundSystem() {
        customerToTrips = new HashMap<>();
        startStationToTrips = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        Trip trip = new Trip();
        trip.startTime = t;
        trip.startStation = stationName;
        
        if (customerToTrips.containsKey(id)) {
            customerToTrips.get(id).add(trip);
        } else {
            customerToTrips.put(id, new ArrayList<>(Collections.singletonList(trip)));
        }
        
        if (startStationToTrips.containsKey(stationName)) {
            startStationToTrips.get(stationName).add(trip);
        } else {
            startStationToTrips.put(stationName, new ArrayList<>(Collections.singletonList(trip)));
        }
    }
    
    public void checkOut(int id, String stationName, int t) {
        if (customerToTrips.containsKey(id)) {
            List<Trip> customerTrips = customerToTrips.get(id);
            Trip latestTrip = customerTrips.get(customerTrips.size() - 1);
            latestTrip.endTime = t;
            latestTrip.endStation = stationName;
        }
    }
    
    public double getAverageTime(String startStation, String endStation) {
        List<Trip> trips = startStationToTrips.get(startStation);
        if (trips != null) {
            double totalTime = 0;
            int count = 0;
            for (Trip trip : trips) {
                if (trip.endTime != 0 && endStation.equals(trip.endStation)) {
                    totalTime += (trip.endTime - trip.startTime);
                    count++;
                }
            }
            return totalTime / count;
        }
        return 0.00;
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
