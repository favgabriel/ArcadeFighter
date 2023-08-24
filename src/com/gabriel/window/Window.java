package com.gabriel.window;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class Window extends Application {
    public Window(int w,int h, String t,Game g){
         g.setPreferredSize(new Dimension(w,h));
         g.setMaximumSize(new Dimension(w,h));
         g.setMinimumSize(new Dimension(w,h));
         JPanel panel = new JPanel();
         Container container =new Container();
         //Label label = new Label("Tooms Ranger");
         //panel.add(label);
         panel.add(g);
         JFrame frame = new JFrame(t);
         frame.add(panel);
         frame.pack();
         frame.setLocationRelativeTo(null);
         //frame.setResizable(false);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);

         g.start();
    }

     @Override
     public void start(Stage primaryStage) throws Exception {

     }
}
