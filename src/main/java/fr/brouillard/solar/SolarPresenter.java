package fr.brouillard.solar;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;

public class SolarPresenter implements Initializable {
	@Inject Sun theSun;
	@Inject Instance<Star> starFactory;
	@FXML ListView<String> list;
	
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        list.getItems().add("Building solar system");
        list.getItems().add("adding: " + theSun);
        for (int i = 0; i < 10; i++) {
            list.getItems().add("adding: " + starFactory.get());
        }
    }
}
