package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TicTacToePanel extends JPanel {
    boolean player1;
    int winner = -1;

    int[][] board = new int[3][3];
    int x;
    int y;

    int p1Scores;
    int p2Scores;

    JLabel turn = new JLabel("Player 1's Turn");
    JButton restartButton = new JButton("Play Again");

    Color background = new Color(201, 255, 228);

    public TicTacToePanel() {
        setBackground(background);

        player1 = true;
        p1Scores = 0;
        p2Scores = 0;

        JPanel restartPanel = new JPanel();
        restartPanel.setBackground(background);
        restartPanel.add(restartButton, BorderLayout.CENTER);
        this.add(restartPanel);

        JPanel turnPanel = new JPanel();
        turnPanel.setBackground(background);
        turnPanel.add(turn, BorderLayout.CENTER);
        this.add(turnPanel);

        turn.setFont(new Font("Courier New", Font.BOLD, 20));
        addMouseListener(new GameListener());
        restartButton.setFont(new Font("Courier New", Font.PLAIN, 20));


        restartButton.addActionListener(e -> {
            reset();
            repaint();
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image gametitle = Toolkit.getDefaultToolkit().getImage("C:\\Users\\kikki\\Downloads\\Image.png");
        g.drawImage(gametitle, 465, 20, 350, 230, this);

        int wid = 5;
        int hei = 300;
        int xpos = 490;
        int ypos = 218;

        if (player1) {
            turn.setText("Player 1's turn");
        } else {
            turn.setText("Player 2's turn");
        }

        g.setColor(Color.WHITE);
        g.fillRoundRect(xpos + 100, 220, wid, hei, 5, 5);
        g.fillRoundRect(xpos + 200, 220, wid, hei, 5, 5);
        g.fillRoundRect(xpos, ypos + 100, hei, wid, 5, 5);
        g.fillRoundRect(xpos, ypos + 200, hei, wid, 5, 5);

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                if (board[x][y] == 1) {
                    g.setColor(new Color(127, 0, 255));
                    int[] p = {xpos + 20 + 100 * x, xpos + 30 + 100 * x, xpos + 50 + 100 * x, xpos + 70 + 100 * x, xpos +
                            80 + 100 * x, xpos + 60 + 100 * x, xpos + 80 + 100 * x, xpos + 70 + 100 * x, xpos + 50 + 100 *
                            x, xpos + 30 + 100 * x, xpos + 20 + 100 * x, xpos + 40 + 100 * x,};
                    int[] q = {245 + 100 * y, 245 + 100 * y, 265 + 100 * y, 245 + 100 * y, 245 + 100 * y, 270 + 100 * y,
                            295 + 100 * y, 295 + 100 * y, 275 + 100 * y, 295 + 100 * y, 295 + 100 * y, 270 + 100 * y};
                    g.fillPolygon(p, q, 12);
                } else if (board[x][y] == 2) {
                    g.setColor(new Color(255, 102, 102));
                    g.fillOval(xpos + 20 + 100 * x, 240 + 100 * y, 60, 60);
                    g.setColor(background);
                    g.fillOval(xpos + 25 + 100 * x, 243 + 101 * y, 50, 50);
                    g.setColor(new Color(255, 102, 102));
                    g.drawOval(xpos + 25 + 100 * x, 243 + 101 * y, 50, 50);
                }
            }
        }

        g.setFont(new Font("Courier New", Font.BOLD, 30));
        g.setColor(new Color(56, 80, 202));
        if (checkWin() == 1) {
            g.drawString("CONGRATS PLAYER 1, YOU WON!", 410, 600);
        } else if (checkWin() == 2) {
            g.drawString("CONGRATS PLAYER 2, YOU WON!", 410, 600);
        } else if (checkDraw()) {
            g.drawString("DRAW", 605, 600);
        }

        g.setFont(new Font("Courier New", Font.PLAIN, 35));
        g.setColor(Color.DARK_GRAY);

        g.drawString("Player 1", 273, 300);
        g.drawString(p1Scores + " wins", 290, 410);
        g.drawString("Player 2", 868, 300);
        g.drawString(p2Scores + " wins", 890, 410);

        x = 0;
        y = 0;
        xpos = 900;
        ypos = 315;
        g.setColor(new Color(255, 102, 102));
        g.fillOval(xpos + 20 + 100 * x, (ypos - 4) + 100 * y, 60, 60);
        g.setColor(background);
        g.fillOval(xpos + 25 + 100 * x, ypos + 150 * y, 50, 50);
        g.setColor(new Color(255, 102, 102));
        g.drawOval(xpos + 25 + 100 * x, ypos + 150 * y, 50, 50);

        x = 0;
        y = 0;
        xpos = 300;
        ypos = 320;
        int[] p = {xpos + 20 + 100 * x, xpos + 30 + 100 * x, xpos + 50 + 100 * x, xpos + 70 + 100 * x, xpos + 80 + 100 * x,
                xpos + 60 + 100 * x, xpos + 80 + 100 * x, xpos + 70 + 100 * x, xpos + 50 + 100 * x, xpos + 30 + 100 * x,
                xpos + 20 + 100 * x, xpos + 40 + 100 * x,};
        int[] q = {ypos + 100 * y, ypos + 100 * y, ypos + 20 + 100 * y, ypos + 100 * y, ypos + 100 * y, ypos + 25 + 100 * y,
                ypos + 50 + 100 * y, ypos + 50 + 100 * y, ypos + 30 + 100 * y, ypos + 50 + 100 * y, ypos + 50 + 100 * y,
                ypos + 25 + 100 * y};
        g.fillPolygon(p, q, 12);
    }

    public void reset() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 0;
            }
        }
        winner = -1;
        player1 = true;
    }

    public boolean checkDraw() {
        boolean completed = true;
        for (int[] ints : board) {
            for (int j = 0; j < board.length; j++) {
                if (ints[j] == 0) {
                    completed = false;
                    break;
                }
            }
        }
        return completed;
    }

    public int checkWin() {
        for (int[] ints : board) {
            int m = ints[0];
            int n = ints[1];
            int o = ints[2];
            if ((m == n) && (n == o) && (m != 0)) {
                winner = m;
            }
        }
        for (int i = 0; i < board.length; i++) {
            int m = board[0][i];
            int n = board[1][i];
            int o = board[2][i];
            if ((m == n) && (n == o) && (m != 0)) {
                winner = m;
            }
        }
        int m = board[0][0];
        int n = board[1][1];
        int o = board[2][2];
        if ((m == n) && (n == o)) {
            winner = m;
        }
        m = board[0][2];
        n = board[1][1];
        o = board[2][0];
        if ((m == n) && (n == o)) {
            winner = m;
        }
        return winner;
    }

    private class GameListener implements MouseListener {
        public void mousePressed(MouseEvent event) {}
        public void mouseReleased(MouseEvent event) {}
        public void mouseEntered(MouseEvent event) {}
        public void mouseExited(MouseEvent event) {}
        public void mouseClicked(MouseEvent event) {
            if (checkWin() == 0) {
                int a = event.getX();
                int b = event.getY();
                if ((a < 500) || (b < 200)) {
                    repaint();
                } else if ((a > 780) || (b > 510)) {
                    repaint();
                } else {
                    if (a > 500 && a < 580) {
                        x = 0;
                    } else if (a > 600 && a < 680) {
                        x = 1;
                    } else if (a > 700 && a < 780) {
                        x = 2;
                    }
                    if (b > 225 && b < 310) {
                        y = 0;
                    } else if (b > 325 && b < 410) {
                        y = 1;
                    } else if (b > 425 && b < 510) {
                        y = 2;
                    }
                    if (board[x][y] == 1 || board[x][y] == 2) {
                        repaint();
                    } else {
                        if (player1) {
                            board[x][y] = 1;
                        }
                        if (!player1) {
                            board[x][y] = 2;
                        }
                    }
                }
                if (checkWin() == 1) {
                    p1Scores++;
                } else if (checkWin() == 2) {
                    p2Scores++;
                } else if (player1) {
                    turn.setText("Player 2'S TURN");
                    player1 = !player1;
                } else {
                    turn.setText("Player 1'S TURN");
                    player1 = !player1;
                }
                repaint();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1350, 800));
        frame.getContentPane().add(new TicTacToePanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
