/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book;
import book.Location;

/**
 *
 * @author Monish De
 */
public class Cabs {
    private String source;
    private String destination;
    private String cabs;
    private String cabsNo;
    private String arrivalTime;
    private int index;

    public void printDetails() {
        System.out.println(String.format("Cabs %s from %s to %s", this.getCabsNo(), this.getSource(), this.getDestination()));
        
        System.out.println("Arrival : " + this.getArrivalTime());
        
    }

    public boolean searchCabs(String source, String destination) {
        return this.getSource().equalsIgnoreCase(source) && this.getDestination().equalsIgnoreCase(destination);
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * @return the cabs
     */
    public String getCabs() {
        return cabs;
    }

    /**
     * @param cabs the cabs to set
     */
    public void setCabs(String cabs) {
        this.cabs = cabs;
    }

    /**
     * @return the cabsNo
     */
    public String getCabsNo() {
        return cabsNo;
    }

    /**
     * @param cabsNo the cabsNo to set
     */
    public void setCabsNo(String cabsNo) {
        this.cabsNo = cabsNo;
    }

    /**
     * @return the arrivalTime
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * @param arrivalTime the arrivalTime to set
     */
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    
    
}
