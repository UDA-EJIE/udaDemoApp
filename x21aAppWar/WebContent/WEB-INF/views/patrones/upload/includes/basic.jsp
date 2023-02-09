
<%@include file="/WEB-INF/includeTemplate.inc"%>

<form:form action="../upload/add" id="uploadBasicForm" enctype="multipart/form-data" method="POST" modelAttribute="collection">
<span class="btn btn-success fileinput-button">
      <i class="mdi mdi-plus"></i>
      <span><spring:message code="upload.button.addFiles"/></span>
      <form:input id="basicFileupload" type="file" path="files" multiple="multiple" />
</span>
<span id="basicFileuploadContext"></span>
</form:form>
