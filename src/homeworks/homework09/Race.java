package homeworks.homework09;

import homeworks.homework09.car.Car;

import java.util.Arrays;
import java.util.Objects;

public class Race {
    private int distance;

    private String route;

    private int prizeFound;

    private Car[] members;



    public Race(int prizeFound, Car[] members, String route, int distance) {
        this.prizeFound = prizeFound;
        this.members = members;
        this.route = route;
        this.distance = distance;
    }
    public Race() {
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Car[] getMembers() {
        return members;
    }

    public void setMembers(Car[] members) {
        this.members = members;
    }

    public int getPrizeFound() {
        return prizeFound;
    }

    public void setPrizeFound(int prizeFound) {
        this.prizeFound = prizeFound;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Race race = (Race) o;
        return distance == race.distance && prizeFound == race.prizeFound && Objects.equals(route, race.route) && Objects.deepEquals(members, race.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, route, prizeFound, Arrays.hashCode(members));
    }

    @Override
    public String toString() {
        return "Race{" +
                "distance=" + distance +
                ", route='" + route + '\'' +
                ", prizeFound=" + prizeFound +
                ", members=" + Arrays.toString(members) +
                '}';
    }
}
