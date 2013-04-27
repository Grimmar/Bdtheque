<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultation</title>
    </head>
    <body>
        <section class="bd-show">
            ${requestScope.bd.titre}
            ${requestScope.bd.editeur}
            ${requestScope.bd.resume}
            ${requestScope.bd.format}
            ${requestScope.bd.finImpression}
        </section>
    </body>
</html>
