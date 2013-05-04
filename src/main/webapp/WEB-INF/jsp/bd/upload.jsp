<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="<c:url value = "/img/favicon.ico" />" />
        <link rel="stylesheet" href="<c:url value = "/css/main.css" />" type="text/css" media="screen"/>
        <link rel="stylesheet" href="<c:url value = "/css/form.css" />" type="text/css" media="screen"/>
        <link rel="stylesheet" href="<c:url value = "/css/show.css" />" type="text/css" media="screen"/>
        <title>Charger une bd</title>
    </head>
    <body>

        <c:import url="/WEB-INF/jsp/header.jsp"/>
        <section class="content">
            <c:out value="${requestScope.form.result}" />
            <form action="<c:url value="/add" />" method="post" enctype="multipart/form-data">
                <fieldset>
                    <legend>Envoi de fichier</legend>
                    <c:if test="${!empty requestScope.form.errors}">
                        <ul>
                            <c:forEach items="${requestScope.form.errors['file']}" var="error">
                                <li><c:out value="${error}" /></li>
                            </c:forEach>    
                        </ul>
                    </c:if>
                    <label for="file">Emplacement du fichier</label>
                    <input type="file" name="file" />
                    <br />
                    <input type="submit" value="Envoyer" />
                    <br />
                </fieldset>
            </form>
        </section>
        <c:import url="/WEB-INF/jsp/footer.jsp"/>
    </body>
</html>
