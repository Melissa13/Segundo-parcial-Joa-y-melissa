package main;

import Database.*;
import Clases.*;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;
//import static spark.Spark.staticFiles;

public class main {

    public static void main(String[] args) throws SQLException {

        //Iniciando el servicio
        BootStrapService.getInstancia().init();

        //pruebas
        //Insertando administrador por defecto
        User insertar = new User();
        insertar.setUsername("Admin");
        insertar.setNombre("Carla");
        insertar.setPassword("123");
        insertar.setAdministrador(true);
        UserServices.getInstancia().crear(insertar);

        User a=null;
        a=UserServices.getInstancia().find("Admin");
        if(a!=null){
            System.out.println("Username "+a.getUsername()+" Contraseña"+a.getPassword());
        }
        else {System.out.println("no esta");}

        manejadorFremarker();
    }

    public static void manejadorFremarker()throws SQLException {

        staticFileLocation("/public");

        Configuration configuration= new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(
                main.class,"/templates/");
        FreeMarkerEngine motor= new FreeMarkerEngine(configuration);

        //urls
        get("/", (request, response) -> {

            Map<String, Object> mapa = new HashMap<>();
            return new ModelAndView(mapa, "login.ftl");
        }, motor);

        get("/inicio", (request, response) -> {

            Map<String, Object> mapa = new HashMap<>();
            return new ModelAndView(mapa, "base.ftl");
        }, motor);

        get("/prueba", (request, response) -> {

            Map<String, Object> mapa = new HashMap<>();
            return new ModelAndView(mapa, "ayuda.ftl");
        }, motor);

        get("/prueba2", (request, response) -> {

            Map<String, Object> mapa = new HashMap<>();
            return new ModelAndView(mapa, "baseperfil.ftl");
        }, motor);



    }

}

