package controllers;

import dal.IssueDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import model.Issue;

public class IssueController extends HttpServlet {

    IssueDAO dao = new IssueDAO();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {

            // ================= LIST =================
            case "list":
                List<Issue> list = dao.getAllIssues();
                request.setAttribute("issues", list);
                request.getRequestDispatcher(
                        "views_issue_report/IssueReportManagement.jsp")
                        .forward(request, response);
                break;

            // ================= DETAIL =================
            case "detail":
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Issue issue = dao.getIssueById(id);
                    if (issue != null) {
                        request.setAttribute("issue", issue);
                        request.getRequestDispatcher(
                                "views_issue_report/DetailIssue.jsp")
                                .forward(request, response);
                    } else {
                        response.sendRedirect("Issue?action=list");
                    }

                } catch (Exception e) {
                    response.sendRedirect("Issue?action=list");
                }
                break;

            // ================= FILTER =================
            case "filter":
                String date = request.getParameter("date");
                String status = request.getParameter("status");
                List<Issue> issues = dao.filter(date, status);
                request.setAttribute("issues", issues);
                request.getRequestDispatcher(
                        "views_issue_report/IssueReportManagement.jsp")
                        .forward(request, response);
                break;

            default:
                response.sendRedirect("Issue?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        // ================= UPDATE STATUS =================
        if ("updateStatus".equals(action)) {

            try {

                int issueId = Integer.parseInt(request.getParameter("issueId"));

                String issueStatus = request.getParameter("issueStatus");

                dao.updateIssue(issueId, issueStatus);

            } catch (Exception e) {

                e.printStackTrace();
            }

            response.sendRedirect("Issue?action=list");
        }
    }
}