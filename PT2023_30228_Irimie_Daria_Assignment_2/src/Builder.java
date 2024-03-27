import java.util.ArrayList;

public class Builder {

    private Settings s;
    public Builder(Settings s) {
        this.s = s;
    }
    private Client generateRandomClient(int ID) {

            int randomArrivalTime = (int)Math.floor(Math.random() * (this.s.getMaxArrivalTime() - this.s.getMinArrivalTime() + 1) + this.s.getMinArrivalTime());
            int randomServiceTime = (int)Math.floor(Math.random() * (this.s.getMaxServiceTime() - this.s.getMinServiceTime() + 1) + this.s.getMinServiceTime());

            Client c = new Client(ID, randomArrivalTime, randomServiceTime);
            return c;
    }

    // Metoda Math.random() genereaza un numar aleator intre 0 si 1.
    // Aceasta valoare este inmultita cu intervalul de timp de sosire, respectiv de serviciu
    // Se adauga la aceasta valoarea minima a timpului de sosire / serviciu


    public ArrayList<Client> RandomClients() {

        ArrayList<Client> clients = new ArrayList<Client>();

        for(int i = 0; i < this.s.getNrOfClients(); i++) {
            clients.add(generateRandomClient(i + 1));
        }

        return clients;
    }
}
