<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="<c:url value = "/img/favicon.ico" />" />
        <link rel="stylesheet" href="<c:url value = "/css/show.css" />" type="text/css" media="screen"/>
        <c:set scope="request" var="angular" > <c:url value = "/js/vendor/angular.min.js" /> </c:set>
        <c:set scope="request" var="app" > <c:url value = "/js/app.js" /> </c:set>
        <c:set scope="request" var="showCtrl" > <c:url value = "/js/controllers/ShowBdCtrl.js" /></c:set>
        <script type="text/javascript" src="${angular}"></script>
        <script type="text/javascript" src="${app}"></script>
        <script type="text/javascript" src="${showCtrl}"></script>
        <title>Consultation</title>
    </head>
    <body>
        <c:import url="/WEB-INF/jsp/header.jsp"/>
        <c:if test="${!empty requestScope.notice}">
            <div class="flash-notice">${requestScope.notice}</div>
        </c:if>
        <section class="content" ng-controller="ShowBdCtrl">          
            <c:if test="${empty requestScope.message}">
                <div class="bd-actions">
                    <a class="bd-update-btn btn" href="<c:url value="/update/${requestScope.bd.id}" />">
                        <c:url value="/img/edit.png" var="image" />
                        <img class="bd-image-btn" src="${image}" alt="">
                        Modifier</a>
                    <a class="bd-delete-btn btn" ng-click="areYouSure($event);" href="<c:url value="/delete/${requestScope.bd.id}" />">
                        <c:url value="/img/delete.png" var="image" />
                        <img class="bd-image-btn" src="${image}" alt="">Supprimer</a>
                </div>
                <section class="page-content">
                    <h2 class="bd-title">
                        <c:out value="${requestScope.bd.titre}" />
                    </h2>
                    <section class="bd-main-content">
                        <div class="left bd-image-block">
                            <c:url value="${requestScope.bd.image}" var="image" />
                            <img class="bd-image" src="${image}" alt="${requestScope.bd.titre}">
                            <div class="export-buttons">
                                <c:set var="identifier"><c:out value="${requestScope.bd.id}" /></c:set>
                                <a class="bd-export-pdf btn" href="<c:url value="/export/pdf/${identifier}" />">Format PDF</a>
                                <a class="bd-export-html btn" href="<c:url value="/export/html/${identifier}" />">Format HTML</a>
                            </div>
                        </div>
                        <div class="left">
                            <h3 class="bd-normal-title no-margin">Informations générales</h3>
                            <p class="bd-isbn">
                                <span class="bd-key-name">ISBN:&nbsp;</span>
                                <c:out value="${requestScope.bd.isbn}" />
                            </p>
                            <p class="bd-planches">
                                <span class="bd-key-name">Planches:&nbsp;</span>
                                <c:out value="${requestScope.bd.planches}" />
                            </p>
                            <p class="bd-langue">
                                <span class="bd-key-name">Langue:&nbsp;</span>
                                <c:out value="${requestScope.bd.langue}" />
                            </p>
                            <p class="bd-editor">
                                <span class="bd-key-name">Editeur:&nbsp;</span>
                                <c:out value="${requestScope.bd.editeur}" />
                            </p>
                            <p class="bd-format">
                                <span class="bd-key-name">Format:&nbsp;</span>
                                <c:out value="${requestScope.bd.format}" />
                            </p>
                            <c:if test="${!empty requestScope.bd.finImpression}">
                                <p class="bd-print-end">
                                    <span class="bd-key-name">Fin d'impression:&nbsp;</span>
                                    <c:out value="${requestScope.bd.finImpression}" />
                                </p>
                            </c:if>
                            <p class="bd-serie">
                                <span class="bd-key-name">Série:&nbsp;</span>
                                <c:out value="${requestScope.bd.serie}" />
                            </p>
                            <c:if test="${!empty requestScope.bd.tome}">
                                <p class="bd-tome">
                                    <span class="bd-key-name">Tome:&nbsp;</span>
                                    <c:out value="${requestScope.bd.tome.numero}" />
                                    &nbsp;
                                    <c:out value="${requestScope.bd.tome.informations}" />
                                </p>
                            </c:if>
                            <c:if test="${!empty requestScope.bd.depotLegal}">
                                <p class="bd-depot-legal">
                                    <span class="bd-key-name">Dépôt légal:&nbsp;</span>
                                    <c:out value="${requestScope.bd.depotLegal}" />
                                </p>
                            </c:if>
                            <p class="bd-parution">
                                <span class="bd-key-name">Date de parution:&nbsp;</span>
                                <c:out value="${requestScope.bd.parution}" />
                            </p>
                        </div>
                        <div class="left">
                            <h3 class="bd-normal-title no-margin">Informations complémentaires</h3>
                            <c:if test="${!empty requestScope.bd.scenaristes}">
                                <div class="bd-individus bd-scenaristes">
                                    <p><span class="bd-key-name">Scénaristes:&nbsp;</span></p>
                                    <table class="individus">
                                        <thead>
                                            <tr>
                                                <th>Id</th>
                                                <th>Nom</th>
                                                <th>Prénom</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="individu" items="${requestScope.bd.scenaristes.scenariste}" varStatus="status">
                                                <tr>
                                                    <td><c:out value="${status.index + 1}" /></td>
                                                    <td><c:out value="${individu.nom}" /></td>
                                                    <td><c:out value="${individu.prenom}"/></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>    
                            </c:if>
                            <c:if test="${!empty requestScope.bd.dessinateurs}">
                                <div class="bd-individus bd-dessinateurs">
                                    <p><span class="bd-key-name">Dessinateurs&nbsp;</span></p>
                                    <table class="individus">
                                        <thead>
                                            <tr>
                                                <th>Id</th>
                                                <th>Nom</th>
                                                <th>Prénom</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="individu" items="${requestScope.bd.dessinateurs.dessinateur}" varStatus="status">
                                                <tr>
                                                    <td><c:out value="${status.index + 1}" /></td>
                                                    <td><c:out value="${individu.nom}" /></td>
                                                    <td><c:out value="${individu.prenom}"/></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>    
                            </c:if>
                            <c:if test="${!empty requestScope.bd.coloristes}">
                                <div class="bd-individus bd-coloristes">
                                    <p><span class="bd-key-name">Coloristes:&nbsp;</span></p>
                                    <table class="individus">
                                        <thead>
                                            <tr>
                                                <th>Id</th>
                                                <th>Nom</th>
                                                <th>Prénom</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="individu" items="${requestScope.bd.coloristes.coloriste}" varStatus="status">
                                                <tr>
                                                    <td><c:out value="${status.index + 1}" /></td>
                                                    <td><c:out value="${individu.nom}" /></td>
                                                    <td><c:out value="${individu.prenom}"/></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>    
                            </c:if>
                            <c:if test="${!empty requestScope.bd.lettrages}">
                                <div class="bd-individus bd-lettrages">
                                    <p><span class="bd-key-name">Lettreurs&nbsp;</span></p>
                                     <table class="individus">
                                        <thead>
                                            <tr>
                                                <th>Id</th>
                                                <th>Nom</th>
                                                <th>Prénom</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="individu" items="${requestScope.bd.lettrages.lettrage}" varStatus="status">
                                                <tr>
                                                    <td><c:out value="${status.index + 1}" /></td>
                                                    <td><c:out value="${individu.nom}" /></td>
                                                    <td><c:out value="${individu.prenom}"/></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>    
                            </c:if>
                            <c:if test="${!empty requestScope.bd.encrages}">
                                <div class="bd-individus bd-encrages">
                                    <p><span class="bd-key-name">Encreurs:&nbsp;</span></p>
                                    <table class="individus">
                                        <thead>
                                            <tr>
                                                <th>Id</th>
                                                <th>Nom</th>
                                                <th>Prénom</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="individu" items="${requestScope.bd.encrages.encrage}" varStatus="status">
                                                <tr>
                                                    <td><c:out value="${status.index + 1}" /></td>
                                                    <td><c:out value="${individu.nom}" /></td>
                                                    <td><c:out value="${individu.prenom}"/></td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>    
                            </c:if>
                        </div>
                        <div class="clear">
                    </section>
                    <section class="bd-resume-content">        
                        <h3 class="bd-normal-title">Résumé</h3>
                        <p class="bd-resume">
                            <c:out value="${requestScope.bd.resume}" />
                        </p>
                    </section>
                </section>
            </c:if>
            <c:if test="${!empty requestScope.message}">
                <p><c:out value="${requestScope.message}"/></p>
            </c:if>
        </section>

        <c:import url="/WEB-INF/jsp/footer.jsp"/>
    </body>
</html>
