package UndergroundSystem;

public class StationVisit {
    String checkInStation;
    String checkOutStation;
    int checkInTime;
    int checkOutTime;

    public StationVisit(int checkInTime, String checkInStation, int checkOutTime, String checkOutStation) {
        setCheckInStationName(checkInStation);
        setCheckInTime(checkInTime);
        setCheckOutStationName(checkOutStation);
        setCheckOutTime(checkOutTime);
    }

    public int getCheckInTime() {
        return this.checkInTime;
    }

    public void setCheckInTime(int t) {
        this.checkInTime = t;
    }

    public int getCheckOutTime() {
        return this.checkOutTime;
    }

    public void setCheckOutTime(int t) {
        this.checkOutTime = t;
    }

    public String getCheckInStationName() {
        return this.checkInStation;
    }

    public void setCheckInStationName(String stationName) {
        this.checkInStation = stationName;
    }

    public String getCheckOutStationName() {
        return this.checkOutStation;
    }

    public void setCheckOutStationName(String stationName) {
        this.checkOutStation = stationName;
    }

    public double getTimeAtStation() {
        return (getCheckOutTime() - getCheckInTime());
    }

    public boolean isStation(String start, String end) {
        if (start.equals(getCheckInStationName()) && end.equals(getCheckOutStationName()))
            return true;
        else
            return false;
    }
}
