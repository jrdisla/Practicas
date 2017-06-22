package Clases;
import Clases.*;
import Handlers.*;
import freemarker.template.Configuration;
import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.*;

import static spark.Spark.*;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by ${jrdis} on ${16/6/2017}.
 */
public class manejadorTemplate {
    int page =1;
    int id_actual_article =0;
    int id_local_article =0;
    int comment_id_global = 0;

    public void startApp() {
        Spark.staticFileLocation("/public");
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
        configuration.setClassForTemplateLoading(manejadorTemplate.class, "/templates");
        FreeMarkerEngine FreeMarkerengine = new FreeMarkerEngine(configuration);

        startPage(FreeMarkerengine);
        login(FreeMarkerengine);
        home(FreeMarkerengine);
        add(FreeMarkerengine);
        addArticulo(FreeMarkerengine);
        addArticuloAdded(FreeMarkerengine);
        pagination(FreeMarkerengine);
        List_One_article(FreeMarkerengine);
        validateUser(FreeMarkerengine);
        addedUser(FreeMarkerengine);
        callUserAdd(FreeMarkerengine);
        listArtyby(FreeMarkerengine);
        deleteArticulo(FreeMarkerengine);
        updateArtit(FreeMarkerengine);
        IndividualShow(FreeMarkerengine);
        deleteComment(FreeMarkerengine);
        showTags(FreeMarkerengine);
        listArticleByTag(FreeMarkerengine);
        invalidadSesion(FreeMarkerengine);
        preferesDislike(FreeMarkerengine);
        preferesDislikeComment(FreeMarkerengine);

    }

    public void home(FreeMarkerEngine engine) {
        /***
         * http://localhost:4567/home/
         * @param engine
         */
        before("/home/",(request, response) -> {
            User usuario_ = request.session().attribute("username");
            UserHandler userHandler = UserHandler.getInstance();
            User usuario = userHandler.GetUser(usuario_.getUsername());
            if(usuario==null){
                //parada del request, enviando un codigo.
                halt(401, "No tiene permisos para acceder");
            }
        });
        get("/home/", (request, response) -> {
            int offset = ((page-1)*5);
            ArticleHandler articleHandler = ArticleHandler.getInstance();
            List<Article> articles = articleHandler.pagination(5,offset);
            String html = autoMaticHtml_List_Arti(articles);
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Start Page");
            attributes.put("code",html);
            User user = request.session().attribute("username");
            attributes.put("user",user);
            return new ModelAndView(attributes, "home.ftl");
        }, engine);
    }

    public void startPage(FreeMarkerEngine engine) {
        /***
         * http://localhost:4567/startPage/
         * @param engine
         */
        get("/startPage/", (request, response) -> {


            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Start Page");


            return new ModelAndView(attributes, "startPage.ftl");
        }, engine);
    }

    public void login(FreeMarkerEngine engine) {
        post("/login/", (request, response) -> {
            //

            Session session = request.session(true);
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            System.out.println(username);
            UserHandler userHandler = UserHandler.getInstance();
            User usuario = userHandler.GetUser(username);
            if (usuario != null && usuario.getPassword().equalsIgnoreCase(password)) {

                session.attribute("username", usuario);

            } else {
                halt(401, "Credenciales no validas...");
                response.redirect("/startPage/");
            }
            int offset = ((page-1)*5);
            ArticleHandler articleHandler = ArticleHandler.getInstance();
            List<Article> articles = articleHandler.pagination(5,offset);
            String html = autoMaticHtml_List_Arti(articles);

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Start Page");
            attributes.put("code",html);
            User user = request.session().attribute("username");
            attributes.put("user",user);

            return new ModelAndView(attributes, "home.ftl");
        }, engine);
    }

    public void add(FreeMarkerEngine engine) {


        post("/signup/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            //get fields:
            String username = request.queryParams("username"),
                    fullname = request.queryParams("fullname"),
                    password = request.queryParams("password"),
                    password2 = request.queryParams("password2");

            System.out.println(username);

            UserHandler userHandler = UserHandler.getInstance();

            attributes.put("username", username);
            attributes.put("fullname", fullname);

            if (username.length() < 6) {
                return ("El nombre de usuario ha de tener almenos seis (6) caracteres.");
            }
            if (fullname == null || fullname.equals("")) {
                return ("No es posible dejar el campo de nombre vacio.");
            }
            if (!password.equals(password2)) {
                return ("Las contrasenas insertadas no son iguales. Revise de nuevo.");
            }
            if (password.length() < 6) {
                return ("La contrasena debe contener almenos seis (6) caracteres.");
            }

            User user = new User();
            user.setUsername(username);
            user.setName(fullname);
            user.setPassword(password);
            user.setAdministrator(false);
            user.setAuthor(false);

            userHandler.insertIntoDatabase(user);
            request.session(true).attribute("user", user);
            response.redirect("/home/");


            return null;
        });
    }

    public void addArticulo (FreeMarkerEngine engine)
    {
        /***
         * http://localhost:4567/startPage/
         * @param engine
         */
        get("/addArti/", (request, response) -> {


            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Add Articulo");
            User user = request.session().attribute("username");
            attributes.put("user",user);

            return new ModelAndView(attributes, "addArticulo.ftl");
        }, engine);
    }
    public void addArticuloAdded(FreeMarkerEngine engine) {

        post("/addArti/added/", (request, response) -> {

            ArticleHandler articleHandler = ArticleHandler.getInstance();
            TagHandler tagHandler = TagHandler.getInstance();

            User user = request.session().attribute("username");
            Date publishedDate = new Date();

            String articleTitle = request.queryParams("titulo");
            String articleBody = request.queryParams("comment");

            List<String> articleTags = Arrays.asList(request.queryParams("tags").split("\\s*,\\s*"));
            Article article = new Article(articleTitle, articleBody, publishedDate, user);
            articleHandler.insertIntoDatabase(article);



            for (String tagTitle : articleTags) {

                Tag existingTag = tagHandler.getTags(tagTitle);
                Tag tag = null;
                if(existingTag != null) {

                    tag = existingTag;
                }
                else{
                    System.out.println(article.getBody());
                    tag = new Tag(tagTitle);
                    tagHandler.insertIntoDatabase(tag);
                    tag.getArticles().add(article);
                    article.getTags().add(tag);

                }

                article.getTags().add(tag);
                tag.getArticles().add(article);
                continue;
            }
            articleHandler.updateObject(article);
            response.redirect("/home/");
            return null;
        });
    }

    public void pagination (FreeMarkerEngine engine)
    {
        before("/home/:id/",(request, response) -> {
            User usuario_ = request.session().attribute("username");
            UserHandler userHandler = UserHandler.getInstance();
            User usuario = userHandler.GetUser(usuario_.getUsername());
            if(usuario==null){
                //parada del request, enviando un codigo.
                halt(401, "No tiene permisos para acceder");
            }
        });
        get("/home/:id/", (request, response) -> {

            page = Integer.parseInt(request.params(":id"));
            int offset = ((page-1)*5);
            ArticleHandler articleHandler = ArticleHandler.getInstance();
            List<Article> articles = articleHandler.pagination(5,offset);
            String html = autoMaticHtml_List_Arti(articles);
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Start Page");
            attributes.put("code",html);
            User user = request.session().attribute("username");
            attributes.put("user",user);
            return new ModelAndView(attributes, "home.ftl");
        }, engine);
    }

    public void List_One_article (FreeMarkerEngine engine)
    {
        before("/article/:id/",(request, response) -> {
            User usuario_ = request.session().attribute("username");
            UserHandler userHandler = UserHandler.getInstance();

            if(userHandler.GetUser(usuario_.getUsername())== null){
                //parada del request, enviando un codigo.
                halt(401, "No tiene permisos para acceder");
            }
        });
        get("/article/:id/", (request, response) -> {

            int id = Integer.parseInt(request.params(":id"));
            id_actual_article = id;
            ArticleHandler articleHandler = ArticleHandler.getInstance();
            Article article = articleHandler.findObjectWithId(id);
            User usuario_ = request.session().attribute("username");
            ArticleLikesHandler articlePreferenceHandler = ArticleLikesHandler.getInstance();

            List<ArticleLikes> articlePreferences = articlePreferenceHandler.getAllObjects();
            // Article article = articleHandler.findObjectWithId(id)

            CommentHandler commentHandler = CommentHandler.getInstance();


            User user = request.session().attribute("username");
            String htmlCode = autoMaticHtml_One_Arti(article);

            Map<String, Object> attributes = new HashMap<>();
            int count =0;
            int cout_dis=0;

            int count_comment =0;
            int cout_dis_comment=0;

            for(ArticleLikes item: article.getArticlePreferences())
            {
                System.out.println("Size de articulos: "+ article.getArticlePreferences().size());
                if (item.getStatus().equalsIgnoreCase("LIKE"))
                {
                    count++;
                }
                else
                {
                    cout_dis++;
                }
            }
            if (comment_id_global != 0){

                for(CommentLikes item: CommentHandler.getInstance().findObjectWithId(comment_id_global).getCommentLikes())
                {

                    if (item.getStatus().equalsIgnoreCase("LIKE"))
                    {
                        count_comment++;
                    }
                    else
                    {
                        cout_dis_comment++;
                    }
                }}
            String htmlCode2 = automaticCommentHtmlCode(commentHandler.getAllObjects(),id);
            attributes.put("Titulo", "List Article");
            attributes.put("user",user);
            attributes.put("code",htmlCode);

            attributes.put("cout",count);

            attributes.put("cout_dis",cout_dis);
            attributes.put("code2",htmlCode2);
            attributes.put("cout_c",count_comment);

            attributes.put("cout_dis_c",cout_dis_comment);
            System.out.println(count);
            System.out.println(cout_dis);

            return new ModelAndView(attributes, "only_one_article.ftl");

        }, engine);
    }
    public void validateUser (FreeMarkerEngine engine)
    {
        before("/articulo/valida/",(request, response) -> {
            User usuario_ = request.session().attribute("username");
            UserHandler userHandler = UserHandler.getInstance();
            User usuario = userHandler.GetUser(usuario_.getUsername());
            if(usuario==null){
                //parada del request, enviando un codigo.
                halt(401, "No tiene permisos para acceder");
            }
        });

        post("/articulo/valida/", (request, response) -> {
            String comment = request.queryParams("comment");
            ArticleHandler articleHandler = ArticleHandler.getInstance();
            CommentHandler commentHandler = CommentHandler.getInstance();

            Article articulo = articleHandler.findObjectWithId(id_actual_article);
            User usuario = request.session().attribute("username");

            Comment comentario = new Comment(comment, usuario, articulo);
            commentHandler.insertIntoDatabase(comentario);

            List<Comment> comentarios = commentHandler.getAllObjects();

            String htmlCode =  autoMaticHtml_One_Arti(articulo);




            String htmlCode2 =  automaticCommentHtmlCode(comentarios,articulo.getId());

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Articles");
            attributes.put("code",htmlCode);
            attributes.put("code2",htmlCode2);
            User user = request.session().attribute("username");
            attributes.put("user",user);
            response.redirect("/article/"+articulo.getId()+"/");
            return new ModelAndView(attributes, "only_one_article.ftl");
        }, engine);
    }
    public void callUserAdd (FreeMarkerEngine engine)
    {
        before("/addUser/",(request, response) -> {
            User usuario_ = request.session().attribute("username");
            UserHandler userHandler = UserHandler.getInstance();
            User usuario = userHandler.GetUser(usuario_.getUsername());
            if(usuario==null){
                //parada del request, enviando un codigo.
                halt(401, "No tiene permisos para acceder");
            }
        });
        get("/addUser/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Add User");
            User user = request.session().attribute("username");
            attributes.put("user",user);
            return new ModelAndView(attributes, "admin_add_user.ftl");
        }, engine);
    }
    public void addedUser (FreeMarkerEngine engine)
    {
        before("/addUser/added/",(request, response) -> {
            User usuario_ = request.session().attribute("username");
            UserHandler userHandler = UserHandler.getInstance();
            User usuario = userHandler.GetUser(usuario_.getUsername());
            if(usuario==null){
                //parada del request, enviando un codigo.
                halt(401, "No tiene permisos para acceder");
            }
        });
        post("/addUser/added/", (request, response) -> {
            boolean adm = false;
            boolean autor = false;
            String username = request.queryParams("username");
            String name = request.queryParams("nombre");
            String password = request.queryParams("password");
            String password2 = request.queryParams("password2");
            String adm_strin = request.queryParams("adm");
            if(password.equalsIgnoreCase(password2)) {
                if (adm_strin != null) {
                    adm = true;
                }
                String autor_strin = request.queryParams("autor");
                if (autor_strin != null) {
                    autor = true;
                }

                User user = new User(username, name, password, adm, autor);
                UserHandler.getInstance().insertIntoDatabase(user);
                response.redirect("/home/");

            }
            else
            {
                halt(401, "Contraseñas no coinciden");
            }
            return null;
        }, engine);
    }

    public void listArtyby (FreeMarkerEngine engine)
    {
        get("/listArtiBy/", (request, response) -> {
            User user_arti = request.session().attribute("username");
            List<Article> articulos = ArticleHandler.getInstance().getAllObjects();
            String html="";
            List<Article> list_aux= new ArrayList<>();
            if(!user_arti.getAdministrator()){
                for (Article item:articulos
                        ){
                    if(item.getAuthor().getUsername().equalsIgnoreCase(user_arti.getUsername()))
                    {
                        list_aux.add(item);
                    }
                }
                html = automaticHtmlCodeForTable(list_aux);}
            else
            {
                html = automaticHtmlCodeForTable(articulos);
            }
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Adding Articule");
            attributes.put("code", html);
            User user = request.session().attribute("username");
            attributes.put("user",user);
            return new ModelAndView(attributes, "listArtyByUser.ftl");
        }, engine);
    }

    public void deleteArticulo(FreeMarkerEngine engine) {
        get("/deleteArticulo/:id/", (request, response) -> {
            int ID = Integer.parseInt(request.params(":id"));

            ArticleHandler.getInstance().deleteObjectWithId(ID);
            response.redirect("/listArtiBy/");
            return "";
        });
    }

    public void updateArtit(FreeMarkerEngine FreeMarkerengine) {
        get("/actArticulo/:id/", (request, response) -> {
            Article articulo = ArticleHandler.getInstance().findObjectWithId(Integer.parseInt(request.params("id")));
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Updating Article");
            attributes.put("articulo", articulo);
            attributes.put("tags",articulo.getStringTags());
            User user = request.session().attribute("username");
            attributes.put("user",user);
            return new ModelAndView(attributes, "updateArti.ftl");
        }, FreeMarkerengine);
    }

    public void IndividualShow(FreeMarkerEngine FreeMarkerengine) {
        get("/individualInfo/:id/", (request, response) -> {
            ArticleHandler articleHandler =ArticleHandler.getInstance();
            Article articulo = articleHandler.findObjectWithId(Integer.parseInt(request.params("id")));
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Información de Articulo");
            attributes.put("articulo", articulo);
            attributes.put("tags",articulo.getStringTags());
            User user = request.session().attribute("username");
            attributes.put("user",user);
            return new ModelAndView(attributes, "infoArticulo.ftl");
        }, FreeMarkerengine);

        post("/individualInfo/:id/", (request, response) -> {
            ArticleHandler articleHandler =ArticleHandler.getInstance();
            Article articulo = articleHandler.findObjectWithId(Integer.parseInt(request.params("id")));
            TagHandler tagHandler = TagHandler.getInstance();

            articulo.setBody(request.queryParams("comment"));
            articulo.setTitle(request.queryParams("titulo"));

            Collection<String> articleTagss = Arrays.asList(request.queryParams("tags").split("\\s*,\\s*")); // modificados
            Collection<String> articletagss = Arrays.asList(articulo.getStringTags().split("\\s*,\\s*")); // los que tiene

            Collection<String> similar = new HashSet<String>( articletagss );
            Collection<String> different = new HashSet<String>();
            different.addAll( articletagss );
            different.addAll( articleTagss );

            similar.retainAll( articleTagss );
            different.removeAll( similar );

            for (String tagTitle : different) {

                Tag tag = null;

                tag = new Tag(tagTitle);
                tagHandler.insertIntoDatabase(tag);
                tag.getArticles().add(articulo);
                articulo.getTags().add(tag);

                continue;
            }
            articleHandler.updateObject(articulo);
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Article's Information");
            attributes.put("articulo", articulo);
            User user = request.session().attribute("username");
            attributes.put("user",user);
            attributes.put("tags",articulo.getStringTags());
            return new ModelAndView(attributes, "infoArticulo.ftl");
        }, FreeMarkerengine);
    }

    public void deleteComment(FreeMarkerEngine engine) {
        get("/deleteComment/:id/", (request, response) -> {
            int ID = Integer.parseInt(request.params(":id"));
            System.out.println("LLEGANDOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo");
            CommentHandler.getInstance().deleteCommentById(ID);
            response.redirect("/article/"+id_local_article+"/");
            return "";
        });
    }

    public void showTags (FreeMarkerEngine engine)
    {
        get("/listTags/", (request, response) -> {

            String html = automaticHtmlCodeForTabletags(TagHandler.getInstance().getAllObjects());
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Información de Articulo");
            attributes.put("code",html);
            User user = request.session().attribute("username");
            attributes.put("user",user);
            return new ModelAndView(attributes, "list_tags.ftl");
        }, engine);

    }

    public void listArticleByTag (FreeMarkerEngine engine)
    {
        get("/seeArticles/:id/", (request, response) -> {
            int id_tag = Integer.parseInt(request.params(":id"));
            List<Article> articles = ArticleHandler.getInstance().getByTag(id_tag);
            String html = automaticHtmlCodeForTable(articles);
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Adding Articule");
            attributes.put("code", html);
            User user = request.session().attribute("username");
            attributes.put("user",user);
            return new ModelAndView(attributes, "listArtyByUser.ftl");
        }, engine);
    }

    public void invalidadSesion (FreeMarkerEngine engine)
    {
        get("/invalidarSesion/", (request, response) -> {
            Session session = request.session(true);
            session.invalidate();
            response.redirect("/startPage/");
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("Titulo", "Logout");
            return null;
        }, engine);
    }


    public void preferesDislike (FreeMarkerEngine engine)
    {
        get("/preferences/likes/:id", (request, response) -> {

            int id = Integer.parseInt(request.params(":id"));

            User user = request.session().attribute("username");
            Article article = ArticleHandler.getInstance().findObjectWithId(id_actual_article);

            ArticleLikesHandler articleLikesHandler =  ArticleLikesHandler.getInstance();


            if (id == 1) {
                ArticleLikes articleLikes = new  ArticleLikes("LIKE", user, article);
                ArticleLikes item = articleLikesHandler.getItByArtiUser(user.getId(), article.getId());
                if (item != null) {


                    if (item.getStatus().substring(0, 3).equalsIgnoreCase("DIS")) {

                        int ida = item.getId();
                        System.out.println(item.getId());
                        articleLikesHandler.deleteObjectWithId(ida);
                        articleLikes.setStatus("LIKE");
                        articleLikesHandler.insertIntoDatabase(articleLikes);

                    } else {
                        int id_a = item.getId();
                        articleLikesHandler.deleteObjectWithId(id_a);
                        response.redirect("/article/"+article.getId()+"/");

                    }
                } else {

                    articleLikesHandler.insertIntoDatabase(articleLikes);
                }
            }

            else {

                ArticleLikes articlePreference = new ArticleLikes("DISLIKE", user, article);
                ArticleLikes item = articleLikesHandler.getItByArtiUser(user.getId(), article.getId());
                if (item != null) {
                    if (item.getStatus().substring(0, 3).equalsIgnoreCase("LIK")) {

                        int ida = item.getId();
                        articleLikesHandler.deleteObjectWithId(ida);
                        articlePreference.setStatus("DISLIKE");
                        articleLikesHandler.insertIntoDatabase(articlePreference);

                    }
                    else {
                        int id_a = item.getId();
                        articleLikesHandler.deleteObjectWithId(id_a);
                        response.redirect("/article/"+article.getId()+"/");
                    }
                }
                else {
                    articleLikesHandler.insertIntoDatabase(articlePreference);
                }

            }
            response.redirect("/article/"+article.getId()+"/");
            return null;
        }, engine);
    }

    public void preferesDislikeComment (FreeMarkerEngine engine)
    {
        get("/preferencesA/:id/:comment_id/", (request, response) -> {
            int id = Integer.parseInt(request.params(":id"));
            int comment_id = Integer.parseInt(request.params(":comment_id"));
            comment_id_global = comment_id;
            User user = request.session().attribute("username");

            CommentLikesHandler commentLikesHandler = CommentLikesHandler.getInstance();
            Comment comment = CommentHandler.getInstance().findObjectWithId(comment_id);

            if (id == 1) {
                CommentLikes articlePreference = new CommentLikes("LIKE", user, comment);
                CommentLikes item = commentLikesHandler.getByUserComment(user.getId(), comment.getId());
                if (item != null) {


                    if (item.getStatus().substring(0, 3).equalsIgnoreCase("DIS")) {

                        int ida = item.getId();
                        commentLikesHandler.deleteObjectWithId(ida);
                        articlePreference.setStatus("LIKE");
                        commentLikesHandler.insertIntoDatabase(articlePreference);

                    } else {
                        int ida2 = item.getId();
                        commentLikesHandler.deleteObjectWithId(ida2);

                        response.redirect("/article/"+id_actual_article+"/");
                    }
                } else {

                    commentLikesHandler.insertIntoDatabase(articlePreference);
                }
            }

            else {

                CommentLikes commentLikes = new CommentLikes("DISLIKE", user, comment);
                CommentLikes item_comment = commentLikesHandler.getByUserComment(user.getId(), comment.getId());
                if (item_comment != null) {
                    if (item_comment.getStatus().substring(0, 3).equalsIgnoreCase("LIK")) {

                        int ida = item_comment.getId();
                        commentLikesHandler.deleteObjectWithId(ida);
                        commentLikes.setStatus("DISLIKE");
                        commentLikesHandler.insertIntoDatabase(commentLikes);

                    }
                    else {
                        int ida2 = item_comment.getId();
                        commentLikesHandler.deleteObjectWithId(ida2);
                        response.redirect("/article/"+id_actual_article+"/");
                    }
                }
                else {
                    commentLikesHandler.insertIntoDatabase(commentLikes);
                }

            }
            response.redirect("/article/"+comment.getArticle().getId()+"/");
            return null;
        }, engine);
    }

    //------------------------ FUNTIONS TO GENERATE HTML CODE --------------------------------------
    public String autoMaticHtml_List_Arti (List<Article> articles)
    {
        String htmlCode = "";
        for (Article item: articles) {
            htmlCode += "<div class=\"post-preview\">" + "\n\t\t" +
                    "<h2 class=\"post-title\">" + item.getTitle() + "</h2>" + "\n\t\t" +
                    "<h5> Posted by: "+ item.getAuthor().getUsername() + "</h5>" + "\n\t\t" +
                    "<h5><span class=\"glyphicon glyphicon-time\"></span>"+ item.getDate() + "</h5>" + "\n\t\t" +
                    "<h5><span class=\"glyphicon glyphicon-tags\"></span>"+ " " + item.getStringTags() + "</h5>" + "\n\t\t" +
                    "<h5 onclick=\"document.location = '/article/" + item.getId() + "/';\"><span class=\"label label-danger\">" + item.getTitle() + "</h5><br>" + "\n\t\t" +
                    "<p>" + item.getBody().substring(0,70) + "</p>" + "\n\t\t\t" +
                    " <br><br>\n\t    ";

        }

        return htmlCode;
    }
    public String autoMaticHtml_One_Arti (Article item)
    {
        String htmlCode = "";

        htmlCode += "<div class=\"post-preview\">" + "\n\t\t" +
                // "<a href=\"/article/" + item.getId() + ">" + "\n\t\t" +
                "<h2 class=\"post-title\">" + item.getTitle() + "</h2>" + "\n\t\t" +
                //   "</a>"+ "\n\t\t" +
                "<h5> Posted by: "+ item.getAuthor().getUsername() + "</h5>" + "\n\t\t" +
                "<h5><span class=\"glyphicon glyphicon-time\"></span>"+ item.getDate() + "</h5>" + "\n\t\t" +
                "<h5><span class=\"glyphicon glyphicon-tags\"></span>"+ " " + item.getStringTags() + "</h5>" + "\n\t\t" +
                "<p>" + item.getBody() + "</p>" + "\n\t\t\t" +
                " <br><br>\n\t    ";

        return htmlCode;
    }
    public String automaticCommentHtmlCode (List<Comment> item, int id_arti)
    {
        id_local_article = id_arti;
        String htmlCode = "";
        for (Comment itemp:item
                ) {
            if (itemp.getArticle().getId() == id_arti){
                System.out.println(itemp.getAuthor().getUsername());
                htmlCode += "<h4><small> Recent Post </small></h4>" + "\n\t\t" +
                        "<hr>" + "\n\t\t" +
                        "<h5><span class=\"glyphicon glyphicon-time\"></span> Posted by: " + itemp.getAuthor().getUsername() + "</h5>" + "\n\t\t" +
                        "<p>" + itemp.getBody() + "</p>" + "\n\t\t\t" +
                        "<a href=\"/deleteComment/" + itemp.getId() + "/\"class=\"btn btn-danger\"  role=\"button\">Eliminar</a>" + "\n\t\t\t"+
                        "<a href=\"/preferencesA/"+1+"/"+itemp.getId()+"/\"class=\"btn btn-danger\"  role=\"button\">LIKE</a>" + "\n\t\t\t"+
                        "<a href=\"/preferencesA/"+2+"/"+itemp.getId()+"/\"class=\"btn btn-danger\"  role=\"button\">DISLIKE </a>" + "\n\t\t\t";

            }
        }
        return htmlCode;}
    private String automaticHtmlCodeForTable(List<Article> articulos) {
        String htmlCode = "";
        for (Article item : articulos) {
            htmlCode += "<tr onclick=\"document.location = '/individualInfo/" + item.getId() + "/';\">" + "\n\t\t" +
                    "<td>" + item .getTitle() + "</td>" + "\n\t\t" +
                    "<td>" + item .getAuthor().getUsername() + "</td>" + "\n\t\t" +
                    "<td>" + item .getDate() + "</td>" + "\n\t\t" +
                    "<td>" + "\n\t\t\t" +
                    "<a href=\"/actArticulo/" + item .getId() + "/\" class=\"btn btn-warning\"  role=\"button\">Actualizar</a>" + "\n\t\t\t" +
                    "<a href=\"/deleteArticulo/" + item .getId() + "/\"class=\"btn btn-danger\"  role=\"button\">Eliminar</a>" + "\n\t\t\t" +
                    "<a href=\"/article/" + item .getId() + "/\"class=\"btn btn-primary\"  role=\"button\">Details</a>" + "\n\t\t\t" +
                    "</td>" + "\n\t    " +
                    "</tr>\n\t";
        }

        return htmlCode;
    }

    private String automaticHtmlCodeForTabletags(List<Tag> tags) {
        String htmlCode = "";
        for (Tag item : tags) {
            ;
            htmlCode += "<tr onclick=\"document.location = '/individualInfo/" + item.getId() + "/';\">" + "\n\t\t" +
                    "<td>" + item .getTags() + "</td>" + "\n\t\t" +
                    "<td>" + ArticleHandler.getInstance().getByTag(item.getId()).size() + "</td>" + "\n\t\t" +
                    "<td>" + "\n\t\t\t" +
                    "<a href=\"/seeArticles/" + item .getId() + "/\" class=\"btn btn-warning\"  role=\"button\">See Articles</a>" + "\n\t\t\t" +
                    "</td>" + "\n\t    " +
                    "</tr>\n\t";
        }

        return htmlCode;
    }
}
