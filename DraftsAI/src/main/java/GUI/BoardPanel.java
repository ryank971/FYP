/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import MainFolder.Board;
import Checkers.Checkers;
import static GUI.GameFrame.AvailableMoves;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Ryan Kelly
 */
public class BoardPanel extends JPanel {

    String pieceIconPath = "Resources/";
    public static int rows = 8;
    public static int columns = 8;
    public static Color col1 = Color.BLACK;
    public static Color col2 = Color.WHITE;
    static Checkers checkers = new Checkers();

    public BoardPanel(Board Board) {
        setBounds(20, 60, 800, 800);
        setLayout(new GridLayout(rows, columns));
        Color temp;
        for (int i = 0; i < rows; i++) {
            if (i % 2 == 0) {
                temp = col1;
            } else {
                temp = col2;
            }
            outerloop:
            for (int j = 0; j < columns; j++) {

                if (Board.GetBoard()[i][j] == 1) {
                    TilePanel tile = new TilePanel(i, j);
                    tile.setIcon(new ImageIcon(loadImage("black" + ".png")));
                    tile.setOpaque(true);
                    tile.setBackground(temp);
                    add(tile);

                } else if (Board.GetBoard()[i][j] == 3) {
                    TilePanel tile = new TilePanel(i, j);
                    tile.setIcon(new ImageIcon(loadImage("blackKing" + ".png")));
                    tile.setOpaque(true);
                    tile.setBackground(temp);
                    add(tile);
                } else if (Board.GetBoard()[i][j] == 2) {
                    TilePanel tile = new TilePanel(i, j);
                    tile.setIcon(new ImageIcon(loadImage("red" + ".png")));
                    tile.setOpaque(true);
                    tile.setBackground(temp);
                    add(tile);
                } else if (Board.GetBoard()[i][j] == 4) {
                    TilePanel tile = new TilePanel(i, j);
                    tile.setIcon(new ImageIcon(loadImage("redKing" + ".png")));
                    tile.setOpaque(true);
                    tile.setBackground(temp);
                    add(tile);
                } else {

                    if (!AvailableMoves.isEmpty()) {
                        boolean isAvailableMove = false;
                        for (int l = 0; l < AvailableMoves.size(); l++) {
                            String[] tempM = AvailableMoves.get(l).split("-");
                            String[] CurrentS = tempM[1].split(",");
                            int size = CurrentS.length;
                            int[] Current = new int[size];
                            for (int a = 0; a < size; a++) {
                                Current[a] = Integer.parseInt(CurrentS[a]);
                            }

                            if (i == Current[0] && j == Current[1]) {
                                isAvailableMove = true;

                            }
                        }
                        if (isAvailableMove == true) {
                            TilePanel tile = new TilePanel(i, j);
                            tile.setOpaque(true);
                            tile.setBackground(Color.GREEN);
                            add(tile);
                        } else {
                            TilePanel tile = new TilePanel(i, j);
                            tile.setOpaque(true);
                            tile.setBackground(temp);
                            add(tile);
                        }

                    } else {
                        TilePanel tile = new TilePanel(i, j);
                        tile.setOpaque(true);
                        tile.setBackground(temp);
                        add(tile);
                    }

                }

                if (temp == col1) {
                    temp = col2;
                } else {
                    temp = col1;
                }

            }
        }
    }

    public void updateBoardPanel(Board Board, String move) {
        AvailableMoves = AvailableMoves = checkers.validMovesFromPiece(Board, move);
        removeAll();
        setBounds(20, 60, 800, 800);
        setLayout(new GridLayout(rows, columns));
        Color temp;
        for (int i = 0; i < rows; i++) {
            if (i % 2 == 0) {
                temp = col1;
            } else {
                temp = col2;
            }
            outerloop:
            for (int j = 0; j < columns; j++) {

                 if (Board.GetBoard()[i][j] == 1) {
                    TilePanel tile = new TilePanel(i, j);
                    tile.setIcon(new ImageIcon(loadImage("black" + ".png")));
                    tile.setOpaque(true);
                    tile.setBackground(temp);
                    add(tile);

                } else if (Board.GetBoard()[i][j] == 3) {
                    TilePanel tile = new TilePanel(i, j);
                    tile.setIcon(new ImageIcon(loadImage("blackKing" + ".png")));
                    tile.setOpaque(true);
                    tile.setBackground(temp);
                    add(tile);
                } else if (Board.GetBoard()[i][j] == 2) {
                    TilePanel tile = new TilePanel(i, j);
                    tile.setIcon(new ImageIcon(loadImage("red" + ".png")));
                    tile.setOpaque(true);
                    tile.setBackground(temp);
                    add(tile);
                } else if (Board.GetBoard()[i][j] == 4) {
                    TilePanel tile = new TilePanel(i, j);
                    tile.setIcon(new ImageIcon(loadImage("redKing" + ".png")));
                    tile.setOpaque(true);
                    tile.setBackground(temp);
                    add(tile);
                } else {

                    if (!AvailableMoves.isEmpty()) {
                        boolean isAvailableMove = false;
                        for (int l = 0; l < AvailableMoves.size(); l++) {
                            String[] tempM = AvailableMoves.get(l).split("-");
                            String[] CurrentS = tempM[1].split(",");
                            int size = CurrentS.length;
                            int[] Current = new int[size];
                            for (int a = 0; a < size; a++) {
                                Current[a] = Integer.parseInt(CurrentS[a]);
                            }

                            if (i == Current[0] && j == Current[1]) {
                                isAvailableMove = true;

                            }
                        }
                        if (isAvailableMove == true) {
                            TilePanel tile = new TilePanel(i, j);
                            tile.setOpaque(true);
                            tile.setBackground(Color.GREEN);
                            add(tile);
                        } else {
                            TilePanel tile = new TilePanel(i, j);
                            tile.setOpaque(true);
                            tile.setBackground(temp);
                            add(tile);
                        }

                    } else {
                        TilePanel tile = new TilePanel(i, j);
                        tile.setOpaque(true);
                        tile.setBackground(temp);
                        add(tile);
                    }

                }

                if (temp == col1) {
                    temp = col2;
                } else {
                    temp = col1;
                }

            }
        }
    }

    public void updateBoardPanel(Board Board) {
        removeAll();
        setBounds(20, 60, 800, 800);
        setLayout(new GridLayout(rows, columns));
        Color temp;
        for (int i = 0; i < rows; i++) {
            if (i % 2 == 0) {
                temp = col1;
            } else {
                temp = col2;
            }
            outerloop:
            for (int j = 0; j < columns; j++) {

                if (Board.GetBoard()[i][j] == 1) {
                    TilePanel tile = new TilePanel(i, j);
                    tile.setIcon(new ImageIcon(loadImage("black" + ".png")));
                    tile.setOpaque(true);
                    tile.setBackground(temp);
                    add(tile);

                } else if (Board.GetBoard()[i][j] == 3) {
                    TilePanel tile = new TilePanel(i, j);
                    tile.setIcon(new ImageIcon(loadImage("blackKing" + ".png")));
                    tile.setOpaque(true);
                    tile.setBackground(temp);
                    add(tile);
                } else if (Board.GetBoard()[i][j] == 2) {
                    TilePanel tile = new TilePanel(i, j);
                    tile.setIcon(new ImageIcon(loadImage("red" + ".png")));
                    tile.setOpaque(true);
                    tile.setBackground(temp);
                    add(tile);
                } else if (Board.GetBoard()[i][j] == 4) {
                    TilePanel tile = new TilePanel(i, j);
                    tile.setIcon(new ImageIcon(loadImage("redKing" + ".png")));
                    tile.setOpaque(true);
                    tile.setBackground(temp);
                    add(tile);
                } else {

                    if (!AvailableMoves.isEmpty()) {
                        boolean isAvailableMove = false;
                        for (int l = 0; l < AvailableMoves.size(); l++) {

                            int[] Current = new int[1];
                            if (Menu.PlayCheckers == true) {
                                String[] tempM = AvailableMoves.get(l).split("-");
                                String[] CurrentS = tempM[1].split(",");
                                int size = CurrentS.length;
                                Current = new int[size];
                                for (int a = 0; a < size; a++) {
                                    Current[a] = Integer.parseInt(CurrentS[a]);
                                }

                            } else if (Menu.PlayReversi == true) {
                                Current = new int[2];
                                Current[0] = Integer.parseInt(AvailableMoves.get(l).split(",")[0]);
                                Current[1] = Integer.parseInt(AvailableMoves.get(l).split(",")[1]);
                            }

                            if (i == Current[0] && j == Current[1]) {
                                isAvailableMove = true;

                            }

                        }
                        if (isAvailableMove == true) {
                            TilePanel tile = new TilePanel(i, j);
                            tile.setOpaque(true);
                            tile.setBackground(Color.GREEN);
                            add(tile);
                        } else {
                            TilePanel tile = new TilePanel(i, j);
                            tile.setOpaque(true);
                            tile.setBackground(temp);
                            add(tile);
                        }

                    } else {
                        TilePanel tile = new TilePanel(i, j);
                        tile.setOpaque(true);
                        tile.setBackground(temp);
                        add(tile);
                    }

                }

                if (temp == col1) {
                    temp = col2;
                } else {
                    temp = col1;
                }

            }
        }
    }

    public BufferedImage loadImage(String imageName) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(imageName));
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

}
