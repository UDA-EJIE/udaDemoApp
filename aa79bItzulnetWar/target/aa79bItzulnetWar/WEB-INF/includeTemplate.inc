<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="/WEB-INF/tld/security.tld" %>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring.tld" %>
<%@ taglib prefix="form" uri="/WEB-INF/tld/spring-form.tld" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@page import="com.ejie.x38.util.StaticsContainer"%>
<%@page import="com.ejie.aa79b.common.Constants"%>
<c:set var="staticsUrl" value='<%= StaticsContainer.staticsUrl %>'/>

<c:set var="SESSION_LANGUAGE" value="${pageContext.response.locale}"></c:set>
<c:set var="LANGUAGE_ES" value="<%=Constants.LANG_CASTELLANO%>"></c:set>
<c:set var="LANGUAGE_EU" value="<%=Constants.LANG_EUSKERA%>"></c:set>
