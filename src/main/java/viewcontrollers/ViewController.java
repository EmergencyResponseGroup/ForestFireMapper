/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewcontrollers;

import ist311.forestfiremapper.SessionInfo;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author dging
 */
public abstract class ViewController {

    public SessionInfo sessionInfo;

    public void setFXMLView(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();

        ViewController viewController = loader.getController();
        viewController.setSessionInfo(sessionInfo);
        viewController.finishInitialization();

        sessionInfo.getStage().setScene(new Scene(root));
        sessionInfo.getStage().show();
    }

    //Used to show error popup
    public void createErrorPopup(String errorMessage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/errorPopupView.fxml"));
            Parent root = loader.load();
            ErrorPopupViewController errorPopupCntrl = loader.getController();
            errorPopupCntrl.setErrorMessage(errorMessage);
            Stage popupStage = new Stage();
            popupStage.setScene(new Scene(root));
            popupStage.show();
            popupStage.requestFocus();
            popupStage.toFront();
        } catch (IOException ex) {
            Logger.getLogger(EditUsersViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Cannot call methods that use non-FXML variables in Initialize method, as
    // non-FXML variables are not instantiated in Initialize. This method will be run
    // automatically, if implemented, by View Controller classes
    public abstract void finishInitialization();

    public void setSessionInfo(SessionInfo sessionInfo) {
        this.sessionInfo = sessionInfo;
    }

}
