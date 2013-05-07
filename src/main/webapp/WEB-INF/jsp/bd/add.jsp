<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="<c:url value = "/img/favicon.ico" />" />
        <link rel="stylesheet" href="<c:url value = "/css/addBd.css" />" type="text/css" media="screen"/>
        <c:set scope="request" var="js1" > <c:url value = "/js/vendor/jquery-1.7.1.min.js" /> </c:set>
        <c:set scope="request" var="js2" > <c:url value = "/js/vendor/jquery-ui-1.8.18.custom.min.js" /> </c:set>
        <c:set scope="request" var="js3" > <c:url value = "/js/vendor/angular.min.js" /> </c:set>
        <c:set scope="request" var="js4" > <c:url value = "/js/app.js" /> </c:set>
        <c:set scope="request" var="js5" > <c:url value = "/js/controllers/AddBdCtrl.js" /></c:set>
        <c:set scope="request" var="js6" > <c:url value = "/js/controllers/FileUploadCtrl.js" /></c:set>
        <script type="text/javascript" src="${js1}"></script>
        <script type="text/javascript" src="${js2}"></script>
        <script type="text/javascript" src="${js3}"></script>
        <script type="text/javascript" src="${js4}"></script>
        <script type="text/javascript" src="${js5}"></script>
        <script type="text/javascript" src="${js6}"></script>
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
                        <c:import url="/WEB-INF/jsp/bd/addForm.jsp" />
                    </c:when>
                    <c:otherwise>
                        <c:import url="/WEB-INF/jsp/bd/uploadForm.jsp" />
                    </c:otherwise>
                </c:choose>
            </section>

        </section>
        <c:import url="/WEB-INF/jsp/footer.jsp"/>
    </body>
</html>
