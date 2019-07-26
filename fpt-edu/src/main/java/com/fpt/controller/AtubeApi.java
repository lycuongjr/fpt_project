package com.fpt.controller;

import com.fpt.entity.Atube;
import com.fpt.entity.JsonData;
import com.fpt.util.StringUtil;
import com.google.appengine.repackaged.com.google.api.client.json.Json;
import com.google.appengine.repackaged.com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class AtubeApi extends HttpServlet {
    private static Gson gson = new Gson();
    private static final Logger LOGGER = Logger.getLogger(Atube.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // tra ve list or detail
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        try{
            String strId = req.getParameter("id");
            if (strId == null || strId.length() == 0){
                //load list
                List<Atube> list = ofy().load().type(Atube.class).filter("status != ", 1).list();
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println(new JsonData().setStatus(HttpServletResponse.SC_OK).setMessage("Save atuve success").setData(list).toJsonString());
            }else {
                //load detail
                Atube atube = ofy().load().type(Atube.class).id(Long.parseLong(strId)).now();
                if (atube == null){
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().println(new JsonData().setStatus(HttpServletResponse.SC_OK).setMessage("Atube is not found or deleted").toJsonString());
                    return;
                }
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().println(new JsonData().setStatus(HttpServletResponse.SC_OK).setMessage("Atube detail").setData(atube).toJsonString());
            }

        } catch (Exception ex){
            String messageError = String.format("Can not create atube, error %s", ex.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println(new JsonData().setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).setMessage(messageError).toJsonString());
            LOGGER.log(Level.SEVERE, messageError);

        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //create new
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try {
            String content = StringUtil.convertInputStreamToString(req.getInputStream());
            Atube atube = gson.fromJson(content, Atube.class);
            ofy().save().entity(atube).now();
            resp.setStatus(HttpServletResponse.SC_CREATED);
            JsonData jsonData = new JsonData(HttpServletResponse.SC_CREATED, atube, "Save atube Success");
            resp.getWriter().println(gson.toJson(jsonData));


        }catch (Exception ex){
            String messageError = String.format("Can not create new aube, error: %s", ex.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonData jsonData = new JsonData(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, messageError, null);
            resp.getWriter().println(gson.toJson(jsonData));
            LOGGER.log(Level.SEVERE, messageError);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        //update


        // kiem tra ton tai tham so id trong paramater (cach tam thoi)
        //trong truong hop khong ton tai thi tra ve bad request
        String strId = req.getParameter("id");
        if (strId == null || strId.length() == 0){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonData jsonData = new JsonData(HttpServletResponse.SC_BAD_REQUEST, null, "bad request");
            resp.getWriter().println(gson.toJson(jsonData));
            LOGGER.log(Level.SEVERE, "do not ID");
            return;
        }
        // kiem tra su ton tai cua Atube trong database voi id truyen len
        // trong truong` hop` k to`n tai thi` tra ve not found
        Atube existAtube = ofy().load().type(Atube.class).id(Long.parseLong(strId)).now();
        if (existAtube == null || existAtube.getStatus() == 0){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            JsonData jsonData = new JsonData(HttpServletResponse.SC_NOT_FOUND, null,"Atube not found or deleted");
            resp.getWriter().println(gson.toJson(jsonData));
            LOGGER.log(Level.SEVERE, "Atube is not found or deleted!");
            return;
        }
        try {
            String content = StringUtil.convertInputStreamToString(req.getInputStream());
            Atube atubeUpdate = gson.fromJson(content, Atube.class);
            existAtube.setName(atubeUpdate.getName());
            existAtube.setDescription(atubeUpdate.getDescription());
            existAtube.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
            ofy().save().entity(existAtube).now();

            resp.setStatus(HttpServletResponse.SC_OK);
            JsonData jsonData = new JsonData(HttpServletResponse.SC_OK, existAtube, "Save atube success (update)");
            resp.getWriter().println(gson.toJson(existAtube));


        }catch (Exception ex){
            String messageError = String.format("Can not update atube %s", ex.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonData jsonData = new JsonData(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null, messageError);
            resp.getWriter().println(gson.toJson(jsonData));
            LOGGER.log(Level.SEVERE, messageError);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //remove
        //update
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String strId = req.getParameter("id");
        if(strId == null || strId.length() == 0){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JsonData jsonData = new JsonData(HttpServletResponse.SC_BAD_REQUEST, null, "Bad request");
            resp.getWriter().println(gson.toJson(jsonData));
            LOGGER.log(Level.SEVERE, "Have no id");
            return;
        }
        //kiem tra ton tai cua Atube trong database voi id truyen len
        // trong truong hop khong ton tai thi tra ve not found
        Atube existTube = ofy().load().type(Atube.class).id(Long.parseLong(strId)).now();
        if (existTube == null || existTube.getStatus() == 0){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            JsonData jsonData = new JsonData(HttpServletResponse.SC_NOT_FOUND, null, "Atube is not found or deteled!");
            resp.getWriter().println(gson.toJson(jsonData));
            LOGGER.log(Level.SEVERE, "Atube is not found or deleted");
            return;
        }
        try {
                existTube.setStatus(0);
                existTube.setDeletedat(Calendar.getInstance().getTimeInMillis());
                ofy().save().entity(existTube).now();
                resp.setStatus(HttpServletResponse.SC_OK);
                JsonData jsonData = new JsonData(HttpServletResponse.SC_OK, null, "Remove atube success!" );
                resp.getWriter().println(gson.toJson(jsonData));
                LOGGER.log(Level.SEVERE, "remove atube success");

        }catch (Exception ex){
            String messageError = String.format("Can not remove atube, error %s", ex.getMessage());
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JsonData jsonData = new JsonData(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, null, messageError);
            resp.getWriter().println(gson.toJson(jsonData));
            LOGGER.log(Level.SEVERE, messageError);

        }

    }
}
