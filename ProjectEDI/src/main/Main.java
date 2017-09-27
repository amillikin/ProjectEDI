package main;

import java.awt.EventQueue;
import java.io.File;
import java.nio.file.Files;

import gui.MainWindow;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Before execution: need to delete settings file from execution directory if exists
					Files.deleteIfExists(new File(System.getProperty("user.dir").replace("\\", "/") + "/settings.xml").toPath());
					MainWindow window = new MainWindow();
					window.mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
