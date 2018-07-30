package main;

import Database.*;
import Clases.*;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;
import spark.*;
import static spark.Spark.*;
import spark.Session;
import org.jasypt.util.text.BasicTextEncryptor;

import java.nio.file.*;

import javax.jws.soap.SOAPBinding;
//import static spark.Spark.staticFiles;

public class main {

    public static void main(String[] args) throws SQLException {

        //Iniciando el servicio
        BootStrapService.getInstancia().init();
        //pruebas
        //User s=UserServices.getInstancia().find("Admin");
        //s.setAdministrador(true);
        //UserServices.getInstancia().editar(s);
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

        //prueba post
        User m=UserServices.getInstancia().find("Meli");
        Set<User> esto=new HashSet<>();
        esto.add(m);
        Date today = Calendar.getInstance().getTime();
        /*Post p1=new Post();
        p1.setAuthorp(a);
        p1.setBody("cuerpo del post");
        p1.setDateTime(today);
        p1.setTitle("post 1");
        //p1.setUserTags(esto);
        PostServices.getInstancia().crear(p1);*/


        System.out.println("cantidad de post");
        List<Post> p2=PostServices.getInstancia().findAll();
        for (Post p: p2){
            System.out.println("ID:"+p.getId()+" Title:"+p.getTitle()+" Body:"+p.getBody()+" fecha:"+p.getDateTime()+" Autor:"+p.getAuthorp().getUsername()+" Tags:"+p.getUserTags().size()+" Comentarios:"+p.getComments().size());
        }


        //fecha prueba
        //Date today = Calendar.getInstance().getTime();


        manejadorFremarker();
    }

    public static void manejadorFremarker()throws SQLException {

        staticFileLocation("/public");
        staticFiles.externalLocation("upload");

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
            if(nacimiento != null && !nacimiento.isEmpty()) {
                try {
                    DateFormat formatter;
                    formatter = new SimpleDateFormat("dd/MM/yyyy");
                    date = formatter.parse(nacimiento);
                    System.out.println(date);
                } catch (java.text.ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            User insertar = new User();
            insertar.setUsername(username);
            if(nombre != null && !nombre.isEmpty()) {
                insertar.setNombre(nombre);
            }
            insertar.setPassword(pass);
            insertar.setAdministrador(false);
            if(nacimiento != null && !nacimiento.isEmpty()) {
                insertar.setDate_birth(date);
            }
            if(lugaractual != null && !lugaractual.isEmpty()) {
                insertar.setActual_place(lugaractual);
            }
            if(descripcion != null && !descripcion.isEmpty()) {
                insertar.setDescripcion(descripcion);
            }
            if(lugarnacimiento != null && !lugarnacimiento.isEmpty()) {
                insertar.setPlace_birth(lugarnacimiento);
            }
            if(trabajo!= null && !trabajo.isEmpty()) {
                insertar.setJob(trabajo);
            }
            if(estudios != null && !estudios.isEmpty()) {
                insertar.setStudies(estudios);
            }
            if(lugartrabajo != null && !lugartrabajo.isEmpty()) {
                insertar.setWorkplace(lugartrabajo);
            }
            UserServices.getInstancia().crear(insertar);

            response.redirect("/loginsucess");
            return "";
        });

        get("/loginsucess", (request, response) -> {

            Map<String, Object> mapa = new HashMap<>();
            return new ModelAndView(mapa, "loginsucess.ftl");
        }, motor);

        get("/perfil/editar", (request, response) -> {

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

            return new ModelAndView(mapa, "editarperfil.ftl");
        }, motor);

        post("/perfil/editar", (request, response) -> {
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
            if(nacimiento != null && !nacimiento.isEmpty()) {
                try {
                    DateFormat formatter;
                    formatter = new SimpleDateFormat("dd/MM/yyyy");
                    date = formatter.parse(nacimiento);
                    System.out.println(date);
                } catch (java.text.ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if(nombre != null && !nombre.isEmpty()) {
                user.setNombre(nombre);
            }
            else {user.setNombre(null);}

            user.setPassword(pass);
            if(nacimiento != null && !nacimiento.isEmpty()) {
                user.setDate_birth(date);
            }
            else {user.setDate_birth(null);}

            if(lugaractual != null && !lugaractual.isEmpty()) {
                user.setActual_place(lugaractual);
            }
            else {user.setActual_place(null);}

            if(descripcion != null && !descripcion.isEmpty()) {
                user.setDescripcion(descripcion);
            }
            else {user.setDescripcion(null);}

            if(lugarnacimiento != null && !lugarnacimiento.isEmpty()) {
                user.setPlace_birth(lugarnacimiento);
            }
            else {user.setPlace_birth(null);}

            if(trabajo!= null && !trabajo.isEmpty()) {
                user.setJob(trabajo);
            }
            else {user.setJob(null);}

            if(estudios != null && !estudios.isEmpty()) {
                user.setStudies(estudios);
            }
            else {user.setStudies(null);}

            if(lugartrabajo != null && !lugartrabajo.isEmpty()) {
                user.setWorkplace(lugartrabajo);
            }
            else {user.setWorkplace(null);}

            UserServices.getInstancia().editar(user);

            response.redirect("/perfil");
            return "";
        });

        get("/gestion", (request, response) -> {

            System.out.println("entra a gestion");
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

            List<User> lista= UserServices.getInstancia().findAll();

            Map<String, Object> mapa = new HashMap<>();
            mapa.put("userl",user);
            mapa.put("lista", lista);
            return new ModelAndView(mapa, "gestion_users.ftl");
        }, motor);

        get("/gestion/promove/:user",(request, response) -> {
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

            System.out.println("aqui no deberia entrar");
            String username = request.params("user");
            User usuario=UserServices.getInstancia().find(username);
            if(usuario.isAdministrador()){
                usuario.setAdministrador(false);

            }else {
                usuario.setAdministrador(true);
            }
            UserServices.getInstancia().editar(usuario);

            Map<String,Object> mapa = new HashMap<>();
            mapa.put("userl",user);
            mapa.put("eluser",usuario);
            return new ModelAndView(mapa,"gestion_promote.ftl");
        },motor);

        get("/gestion/delete/:user",(request, response) -> {
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
            String username = request.params("user");
            User usuario=UserServices.getInstancia().find(username);
            UserServices.getInstancia().eliminar(username);

            Map<String,Object> mapa = new HashMap<>();
            mapa.put("userl",user);
            return new ModelAndView(mapa,"ayuda.ftl");
        },motor);

        get("/gestion/invalid", (request, response) -> {
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

            Map<String,Object> mapa = new HashMap<>();
            mapa.put("userl",user);
            return new ModelAndView(mapa,"gestion_error.ftl");
        },motor);

        get("/inicio/add", (request, response) -> {

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

            return new ModelAndView(mapa, "post_crear.ftl");
        }, motor);

        post("/inicio/add", (request, response) -> {
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


            String nombre = getFileName(request.raw().getPart("filecover"));//request.queryParams("filecover");
            System.out.println("archivo devuelto: "+nombre);

            //long id=1;
            //Post p1=PostServices.getInstancia().find(id);
            //p1.setUserTags(esto);
            //PostServices.getInstancia().editar(p1);

            response.redirect("/inicio");
            return "";
        });

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
                halt(404,"Ya esta logueado");
            }
            else{
                user= request.session(true).attribute("user");
                if(user!= null){
                    response.redirect("/inicio");
                    halt(404,"Ya esta logueado");
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
                    halt(404,"No tiene permiso");
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
                    halt(404,"No tiene permiso");
                }

            }

        });

        before("/perfil/*",(request, response) -> {
            User user =null;
            String cook=decrypt(request.cookie("test"));
            System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
            }
            else{
                user= request.session(true).attribute("user");
                if(user == null){
                    response.redirect("/");
                    halt(404,"No tiene permiso");
                }

            }

        });

        before("/gestion",(request, response) -> {
            User user =null;
            String cook=decrypt(request.cookie("test"));
            System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                user=UserServices.getInstancia().find(cook);
                if(!user.isAdministrador()){
                    response.redirect("/inicio");
                    halt(404,"vete");
                }
            }
            else{
                user= request.session(true).attribute("user");
                if(user == null){
                    response.redirect("/");
                    halt(404,"vete");
                }
                else if(!user.isAdministrador()){
                    response.redirect("/inicio");
                    halt(404,"vete");
                }
            }

            System.out.println("verigicar gestion");
        });

        before("/gestion/*",(request, response) -> {
            User user =null;
            String cook=decrypt(request.cookie("test"));
            System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                user=UserServices.getInstancia().find(cook);
                if(!user.isAdministrador()){
                    response.redirect("/inicio");
                    halt(404,"No tiene permiso");
                }
            }
            else{
                user= request.session(true).attribute("user");
                if(user == null){
                    response.redirect("/");
                    halt(404,"No tiene permiso");
                }
                else if(!user.isAdministrador()){
                    response.redirect("/inicio");
                    halt(404,"No tiene permiso");
                }
            }
        });

        before("/gestion/delete/:user",(request, response) -> {
            User user =null;
            String cook=decrypt(request.cookie("test"));
            System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
            }
            else{
                user= request.session(true).attribute("user");
                if(user == null){
                    response.redirect("/");
                    halt(404,"No tiene permiso");
                }

            }

            String username = request.params("user");
            if(username.equals("Admin")){
                response.redirect("/gestion/invalid");
                halt(404,"No tiene permiso");
            }
        });

        before("/gestion/promove/:user",(request, response) -> {
            User user =null;
            String cook=decrypt(request.cookie("test"));
            System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
            }
            else{
                user= request.session(true).attribute("user");
                if(user == null){
                    response.redirect("/");
                    halt(404,"No tiene permiso");
                }

            }

            System.out.println("entra aqui");

            String username = request.params("user");
            if(username.equals("Admin")){
                response.redirect("/gestion/invalid");
                halt(404,"No tiene permiso");
            }
        });



        get("/testImage",(request, response) ->
            "<form method='post' enctype='multipart/form-data'>" // note the enctype
                    + "    <input type='file' name='uploaded_file' accept='.png'>" // make sure to call getPart using the same "name" in the post
                    + "    <button>Upload picture</button>"
                    + "</form>"
        );

        post("/testImage", (req, res) -> {

            File uploadDir = new File("upload");
            uploadDir.mkdir();


            Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");

            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            try (InputStream input = req.raw().getPart("uploaded_file").getInputStream()) { // getPart needs to use same "name" as input field in form
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }

            logInfo(req, tempFile);
            return "<h1>You uploaded this image:<h1><img src='" + tempFile.getFileName() + "'>";

        });


        get("/prueba", (request, response) -> {

            Map<String, Object> mapa = new HashMap<>();
            return new ModelAndView(mapa, "ayuda.ftl");
        }, motor);

    }

    private static void logInfo(Request req, Path tempFile) throws IOException, ServletException {
        System.out.println("Uploaded file '" + getFileName(req.raw().getPart("uploaded_file")) + "' saved as '" + tempFile.toAbsolutePath() + "'");
    }

    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
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

