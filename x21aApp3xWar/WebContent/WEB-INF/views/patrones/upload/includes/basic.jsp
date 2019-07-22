
<%@include file="/WEB-INF/includeTemplate.inc"%>


<span class="btn btn-success fileinput-button">
      <i class="glyphicon glyphicon-plus"></i>
      <span><spring:message code="upload.button.addFiles"/></span>
      <input id="basicFileupload" type="file" name="files[]" data-url="../upload" multiple="multiple" />
</span>
<span id="basicFileuploadContext"></span>

