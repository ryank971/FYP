/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import MainFolder.Board;
import MainFolder.Agent;
import MainFolder.AgentFactory;
import MainFolder.Game;
import MainFolder.GameFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.text.*;

/**
 *
 * @author Ryan Kelly
 */
public class GameFrame extends javax.swing.JFrame implements WindowListener {

    static Boolean popUp = true;
    static final long timerAgent = 500L;
    static long timestart = -1L;
    public static int rows = 8;
    public static int columns = 8;
    static Board Board = new Board();
    static List<String> AvailableMoves = new ArrayList();
    static GameFactory gameFactory = new GameFactory();
    static Game checkers = gameFactory.getGame("Checkers");
    static Game reversi = gameFactory.getGame("Reversi");
    static Game connect4 = gameFactory.getGame("Connect4");
    static AgentFactory agentFactory = new AgentFactory();
    static Agent connect4Random = agentFactory.getAgent("Connect4Random");
    static Agent connect4Minimax = agentFactory.getAgent("Connect4Minimax");
    static Agent connect4AlphaBeta = agentFactory.getAgent("Connect4AlphaBeta");
    static Agent checkersRandom = agentFactory.getAgent("CheckersRandom");
    static Agent checkersMinimax = agentFactory.getAgent("CheckersMinimax");
    static Agent checkersAlphaBeta = agentFactory.getAgent("CheckersAlphaBeta");
    static Agent reversiRandom = agentFactory.getAgent("ReversiRandom");
    static Agent reversiMinimax = agentFactory.getAgent("ReversiMinimax");
    static Agent reversiAlphaBeta = agentFactory.getAgent("ReversiAlphaBeta");
    static javax.swing.Timer ReversiTimer;
    static javax.swing.Timer ConnectFourTimer;
    static javax.swing.Timer CheckerTimer;
    static JTextPane logMoves = new JTextPane();
    static BoardPanel boardPanel = new BoardPanel(Board);
    static GameFrame c;
    static Menu menu;
    static String PlayerOneAlgorithm;
    static int PlayerOneDepth;
    static String PlayerTwoAlgorithm;
    static int PlayerTwoDepth;
    static String PlayerOne;
    static String PlayerTwo;
    static List<Integer> TurnRecord = new ArrayList<Integer>();
    static List<int[][]> BoardRecord = new ArrayList<int[][]>();

    public GameFrame() {
        initComponents();
        addWindowListener(this);
        setLayout(null);
        setVisible(true);
        addMenuBar();
        addLogMoves();

    }

    public void addLogMoves() {

        logMoves.setBounds(850, 60, 300, 800);
        JScrollPane sp = new JScrollPane(logMoves);
        getContentPane().add(sp);
        this.add(logMoves);
        logMoves.setVisible(true);
    }

    public static void addMovetoLogMoves(String move) {
        if (Board.turn == 1) {
            if (menu.PlayCheckers) {
                move = "\n" + "Red Moves piece " + move.split("-")[0] + " too " + move.split("-")[1];
            } else {
                move = "\n" + "Red Moves Piece too " + move;
            }
            StyleContext sc = StyleContext.getDefaultStyleContext();
            AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.red);
            aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
            aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
            int len = logMoves.getDocument().getLength();
            logMoves.setCaretPosition(len);
            logMoves.setCharacterAttributes(aset, false);
            logMoves.replaceSelection(move);
        } else {
            if (menu.PlayCheckers) {
                move = "\n" + "Black Moves piece " + move.split("-")[0] + " too " + move.split("-")[1];
            } else {
                move = "\n" + "Black Moves Piece too " + move;
            }
            StyleContext sc = StyleContext.getDefaultStyleContext();
            AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.gray);
            aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
            aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
            int len = logMoves.getDocument().getLength();
            logMoves.setCaretPosition(len);
            logMoves.setCharacterAttributes(aset, false);
            logMoves.replaceSelection(move);
        }
    }

    public void addMenuBar() {
        final JMenuBar boardMenu = new JMenuBar();
        final JMenu optionseMenu = new JMenu("Options");
        final JMenu fileMenu = new JMenu("File");

        final JMenuItem switchSides = new JMenuItem("Switch Sides");
        switchSides.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String tempAlgorithm;
                int tempDepth;
                if (PlayerOne.equals("AI")) {
                    if (PlayerTwo.equals("Human")) {
                        PlayerTwo = "AI";
                        PlayerTwoAlgorithm = PlayerOneAlgorithm;
                        PlayerTwoDepth = PlayerOneDepth;
                        PlayerOne = "Human";
                    } else if (PlayerTwo.equals("AI")) {
                        tempAlgorithm = PlayerTwoAlgorithm;
                        tempDepth = PlayerTwoDepth;
                        PlayerTwoAlgorithm = PlayerOneAlgorithm;
                        PlayerTwoDepth = PlayerOneDepth;
                        PlayerOneAlgorithm = tempAlgorithm;
                        PlayerOneDepth = tempDepth;
                    }
                } else if (PlayerOne.equals("Human")) {
                    if (PlayerTwo.equals("AI")) {
                        PlayerOne = "AI";
                        PlayerOneAlgorithm = PlayerTwoAlgorithm;
                        PlayerOneDepth = PlayerTwoDepth;
                        PlayerTwo = "Human";
                    }
                }
                JOptionPane.showMessageDialog(c, "Sides Changed!");
            }

        });
        optionseMenu.add(switchSides);

        final JMenuItem Undo = new JMenuItem("Undo Move");
        Undo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < BoardRecord.size(); i++) {
                    Board.PrintGame();
                    System.out.println("Current Board ^^");
                    for (int[] x : BoardRecord.get(i)) {
                        for (int y : x) {
                            System.out.print(y + " ");
                        }
                        System.out.println();
                    }
                    System.out.println("Board Compared ^^");
                    System.out.println("");
                    System.out.println("here");
//                    if (BoardRecord.get(i).equals(Board.board)) {
                    if (Arrays.deepEquals(BoardRecord.get(i), Board.board)) {
                        System.out.println("Board Found at " + i);
                        int PreviousMoveIndex = 0;
                        for (int l = i; l >= 0; l--) {
                            if(TurnRecord.get(i).equals(Board.turn))
                            PreviousMoveIndex = l;
                        }
//                        Board.PrintGame();
//                        System.out.println("");
                        Board.setBoard(BoardRecord.get(PreviousMoveIndex));
                        Board.turn = TurnRecord.get(PreviousMoveIndex);
                        break;
                    }

                }
                boardPanel.updateBoardPanel(Board);
                boardPanel.revalidate();
            }

        });
        optionseMenu.add(Undo);

        final JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (menu.PlayCheckers == true) {
                    CheckerTimer.stop();
                } else if (menu.PlayReversi == true) {
                    ReversiTimer.stop();
                } else if (menu.PlayConnect4 == true) {
                    ConnectFourTimer.stop();
                }

                popUp = true;
                showPopUp();

                while (popUp
                        == true) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                logMoves.setText("");
                if (menu.PlayCheckers == true) {
                    Board.setCheckerBoard();
                    CheckerTimer.start();

                } else if (menu.PlayReversi == true) {
                    Board.setReversiBoard();
                    ReversiTimer.start();
                } else {
                    Board.setConnect4Board();
                    ConnectFourTimer.start();
                }
                boardPanel.updateBoardPanel(Board);
                boardPanel.revalidate();
            }
        });

        final JMenu changeGame = new JMenu("Change Game");
        JMenuItem selectCheckers = new JMenuItem("Play Checkers");
        JMenuItem selectReversi = new JMenuItem("Play Reversi");
        JMenuItem selectConnectFour = new JMenuItem("Play Connect4");

        selectCheckers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (menu.PlayCheckers == true) {
                    CheckerTimer.stop();
                } else if (menu.PlayReversi == true) {
                    ReversiTimer.stop();
                } else if (menu.PlayConnect4 == true) {
                    ConnectFourTimer.stop();
                }

                menu.PlayCheckers = true;
                menu.PlayReversi = false;
                menu.PlayConnect4 = false;
                Board.setCheckerBoard();
                popUp = true;
                showPopUp();
                while (popUp
                        == true) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                AvailableMoves = new ArrayList();
                boardPanel.updateBoardPanel(Board);
                boardPanel.revalidate();
                startCheckers();

                selectCheckers.setEnabled(false);
                selectReversi.setEnabled(true);
                selectConnectFour.setEnabled(true);
                c.repaint();
            }

        }
        );

        selectReversi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menu.PlayCheckers == true) {
                    CheckerTimer.stop();
                } else if (menu.PlayReversi == true) {
                    ReversiTimer.stop();
                } else if (menu.PlayConnect4 == true) {
                    ConnectFourTimer.stop();
                }
                menu.PlayCheckers = false;
                menu.PlayReversi = true;
                menu.PlayConnect4 = false;
                Board.setReversiBoard();
                popUp = true;
                showPopUp();

                while (popUp
                        == true) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                AvailableMoves = new ArrayList();
                boardPanel.updateBoardPanel(Board);
                boardPanel.revalidate();
                startReversi();
                selectCheckers.setEnabled(true);
                selectReversi.setEnabled(false);
                selectConnectFour.setEnabled(true);
                c.repaint();
            }

        }
        );

        selectConnectFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menu.PlayCheckers == true) {
                    CheckerTimer.stop();
                } else if (menu.PlayReversi == true) {
                    ReversiTimer.stop();
                } else if (menu.PlayConnect4 == true) {
                    ConnectFourTimer.stop();
                }
                menu.PlayCheckers = false;
                menu.PlayReversi = false;
                menu.PlayConnect4 = true;
                Board.setConnect4Board();
                popUp = true;

                showPopUp();

                while (popUp
                        == true) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                AvailableMoves = new ArrayList();
                boardPanel.updateBoardPanel(Board);
                boardPanel.revalidate();
                startConnect4();
                selectCheckers.setEnabled(true);
                selectReversi.setEnabled(true);
                selectConnectFour.setEnabled(false);
                c.repaint();
            }

        }
        );
        changeGame.add(selectCheckers);
        changeGame.add(selectReversi);
        changeGame.add(selectConnectFour);

        if (menu.PlayCheckers == true) {
            selectCheckers.setEnabled(false);
            selectReversi.setEnabled(true);
            selectConnectFour.setEnabled(true);
        } else if (menu.PlayReversi == true) {
            selectCheckers.setEnabled(true);
            selectReversi.setEnabled(false);
            selectConnectFour.setEnabled(true);
        } else {
            selectCheckers.setEnabled(true);
            selectReversi.setEnabled(true);
            selectConnectFour.setEnabled(false);
        }

        final JMenuItem onOffMoveLog = new JMenuItem();

        if (logMoves.isVisible()) {
            onOffMoveLog.setText("Hide Move Log");
        } else {
            onOffMoveLog.setText("Show Move Log");
        }

        onOffMoveLog.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (logMoves.isVisible()) {
                    logMoves.setVisible(false);
                    onOffMoveLog.setText("Show Move Log");
                } else {
                    logMoves.setVisible(true);
                    onOffMoveLog.setText("Hide Move Log");
                }
                boardPanel.updateBoardPanel(Board);
                boardPanel.revalidate();
            }

        }
        );

        fileMenu.add(newGame);

        fileMenu.add(changeGame);

        optionseMenu.add(switchSides);

        optionseMenu.add(onOffMoveLog);
        boardMenu.add(fileMenu);
        boardMenu.add(optionseMenu);

        this.setJMenuBar(boardMenu);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 1000));
        setPreferredSize(new java.awt.Dimension(690, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws InterruptedException, IOException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        menu = new Menu();
        menu.myComponents();
        menu.setVisible(true);
        while (menu.PlayCheckers == false && menu.PlayReversi == false && menu.PlayConnect4 == false) {
            Thread.sleep(200);
        }
        mainMenu();

    }

    public static void mainMenu() throws InterruptedException, IOException {

        c = new GameFrame();
        boardPanel.setVisible(true);
        c.add(boardPanel);
        while (popUp == true) {
            Thread.sleep(200);
        }

        PlayerTurnAlert(c);

        if (menu.PlayCheckers == true) {
            startCheckers();
        } else if (menu.PlayReversi == true) {
            startReversi();
        } else if (menu.PlayConnect4 == true) {
            startConnect4();
        }

    }

    public static void startCheckers() {
        Board.setCheckerBoard();
        boardPanel.updateBoardPanel(Board);
        boardPanel.revalidate();

        BoardRecord.add(cloneBoard(Board.board));
        TurnRecord.add(Board.turn);

        CheckerTimer = new javax.swing.Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CheckWin();
                if (Board.turn == 1 && PlayerOne.equals("AI")) {

                    switch (PlayerOneAlgorithm) {
                        case "Minimax":
                            addMovetoLogMoves(checkersMinimax.makeMove(Board, PlayerOneDepth, true));
                            BoardRecord.add(cloneBoard(Board.board));
                            TurnRecord.add(Board.turn);
                            break;
                        case "Alpha Beta":
                            addMovetoLogMoves(checkersAlphaBeta.makeMove(Board, PlayerOneDepth, true));
                            BoardRecord.add(cloneBoard(Board.board));
                            TurnRecord.add(Board.turn);
                            break;
                        default:
                            addMovetoLogMoves(checkersRandom.makeMove(Board, PlayerOneDepth, true));
                            BoardRecord.add(cloneBoard(Board.board));
                            TurnRecord.add(Board.turn);
                            break;
                    }

                    boardPanel.updateBoardPanel(Board);
                    boardPanel.revalidate();
                }
                if (PlayerOne.equals("AI") && PlayerTwo.equals("AI")) {
                    if (timestart < 0) {
                        timestart = System.currentTimeMillis();
                    }
                }
                CheckWin();

                if (Board.turn == 2 && PlayerTwo.equals("AI")) {
                    long time = System.currentTimeMillis() - timestart;
                    if (time > timerAgent) {
                        if (null == PlayerTwoAlgorithm) {
                            addMovetoLogMoves(checkersRandom.makeMove(Board, PlayerTwoDepth, false));
                        } else {
                            switch (PlayerTwoAlgorithm) {
                                case "Minimax":
                                    addMovetoLogMoves(checkersMinimax.makeMove(Board, PlayerTwoDepth, false));
                                    BoardRecord.add(cloneBoard(Board.board));
                                    TurnRecord.add(Board.turn);
                                    break;
                                case "Alpha Beta":
                                    addMovetoLogMoves(checkersAlphaBeta.makeMove(Board, PlayerTwoDepth, false));
                                    BoardRecord.add(cloneBoard(Board.board));
                                    TurnRecord.add(Board.turn);
                                    break;
                                default:
                                    addMovetoLogMoves(checkersRandom.makeMove(Board, PlayerTwoDepth, false));
                                    BoardRecord.add(cloneBoard(Board.board));
                                    TurnRecord.add(Board.turn);
                                    break;
                            }
                        }
                        boardPanel.updateBoardPanel(Board);
                        boardPanel.revalidate();
                        timestart = -1L;
                    }
                }

            }
        });

        CheckerTimer.start();
    }

    public static void startReversi() {
        Board.setReversiBoard();
        boardPanel.updateBoardPanel(Board);
        boardPanel.revalidate();

        ReversiTimer = new javax.swing.Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CheckWin();
                if (Board.turn == 1 && PlayerOne.equals("AI")) {
                    AvailableMoves = new ArrayList();
                    if (null == PlayerOneAlgorithm) {
                        addMovetoLogMoves(reversiRandom.makeMove(Board, PlayerOneDepth, true));
                    } else {
                        switch (PlayerOneAlgorithm) {
                            case "Minimax":
                                addMovetoLogMoves(reversiMinimax.makeMove(Board, PlayerOneDepth, true));
                                break;
                            case "Alpha Beta":
                                addMovetoLogMoves(reversiAlphaBeta.makeMove(Board, PlayerOneDepth, true));
                                break;
                            default:
                                addMovetoLogMoves(reversiRandom.makeMove(Board, PlayerOneDepth, true));
                                break;
                        }
                    }
                    boardPanel.updateBoardPanel(Board);
                    boardPanel.revalidate();
                }
                if (PlayerOne.equals("AI") && PlayerTwo.equals("AI")) {
                    AvailableMoves = new ArrayList();
                    if (timestart < 0) {
                        timestart = System.currentTimeMillis();
                    }
                }
                CheckWin();

                if (Board.turn == 2 && PlayerTwo.equals("AI")) {
                    AvailableMoves = new ArrayList();
                    long time = System.currentTimeMillis() - timestart;
                    if (time > timerAgent) {
                        if (null == PlayerTwoAlgorithm) {
                            addMovetoLogMoves(reversiRandom.makeMove(Board, PlayerTwoDepth, false));
                        } else {
                            switch (PlayerTwoAlgorithm) {
                                case "Minimax":
                                    addMovetoLogMoves(reversiMinimax.makeMove(Board, PlayerTwoDepth, false));
                                    break;
                                case "Alpha Beta":
                                    addMovetoLogMoves(reversiAlphaBeta.makeMove(Board, PlayerTwoDepth, false));
                                    break;
                                default:
                                    addMovetoLogMoves(reversiRandom.makeMove(Board, PlayerTwoDepth, false));
                                    break;
                            }
                        }
                        boardPanel.updateBoardPanel(Board);
                        boardPanel.revalidate();
                        timestart = -1L;
                    }
                }
                if (Board.turn == 1 && PlayerOne.equals("Human")) {

                    AvailableMoves = reversi.validMoves(Board);
                    boardPanel.updateBoardPanel(Board);
                    boardPanel.revalidate();
                }

                if (Board.turn == 2 && PlayerTwo.equals("Human")) {
                    AvailableMoves = reversi.validMoves(Board);
                    boardPanel.updateBoardPanel(Board);
                    boardPanel.revalidate();
                }
            }
        });

        ReversiTimer.start();

    }

    public static void startConnect4() {
        Board.setConnect4Board();
        boardPanel.updateBoardPanel(Board);
        boardPanel.revalidate();

        ConnectFourTimer = new javax.swing.Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CheckWin();
                if (Board.turn == 1 && PlayerOne.equals("AI")) {

                    if (null == PlayerOneAlgorithm) {
                        addMovetoLogMoves(connect4Random.makeMove(Board, PlayerOneDepth, true));
                    } else {
                        switch (PlayerOneAlgorithm) {
                            case "Minimax":
                                addMovetoLogMoves(connect4Minimax.makeMove(Board, PlayerOneDepth, true));
                                break;
                            case "Alpha Beta":
                                addMovetoLogMoves(connect4AlphaBeta.makeMove(Board, PlayerOneDepth, true));
                                break;
                            default:
                                addMovetoLogMoves(connect4Random.makeMove(Board, PlayerOneDepth, true));
                                break;
                        }
                    }
                    boardPanel.updateBoardPanel(Board);
                    boardPanel.revalidate();
                }
                if (PlayerOne.equals("AI") && PlayerTwo.equals("AI")) {
                    if (timestart < 0) {
                        timestart = System.currentTimeMillis();
                    }
                }
                CheckWin();

                if (Board.turn == 2 && PlayerTwo.equals("AI")) {
                    long time = System.currentTimeMillis() - timestart;
                    if (time > timerAgent) {
                        if (null == PlayerTwoAlgorithm) {
                            addMovetoLogMoves(connect4Random.makeMove(Board, PlayerTwoDepth, false));
                        } else {
                            switch (PlayerTwoAlgorithm) {
                                case "Minimax":
                                    addMovetoLogMoves(connect4Minimax.makeMove(Board, PlayerTwoDepth, false));
                                    break;
                                case "Alpha Beta":
                                    addMovetoLogMoves(connect4AlphaBeta.makeMove(Board, PlayerTwoDepth, false));
                                    break;
                                default:
                                    addMovetoLogMoves(connect4Random.makeMove(Board, PlayerTwoDepth, false));
                                    break;
                            }
                        }
                        boardPanel.updateBoardPanel(Board);
                        boardPanel.revalidate();
                        timestart = -1L;
                    }
                }

            }
        });

        ConnectFourTimer.start();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void windowOpened(WindowEvent arg0) {
        showPopUp();
    }

    @Override
    public void windowClosing(WindowEvent arg0) {

    }

    @Override
    public void windowClosed(WindowEvent arg0) {

    }

    @Override
    public void windowIconified(WindowEvent arg0) {

    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {

    }

    @Override
    public void windowActivated(WindowEvent arg0) {

    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
    }

    public static void CheckWin() {
        String winner;
        if ((menu.PlayCheckers == true && checkers.isOver(Board))) {
            int player = checkers.CheckWin(Board);
            if (player == 1) {
                winner = "black";
            } else if (player == 2) {
                winner = "red";
            } else {
                winner = "draw";
            }
            ResultGame(winner);

        } else if (menu.PlayReversi == true && reversi.isOver(Board)) {
            int player = reversi.CheckWin(Board);
            if (player == 1) {
                winner = "black";
            } else if (player == 2) {
                winner = "red";
            } else {
                winner = "draw";
            }
            ResultGame(winner);
        } else if (menu.PlayConnect4 == true) {
            if (connect4.isOver(Board)) {
                int player = connect4.CheckWin(Board);
                if (player == 1) {
                    winner = "black";
                } else if (player == 2) {
                    winner = "red";
                } else {
                    winner = "draw";
                }
                ResultGame(winner);
            }
        }

    }

    public static void resetGame() {
        if (menu.PlayCheckers == true) {
            Board.setCheckerBoard();

        } else if (menu.PlayReversi == true) {
            Board.setReversiBoard();

        } else {
            Board.setConnect4Board();

        }
        boardPanel.updateBoardPanel(Board);
        boardPanel.revalidate();

    }

    public static void ResultGame(String winner) {

        int response = JOptionPane.showConfirmDialog(boardPanel,
                winner + " won the game! Restart Game?", "Restart", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            resetGame();
            logMoves.setText("");
            boardPanel.updateBoardPanel(Board);
            boardPanel.revalidate();
        }
        if (response == JOptionPane.NO_OPTION) {
            System.exit(0);
        } else if (response == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }
    }

    public void showPopUp() {
        setup setup = new setup();
        JOptionPane pane = new JOptionPane();
        pane.showMessageDialog(null, setup, "Information", JOptionPane.INFORMATION_MESSAGE);

        Object selected = setup.Player1.getSelectedItem();
        if (selected.toString().equals("AI")) {
            PlayerOne = "AI";
            for (Enumeration<AbstractButton> buttons = setup.buttonGroup.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    if (button.getText() == "Minimax") {
                        PlayerOneAlgorithm = button.getText();
                        PlayerOneDepth = setup.sliderMinimaxPlayer1.getValue();
                    } else if (button.getText() == "Alpha Beta") {
                        PlayerOneAlgorithm = button.getText();
                        PlayerOneDepth = setup.sliderAlphaBetaPlayer1.getValue();
                    } else {
                        PlayerOneAlgorithm = button.getText();
                    }
                }
            }
        } else {
            PlayerOne = "Human";
        }

        selected = setup.Player2.getSelectedItem();
        if (selected.toString().equals("AI")) {
            PlayerTwo = "AI";
            for (Enumeration<AbstractButton> buttons = setup.buttonGroup2.getElements(); buttons.hasMoreElements();) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    if (button.getText() == "Minimax") {
                        PlayerTwoAlgorithm = button.getText();
                        PlayerTwoDepth = setup.sliderMinimax1Player2.getValue();
                    } else if (button.getText() == "Alpha Beta") {
                        PlayerTwoAlgorithm = button.getText();
                        PlayerTwoDepth = setup.sliderAlphaBeta1Player2.getValue();
                    } else {
                        PlayerTwoAlgorithm = button.getText();
                    }
                }
            }
        } else {
            PlayerTwo = "Human";
        }

        popUp = false;

        System.out.println("PLAYER ONE HUMAN/AI? " + PlayerOne);
        if (PlayerOne.equals("AI")) {
            System.out.println("PLAYER ONE ALGORITHM? " + PlayerOneAlgorithm);
            System.out.println("PLAYER ONE DEPTH TO SEARCH? " + PlayerOneDepth);
        }

        System.out.println("PLAYER TWO HUMAN/AI? " + PlayerTwo);
        if (PlayerTwo.equals("AI")) {
            System.out.println("PLAYER TWO ALGORITHM? " + PlayerTwoAlgorithm);
            System.out.println("PLAYER TWO DEPTH TO SEARCH? " + PlayerTwoDepth);
        }
    }

    public static void PlayerTurnAlert(GameFrame c) {
        JLabel blackAlert = new JLabel();
        blackAlert.setFont(new Font("Serif", Font.BOLD, 20));
        JLabel redAlert = new JLabel();
        redAlert.setFont(new Font("Serif", Font.BOLD, 20));
        javax.swing.Timer t = new javax.swing.Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (Board.turn == 1) {
                    redAlert.setVisible(false);
                    blackAlert.setBounds(390, 0, 200, 100);
                    blackAlert.setText("BLACKS TURN");
                    blackAlert.setVisible(true);
                    c.add(blackAlert);
                    c.repaint();

                }

                if (Board.turn == 2) {
                    blackAlert.setVisible(false);
                    redAlert.setBounds(390, 820, 200, 100);
                    redAlert.setText("REDS TURN");
                    redAlert.setVisible(true);
                    c.add(redAlert);
                    c.repaint();

                }

            }
        });
        t.start();
    }

    public static int[][] cloneBoard(int[][] board) {

        final int[][] result = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            result[i] = Arrays.copyOf(board[i], board[i].length);
        }
        return result;
    }

}
