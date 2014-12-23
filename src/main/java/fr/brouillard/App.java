package fr.brouillard;

/*
 * #%L
 * igniter
 * %%
 * Copyright (C) 2013 - 2014 Adam Bien
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import com.airhacks.afterburner.injection.Injector;

import fr.brouillard.solar.SolarView;

/**
 *
 * @author adam-bien.com
 */
public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // Let's change Afterburner Injector with a CDI aware injector
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();

        Injector.setInstanceSupplier(new Injector.InstanceProvider() {
            @Override
            public boolean isInjectionAware() {
                // CDI realizes injections
                return true;
            }

            @Override
            public boolean isScopeAware() {
                // CDI knows about scopes
                return true;
            }

			@Override
			public Object instantiate(Class<?> c) {
                return container.instance().select(c).get();
			}
        });        
                
        
        SolarView appView = new SolarView();
        Scene scene = new Scene(appView.getView());
        stage.setTitle("CDI with Afterburner");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        Injector.forgetAll();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
