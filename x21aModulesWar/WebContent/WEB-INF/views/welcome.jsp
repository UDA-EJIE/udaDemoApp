<%--  
 -- Copyright 2011 E.J.I.E., S.A.
 --
 -- Licencia con arreglo a la EUPL, Versión 1.1 exclusivamente (la «Licencia»);
 -- Solo podrá usarse esta obra si se respeta la Licencia.
 -- Puede obtenerse una copia de la Licencia en
 --
 --      http://ec.europa.eu/idabc/eupl.html
 --
 -- Salvo cuando lo exija la legislación aplicable o se acuerde por escrito, 
 -- el programa distribuido con arreglo a la Licencia se distribuye «TAL CUAL»,
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
<%@include file="/WEB-INF/includeTemplate.inc"%>
<div class="container" style="margin-top: 2em;">

	<div class="row">
		<div class="col-xs-12">
			<h1>
				<img alt="El contenido estático no está correctamente desplegado."
					src="${staticsUrl}/rup/css/images/uda_logo.png" /> v3.0.0
				<small>(Portal demostrativo)</small>
			</h1>
			<hr>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<section id="mainContent">
			
				<p>
					<a target="_blank"
						href="${staticsUrl}/rup/css/images/uda-mini-micro2.png"><img
						style="max-width: 100%;"
						src="${staticsUrl}/rup/css/images/uda-mini-micro2.png"></a>
					es el conjunto de <a
						href="https://github.com/UDA-EJIE/uda-ejie.github.io/wiki/Componentes">utilidades,
						herramientas, librerías, plugins, guías, y recomendaciones
						funcionales y técnicas</a> que permiten acelerar el proceso de
					desarrollo de sistemas software con tecnología Java.<br> <br>
					El espíritu del proyecto, y por tanto su principal objetivo, es
					aumentar la productividad del desarrollador, sin coartar por ello
					su imaginación ni su libertad para crear software. <a
						target="_blank"
						href="${staticsUrl}/rup/css/images/uda-mini-micro2.png"><img
						style="max-width: 100%;"
						src="${staticsUrl}/rup/css/images/uda-mini-micro2.png"></a>
					ayuda y colabora en las tareas repetitivas y de poco (o nulo) valor
					añadido, pero que sin embargo, son imprescindibles. Pero también
					persigue otros fines:<br> <br>
				</p>
				<ul>
					<li>Minimizar la curva de aprendizaje. No pretende ser un
						framework más, <a target="_blank"
						href="${staticsUrl}/rup/css/images/uda-mini-micro2.png"><img
							style="max-width: 100%;"
							src="${staticsUrl}/rup/css/images/uda-mini-micro2.png"></a>
						adopta y explota los ya consolidados y de uso extendido y se
						aprovecha del conocimiento compartido por sus creadores y
						usuarios.
					</li>
					<li>Proponer patrones de usabilidad web, que aprovechen las
						mejoras proporcionadas por las tecnologías <a target="_blank"
						href="https://es.wikipedia.org/wiki/Rich_Internet_Application">RIA</a>
						- <a target="_blank" href="https://es.wikipedia.org/wiki/AJAX">Ajax</a>.
						Se cubren los escenarios de uso más habituales para las
						aplicaciones web con dicha tecnología.
					</li>
					<li>Generar interfaces de usuario. Crea automáticamente las <a
						href="https://github.com/UDA-EJIE/uda-ejie.github.io/wiki/Patrones#14._Mantenimiento_con_formulario">interfaces
							de mantenimiento de datos</a> (acciones <a target="_blank"
						href="https://es.wikipedia.org/wiki/CRUD">CRUD</a>), y otras de
						uso común (combos enlazados, fechas-horas, feedback, etc.)
					</li>
					<li>Generar código. Crea el código imprescindible en toda
						aplicación con arquitectura en capas y bajo el paradigma <a
						target="_blank"
						href="https://es.wikipedia.org/wiki/Modelo%E2%80%93vista%E2%80%93controlador">MVC</a>
						(Modelo-Vista-Controlador).
					</li>
					<li>Ser flexible y configurable. Permite adaptar el código
						generado por los asistentes a las necesidades de cada organización
						(libro de estilo, estándares de codificación, etc.)</li>
					<li>Ser adaptable y ampliable. Su arquitectura modular y
						desacoplada permitirá ampliar y/o sustituir las tecnologías
						utilizadas en cada una de las capas de responsabilidad que la
						componen.</li>
					<li>No imponer un modelo de uso único. En algún caso ya se
						proponen tecnologías alternativas para la misma capa de la
						arquitectura.</li>
					<li>Crear aplicaciones accesibles. Hasta donde sea posible, se
						cumplen con las recomendaciones <a target="_blank"
						href="http://www.w3.org/TR/WCAG20/">WCAG2.0</a> y <a
						target="_blank" href="http://www.w3.org/TR/wai-aria/">WAI-ARIA</a>
					</li>
				</ul>
				</p>
			</section>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<section id="newFeaturesContent">
				<h2>Novedades</h2>

				<b><a
					href="https://github.com/UDA-EJIE/uda-ejie.github.io/wiki/Actualizar">9-Agosto-2016</a>:</b>
				<br> Se actualizan las siguientes <a target="_blank"
					href="https://drive.google.com/folderview?id=0B2jWuJHnBpz_VFVLU2ZoREQ2Q1E&amp;usp=sharing#list">descargas</a>:<br>
				<ul>
					<li>Plantillas de generación de código: <a
						href="https://docs.google.com/uc?authuser=0&amp;id=0B2jWuJHnBpz_b3dWblVzVTJOT0E&amp;export=download">Plantillas
							(v2.4.8)</a></li>
					<li>Componentes visuales: <a
						href="https://docs.google.com/uc?authuser=0&amp;id=0B2jWuJHnBpz_OWZrTnZUMnIySGc&amp;export=download">Componentes
							RUP (v2.4.8)</a></li>
					<li>Asistente de generación de código: <a
						href="https://docs.google.com/uc?authuser=0&amp;id=0B2jWuJHnBpz_YkEyZUlEb1dvRnM&amp;export=download">Plugin
							UDA (v2.4.8)</a></li>
					<li>Eclipse IDE configurado con nuevas plantillas y nuevo
						plugin: <a
						href="https://docs.google.com/uc?authuser=0&amp;id=0B2jWuJHnBpz_eng5RmV1YTk3Z1E&amp;export=download">Eclipse
							Helios UDA (v2.4.8)</a>
					</li>
					<li>Librería de utilidades comunes: <a
						href="https://docs.google.com/uc?authuser=0&amp;id=0B2jWuJHnBpz_dUxGLXhXWTdhUlE&amp;export=download">x38ShLibClasses-2.4.7-RELEASE.jar</a></li>
					<li>Actualización con las nuevas versiones de la librería del
						<a
						href="https://docs.google.com/uc?authuser=0&amp;id=0B2jWuJHnBpz_dzVPQWp1YWl1ZDQ&amp;export=download">Repositorio
							Maven</a>
					</li>
					<li><a
						href="https://docs.google.com/uc?authuser=0&amp;id=0B2jWuJHnBpz_VGowMkNWa1NyeEU&amp;export=download">Documentación
							actualizada</a></li>
				</ul>

				En esta versión se han realizado las siguientes mejoras:<br>
				<ul>
					<li><a target="_blank"
						href="https://github.com/UDA-EJIE/udaRUP/issues?q=milestone%3Av2.4.8+label%3Abug">Correción
							de bugs detectados en la versión anterior de los componentes</a></li>
					<li><a target="_blank"
						href="https://github.com/UDA-EJIE/udaPlugin/issues?q=milestone%3Av2.4.8">Plugin
							UDA: </a>Adecuación de la cadena de conexión a base de datos usando
						Service Name</li>
				</ul>

				En la sección UDA v2.4.8 (09-Agosto-2016) del archivo <a
					href="https://docs.google.com/uc?authuser=0&amp;id=0B2jWuJHnBpz_TFMxZFVrMFJDQVk&amp;export=download">ChangeLog.txt</a>
				podrás encontrar la lista de cambios realizados en esta versión.<br>
				<br> La <a
					href="https://docs.google.com/uc?authuser=0&amp;id=0B2jWuJHnBpz_TnhuN2k1bElmTFU&amp;export=download">máquina
					virtual</a> configurada con el entorno completo sobre la versión 2.4.4
				sigue estando disponible en caso de necesitar una maqueta de entorno
				completo.<br> <br> <i>Puedes encontrar la información
					sobre los productos liberados anteriormente en el apartado <a
					target="_blank"
					href="https://github.com/UDA-EJIE/uda-ejie.github.io/wiki/Versiones">Versiones</a>
				</i> <br> <br>
			</section>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<section>
				<h2>verlo en funcionamiento</h2>
				<ul>
					<li>los <a
						href="https://github.com/UDA-EJIE/uda-ejie.github.io/wiki/Videos">videos</a></li>
					<li>la <a target="_blank"
						href="http://www.ejie.eus/x21aPilotoPatronesWar/">aplicación
							de demostración</a></li>
				</ul>
			</section>
		</div>
		<div class="col-md-4">
			<section>
				<h2>profundizar</h2>
				<ul>
					<li>las <a
						href="https://github.com/UDA-EJIE/uda-ejie.github.io/wiki/Arquitectura">guías,
							y recomendaciones funcionales y técnicas</a></li>
					<li>las <a
						href="https://github.com/UDA-EJIE/uda-ejie.github.io/wiki/Componentes">utilidades,
							herramientas, librerías, plugins</a></li>
					<li>los <a
						href="https://github.com/UDA-EJIE/uda-ejie.github.io/wiki/Patrones">
							patrones de usabilidad web</a><a></a></li>
					<a> </a>
					<li><a>la </a><a
						href="https://github.com/UDA-EJIE/uda-ejie.github.io/wiki/Documentacion">documentación</a></li>
				</ul>
			</section>
		</div>
		<div class="col-md-4">
			<section>
				<h2>pasar a la acción</h2>
				<ul>
					<li>partiendo de cero, consulta cómo <a
						href="https://github.com/UDA-EJIE/uda-ejie.github.io/wiki/Instalar">Instalar</a></li>
					<li>si ya has instalado alguna versión anteriormente, puedes
						ver cómo <a
						href="https://github.com/UDA-EJIE/uda-ejie.github.io/wiki/Actualizar">Actualizar</a>
						desde tu <a
						href="https://github.com/UDA-EJIE/uda-ejie.github.io/wiki/Versiones">versión</a>
					</li>
				</ul>
			</section>
		</div>
	</div>
</div>