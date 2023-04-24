import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private boolean player1Turn = true;

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();
        if (button.getText().equals("")) {
            if (player1Turn) {
                button.setText("X");
            } else {
                button.setText("O");
            }
            player1Turn = !player1Turn;
        }

        // check for winner
        String winner = checkForWinner();
        if (winner != null) {
            JOptionPane.showMessageDialog(this, winner + " wins!");
            System.exit(0);
        }
    }

    private String checkForWinner() {
        // check rows
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().equals("") && 
                buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][1].getText().equals(buttons[i][2].getText())) {
                return buttons[i][0].getText();
            }
        }

        // check columns
        for (int j = 0; j < 3; j++) {
            if (!buttons[0][j].getText().equals("") && 
                buttons[0][j].getText().equals(buttons[1][j].getText()) &&
                buttons[1][j].getText().equals(buttons[2][j].getText())) {
                return buttons[0][j].getText();
            }
        }

        // check diagonals
        if (!buttons[0][0].getText().equals("") && 
            buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][2].getText())) {
            return buttons[0][0].getText();
        }

        if (!buttons[0][2].getText().equals("") && 
            buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][0].getText())) {
            return buttons[0][2].getText();
        }

        // check for tie
        boolean tie = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    tie = false;
                    break;
                }
            }
        }
        if (tie) {
            return "Tie game";
        }

        // no winner yet
        return null;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}

