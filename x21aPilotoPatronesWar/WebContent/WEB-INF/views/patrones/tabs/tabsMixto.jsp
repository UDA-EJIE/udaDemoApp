<%--  
 -- Copyright 2012 E.J.I.E., S.A.
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

<h1><spring:message code="tab.mixta" /></h1>

<div id="tabsMixta"></div>

<div id="ejemploVisual" class="estiloMixto" style="display: none;">
	<div class="box-content-2">
		<div class="box-content-5">
			<h1>
				<a rel="permalink"
					href="http://desarrollo.jakina.ejiedes.net:7001/x21aPilotoPatronesWar/patrones/tabsStatic">JSRs
					for Java 7 and Java 8 Approved</a>
			</h1>

			<p>The results of the recent Java JSRs are in, and all have
				passed with all but Apache voting consistently against them. Google
				and Tim Peierls voted against the Java SE 7 and Java SE 8 JSRs,
				supporting the ongoing licensing issues and field-of-use
				restrictions for the TCK.</p>
			<ul>
				<li>Project Coin, <a
					href="http://jcp.org/en/jsr/summary?id=334">JSR 334</a>, <a
					href="http://jcp.org/en/jsr/results?id=5109">passed</a> with 13
					votes to 1 (and 1 abstention)</li>
				<li>Project Lambda, <a
					href="http://jcp.org/en/jsr/summary?id=335">JSR 335</a>, <a
					href="http://jcp.org/en/jsr/results?id=5110">passed</a> with 13
					votes to 1 (and 1 abstention)</li>
				<li>Java SE 7, <a href="http://jcp.org/en/jsr/summary?id=336">JSR
						336</a>, <a href="http://jcp.org/en/jsr/results?id=5111">passed</a>
					with 12 votes to 3</li>
				<li>Java SE 8, <a href="http://jcp.org/en/jsr/summary?id=337">JSR
						337</a>, <a href="http://jcp.org/en/jsr/results?id=5112">passed</a>
					with 12 votes to 3</li>
			</ul>
			<p>
				The comments make interesting reading; Steven Colebourne <a
					href="http://www.jroller.com/scolebourne/entry/java_se_7_8_passed">summarises
					them on one page</a>. Even though the majority voted for the TCK, the
				comments continue to criticise the licensing issues:
			</p>
			<ul>
				<li><b>Google</b>: is voting no because of its licensing terms</li>
				<li><b>SAP AG</b>: While we believe it is important for Java 7
					to proceed now, we want to express our disagreement about Oracle's
					decision regarding the TCK for Apache.</li>
				<li><b>Eclipse</b>: is disappointed with the continuing issues
					around Java licensing</li>
				<li><b>RedHat</b>: is extremely disappointed with the license
					terms and that a more open license has not been adopted by the
					Specification Lead</li>
				<li><b>Credit Suisse</b>: The current battle around licensing
					term, however, reveals that Java never actually was an open
					standard.</li>
			</ul>
			<p>Many of the comments also show that they are begrudgingly
				voting for the JSR if only to move the Java platform forwards from
				the stagnant location it is today. In addition, modularity raises
				its voice in both the Java SE 7 and Java SE 8 discussions, with OSGi
				interoperability being mentioned specifically for the Java SE 8 JSR.</p>
			<p>This is also the first time an umbrella Java SE JSR has been
				put to the vote before it's actually ready. Whilst the work on
				Project Coin and Project Lambda have made significant progress
				recently, and the other inclusions (such as JDBC 4.1) in Java SE 7
				will be welcomed, the contents of the Java SE 8 JSR include a number
				of not-even-started JSRs. Perhaps when Java SE 8 is ready, Oracle
				can point back to the votes and claim that it was agreed by most,
				even if the Java SE 8 content differs markedly from its original
				version.</p>
			<p>
				However, with the licensing issue left unresolved, some are calling
				the JCP “<a
					href="http://communityovercode.com/2010/12/java-no-longer-free-as-in-speech/">Just
					Customers, Please</a>” &ndash; Oracle does not appear to take the JCP
				seriously any more. The last stage in this saga will be Apache's
				resignation from the JCP which will follow up on their <a
					href="https://blogs.apache.org/foundation/entry/statement_by_the_asf_board1">earlier
					threat</a>. Here is what may be the Apache Foundation's last vote on a
				JSR:
			</p>
			<blockquote>
				<p>The Apache Software Foundation must vote no on this JSR.
					While we support the technical contents of the JSR, and earnestly
					support the need for the Java platform to move forward, we cannot
					in good conscience vote for this JSR because:</p>
				<ol>
					<li type="a">This JSR's TCK license includes a "Field of Use"
						restriction that restricts commonplace and mundane use of
						independent implementations, a licensing element that not only is
						prohibited by the JSPA but also has been unanimously rejected by
						the majority of the members of the JCP EC - including Oracle - on
						several occasions. We can only speculate why Oracle included such
						an element, but we believe that an open specification ecosystem
						must be independent of - and protected from - any entity's
						commercial interests.</li>
					<li type="a">On process grounds, this JSR is in conflict with
						it's own TCK license. The JSR explicitly states that Java SE is
						targeted for, among others, embedded deployments. Yet the TCK
						license specifically prohibits such usages (for example, netbooks)
						of tested independent implementations. We find this to be a
						misleading legal trap for potential implementers, and believe that
						any independent implementation that passes the TCK should be able
						to be used and distributed under whatever terms deemed fit by the
						implementer.</li>
					<li type="a">The spec lead has ignored repeated requests from
						multiple EC members for and explanation of both a. and b.</li>
					<li type="a">The spec lead - Oracle - is in breach of their
						obligations under the JSPA by continuing to provide a TCK license
						for Apache Harmony under terms that allow Apache to distribute its
						independent implementation under terms of its choice. We do not
						believe that anyone that willfully fails to meet their contractual
						obligations under the JSPA should be allowed to participate as a
						member in good stating in the JCP. The rules apply to everyone.</li>
				</ol>
				<p>While we understand that it's Oracle's stated intent to move
					forward irrespective of the EC's decision, we urge Oracle to fix
					the above-mentioned issues, and continue to work with the members
					of the JCP within the structure of the JCP to keep Java a vital and
					viable platform.</p>
			</blockquote>
			<p>
				Since Oracle looks unlikely to <a
					href="https://blogs.apache.org/foundation/entry/statement_by_the_asf_board2">honor
					the agreement</a>, a statement by the <a
					href="https://blogs.apache.org/foundation/">Apache Foundation</a>
				is expected in the next week.
			</p>
			<div class="box-bottom"></div>
		</div>
	</div>
	<div class="bottom-corners">
		<div></div>
	</div>
</div>