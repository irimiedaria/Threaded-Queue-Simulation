import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldNrClients;
    private JTextField textFieldMinArrivalTime;
    private JTextField textFieldMinServiceTime;
    private JTextField textFieldNrQueues;
    private JTextField textFieldMaxArrivalTime;
    private JTextField textFieldMaxServiceTime;
    private JTextField textFieldSimulationInterval;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 820, 504);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel labelTitlu = new JLabel("Gestionarea cozilor");
        labelTitlu.setFont(new Font("Tahoma", Font.BOLD, 15));
        labelTitlu.setBounds(306, 10, 210, 39);
        contentPane.add(labelTitlu);

        JLabel labelNrClients = new JLabel("Number of clients");
        labelNrClients.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelNrClients.setBounds(10, 68, 118, 19);
        contentPane.add(labelNrClients);

        textFieldNrClients = new JTextField();
        textFieldNrClients.setBounds(155, 69, 96, 19);
        contentPane.add(textFieldNrClients);
        textFieldNrClients.setColumns(10);

        JLabel labelNrQueues = new JLabel("Number of queues");
        labelNrQueues.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelNrQueues.setBounds(277, 71, 118, 13);
        contentPane.add(labelNrQueues);

        JLabel labelSimulationInterval = new JLabel("Simulation interval");
        labelSimulationInterval.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelSimulationInterval.setBounds(541, 71, 118, 13);
        contentPane.add(labelSimulationInterval);

        JLabel labelMinArrivalTime = new JLabel("Minimum arrival time");
        labelMinArrivalTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelMinArrivalTime.setBounds(10, 121, 118, 13);
        contentPane.add(labelMinArrivalTime);

        JLabel labelMaxArrivalTime = new JLabel("Maximum arrival time");
        labelMaxArrivalTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelMaxArrivalTime.setBounds(277, 121, 118, 13);
        contentPane.add(labelMaxArrivalTime);

        JLabel labelMinServiceTime = new JLabel("Minimum service time ");
        labelMinServiceTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelMinServiceTime.setBounds(10, 171, 118, 13);
        contentPane.add(labelMinServiceTime);

        JLabel labelMaxServiceTime = new JLabel("Maximum service time ");
        labelMaxServiceTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelMaxServiceTime.setBounds(277, 171, 129, 13);
        contentPane.add(labelMaxServiceTime);

        textFieldMinArrivalTime = new JTextField();
        textFieldMinArrivalTime.setColumns(10);
        textFieldMinArrivalTime.setBounds(155, 119, 96, 19);
        contentPane.add(textFieldMinArrivalTime);

        textFieldMinServiceTime = new JTextField();
        textFieldMinServiceTime.setColumns(10);
        textFieldMinServiceTime.setBounds(155, 169, 96, 19);
        contentPane.add(textFieldMinServiceTime);

        textFieldNrQueues = new JTextField();
        textFieldNrQueues.setColumns(10);
        textFieldNrQueues.setBounds(420, 69, 96, 19);
        contentPane.add(textFieldNrQueues);

        textFieldMaxArrivalTime = new JTextField();
        textFieldMaxArrivalTime.setColumns(10);
        textFieldMaxArrivalTime.setBounds(420, 119, 96, 19);
        contentPane.add(textFieldMaxArrivalTime);

        textFieldMaxServiceTime = new JTextField();
        textFieldMaxServiceTime.setColumns(10);
        textFieldMaxServiceTime.setBounds(420, 169, 96, 19);
        contentPane.add(textFieldMaxServiceTime);

        textFieldSimulationInterval = new JTextField();
        textFieldSimulationInterval.setColumns(10);
        textFieldSimulationInterval.setBounds(657, 69, 96, 19);
        contentPane.add(textFieldSimulationInterval);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(49, 316, 704, 129);
        contentPane.add(scrollPane);

        JTextArea textArea = new JTextArea();
        scrollPane.setViewportView(textArea);

        JButton btnStartButton = new JButton("START");
        btnStartButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnStartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int nrClients = Integer.parseInt(textFieldNrClients.getText());
                int nrQueues = Integer.parseInt(textFieldNrQueues.getText());
                int simulationInterval = Integer.parseInt(textFieldSimulationInterval.getText());
                int minArrivalTime = Integer.parseInt(textFieldMinArrivalTime.getText());
                int maxArrivalTime = Integer.parseInt(textFieldMaxArrivalTime.getText());
                int minServiceTime = Integer.parseInt(textFieldMinServiceTime.getText());
                int maxServiceTime = Integer.parseInt(textFieldMaxServiceTime.getText());

                Settings s = new Settings(nrClients, nrQueues, simulationInterval, minArrivalTime, maxArrivalTime, minServiceTime, maxServiceTime);
                Builder b = new Builder(s);
                ArrayList<Client> clients;
                clients = b.RandomClients();

                for(Client client : clients) {
                   System.out.println(client.toString());
                }

                SimulationThread simThr;
                simThr = new SimulationThread(s, clients, textArea);
                simThr.start();

            }
        });
        btnStartButton.setBounds(332, 223, 118, 30);
        contentPane.add(btnStartButton);
    }
}
