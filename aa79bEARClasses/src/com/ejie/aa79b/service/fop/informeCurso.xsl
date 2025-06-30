<?xml version="1.0" encoding="ISO-8859-1"?>

<xsl:stylesheet
		version="1.0" 
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
		xmlns:fo="http://www.w3.org/1999/XSL/Format"
		xmlns:java="http://xml.apache.org/xslt/java"
		exclude-result-prefixes="java">

	<xsl:output 
			method="xml"
			version="1.0"
			encoding="ISO-8859-1"
			indent="yes"/>

	<xsl:template match="informe">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="cuerpo"
					page-height="25cm" page-width="29cm" margin-top="1cm"
					margin-bottom="1cm" margin-left="1cm" margin-right="1.5cm">
					<fo:region-body margin-top="6cm" margin-bottom="1.5cm" />
					<!-- Tam Before & After -->
					<fo:region-before extent="6cm" />
					<fo:region-after extent="1.5cm" />
				</fo:simple-page-master>
			</fo:layout-master-set>
			<xsl:apply-templates />
		</fo:root>
	</xsl:template>

	<xsl:template match="contenido">
		<fo:page-sequence master-reference="cuerpo" initial-page-number="1">
			<!-- PARTE ESTATICA -->
			<fo:static-content flow-name="xsl-region-before">
				<fo:table table-layout="fixed">
					<fo:table-column column-width="40mm" />
					<fo:table-column column-width="195mm" />
					<fo:table-column column-width="40mm" />
					<fo:table-body>
						<fo:table-row>
							<!-- IMAGEN IVAP -->
							<fo:table-cell>
								<fo:block>
									<xsl:variable name="imagen" select="//imagen"></xsl:variable>
									<fo:external-graphic src="{$imagen}" />
								</fo:block>
							</fo:table-cell>
							<!-- TITULO -->
							<fo:table-cell text-align="center">
								<fo:block	font-family="Verdana" font-size="13pt"
											font-weight="bold"
											font-style="italic" text-align="center" padding-top="3pt">
									<xsl:value-of select="//tituloEu" />
									<fo:block />
									<xsl:value-of select="//tituloEs" />
								</fo:block>
							</fo:table-cell>

							<!-- FECHA & PAGINA -->
							<fo:table-cell text-align="right">
								<fo:block font-family="Verdana"
										font-size="10pt" 
										font-weight="bold"
										space-after.optimum="5pt">
									Orria
									<fo:inline font-weight="normal"> / Hoja</fo:inline>
									<fo:inline font-weight="normal">&#58;</fo:inline>
									<fo:inline font-weight="normal">&#160;</fo:inline>
									<fo:inline font-weight="normal">
										<fo:page-number/> / <fo:page-number-citation ref-id="last-page"/>
									</fo:inline>
								</fo:block>

								<fo:block 
										font-family="Verdana"
										font-size="10pt" font-weight="bold">
									Data
									<fo:inline font-weight="normal"> / Fecha</fo:inline>
									<fo:inline font-weight="normal">&#58;</fo:inline>
									<fo:inline font-weight="normal">&#160;</fo:inline>
									<fo:inline font-weight="normal">
										<xsl:value-of select="//fecha"/>
									</fo:inline>
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
					</fo:table-body>
				</fo:table>

				<fo:block font-size="9pt">&#160;</fo:block>
	
				<fo:table table-layout="fixed" width="100%" border-width="0.5pt">
					<fo:table-column><xsl:attribute name="column-width">proportional-column-width(50)</xsl:attribute></fo:table-column>
					<fo:table-column><xsl:attribute name="column-width">proportional-column-width(45)</xsl:attribute></fo:table-column>
					<fo:table-column><xsl:attribute name="column-width">proportional-column-width(30)</xsl:attribute></fo:table-column>
					<fo:table-column><xsl:attribute name="column-width">proportional-column-width(30)</xsl:attribute></fo:table-column>
					<fo:table-column><xsl:attribute name="column-width">proportional-column-width(28)</xsl:attribute></fo:table-column>
					<fo:table-column><xsl:attribute name="column-width">proportional-column-width(20)</xsl:attribute></fo:table-column>
					<fo:table-body>
	
						<fo:table-row>
							<fo:table-cell number-columns-spanned="6"
								font-size="5pt">
								<fo:block font-weight="bold">&#160;</fo:block>
							</fo:table-cell>
						</fo:table-row>
						
						<!-- ********  -->
						<!-- CABECERA  -->
						<!-- ********  -->
						<fo:table-row background-color="rgb(217,217,217)">
							
							<!-- CARPETA -->
							<fo:table-cell	border-style="solid"
											padding-left="2pt" padding-right="2pt" padding-top="2pt">
								<fo:block font-weight="bold" font-size="9pt" text-align="center">
									<xsl:value-of select="//carpetaText" />
								</fo:block>
							</fo:table-cell>
							
							<!-- TIPO CURSO -->
							<fo:table-cell	border-style="solid"
											padding-left="2pt" padding-right="2pt" padding-top="2pt">
								<fo:block font-weight="bold" font-size="9pt" text-align="center">
									<xsl:value-of select="//tipoCursoText" />
								</fo:block>
							</fo:table-cell>
							
							<!-- COMIENZO - FIN -->
							<fo:table-cell	border-style="solid"
											padding-left="2pt" padding-right="2pt" padding-top="2pt">
								<fo:block font-weight="bold" font-size="9pt" text-align="center">
									<xsl:value-of select="//fechasText" />
								</fo:block>
							</fo:table-cell>
							
							<!-- HORARIO -->
							<fo:table-cell	border-style="solid"
											padding-left="2pt" padding-right="2pt" padding-top="2pt">
								<fo:block font-weight="bold" font-size="9pt" text-align="center">
									<xsl:value-of select="//horarioText" />
								</fo:block>
							</fo:table-cell>
							
							<!-- GRUPO TITULACION -->
							<fo:table-cell	border-style="solid"
											padding-left="2pt" padding-right="2pt" padding-top="2pt">
								<fo:block font-weight="bold" font-size="9pt" text-align="center">
									<xsl:value-of select="//grupoTitulacionText" />
								</fo:block>
							</fo:table-cell>
							
							<!-- HORAS -->
							<fo:table-cell	border-style="solid"
											padding-left="2pt" padding-right="2pt" padding-top="2pt">
								<fo:block font-weight="bold" font-size="9pt" text-align="center">
									<xsl:value-of select="//horasText" />
								</fo:block>
							</fo:table-cell>
							
						</fo:table-row>
						
						<!-- *******  -->
						<!-- VALORES  -->
						<!-- *******  -->
						<fo:table-row>
							<!-- CARPETA -->
							<fo:table-cell	border-style="solid"
											padding-left="2pt" padding-right="2pt" padding-top="2pt">
								<fo:block font-size="9pt" text-align="left">
									<xsl:value-of select="//carpetaValue" />
								</fo:block>
							</fo:table-cell>
							
							<!-- TIPO CURSO -->
							<fo:table-cell	border-style="solid"
											padding-left="2pt" padding-right="2pt" padding-top="2pt">
								<fo:block font-size="9pt" text-align="left">
									<xsl:value-of select="//tipoCursoValue" />
								</fo:block>
							</fo:table-cell>
							
							<!-- COMIENZO - FIN -->
							<fo:table-cell	border-style="solid"
											padding-left="2pt" padding-right="2pt" padding-top="2pt">
								<fo:block font-size="9pt" text-align="center">
									<xsl:value-of select="//fechasValue" />
								</fo:block>
							</fo:table-cell>
							
							<!-- HORARIO -->
							<fo:table-cell	border-style="solid"
											padding-left="2pt" padding-right="2pt" padding-top="2pt">
								<fo:block font-size="9pt" text-align="center">
									<xsl:value-of select="//horarioMValue" />
								</fo:block>
								<fo:block font-size="9pt" text-align="center">
									<xsl:value-of select="//horarioTValue" />
								</fo:block>
							</fo:table-cell>
							
							<!-- GRUPO TITULACION -->
							<fo:table-cell	border-style="solid"
											padding-left="2pt" padding-right="2pt" padding-top="2pt">
								<fo:block font-size="9pt" text-align="center">
									<xsl:value-of select="//grupoTitulacionValue" />
								</fo:block>
							</fo:table-cell>
							
							<!-- HORAS -->
							<fo:table-cell	border-style="solid"
											padding-left="2pt" padding-right="2pt" padding-top="2pt">
								<fo:block font-size="9pt" text-align="center">
									<xsl:value-of select="//horasValue" />
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						
						<fo:table-row>
							<fo:table-cell number-columns-spanned="4">
								<fo:block font-size="12pt" text-align="left">
									&#160;
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
						
						<!-- ***** -->
						<!-- CURSO -->
						<!-- ***** -->
						<fo:table-row>
							<fo:table-cell	number-columns-spanned="6" border-style="solid" 
											padding-left="2pt" padding-right="2pt" padding-top="2pt">
								<fo:block font-weight="bold" font-size="10pt" text-align="left">
									<xsl:value-of select="//cursoText" />
									:&#160;
									<xsl:value-of select="//cursoValue" />
								</fo:block>
							</fo:table-cell>
						</fo:table-row>
	
					</fo:table-body>
	
				</fo:table>
				
				<fo:block font-size="12pt">&#160;</fo:block>
					
			</fo:static-content>

			<!-- BODY -->
			<!-- CUERPO -->
			<fo:flow flow-name="xsl-region-body">
				<xsl:apply-templates/>
				<fo:block id="last-page"/>
			</fo:flow>
		</fo:page-sequence>

	</xsl:template>
	
	
	<xsl:template match="resumenCurso">
		<!-- ******* -->
		<!-- RESUMEN -->
		<!-- ******* -->
		<fo:table table-layout="fixed" width="100%" border-width="0.5pt" >
			
			<fo:table-column column-width="proportional-column-width(50)"/>
			<fo:table-column column-width="proportional-column-width(50)"/>
	
			<fo:table-body>
				
				<fo:table-row font-weight="normal" background-color="rgb(217,217,217)">
					<fo:table-cell	border-style="solid"
									padding-left="2pt" padding-right="2pt" padding-top="2pt"
									number-columns-spanned="2">
						<fo:block font-size="9pt" text-align="left">
							<xsl:value-of select="titulo" />
						</fo:block>
					</fo:table-cell>
				</fo:table-row>	
					<xsl:for-each select="./filaCriterios">
						<fo:table-row font-weight="normal">
							<fo:table-cell column-number="1" border-style="solid" padding-left="2pt" padding-right="2pt" padding-top="2pt">
								<fo:block font-size="9pt">
									<xsl:value-of select="./key"/>
								</fo:block>
							</fo:table-cell>
				  			<fo:table-cell column-number="2" border-style="solid" padding-left="2pt" padding-right="2pt" padding-top="2pt">
				  				<fo:block font-size="9pt">
				  					<xsl:value-of select="./value"/>
				  				</fo:block>
				  			</fo:table-cell>
				  		</fo:table-row>	
					</xsl:for-each>
			</fo:table-body>
		</fo:table>
		<fo:block font-size="12pt">&#160;</fo:block>	
	</xsl:template>
	
	<xsl:template match="listadoSolicitudes">
		<!-- TABLA DE RESULTADOS -->				
		<fo:table table-layout="fixed"
				width="100%"
				border-style="none"
				font-family="Verdana"
				border-width="0.5pt">				
			<xsl:for-each select="./cabecera">
					<fo:table-column>
						<xsl:attribute name="column-width">proportional-column-width(<xsl:value-of select="./ancho"/>)</xsl:attribute>
					</fo:table-column>
			</xsl:for-each>

			<fo:table-header space-after.optimum="0pt">
				<fo:table-row height="5pt" background-color="rgb(217,217,217)">		
						<xsl:for-each select="./cabecera">
							<fo:table-cell 
									text-align="center" display-align="center"
									border-color="black" border-style="solid" padding-top="4pt">
								<fo:block 
										font-size="9pt" font-weight="bold"  wrap-option="wrap" hyphenate="true">
												<xsl:value-of select="./nombreEu"/>
								</fo:block>
								<fo:block 
										font-size="9pt" font-weight="bold"  wrap-option="wrap" hyphenate="true">
												<xsl:value-of select="./nombreEs"/>
								</fo:block>
							</fo:table-cell>
					   </xsl:for-each>				
				</fo:table-row>
			</fo:table-header>
			
			<fo:table-body start-indent="0" end-indent="0">
			<xsl:for-each select="./filas">
				<fo:table-row height="5pt" >
						<xsl:for-each select="./fila">
							<fo:table-cell text-align="center" display-align="center"
								border-color="black" border-style="solid" padding-top="2pt">
								 <fo:block 
										font-size="9pt" wrap-option="wrap" hyphenate="true" language="es">
									<xsl:value-of select="./nombre"/>
								</fo:block>
							</fo:table-cell>
						</xsl:for-each>
			
				</fo:table-row>
		</xsl:for-each>
		</fo:table-body>
		</fo:table>
		
		<fo:block font-size="24pt">&#160;</fo:block>
		
	</xsl:template>
	
	<xsl:template match="ruptura">
		<fo:block break-after="page" page-break-after="auto"> 
			<fo:leader leader-pattern="space" /> 
		</fo:block>	
	</xsl:template>

</xsl:stylesheet>