/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.rfc.tarearfc;

import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import static spark.Spark.get;
import static spark.Spark.post;
import spark.template.freemarker.FreeMarkerEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author rfc
 */
public class Login {
    
        private static final Logger LOGGER = LogManager.getLogger("Login");
        
        public static void main(String[] args) {
   
        /*inicio app */    
        get("/", (req, res) -> {
            Map<String, Object> attributes = new HashMap<>();
            return new ModelAndView(attributes, "registrar.ftl");
            }, new FreeMarkerEngine());

        /*Registro*/
        post("/registrar", (req, res) -> {
            Map<String, Object> attributes = new HashMap<>();

                     
            //TODO Guardar en Base de datos.
            return new ModelAndView(attributes, "registrar.ftl");
        }, new FreeMarkerEngine());
        
        /*verifica inicio de sesión*/
        post("/login", (req, res) -> {
            Map<String, Object> attributes = new HashMap<>();
            String email = req.queryParams("email");
            String password = req.queryParams("password");
            
            String validador = "";
            String nombre = "";
            String mensaje = "";
            String usuario = "n/a";
            attributes.put("validador", validador);
            attributes.put("nombre", nombre);
            
            if(email.equals("rfc@mail.com") && password.equals("rfc123")){
                LOGGER.trace(String.format("El usuario %s ha iniciado sesión.", email));
                usuario = email;
                attributes.put("mensaje", mensaje);
                attributes.put("usuario", usuario);
                return new ModelAndView(attributes, "home.ftl");
            }else{
                mensaje = "Usuario y/o Password son incorrectos.";
            }
            
            attributes.put("mensaje", mensaje);
            
            return new ModelAndView(attributes, "home.ftl");
        }, new FreeMarkerEngine());
    
        }  
}
