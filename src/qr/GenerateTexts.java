
package qr;

import java.util.ArrayList;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

    
    public class GenerateTexts {
    
        public ArrayList<String> generate(Locale locale) {
            ArrayList<String> texts = new ArrayList<>();
            try {
                // Cargar el ResourceBundle según el locale
                ResourceBundle bundle = ResourceBundle.getBundle("resources.Texts", locale);
    
                // Añadir los textos deseados al ArrayList
                
                texts.add(bundle.getString("label.firstName"));
                texts.add(bundle.getString("label.middleName"));
                texts.add(bundle.getString("label.lastName"));
                texts.add(bundle.getString("label.organization"));
                texts.add(bundle.getString("label.title"));
                texts.add(bundle.getString("label.email"));
                texts.add(bundle.getString("label.phone"));
                texts.add(bundle.getString("label.url"));
                texts.add(bundle.getString("label.address"));
                texts.add(bundle.getString("label.city"));
                texts.add(bundle.getString("label.state"));
                texts.add(bundle.getString("label.country"));
                texts.add(bundle.getString("label.birthday"));
                texts.add(bundle.getString("label.nickname"));
                texts.add(bundle.getString("label.note"));
                
    
            } catch (Exception e) {
                e.printStackTrace(); // Imprimir detalles del error si algo falla
            }
            return texts;
        }
    }
    

