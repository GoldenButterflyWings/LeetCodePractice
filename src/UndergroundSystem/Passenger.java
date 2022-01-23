package UndergroundSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Passenger {
    int id;
    List<StationVisit> visits;

    public Passenger(int id, String checkInStation, int checkInTime) {
        setId(id);
        visits = new ArrayList<>();
        checkInStationVisits(checkInStation, checkInTime, null, 0);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<StationVisit> getStationVisits() {
        return this.visits;
    }

    public StationVisit getLastStationVisit() {
        if (getStationVisits().isEmpty())
            return null;
        else {
            int lastVisit = this.visits.size() - 1;
            return this.visits.get(lastVisit);
        }
    }

    public void checkInStationVisits(String checkInStation, int checkInTime, String checkOutStation, int checkOutTime) {
        if (visits.isEmpty()) {
            visits.add(new StationVisit(checkInTime, checkInStation, checkOutTime, checkOutStation));
        } else {
            //check if the passenger has another visit then update
            for (int i = 0; i < visits.size(); i++) {
                StationVisit tempVisit = visits.get(i);

                if (tempVisit.getCheckOutTime() > 0) {
                    visits.add(new StationVisit(checkInTime, checkInStation, checkOutTime, checkOutStation));
                    break;
                }

            }
        }
    }

    public void checkOutStationVisits(String checkOutStation, int checkOutTime) {
        if (!visits.isEmpty()) {
            //check if the passenger has another visit then update
            for (int i = 0; i < visits.size(); i++) {
                StationVisit tempVisit = visits.get(i);

                if (tempVisit.getCheckOutTime() == 0) {
                    tempVisit.setCheckOutStationName(checkOutStation);
                    tempVisit.setCheckOutTime(checkOutTime);
                    break;
                }
            }
        }
    }

    public Boolean hasBeenOnThisRoute(String startStation, String endStation) {
        List<StationVisit> hasBeenToQueryRoute =
                getBeenToQueryRoute(startStation, endStation);

        if (hasBeenToQueryRoute.isEmpty()) //no matches where found
            return false;
        else
            return true;
    }

    private List<StationVisit> getBeenToQueryRoute(String startStation, String endStation) {
        return visits.stream()
                .filter((v) -> v.isStation(startStation, endStation) == true)
                .collect(Collectors.toList());
    }

    public Double getTimeOnRoutes(String startStation, String endStation) {
        Double sum =
                getBeenToQueryRoute(startStation, endStation)
                        .stream()
                        .mapToDouble(x -> x.getTimeAtStation())
                        .sum();

        return sum;
    }

}
