package org.itstep.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.itstep.data.User;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends BaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = blogDao.findAllUsers();
        if (users != null){
            System.out.println("Admin GET users");
            users.stream().forEach(user -> System.out.println(user.toString()));
        }
        HttpSession session = req.getSession();
        session.setAttribute("users", users);
        System.out.println();
        req.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("title");
        LocalDateTime time = LocalDateTime.now();
        String published = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss").format((TemporalAccessor) time);
//        String author = req.getParameter("author");
        String imagePath = req.getParameter("imagepath");
        String content = req.getParameter("content");
        String draft = req.getParameter("draft");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Integer draftId = 0;
        if (title != null && !title.isBlank() &&
                imagePath != null && !imagePath.isBlank() &&
                content != null && !content.isBlank() &&
                user != null) {

            int authorId = user.getId();
            System.out.println("authorId = " + authorId);
            if (draft == null) {
                draft = "off";
            }
            switch (draft){
                case "on" -> draftId = Integer.valueOf(1);
                case "off" -> draftId = Integer.valueOf(2);
            }
            if (Integer.valueOf(authorId) instanceof Integer && draftId instanceof Integer){
                blogDao.addPostToDatabase(title, published, authorId, imagePath, content, draftId);
            }

        }
//        System.out.println("Post:" + req.getServletContext().getContextPath() + "/admin");
        resp.sendRedirect(req.getServletContext().getContextPath() + "/admin");
    }
}

