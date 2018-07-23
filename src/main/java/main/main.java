package main;

import Database.*;
import Clases.*;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;
import spark.*;
import static spark.Spark.*;
import spark.Session;
import org.jasypt.util.text.BasicTextEncryptor;
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

        //prueba amigos
        /*
        User use=UserServices.getInstancia().find("User1");
        User use2=UserServices.getInstancia().find("Admin");
        Set<User> esto=new HashSet<>();
        Set<User> esto2=new HashSet<>();
        esto.add(use);
        esto2.add(use2);
        use.setFriends(esto2);
        use2.setFriends(esto);
        UserServices.getInstancia().editar(use);
        UserServices.getInstancia().editar(use2);*/



        //prueba usuarios
        List<User> pu=UserServices.getInstancia().findAll();
        for (User u: pu){
            System.out.println("Username:"+u.getUsername()+" Pass:"+u.getPassword()+" name:"+u.getNombre()+" administrador:"+u.isAdministrador()+" Datebirth:"+u.getDate_birth()+" Placebirth:"+u.getPlace_birth()+" Amigos:"+u.getFriends().size());
        }
        /*String str_date = "22/07/2018";
        try {
            DateFormat formatter;
            Date date;
            formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = formatter.parse(str_date);
            System.out.println(date);
            User insertar = new User();
            insertar.setUsername("User1");
            insertar.setNombre("Shanika");
            insertar.setPassword("1234");
            insertar.setAdministrador(false);
            insertar.setDate_birth(date);
            UserServices.getInstancia().crear(insertar);
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/


        //fecha prueba
        //Date today = Calendar.getInstance().getTime();


        manejadorFremarker();
    }

    public static void manejadorFremarker()throws SQLException {

        staticFileLocation("/public");

        Configuration configuration= new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(
                main.class,"/templates/");
        FreeMarkerEngine motor= new FreeMarkerEngine(configuration);

        //urls
        //login
        get("/", (request, response) -> {

            Map<String, Object> mapa = new HashMap<>();
            return new ModelAndView(mapa, "login.ftl");
        }, motor);

        get("/loginerror", (request, response) -> {

            Map<String, Object> mapa = new HashMap<>();
            return new ModelAndView(mapa, "loginerror.ftl");
        }, motor);

        Spark.post("/login", (request, response) -> {

            String username =request.queryParams("username") != null ? request.queryParams("username") : "unknown";
            String pass =request.queryParams("pass") != null ? request.queryParams("pass") : "unknown";

            User user = UserServices.getInstancia().find(username);
            //System.out.println("usuario "+ user.getUsername());
            if(user!=null){
                if(user.getPassword().equals(pass)) {

                    request.session(true);
                    request.session().attribute("user", user);
                    response.cookie("/", "test", encrypt(user.getUsername()), 604800, false);
                    response.redirect("/inicio");

                } else{
                    response.redirect("/loginerror");
                }

            }else{
                response.redirect("/loginerror");
            }


            return "";

        });

        Spark.get("/logout", (request, response) -> {

            Session actual = request.session(true);
            actual.invalidate();
            response.removeCookie("test");
            response.redirect("/");
            return "";
        });

        get("/inicio", (request, response) -> {
            User user =null;
            String cook=decrypt(request.cookie("test"));
            System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                user=UserServices.getInstancia().find(cook);
                request.session(true);
                request.session().attribute("user", user);
            }
            else{
                user= request.session(true).attribute("user");
            }

            Map<String, Object> mapa = new HashMap<>();
            mapa.put("userl",user);

            return new ModelAndView(mapa, "inicio.ftl");
        }, motor);

        get("/perfil", (request, response) -> {

            User user =null;
            String cook=decrypt(request.cookie("test"));
            //System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                user=UserServices.getInstancia().find(cook);
                request.session(true);
                request.session().attribute("user", user);
            }
            else{
                user= request.session(true).attribute("user");
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fecha="";
            String edad="";
            if(user.getDate_birth() != null) {
                fecha = dateFormat.format(user.getDate_birth());

                Calendar cal = Calendar.getInstance();
                cal.setTime(user.getDate_birth());
                int year = cal.get(Calendar.YEAR);
                cal.setTime(Calendar.getInstance().getTime());
                int year_now = cal.get(Calendar.YEAR);
                edad = String.valueOf(year_now - year);
            }

            Map<String, Object> mapa = new HashMap<>();
            mapa.put("userl",user);
            mapa.put("fecha",fecha);
            mapa.put("edad",edad);

            return new ModelAndView(mapa, "perfil.ftl");
        }, motor);

        get("/registrar", (request, response) -> {

            Map<String, Object> mapa = new HashMap<>();
            return new ModelAndView(mapa, "registro.ftl");
        }, motor);

        post("/registrar", (request, response) -> {

            String username =request.queryParams("username") != null ? request.queryParams("username") : "unknown";
            String pass =request.queryParams("password") != null ? request.queryParams("password") : "unknown";
            String nombre =request.queryParams("name");
            String nacimiento =request.queryParams("date");
            String descripcion =request.queryParams("description");
            String lugarnacimiento =request.queryParams("place_birth");
            String lugaractual =request.queryParams("actual_place");
            String trabajo =request.queryParams("job");
            String lugartrabajo =request.queryParams("workplace");
            String estudios =request.queryParams("studies");

            Date date=null;
            try {
                DateFormat formatter;
                formatter = new SimpleDateFormat("dd/MM/yyyy");
                date = formatter.parse(nacimiento);
                System.out.println(date);
            } catch (java.text.ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            User insertar = new User();
            insertar.setUsername(username);
            insertar.setNombre(nombre);
            insertar.setPassword(pass);
            insertar.setAdministrador(false);
            insertar.setDate_birth(date);
            insertar.setActual_place(lugaractual);
            insertar.setDescripcion(descripcion);
            insertar.setPlace_birth(lugarnacimiento);
            insertar.setJob(trabajo);
            insertar.setStudies(estudios);
            insertar.setWorkplace(lugartrabajo);
            UserServices.getInstancia().crear(insertar);

            response.redirect("/loginsucess");
            return "";
        });

        get("/loginsucess", (request, response) -> {

            Map<String, Object> mapa = new HashMap<>();
            return new ModelAndView(mapa, "loginsucess.ftl");
        }, motor);

        //condiciones before
        before("/",(request, response) -> {
            User user =null;
            String cook=decrypt(request.cookie("test"));
            System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                user=UserServices.getInstancia().find(cook);
                request.session(true);
                request.session().attribute("user", user);
                response.redirect("/inicio");
            }
            else{
                user= request.session(true).attribute("user");
                if(user!= null){
                    response.redirect("/inicio");
                }

            }

        });

        before("/inicio",(request, response) -> {
            User user =null;
            String cook=decrypt(request.cookie("test"));
            System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
            }
            else{
                user= request.session(true).attribute("user");
                if(user == null){
                    response.redirect("/");
                }

            }

        });

        before("/perfil",(request, response) -> {
            User user =null;
            String cook=decrypt(request.cookie("test"));
            System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
            }
            else{
                user= request.session(true).attribute("user");
                if(user == null){
                    response.redirect("/");
                }

            }

        });



        get("/prueba", (request, response) -> {

            Map<String, Object> mapa = new HashMap<>();
            return new ModelAndView(mapa, "ayuda.ftl");
        }, motor);

        get("/prueba2", (request, response) -> {

            User user =null;
            String cook=decrypt(request.cookie("test"));
            //System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                user=UserServices.getInstancia().find(cook);
                request.session(true);
                request.session().attribute("user", user);
            }
            else{
                user= request.session(true).attribute("user");
            }

            Map<String, Object> mapa = new HashMap<>();
            mapa.put("userl",user);
            return new ModelAndView(mapa, "editarperfil.ftl");
        }, motor);


    }

    public static String encrypt(String secret){
        BasicTextEncryptor a = new BasicTextEncryptor();
        a.setPasswordCharArray("some-random-data".toCharArray());
        String topsecret = a.encrypt(secret);
        return topsecret;

    }

    public static String decrypt(String secret){
        BasicTextEncryptor a = new BasicTextEncryptor();
        a.setPasswordCharArray("some-random-data".toCharArray());
        String topsecret = a.decrypt(secret);

        return topsecret;
    }

}

