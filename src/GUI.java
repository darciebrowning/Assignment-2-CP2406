import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GUI {
    public static SuperTrumpGame game;
    public int numberOfPlayers;
    public Game stgame;
    public Player player;
    static Player[] players;
    static JLabel PlayedDeck;


    public static void SetupGame(final Container pane) throws IOException {

        final Color customColor = new Color(52, 86, 55);
        pane.setBackground(customColor);
        pane.setLayout(new BorderLayout());

        final JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(customColor);
        pane.add(panel);
        JLabel numberPlayers = new JLabel("How many players would you like to play with?");
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
                int firstPlayer = 2;

                pane.remove(panel);

                //Get human player and pass to playerview to view hand.
                Player humanPlayer = game.players[0];

                PlayerView view = new PlayerView(humanPlayer);
                view.setBackground(customColor);


                //Button to click for user to pickup a card
                BufferedImage buttonIcon = null;
                try {
                    buttonIcon = ImageIO.read(new File("CardBack.jpg"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                //Shows previously played card.
                ImageIcon playedIcon = new ImageIcon("CardBack.jpg");
                Image image = playedIcon.getImage();
                Image playedImage = image.getScaledInstance(200, 250, Image.SCALE_SMOOTH);
                ImageIcon played = new ImageIcon(playedImage);
                JLabel icon = new JLabel(played);

                //Font for labels.
                Font font = new Font("Verdana", Font.PLAIN, 12);

                //create a container in the center of the GUI for card decks.
                JPanel deckPanel = new JPanel();
                deckPanel.setBackground(customColor);
                deckPanel.setLayout(new GridBagLayout());
                deckPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                pane.add(deckPanel);

                //Adds players cards to the screen.
                JPanel playersHand = new JPanel(new BorderLayout());
                playersHand.setBackground(Color.black);
                playersHand.add(view, BorderLayout.NORTH);


                JPanel trumpPanel = new JPanel();
                trumpPanel.setLayout(new GridBagLayout());
                trumpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));


                JButton hardnessButton = new JButton("Hardness");
                JButton specificGravityButton = new JButton("Specific Gravity");
                JButton cleavageButton = new JButton("Cleavage");
                JButton economicValueButton = new JButton("Economic Value");
                JButton crustalAbundanceButton = new JButton("Crustal Abundance");


                trumpPanel.setBackground(customColor);
                trumpPanel.add(hardnessButton);
                trumpPanel.add(specificGravityButton);
                trumpPanel.add(cleavageButton);
                trumpPanel.add(economicValueButton);
                trumpPanel.add(crustalAbundanceButton);
                playersHand.add(trumpPanel, BorderLayout.SOUTH);

                pane.add(deckPanel, BorderLayout.CENTER);
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
        frame.setSize(800, 800);
        frame.setVisible(true);
    }
}






