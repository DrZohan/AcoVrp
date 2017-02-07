package model;

import java.io.Serializable;
import java.util.LinkedList;

import static util.DataUtil.distance;

/**
 * Created by ab792 on 2017/1/18.
 * 代表一个完整的解
 */
public class Solution implements Serializable {
    private LinkedList<Truck> truckSols = new LinkedList<Truck>(); //卡车集合(路径集合)
    private double cost;    //整个解的代价
    private double realCost;    //扣掉truck penalty后的cost
    private int truckNum;   //卡车数量
    private int overLoadCount;  //超重卡车数
    private Truck firstTruck;   //第一量车
    private Truck lastTruck;    //最后一辆车
    private Truck currentTruck; //当前车辆
    private int currentCicycle;     //当前在第几个循环（即第几辆车）

    public Solution() {
        currentCicycle = 0;
        Truck firstTruck = new Truck(currentCicycle);
        truckSols.add(firstTruck);
        currentTruck = firstTruck;
    }

    /**
     * 是否是一个好的解（1.解集中没有超载车辆2.是否违反距离约束，暂时不用）
     *
     * @return
     */
    public boolean isGoodSolution() {
        for (Truck truck : truckSols) {
            if (truck.isOverLoad())
                return false;
        }
        return true;
    }

    /**
     * 向当前解中添加客户
     *
     * @param currentCus
     */
    public void addCus(int currentCus) {
        if (currentCus!=0){
            if (currentCicycle >= truckSols.size()) {
                Truck truck = new Truck(currentCicycle);
                truck.addCus(currentCus);
                addTruck(truck);
            }else {
                truckSols.get(currentCicycle).addCus(currentCus);
            }
        }
    }

    /**
     * 向当前解中添加车辆
     * @param truck
     */
    public void addTruck(Truck truck){
        truckSols.add(truck);
        currentTruck = truck;
    }

    /**
     * 递增当前循环
     */
    public void increaseLoop(){
        ++currentCicycle;
    }

    /**
     * 计算一个解的路径长度
     *
     * @return
     */
    public double calCost() {
        double len = 0;
        if (truckSols.size()>0){
            for (Truck truck : truckSols){
                len+=truck.calCost();
            }
        }
        return len;
    }

    /**
     * 返回解中路径数量
     * @return
     */
    public int size(){
        return truckSols.size();
    }
    /**
     * 是否超载
     *
     * @return
     */
    public boolean isOverLoad() {
        return overLoadCount > 0;
    }


    /*********getters and setters**********/
    public LinkedList<Truck> getTruckSols() {
        return truckSols;
    }

    public double getCost() {
        return cost;
    }

    public double getRealCost() {
        return realCost;
    }

    public int getTruckNum() {
        return truckNum;
    }

    public int getOverLoadCount() {
        return overLoadCount;
    }

    public void setTruckSols(LinkedList<Truck> truckSols) {
        this.truckSols = truckSols;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setRealCost(double realCost) {
        this.realCost = realCost;
    }

    public void setTruckNum(int truckNum) {
        this.truckNum = truckNum;
    }

    public void setOverLoadCount(int overLoadCount) {
        this.overLoadCount = overLoadCount;
    }

    public Truck getFirstTruck() {
        firstTruck = truckSols.getFirst();
        return firstTruck;
    }

    public void setFirstTruck(Truck firstTruck) {
        this.firstTruck = firstTruck;
    }

    public void setLastTruck(Truck lastTruck) {
        this.lastTruck = lastTruck;
    }

    public Truck getLastTruck() {
        lastTruck = truckSols.getLast();
        return lastTruck;
    }

    public int getCurrentCicycle() {
        return currentCicycle;
    }

    public void setCurrentCicycle(int currentCicycle) {
        this.currentCicycle = currentCicycle;
    }

    public void setCurrentTruck(Truck currentTruck) {
        this.currentTruck = currentTruck;
    }

    public Truck getCurrentTruck() {
        /*******************/
        if (currentCicycle >= truckSols.size()) {
            Truck truck = new Truck(currentCicycle);
            addTruck(truck);
        }
        return currentTruck;
    }

    /*********getters and setters**********/

    @Override
    public String toString() {
        return "Solution{" +
                "truckSols=" + truckSols +
                ", overLoadCount=" + overLoadCount +
                '}';
    }
}