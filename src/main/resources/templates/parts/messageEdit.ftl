<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
   Message Edit
</a>
<div class="collapse <#if message?? > show </#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data" >
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <input type="text" name="text" placeholder="введите сообщение"
                       value="<#if message?? > ${message.text} </#if>" class="form-control ${(textError??)?string('is-invalid','')}">
                <#if textError??>
                <div class="invalid-feedback">
                    ${textError}
                </div>
            </#if>
    </div>
    <div class="form-group">
        <input type="text" name="tag" value="<#if message??> ${message.tag} </#if>"
               placeholder="введите тег" class="form-control">
        <#if tagError??>
        <div class="invalid-feedback">
            ${tagError}
        </div>
    </#if>
</div>
<div class="form-group">
    <div class="custom-file">
        <label class="custom-file-label" for="customFile">Choose file</label>
        <input type="file" name="file" id="customFile" >
    </div>
</div>
<input type="hidden" name="id" value="<#if message??>${message.id}</#if>"/>
<div class="form-group">
    <button type="submit" class="btn btn-primary">Save message</button>
</div>

</form>
</div>
</div>