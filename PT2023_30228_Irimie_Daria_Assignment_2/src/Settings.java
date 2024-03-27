public class Settings {
    private int nrOfClients;
    private int nrOfQueues;
    private int simulationTime;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int minServiceTime;
    private int maxServiceTime;

    public int getNrOfClients() {
        return nrOfClients;
    }

    public int getNrOfQueues() {
        return nrOfQueues;
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public int getMinArrivalTime() {
        return minArrivalTime;
    }

    public int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public int getMinServiceTime() {
        return minServiceTime;
    }

    public int getMaxServiceTime() {
        return maxServiceTime;
    }

    public Settings(int nrOfClients, int nrOfQueues, int simulationTime, int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime) {
        this.nrOfClients = nrOfClients;
        this.nrOfQueues = nrOfQueues;
        this.simulationTime = simulationTime;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
    }

}
