<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultation</title>
    </head>
    <body>
        <c:if test="${empty requestScope.message}">
            <section class="bd-show">
                <p class="bd-title"><c:out value="${requestScope.bd.titre}" /></p>
                <c:url value="${requestScope.bd.image}" var="image" />
                <img class="bd-image" src="${image}" alt="${requestScope.bd.titre}">
                <p class="bd-editor">Editeur: <c:out value="${requestScope.bd.editeur}" /></p>
                <p class="bd-resume">Résumé: <c:out value="${requestScope.bd.resume}" /></p>
                <p class="bd-format">Format: <c:out value="${requestScope.bd.format}" /></p>
                <p class="bd-print-end">Fin d'impression: <c:out value="${requestScope.bd.finImpression}" /></p>
                <p class="bd-serie">Série: <c:out value="${requestScope.bd.serie}" /></p>
            </section>
        </c:if>
        ${requestScope.message}
    </body>
</html>
