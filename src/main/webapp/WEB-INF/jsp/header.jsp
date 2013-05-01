<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="main-header">
    <nav>
        <c:url value="/" var="homeLink" />
        <a class="home" href="${homeLink}"><h1>BDtheque</h1></a>
        <div class="menu-right">
        <c:url value="/search" var="searchLink" />
        <form class="searchForm" method="post" action="${searchLink}">
            <input type="submit" value="">
            <input type="search" name="q" placeholder="Titre ..." value=""/>
        </form>
        </div>
        <div class="clear"></div>
    </nav>
</header>
