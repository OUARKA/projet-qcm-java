<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="modele.QuestionnairePasse" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="css/screen.css" type="text/css" media="screen" title="css" charset="utf-8" />
        <title>Projet QCM</title>
    </head>
    <body>
        <div id="content">
            <jsp:include page="scripts/header.jsp" />

            <div id="body">
                <jsp:include page="scripts/menu_left.jsp" />

                <div id="contenu">
                    <h4>Mes questionnaires passés</h4>
                    <%
                                List<QuestionnairePasse> questionnairesPasses = (List<QuestionnairePasse>) request.getAttribute("questionnairesPasses");
                                if (questionnairesPasses != null && !questionnairesPasses.isEmpty()) {

                                    out.println("<table class='format'>");
                    %>
                    <tr>
                        <th>Date</th>
                        <th>Intitulé</th>
                        <th>Note</th>
                        <th>Limite de temps</th>
                    </tr>

                    <%
                                                for (QuestionnairePasse qP : questionnairesPasses) {
                    %>
                    <tr>
                        <td class="centered"><%= qP.getDate()%></td>
                        <td><a href="MesResultats?action=getCorrection&questionnaire=<%= qP.getIdQuestionnaire()%>"><%= qP.getLibelleQuestionnaire()%></a></td>
                        <td class="centered"><%= qP.getNote()%></td>
                        <td class="centered">
                            <%
                                                            if (qP.getTemps() != null && qP.getTemps() > 0) {
                                                                out.println(qP.getTemps());
                                                            }
                            %>

                        </td>


                    </tr>
                    <%
                                    }
                                    out.println("</table>");
                                } else {
                                    out.println("Vous n'avez encore passé aucun questionnaire.");
                                }
                    %>
                </div>
            </div>

            <div id="footer">
                <p>&copy; Copyright 2009 Ferrand &ndash; Rabarison &mdash; Design: Lou Ferrand &ndash; Maria Rabarison, <a href="#" title="Projet Java">DagoFly</a></p>
            </div>
        </div>
    </body>
</html>