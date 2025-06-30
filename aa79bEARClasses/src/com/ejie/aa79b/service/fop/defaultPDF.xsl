<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
    xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">
    <xsl:output method="xml" version="1.0" encoding="ISO-8859-1"
        indent="yes" />
    <!-- PLANTILLA DE LA PAGINA -->
    <xsl:template match="informe">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="cuerpo"
                    page-height="21cm" page-width="29cm" margin-top="1cm"
                    margin-bottom="1cm" margin-left="1cm" margin-right="1cm">
                    <fo:region-body margin-top="3cm" margin-bottom="3cm" />
                    <fo:region-before extent="5cm" />
                    <fo:region-after extent="3cm" />
                </fo:simple-page-master>
            </fo:layout-master-set>
            <xsl:apply-templates />
        </fo:root>
    </xsl:template>
    <!-- PLANTILLA DEL CONTENIDO -->
    <xsl:template match="contenido">
        <fo:page-sequence master-reference="cuerpo"
            initial-page-number="0">
            <!-- PARTE ESTATICA: CABECERA PAGINA -->
            <fo:static-content flow-name="xsl-region-before">
                <fo:table table-layout="fixed">
                    <fo:table-column column-width="40mm" />
                    <fo:table-column column-width="180mm" />
                    <fo:table-column column-width="50mm" />
                    <fo:table-body>
                        <fo:table-row>
                            <!-- IMAGEN IVAP -->
                            <fo:table-cell>
                                <fo:block>
                                    <xsl:variable name="imagen" select="//imagen" />
                                    <fo:external-graphic src="{$imagen}" />
                                </fo:block>
                            </fo:table-cell>
                            <!-- TITULO -->
                            <fo:table-cell text-align="center">
                                <fo:block font-family="Verdana" font-size="10pt"
                                    line-height="20pt" font-weight="bold" text-align="center">
                                    <fo:block font-family="Verdana" font-size="20pt"
                                        line-height="20pt" font-weight="bold" text-align="center">
                                        <xsl:value-of select="//titulo" />
                                    </fo:block>
                                </fo:block>
                            </fo:table-cell>
                            <!-- FECHA & PAGINA -->
                            <fo:table-cell text-align="right">
                                <fo:block font-family="Verdana" font-size="10pt"
                                    font-weight="bold" space-after.optimum="5pt">
                                    <xsl:value-of select="//pagLabel" />:
                                    <fo:inline font-weight="normal">&#160;</fo:inline>
                                    <fo:inline font-weight="normal">
                                        <fo:page-number />
                                        /
                                        <fo:page-number-citation ref-id="last-page" />
                                    </fo:inline>
                                </fo:block>
                                <fo:block font-family="Verdana" font-size="10pt"
                                    font-weight="bold">
                                    <xsl:value-of select="//fechaLabel" />:
                                    <fo:inline font-weight="normal">&#160;</fo:inline>
                                    <fo:inline font-weight="normal">
                                        <xsl:value-of select="//fecha" />
                                    </fo:inline>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <!-- TITULO -->
                            <fo:table-cell text-align="center"
                                number-columns-spanned="3">
                                <fo:block font-family="Verdana" font-size="10pt"
                                    line-height="20pt" font-weight="bold" text-align="left">
                                    <xsl:value-of select="//leyendaEu" />
                                    <fo:inline font-family="Verdana" font-size="10pt"
                                        line-height="20pt" font-weight="normal" text-align="left">
                                        <xsl:value-of select="//leyendaEs" />
                                    </fo:inline>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </fo:table-body>
                </fo:table>
            </fo:static-content>
            <!-- CUERPO -->
            <fo:flow flow-name="xsl-region-body">
                <fo:block></fo:block>
                <xsl:apply-templates />
                <fo:block id="last-page" />
            </fo:flow>
        </fo:page-sequence>
    </xsl:template>
    <xsl:template match="criterios">
        <fo:table table-layout="fixed" width="100%" border-style="solid"
            border-collapse="separate" border-width="0.5pt" space-after.optimum="15pt">
            <fo:table-column />
            <fo:table-column column-width="1mm" />
            <fo:table-column />
            <fo:table-column column-width="20mm" />
            <fo:table-column />
            <fo:table-column column-width="1mm" />
            <fo:table-column />
            <fo:table-body>
                <xsl:for-each select="./filaCriterios">
                    <fo:table-row height="20pt">
                        <fo:table-cell padding-top="5pt" padding-left="5pt"
                            text-align="left">
                            <fo:block font-size="11pt" font-weight="bold"
                                font-family="Verdana" wrap-option="wrap" hyphenate="true">
                                <xsl:value-of select="./keyIzq" />
                                <fo:inline font-weight="normal">:</fo:inline>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                            <fo:block />
                        </fo:table-cell>
                        <fo:table-cell padding-top="5pt" padding-left="5pt"
                            text-align="left">
                            <fo:block font-size="11pt" font-family="Verdana"
                                wrap-option="wrap" hyphenate="true">
                                <xsl:value-of select="./valueIzq" />
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                            <fo:block />
                        </fo:table-cell>
                        <fo:table-cell padding-top="5pt" padding-left="5pt"
                            text-align="left">
                            <fo:block font-size="11pt" font-weight="bold"
                                font-family="Verdana" wrap-option="wrap" hyphenate="true">
                                <xsl:value-of select="./keyDer" />
                                <fo:inline font-weight="normal">
                                    <xsl:if test="./keyDer!=''"></xsl:if>
                                    <xsl:if test="./keyDer!=''">
                                        :
                                    </xsl:if>
                                </fo:inline>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell>
                            <fo:block />
                        </fo:table-cell>
                        <fo:table-cell padding-top="5pt" padding-left="5pt"
                            text-align="left">
                            <fo:block font-size="11pt" font-family="Verdana"
                                wrap-option="wrap" hyphenate="true">
                                <xsl:value-of select="./valueDer" />
                            </fo:block>
                        </fo:table-cell>
                    </fo:table-row>
                </xsl:for-each>
            </fo:table-body>
        </fo:table>
    </xsl:template>
    <xsl:template match="datos">
        <!-- TABLA DE RESULTADOS -->
        <fo:table table-layout="fixed" width="100%" border-color="#861130"
            border-style="solid" border-width="1px">
            <xsl:for-each select="./columnas">
                <xsl:for-each select="./columna">
                    <fo:table-column>
                        <xsl:if test="child::ancho">
                            <xsl:attribute name="column-width">proportional-column-width(<xsl:value-of
                                select="./ancho" />)</xsl:attribute>
                        </xsl:if>
                    </fo:table-column>
                </xsl:for-each>
            </xsl:for-each>
            <fo:table-header space-after.optimum="0pt">
                <fo:table-row height="10pt">
                    <xsl:for-each select="./literales">
                        <xsl:for-each select="./literal">
                            <fo:table-cell border-bottom-style="solid"
                                border-width="1pt" text-align="left" background-color="#666"
                                display-align="center" padding-left="2pt">
                                <fo:block text-align="center" font-size="10pt"
                                    font-family="Roboto" wrap-option="wrap" hyphenate="true">
                                    <fo:inline color="white">
                                        <xsl:value-of select="./texto" />
                                    </fo:inline>
                                </fo:block>
                            </fo:table-cell>
                        </xsl:for-each>
                    </xsl:for-each>
                </fo:table-row>
            </fo:table-header>
            <fo:table-body>
                <xsl:for-each select="./fila">
                    <fo:table-row height="15pt">
                        <xsl:variable name="bgclr">
                            <xsl:choose>
                                <xsl:when test="position() mod 2">#eceeef</xsl:when>
                                <xsl:otherwise>#FFFFFF</xsl:otherwise>
                            </xsl:choose>
                        </xsl:variable>
                        <xsl:for-each select="./celda">
                            
                            <fo:table-cell background-color="{$bgclr}" border="solid 0.2mm black"
                                display-align="center"
                                padding-left="2pt" padding-right="2pt">
                                <xsl:attribute name="text-align"><xsl:value-of
                                select="./align" /></xsl:attribute>
                                <fo:block font-family="Roboto" font-size="8.5pt" linefeed-treatment="preserve"
                                    wrap-option="wrap" hyphenate="true" language="es">
                                    <xsl:value-of select="./texto" />&#160;&#160;
                                </fo:block>
                            </fo:table-cell>
                        </xsl:for-each>
                    </fo:table-row>
                </xsl:for-each>
            </fo:table-body>
        </fo:table>
    </xsl:template>
    <xsl:template match="ruptura">
        <fo:block break-after="page" page-break-after="auto">
            <fo:leader leader-pattern="space" />
        </fo:block>
    </xsl:template>
</xsl:stylesheet>
