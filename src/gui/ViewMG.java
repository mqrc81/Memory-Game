package gui;

import javax.swing.*;

import model.ModelMG;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * @author: Marc Schmidt
 * @date: 2020-04-09
 * @project: M120
 */

public class ViewMG extends JFrame {

	   private JPanel gamePanel, settingsPanel, scorePanel;

	    private final JButton[] button = new JButton[16];
	    private JButton start;
	    private JPanel buttonPanel, buttonPanel2;

	    private JPanel difficultyPanel, difficultyPanel2;
	    private JLabel difficultyLabel;
	    private ButtonGroup difficultyButtonGroup;
	    private final JRadioButton[] difficultyButton = new JRadioButton[4];

	    private JComboBox guiColorChanger;
	    private final String[] guiColor = new String[2];
	    private JPanel guiColorChangerPanel;
	    private JLabel guiColorChangerLabel;

	    private JPanel currentScorePanel, currentScorePanel2, highScorePanel, highScorePanel2;
	    private JLabel currentScoreLabel, currentScoreLabel2, highScoreLabel, highScoreLabel2;

	    private int[] arrayAuto;
	    private int[] arrayUser;

	    private Timer timer;

	    private final Color babyBlue = new Color(137, 156, 240);
	    private final Color brightRed = new Color(255, 69, 0);
	    private final Color limeGreen = new Color(50, 205, 50);
	    private final Color whiteBlue = new Color(240, 240, 255);
	    private final Color blackBlue = new Color(0, 0, 15);

	    private final ModelMG model;

	    public ViewMG(ModelMG model) {
	        super();
	        this.model = model;
	        init();
	    }

	    public void init() {
	        setTitle("Memory Game - by Marc");
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setSize(1200, 500);
	        setLocationRelativeTo(null);
	        setLayout(new GridLayout(1, 3));

	        settingsPanel = new JPanel(new BorderLayout());
	        gamePanel = new JPanel(new GridBagLayout());
	        scorePanel = new JPanel(new BorderLayout());
	        settingsPanel.setBackground(whiteBlue);
	        gamePanel.setBackground(Color.WHITE);
	        scorePanel.setBackground(whiteBlue);
	        add(settingsPanel);
	        add(gamePanel);
	        add(scorePanel);

	        //GAME PANEL (CENTER) -----------------------------------------------------------------------------------------------------

	        //GAME GRID PANEL:

	        buttonPanel = new JPanel(new GridLayout(4, 4, 4, 4));
	        for (int x = 0; x < 16; x++) {
	            button[x] = new JButton();
	            button[x].setBackground(babyBlue);
	            button[x].setEnabled(false);
	            button[x].addActionListener(new AttemptMemoryGame());
	            button[x].setActionCommand(x + "");
	            buttonPanel.add(button[x]);
	        }

	        buttonPanel2 = new JPanel();
	        buttonPanel2.setBackground(Color.WHITE);
	        buttonPanel2.setPreferredSize(new Dimension(320, 320));
	        buttonPanel.setPreferredSize(new Dimension(312, 312));
	        buttonPanel2.add(buttonPanel);
	        GridBagConstraints buttonGBC = new GridBagConstraints();
	        buttonGBC.gridy = 0;
	        buttonGBC.insets = new Insets(10, 10, 0, 10);
	        gamePanel.add(buttonPanel2, buttonGBC);

	        //START BUTTON:

	        start = new JButton("START");
	        start.setBackground(Color.ORANGE);
	        start.setPreferredSize(new Dimension(200, 40));
	        GridBagConstraints startGBC = new GridBagConstraints();
	        startGBC.gridy = 1;
	        startGBC.insets.top = 20;
	        startGBC.insets.bottom = 20;
	        gamePanel.add(start, startGBC);

	        start.addActionListener(new CreateMemoryGame());
	        timer = new Timer(750, new TimerListener());

	        //SETTINGS PANEL (LEFT) ----------------------------------------------------------------------------------------------

	        //GUI COLOR PANEL:

	        guiColor[0] = "Light";
	        guiColor[1] = "Dark";

	        guiColorChangerPanel = new JPanel(new GridBagLayout());
	        guiColorChangerPanel.setBackground(whiteBlue);
	        settingsPanel.add(guiColorChangerPanel, BorderLayout.NORTH);

	        guiColorChanger = new JComboBox(guiColor);
	        guiColorChanger.setBackground(Color.WHITE);
	        guiColorChanger.setPreferredSize(new Dimension(200, 30));
	        guiColorChanger.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        GridBagConstraints guiColorChangerGBC = new GridBagConstraints();
	        guiColorChangerGBC.gridy = 1;
	        guiColorChangerGBC.insets = new Insets(0, 20, 0, 50);
	        guiColorChangerGBC.anchor = GridBagConstraints.WEST;
	        guiColorChangerPanel.add(guiColorChanger, guiColorChangerGBC);

	        guiColorChangerLabel = new JLabel("GUI Color Mode:");
	        GridBagConstraints guiColorChangerLabelGBC = new GridBagConstraints();
	        guiColorChangerLabelGBC.gridy = 0;
	        guiColorChangerLabelGBC.insets = new Insets(30, 20, 5, 0);
	        guiColorChangerLabelGBC.anchor = GridBagConstraints.WEST;
	        guiColorChangerPanel.add(guiColorChangerLabel, guiColorChangerLabelGBC);

	        guiColorChanger.addActionListener(new ChangeColorsGUI());
	        guiColorChanger.setFocusable(false);

	        //GAME DIFFICULTY PANEL:

	        difficultyPanel2 = new JPanel(new GridBagLayout());
	        difficultyPanel2.setBackground(whiteBlue);
	        settingsPanel.add(difficultyPanel2, BorderLayout.SOUTH);

	        difficultyLabel = new JLabel("Difficulty:");
	        GridBagConstraints difficultyLabelGBC = new GridBagConstraints();
	        difficultyLabelGBC.gridy = 0;
	        difficultyLabelGBC.insets = new Insets(30, 0, 5, 50);
	        difficultyLabelGBC.anchor = GridBagConstraints.WEST;
	        difficultyPanel2.add(difficultyLabel, difficultyLabelGBC);

	        difficultyPanel = new JPanel(new GridBagLayout());
	        difficultyPanel.setBackground(Color.WHITE);
	        difficultyPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        GridBagConstraints difficultyPanelGBC = new GridBagConstraints();
	        difficultyPanelGBC.gridy = 1;
	        difficultyPanelGBC.insets = new Insets(0, 0, 75, 100);
	        difficultyPanel2.add(difficultyPanel, difficultyPanelGBC);

	        GridBagConstraints difficultyGBC = new GridBagConstraints();
	        difficultyGBC.insets = new Insets(5, 5, 5, 5);
	        difficultyGBC.anchor = GridBagConstraints.WEST;
	        difficultyButton[0] = new JRadioButton("Easy [3]");
	        difficultyButton[1] = new JRadioButton("Normal [5]");
	        difficultyButton[2] = new JRadioButton("Hard [7]");
	        difficultyButton[3] = new JRadioButton("Impossible [9]");

	        difficultyButtonGroup = new ButtonGroup();

	        for (int x = 0; x < 4; x++) {
	            difficultyButton[x].setBackground(Color.WHITE);
	            difficultyButton[x].setActionCommand(x + "");
	            difficultyGBC.gridy = x;
	            difficultyPanel.add(difficultyButton[x], difficultyGBC);
	            difficultyButtonGroup.add(difficultyButton[x]);
	            difficultyButton[x].addActionListener(new SelectDifficulty());
	        }

	        //SCORE PANEL (RIGHT) --------------------------------------------------------------------------------------------------------

	        //CURRENT SCORE:

	        currentScorePanel2 = new JPanel(new GridBagLayout());
	        currentScorePanel2.setBackground(whiteBlue);
	        scorePanel.add(currentScorePanel2, BorderLayout.NORTH);

	        currentScoreLabel2 = new JLabel("Score:        ");
	        GridBagConstraints currentScoreLabelGBC = new GridBagConstraints();
	        currentScoreLabelGBC.gridx = 0;
	        currentScoreLabelGBC.insets = new Insets(5, 5, 5, 5);
	        currentScoreLabelGBC.anchor = GridBagConstraints.WEST;
	        currentScorePanel2.add(currentScoreLabel2, currentScoreLabelGBC);

	        currentScorePanel = new JPanel(new GridBagLayout());
	        currentScorePanel.setBackground(Color.WHITE);
	        currentScorePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        GridBagConstraints currentScorePanelGBC = new GridBagConstraints();
	        currentScorePanelGBC.insets = new Insets(100, 0, 100, 100);
	        currentScorePanelGBC.anchor = GridBagConstraints.WEST;
	        currentScorePanel2.add(currentScorePanel, currentScorePanelGBC);

	        currentScoreLabel = new JLabel("        ");
	        currentScoreLabelGBC.gridx = 1;
	        currentScorePanel.add(currentScoreLabel, currentScoreLabelGBC);


	        //HIGHSCORE:

	        highScorePanel2 = new JPanel(new GridBagLayout());
	        highScorePanel2.setBackground(whiteBlue);
	        scorePanel.add(highScorePanel2, BorderLayout.SOUTH);

	        highScoreLabel2 = new JLabel("Highscore: ");
	        GridBagConstraints highScoreLabelGBC = new GridBagConstraints();
	        highScoreLabelGBC.gridx = 0;
	        highScoreLabelGBC.insets = new Insets(5, 5, 5, 5);
	        highScoreLabelGBC.anchor = GridBagConstraints.WEST;
	        highScorePanel2.add(highScoreLabel2, highScoreLabelGBC);

	        highScorePanel = new JPanel(new GridBagLayout());
	        highScorePanel.setBackground(Color.WHITE);
	        highScorePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        GridBagConstraints highScorePanelGBC = new GridBagConstraints();
	        highScorePanelGBC.insets = new Insets(100, 0, 100, 100);
	        highScorePanelGBC.anchor = GridBagConstraints.WEST;
	        highScorePanel2.add(highScorePanel, highScorePanelGBC);

	        highScoreLabel = new JLabel("        ");
	        highScoreLabelGBC.gridx = 1;
	        highScorePanel.add(highScoreLabel, highScoreLabelGBC);

	        pack();
	        setVisible(true);
	    }

	    public class CreateMemoryGame implements ActionListener {
	        @Override
	        public void actionPerformed(ActionEvent ae) {
	            if (model.getDifficulty() != 0) {
	                model.setCounter(0);
	                for (JRadioButton rb: difficultyButton) {
	                    rb.setEnabled(false);
	                }
	                for (JButton b: button) {
	                    b.setBackground(babyBlue);
	                    b.setEnabled(false);
	                }
	                start.setEnabled(false);
	                arrayAuto = new int[model.getDifficulty()];
	                arrayAuto[0] = (int)(Math.random() * 16);
	                for (int x = 1; x < model.getDifficulty(); x++) {
	                    arrayAuto[x] = (int)(Math.random() * 16);
	                    while (arrayAuto[x] == arrayAuto[x - 1]) {
	                        arrayAuto[x] = (int)(Math.random() * 16);
	                    }
	                }
	                arrayUser = Arrays.copyOf(arrayAuto, arrayAuto.length);
	                button[arrayAuto[0]].setBackground(limeGreen);
	                timer.start();
	            } else {
	                Object[] options = {"OK"};
	                JOptionPane.showOptionDialog(null, "Please select a Difficulty.", "ERROR", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE,
	                        null,
	                        options,
	                        options[0]);
	            }
	        }
	    }

	    public class TimerListener implements ActionListener {
	        @Override
	        public void actionPerformed(ActionEvent ae) {
	            button[arrayAuto[0]].setBackground(babyBlue);
	            arrayAuto = Arrays.copyOfRange(arrayAuto, 1, arrayAuto.length);
	            if (arrayAuto.length == 0) {
	                timer.stop();
	                for (JButton b: button) {
	                    b.setEnabled(true);
	                }
	            } else {
	                button[arrayAuto[0]].setBackground(limeGreen);
	            }
	        }
	    }

	    public class AttemptMemoryGame implements ActionListener {
	        @Override
	        public void actionPerformed(ActionEvent ae) {
	            if (Integer.parseInt(ae.getActionCommand()) == arrayUser[model.getCounter()]) {
	                if (model.getCounter() != 0) {
	                    button[arrayUser[model.getCounter() - 1]].setBackground(babyBlue);
	                }
	                button[arrayUser[model.getCounter()]].setBackground(limeGreen);
	                model.setCounter(model.getCounter() + 1);
	                model.setCurrentScore(model.getCurrentScore() + model.getDifficulty());
	                currentScoreLabel.setText("  " + model.getCurrentScore() + "  ");
	            } else {
	                if (model.getCurrentScore() > model.getHighScore()) {
	                    model.setHighScore(model.getCurrentScore());
	                    highScoreLabel.setText("  " + model.getHighScore() + "  ");
	                }
	                model.setCurrentScore(0);
	                currentScoreLabel.setText("        ");
	                for (int x = 0; x < model.getDifficulty(); x++) {
	                    button[arrayUser[x]].setBackground(brightRed);
	                }
	                start.setEnabled(true);
	                for (JRadioButton rb: difficultyButton) {
	                    rb.setEnabled(true);
	                }
	                for (JButton b: button) {
	                    b.setEnabled(false);
	                }
	            }
	            if (model.getCounter() == arrayUser.length) {
	                start.setEnabled(true);
	                for (int x = 0; x < model.getCounter() - 1; x++) {
	                    button[arrayUser[x]].setBackground(limeGreen);
	                }
	                for (JButton b: button) {
	                    b.setEnabled(false);
	                }
	                for (JRadioButton rb: difficultyButton) {
	                    rb.setEnabled(true);
	                }
	            }
	        }
	    }

	    public class SelectDifficulty implements ActionListener {
	        @Override
	        public void actionPerformed(ActionEvent ae) {
	            if (ae.getActionCommand().equals(0 + "")) {
	                model.setDifficulty(3);
	            } else if (ae.getActionCommand().equals(2 + "")) {
	                model.setDifficulty(7);
	            } else if (ae.getActionCommand().equals(3 + "")) {
	                model.setDifficulty(9);
	            } else {
	                model.setDifficulty(5);
	            }
	        }
	    }

	    public class ChangeColorsGUI implements ActionListener {

	        @Override
	        public void actionPerformed(ActionEvent ae) {
	            if (guiColorChanger.getSelectedItem() == guiColor[0]) {
	                guiColorChanger.setBackground(Color.WHITE);
	                settingsPanel.setBackground(whiteBlue);
	                gamePanel.setBackground(Color.WHITE);
	                scorePanel.setBackground(whiteBlue);
	                buttonPanel.setBackground(Color.WHITE);
	                guiColorChangerPanel.setBackground(whiteBlue);
	                buttonPanel2.setBackground(Color.WHITE);
	                guiColorChanger.setForeground(Color.BLACK);
	                guiColorChangerLabel.setForeground(Color.BLACK);
	                difficultyPanel.setBackground(Color.WHITE);
	                for (JRadioButton rb: difficultyButton) {
	                    rb.setBackground(Color.WHITE);
	                    rb.setForeground(Color.BLACK);
	                }
	                difficultyLabel.setBackground(whiteBlue);
	                difficultyLabel.setForeground(Color.BLACK);
	                difficultyPanel2.setBackground(whiteBlue);
	                guiColorChanger.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	                difficultyPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	                scorePanel.setBackground(whiteBlue);
	                currentScoreLabel.setBackground(Color.WHITE);
	                currentScoreLabel.setForeground(Color.BLACK);
	                currentScoreLabel2.setForeground(Color.BLACK);
	                currentScorePanel.setBackground(whiteBlue);
	                currentScorePanel2.setBackground(whiteBlue);
	                currentScorePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	                highScoreLabel.setBackground(Color.WHITE);
	                highScoreLabel.setForeground(Color.BLACK);
	                highScoreLabel2.setForeground(Color.BLACK);
	                highScorePanel.setBackground(whiteBlue);
	                highScorePanel2.setBackground(whiteBlue);
	                highScorePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	            } else {
	                guiColorChanger.setBackground(Color.BLACK);
	                settingsPanel.setBackground(blackBlue);
	                gamePanel.setBackground(Color.BLACK);
	                scorePanel.setBackground(blackBlue);
	                buttonPanel.setBackground(Color.BLACK);
	                guiColorChangerPanel.setBackground(blackBlue);
	                buttonPanel2.setBackground(Color.BLACK);
	                guiColorChanger.setForeground(Color.WHITE);
	                guiColorChangerLabel.setForeground(Color.WHITE);
	                difficultyPanel.setBackground(Color.BLACK);
	                for (JRadioButton rb: difficultyButton) {
	                    rb.setBackground(Color.BLACK);
	                    rb.setForeground(Color.WHITE);
	                }
	                difficultyLabel.setBackground(blackBlue);
	                difficultyLabel.setForeground(Color.WHITE);
	                difficultyPanel2.setBackground(blackBlue);
	                guiColorChanger.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	                difficultyPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	                scorePanel.setBackground(blackBlue);
	                currentScoreLabel.setBackground(Color.BLACK);
	                currentScoreLabel.setForeground(Color.WHITE);
	                currentScoreLabel2.setForeground(Color.WHITE);
	                currentScorePanel.setBackground(blackBlue);
	                currentScorePanel2.setBackground(blackBlue);
	                currentScorePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	                highScoreLabel.setBackground(Color.BLACK);
	                highScoreLabel.setForeground(Color.WHITE);
	                highScoreLabel2.setForeground(Color.WHITE);
	                highScorePanel.setBackground(blackBlue);
	                highScorePanel2.setBackground(blackBlue);
	                highScorePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	            }
	        }
	    }
	}