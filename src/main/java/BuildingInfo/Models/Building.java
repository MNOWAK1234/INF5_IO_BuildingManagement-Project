package BuildingInfo.Models;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class Building implements Entity {

    private Floor[] floors;

    /**
     * Public constructor of Building-Entity
     * @param floors array of Floor-Entity
     */
    public Building(Floor[] floors) {
        this.floors = floors;
    }

    /**
     * JSON floors getter of Building
     * @return array of Floor-Entity
     */
    @JsonProperty("floors")
    public Floor[] getFloors() {
        return floors;
    }

    /**
     * Public setter of floors
     * @param floors array of Floor-Entity
     */
    public void setFloors(Floor[] floors) {
        this.floors = floors;
    }

    /**
     * Calculate surface area of Building
     * @return surface area of Building
     */
    @Override
    public double calcSurfaceArea() {
        double sum = 0.0;
        for (Floor floor : this.floors) {
            sum += floor.calcSurfaceArea();
        }
        return sum;
    }

    /**
     * Calculate volume of Building
     * @return volume of Building
     */
    @Override
    public double calcVolume() {
        double sum = 0.0;
        for (Floor floor : this.floors) {
            sum += floor.calcVolume();
        }
        return sum;
    }

    /**
     * Calculate mean light intensity of Building per Floor
     * @return mean light intensity of Building per Floor
     */
    @Override
    public double calcLightIntensity() {
        double sumLightIntensity = 0.0;
        for (Floor floor : this.floors) {
            sumLightIntensity += floor.calcLightIntensity();
        }
        return sumLightIntensity / getFloors().length;
    }

    /**
     * Calculate mean energy consumption of Building per Floor
     * @return  mean energy consumption of Building per Floor
     */
    @Override
    public double calcEnergyConsumption() {
        double sumEnergyConsumption = 0.0;
        for (Floor floor : this.floors) {
            sumEnergyConsumption += floor.calcEnergyConsumption();
        }
        return sumEnergyConsumption / getFloors().length;
    }

    /**
     *
     * @param limit given maximum value of room energy consumption
     * @return array of Room-Entities which energy consumption is over the limit
     */
    @Override
    public List<String> findHighConsumption(double limit) {
        List<String> highConsumptionEntities = new ArrayList<>();
        for (Floor floor : this.getFloors()) {
            for (Room room : floor.getRooms()) {
                if (room.getEnergyConsumption() > limit){
                    highConsumptionEntities.add(room.getName());
                }
            }
        }
        return highConsumptionEntities;
    }
}
