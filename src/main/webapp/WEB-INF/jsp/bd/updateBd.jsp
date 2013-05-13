<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="directives">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="<c:url value = "/img/favicon.ico" />" />
        <link rel="stylesheet" href="<c:url value = "/css/addBd.css" />" type="text/css" media="screen"/>
        <c:set scope="request" var="jquery" > <c:url value = "/js/vendor/jquery-1.7.1.min.js" /> </c:set>
        <c:set scope="request" var="jqueryUI" > <c:url value = "/js/vendor/jquery-ui-1.8.18.custom.min.js" /> </c:set>
        <c:set scope="request" var="angular" > <c:url value = "/js/vendor/angular.min.js" /> </c:set>
        <c:set scope="request" var="app" > <c:url value = "/js/app.js" /> </c:set>
        <c:set scope="request" var="addCtrl" > <c:url value = "/js/controllers/AddBdCtrl.js" /></c:set>
        <c:set scope="request" var="fileCtrl" > <c:url value = "/js/controllers/FileUploadCtrl.js" /></c:set>
        <script type="text/javascript" src="${jquery}"></script>
        <script type="text/javascript" src="${jqueryUI}"></script>
        <script type="text/javascript" src="${angular}"></script>
        <script type="text/javascript" src="${app}"></script>
        <script type="text/javascript" src="${addCtrl}"></script>
        <script type="text/javascript" src="${fileCtrl}"></script>
        <title>Modifier une bd</title>
    </head>
    <body>
        <c:import url="/WEB-INF/jsp/header.jsp"/>
        <c:if test="${!empty requestScope.form.result}">
            <div class="flash-error">${requestScope.form.result}</div>
        </c:if>
        <section class="content">
            <c:import url="/WEB-INF/jsp/bd/updateForm.jsp" />
        </section>

        <c:import url="/WEB-INF/jsp/footer.jsp"/>
    </body>
</html>
