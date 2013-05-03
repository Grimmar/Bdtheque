<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.1"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:bd="http://univ-rouen.fr/bd"
                exclude-result-prefixes="fo">
    <xsl:template match="bd:bd">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="bd-page" page-height="11in" page-width="8.5in" margin-top="0.5in" margin-bottom="0.5in" margin-left="15mm" 
                                       margin-right="15mm" >
                    <fo:region-body />
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="bd-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block border-style="solid"
                              border-width="3pt"
                              font-size="16pt"
                              font-weight="bold"
                              padding-left="-1mm"
                              padding-right="-1mm"
                              text-align="center"
                              margin-bottom="5mm"
                              position="absolute">
                        <xsl:value-of select="bd:titre" />
                    </fo:block>
                    <fo:block>
                        <fo:block-container>
                            <fo:block>
                                <xsl:variable name="img2">
                                    <xsl:value-of select="bd:image"/>
                                </xsl:variable>
                                <fo:external-graphic src="url({$img2})" ></fo:external-graphic>
                            </fo:block>
                        </fo:block-container>
                        <fo:block-container position="absolute" left="175px" top="15%">
                            <fo:block>
                                <fo:inline>
                                    <fo:inline color="#1B5E55">ISBN: </fo:inline>
                                    <xsl:value-of select="@bd:isbn" />
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <fo:inline>
                                    <fo:inline color="#1B5E55">Editeur: </fo:inline>
                                    <xsl:value-of select="bd:editeur" />
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <fo:block>
                                    <fo:inline>
                                        <fo:inline color="#1B5E55">Scenaristes: </fo:inline>
                                        <xsl:for-each select="bd:scenaristes/bd:scenariste">
                                            <xsl:value-of select="bd:nom" />
                                            <xsl:text> </xsl:text>
                                            <xsl:value-of select="bd:prenom" />
                                            <xsl:if test="position() != last()">
                                                <xsl:text>, </xsl:text> 
                                            </xsl:if>
                                        </xsl:for-each>
                                    </fo:inline>
                                </fo:block>
                                <fo:block>
                                    <fo:inline>
                                        <fo:inline color="#1B5E55">Dessinateurs: </fo:inline>
                                        <xsl:for-each select="bd:dessinateurs/bd:dessinateur">
                                            <xsl:value-of select="bd:nom" />
                                            <xsl:text> </xsl:text>
                                            <xsl:value-of select="bd:prenom" />
                                            <xsl:if test="position() != last()">
                                                <xsl:text>, </xsl:text> 
                                            </xsl:if>
                                        </xsl:for-each>
                                    </fo:inline>
                                </fo:block>
                                <fo:block>
                                    <fo:inline>
                                        <fo:inline color="#1B5E55">Coloristes: </fo:inline>
                                        <xsl:for-each select="bd:coloristes/bd:coloriste">
                                            <xsl:value-of select="bd:nom" />
                                            <xsl:text> </xsl:text>
                                            <xsl:value-of select="bd:prenom" />
                                            <xsl:if test="position() != last()">
                                                <xsl:text>, </xsl:text> 
                                            </xsl:if>
                                        </xsl:for-each>
                                    </fo:inline>
                                </fo:block>
                                <fo:block>
                                    <fo:inline>
                                        <fo:inline color="#1B5E55">Lettrages: </fo:inline>
                                        <xsl:for-each select="bd:lettrages/bd:lettrage">
                                            <xsl:value-of select="bd:nom" />
                                            <xsl:text> </xsl:text>
                                            <xsl:value-of select="bd:prenom" />
                                            <xsl:if test="position() != last()">
                                                <xsl:text>, </xsl:text> 
                                            </xsl:if>
                                        </xsl:for-each>
                                    </fo:inline>
                                </fo:block>
                                <fo:block>
                                    <fo:inline>
                                        <fo:inline color="#1B5E55">Encrages: </fo:inline>
                                        <xsl:for-each select="bd:encrages/bd:encrage">
                                            <xsl:value-of select="bd:nom" />
                                            <xsl:text> </xsl:text>
                                            <xsl:value-of select="bd:prenom" />
                                            <xsl:if test="position() != last()">
                                                <xsl:text>, </xsl:text> 
                                            </xsl:if>
                                        </xsl:for-each>
                                    </fo:inline>
                                </fo:block>
                                <fo:block>
                                    <fo:inline>
                                        <fo:inline color="#1B5E55">Tome: </fo:inline>
                                        <xsl:value-of select="bd:tome/bd:numero" />
                                        <xsl:value-of select="bd:tome/bd:informations" />
                                    </fo:inline>
                                </fo:block>
                                <fo:block>
                                    <fo:inline>
                                        <fo:inline color="#1B5E55">Planche: </fo:inline>
                                        <xsl:value-of select="@bd:planches" />
                                    </fo:inline>
                                </fo:block>
                                <fo:block>
                                    <fo:inline>
                                        <fo:inline color="#1B5E55">Serie: </fo:inline>
                                        <xsl:value-of select="@bd:serie" />
                                    </fo:inline>
                                </fo:block>
                                <fo:block>
                                    <fo:inline>
                                        <fo:inline color="#1B5E55">Langue: </fo:inline>
                                        <xsl:value-of select="@bd:langue" />
                                    </fo:inline>
                                </fo:block>
                                <fo:block>
                                    <fo:inline>
                                        <fo:inline color="#1B5E55">Dépot légal: </fo:inline>
                                        <xsl:value-of select="bd:depotLegal" />
                                    </fo:inline>
                                </fo:block>
                                <fo:block>
                                    <fo:inline>
                                        <fo:inline color="#1B5E55">Fin impression: </fo:inline>
                                        <xsl:value-of select="bd:finImpression" />
                                    </fo:inline>
                                </fo:block>
                                <fo:block>
                                    <fo:inline>
                                        <fo:inline color="#1B5E55">Parution: </fo:inline>
                                        <xsl:value-of select="bd:parution" />
                                    </fo:inline>
                                </fo:block>
                                <fo:block>
                                    <fo:inline>
                                        <fo:inline color="#1B5E55">Création: </fo:inline>
                                        <xsl:value-of select="bd:creation" />
                                    </fo:inline>
                                </fo:block>
                            </fo:block>
                        </fo:block-container>
                    </fo:block>
                    <fo:block text-align="justify">
                        <fo:block color="#1B5E55">Résumé: </fo:block>
                        <xsl:value-of select="bd:resume" />
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>