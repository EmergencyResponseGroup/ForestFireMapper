    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewcontrollers;

import ist311.forestfiremapper.Location;
import ist311.forestfiremapper.LocationList;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * FXML Controller class
 *
 * @author dging
 */
public class NavigationController extends ViewController{
    
    @FXML
    Button editUsersBtn, logoutBtn;
    
    private CloseableHttpClient httpClient = HttpClients.createDefault();
    
    public void initialize() {
        
        editUsersBtn.setOnAction((ActionEvent e) -> {
            try {
                setFXMLView("/fxml/EditUsersView.fxml");
                
            } catch (IOException ex) {
                Logger.getLogger(NavigationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        logoutBtn.setOnAction((ActionEvent e) -> {
            try {
                setFXMLView("/fxml/LoginView.fxml");
                sessionInfo.setCurrentUser(null);
            } catch (IOException ex) {
                Logger.getLogger(NavigationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        
        
    }    

    @Override
    public void finishInitialization() {
        HttpGet request = new HttpGet("http://34.67.165.237:3000/results");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            String result = EntityUtils.toString(entity);
            Jsonb jsonb = JsonbBuilder.create();
            LocationList locationList = jsonb.fromJson(result, LocationList.class);
            Location[] locations = locationList.locations;
            for (int i = 0; i < locations.length; i++) {
                if (sessionInfo.getCurrentUser().getLocation().distanceTo(locations[i].latitude, locations[i].longitude) <= 10.0) {
                    createErrorPopup("Warning! You are within 10 miles of a fire location retrieved from our web service.");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(NavigationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
