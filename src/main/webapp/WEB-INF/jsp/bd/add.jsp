<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="<c:url value = "/img/favicon.ico" />" />
        <link rel="stylesheet" href="<c:url value = "/css/addBd.css" />" type="text/css" media="screen"/>
        <script src="<c:url value = "/js/vendor/jquery-1.7.1.min.js" />"></script>
        <script src="<c:url value = "/js/vendor/jquery-ui-1.8.18.custom.min.js" />"></script>
        <script src="<c:url value = "/js/vendor/angular.min.js" />"></script>
        <script src="<c:url value = "/js/app.js" />"></script>
        <script src="<c:url value = "/js/controllers/FileUploadCtrl.js" />"></script>
        <script src="<c:url value = "/js/controllers/AddBdCtrl.js" />"></script>
        <title>Ajouter une nouvelle bd</title>
    </head>

    <body>
        <c:import url="/WEB-INF/jsp/header.jsp"/>
        <input type="hidden" value="<c:url value = "/add" />" />
        <input type="hidden" value="<c:url value = "/upload" />" />


        <section class="tabs">
            <ul>
                <c:choose>
                    <c:when test="${requestScope.formName == 'add'}">
                        <li><a class="switch-link active" href="<c:url value = "/add" />" name="">Saisie manuelle</a></li>
                        <li><a class="switch-link}" href="<c:url value = "/upload" />" name="">Fichier XML</a></li>
                        </c:when>
                        <c:otherwise>
                        <li><a class="switch-link}" href="<c:url value = "/add" />" name="">Saisie manuelle</a></li>
                        <li><a class="switch-link active" href="<c:url value = "/upload" />" name="">Fichier XML</a></li>
                        </c:otherwise>
                    </c:choose>
            </ul>
        </section>
        <section class="content">
            <section class="bd-form">
                <c:choose>
                    <c:when test="${requestScope.formName == 'add'}">
                        <c:import url="/WEB-INF/jsp/bd/addForm.jsp"/>
                    </c:when>
                    <c:otherwise>
                        <c:import url="/WEB-INF/jsp/bd/uploadForm.jsp"/>
                    </c:otherwise>
                </c:choose>
            </section>

        </section>
        <c:import url="/WEB-INF/jsp/footer.jsp"/>
    </body>
</html>
