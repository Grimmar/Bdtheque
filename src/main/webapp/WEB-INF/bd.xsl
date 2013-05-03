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
                <!-- css -->	
            </head>
            <body>
                <div class="bd">
                    <div class="bd-image">
                        <xsl:variable name="img2">
                            <xsl:value-of select="bd:image"/>
                        </xsl:variable>
                        <img src='{$img2}' alt="Image de couverture"/>
                    </div>
                    <div class="bd-infos">
                        <div class="header">
                            <xsl:value-of select="bd:titre" />
                        </div>
                        <p>ISBN: 
                            <xsl:value-of select="@bd:isbn" />
                        </p>
                        <p>Planches: 
                            <xsl:value-of select="@bd:planches" />
                        </p>
                        <p>Langue: 
                            <xsl:value-of select="@bd:langue" />
                        </p>
                        <p>Série: 
                            <xsl:value-of select="@bd:serie" />
                        </p>
                        <p>Date de dépôt légal: 
                            <xsl:value-of select="bd:depotLegal" />
                        </p>
                        <p>Date de fin d'impression: 
                            <xsl:value-of select="bd:finImpression" />
                        </p>
                        <p>Date de parution: 
                            <xsl:value-of select="bd:parution" />
                        </p>
                        <p>Editeur: 
                            <xsl:value-of select="bd:editeur" />
                        </p>
                        <p>Format: 
                            <xsl:value-of select="bd:format" />
                        </p>
                        <xsl:apply-templates select="bd:scenaristes" />
                        <xsl:apply-templates select="bd:dessinateurs" />
                        <xsl:apply-templates select="bd:coloristes" />
                        <xsl:apply-templates select="bd:lettrages" />
                        <xsl:apply-templates select="bd:encrages" />
                        <xsl:apply-templates select="bd:tome" />
                    </div>
                    <div class="bd-resume">
                        <p>Résumé:</p>
                        <p>
                            <xsl:value-of select="bd:resume" />
                        </p>
                    </div>
                </div>
            </body>	
		
        </html>
        
    </xsl:template>
    <xsl:template match="bd:scenaristes">
        <h3>Scenaristes:</h3>
        <xsl:for-each select="bd:scenariste">
            <xsl:sort select="bd:nom" order="ascending"/>
            <p>
                <xsl:value-of select="bd:nom" /> 
                <xsl:value-of select="bd:prenom" />
            </p>
        </xsl:for-each>
    </xsl:template>
	
    <xsl:template match="bd:dessinateurs">
        <h3>Dessinateurs:</h3>
        <xsl:for-each select="bd:dessinateur">
            <xsl:sort select="bd:nom" order="ascending"/>
            <p>
                <xsl:value-of select="bd:nom" /> 
                <xsl:value-of select="bd:prenom" />
            </p>
        </xsl:for-each>
    </xsl:template>
	
    <xsl:template match="bd:coloristes">
        <h3>Coloristes:</h3>
        <xsl:for-each select="bd:coloriste">
            <xsl:sort select="bd:nom" order="ascending"/>
            <p>
                <xsl:value-of select="bd:nom" /> 
                <xsl:value-of select="bd:prenom" />
            </p>
        </xsl:for-each>
    </xsl:template>
	
    <xsl:template match="bd:lettrages">
        <h3>Lettrages:</h3>
        <xsl:for-each select="bd:lettrage">
            <xsl:sort select="bd:nom" order="ascending"/>
            <p>
                <xsl:value-of select="bd:nom" /> 
                <xsl:value-of select="bd:prenom" />
            </p>
        </xsl:for-each>
    </xsl:template>
	
    <xsl:template match="bd:encrages">
        <h3>Encrages:</h3>
        <xsl:for-each select="bd:encrage">
            <xsl:sort select="bd:nom" order="ascending"/>
            <p>
                <xsl:value-of select="bd:nom" /> 
                <xsl:value-of select="bd:prenom" />
            </p>
        </xsl:for-each>
    </xsl:template>
	
    <xsl:template match="bd:tome">
        <p>Tome: 
            <xsl:value-of select="bd:numero" /> 
            <xsl:value-of select="bd:informations" />
        </p>
    </xsl:template>

</xsl:stylesheet>
