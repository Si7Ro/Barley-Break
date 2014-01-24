import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main {
    int count = 0;
    JFrame frame = new JFrame("Пятнашки Classic");
    Component[] barley = new Component[16];
    ActionListener handler = new action();
    ActionListener start = new startGameButton();
    JButton startGame = new JButton("Начать игру");
    JButton restartGame = new JButton("Рестарт");
    JButton exitGame = new JButton("Выход");
    JLabel countCheckLabel = new JLabel("Кол-во ходов");
    JLabel countLabel = new JLabel("" + count);

    public static void main(String[] args) {
	Main game = new Main();
	game.gui();
    }

    public void gui() {

	frame.setSize(407, 630);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLayout(null);
	startGame.setBounds(0, 450, 200, 50);
	frame.getContentPane().add(startGame);
	restartGame.setBounds(0, 500, 200, 50);
	restartGame.setEnabled(false);
	frame.getContentPane().add(restartGame);
	exitGame.setBounds(0, 550, 200, 50);
	frame.getContentPane().add(exitGame);
	startGame.addActionListener(start);
	restartGame.addActionListener(start);
	exitGame.addActionListener(start);
	start();
	frame.setResizable(false);
	frame.setVisible(true);

    }

    class action implements ActionListener {

	public void actionPerformed(ActionEvent e) {

	    for (int i = 1; i < 16; i++) {
		int x = barley[i].getX();
		int y = barley[i].getY();
		int copyx = x;
		int copyy = y;
		if (e.getSource() == barley[i]) {

		    for (int h = 0; h != 4; h++) {

			if (h == 0) {
			    barley[i].setBounds(x + 100, y, 100, 100);
			}
			if (h == 1) {
			    barley[i].setBounds(x - 100, y, 100, 100);
			}
			if (h == 2) {
			    barley[i].setBounds(x, y + 100, 100, 100);
			}
			if (h == 3) {
			    barley[i].setBounds(x, y - 100, 100, 100);
			}

			if (barley[i].getLocation().equals(
				barley[0].getLocation())) {

			    barley[i].setLocation(barley[0].getX(),
				    barley[0].getY());
			    barley[0].setLocation(x, y);

			    break;
			} else {
			    barley[i].setBounds(copyx, copyy, 100, 100);

			}
		    }

		}

	    }
	    checkWin();

	}
    }

    class startGameButton implements ActionListener {
	public void actionPerformed(ActionEvent event) {
	    if (event.getSource() == startGame) {
		Font font = new Font("TimesRoman", Font.BOLD, 16);
		Font font2 = new Font("TimesRoman", Font.BOLD, 32);
		random();
		countCheckLabel.setFont(font);
		countCheckLabel.setBounds(250, 400, 150, 50);
		countLabel.setFont(font2);
		countLabel.setBounds(300, 450, 100, 100);
		frame.getContentPane().add(countCheckLabel);
		frame.getContentPane().add(countLabel);
		restartGame.setEnabled(true);
		for (int i = 0; i != 16; i++) {

		    barley[i].setEnabled(true);

		}
		startGame.setEnabled(false);
	    }

	    if (event.getSource() == restartGame) {
		count = 0;
		countLabel.setText("" + count);
		random();
	    }

	    if (event.getSource() == exitGame) {
		System.exit(0);
	    }

	}

    }

    public void checkWin() {
	count++;
	countLabel.setText("" + count);
	for (int i = 0; i < 16; i++) {

	    if (barley[0].getX() == 300 & barley[0].getY() == 300
		    & barley[1].getX() == 0 & barley[1].getY() == 0
		    & barley[2].getX() == 100 & barley[2].getY() == 0
		    & barley[3].getX() == 200 & barley[3].getY() == 0
		    & barley[4].getX() == 300 & barley[4].getY() == 0
		    & barley[5].getX() == 0 & barley[5].getY() == 100
		    & barley[6].getX() == 100 & barley[6].getY() == 100
		    & barley[7].getX() == 200 & barley[7].getY() == 100
		    & barley[8].getX() == 300 & barley[8].getY() == 100
		    & barley[9].getX() == 0 & barley[9].getY() == 200
		    & barley[10].getX() == 100 & barley[10].getY() == 200
		    & barley[11].getX() == 200 & barley[11].getY() == 200
		    & barley[12].getX() == 300 & barley[12].getY() == 200
		    & barley[13].getX() == 0 & barley[13].getY() == 300
		    & barley[14].getX() == 100 & barley[14].getY() == 300
		    & barley[15].getX() == 200 & barley[15].getY() == 300) {

		JOptionPane.showMessageDialog(null,
			"Вы победили! Кол-во сделаных ходов: " + count);
		startGame.setEnabled(true);
		restartGame.setEnabled(false);
		random();
		count = 0;

	    }

	}
    }

    public void random() {
	int x, y;
	int copyx, copyy;
	int count = 0;
	while (count != 300) {
	    int r = (int) (Math.random() * 14);
	    x = barley[0].getX();
	    y = barley[0].getY();
	    copyx = barley[r].getX();
	    copyy = barley[r].getY();
	    barley[r].setBounds(x, y, 100, 100);
	    barley[0].setBounds(copyx, copyy, 100, 100);
	    count++;
	}
    }

    public void start() {
	int x = 0;
	int y = 0;
	for (int i = 0; i != 16; i++) {
	    barley[i] = new JButton(String.valueOf(i));
	    ((AbstractButton) barley[i]).addActionListener(handler);
	    barley[i].setBounds(x, y, 100, 100);
	    if (x != 300) {
		x += 100;
	    } else {
		x = 0;
		y += 100;
	    }

	    frame.getContentPane().add(barley[i]);
	    barley[i].setEnabled(false);
	}
	((AbstractButton) barley[0]).setText("");

    }

}
