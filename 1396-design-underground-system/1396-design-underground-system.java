import java.util.*;

class UndergroundSystem {

    // id → (station, time)
    Map<Integer, Pair> checkInMap;

    // route → [totalTime, count]
    Map<String, int[]> routeMap;

    class Pair {
        String station;
        int time;

        Pair(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        routeMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new Pair(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Pair p = checkInMap.get(id);

        String route = p.station + "->" + stationName;
        int travelTime = t - p.time;

        routeMap.putIfAbsent(route, new int[2]);
        int[] data = routeMap.get(route);

        data[0] += travelTime; // total time
        data[1] += 1;          // count
    }

    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "->" + endStation;
        int[] data = routeMap.get(route);

        return (double) data[0] / data[1];
    }
}