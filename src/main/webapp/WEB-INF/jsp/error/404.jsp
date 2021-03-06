<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value = "/css/main.css" />" type="text/css" media="screen"/>
        <link rel="stylesheet" href="<c:url value = "/css/form.css" />" type="text/css" media="screen"/>
        <link rel="stylesheet" href="<c:url value = "/css/show.css" />" type="text/css" media="screen"/>
        <title>Consultation</title>
    </head>
    <body>
        <c:import url="/WEB-INF/jsp/header.jsp"/>

        <section class="content">
            <header>
                <h1>Erreur 404</h1>
            </header>
            <c:out value="${requestScope['javax.servlet.error.message']}"/>
        </section>

        <c:import url="/WEB-INF/jsp/footer.jsp"/>
    </body>
</html>
