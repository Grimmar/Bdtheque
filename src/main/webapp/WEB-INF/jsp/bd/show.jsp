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
                <h1>Consultation</h1>
            </header>
            <c:if test="${empty requestScope.message}">
                <section class="bd-show">
                    <h2 class="bd-title">
                        <c:out value="${requestScope.bd.titre}" />
                    </h2>
                    <section class="bd-main-content">
                        <div class="left">
                            <c:url value="${requestScope.bd.image}" var="image" />
                            <img class="bd-image" src="${image}" alt="${requestScope.bd.titre}">
                            <div>
                                <c:set var="identifier"><c:out value="${requestScope.bd.id}" /></c:set>
                                <a class="bd-export-pdf btn" href="<c:url value="/export/pdf/${identifier}" />">Format PDF</a>
                                <a class="bd-export-html btn" href="<c:url value="/export/html/${identifier}" />">Format HTML</a>
                            </div>
                        </div>
                        <div class="left">
                            <p class="bd-isbn">
                                <span class="bd-key-name">ISBN:&nbsp;</span>
                                <c:out value="${requestScope.bd.isbn}" />
                            </p>
                            <p class="bd-planches">
                                <span class="bd-key-name">Planches:&nbsp;</span>
                                <c:out value="${requestScope.bd.planches}" />
                            </p>
                            <p class="bd-langue">
                                <span class="bd-key-name">Langue&nbsp;</span>
                                <c:out value="${requestScope.bd.langue}" />
                            </p>
                            <p class="bd-editor">
                                <span class="bd-key-name">Editeur:&nbsp;</span>
                                <c:out value="${requestScope.bd.editeur}" />
                            </p>
                            <p class="bd-resume">
                                <span class="bd-key-name">Résumé:&nbsp;</span>
                                <c:out value="${requestScope.bd.resume}" />
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
                        <div class="clear">
                    </section>
                    <c:if test="${!empty requestScope.bd.scenaristes}">
                        <div class="bd-individus bd-scenaristes">
                            <p><span class="bd-key-name">Scénaristes:&nbsp;</span></p>
                            <ul>
                                <c:forEach var="individu" items="${requestScope.bd.scenaristes}">
                                    <li>
                                        <span><c:out value="${individu.prenom}" /></span>
                                        &nbsp;
                                        <span><c:out value="${individu.nom}"/></span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>    
                    </c:if>
                    <c:if test="${!empty requestScope.bd.dessinateurs}">
                        <div class="bd-individus bd-dessinateurs">
                            <p><span class="bd-key-name">Dessinateurs&nbsp;</span></p>
                            <ul>
                                <c:forEach var="individu" items="${requestScope.bd.dessinateurs}">
                                    <li>
                                        <span><c:out value="${individu.prenom}" /></span>
                                        &nbsp;
                                        <span><c:out value="${individu.nom}"/></span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>    
                    </c:if>
                    <c:if test="${!empty requestScope.bd.coloristes}">
                        <div class="bd-individus bd-coloristes">
                            <p><span class="bd-key-name">Coloristes:&nbsp;</span></p>
                            <ul>
                                <c:forEach var="individu" items="${requestScope.bd.coloristes}">
                                    <li>
                                        <span><c:out value="${individu.prenom}" /></span>
                                        &nbsp;
                                        <span><c:out value="${individu.nom}"/></span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>    
                    </c:if>
                    <c:if test="${!empty requestScope.bd.lettrages}">
                        <div class="bd-individus bd-lettrages">
                            <p><span class="bd-key-name">Lettreurs&nbsp;</span></p>
                            <ul>
                                <c:forEach var="individu" items="${requestScope.bd.lettrages}">
                                    <li>
                                        <span><c:out value="${individu.prenom}" /></span>
                                        &nbsp;
                                        <span><c:out value="${individu.nom}"/></span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>    
                    </c:if>
                    <c:if test="${!empty requestScope.bd.encrages}">
                        <div class="bd-individus bd-encrages">
                            <p><span class="bd-key-name">Encreurs:&nbsp;</span></p>
                            <ul>
                                <c:forEach var="individu" items="${requestScope.bd.encrages}">
                                    <li>
                                        <span><c:out value="${individu.prenom}" /></span>
                                        &nbsp;
                                        <span><c:out value="${individu.nom}"/></span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>    
                    </c:if>        
                </section>
                <footer>
                    <p class="right">Cette bd a été ajoutée le ${requestScope.bd.insertedDate}</p>
                </footer>
            </c:if>
            <c:if test="${!empty requestScope.message}">
                <p><c:out value="${requestScope.message}"/></p>
            </c:if>
        </section>

        <c:import url="/WEB-INF/jsp/footer.jsp"/>
    </body>
</html>
