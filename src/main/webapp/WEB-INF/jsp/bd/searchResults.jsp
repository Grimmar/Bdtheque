<div class="result-section">
    <h2 class="main-title">Résultats</h2>
    <div class="search-result">
        <c:forEach items="${requestScope.searchBd}" var="bd">
            <c:url value="/show/${bd.id}" var="show" />
            <a href="${show}" class="bd">
                <section >
                    <div class="miniature">
                        <c:url value="${bd.image}" var="image" />
                        <img class="bd-image" src="${image}" alt="${bd.titre}">
                    </div>
                    <div class="info">
                        <h2><c:out value="${bd.titre}" /> </h2> <br/>
                        Editeur:  <c:out value="${bd.editeur}" />
                        Série: <c:out value="${bd.serie}" /> <br/>
                        Langue:  <c:out value="${bd.langue}" />
                    </div>
                </section>
            </a>
        </c:forEach>
    </div>
</div>
<c:if test="${nbResult != 1}">
    <div class="paginator">
        <c:forEach var="i" begin="1" end="${nbResult}">
            <c:if test="${i == page}">
                <a class="paginator-number selected-page" href="<c:url value = "/search/${i}/"/>">${i}</a>
            </c:if>
            <c:if test="${i != page}">
                <a class="paginator-number" href="<c:url value = "/search/${i}/"/>">${i}</a>
            </c:if>

        </c:forEach>
    </div>
</c:if>