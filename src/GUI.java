import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class GUI {
    public static SuperTrumpGame game;
    public static STDeck deck;
    public static GUI gui;
    public Game stgame;
    public Player player;
    static Player[] players;
    static JLabel PlayedDeck;
    public static int numberOfPlayers;


    public static void SetupGame(final Container pane) throws IOException{

        final Color customColor = new Color(52, 86, 55);
        pane.setBackground(customColor);
        pane.setLayout(new BorderLayout());

        final JPanel panel = new JPanel(new GridBagLayout());

        panel.setBackground(customColor);
        pane.add(panel);
        JLabel welcomeMessage = new JLabel("Welcome to Mineral SuperTrumps! \n");
        JLabel numberPlayers = new JLabel("How many players would you like to play in this game?");
        panel.add(welcomeMessage);
        panel.add(numberPlayers);
        final JTextField textNumberOfPlayers = new JTextField();

        textNumberOfPlayers.setColumns(4);
        panel.add(textNumberOfPlayers);

        final JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numberPlayers = textNumberOfPlayers.getText();
                int numberOfPlayers = Integer.parseInt(numberPlayers);
                //Get the number of players from the input box
                SuperTrumpGame game = null;
                try {
                    game = new SuperTrumpGame(numberOfPlayers);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                int dealerNumber = game.selectDealer(numberOfPlayers);
                game.dealHand(numberOfPlayers);
                int firstPlayer = 0;

                pane.remove(panel);

                //Get human player and pass to playerview to view hand.
                Player humanPlayer = game.players[0];
                Player player1 = game.players[1];
                Player player2 = game.players[2];
                Player player3 = game.players[3];

                PlayerView view = new PlayerView(humanPlayer);
                view.setBackground(customColor);

                try {
                    BotView view1 = new BotView(player1);
                    pane.add(view1, BorderLayout.WEST);
                    view1.setBackground(customColor);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {
                    BotView view2 = new BotView(player2);
                    pane.add(view2, BorderLayout.NORTH);
                    view2.setBackground(customColor);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {
                    BotView view3 = new BotView(player3);
                    pane.add(view3, BorderLayout.EAST);
                    view3.setBackground(customColor);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                //Font for labels.
                Font font = new Font("Verdana", Font.PLAIN, 12);

                //Adds players cards to the screen.
                JPanel playersHand = new JPanel(new BorderLayout());
                playersHand.setBackground(Color.black);
                playersHand.add(view, BorderLayout.NORTH);

                JPanel deckPanel = new JPanel();
                deckPanel.setBackground(customColor);
                deckPanel.setLayout(new BorderLayout());
                deckPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                pane.add(deckPanel, BorderLayout.CENTER);

                //Button to click for user to pickup a card
                JButton pass = new JButton("Pass!");
                final SuperTrumpGame finalGame = game;
                pass.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent p) {
                        new ShowCard(finalGame.playerHasPassed());
                        pane.revalidate();
                        pane.repaint();
                    }
                });
                deckPanel.add(pass, BorderLayout.SOUTH);

                JPanel trumpPanel = new JPanel();
                trumpPanel.setLayout(new GridBagLayout());
                trumpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


                JButton hardnessButton = new JButton("Hardness");
                final SuperTrumpGame finalGame1 = game;
                hardnessButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent h) {
                        SuperTrumpGame.currentGame.currentAttribute = "Hardness";
                        finalGame1.getAttribute("Hardness");

                    }
                });
                JButton specificGravityButton = new JButton("Specific Gravity");
                specificGravityButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent sp) {
                        SuperTrumpGame.currentGame.currentAttribute = "Specific Gravity";
                        finalGame1.getAttribute("Specific Gravity");

                    }
                });
                JButton cleavageButton = new JButton("Cleavage");
                cleavageButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent cl) {
                        SuperTrumpGame.currentGame.currentAttribute = "Cleavage";
                        finalGame1.getAttribute("Cleavage");

                    }
                });
                JButton economicValueButton = new JButton("Economic Value");
                economicValueButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        SuperTrumpGame.currentGame.currentAttribute = "Economic Value";
                        finalGame1.getAttribute("Economic Value");

                    }
                });
                JButton crustalAbundanceButton = new JButton("Crustal Abundance");
                crustalAbundanceButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ca) {
                        SuperTrumpGame.currentGame.currentAttribute = "Crustal Abundance";
                        finalGame1.getAttribute("Crustal Abundance");

                    }
                });


                trumpPanel.setBackground(customColor);
                trumpPanel.add(hardnessButton);
                trumpPanel.add(specificGravityButton);
                trumpPanel.add(cleavageButton);
                trumpPanel.add(economicValueButton);
                trumpPanel.add(crustalAbundanceButton);
                playersHand.add(trumpPanel, BorderLayout.SOUTH);


                pane.add(playersHand, BorderLayout.PAGE_END);

                pane.repaint();
                pane.revalidate();

            }

        });

        panel.add(newGame);
    }



    public static void main(String args[]) throws IOException {

        //Create and set up the window.
        JFrame frame = new JFrame("Mineral SuperTrumps!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        SetupGame(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setSize(1200, 800);
        frame.setVisible(true);
    }


}






