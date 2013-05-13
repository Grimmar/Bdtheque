<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:bd="http://univ-rouen.fr/bd">
    <xsl:output method="html" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-
		transitional.dtd" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//
		EN" indent="yes"/>

    <xsl:template match="/bd:bd">
        <html>
            <head>
                <title>Export au format HTML</title>
                <style>
                    table.individus {
                    margin: auto;
                    width: 50%;
                    border-collapse: separate;
                    font-family:Arial, Helvetica, sans-serif;
                    color:#666;
                    font-size:12px;
                    text-shadow: 1px 1px 0px #fff;
                    background:#eaebec;
                    border:#ccc 1px solid;

                    -moz-border-radius:3px;
                    -webkit-border-radius:3px;
                    border-radius:3px;

                    -moz-box-shadow: 0 1px 2px #d1d1d1;
                    -webkit-box-shadow: 0 1px 2px #d1d1d1;
                    box-shadow: 0 1px 2px #d1d1d1;
                    }

                    table.individus th {
                    padding: 10px;
                    border-top:1px solid #fafafa;
                    border-bottom:1px solid #e0e0e0;

                    background: #ededed;
                    background: -webkit-gradient(linear, left top, left bottom, from(#ededed), to(#ebebeb));
                    background: -moz-linear-gradient(top,  #ededed,  #ebebeb);
                    }

                    table.individus th:first-child {
                    text-align: center;
                    }

                    table.individus tr:first-child th:first-child {
                    -moz-border-radius-topleft:3px;
                    -webkit-border-top-left-radius:3px;
                    border-top-left-radius:3px;
                    }
                    table.individus tr:first-child th:last-child {
                    -moz-border-radius-topright:3px;
                    -webkit-border-top-right-radius:3px;
                    border-top-right-radius:3px;
                    }
                    table.individus tr {
                    padding-left:20px;
                    }
                    table.individus td:first-child {
                    text-align: center;
                    border-left: 0;
                    width: 10%;
                    }

                    table.individus td:last-child {
                    text-align: center;
                    width: 10%;
                    }

                    table.individus td {
                    padding: 10px 5px;
                    border-top: 1px solid #ffffff;
                    border-bottom:1px solid #e0e0e0;
                    border-left: 1px solid #e0e0e0;

                    background: #fafafa;
                    background: -webkit-gradient(linear, left top, left bottom, from(#fbfbfb), to(#fafafa));
                    background: -moz-linear-gradient(top,  #fbfbfb,  #fafafa);
                    }
                    table.individus tr.even td {
                    background: #f6f6f6;
                    background: -webkit-gradient(linear, left top, left bottom, from(#f8f8f8), to(#f6f6f6));
                    background: -moz-linear-gradient(top,  #f8f8f8,  #f6f6f6);
                    }
                    table.individus tr:last-child td {
                    border-bottom:0;
                    }
                    table.individus tr:last-child td:first-child {
                    -moz-border-radius-bottomleft:3px;
                    -webkit-border-bottom-left-radius:3px;
                    border-bottom-left-radius:3px;
                    }
                    table.individus tr:last-child td:last-child {
                    -moz-border-radius-bottomright:3px;
                    -webkit-border-bottom-right-radius:3px;
                    border-bottom-right-radius:3px;
                    }
                    table.individus tr:hover td {
                    background: #f2f2f2;
                    background: -webkit-gradient(linear, left top, left bottom, from(#f2f2f2), to(#f0f0f0));
                    background: -moz-linear-gradient(top,  #f2f2f2,  #f0f0f0);	
                    }
                    .bd-main-content {
                    margin: 0; 
                    }

                    .bd-actions {
                    position: absolute;
                    right: 0;    
                    width: 240px;
                    top: 10px;
                    }

                    .bd-actions a {
                    text-decoration: none;
                    width: 90px;
                    }

                    .bd-export-html, .bd-export-pdf {
                    display: block;
                    text-decoration: none;
                    width: 42%;
                    float: left;
                    padding-left: 5px;
                    padding-right: 5px;
                    }

                    .bd-export-html {
                    margin-left: 10px;
                    }

                    .bd-image-btn {
                    width: 16px;
                    }

                    .bd-export-html:before {
                    content: url(../img/html_file.png);
                    vertical-align:-50%;
                    margin-left: 2px;
                    }

                    .bd-export-pdf:before {
                    content: url(../img/pdf_file.png);
                    vertical-align:-50%;
                    margin-left: 2px;
                    }

                    .left {
                    margin-left: 20px;
                    }

                    .bd-image {
                    height: auto;
                    width: 180px;
                    margin:auto;
                    display: block;
                    box-shadow: 1px 1px 12px #555;
                    }

                    .export-buttons {
                    margin-top: 10px;
                    }

                    .bd-main-content p {
                    margin: 2px 0;
                    }

                    .bd-main-content > div {
                    width: 30%;
                    }

                    h3.bd-normal-title {
                    padding: 10px 10px;
                    background-color: #ccc;
                    font-size: 18px;
                    color: #505050;
                    border-left: 4px solid #053a6c;
                    font-weight: normal;    
                    }

                    .bd-resume-content {
                    margin-left: 20px;
                    margin-right: 20px;
                    text-align: justify;
                    }

                    .bd-resume-content h3 {
                    max-width: 30%;
                    }

                    table.individus {
                    border-collapse: collapse;
                    border-top-width: 2px;
                    width: 100%;
                    margin-bottom: 20px;
                    }

                    table.individus th + th {
                    width: 40%;
                    text-align: center;
                    }

                    table.individus td + td {
                    text-align: center;
                    }

                    div > p > span {
                    font-size: 13pt;
                    font-weight: bold;
                    }

                    p > span{
                    font-family: "Skolar Regular","Times New Roman",serif;
                    }
                </style>	
            </head>
            <body>
                <section class="content" ng-controller="ShowBdCtrl">          
                    <section class="page-content">
                        <h2 class="bd-title">
                            <xsl:value-of select="bd:titre" />
                        </h2>
                        <section class="bd-main-content">
                            <div class="left bd-image-block">
                                <xsl:variable name="img2">
                                    <xsl:value-of select="bd:image"/>
                                </xsl:variable>
                                <img class="bd-image" src='{$img2}' alt="Image de couverture"/>
                            </div>
                            <div class="left">
                                <h3 class="bd-normal-title no-margin">Informations générales</h3>
                                <p class="bd-isbn">
                                    <span class="bd-key-name">ISBN:</span>
                                    <xsl:value-of select="@bd:isbn" />
                                </p>
                                <p class="bd-planches">
                                    <span class="bd-key-name">Planches:</span>
                                    <xsl:value-of select="@bd:planches" />
                                </p>
                                <p class="bd-langue">
                                    <span class="bd-key-name">Langue:</span>
                                    <xsl:value-of select="@bd:langue" />
                                </p>
                                <p class="bd-editor">
                                    <span class="bd-key-name">Editeur:</span>
                                    <xsl:value-of select="bd:editeur" />
                                </p>
                                <p class="bd-format">
                                    <span class="bd-key-name">Format:</span>
                                    <xsl:value-of select="bd:format" />
                                </p>
                                <p class="bd-print-end">
                                    <span class="bd-key-name">Fin d'impression:</span>
                                    <xsl:value-of select="bd:finImpression" />
                                </p>
                                <p class="bd-serie">
                                    <span class="bd-key-name">Série:</span>
                                    <xsl:value-of select="@bd:serie" />
                                </p>
                                <xsl:apply-templates select="bd:tome" />
                                <p class="bd-depot-legal">
                                    <span class="bd-key-name">Dépôt légal:</span>
                                    <xsl:value-of select="bd:depotLegal" />
                                </p>
                                <p class="bd-parution">
                                    <span class="bd-key-name">Date de parution:</span>
                                    <xsl:value-of select="bd:parution" />
                                </p>
                            </div>
                            <div class="left">
                                <h3 class="bd-normal-title no-margin">Informations complémentaires</h3>
                                <xsl:apply-templates select="bd:scenaristes" />
                                <xsl:apply-templates select="bd:dessinateurs" />
                                <xsl:apply-templates select="bd:coloristes" />
                                <xsl:apply-templates select="bd:lettrages" />
                                <xsl:apply-templates select="bd:encrages" /> 
                            </div>
                            <div class="clear"></div>
                        </section>
                        <section class="bd-resume-content">        
                            <h3 class="bd-normal-title">Résumé</h3>
                            <p class="bd-resume">
                                <xsl:value-of select="bd:resume" />
                            </p>
                        </section>
                    </section>
                </section>
       
            </body>	
        </html>
    </xsl:template>
    
    <xsl:template match="bd:scenaristes">
        <div class="bd-individus bd-scenaristes">
            <p>
                <span class="bd-key-name">Scénaristes</span>
            </p>
            <table class="individus">
                <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Prénom</th>
                    </tr>
                </thead>
                <tbody>
                    <xsl:for-each select="bd:scenariste">
                        <xsl:sort select="bd:nom" order="ascending"/>
                        <tr>                      
                            <td>
                                <xsl:value-of select="bd:nom" /> 
                            </td>
                            <td>
                                <xsl:value-of select="bd:prenom" /> 
                            </td>
                        </tr>
                    </xsl:for-each>
                </tbody>
            </table>
        </div>
    </xsl:template>
	
    <xsl:template match="bd:dessinateurs">
        <div class="bd-individus bd-dessinateurs">
            <p>
                <span class="bd-key-name">Dessinateurs</span>
            </p>
            <table class="individus">
                <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Prénom</th>
                    </tr>
                </thead>
                <tbody>
                    <xsl:for-each select="bd:dessinateur">
                        <xsl:sort select="bd:nom" order="ascending"/>
                        <tr>                      
                            <td>
                                <xsl:value-of select="bd:nom" /> 
                            </td>
                            <td>
                                <xsl:value-of select="bd:prenom" /> 
                            </td>
                        </tr>
                    </xsl:for-each>
                </tbody>
            </table>
        </div>
    </xsl:template>
	
    <xsl:template match="bd:coloristes">
        <div class="bd-individus bd-coloristes">
            <p>
                <span class="bd-key-name">Coloristes</span>
            </p>
            <table class="individus">
                <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Prénom</th>
                    </tr>
                </thead>
                <tbody>
                    <xsl:for-each select="bd:coloriste">
                        <xsl:sort select="bd:nom" order="ascending"/>
                        <tr>                      
                            <td>
                                <xsl:value-of select="bd:nom" /> 
                            </td>
                            <td>
                                <xsl:value-of select="bd:prenom" /> 
                            </td>
                        </tr>
                    </xsl:for-each>
                </tbody>
            </table>
        </div>
    </xsl:template>
	
    <xsl:template match="bd:lettrages">
        <div class="bd-individus bd-lettreurs">
            <p>
                <span class="bd-key-name">Lettreurs</span>
            </p>
            <table class="individus">
                <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Prénom</th>
                    </tr>
                </thead>
                <tbody>
                    <xsl:for-each select="bd:lettrage">
                        <xsl:sort select="bd:nom" order="ascending"/>
                        <tr>                      
                            <td>
                                <xsl:value-of select="bd:nom" /> 
                            </td>
                            <td>
                                <xsl:value-of select="bd:prenom" /> 
                            </td>
                        </tr>
                    </xsl:for-each>
                </tbody>
            </table>
        </div>
    </xsl:template>
	
    <xsl:template match="bd:encrages">
        <div class="bd-individus bd-encrages">
            <p>
                <span class="bd-key-name">Encreurs:</span>
            </p>
            <table class="individus">
                <thead>
                    <tr>
                        <th>Nom</th>
                        <th>Prénom</th>
                    </tr>
                </thead>
                <tbody>
                    <xsl:for-each select="bd:encrage">
                        <tr>
                                                        
                            <td>
                                <xsl:value-of select="bd:nom" /> 
                            </td>
                            <td>
                                <xsl:value-of select="bd:prenom" /> 
                            </td>
                        </tr>
                    </xsl:for-each>
                </tbody>
            </table>
        </div>    
    </xsl:template>
	
    <xsl:template match="bd:tome">
        <p class="bd-tome">
            <span class="bd-key-name">Tome:</span>
            <xsl:value-of select="bd:numero" /> 
                                    
            <xsl:value-of select="bd:informations" />
        </p>
    </xsl:template>

</xsl:stylesheet>
