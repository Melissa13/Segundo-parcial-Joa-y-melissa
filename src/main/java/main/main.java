package main;

import Database.*;
import Clases.*;
import SOAP.SOAP_Start;
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

        port(getPuertoHeroku());



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


        //ESTO DA USO A AL SERVICIO SOAP, SOLO HAY QUE DESCOMENTARLO
        /* try {
         //   SOAP_Start.init();
        }
        catch (Exception e){
            System.out.println("Esta vaina no sirve");
            e.printStackTrace();
        }*/

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
        System.out.println("TAGS!");
        List<Tag> ss2=TagServices.getInstancia().findAll();
        for (Tag u: ss2){
            System.out.println("tag ss2: "+u.getTag()+" ID "+ u.getId());
        }

        //prueba post
        /*User m=UserServices.getInstancia().find("Meli");
        Set<User> esto=new HashSet<>();
        esto.add(m);
        Date today = Calendar.getInstance().getTime();
        Post p1=new Post();
        p1.setAuthorp(a);
        p1.setBody("cuerpo del post");
        p1.setDateTime(today);
        p1.setTitle("post 1");
        p1.setUserTags(esto);
        PostServices.getInstancia().crear(p1);*/




        //fecha prueba
        //Date today = Calendar.getInstance().getTime();


        manejadorFremarker();
    }

    public static void manejadorFremarker()throws SQLException {

        File uploadDir = new File("upload");
        uploadDir.mkdir();

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

            List<Post> publicaciones=PostServices.getInstancia().findAllById();
            //List<Post> auz=new ArrayList<Post>();
            for(Post p: publicaciones){
                if(p.getImage()!=null && !p.getImage().isEmpty()) {
                    Path prueba = Paths.get(p.getImage());
                    p.setImage(prueba.getFileName().toString());
                }

            }
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("userl",user);
            mapa.put("posts",publicaciones);
            mapa.put("amigos",UserServices.getInstancia().findAll());

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

        //posts

        post("/inicio/agregar", (request, response) -> {
            User user =null;
            String cook=decrypt(request.cookie("test"));
            //System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                //verificar si hay cookies
                user=UserServices.getInstancia().find(cook);
                request.session(true);
                request.session().attribute("user", user);
                //algo aqui
            }
            else{
                user= request.session(true).attribute("user");
            }

            Post insertar=new Post();
            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            String title =request.queryParams("title");
            String body =request.queryParams("body");
            String[] tags =request.queryParams("tag").split(",");
            Date today = Calendar.getInstance().getTime();
            String[] lista= request.queryParamsValues("amigos");

            if(!tags[0].isEmpty()){
                System.out.println("esta lleno");
            }
            else { System.out.println("esta vacio"); }

            Set<User> amigos=new HashSet<>();
            if(lista!=null) {
                for (String esto : lista) {
                    User u = UserServices.getInstancia().find(esto);
                    //System.out.println("Valor: "+esto);
                    amigos.add(u);
                }
                insertar.setUserTags(amigos);
            }


            insertar.setDateTime(today);
            insertar.setBody(body);
            if(title != null && !title.isEmpty()) {
                insertar.setTitle(title);
            }
            insertar.setAuthorp(user);

            //recibir imagen
            if(getFileName(request.raw().getPart("uploaded_file"))!=null && !getFileName(request.raw().getPart("uploaded_file")).isEmpty()) {

                Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");
                try (InputStream input = request.raw().getPart("uploaded_file").getInputStream()) { // getPart needs to use same "name" as input field in form
                    Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
                }
                String img_direccion = tempFile.toAbsolutePath().toString();
                //System.out.println("direccion: " + img_direccion);

                insertar.setImage(img_direccion);

            }

            if(!tags[0].isEmpty()) {
                Set<Tag> gt = new HashSet<>();
                gt=trabajo_tags(tags);
                insertar.setTags(gt);
            }


            PostServices.getInstancia().crear(insertar);

            //guardar archivo

            response.redirect("/inicio");
            return "";
        });

        get("/inicio/delete/:id", (request, response) -> {
            System.out.println("entra a delete post");
            User user =null;
            String cook=decrypt(request.cookie("test"));
            //System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                //verificar si hay cookies
                user=UserServices.getInstancia().find(cook);
                request.session(true);
                request.session().attribute("user", user);
                //algo aqui
            }
            else{
                user= request.session(true).attribute("user");
            }


            long postid = Long.parseLong(request.params("id"));
            List<Comment> c=CommentServices.getInstancia().findAll();
            for(Comment cc:c){
                if(cc.getPost().getId()==postid){
                    //caux.add(cc);
                    CommentServices.getInstancia().eliminar(cc.getId());
                }
            }

            PostServices.getInstancia().eliminar(postid);//ps.DeleteProduct(productid);

            //guardar archivo

            response.redirect("/inicio");
            return "";
        });

        get("/inicio/edit/:id", (request, response) -> {
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

            long postid = Long.parseLong(request.params("id"));

            Post p=PostServices.getInstancia().find(postid);//.getProduct(productid);

            if(p.getImage()!=null && !p.getImage().isEmpty()) {
                Path prueba = Paths.get(p.getImage());
                p.setImage(prueba.getFileName().toString());
            }

            List<User> auxu=UserServices.getInstancia().findAll(); //amigos
            List<User> u_selec=new ArrayList<>();
            ArrayList<User> no_select=new ArrayList<>();
            for (User u2:auxu){
                no_select.add(u2);
            }
            for (User u:p.getUserTags()){
                for (User u2:auxu){
                    if(u.getUsername().equals(u2.getUsername())){
                        u_selec.add(u);
                        no_select.remove(u2);
                    }
                }
            }


            for (User us:u_selec){
                System.out.println("Amigo seleccionado: "+us.getUsername());
            }
            for (User us:no_select){
                System.out.println("Amigo no seleccionado: "+us.getUsername());
            }


            Map<String, Object> mapa = new HashMap<>();

            if(p.getTags().size()>0){
                String tag="";
                for (Tag t:p.getTags()){
                    tag+=t.getTag()+",";
                }

                tag=tag.substring(0, Math.min(tag.length(), tag.length()-1));

                mapa.put("tag",tag);
            }
            else {mapa.put("tag",null);}


            mapa.put("userl",user);
            mapa.put("post",p);;
            mapa.put("amigos",UserServices.getInstancia().findAll());
            mapa.put("amigos_tag",u_selec);
            mapa.put("amigos_notag",no_select);
            return new ModelAndView(mapa, "post_editar.ftl");
        }, motor);

        post("/inicio/edit/:id", (request, response) -> {
            User user =null;
            String cook=decrypt(request.cookie("test"));
            //System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                //verificar si hay cookies
                user=UserServices.getInstancia().find(cook);
                request.session(true);
                request.session().attribute("user", user);
                //algo aqui
            }
            else{
                user= request.session(true).attribute("user");
            }

            long postid = Long.parseLong(request.params("id"));
            Post insertar=PostServices.getInstancia().find(postid);
            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            String title =request.queryParams("title");
            String body =request.queryParams("body");
            String[] tags =request.queryParams("tag").split(",");
            String[] lista= request.queryParamsValues("amigos");

            System.out.println("TAG AMIGOS: ");
            if(lista!=null) {
                for (String esto : lista) {
                    System.out.println("Valor: "+esto);
                }
            }


            Set<User> amigos=new HashSet<>();
            if(lista!=null) {
                for (String esto : lista) {
                    User u = UserServices.getInstancia().find(esto);
                    //System.out.println("Valor: "+esto);
                    amigos.add(u);
                }
                insertar.setUserTags(amigos);
            }
            else {insertar.setUserTags(null);}


            insertar.setBody(body);
            if(title != null && !title.isEmpty()) {
                insertar.setTitle(title);
            }
            else {insertar.setTitle(null);}

            //recibir imagen
            if(getFileName(request.raw().getPart("uploaded_file"))!=null && !getFileName(request.raw().getPart("uploaded_file")).isEmpty()) {

                Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");
                try (InputStream input = request.raw().getPart("uploaded_file").getInputStream()) { // getPart needs to use same "name" as input field in form
                    Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
                }
                String img_direccion = tempFile.toAbsolutePath().toString();
                //System.out.println("direccion: " + img_direccion);

                insertar.setImage(img_direccion);

            }

            if(!tags[0].isEmpty()) {
                Set<Tag> gt = new HashSet<>();
                gt=trabajo_tags2(tags);
                insertar.setTags(gt);
            }
            else {insertar.setTags(null);}


            PostServices.getInstancia().editar(insertar);


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

        before("/inicio/*",(request, response) -> {
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
                        + "<h1>ruta 1:<h1><img src='upload/6390579131001553725.PNG'>"
                        + "<h1>ruta 2:<h1><img src='/6390579131001553725.PNG'>"
                        + "<h1>ruta 3:<h1><img src='C:/Users/Melisa/Documents/PUCMM/ISC-415 Programacion web/segundo parcial/Segundo-parcial-Joa-y-melissa/upload/6390579131001553725.PNG'>"
        );

        post("/testImage", (req, res) -> {


            Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");

            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            try (InputStream input = req.raw().getPart("uploaded_file").getInputStream()) { // getPart needs to use same "name" as input field in form
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }

            logInfo(req, tempFile);
            System.out.println("direccion: "+tempFile.toAbsolutePath());
            return "<h1>You uploaded this image:<h1><img src='" + tempFile.getFileName() + "'>";

        });


        get("/prueba", (request, response) -> {

            Map<String, Object> mapa = new HashMap<>();
            return new ModelAndView(mapa, "ayuda.ftl");
        }, motor);

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

            long id=1;
            Post p1=PostServices.getInstancia().find(id);
            Path prueba=Paths.get(p1.getImage());
            p1.setImage(prueba.getFileName().toString());

            Map<String, Object> mapa = new HashMap<>();
            mapa.put("userl",user);
            mapa.put("imagen", prueba);
            mapa.put("img2",p1.getImage());
            mapa.put("amigos",UserServices.getInstancia().findAll());

            return new ModelAndView(mapa, "post_crear.ftl");
        }, motor);

        post("/inicio/add", (request, response) -> {
            User user =null;
            String cook=decrypt(request.cookie("test"));
            //System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                //verificar si hay cookies
                user=UserServices.getInstancia().find(cook);
                request.session(true);
                request.session().attribute("user", user);
                //algo aqui
            }
            else{
                user= request.session(true).attribute("user");
            }



            //recibir archivo
            Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");
            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            try (InputStream input = request.raw().getPart("uploaded_file").getInputStream()) { // getPart needs to use same "name" as input field in form
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }
            String img_direccion=tempFile.toAbsolutePath().toString();
            System.out.println("direccion: "+img_direccion);

            //guardar archivo
            //long id=1;
            //Post p1=PostServices.getInstancia().find(id);
            //p1.setImage(img_direccion);
            //PostServices.getInstancia().editar(p1);

            response.redirect("/inicio");
            return "";
        });

        post("/inicio/add2", (request, response) -> {
            User user =null;
            String cook=decrypt(request.cookie("test"));
            //System.out.println("El cookie: "+request.cookie("test"));
            if(cook != null && !cook.isEmpty()){
                //verificar si hay cookies
                user=UserServices.getInstancia().find(cook);
                request.session(true);
                request.session().attribute("user", user);
                //algo aqui
            }
            else{
                user= request.session(true).attribute("user");
            }

            //codigo checkbox  request.queryParams("description");
            String[] lista= request.queryParamsValues("cars");
            for (String esto: lista){
                System.out.println("Valor: "+esto);
            }


            response.redirect("/inicio");
            return "";
        });




    }

    static int getPuertoHeroku() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //En caso de no pasar la información, toma el puerto 4567
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

    public static Set<Tag> trabajo_tags(String[] tags){
        Set<Tag> gt = new HashSet<>();
        List<Tag> ss = TagServices.getInstancia().findAll();
        for (int i = 0; i < tags.length; i++) {
            boolean exist = false;
            for (Tag u : ss) {
                if (u.getTag().equals(tags[i])) {
                    exist = true;
                    List<Tag> aux2 = TagServices.getInstancia().findAllBytag(tags[i]);
                    for (Tag tt : aux2) {
                        gt.add(tt);
                    }
                }
            }
            if (!exist) {
                Tag t = new Tag();
                t.setTag(tags[i]);
                TagServices.getInstancia().crear(t);
                List<Tag> aux2 = TagServices.getInstancia().findAllBytag(tags[i]);
                for (Tag tt : aux2) {
                    gt.add(tt);
                }
            }
        }
        return gt;
    }

    public static Set<Tag> trabajo_tags2(String[] tags){
        List<Tag> ss=TagServices.getInstancia().findAll();
        Set<Tag> gt = new HashSet<>();
        for (int i=0;i<tags.length;i++){
            boolean exist=false;
            for (Tag u: ss){
                if(u.getTag().equals(tags[i])){
                    exist=true;
                    gt.add(u);
                }
            }
            if(!exist){
                Tag t=new Tag();
                t.setTag(tags[i]);
                TagServices.getInstancia().crear(t);
                List<Tag> aux2=TagServices.getInstancia().findAllBytag(tags[i]);
                for (Tag tt: aux2){
                    gt.add(tt);
                }
            }
        }
        return gt;
    }

    public static List<Post> user_post(String nombre) {
        List<Post> fulano=new ArrayList<>();
        List<Post> aux=PostServices.getInstancia().findAll();
        for (Post p:aux){
            if(p.getAuthorp().getUsername().equals(nombre)){
                fulano.add(p);
            }
        }
        return fulano;
    }

    public static ArrayList<String> Posteo(String username)
    {

        String nombre=username;
        ArrayList<String> postes = new ArrayList<String>();
        System.out.println("cantidad de post");
        //List<Post> p2=PostServices.getInstancia().findAll();
        List<Post> p2=user_post(nombre);
        for (Post p: p2){
            postes.add("ID:"+p.getId()+" Title:"+p.getTitle()+" Body:"+p.getBody()+" fecha:"+p.getDateTime()+" Autor:"+p.getAuthorp().getUsername()+" Tags:"+(p.getUserTags().size()+p.getTags().size())+" Comentarios:"+p.getComments().size() + " imagen: "+ p.getImage());
            System.out.println("ID:"+p.getId()+" Title:"+p.getTitle()+" Body:"+p.getBody()+" fecha:"+p.getDateTime()+" Autor:"+p.getAuthorp().getUsername()+" Tags:"+(p.getUserTags().size()+p.getTags().size())+" Comentarios:"+p.getComments().size() + " imagen: "+ p.getImage());
        }
        return postes;
    }


}

