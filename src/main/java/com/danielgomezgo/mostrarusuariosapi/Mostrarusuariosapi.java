package com.danielgomezgo.mostrarusuariosapi;

import static java.awt.EventQueue.invokeLater;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Mostrarusuariosapi {

    public static void main(String[] args) {
        
        try {
            for (UIManager.LookAndFeelInfo info: UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TblUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        Create and display the form
        invokeLater(() -> {
            new TblUsuario().setVisible(true);
        });
    }
}
