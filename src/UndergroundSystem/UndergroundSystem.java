package UndergroundSystem;

import java.util.HashMap;
import java.util.Map;

public class UndergroundSystem {
    //https://leetcode.com/problems/design-underground-system/
    //51 / 52 test cases passed.
    HashMap<Integer, Passenger> passengers;

    public UndergroundSystem() {
        passengers = new HashMap<Integer, Passenger>();
    }

    public void checkIn(int id, String stationName, int t) {

        Passenger existingPassenger = getPassenger(id);

        //create a new passenger log if they dont exist
        //if they exist by have checked out.
        if(existingPassenger == null )
        {
            Passenger newPassenger = new Passenger(id,stationName,t);
            passengers.put(id, newPassenger);
        }
        else
        if (existingPassenger.getLastStationVisit().getCheckOutTime() < t)
        {
            existingPassenger.checkInStationVisits(stationName, t, null, 0);
        }
    }

    public void checkOut(int id, String stationName, int t) {

        //find the passenger with the index id
        Passenger updatePassenger = getPassenger(id);

        //set the passengers checkout time.
        if(updatePassenger != null
                && updatePassenger.getLastStationVisit().getCheckOutTime() == 0
                && updatePassenger.getLastStationVisit().getCheckInTime() <= t)
        {
            updatePassenger.checkOutStationVisits(stationName,t);
        }

    }

    private Passenger getPassenger(int id) {
        Map.Entry<Integer, Passenger> foundPassenger = passengers.entrySet().stream()
                .filter(passenger -> id == passenger.getKey())
                .findAny()
                .orElse(null);

        if (foundPassenger == null)
            return null;
        else
            return foundPassenger.getValue();
    }

    public double getAverageTime(String startStation, String endStation)
    {
        double average = passengers.entrySet().stream()
                .filter((passenger) -> passenger.getValue().hasBeenOnThisRoute(startStation,endStation))
                .mapToDouble(x -> x.getValue().getTimeOnRoutes(startStation,endStation))
                .average()
                .getAsDouble();

        return average;
    }
}

/**
 * Your UndergroundSystem.UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem.UndergroundSystem obj = new UndergroundSystem.UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */