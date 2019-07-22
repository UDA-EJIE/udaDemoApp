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
 -- SIN GARANTÍAS NI CONDICIONES DE NINGÚN TIPO, ni expresas ni implícitas.
 -- Véase la Licencia en el idioma concreto que rige los permisos y limitaciones
 -- que establece la Licencia.
 --%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>

<form:form id="formValidaciones2" action='${pageContext.request.contextPath}/patrones/validacion/cliente' modelAttribute="alumno" >
	
	<fieldset class="alumnoFieldset">
		<legend><spring:message code="options" /></legend>
			
			<input  type="radio" id="gender_male" value="m" name="gender" validate="required:true" />
			<label for="gender_male"><spring:message code="patron.validacion.primeraOpcion" /></label>
			<input  type="radio" id="gender_female" value="f" name="gender"/>
			<label for="gender_female"><spring:message code="patron.validacion.segundaOpcion" /></label>
			<label for="gender" class="error" style="display:none"><spring:message code="patron.validacion.seleccioneUnaOpcion" /></label>
			
			<br/>
			
		
	</fieldset>
	
	<fieldset class="alumnoFieldset">
		<legend><spring:message code="checkbox" /></legend>
		<fieldset class="alumnoFieldset">
		<legend><spring:message code="obligatorio" /></legend>
			<input type="checkbox" class="checkbox" id="agree" name="agree" validate="required:true" /><spring:message code="patron.validacion.aceptarCondiciones" />
			<label for="agree" class="error block" style="display:none"><spring:message code="patron.validacion.error.aceptarCondiciones" /></label>
		</fieldset>
		<fieldset class="alumnoFieldset">
				<legend><spring:message code="patron.validacion.seleccioneDosOMas" /></legend>
				<label for="spam_email"><spring:message code="correo" /></label>
				<input type="checkbox" class="checkbox" id="spam_email" value="email" name="notificacion[]" validate="required:true, minlength:2" />
			
				<label for="spam_phone"><spring:message code="sms" /></label>
				<input type="checkbox" class="checkbox" id="spam_phone" value="phone" name="notificacion[]" />
			
				<label for="spam_mail"><spring:message code="email" /></label>
				<input type="checkbox" class="checkbox" id="spam_mail" value="mail" name="notificacion[]" />

				<label for="notificacion[]" class="error" style="display:none"><spring:message code="patron.validacion.error.seleccioneDosOMas" /></label>
			</fieldset>
	</fieldset>
	
	<fieldset class="alumnoFieldset">
		<legend>Combo</legend>
		<label for="lenguaje_combo"><spring:message code="patron.validacion.seleccioneUnLenguaje" /></label><br/>
		<select id="lenguaje_combo" name="lenguaje">
			<option value=""></option>
			<option value="1">Java</option>
			<option value="2">C++</option>
			<option value="3">PHP</option>
			<option value="4">Cobol</option>
		</select>
		<label for="lenguaje" class="error" style="display:none"><spring:message code="patron.validacion.seleccioneDosNotificaciones" /></label>
		<br/><br/>
		<label for="lenguaje_combo_multi"><spring:message code="patron.validacion.seleccioneEntreDosYTres" /></label><br/>
		<select id="lenguaje_combo_multi" name="lenguajeMulti" multiple="multiple">
			<option value=""></option>
			<option value="1">Java</option>
			<option value="2">C++</option>
			<option value="3">PHP</option>
			<option value="4">Cobol</option>
		</select>
		<label for="lenguajeMulti" class="error" style="display:none"><spring:message code="patron.validacion.error.seleccioneEntreDosYTres" /></label>
		
	</fieldset>
	
	
	
	
	<fieldset class="alumnoFieldset">
		<legend><spring:message code="componentesRup" /></legend>
		<div class="two-col">
			<div class="col1">
				<label for="autocomplete" class="label"><spring:message code="patron.autocomplete" /></label>
				<input id="autocomplete" name="autocomplete" /><spring:message code="patron.validacion.autocomplete.ejemplo" />
			</div>
		</div>		
		
		<div class="two-col">
			<div class="col1">
				<label for="comboAbueloRemoto" class="label"><spring:message code="localidad" /></label>
				<select id="comboAbueloRemoto" name="provincia" class="rup-combo"><option>&nbsp;</option></select>
			</div>
			<div class="col1">
				<label for="comboPadreRemoto" class="label"><spring:message code="comarca" /></label>
				<select id="comboPadreRemoto" name="comarca" class="rup-combo"><option>&nbsp;</option></select>
			</div>
			<div class="col1">			
				<label for="comboHijoRemoto" class="label"><spring:message code="localidad" /></label>
				<select id="comboHijoRemoto" name="localidad" class="rup-combo"><option>&nbsp;</option></select>
			</div>
		</div>
		<div class="two-col">
			<div class="col1">
				<label for="fecha" class="label"><spring:message code="patron.validacion.fecha" /></label>
				<input id="fecha" name="fecha" />
			</div>
		</div>
		<div class="two-col">
			<div class="col1">
				<label for="multicombo" class="label"><spring:message code="patron.validacion.multicombo" /></label>
				<select id="multicombo" name="multicombo" multiple="multiple"><optgroup label="Actos Administrativos"><option value="3~85">AAAA Planes de Gobierno_ Justicia y Administración Pública</option><option value="3~125">AAAA Planes de Gobierno_Administración Pública y Justicia</option><option value="3~103">AAAA Planes de Gobierno_Cultura</option><option value="3~124">AAAA Planes de Gobierno_Desarrollo Económico y Competitividad</option><option value="3~104">AAAA Planes de Gobierno_Economía y Hacienda</option><option value="3~123">AAAA Planes de Gobierno_Educación, Política Lingüística y Cultura</option><option value="3~105">AAAA Planes de Gobierno_Educación, Universidades e Investigación</option><option value="3~106">AAAA Planes de Gobierno_Empleo y Asuntos Sociales</option><option value="3~122">AAAA Planes de Gobierno_Empleo y Políticas Sociales</option><option value="3~121">AAAA Planes de Gobierno_Hacienda y Finanzas</option><option value="3~107">AAAA Planes de Gobierno_Industria, Innovación, Comercio y Turismo</option><option value="3~108">AAAA Planes de Gobierno_Interior</option><option value="3~120">AAAA Planes de Gobierno_Lehendakaritza</option><option value="3~109">AAAA Planes de Gobierno_Medio Ambiente, Planificación Territorial, Agricultura y Pesca</option><option value="3~119">AAAA Planes de Gobierno_Medio Ambiente y Política Territorial</option><option value="3~110">AAAA Planes de Gobierno_Presidencia</option><option value="3~118">AAAA Planes de Gobierno_Salud</option><option value="3~114">AAAA Planes de Gobierno_Sanidad y Consumo</option><option value="3~117">AAAA Planes de Gobierno_Seguridad</option><option value="3~112">AAAA Planes de Gobierno_Vivienda, Obras Públicas y Transportes</option><option value="3~134">Actos Administrativos_Administración Pública y Justicia</option><option value="3~96">Actos administrativos_Cultura</option><option value="3~133">Actos Administrativos_Desarrollo Económico y Competitividad</option><option value="3~97">Actos administrativos_Economía y Hacienda</option><option value="3~132">Actos Administrativos_Educación, Política Lingüística y Cultura</option><option value="3~98">Actos administrativos_Educación, Universidades e Investigación</option><option value="3~99">Actos administrativos_Empleo y Asuntos Sociales</option><option value="3~131">Actos Administrativos_Empleo y Políticas Sociales</option><option value="3~130">Actos Administrativos_Hacienda y Finanzas</option><option value="3~100">Actos administrativos_Industria, Innovación, Comercio y Turismo</option><option value="3~101">Actos administrativos_Interior</option><option value="3~84">Actos Administrativos_Justicia y Administración Pública</option><option value="3~129">Actos Administrativos_Lehendakaritza</option><option value="3~102">Actos administrativos_Medio Ambiente, Planificación Territorial, Agricultura y Pesca</option><option value="3~128">Actos Administrativos_Medio Ambiente y Política Territorial</option><option value="3~113">Actos administrativos_Presidencia</option><option value="3~127">Actos Administrativos_Salud</option><option value="3~116">Actos administrativos_Sanidad y Consumo</option><option value="3~126">Actos Administrativos_Seguridad</option><option value="3~115">Actos administrativos_Vivienda, Obras Públicas y Transportes</option><option value="3~143">Ayudas_Administración Pública y Justicia</option><option value="3~87">Ayudas_Cultura</option><option value="3~142">Ayudas_Desarrollo Económico y Competitividad</option><option value="3~88">Ayudas_Economía y Hacienda</option><option value="3~141">Ayudas_Educación, Política Lingüística y Cultura</option><option value="3~89">Ayudas_Educación, Universidades e Investigación</option><option value="3~90">Ayudas_Empleo y Asuntos Sociales</option><option value="3~140">Ayudas_Empleo y Políticas Sociales</option><option value="3~139">Ayudas_Hacienda y Finanzas</option><option value="3~91">Ayudas_Industria, Innovación, Comercio y Turismo</option><option value="3~92">Ayudas_Interior</option><option value="3~86">Ayudas_Justicia y Administración Pública</option><option value="3~138">Ayudas_Lehendakaritza</option><option value="3~93">Ayudas_Medio Ambiente, Planificación Territorial, Agricultura y Pesca</option><option value="3~137">Ayudas_Medio Ambiente y Política Territorial</option><option value="3~94">Ayudas_Presidencia</option><option value="3~136">Ayudas_Salud</option><option value="3~111">Ayudas_Sanidad y Consumo</option><option value="3~135">Ayudas_Seguridad</option><option value="3~95">Ayudas_Vivienda, Obras Públicas y Transportes</option></optgroup><optgroup label="Disposiciones Normativas de Caracter General"><option value="1~15">CN Medio Ambiente, Planificación Territorial, Agricultura y Pesca</option><option value="1~42">CN_ Medio Ambiente y Política Territorial</option><option value="1~2">DNCG Cultura</option><option value="1~4">DNCG Economia y Hacienda</option><option value="1~6">DNCG Educación, universidades e investigación</option><option value="1~8">DNCG Empleo y Asuntos Sociales</option><option value="1~10">DNCG Industria, Innovacion, Comercio y Turismo</option><option value="1~12">DNCG Interior</option><option value="1~1">DNCG Justicia y Administración Pública</option><option value="1~17">DNCG Medio Ambiente, P.Territorial, Agricultura y Pesca</option><option value="1~18">DNCG Presidencia</option><option value="1~20">DNCG Sanidad y Consumo</option><option value="1~22">DNCG Vivienda Obras Públicas y Transportes</option><option value="1~41">DNCG_Administración Pública y Justicia</option><option value="1~40">DNCG_Desarrollo Económico y Competitividad</option><option value="1~39">DNCG_Educación, Política Lingüística y Cultura</option><option value="1~38">DNCG_Empleo y Políticas Sociales</option><option value="1~37">DNCG_Hacienda y Finanzas</option><option value="1~36">DNCG_Lehendakaritza</option><option value="1~35">DNCG_Medio Ambiente y Política Territorial</option><option value="1~34">DNCG_Salud</option><option value="1~33">DNCG_Seguridad</option><option value="1~3">OT Cultura</option><option value="1~5">OT Economia y Hacienda</option><option value="1~7">OT Educación, universidades e investigación</option><option value="1~9">OT Empleo y Asuntos Sociales</option><option value="1~11">OT Industria, Innovación, Comercio y Turismo</option><option value="1~13">OT Interior</option><option value="1~14">OT Justicia y Administración Pública</option><option value="1~16">OT Medio Ambiente, Planificación Territorial, Agricultura y Pesca</option><option value="1~19">OT Presidencia</option><option value="1~21">OT Sanidad y Consumo</option><option value="1~23">OT Vivienda Obras Públicas y Transportes</option><option value="1~32">OT_Administración Pública y Justicia</option><option value="1~31">OT_Desarrollo Económico y Competitividad</option><option value="1~30">OT_Educación, Política Lingüística y Cultura</option><option value="1~29">OT_Empleo y Políticas Sociales</option><option value="1~28">OT_Hacienda y Finanzas</option><option value="1~27">OT_Lehendakaritza</option><option value="1~26">OT_Medio Ambiente y Política Territorial</option><option value="1~25">OT_Salud</option><option value="1~24">OT_Seguridad</option></optgroup><optgroup label="Negocios Bilaterales No Contractuales"><option value="2~43">NBNC Convenios, contratos programa y protocolos_ Justicia y Administración Pública</option><option value="2~55">NBNC Convenios, contratos programa y protocolos_Cultura</option><option value="2~56">NBNC Convenios, contratos programa y protocolos_Economía y Hacienda</option><option value="2~57">NBNC Convenios, contratos programa y protocolos_Educación, Universidades e Investigación</option><option value="2~58">NBNC Convenios, contratos programa y protocolos_Empleo y Asuntos Sociales</option><option value="2~59">NBNC Convenios, contratos programa y protocolos_Industria, Innovación, Comercio y Turismo</option><option value="2~60">NBNC Convenios, contratos programa y protocolos_Interior</option><option value="2~61">NBNC Convenios, contratos programa y protocolos_Medio Ambiente, Planificación Territorial, Agricultura y Pesca</option><option value="2~62">NBNC Convenios, contratos programa y protocolos_Presidencia</option><option value="2~63">NBNC Convenios, contratos programa y protocolos_Sanidad y Consumo</option><option value="2~64">NBNC Convenios, contratos programa y protocolos_Vivienda, Obras Públicas y Transportes</option><option value="2~82">NBNC Convenios, Contratos-Programa y Protocolos_Administración Pública y Justicia</option><option value="2~81">NBNC Convenios, Contratos-Programa y Protocolos_Desarrollo Económico y Competitividad</option><option value="2~80">NBNC Convenios, Contratos-Programa y Protocolos_Educación, Política Lingüística y Cultura</option><option value="2~79">NBNC Convenios, Contratos-Programa y Protocolos_Empleo y Políticas Sociales</option><option value="2~78">NBNC Convenios, Contratos-Programa y Protocolos_Hacienda y Finanzas</option><option value="2~77">NBNC Convenios, Contratos-Programa y Protocolos_Lehendakaritza</option><option value="2~76">NBNC Convenios, Contratos-Programa y Protocolos_Medio Ambiente y Política Territorial</option><option value="2~75">NBNC Convenios, Contratos-Programa y Protocolos_Salud</option><option value="2~74">NBNC Convenios, Contratos-Programa y Protocolos_Seguridad</option><option value="2~44">NBNC Encomiendas de gestión (L 30/92)_ Justicia y Administración Pública</option><option value="2~46">NBNC Encomiendas de gestión (L 30/92)_Cultura</option><option value="2~47">NBNC Encomiendas de gestión (L 30/92)_Economía y Hacienda</option><option value="2~48">NBNC Encomiendas de gestión (L 30/92)_Educación, Universidades e Investigación</option><option value="2~49">NBNC Encomiendas de gestión (L 30/92)_Empleo y Asuntos Sociales</option><option value="2~50">NBNC Encomiendas de gestión (L 30/92)_Industria, Innovación, Comercio y Turismo</option><option value="2~51">NBNC Encomiendas de gestión (L 30/92)_Interior</option><option value="2~52">NBNC Encomiendas de gestión (L 30/92)_Medio Ambiente, Planificación Territorial, Agricultura y Pesca</option><option value="2~45">NBNC Encomiendas de gestión (L 30/92)_Presidencia</option><option value="2~53">NBNC Encomiendas de gestión (L 30/92)_Sanidad y Consumo</option><option value="2~54">NBNC Encomiendas de gestión (L 30/92)_Vivienda, Obras Públicas y Transportes</option><option value="2~73">NBNC Encomiendas de Gestión (L30/92)_Administración Pública y Justicia</option><option value="2~72">NBNC Encomiendas de Gestión (L30/92)_Desarrollo Económico y Competitividad</option><option value="2~71">NBNC Encomiendas de Gestión (L30/92)_Educación, Política Lingüística y Cultura</option><option value="2~70">NBNC Encomiendas de Gestión (L30/92)_Empleo y Políticas Sociales</option><option value="2~69">NBNC Encomiendas de Gestión (L30/92)_Hacienda y Finanzas</option><option value="2~68">NBNC Encomiendas de Gestión (L30/92)_Lehendakaritza</option><option value="2~67">NBNC Encomiendas de Gestión (L30/92)_Medio Ambiente y Política Territorial</option><option value="2~66">NBNC Encomiendas de Gestión (L30/92)_Salud</option><option value="2~65">NBNC Encomiendas de Gestión (L30/92)_Seguridad</option><option value="2~83">NBNC Responsabilidad Patrimonial</option></optgroup></select>
			</div>
		</div>
		<div class="two-col">
			<div class="col1">
				<label for="fechaHora" class="label"><spring:message code="patron.validacion.fechaYhora" /></label>
				<input id="fechaHora" name="fechaHora" />
			</div>
		</div>
	</fieldset>
	<input type="submit" value="Validar formulario"  />
	</form:form>